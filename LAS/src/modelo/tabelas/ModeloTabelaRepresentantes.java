/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.tabelas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import modelos.Representante;

/**
 *
 * @author Jean
 */
public class ModeloTabelaRepresentantes extends javax.swing.table.AbstractTableModel {

    ArrayList<Representante> listaRepresentantes = new ArrayList();
    DecimalFormat form = new DecimalFormat("00");
    Representante representante;

    @Override
    public int getRowCount() {
        return listaRepresentantes.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Nome do Representante";
            case 1:
                return "Cpf";
            case 2:
                return "Telefone";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        int dia;
        int mes;
        int ano;

        representante = listaRepresentantes.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return representante.getRepresentante_nome();
            case 1:
                return representante.getRepresentante_cpf();
            case 2:
                return representante.getRepresentante_telefone();
        }
        return null;
    }

    // MÉTODOS PARA ALIMENTAÇÃO DA LISTA
    public void inserirListaRepresentantes(ArrayList<Representante> listaRepresentantes) {
        this.listaRepresentantes = listaRepresentantes;
    }

    public ArrayList<Representante> retornaListaRepresentantes() {
        return this.listaRepresentantes;
    }

}
