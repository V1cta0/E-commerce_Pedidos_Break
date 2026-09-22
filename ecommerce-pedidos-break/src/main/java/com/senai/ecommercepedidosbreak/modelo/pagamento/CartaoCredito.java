package com.senai.ecommercepedidosbreak.modelo.pagamento;

import java.math.BigDecimal;

public class CartaoCredito extends FormaPagamento {

    private static final int MAXIMO_DE_PARCELAS = 12;

    private String numeroMascarado;
    private String bandeira;
    private int quantidadeDeParcelas;

    public CartaoCredito(BigDecimal valor, String numeroMascarado, String bandeira, int quantidadeDeParcelas) {
        super(valor);
        setNumeroMascarado(numeroMascarado);
        setBandeira(bandeira);
        setQuantidadeDeParcelas(quantidadeDeParcelas);
    }

    public void setNumeroMascarado(String numeroMascarado) {
        if (numeroMascarado == null || numeroMascarado.isBlank()) {
            throw new IllegalArgumentException("Número (mascarado) do cartão é obrigatório");
        }
        this.numeroMascarado = numeroMascarado;
    }

    public void setBandeira(String bandeira) {
        if (bandeira == null || bandeira.isBlank()) {
            throw new IllegalArgumentException("Bandeira é obrigatória");
        }
        this.bandeira = bandeira;
    }

    /**
     * @param quantidadeDeParcelas entre 1 e {@value #MAXIMO_DE_PARCELAS}.
     */
    public void setQuantidadeDeParcelas(int quantidadeDeParcelas) {
        if (quantidadeDeParcelas < 1 || quantidadeDeParcelas > MAXIMO_DE_PARCELAS) {
            throw new IllegalArgumentException(
                    "Quantidade de parcelas deve estar entre 1 e " + MAXIMO_DE_PARCELAS);
        }
        this.quantidadeDeParcelas = quantidadeDeParcelas;
    }

    public String getBandeira() {
        return bandeira;
    }

    public int getQuantidadeDeParcelas() {
        return quantidadeDeParcelas;
    }

    @Override
    public boolean processar() {
        // simulação: cartão é aprovado na hora
        System.out.println("Processando cartão " + bandeira + " " + numeroMascarado
                + " em " + quantidadeDeParcelas + "x");
        marcarComoProcessado();
        return true;
    }

    @Override
    public String getResumo() {
        return super.getResumo() + " (" + quantidadeDeParcelas + "x no " + bandeira + ")";
    }
}
