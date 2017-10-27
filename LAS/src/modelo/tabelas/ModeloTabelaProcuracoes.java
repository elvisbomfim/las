/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.tabelas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import modelos.Procuracao;


/**
 *
 * @author alunov2
 */
public class ModeloTabelaProcuracoes extends  javax.swing.table.AbstractTableModel
{
    ArrayList<Procuracao> listaProcuracoes = new ArrayList();
    DecimalFormat form = new DecimalFormat("00");
    Procuracao procuracao;
    @Override
    public int getRowCount() {
        return listaProcuracoes.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
        switch(columnIndex)
        {
            case 0: return "Representante";
            case 1: return "Profissional";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        int dia;
        int mes;
        int ano;
        
        procuracao = listaProcuracoes.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0: return procuracao.getProcuracao_representante();
            case 1: return procuracao.getProcuracao_profissional();
        }
        return null;
    }
    
    
   // MÉTODOS PARA ALIMENTAÇÃO DA LISTA
    public void inserirListaProcuracoes(ArrayList<Procuracao> listaProcuracoes)
    {
        this.listaProcuracoes = listaProcuracoes;
    }
    
    public ArrayList<Procuracao> retornaListaProcuracoes()
    {
        return this.listaProcuracoes;
    }
    
}