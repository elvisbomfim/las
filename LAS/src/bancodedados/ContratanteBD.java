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
import modelos.Contratante;

/**
 *
 * @author alunov2
 */
public class ContratanteBD extends ConexaoBanco {

    String sql;

    public void inserirNovoContratante(Contratante contratante) {
        try {

            conectarBanco();

            stm = con.createStatement();

            //String dtNasc = aluno.getDataNascimento().get(Calendar.YEAR)+"-"+(aluno.getDataNascimento().get(Calendar.MONTH)+1)+"-"+aluno.getDataNascimento().get(Calendar.DAY_OF_MONTH);
            sql = "INSERT INTO CONTRATANTE (CONTRATANTE_EMPRESA, CONTRATANTE_CPF, CONTRATANTE_CNPJ, CONTRATANTE_TELEFONE, CONTRATANTE_CELULAR)"
                    + "VALUES ('" + contratante.getContratante_empresa() + "', '" + contratante.getContratante_cpf() + "', '" + contratante.getContratante_cnpj() + "', '" + contratante.getContratante_telefone() + "', '" + contratante.getContratante_celular() + "')";

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Nome: " + contratante.getContratante_empresa() + " cadastrado com sucesso!", "Cadastro Finalizado", 3);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            desconectarBanco();
        }
    }

    public void removerContratante(int contratante_id, String contratante_empresa) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "DELETE FROM RECIBO WHERE contratante_id=" + contratante_id;

            stm.executeUpdate(sql);

//            sql = "DELETE FROM RELATORIO WHERE contratante_id=" + contratante_id;

//            stm.executeUpdate(sql);

            sql = "DELETE FROM CONTRATANTE WHERE contratante_id=" + contratante_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Contratante " + contratante_empresa + " foi deletado com sucesso!", "Aviso", 3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public void alterarContratante(int contratante_id, Contratante contratante) {
        try {
            conectarBanco();

            stm = con.createStatement();

            //String dtNasc = aluno.getDataNascimento().get(Calendar.YEAR)+"-"+(aluno.getDataNascimento().get(Calendar.MONTH)+1)+"-"+aluno.getDataNascimento().get(Calendar.DAY_OF_MONTH);
            // CLIENTE_CONTRATANTE, CLIENTE_NOME, CLIENTE_FANTASIA, CLIENTE_CNPJ_CPF, CLIENTE_INSC_ESTADUAL, CLIENTE_INSC_MUNICIPAL, CLIENTE_ENDERECO, CLIENTE_NUMERO, CLIENTE_COMPLEMENTO, CLIENTE_BAIRRO, CLIENTE_MUNICIPIO, CLIENTE_ESTADO, CLIENTE_CEP, CLIENTE_TELEFONE, CLIENTE_CELULAR
            sql = "UPDATE CONTRATANTE "
                    + "SET CONTRATANTE_EMPRESA='" + contratante.getContratante_empresa() + "', CONTRATANTE_CPF='" + contratante.getContratante_cpf() + "', CONTRATANTE_CNPJ='" + contratante.getContratante_cnpj() + "', CONTRATANTE_TELEFONE='" + contratante.getContratante_telefone() + "', CONTRATANTE_CELULAR='" + contratante.getContratante_celular() + "' "
                    + "WHERE contratante_id=" + contratante_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<Contratante> selecionarTodosContratantes() {
        ArrayList<Contratante> listaContratantes = new ArrayList();
        Contratante contratante = new Contratante();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT * "
                    + "FROM CONTRATANTE ORDER BY contratante_id DESC";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                contratante = new Contratante();

                // CLIENTE_CONTRATANTE, CLIENTE_NOME, CLIENTE_FANTASIA, CLIENTE_CNPJ_CPF, CLIENTE_INSC_ESTADUAL, CLIENTE_INSC_MUNICIPAL, CLIENTE_ENDERECO, CLIENTE_NUMERO, CLIENTE_COMPLEMENTO, CLIENTE_BAIRRO, CLIENTE_MUNICIPIO, CLIENTE_ESTADO, CLIENTE_CEP, CLIENTE_TELEFONE, CLIENTE_CELULAR
                contratante.setContratante_id(tabelaRetornada.getInt("CONTRATANTE_ID"));
                contratante.setContratante_empresa(tabelaRetornada.getString("CONTRATANTE_EMPRESA"));
                contratante.setContratante_cpf(tabelaRetornada.getString("CONTRATANTE_CPF"));
                contratante.setContratante_cnpj(tabelaRetornada.getString("CONTRATANTE_CNPJ"));
                contratante.setContratante_telefone(tabelaRetornada.getString("CONTRATANTE_TELEFONE"));
                contratante.setContratante_celular(tabelaRetornada.getString("CONTRATANTE_CELULAR"));

                listaContratantes.add(contratante);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaContratantes;
        }

    }

    public ArrayList<Contratante> selecionarContratanteEspecifico(int contratante_id) {
        ArrayList<Contratante> listaContratantes = new ArrayList();
        Contratante contratante = new Contratante();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT * "
                    + "FROM CONTRATANTE WHERE contratante_id = " + contratante_id;

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                contratante = new Contratante();

                // CLIENTE_CONTRATANTE, CLIENTE_NOME, CLIENTE_FANTASIA, CLIENTE_CNPJ_CPF, CLIENTE_INSC_ESTADUAL, CLIENTE_INSC_MUNICIPAL, CLIENTE_ENDERECO, CLIENTE_NUMERO, CLIENTE_COMPLEMENTO, CLIENTE_BAIRRO, CLIENTE_MUNICIPIO, CLIENTE_ESTADO, CLIENTE_CEP, CLIENTE_TELEFONE, CLIENTE_CELULAR
                contratante.setContratante_id(tabelaRetornada.getInt("CONTRATANTE_ID"));
                contratante.setContratante_empresa(tabelaRetornada.getString("CONTRATANTE_EMPRESA"));
                contratante.setContratante_cpf(tabelaRetornada.getString("CONTRATANTE_CPF"));
                contratante.setContratante_cnpj(tabelaRetornada.getString("CONTRATANTE_CNPJ"));
                contratante.setContratante_telefone(tabelaRetornada.getString("CONTRATANTE_TELEFONE"));
                contratante.setContratante_celular(tabelaRetornada.getString("CONTRATANTE_CELULAR"));

                listaContratantes.add(contratante);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaContratantes;
        }

    }

    public Calendar retornaDateBanco(Date dataBanco) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBanco);
        return cal;
    }
}
