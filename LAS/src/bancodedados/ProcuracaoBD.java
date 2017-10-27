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
import modelos.Procuracao;

/**
 *
 * @author Jean
 */
public class ProcuracaoBD extends ConexaoBanco{
    
        String sql;

    public void inserirNovoProcuracao(Procuracao procuracao) {
        try {

            conectarBanco();

            stm = con.createStatement();

            String data = procuracao.getProcuracao_data().get(Calendar.YEAR)+"-"+(procuracao.getProcuracao_data().get(Calendar.MONTH)+1)+"-"+procuracao.getProcuracao_data().get(Calendar.DAY_OF_MONTH);
            sql = "INSERT INTO PROCURACAO (REPRESENTANTE_ID, PROFISSIONAL_ID, PROCURACAO_CIDADE, PROCURACAO_ESTADO, PROCURACAO_DATA)"
                    + "VALUES (" + procuracao.getRepresentante_id() +", "+ procuracao.getProfissional_id() + ", '" + procuracao.getProcuracao_cidade() + "', '" + procuracao.getProcuracao_estado() + "', '" + data + "')";

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Relatorio cadastrado com sucesso!", "Cadastro Finalizado", 3);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            desconectarBanco();
        }
    }

    public void removerProcuracao(int procuracao_id) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "DELETE FROM PROCURACAO WHERE procuracao_id=" + procuracao_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "O relatorio foi deletado com sucesso!", "Aviso", 3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public void alterarRequerimento(int procuracao_id, Procuracao procuracao) {
        try {
            conectarBanco();

            stm = con.createStatement();

            String data = procuracao.getProcuracao_data().get(Calendar.YEAR) + "-" + (procuracao.getProcuracao_data().get(Calendar.MONTH) + 1) + "-" + procuracao.getProcuracao_data().get(Calendar.DAY_OF_MONTH);
            sql = "UPDATE PROCURACAO "
                    + "SET REPRESENTANTE_ID=" + procuracao.getRepresentante_id() + ", PROFISSIONAL_ID=" + procuracao.getProfissional_id() + ", PROCURACAO_CIDADE='" +  procuracao.getProcuracao_cidade() + "', PROCURACAO_ESTADO='" +  procuracao.getProcuracao_estado() + "', PROCURACAO_DATA='" + data + "', PROCURACAO_REPRESENTANTE='" +  procuracao.getProcuracao_representante() + "', PROCURACAO_PROFISSIONAL='" +  procuracao.getProcuracao_profissional() + "' "
                    + "WHERE procuracao_id=" + procuracao_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<Procuracao> selecionarTodasProcuracoes() {
        ArrayList<Procuracao> listaProcuracoes = new ArrayList();
        Procuracao procuracao = new Procuracao();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT pr.*, p.profissional_nome, r.representante_nome "
                    + "FROM PROCURACAO pr "
                    + "LEFT JOIN PROFISSIONAL p ON p.profissional_id = pr.profissional_id "
                    + "LEFT JOIN REPRESENTANTE r ON r.representante_id = pr.representante_id "
                    + "ORDER BY pr.procuracao_id";
            //sql = "SELECT * "
            //        + "FROM REQUERIMENTO_PLA";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {

                //REQUERIMENTO_TIPO, REQUERIMENTO_FASE_EMPREENDIMENTO, REQUERIMENTO_FASE_EMPREENDIMENTO_DATA, REQUERIMENTO_NUM_PROCESSO_PROTOCOLO, REQUERIMENTO_NUM_LICENCA_ANTERIO, REQUERIMENTO_NUM_DE_GUIA_DE_ENQUADRAMENTO, REQUERIMENTO_DATA, REQUERIMENTO_CLIENTE, REQUERIMENTO_REPRESENTANTE1, REQUERIMENTO_REPRESENTANTE2, REQUERIMENTO_PROFISSIONAL1, REQUERIMENTO_PROFISSIONAL2, REPRESENTANTE_ID1, REPRESENTANTE_ID2, PROFISSIONAL_ID1, PROFISSIONAL_ID2, CLIENTE_ID)"
                procuracao = new Procuracao();

                procuracao.setProcucacao_id(tabelaRetornada.getInt("PROCURACAO_ID"));
                procuracao.setRepresentante_id(tabelaRetornada.getInt("REPRESENTANTE_ID"));
                procuracao.setProfissional_id(tabelaRetornada.getInt("PROFISSIONAL_ID"));
                procuracao.setProcuracao_cidade(tabelaRetornada.getString("PROCURACAO_CIDADE"));
                procuracao.setProcuracao_estado(tabelaRetornada.getString("PROCURACAO_ESTADO"));
                procuracao.setProcuracao_data(retornaDateBanco(tabelaRetornada.getDate("PROCURACAO_DATA")));
                procuracao.setProcuracao_representante(tabelaRetornada.getString("REPRESENTANTE_NOME"));
                procuracao.setProcuracao_profissional(tabelaRetornada.getString("PROFISSIONAL_NOME"));

                listaProcuracoes.add(procuracao);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaProcuracoes;
        }

    }
    public Calendar retornaDateBanco(Date dataBanco) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBanco);
        return cal;
    }
    
}
