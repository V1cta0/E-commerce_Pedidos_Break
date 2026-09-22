package com.senai.ecommercepedidosbreak.app;

import com.senai.ecommercepedidosbreak.modelo.Cliente;
import com.senai.ecommercepedidosbreak.modelo.Endereco;
import com.senai.ecommercepedidosbreak.modelo.Funcionario;
import com.senai.ecommercepedidosbreak.modelo.Pedido;
import com.senai.ecommercepedidosbreak.modelo.Produto;
import com.senai.ecommercepedidosbreak.modelo.pagamento.FormaPagamento;
import com.senai.ecommercepedidosbreak.modelo.pagamento.Pix;
import com.senai.ecommercepedidosbreak.modelo.pagamento.Boleto;
import com.senai.ecommercepedidosbreak.modelo.pagamento.CartaoCredito;
import com.senai.ecommercepedidosbreak.util.PedidoUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe de console que exercita o modelo de domínio inteiro e comprova,
 * na prática, que cada regra de negócio funciona.
 */
public class Aplicacao {

    public static void main(String[] args) {
        System.out.println("=== PedidoUtils (Aula 03) ===");
        String numero = PedidoUtils.gerarNumeroDoPedido(2026);
        System.out.println("Número gerado: " + numero);
        System.out.println("Frete (5kg, subtotal baixo): R$ "
                + PedidoUtils.calcularFrete(5, new BigDecimal("100.00")));
        System.out.println("Frete (5kg, subtotal alto -> grátis): R$ "
                + PedidoUtils.calcularFrete(5, new BigDecimal("500.00")));
        System.out.println("Desconto de 10% sobre R$ 200 (teto R$ 100): R$ "
                + PedidoUtils.calcularDesconto(new BigDecimal("200.00"), new BigDecimal("10")));

        System.out.println("\n=== Modelo de domínio (Aulas 04, 05 e 07) ===");
        Produto teclado = new Produto("TEC-001", "Teclado mecânico", "Teclado ABNT2", new BigDecimal("350.00"), 10);
        Produto mouse = new Produto("MOU-001", "Mouse sem fio", new BigDecimal("120.00"), 20);
        System.out.println(teclado);
        System.out.println(mouse);

        Cliente cliente = new Cliente(PedidoUtils.normalizarNome("  ana  paula souza "), "12345678900", "ana@email.com");
        cliente.setEndereco(new Endereco("Rua das Flores", "123", "Ji-Paraná", "RO", "76900-000"));
        System.out.println(cliente);

        Funcionario funcionario = new Funcionario("Carlos Lima", "98765432100", "F-042", "Atendente");
        System.out.println(funcionario);
        System.out.println("Resumo do funcionário: " + funcionario.getResumo());

        Pedido pedido = new Pedido(numero, cliente);
        pedido.adicionarItem(teclado, 2);
        pedido.adicionarItem(mouse, 1);
        System.out.println(pedido);
        pedido.getItens().forEach(System.out::println);
        System.out.println("Total calculado: R$ " + pedido.calcularValorTotal());

        System.out.println("\n=== Hierarquia de pagamento (Aula 06) polimórfica ===");
        FormaPagamento[] pagamentos = {
                new Pix(new BigDecimal("150.00"), "cliente@email.com"),
                new Boleto(new BigDecimal("300.00"), LocalDate.now().plusDays(3)),
                new CartaoCredito(new BigDecimal("899.90"), "**** 1234", "Visa", 3)
        };
        for (FormaPagamento pagamento : pagamentos) {
            System.out.println(pagamento.getResumo());
            pagamento.processar();
        }

        pedido.pagar(pagamentos[0]);
        System.out.println("Pedido após pagamento: " + pedido);

        System.out.println("\n=== Comprovando que o modelo recusa estados inválidos ===");
        testarFalha("Preço negativo", () -> teclado.setPreco(new BigDecimal("-10.00")));
        testarFalha("Estoque negativo", () -> teclado.setQuantidadeEmEstoque(-1));
        testarFalha("Nome de pessoa em branco", () -> cliente.setNome("   "));
        testarFalha("E-mail sem @", () -> cliente.setEmail("ana-email.com"));
        testarFalha("Pedido sem cliente", () -> new Pedido("PED-2026-00001", null));
        testarFalha("Item com quantidade zero", () -> pedido.adicionarItem(mouse, 0));
        testarFalha("Item com quantidade maior que o estoque", () -> pedido.adicionarItem(mouse, 999));
        testarFalha("Pagar pedido sem itens", () -> new Pedido("PED-2026-00002", cliente).pagar(pagamentos[1]));
        testarFalha("Cartão com mais de 12 parcelas", () -> new CartaoCredito(new BigDecimal("10.00"), "**** 0000", "Visa", 13));
        testarFalha("Boleto com vencimento no passado", () -> new Boleto(new BigDecimal("10.00"), LocalDate.now().minusDays(1)));

        System.out.println("\nQuantos produtos foram criados no total? " + Produto.getTotalDeProdutosCriados());
        System.out.println("Quantos clientes foram criados no total? " + Cliente.getTotalDeClientesCriados());
    }

    private static void testarFalha(String descricao, Runnable acao) {
        try {
            acao.run();
            System.out.println("FALHOU (deveria ter recusado): " + descricao);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("OK: recusou \"" + descricao + "\" -> " + e.getMessage());
        }
    }
}
