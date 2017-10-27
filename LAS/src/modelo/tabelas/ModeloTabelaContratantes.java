/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.tabelas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import modelos.Contratante;


/**
 *
 * @author alunov2
 */
public class ModeloTabelaContratantes extends  javax.swing.table.AbstractTableModel
{
    ArrayList<Contratante> listaContratantes = new ArrayList();
    DecimalFormat form = new DecimalFormat("00");
    Contratante contratante;
    @Override
    public int getRowCount() {
        return listaContratantes.size();
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
            case 0: return "Empresa";
            case 1: return "CPF";
            case 2: return "CNPJ";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        int dia;
        int mes;
        int ano;
        
        contratante = listaContratantes.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0: return contratante.getContratante_empresa();
            case 1: return contratante.getContratante_cpf();
            case 2: return contratante.getContratante_cnpj();
        }
        return null;
    }
    
    
   // MÉTODOS PARA ALIMENTAÇÃO DA LISTA
    public void inserirListaContratantes(ArrayList<Contratante> listaContratantes)
    {
        this.listaContratantes = listaContratantes;
    }
    
    public ArrayList<Contratante> retornarListaContratantes()
    {
        return this.listaContratantes;
    }
    
}