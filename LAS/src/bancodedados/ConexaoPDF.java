/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author elvis
 */
public class ConexaoPDF {
    //criando um método responsável por estabelecer
    //uma conexão com o banco

    public static Connection conector() {
        //criando uma variável especial para 
        //estabelecer uma conexão com o banco
        java.sql.Connection conexao = null;
        //carregando o driver correspondente
        //ao banco (não esqueça de importar ele
        // em libraries 
        String driver = "org.apache.derby.jdbc.EmbeddedDriver"; // Driver de conexão com o banco
        String url = "jdbc:derby:DBLA"; // Caminho para a base 
        String usuario = "adm";
        String senha = "adm123";
        //estabelecendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            return conexao;

        } catch (Exception e) {
            //a linha abaixo serve de apoio para esclarecer o erro
            //System.out.println(e);
            return null;
        }

    }
}
