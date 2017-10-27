/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.Representante;

/**
 *
 * @author Jean
 */
public class RepresentanteBD extends ConexaoBanco {

    String sql;

    public void inserirNovoRepresentante(Representante representante) {
        try {

            conectarBanco();

            stm = con.createStatement();

            //String dtNasc = aluno.getDataNascimento().get(Calendar.YEAR)+"-"+(aluno.getDataNascimento().get(Calendar.MONTH)+1)+"-"+aluno.getDataNascimento().get(Calendar.DAY_OF_MONTH);
            sql = "INSERT INTO REPRESENTANTE (REPRESENTANTE_NOME, REPRESENTANTE_ENDERECO, REPRESENTANTE_NUMERO, REPRESENTANTE_COMPLEMENTO, REPRESENTANTE_BAIRRO, REPRESENTANTE_MUNICIPIO, REPRESENTANTE_ESTADO, REPRESENTANTE_CEP, REPRESENTANTE_CPF, REPRESENTANTE_TELEFONE, REPRESENTANTE_CELULAR, REPRESENTANTE_NACIONALIDADE)"
                    + "VALUES ('" + representante.getRepresentante_nome() + "', '" + representante.getRepresentante_rua() + "', '" + representante.getRepresentante_numero() + "', '" + representante.getRepresentante_complemento() + "', '" + representante.getRepresentante_bairro() + "', '" + representante.getRepresentante_municipio() + "', '" + representante.getRepresentante_estado() + "', '" + representante.getRepresentante_cep() + "', '" + representante.getRepresentante_cpf() + "', '" + representante.getRepresentante_telefone() + "', '" + representante.getRepresentante_celular() +"', '" + representante.getRepresentante_nacionalidade() + "')";

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Nome: " + representante.getRepresentante_nome() + " cadastrado com sucesso!", "Cadastro Finalizado", 3);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            desconectarBanco();
        }
    }

    public void removerRepresentante(int representante_id, String representante_nome) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "DELETE FROM PROCURACAO WHERE representante_id=" + representante_id;

            stm = con.createStatement();

            sql = "DELETE FROM RELATORIO WHERE representante_id1=" + representante_id + " OR representante_id2=" + representante_id;

            stm = con.createStatement();

            sql = "DELETE FROM REQUERIMENTO_PLA WHERE representante_id1=" + representante_id + " OR representante_id2=" + representante_id;

            stm = con.createStatement();

            sql = "DELETE FROM TRA WHERE representante_id1=" + representante_id + " OR representante_id2=" + representante_id;

            stm = con.createStatement();

            sql = "DELETE FROM REPRESENTANTE WHERE representante_id=" + representante_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Representante " + representante_nome + " foi deletado com sucesso!", "Aviso", 3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public void alterarRepresentante(int representante_id, Representante representante) {
        try {
            conectarBanco();

            stm = con.createStatement();

            //String dtNasc = aluno.getDataNascimento().get(Calendar.YEAR)+"-"+(aluno.getDataNascimento().get(Calendar.MONTH)+1)+"-"+aluno.getDataNascimento().get(Calendar.DAY_OF_MONTH);
            sql = "UPDATE REPRESENTANTE "
                    + "SET REPRESENTANTE_NOME='" + representante.getRepresentante_nome() + "', REPRESENTANTE_ENDERECO='" + representante.getRepresentante_rua() + "', REPRESENTANTE_NUMERO='" + representante.getRepresentante_numero() + "', REPRESENTANTE_COMPLEMENTO='" + representante.getRepresentante_complemento() + "', REPRESENTANTE_BAIRRO='" + representante.getRepresentante_bairro() + "', REPRESENTANTE_MUNICIPIO='" + representante.getRepresentante_municipio() + "', REPRESENTANTE_ESTADO='" + representante.getRepresentante_estado() + "', REPRESENTANTE_CEP='" + representante.getRepresentante_cep() + "', REPRESENTANTE_CPF='" + representante.getRepresentante_cpf() + "', REPRESENTANTE_TELEFONE='" + representante.getRepresentante_telefone() + "', REPRESENTANTE_CELULAR='" + representante.getRepresentante_celular() +  "', REPRESENTANTE_NACIONALIDADE='" + representante.getRepresentante_nacionalidade() + "' "
                    + "WHERE representante_id=" + representante_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<Representante> selecionarTodosRepresentantes() {
        ArrayList<Representante> listaRepresentantes = new ArrayList();
        Representante representante = new Representante();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT * "
                    + "FROM REPRESENTANTE ORDER BY representante_id DESC";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                representante = new Representante();

                representante.setRepresentante_id(tabelaRetornada.getInt("REPRESENTANTE_ID"));
                representante.setRepresentante_nome(tabelaRetornada.getString("REPRESENTANTE_NOME"));
                representante.setRepresentante_rua(tabelaRetornada.getString("REPRESENTANTE_ENDERECO"));
                representante.setRepresentante_numero(tabelaRetornada.getString("REPRESENTANTE_NUMERO"));
                representante.setRepresentante_bairro(tabelaRetornada.getString("REPRESENTANTE_BAIRRO"));
                representante.setRepresentante_complemento(tabelaRetornada.getString("REPRESENTANTE_COMPLEMENTO"));
                representante.setRepresentante_municipio(tabelaRetornada.getString("REPRESENTANTE_MUNICIPIO"));
                representante.setRepresentante_estado(tabelaRetornada.getString("REPRESENTANTE_ESTADO"));
                representante.setRepresentante_cep(tabelaRetornada.getString("REPRESENTANTE_CEP"));
                representante.setRepresentante_cpf(tabelaRetornada.getString("REPRESENTANTE_CPF"));
                representante.setRepresentante_telefone(tabelaRetornada.getString("REPRESENTANTE_TELEFONE"));
                representante.setRepresentante_celular(tabelaRetornada.getString("REPRESENTANTE_CELULAR"));
                representante.setRepresentante_nacionalidade(tabelaRetornada.getString("REPRESENTANTE_NACIONALIDADE"));

                listaRepresentantes.add(representante);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaRepresentantes;
        }

    }

}
