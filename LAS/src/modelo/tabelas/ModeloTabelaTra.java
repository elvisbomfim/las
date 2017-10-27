/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.tabelas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import modelos.Tra;

/**
 *
 * @author alunov2
 */
public class ModeloTabelaTra extends javax.swing.table.AbstractTableModel {

    ArrayList<Tra> listaTra = new ArrayList();
    DecimalFormat form = new DecimalFormat("00");
    Tra tra;

    @Override
    public int getRowCount() {
        return listaTra.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Cliente";
            case 1:
                return "Profissional";
            case 2:
                return "1° Representante";
            case 3:
                return "2° Representante";
            case 4:
                return "Data Relatório";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        int dia;
        int mes;
        int ano;

        tra = listaTra.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return tra.getTra_cliente();
            case 1:
                return tra.getTra_profissional1();
            case 2:
                return tra.getTra_representante1();
            case 3:
                return tra.getTra_representante2();
            case 4:
                return form.format(tra.getTra_data().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(tra.getTra_data().get(Calendar.MONTH) + 1) + "/" + tra.getTra_data().get(Calendar.YEAR);

        }
        return null;
    }

    // MÉTODOS PARA ALIMENTAÇÃO DA LISTA
    public void inserirListaTra(ArrayList<Tra> listaTra) {
        this.listaTra = listaTra;
    }

    public ArrayList<Tra> retornaListaTra() {
        return this.listaTra;
    }

}
