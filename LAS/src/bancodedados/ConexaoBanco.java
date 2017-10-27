/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Possui 2 funcionalidades: CONECTAR e DESCONECTAR da BASE DE DADOS
 * @author alunov2
 */
public class ConexaoBanco 
{
    String driver = "org.apache.derby.jdbc.EmbeddedDriver"; // Driver de conexão com o banco
    String url = "jdbc:derby:DBLA"; // Caminho para a base 
    String usuario = "adm";
    String senha = "adm123"; 
    
    // Variáveis para conexão com o banco
    Connection con;
    Statement stm;
    
    // Tabela para retornar dados do SELECT
    ResultSet tabelaRetornada;
    
    
    public void conectarBanco()
    {
        try
        {
            // Buscando o driver 'derby.jar' nas bibliotecas do sistema
            System.out.println("Conectando ao driver... ");
            Class.forName(driver);
            System.out.println("CONECTADO ao driver!");
            // Criando a conexão com a base
            System.out.println("Conectando a base...");
            con = DriverManager.getConnection(url, usuario, senha);
            System.out.println("CONECTADO a base!");
        }catch(Exception e)
            {
              e.printStackTrace();
            }
    }
    
    public void desconectarBanco()
    {
        try
        {
            con.close();
        }catch(Exception e)
            {
                e.printStackTrace();
            }
    }
}
