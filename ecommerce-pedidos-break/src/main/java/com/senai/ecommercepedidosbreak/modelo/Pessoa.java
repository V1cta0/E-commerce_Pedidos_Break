package com.senai.ecommercepedidosbreak.modelo;

/**
 * Conceito abstrato que representa qualquer pessoa do sistema (Cliente,
 * Funcionario, etc). Concentra nome e documento, validados, e obriga cada
 * tipo concreto a saber se identificar do seu próprio jeito.
 */
public abstract class Pessoa {

    private String nome;
    private String documento;

    protected Pessoa(String nome, String documento) {
        setNome(nome);
        setDocumento(documento);
    }

    /**
     * @param nome nome da pessoa; não pode ser nulo ou em branco.
     */
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        this.nome = nome.trim();
    }

    /**
     * @param documento documento (CPF/CNPJ) da pessoa; aceita apenas dígitos.
     */
    public void setDocumento(String documento) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Documento é obrigatório");
        }
        String normalizado = documento.replaceAll("\\D", "");
        if (normalizado.isEmpty()) {
            throw new IllegalArgumentException("Documento deve conter dígitos: " + documento);
        }
        this.documento = normalizado;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    /**
     * Cada tipo de pessoa se identifica do seu próprio jeito.
     * @return identificação legível da pessoa.
     */
    public abstract String getIdentificacao();

    /**
     * Comportamento comum, herdado pronto por todas as filhas.
     */
    public String getResumo() {
        return nome + " (" + documento + ")";
    }
}
