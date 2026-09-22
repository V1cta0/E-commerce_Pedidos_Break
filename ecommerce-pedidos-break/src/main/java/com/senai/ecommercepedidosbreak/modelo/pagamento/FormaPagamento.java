package com.senai.ecommercepedidosbreak.modelo.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Forma de pagamento de um pedido. Ninguém de fora instancia diretamente —
 * só as filhas concretas, por isso o construtor é protected.
 */
public abstract class FormaPagamento {

    private BigDecimal valor;
    private LocalDateTime dataDoPagamento;

    protected FormaPagamento(BigDecimal valor) {
        setValor(valor);
    }

    public void setValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do pagamento deve ser positivo");
        }
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDoPagamento() {
        return dataDoPagamento;
    }

    protected void marcarComoProcessado() {
        this.dataDoPagamento = LocalDateTime.now();
    }

    /** Cada forma de pagamento processa do seu próprio jeito. */
    public abstract boolean processar();

    /** Comportamento comum, herdado pronto por todas as filhas. */
    public String getResumo() {
        return String.format("%s no valor de R$ %s", getClass().getSimpleName(), valor);
    }
}
