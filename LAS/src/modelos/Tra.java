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
public class Tra {
    
    private int tra_id;
    private int tra_localizado;
    private int tra_instalado;
    private int tra_declaracao;
    private Calendar tra_data;
    private String tra_cidade;
    private String tra_estado;
    private String tra_cliente;
    private String tra_representante1;
    private String tra_representante2;
    private String tra_profissional1;
    private String tra_n_artigo;
    private int cliente_id;
    private int representante_id1;
    private int representante_id2;
    private int profissional_id1;

    public int getTra_id() {
        return tra_id;
    }

    public void setTra_id(int tra_id) {
        this.tra_id = tra_id;
    }

    public int getTra_localizado() {
        return tra_localizado;
    }

    public void setTra_localizado(int tra_localizado) {
        this.tra_localizado = tra_localizado;
    }

    public int getTra_instalado() {
        return tra_instalado;
    }

    public void setTra_instalado(int tra_instalado) {
        this.tra_instalado = tra_instalado;
    }

    public int getTra_declaracao() {
        return tra_declaracao;
    }

    public void setTra_declaracao(int tra_declaracao) {
        this.tra_declaracao = tra_declaracao;
    }

    public Calendar getTra_data() {
        return tra_data;
    }

    public void setTra_data(Calendar tra_data) {
        this.tra_data = tra_data;
    }

    public String getTra_cidade() {
        return tra_cidade;
    }

    public void setTra_cidade(String tra_cidade) {
        this.tra_cidade = tra_cidade;
    }

    public String getTra_estado() {
        return tra_estado;
    }

    public void setTra_estado(String tra_estado) {
        this.tra_estado = tra_estado;
    }

    public String getTra_cliente() {
        return tra_cliente;
    }

    public void setTra_cliente(String tra_cliente) {
        this.tra_cliente = tra_cliente;
    }

    public String getTra_representante1() {
        return tra_representante1;
    }

    public void setTra_representante1(String tra_representante1) {
        this.tra_representante1 = tra_representante1;
    }

    public String getTra_representante2() {
        return tra_representante2;
    }

    public void setTra_representante2(String tra_representante2) {
        this.tra_representante2 = tra_representante2;
    }

    public String getTra_profissional1() {
        return tra_profissional1;
    }

    public void setTra_profissional1(String tra_profissional1) {
        this.tra_profissional1 = tra_profissional1;
    }

    public String getTra_n_artigo() {
        return tra_n_artigo;
    }

    public void setTra_n_artigo(String tra_n_artigo) {
        this.tra_n_artigo = tra_n_artigo;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getRepresentante_id1() {
        return representante_id1;
    }

    public void setRepresentante_id1(int representante_id1) {
        this.representante_id1 = representante_id1;
    }

    public int getRepresentante_id2() {
        return representante_id2;
    }

    public void setRepresentante_id2(int representante_id2) {
        this.representante_id2 = representante_id2;
    }

    public int getProfissional_id1() {
        return profissional_id1;
    }

    public void setProfissional_id1(int profissional_id1) {
        this.profissional_id1 = profissional_id1;
    }

 
}
