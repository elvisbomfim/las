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
import modelos.Profissional;

/**
 *
 * @author Jean
 */
public class ProfissionalBD extends ConexaoBanco {

    String sql;

    public void inserirNovoProfissional(Profissional profissional) {
        try {

            conectarBanco();

            stm = con.createStatement();

            sql = "INSERT INTO PROFISSIONAL (PROFISSIONAL_NOME, PROFISSIONAL_ENDERECO, PROFISSIONAL_NUMERO, PROFISSIONAL_COMPLEMENTO, PROFISSIONAL_BAIRRO, PROFISSIONAL_CIDADE, PROFISSIONAL_ESTADO, PROFISSIONAL_CEP, PROFISSIONAL_CPF, PROFISSIONAL_TELEFONE, PROFISSIONAL_PROFISSAO, PROFISSIONAL_CREA, PROFISSIONAL_CTMA, PROFISSIONAL_CELULAR, PROFISSIONAL_RCC)"
                    + "VALUES ('" + profissional.getProfissional_nome() + "', '" + profissional.getProfissional_endereco() + "', '" + profissional.getProfissional_numero() + "', '" + profissional.getProfissional_complemento() + "', '" + profissional.getProfissional_bairro() + "', '" + profissional.getProfissional_cidade() + "', '" + profissional.getProfissional_estado() + "', '" + profissional.getProfissional_cep() + "', '" + profissional.getProfissional_cpf() + "', '" + profissional.getProfissional_telefone() + "', '" + profissional.getProfissional_profissao() + "', '" + profissional.getProfissional_crea() + "', '" + profissional.getProfissional_ctma() + "', '" + profissional.getProfissional_celular() + "', '" + profissional.getProfissional_rcc() + "')";

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Nome: " + profissional.getProfissional_nome() + " cadastrado com sucesso!", "Cadastro Finalizado", 3);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            desconectarBanco();
        }
    }

    public void removerProfissional(int profissional_id, String profissional_nome) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "DELETE FROM RELATORIO WHERE profissional_id=" + profissional_id;

            stm.executeUpdate(sql);

            sql = "DELETE FROM RECIBO WHERE profissional_id=" + profissional_id;

            stm.executeUpdate(sql);

            sql = "DELETE FROM PROCURACAO WHERE profissional_id=" + profissional_id;

            stm.executeUpdate(sql);

//            sql = "DELETE FROM RELATORIO WHERE profissional_id1=" + profissional_id + " OR profissional_id2=" + profissional_id;

//            stm.executeUpdate(sql);

            sql = "DELETE FROM REQUERIMENTO_PLA WHERE profissional_id1=" + profissional_id + " OR profissional_id2=" + profissional_id;

            stm.executeUpdate(sql);

            sql = "DELETE FROM TRA WHERE profissional_id1=" + profissional_id;

            stm.executeUpdate(sql);

            sql = "DELETE FROM profissional WHERE profissional_id=" + profissional_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Profissional " + profissional_nome + " foi deletado com sucesso!", "Aviso", 3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public void alterarProfissional(int profissional_id, Profissional profissional) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "UPDATE PROFISSIONAL "
                    + "SET PROFISSIONAL_NOME='" + profissional.getProfissional_nome() + "', PROFISSIONAL_ENDERECO='" + profissional.getProfissional_endereco() + "', PROFISSIONAL_NUMERO='" + profissional.getProfissional_numero() + "', PROFISSIONAL_COMPLEMENTO='" + profissional.getProfissional_complemento() + "', PROFISSIONAL_BAIRRO='" + profissional.getProfissional_bairro() + "', PROFISSIONAL_CIDADE='" + profissional.getProfissional_cidade() + "', PROFISSIONAL_ESTADO='" + profissional.getProfissional_estado() + "', PROFISSIONAL_CEP='" + profissional.getProfissional_cep() + "', PROFISSIONAL_CPF='" + profissional.getProfissional_cpf() + "', PROFISSIONAL_TELEFONE='" + profissional.getProfissional_telefone() + "', PROFISSIONAL_PROFISSAO='" + profissional.getProfissional_profissao() + "', PROFISSIONAL_CREA='" + profissional.getProfissional_crea() + "', PROFISSIONAL_CTMA='" + profissional.getProfissional_ctma() + "', PROFISSIONAL_CELULAR='" + profissional.getProfissional_celular() + "', PROFISSIONAL_RCC='" + profissional.getProfissional_rcc() + "' "
                    + "WHERE profissional_id=" + profissional_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<Profissional> selecionarTodosProfissionais() {
        ArrayList<Profissional> listaProfissionais = new ArrayList();
        Profissional profissional = new Profissional();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT * "
                    + "FROM PROFISSIONAL ORDER BY profissional_id DESC";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                profissional = new Profissional();

                profissional.setProfissional_id(tabelaRetornada.getInt("PROFISSIONAL_ID"));
                profissional.setProfissional_nome(tabelaRetornada.getString("PROFISSIONAL_NOME"));
                profissional.setProfissional_endereco(tabelaRetornada.getString("PROFISSIONAL_ENDERECO"));
                profissional.setProfissional_numero(tabelaRetornada.getString("PROFISSIONAL_NUMERO"));
                profissional.setProfissional_complemento(tabelaRetornada.getString("PROFISSIONAL_COMPLEMENTO"));
                profissional.setProfissional_bairro(tabelaRetornada.getString("PROFISSIONAL_BAIRRO"));
                profissional.setProfissional_cidade(tabelaRetornada.getString("PROFISSIONAL_CIDADE"));
                profissional.setProfissional_estado(tabelaRetornada.getString("PROFISSIONAL_ESTADO"));
                profissional.setProfissional_cep(tabelaRetornada.getString("PROFISSIONAL_CEP"));
                profissional.setProfissional_cpf(tabelaRetornada.getString("PROFISSIONAL_CPF"));
                profissional.setProfissional_telefone(tabelaRetornada.getString("PROFISSIONAL_TELEFONE"));
                profissional.setProfissional_celular(tabelaRetornada.getString("PROFISSIONAL_CELULAR"));
                profissional.setProfissional_profissao(tabelaRetornada.getString("PROFISSIONAL_PROFISSAO"));
                profissional.setProfissional_crea(tabelaRetornada.getString("PROFISSIONAL_CREA"));
                profissional.setProfissional_ctma(tabelaRetornada.getString("PROFISSIONAL_CTMA"));
                profissional.setProfissional_rcc(tabelaRetornada.getString("PROFISSIONAL_RCC"));

                listaProfissionais.add(profissional);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaProfissionais;
        }

    }

    public ArrayList<Profissional> selecionarProfissionalEspecifico(int profissional_id) {
        ArrayList<Profissional> listaProfissionais = new ArrayList();
        Profissional profissional = new Profissional();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT * "
                    + "FROM PROFISSIONAL WHERE profissional_id = " + profissional_id;

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                profissional = new Profissional();

                profissional.setProfissional_id(tabelaRetornada.getInt("PROFISSIONAL_ID"));
                profissional.setProfissional_nome(tabelaRetornada.getString("PROFISSIONAL_NOME"));
                profissional.setProfissional_endereco(tabelaRetornada.getString("PROFISSIONAL_ENDERECO"));
                profissional.setProfissional_numero(tabelaRetornada.getString("PROFISSIONAL_NUMERO"));
                profissional.setProfissional_complemento(tabelaRetornada.getString("PROFISSIONAL_COMPLEMENTO"));
                profissional.setProfissional_bairro(tabelaRetornada.getString("PROFISSIONAL_BAIRRO"));
                profissional.setProfissional_cidade(tabelaRetornada.getString("PROFISSIONAL_CIDADE"));
                profissional.setProfissional_estado(tabelaRetornada.getString("PROFISSIONAL_ESTADO"));
                profissional.setProfissional_cep(tabelaRetornada.getString("PROFISSIONAL_CEP"));
                profissional.setProfissional_cpf(tabelaRetornada.getString("PROFISSIONAL_CPF"));
                profissional.setProfissional_telefone(tabelaRetornada.getString("PROFISSIONAL_TELEFONE"));
                profissional.setProfissional_profissao(tabelaRetornada.getString("PROFISSIONAL_PROFISSAO"));
                profissional.setProfissional_crea(tabelaRetornada.getString("PROFISSIONAL_CREA"));
                profissional.setProfissional_ctma(tabelaRetornada.getString("PROFISSIONAL_CTMA"));
                profissional.setProfissional_rcc(tabelaRetornada.getString("PROFISSIONAL_RCC"));

                listaProfissionais.add(profissional);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaProfissionais;
        }

    }

    public Calendar retornaDateBanco(Date dataBanco) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBanco);
        return cal;
    }
}
