package com.senai.ecommercepedidosbreak.modelo;

/**
 * Cliente do e-commerce. É uma Pessoa (herança) e opcionalmente tem um
 * Endereco (associação 0..1).
 */
public class Cliente extends Pessoa {

    private static int totalDeClientesCriados = 0;

    private String email;
    private String telefone;
    private Endereco endereco;

    public Cliente(String nome, String cpf, String email, String telefone) {
        super(nome, cpf);
        setEmail(email);
        setTelefone(telefone);
        totalDeClientesCriados++;
    }

    /** Construtor reduzido: cliente sem telefone informado ainda. */
    public Cliente(String nome, String cpf, String email) {
        this(nome, cpf, email, null);
    }

    /**
     * @param email e-mail do cliente; precisa conter "@".
     */
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido: " + email);
        }
        this.email = email.trim();
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone == null ? null : telefone.trim();
    }

    /** O endereço é opcional (0..1): pode ser cadastrado depois. */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public static int getTotalDeClientesCriados() {
        return totalDeClientesCriados;
    }

    @Override
    public String getIdentificacao() {
        return getNome() + " (CPF " + getDocumento() + ")";
    }

    @Override
    public String toString() {
        return "Cliente{" + getIdentificacao() + ", email=" + email
                + (endereco != null ? ", endereco=" + endereco : "") + "}";
    }
}
