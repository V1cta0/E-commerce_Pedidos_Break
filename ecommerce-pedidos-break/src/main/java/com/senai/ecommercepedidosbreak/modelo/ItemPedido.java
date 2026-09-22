package com.senai.ecommercepedidosbreak.modelo;

import java.math.BigDecimal;

/**
 * Item de um pedido. Guarda uma associação obrigatória com o Produto e o
 * preço praticado no momento da compra — que não muda mesmo que o preço do
 * produto mude depois (por isso não tem setter).
 */
public class ItemPedido {

    private final Produto produto;
    private int quantidade;
    private final BigDecimal precoPraticado;

    public ItemPedido(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Item de pedido exige um produto");
        }
        this.produto = produto;
        setQuantidade(quantidade);
        this.precoPraticado = produto.getPreco();
    }

    /**
     * @param quantidade quantidade do item; precisa ser maior que zero.
     */
    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero: " + quantidade);
        }
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoPraticado() {
        return precoPraticado;
    }

    public BigDecimal calcularSubtotal() {
        return precoPraticado.multiply(BigDecimal.valueOf(quantidade));
    }

    @Override
    public String toString() {
        return String.format("%-20s x%-3d R$ %-10s = R$ %s",
                produto.getNome(), quantidade, precoPraticado, calcularSubtotal());
    }
}
