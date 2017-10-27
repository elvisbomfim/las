/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import modelos.Recibo;

/**
 *
 * @author Jean
 */
public class ReciboBD extends ConexaoBanco {

    String sql;

    public void inserirNovoRecibo(Recibo recibo) {
        try {

            conectarBanco();

            stm = con.createStatement();

            String dtRecibo = recibo.getRecibo_data().get(Calendar.YEAR) + "-" + (recibo.getRecibo_data().get(Calendar.MONTH) + 1) + "-" + recibo.getRecibo_data().get(Calendar.DAY_OF_MONTH);

            sql = "INSERT INTO RECIBO (CONTRATANTE_ID, PROFISSIONAL_ID, RECIBO_CIDADE, RECIBO_ESTADO, RECIBO_DATA, RECIBO_VALOR)"
                    + "VALUES (" + recibo.getContratante_id() + ", " + recibo.getProfissional_id() + ", '" + recibo.getRecibo_cidade() + "', '" + recibo.getRecibo_estado() + "', '" + dtRecibo + "', '" + recibo.getRecibo_valor() + "')";

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Dados do recibo cadastrado com sucesso!", "Cadastro Finalizado", 3);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            desconectarBanco();
        }
    }

    public void removerRecibo(int recibo_id) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "DELETE FROM RECIBO WHERE recibo_id=" + recibo_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Arquivo deletado com sucesso!", "Aviso", 3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public void alterarRecibo(int recibo_id, Recibo recibo) {
        try {
            conectarBanco();

            stm = con.createStatement();

            String dtRecibo = recibo.getRecibo_data().get(Calendar.YEAR) + "-" + (recibo.getRecibo_data().get(Calendar.MONTH) + 1) + "-" + recibo.getRecibo_data().get(Calendar.DAY_OF_MONTH);
            // CLIENTE_CONTRATANTE, CLIENTE_NOME, CLIENTE_FANTASIA, CLIENTE_CNPJ_CPF, CLIENTE_INSC_ESTADUAL, CLIENTE_INSC_MUNICIPAL, CLIENTE_ENDERECO, CLIENTE_NUMERO, CLIENTE_COMPLEMENTO, CLIENTE_BAIRRO, CLIENTE_MUNICIPIO, CLIENTE_ESTADO, CLIENTE_CEP, CLIENTE_TELEFONE, CLIENTE_CELULAR
            sql = "UPDATE RECIBO "
                    + "SET CONTRATANTE_ID=" + recibo.getContratante_id() + ", PROFISSIONAL_ID=" + recibo.getProfissional_id() + ", RECIBO_CIDADE='" + recibo.getRecibo_cidade() + "', RECIBO_ESTADO='" + recibo.getRecibo_estado() + "', RECIBO_DATA='" + dtRecibo + "', RECIBO_VALOR='" + recibo.getRecibo_valor() + "' "
                    + "WHERE recibo_id=" + recibo_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<Recibo> selecionarTodosRecibos() {
        ArrayList<Recibo> listaRecibos = new ArrayList();
        Recibo recibo = new Recibo();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT r.*, c.contratante_empresa, p.profissional_nome "
                    + "FROM RECIBO r "
                    + "LEFT JOIN CONTRATANTE c ON c.contratante_id =  r.contratante_id "
                    + "LEFT JOIN PROFISSIONAL p ON p.profissional_id = r.profissional_id "
                    + "ORDER BY r.recibo_id DESC";
            //sql = "SELECT * "
            //        + "FROM RECIBO";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                recibo = new Recibo();

                // CLIENTE_CONTRATANTE, CLIENTE_NOME, CLIENTE_FANTASIA, CLIENTE_CNPJ_CPF, CLIENTE_INSC_ESTADUAL, CLIENTE_INSC_MUNICIPAL, CLIENTE_ENDERECO, CLIENTE_NUMERO, CLIENTE_COMPLEMENTO, CLIENTE_BAIRRO, CLIENTE_MUNICIPIO, CLIENTE_ESTADO, CLIENTE_CEP, CLIENTE_TELEFONE, CLIENTE_CELULAR
                recibo.setRecibo_id(tabelaRetornada.getInt("RECIBO_ID"));
                recibo.setContratante_id(tabelaRetornada.getInt("CONTRATANTE_ID"));
                recibo.setProfissional_id(tabelaRetornada.getInt("PROFISSIONAL_ID"));
                recibo.setRecibo_contratante(tabelaRetornada.getString("CONTRATANTE_EMPRESA"));
                recibo.setRecibo_profissional(tabelaRetornada.getString("PROFISSIONAL_NOME"));
                recibo.setRecibo_estado(tabelaRetornada.getString("RECIBO_ESTADO"));
                recibo.setRecibo_cidade(tabelaRetornada.getString("RECIBO_CIDADE"));
                recibo.setRecibo_data(retornaDateBanco(tabelaRetornada.getDate("RECIBO_DATA")));
                recibo.setRecibo_valor(tabelaRetornada.getString("RECIBO_VALOR"));

                listaRecibos.add(recibo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaRecibos;
        }

    }

    public Calendar retornaDateBanco(Date dataBanco) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBanco);
        return cal;
    }
}
