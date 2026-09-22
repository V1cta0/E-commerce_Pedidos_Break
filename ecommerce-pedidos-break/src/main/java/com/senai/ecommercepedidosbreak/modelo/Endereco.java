package com.senai.ecommercepedidosbreak.modelo;

/**
 * Endereço de um Cliente. Relacionamento 0..1: um cliente pode não ter
 * endereço cadastrado ainda.
 */
public class Endereco {

    private final String logradouro;
    private final String numero;
    private final String cidade;
    private final String uf;
    private final String cep;

    public Endereco(String logradouro, String numero, String cidade, String uf, String cep) {
        if (logradouro == null || logradouro.isBlank()) {
            throw new IllegalArgumentException("Logradouro é obrigatório");
        }
        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }
        if (uf == null || uf.trim().length() != 2) {
            throw new IllegalArgumentException("UF deve ter 2 letras: " + uf);
        }
        this.logradouro = logradouro.trim();
        this.numero = numero == null ? "S/N" : numero.trim();
        this.cidade = cidade.trim();
        this.uf = uf.trim().toUpperCase();
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return logradouro + ", " + numero + " - " + cidade + "/" + uf
                + (cep != null ? " CEP " + cep : "");
    }
}
