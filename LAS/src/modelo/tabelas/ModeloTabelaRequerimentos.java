/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.tabelas;

import java.util.ArrayList;
import modelos.Requerimento;

/**
 *
 * @author Jean
 */
public class ModeloTabelaRequerimentos extends javax.swing.table.AbstractTableModel {

    ArrayList<Requerimento> listaRequerimentos = new ArrayList();
    Requerimento requerimento;

    @Override
    public int getRowCount() {
        return listaRequerimentos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Cliente";
            case 1:
                return "1° Representante";
            case 2:
                return "2° Representante";
            case 3:
                return "1° Profissional";
            case 4:
                return "2° Profissional";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        int dia;
        int mes;
        int ano;

        requerimento = listaRequerimentos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return requerimento.getCliente_nome();
            case 1:
                return requerimento.getRequerimento_representante1();
            case 2:
                return requerimento.getRequerimento_representante2();
            case 3:
                return requerimento.getRequerimento_profissional1();
            case 4:
                return requerimento.getRequerimento_profissional2();

        }
        return null;
    }

    // MÉTODOS PARA ALIMENTAÇÃO DA LISTA
    public void inserirListaRequerimentos(ArrayList<Requerimento> listaRequerimentos) {
        this.listaRequerimentos = listaRequerimentos;
    }

    public ArrayList<Requerimento> retornaListaRequerimentos() {
        return this.listaRequerimentos;
    }
}
