/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Calendar;

/**
 *
 * @author Jean
 */
public class Recibo {
    
    private int recibo_id;
    private String recibo_cidade;
    private String recibo_estado;
    private Calendar recibo_data;
    private int cliente_id;
    private int profissional_id;
    private String recibo_cliente;
    private String recibo_profissional;
    private String recibo_valor;

    public int getRecibo_id() {
        return recibo_id;
    }

    public void setRecibo_id(int recibo_id) {
        this.recibo_id = recibo_id;
    }

    public String getRecibo_cidade() {
        return recibo_cidade;
    }

    public void setRecibo_cidade(String recibo_cidade) {
        this.recibo_cidade = recibo_cidade;
    }

    public String getRecibo_estado() {
        return recibo_estado;
    }

    public void setRecibo_estado(String recibo_estado) {
        this.recibo_estado = recibo_estado;
    }

    public Calendar getRecibo_data() {
        return recibo_data;
    }

    public void setRecibo_data(Calendar recibo_data) {
        this.recibo_data = recibo_data;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getProfissional_id() {
        return profissional_id;
    }

    public void setProfissional_id(int profissional_id) {
        this.profissional_id = profissional_id;
    }

    public String getRecibo_cliente() {
        return recibo_cliente;
    }

    public void setRecibo_cliente(String recibo_cliente) {
        this.recibo_cliente = recibo_cliente;
    }

    public String getRecibo_profissional() {
        return recibo_profissional;
    }

    public void setRecibo_profissional(String recibo_profissional) {
        this.recibo_profissional = recibo_profissional;
    }

    public String getRecibo_valor() {
        return recibo_valor;
    }

    public void setRecibo_valor(String recibo_valor) {
        this.recibo_valor = recibo_valor;
    }

 
}
