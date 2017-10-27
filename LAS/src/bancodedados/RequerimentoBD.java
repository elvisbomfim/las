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
import modelos.Requerimento;

/**
 *
 * @author Jean
 */
public class RequerimentoBD extends ConexaoBanco {

    String sql;

    public void inserirNovoRequerimento(Requerimento requerimento) {
        try {

            conectarBanco();

            stm = con.createStatement();

            String fase_empreendimento_data = requerimento.getRequerimento_fase_empreendimento_data().get(Calendar.YEAR) + "-" + (requerimento.getRequerimento_fase_empreendimento_data().get(Calendar.MONTH) + 1) + "-" + requerimento.getRequerimento_fase_empreendimento_data().get(Calendar.DAY_OF_MONTH);
            String dtRequerimento = requerimento.getRequerimento_data().get(Calendar.YEAR) + "-" + (requerimento.getRequerimento_data().get(Calendar.MONTH) + 1) + "-" + requerimento.getRequerimento_data().get(Calendar.DAY_OF_MONTH);
            sql = "INSERT INTO REQUERIMENTO_PLA (REQUERIMENTO_TIPO, REQUERIMENTO_FASE_EMPREENDIMENTO, REQUERIMENTO_FASE_EMPREENDIMENTO_DATA, REQUERIMENTO_NUM_PROCESSO_PROTOCOLO, REQUERIMENTO_NUM_LICENCA_ANTERIOR, REQUERIMENTO_NUM_DE_GUIA_DE_ENQUADRAMENTO_E_DOCUMENTACAO, REQUERIMENTO_DATA, REQUERIMENTO_CLIENTE, REQUERIMENTO_REPRESENTANTE1, REQUERIMENTO_REPRESENTANTE2, REQUERIMENTO_PROFISSIONAL1, REQUERIMENTO_PROFISSIONAL2, REPRESENTANTE_ID1, REPRESENTANTE_ID2, PROFISSIONAL_ID1, PROFISSIONAL_ID2, CLIENTE_ID, REQUERIMENTO_LATN, REQUERIMENTO_LATE, REQUERIMENTO_SEMA)"
                    + "VALUES (" + requerimento.getRequerimento_tipo() +", "+ requerimento.getRequerimento_fase_empreendimento() + ", '" + fase_empreendimento_data + "', '" + requerimento.getRequerimento_num_processo_protocolo() + "', '" + requerimento.getRequerimento_num_licenca_anterior() + "', '" + requerimento.getRequerimento_num_de_guia_de_enquadramento_e_documentacao() + "', '" + dtRequerimento + "', '" + requerimento.getRequerimento_cliente() + "', '" + requerimento.getRequerimento_representante1() + "', '" + requerimento.getRequerimento_representante2() + "', '" + requerimento.getRequerimento_profissional1() + "', '" + requerimento.getRequerimento_profissional2() + "', " + requerimento.getRepresentante_id1() + ", " + requerimento.getRepresentante_id2() + ", " + requerimento.getProfissional_id1() + ", " + requerimento.getProfissional_id2() + ", " + requerimento.getCliente_id() + ", '" + requerimento.getRequerimento_latn() + "', '" + requerimento.getRequerimento_late() + "', '" + requerimento.getRequerimento_sema() +"')";

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Relatorio cadastrado com sucesso!", "Cadastro Finalizado", 3);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            desconectarBanco();
        }
    }

    public void removerRequerimento(int requerimento_id) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "DELETE FROM REQUERIMENTO_PLA WHERE requerimento_id=" + requerimento_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "O relatorio foi deletado com sucesso!", "Aviso", 3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public void alterarRequerimento(int requerimento_id, Requerimento requerimento) {
        try {
            conectarBanco();

            stm = con.createStatement();
            String fase_empreendimento_data = requerimento.getRequerimento_fase_empreendimento_data().get(Calendar.YEAR) + "-" + (requerimento.getRequerimento_fase_empreendimento_data().get(Calendar.MONTH) + 1) + "-" + requerimento.getRequerimento_fase_empreendimento_data().get(Calendar.DAY_OF_MONTH);
            String dtRequerimento = requerimento.getRequerimento_data().get(Calendar.YEAR) + "-" + (requerimento.getRequerimento_data().get(Calendar.MONTH) + 1) + "-" + requerimento.getRequerimento_data().get(Calendar.DAY_OF_MONTH);
            //String dtNasc = aluno.getDataNascimento().get(Calendar.YEAR)+"-"+(aluno.getDataNascimento().get(Calendar.MONTH)+1)+"-"+aluno.getDataNascimento().get(Calendar.DAY_OF_MONTH);
            sql = "UPDATE REQUERIMENTO_PLA "
                    + "SET REQUERIMENTO_TIPO=" + requerimento.getRequerimento_tipo() + ", REQUERIMENTO_FASE_EMPREENDIMENTO=" + requerimento.getRequerimento_fase_empreendimento() + ", REQUERIMENTO_FASE_EMPREENDIMENTO_DATA='" + fase_empreendimento_data + "', REQUERIMENTO_NUM_PROCESSO_PROTOCOLO='" + requerimento.getRequerimento_num_processo_protocolo() + "', REQUERIMENTO_NUM_LICENCA_ANTERIOR='" + requerimento.getRequerimento_num_licenca_anterior() + "', REQUERIMENTO_NUM_DE_GUIA_DE_ENQUADRAMENTO_E_DOCUMENTACAO='" + requerimento.getRequerimento_num_de_guia_de_enquadramento_e_documentacao() + "', REQUERIMENTO_DATA='" + dtRequerimento + "', REQUERIMENTO_CLIENTE='" + requerimento.getRequerimento_cliente() + "', REQUERIMENTO_REPRESENTANTE1='" + requerimento.getRequerimento_representante1() + "', REQUERIMENTO_REPRESENTANTE2='" + requerimento.getRequerimento_representante2() + "', REQUERIMENTO_PROFISSIONAL1='" + requerimento.getRequerimento_profissional1() + "', REQUERIMENTO_PROFISSIONAL2='" + requerimento.getRequerimento_profissional2() + "', CLIENTE_ID=" + requerimento.getCliente_id() + ", REPRESENTANTE_ID1=" + requerimento.getRepresentante_id1() + ", REPRESENTANTE_ID2=" + requerimento.getRepresentante_id2() + ", PROFISSIONAL_ID1=" + requerimento.getProfissional_id1() + ", PROFISSIONAL_ID2=" + requerimento.getProfissional_id2() + ", REQUERIMENTO_LATN='" + requerimento.getRequerimento_latn() + "', REQUERIMENTO_LATE='" + requerimento.getRequerimento_late() + "', REQUERIMENTO_SEMA='" + requerimento.getRequerimento_sema() + "' "
                    + "WHERE requerimento_id=" + requerimento_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<Requerimento> selecionarTodosRequerimentos() {
        ArrayList<Requerimento> listaRequerimentos = new ArrayList();
        Requerimento requerimento = new Requerimento();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT r.*, c.cliente_nome, p1.profissional_nome AS profissional_nome1, p2.profissional_nome AS profissional_nome2, r1.representante_nome AS representante_nome1, r2.representante_nome AS representante_nome2 "
                    + "FROM REQUERIMENTO_PLA r "
                    + "LEFT JOIN CLIENTE c ON c.cliente_id = r.cliente_id "
                    + "LEFT JOIN PROFISSIONAL p1 ON p1.profissional_id = r.profissional_id1 "
                    + "LEFT JOIN PROFISSIONAL p2 ON p2.profissional_id = r.profissional_id1 "
                    + "LEFT JOIN REPRESENTANTE r1 ON r1.representante_id = r.representante_id1 "
                    + "LEFT JOIN REPRESENTANTE r2 ON r2.representante_id = r.representante_id2 "
                    + "ORDER BY r.requerimento_id DESC";
            //sql = "SELECT * "
            //        + "FROM REQUERIMENTO_PLA";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {

                //REQUERIMENTO_TIPO, REQUERIMENTO_FASE_EMPREENDIMENTO, REQUERIMENTO_FASE_EMPREENDIMENTO_DATA, REQUERIMENTO_NUM_PROCESSO_PROTOCOLO, REQUERIMENTO_NUM_LICENCA_ANTERIO, REQUERIMENTO_NUM_DE_GUIA_DE_ENQUADRAMENTO, REQUERIMENTO_DATA, REQUERIMENTO_CLIENTE, REQUERIMENTO_REPRESENTANTE1, REQUERIMENTO_REPRESENTANTE2, REQUERIMENTO_PROFISSIONAL1, REQUERIMENTO_PROFISSIONAL2, REPRESENTANTE_ID1, REPRESENTANTE_ID2, PROFISSIONAL_ID1, PROFISSIONAL_ID2, CLIENTE_ID)"
                requerimento = new Requerimento();

                requerimento.setRequerimento_id(tabelaRetornada.getInt("REQUERIMENTO_ID"));
                requerimento.setRequerimento_tipo(tabelaRetornada.getInt("REQUERIMENTO_TIPO"));
                requerimento.setRequerimento_fase_empreendimento(tabelaRetornada.getInt("REQUERIMENTO_FASE_EMPREENDIMENTO"));
                requerimento.setRequerimento_num_processo_protocolo(tabelaRetornada.getString("REQUERIMENTO_NUM_PROCESSO_PROTOCOLO"));
                requerimento.setRequerimento_num_licenca_anterior(tabelaRetornada.getString("REQUERIMENTO_NUM_LICENCA_ANTERIOR"));
                requerimento.setRequerimento_num_de_guia_de_enquadramento_e_documentacao(tabelaRetornada.getString("REQUERIMENTO_NUM_DE_GUIA_DE_ENQUADRAMENTO_E_DOCUMENTACAO"));
                requerimento.setRequerimento_data(retornaDateBanco(tabelaRetornada.getDate("REQUERIMENTO_DATA")));
                requerimento.setRequerimento_fase_empreendimento_data(retornaDateBanco(tabelaRetornada.getDate("REQUERIMENTO_FASE_EMPREENDIMENTO_DATA")));
                requerimento.setRequerimento_cliente(tabelaRetornada.getString("REQUERIMENTO_CLIENTE"));
                requerimento.setRequerimento_representante1(tabelaRetornada.getString("REQUERIMENTO_REPRESENTANTE1"));
                requerimento.setRequerimento_representante2(tabelaRetornada.getString("REQUERIMENTO_REPRESENTANTE2"));
                requerimento.setRequerimento_profissional1(tabelaRetornada.getString("REQUERIMENTO_PROFISSIONAL1"));
                requerimento.setRequerimento_profissional2(tabelaRetornada.getString("REQUERIMENTO_PROFISSIONAL2"));
                requerimento.setCliente_id(tabelaRetornada.getInt("CLIENTE_ID"));
                requerimento.setRepresentante_id1(tabelaRetornada.getInt("REPRESENTANTE_ID1"));
                requerimento.setRepresentante_id2(tabelaRetornada.getInt("REPRESENTANTE_ID2"));
                requerimento.setProfissional_id1(tabelaRetornada.getInt("PROFISSIONAL_ID1"));
                requerimento.setProfissional_id2(tabelaRetornada.getInt("PROFISSIONAL_ID2"));
                requerimento.setRequerimento_latn(tabelaRetornada.getString("REQUERIMENTO_LATN"));
                requerimento.setRequerimento_late(tabelaRetornada.getString("REQUERIMENTO_LATE"));
                requerimento.setRequerimento_sema(tabelaRetornada.getString("REQUERIMENTO_SEMA"));

                listaRequerimentos.add(requerimento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaRequerimentos;
        }

    }
    
    public Calendar retornaDateBanco(Date dataBanco) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBanco);
        return cal;
    }    

}
