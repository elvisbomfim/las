/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.tabelas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import modelos.Cliente;


/**
 *
 * @author alunov2
 */
public class ModeloTabelaClientes extends  javax.swing.table.AbstractTableModel
{
    ArrayList<Cliente> listaClientes = new ArrayList();
    DecimalFormat form = new DecimalFormat("00");
    Cliente cliente;
    @Override
    public int getRowCount() {
        return listaClientes.size();
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
            case 0: return "Nome do Cliente";
            case 1: return "Cpf";
            case 2: return "Cnpj";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        int dia;
        int mes;
        int ano;
        
        cliente = listaClientes.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0: return cliente.getCliente_nome();
            case 1: return cliente.getCliente_cpf();
            case 2: return cliente.getCliente_cnpj();
        }
        return null;
    }
    
    
   // MÉTODOS PARA ALIMENTAÇÃO DA LISTA
    public void inserirListaClientes(ArrayList<Cliente> listaClientes)
    {
        this.listaClientes = listaClientes;
    }
    
    public ArrayList<Cliente> retornaListaClientes()
    {
        return this.listaClientes;
    }
    
}