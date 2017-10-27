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
public class Procuracao {
    
   private int procucacao_id;
   private String procuracao_representante;
   private String procuracao_profissional;
   private String procuracao_cidade;
   private String procuracao_estado;
   private Calendar procuracao_data;
   private int representante_id;
   private int profissional_id;

    public int getProcucacao_id() {
        return procucacao_id;
    }

    public void setProcucacao_id(int procucacao_id) {
        this.procucacao_id = procucacao_id;
    }

    public String getProcuracao_representante() {
        return procuracao_representante;
    }

    public void setProcuracao_representante(String procuracao_representante) {
        this.procuracao_representante = procuracao_representante;
    }

    public String getProcuracao_profissional() {
        return procuracao_profissional;
    }

    public void setProcuracao_profissional(String procuracao_profissional) {
        this.procuracao_profissional = procuracao_profissional;
    }

    public String getProcuracao_cidade() {
        return procuracao_cidade;
    }

    public void setProcuracao_cidade(String procuracao_cidade) {
        this.procuracao_cidade = procuracao_cidade;
    }

    public String getProcuracao_estado() {
        return procuracao_estado;
    }

    public void setProcuracao_estado(String procuracao_estado) {
        this.procuracao_estado = procuracao_estado;
    }

    public Calendar getProcuracao_data() {
        return procuracao_data;
    }

    public void setProcuracao_data(Calendar procuracao_data) {
        this.procuracao_data = procuracao_data;
    }

    public int getRepresentante_id() {
        return representante_id;
    }

    public void setRepresentante_id(int representante_id) {
        this.representante_id = representante_id;
    }

    public int getProfissional_id() {
        return profissional_id;
    }

    public void setProfissional_id(int profissional_id) {
        this.profissional_id = profissional_id;
    }

 
    
}
