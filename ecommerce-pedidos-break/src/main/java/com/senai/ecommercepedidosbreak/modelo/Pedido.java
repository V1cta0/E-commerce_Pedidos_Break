package com.senai.ecommercepedidosbreak.modelo;

import com.senai.ecommercepedidosbreak.modelo.pagamento.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Pedido do e-commerce.
 * <p>
 * Relacionamentos:
 * <ul>
 *   <li>Pedido -&gt; Cliente: associação obrigatória (1). O construtor recusa cliente nulo.</li>
 *   <li>Pedido ◆ ItemPedido: composição. O pedido cria os próprios itens.</li>
 *   <li>Pedido -&gt; FormaPagamento: associação opcional (0..1), só existe após o pagamento.</li>
 * </ul>
 */
public class Pedido {

    private final String numero;
    private final Cliente cliente;
    private final LocalDateTime data;
    private SituacaoPedido situacao;
    private final List<ItemPedido> itens = new ArrayList<>();
    private FormaPagamento formaPagamento;

    public Pedido(String numero, Cliente cliente) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número do pedido é obrigatório");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("Pedido exige um cliente");
        }
        this.numero = numero;
        this.cliente = cliente;
        this.data = LocalDateTime.now();
        this.situacao = SituacaoPedido.ABERTO;
    }

    /**
     * O pedido CRIA o próprio item (composição): quem chama entrega o
     * produto e a quantidade, nunca um ItemPedido pronto.
     */
    public void adicionarItem(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto é obrigatório");
        }
        if (!produto.temEstoqueDisponivel(quantidade)) {
            throw new IllegalStateException("Estoque insuficiente para " + produto.getNome());
        }
        itens.add(new ItemPedido(produto, quantidade));
        produto.baixarEstoque(quantidade);
    }

    /** Nunca devolve a lista interna — sempre uma cópia somente leitura. */
    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public BigDecimal calcularValorTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedido item : itens) {
            total = total.add(item.calcularSubtotal());
        }
        return total;
    }

    /**
     * Associa uma forma de pagamento e marca o pedido como pago. Um pedido
     * sem itens (multiplicidade 1..*) não pode ser pago.
     */
    public void pagar(FormaPagamento formaPagamento) {
        if (itens.isEmpty()) {
            throw new IllegalStateException("Não é possível pagar um pedido sem itens");
        }
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento é obrigatória para pagar");
        }
        if (!formaPagamento.processar()) {
            throw new IllegalStateException("Pagamento não foi aprovado");
        }
        this.formaPagamento = formaPagamento;
        this.situacao = SituacaoPedido.PAGO;
    }

    public void finalizar() {
        if (itens.isEmpty()) {
            throw new IllegalStateException("Não é possível finalizar um pedido sem itens");
        }
        if (situacao != SituacaoPedido.PAGO) {
            throw new IllegalStateException("Pedido precisa estar pago antes de finalizar");
        }
        this.situacao = SituacaoPedido.ENVIADO;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public SituacaoPedido getSituacao() {
        return situacao;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    @Override
    public String toString() {
        return "Pedido{" + numero + ", cliente=" + cliente.getNome()
                + ", situacao=" + situacao + ", itens=" + itens.size()
                + ", total=R$ " + calcularValorTotal() + "}";
    }
}
