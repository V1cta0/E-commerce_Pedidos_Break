package com.senai.ecommercepedidosbreak.modelo;

public class Pedido {

    private int id;
    private int clienteId;
    private String data;
    private String status;
    private double valorTotal;

    public Pedido() {
    }

    public Pedido(int id, int clienteId, String data, String status, double valorTotal) {
        this.id = id;
        this.clienteId = clienteId;
        this.data = data;
        this.status = status;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}