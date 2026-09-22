package com.senai.ecommercepedidosbreak.modelo.pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Boleto extends FormaPagamento {

    private String codigoDeBarras;
    private LocalDate dataDeVencimento;

    public Boleto(BigDecimal valor, LocalDate dataDeVencimento) {
        super(valor);
        setDataDeVencimento(dataDeVencimento);
        this.codigoDeBarras = gerarCodigoDeBarras();
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        if (dataDeVencimento == null || dataDeVencimento.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de vencimento não pode estar no passado");
        }
        this.dataDeVencimento = dataDeVencimento;
    }

    private String gerarCodigoDeBarras() {
        return "23790" + System.nanoTime() % 100000000000L;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    @Override
    public boolean processar() {
        // simulação: boleto fica pendente de compensação
        System.out.println("Boleto gerado, código " + codigoDeBarras
                + ", vencimento em " + dataDeVencimento);
        marcarComoProcessado();
        return true;
    }

    @Override
    public String getResumo() {
        return super.getResumo() + " (vence em " + dataDeVencimento + ")";
    }
}
