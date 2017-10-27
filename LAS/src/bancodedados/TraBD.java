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
import modelos.Tra;

/**
 *
 * @author Jean
 */
public class TraBD extends ConexaoBanco {

    String sql;

    public void inserirNovoTra(Tra tra) {
        try {

            conectarBanco();

            stm = con.createStatement();

            String dataAtual = tra.getTra_data().get(Calendar.YEAR) + "-" + (tra.getTra_data().get(Calendar.MONTH) + 1) + "-" + tra.getTra_data().get(Calendar.DAY_OF_MONTH);
            sql = "INSERT INTO TRA (TRA_LOCALIZADO, TRA_INSTALADO, TRA_DECLARACAO, TRA_DATA, CLIENTE_ID, REPRESENTANTE_ID1, REPRESENTANTE_ID2, PROFISSIONAL_ID1, TRA_CIDADE, TRA_ESTADO, TRA_N_ARTIGO)"
                    + "VALUES (" + tra.getTra_localizado() + ", " + tra.getTra_instalado() + ", " + tra.getTra_declaracao() + ", '" + dataAtual + "', " + tra.getCliente_id() + ", " + tra.getRepresentante_id1() + ", " + tra.getRepresentante_id2() + ", " + tra.getProfissional_id1() + ", '" + tra.getTra_cidade() + "', '" + tra.getTra_estado() + "', '" + tra.getTra_n_artigo() + "')";

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Relatorio TRA cadastrado com sucesso!", "Cadastro Finalizado", 3);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            desconectarBanco();
        }
    }

    public void removerTra(int tra_id) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "DELETE FROM TRA WHERE tra_id=" + tra_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Relatorio TRA foi deletado com sucesso!", "Aviso", 3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public void alterarTra(int tra_id, Tra tra) {
        try {
            conectarBanco();

            stm = con.createStatement();

            String dataAtual = tra.getTra_data().get(Calendar.YEAR) + "-" + (tra.getTra_data().get(Calendar.MONTH) + 1) + "-" + tra.getTra_data().get(Calendar.DAY_OF_MONTH);
            // CLIENTE_CONTRATANTE, CLIENTE_NOME, CLIENTE_FANTASIA, CLIENTE_CNPJ_CPF, CLIENTE_INSC_ESTADUAL, CLIENTE_INSC_MUNICIPAL, CLIENTE_ENDERECO, CLIENTE_NUMERO, CLIENTE_COMPLEMENTO, CLIENTE_BAIRRO, CLIENTE_MUNICIPIO, CLIENTE_ESTADO, CLIENTE_CEP, CLIENTE_TELEFONE, CLIENTE_CELULAR
            sql = "UPDATE TRA "
                    + "SET TRA_LOCALIZADO=" + tra.getTra_localizado() + ", TRA_INSTALADO=" + tra.getTra_instalado() + ", TRA_DECLARACAO=" + tra.getTra_declaracao() + ", TRA_DATA='" + dataAtual + "', CLIENTE_ID=" + tra.getCliente_id() + ", REPRESENTANTE_ID1=" + tra.getRepresentante_id1() + ", REPRESENTANTE_ID2=" + tra.getRepresentante_id2() + ", PROFISSIONAL_ID1=" + tra.getProfissional_id1() + ", TRA_CIDADE='" + tra.getTra_cidade() + "', TRA_ESTADO='" + tra.getTra_estado() + "', TRA_N_ARTIGO='" + tra.getTra_n_artigo() + "' "
                    + "WHERE tra_id=" + tra_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<Tra> selecionarTodosTra() {
        ArrayList<Tra> listaTra = new ArrayList();
        Tra tra = new Tra();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT t.*, c.cliente_nome, p.profissional_nome, r1.representante_nome AS representante_nome1, r2.representante_nome AS representante_nome2 "
                    + "FROM TRA t "
                    + "LEFT JOIN CLIENTE c ON c.cliente_id = t.cliente_id "
                    + "LEFT JOIN PROFISSIONAL p ON p.profissional_id = t.profissional_id1 "
                    + "LEFT JOIN REPRESENTANTE r1 ON r1.representante_id = t.representante_id1 "
                    + "LEFT JOIN REPRESENTANTE r2 ON r2.representante_id = t.representante_id2 "
                    + "ORDER BY t.tra_id DESC";

            //sql = "SELECT * "
            //        + "FROM TRA";
            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                tra = new Tra();

                tra.setTra_id(tabelaRetornada.getInt("TRA_ID"));
                tra.setTra_localizado(tabelaRetornada.getInt("TRA_LOCALIZADO"));
                tra.setTra_instalado(tabelaRetornada.getInt("TRA_INSTALADO"));
                tra.setTra_declaracao(tabelaRetornada.getInt("TRA_DECLARACAO"));
                tra.setTra_data(retornaDateBanco(tabelaRetornada.getDate("TRA_DATA")));
                tra.setTra_cliente(tabelaRetornada.getString("CLIENTE_NOME"));
                tra.setTra_profissional1(tabelaRetornada.getString("PROFISSIONAL_NOME"));
                tra.setTra_representante1(tabelaRetornada.getString("REPRESENTANTE_NOME1"));
                tra.setTra_representante2(tabelaRetornada.getString("REPRESENTANTE_NOME2"));
                tra.setCliente_id(tabelaRetornada.getInt("CLIENTE_ID"));
                tra.setProfissional_id1(tabelaRetornada.getInt("PROFISSIONAL_ID1"));
                tra.setRepresentante_id1(tabelaRetornada.getInt("REPRESENTANTE_ID1"));
                tra.setRepresentante_id2(tabelaRetornada.getInt("REPRESENTANTE_ID2"));
                tra.setTra_cidade(tabelaRetornada.getString("TRA_CIDADE"));
                tra.setTra_estado(tabelaRetornada.getString("TRA_ESTADO"));
                tra.setTra_n_artigo(tabelaRetornada.getString("TRA_N_ARTIGO"));

                listaTra.add(tra);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaTra;
        }

    }

    public Calendar retornaDateBanco(Date dataBanco) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBanco);
        return cal;
    }
}
