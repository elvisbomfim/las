/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.tabelas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import modelos.Recibo;


/**
 *
 * @author alunov2
 */
public class ModeloTabelaRecibos extends  javax.swing.table.AbstractTableModel
{
    ArrayList<Recibo> listaRecibos = new ArrayList();
    DecimalFormat form = new DecimalFormat("00");
    Recibo recibo;
    @Override
    public int getRowCount() {
        return listaRecibos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
        switch(columnIndex)
        {
            case 0: return "Cliente";
            case 1: return "Profissional";
            case 2: return "Data Relatório";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        int dia;
        int mes;
        int ano;
        
        recibo = listaRecibos.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0: return recibo.getRecibo_cliente();
            case 1: return recibo.getRecibo_profissional();
            case 2: return form.format(recibo.getRecibo_data().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(recibo.getRecibo_data().get(Calendar.MONTH) + 1) + "/" + recibo.getRecibo_data().get(Calendar.YEAR);

        }
        return null;
    }
    
    
   // MÉTODOS PARA ALIMENTAÇÃO DA LISTA
    public void inserirListaRecibos(ArrayList<Recibo> listaRecibos)
    {
        this.listaRecibos = listaRecibos;
    }
    
    public ArrayList<Recibo> retornaListaRecibos()
    {
        return this.listaRecibos;
    }
    
}