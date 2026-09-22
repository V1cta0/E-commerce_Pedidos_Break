package com.senai.ecommercepedidosbreak.modelo;

/**
 * Funcionário da loja. Segunda filha de Pessoa: reaproveita nome, documento
 * e validações, sem duplicar nada.
 */
public class Funcionario extends Pessoa {

    private String matricula;
    private String cargo;

    public Funcionario(String nome, String cpf, String matricula, String cargo) {
        super(nome, cpf);
        setMatricula(matricula);
        setCargo(cargo);
    }

    public void setMatricula(String matricula) {
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula é obrigatória");
        }
        this.matricula = matricula.trim();
    }

    public void setCargo(String cargo) {
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("Cargo é obrigatório");
        }
        this.cargo = cargo.trim();
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String getIdentificacao() {
        return getNome() + " - matrícula " + matricula;
    }

    @Override
    public String getResumo() {
        return super.getResumo() + " - " + cargo;
    }

    @Override
    public String toString() {
        return "Funcionario{" + getIdentificacao() + ", cargo=" + cargo + "}";
    }
}
