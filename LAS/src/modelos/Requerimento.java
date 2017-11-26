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
public class Requerimento {

    private int requerimento_id;
    private int requerimento_tipo;
    private int requerimento_fase_empreendimento;
    private Calendar requerimento_fase_empreendimento_data;
    private String requerimento_num_processo_protocolo;
    private String requerimento_num_licenca_anterior;
    private String requerimento_num_de_guia_de_enquadramento_e_documentacao;
    private Calendar requerimento_data;
    private String requerimento_representante1;
    private String requerimento_representante2;
    private String requerimento_profissional1;
    private String requerimento_profissional2;
    private String requerimento_latn;
    private String requerimento_late;
    private String requerimento_sema;
    private int cliente_id;
    private String cliente_nome;
    private Calendar cliente_data_atividade;
    private int representante_id1;
    private int representante_id2;
    private int profissional_id1;
    private int profissional_id2;

    public int getRequerimento_id() {
        return requerimento_id;
    }

    public void setRequerimento_id(int requerimento_id) {
        this.requerimento_id = requerimento_id;
    }

    public int getRequerimento_tipo() {
        return requerimento_tipo;
    }

    public void setRequerimento_tipo(int requerimento_tipo) {
        this.requerimento_tipo = requerimento_tipo;
    }

    public int getRequerimento_fase_empreendimento() {
        return requerimento_fase_empreendimento;
    }

    public void setRequerimento_fase_empreendimento(int requerimento_fase_empreendimento) {
        this.requerimento_fase_empreendimento = requerimento_fase_empreendimento;
    }

    public Calendar getRequerimento_fase_empreendimento_data() {
        return requerimento_fase_empreendimento_data;
    }

    public void setRequerimento_fase_empreendimento_data(Calendar requerimento_fase_empreendimento_data) {
        this.requerimento_fase_empreendimento_data = requerimento_fase_empreendimento_data;
    }

    public String getRequerimento_num_processo_protocolo() {
        return requerimento_num_processo_protocolo;
    }

    public void setRequerimento_num_processo_protocolo(String requerimento_num_processo_protocolo) {
        this.requerimento_num_processo_protocolo = requerimento_num_processo_protocolo;
    }

    public String getRequerimento_num_licenca_anterior() {
        return requerimento_num_licenca_anterior;
    }

    public void setRequerimento_num_licenca_anterior(String requerimento_num_licenca_anterior) {
        this.requerimento_num_licenca_anterior = requerimento_num_licenca_anterior;
    }

    public String getRequerimento_num_de_guia_de_enquadramento_e_documentacao() {
        return requerimento_num_de_guia_de_enquadramento_e_documentacao;
    }

    public void setRequerimento_num_de_guia_de_enquadramento_e_documentacao(String requerimento_num_de_guia_de_enquadramento_e_documentacao) {
        this.requerimento_num_de_guia_de_enquadramento_e_documentacao = requerimento_num_de_guia_de_enquadramento_e_documentacao;
    }

    public Calendar getRequerimento_data() {
        return requerimento_data;
    }

    public void setRequerimento_data(Calendar requerimento_data) {
        this.requerimento_data = requerimento_data;
    }

    public String getRequerimento_representante1() {
        return requerimento_representante1;
    }

    public void setRequerimento_representante1(String requerimento_representante1) {
        this.requerimento_representante1 = requerimento_representante1;
    }

    public String getRequerimento_representante2() {
        return requerimento_representante2;
    }

    public void setRequerimento_representante2(String requerimento_representante2) {
        this.requerimento_representante2 = requerimento_representante2;
    }

    public String getRequerimento_profissional1() {
        return requerimento_profissional1;
    }

    public void setRequerimento_profissional1(String requerimento_profissional1) {
        this.requerimento_profissional1 = requerimento_profissional1;
    }

    public String getRequerimento_profissional2() {
        return requerimento_profissional2;
    }

    public void setRequerimento_profissional2(String requerimento_profissional2) {
        this.requerimento_profissional2 = requerimento_profissional2;
    }

    public String getRequerimento_latn() {
        return requerimento_latn;
    }

    public void setRequerimento_latn(String requerimento_latn) {
        this.requerimento_latn = requerimento_latn;
    }

    public String getRequerimento_late() {
        return requerimento_late;
    }

    public void setRequerimento_late(String requerimento_late) {
        this.requerimento_late = requerimento_late;
    }

    public String getRequerimento_sema() {
        return requerimento_sema;
    }

    public void setRequerimento_sema(String requerimento_sema) {
        this.requerimento_sema = requerimento_sema;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getCliente_nome() {
        return cliente_nome;
    }

    public void setCliente_nome(String cliente_nome) {
        this.cliente_nome = cliente_nome;
    }

    public Calendar getCliente_data_atividade() {
        return cliente_data_atividade;
    }

    public void setCliente_data_atividade(Calendar cliente_data_atividade) {
        this.cliente_data_atividade = cliente_data_atividade;
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

    public int getProfissional_id2() {
        return profissional_id2;
    }

    public void setProfissional_id2(int profissional_id2) {
        this.profissional_id2 = profissional_id2;
    }

 
 
}
