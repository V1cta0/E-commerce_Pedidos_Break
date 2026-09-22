package com.senai.ecommercepedidosbreak.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

import com.senai.ecommercepedidosbreak.modelo.ItemPedido;

/**
 * Concentra os cálculos e formatações do módulo de pedidos. Todos os
 * métodos são estáticos; a classe não pode ser instanciada.
 */
public final class PedidoUtils {

    private static final Random ALEATORIO = new Random();

    private static final BigDecimal VALOR_FRETE_POR_KG = new BigDecimal("8.00");
    private static final BigDecimal FRETE_MINIMO = new BigDecimal("15.00");
    private static final BigDecimal FRETE_GRATIS_ACIMA_DE = new BigDecimal("300.00");

    private static final BigDecimal DESCONTO_MAXIMO = new BigDecimal("100.00");

    private PedidoUtils() {
    }

    /**
     * Gera um número de pedido no padrão PED-AAAA-NNNNN.
     */
    public static String gerarNumeroDoPedido(int ano) {
        int sequencial = ALEATORIO.nextInt(100000);
        return String.format("PED-%d-%05d", ano, sequencial);
    }

    /**
     * Soma o subtotal (preço praticado x quantidade) de todos os itens do
     * pedido.
     */
    public static BigDecimal calcularSubtotal(List<ItemPedido> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Pedido sem itens não tem subtotal");
        }
        BigDecimal subtotal = BigDecimal.ZERO;
        for (ItemPedido item : itens) {
            subtotal = subtotal.add(item.calcularSubtotal());
        }
        return subtotal;
    }

    /**
     * Cobra por quilo iniciado (arredondamento para cima), respeita o frete
     * mínimo e zera acima do valor de frete grátis.
     */
    public static BigDecimal calcularFrete(double pesoTotalEmKg, BigDecimal subtotal) {
        if (pesoTotalEmKg <= 0) {
            throw new IllegalArgumentException("Peso deve ser maior que zero");
        }
        if (subtotal != null && subtotal.compareTo(FRETE_GRATIS_ACIMA_DE) >= 0) {
            return BigDecimal.ZERO;
        }
        int quilosCobrados = (int) Math.ceil(pesoTotalEmKg);
        BigDecimal frete = VALOR_FRETE_POR_KG.multiply(BigDecimal.valueOf(quilosCobrados));
        return frete.max(FRETE_MINIMO);
    }

    /**
     * Aplica a taxa percentual de desconto sobre o subtotal, respeitando o
     * teto máximo de desconto.
     */
    public static BigDecimal calcularDesconto(BigDecimal subtotal, BigDecimal taxaPercentual) {
        if (subtotal == null || subtotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Subtotal inválido");
        }
        if (taxaPercentual == null || taxaPercentual.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Taxa de desconto inválida");
        }
        BigDecimal desconto = subtotal.multiply(taxaPercentual)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return desconto.min(DESCONTO_MAXIMO);
    }

    /**
     * Devolve a linha do recibo alinhada em colunas.
     */
    public static String formatarLinhaDoRecibo(String nomeProduto, int quantidade,
                                                BigDecimal precoUnitario, BigDecimal subtotalDoItem) {
        return String.format("%-20s x%-3d R$ %-10s R$ %s",
                nomeProduto, quantidade, precoUnitario, subtotalDoItem);
    }

    /**
     * Monta o recibo completo, percorrendo todos os itens.
     */
    public static String montarRecibo(List<ItemPedido> itens) {
        StringBuilder recibo = new StringBuilder();
        for (ItemPedido item : itens) {
            recibo.append(formatarLinhaDoRecibo(
                            item.getProduto().getNome(), item.getQuantidade(),
                            item.getPrecoPraticado(), item.calcularSubtotal()))
                    .append(System.lineSeparator());
        }
        recibo.append("TOTAL: R$ ").append(calcularSubtotal(itens));
        return recibo.toString();
    }

    /**
     * Normaliza nome de cliente: remove espaços nas pontas e padroniza a
     * capitalização (primeira letra de cada palavra em maiúscula).
     */
    public static String normalizarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        String[] palavras = nome.trim().toLowerCase().split("\\s+");
        StringBuilder normalizado = new StringBuilder();
        for (String palavra : palavras) {
            normalizado.append(Character.toUpperCase(palavra.charAt(0)))
                    .append(palavra.substring(1))
                    .append(" ");
        }
        return normalizado.toString().trim();
    }
}
