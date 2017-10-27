/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.tabelas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import modelos.Profissional;

/**
 *
 * @author Jean
 */
public class ModeloTabelaProfissionais extends javax.swing.table.AbstractTableModel{
    
    ArrayList<Profissional> listaProfissionais = new ArrayList();
    DecimalFormat form = new DecimalFormat("00");
    Profissional profissional;
    @Override
    public int getRowCount() {
        return listaProfissionais.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
        switch(columnIndex)
        {
            case 0: return "Nome";
            case 1: return "CPF";
            case 2: return "Telefone";
            case 3: return "Profissão";
            case 4: return "Cidade";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        int dia;
        int mes;
        int ano;
        
        profissional = listaProfissionais.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0: return profissional.getProfissional_nome();
            case 1: return profissional.getProfissional_cpf();
            case 2: return profissional.getProfissional_telefone();
            case 3: return profissional.getProfissional_profissao();
            case 4: return profissional.getProfissional_cidade();
        }
        return null;
    }
    
    
   // MÉTODOS PARA ALIMENTAÇÃO DA LISTA
    public void inserirListaProfissionais(ArrayList<Profissional> listaProfissionais)
    {
        this.listaProfissionais = listaProfissionais;
    }
    
    public ArrayList<Profissional> retornaListaProfissionais()
    {
        return this.listaProfissionais;
    }
    
}
