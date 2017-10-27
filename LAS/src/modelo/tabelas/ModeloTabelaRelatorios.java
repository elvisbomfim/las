/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.tabelas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import modelos.RelatorioPrincipal;

/**
 *
 * @author Jean
 */
public class ModeloTabelaRelatorios extends javax.swing.table.AbstractTableModel {

    ArrayList<RelatorioPrincipal> listaR = new ArrayList();
    DecimalFormat form = new DecimalFormat("00");
    RelatorioPrincipal r;

    @Override
    public int getRowCount() {
        return listaR.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Categoria";
            case 1:
                return "Cliente";
            case 2:
                return "Profissional";
            case 3:
                return "Data Relatório";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        int dia;
        int mes;
        int ano;

        r = listaR.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return r.getCATEGORIA_NOME();
            case 1:
                return r.getCLIENTE_NOME();
            case 2:
                return r.getPROFISSIONAL_NOME();
            case 3:
                return form.format(r.getRELATORIO_DATA_ATUAL().get(Calendar.DAY_OF_MONTH))+"/"+form.format(r.getRELATORIO_DATA_ATUAL().get(Calendar.MONTH)+1)+"/"+r.getRELATORIO_DATA_ATUAL().get(Calendar.YEAR);
        }
        return null;
    }

    // MÉTODOS PARA ALIMENTAÇÃO DA LISTA
    public void inserirListaRelatorioPrincipal(ArrayList<RelatorioPrincipal> listaR) {
        this.listaR = listaR;
    }

    public ArrayList<RelatorioPrincipal> retornaListaRelatorioPrincipal() {
        return this.listaR;
    }
}
