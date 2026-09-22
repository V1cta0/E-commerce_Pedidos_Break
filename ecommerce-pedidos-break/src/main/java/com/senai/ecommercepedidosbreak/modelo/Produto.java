package com.senai.ecommercepedidosbreak.modelo;

import java.math.BigDecimal;

/**
 * Produto do catálogo. O código nasce com o produto e não pode ser alterado
 * depois (sem setter público) — assim como preço e estoque nunca ficam
 * negativos.
 */
public class Produto {

    private static int totalDeProdutosCriados = 0;

    private final String codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidadeEmEstoque;
    private boolean ativo;

    public Produto(String codigo, String nome, String descricao, BigDecimal preco, int quantidadeEmEstoque) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código é obrigatório");
        }
        this.codigo = codigo.trim();
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setQuantidadeEmEstoque(quantidadeEmEstoque);
        this.ativo = true;
        totalDeProdutosCriados++;
    }

    /** Construtor reduzido: produto sem descrição informada ainda. */
    public Produto(String codigo, String nome, BigDecimal preco, int quantidadeEmEstoque) {
        this(codigo, nome, "", preco, quantidadeEmEstoque);
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        this.nome = nome.trim();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao == null ? "" : descricao.trim();
    }

    /**
     * @param preco preço de venda; não pode ser nulo ou negativo.
     */
    public void setPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo: " + preco);
        }
        this.preco = preco;
    }

    /**
     * @param quantidadeEmEstoque quantidade disponível; não pode ser negativa.
     */
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo: " + quantidadeEmEstoque);
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public static int getTotalDeProdutosCriados() {
        return totalDeProdutosCriados;
    }

    public boolean temEstoqueDisponivel(int quantidade) {
        return ativo && quantidade > 0 && quantidade <= quantidadeEmEstoque;
    }

    /**
     * Dá baixa no estoque. Recusa quantidade não positiva e maior que o
     * disponível.
     */
    public void baixarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }
        if (quantidade > quantidadeEmEstoque) {
            throw new IllegalArgumentException(
                    "Estoque insuficiente. Disponível: " + quantidadeEmEstoque);
        }
        this.quantidadeEmEstoque -= quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" + codigo + " - " + nome + ", R$ " + preco
                + ", estoque=" + quantidadeEmEstoque + ", ativo=" + ativo + "}";
    }
}
