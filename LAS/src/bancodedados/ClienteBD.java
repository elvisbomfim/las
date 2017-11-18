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
import modelos.Cliente;

/**
 *
 * @author alunov2
 */
public class ClienteBD extends ConexaoBanco {

    String sql;

    public void inserirNovoCliente(Cliente cliente) {
        try {

            conectarBanco();

            stm = con.createStatement();

            String data = cliente.getCliente_data_atividade().get(Calendar.YEAR) + "-" + (cliente.getCliente_data_atividade().get(Calendar.MONTH) + 1) + "-" + cliente.getCliente_data_atividade().get(Calendar.DAY_OF_MONTH);
            sql = "INSERT INTO CLIENTE "
                    + "(CLIENTE_NOME, "
                    + "CLIENTE_FANTASIA, "
                    + "CLIENTE_CPF, "
                    + "CLIENTE_CNPJ, "
                    + "CLIENTE_INSC_ESTADUAL, "
                    + "CLIENTE_INSC_MUNICIPAL, "
                    + "CLIENTE_ENDERECO, "
                    + "CLIENTE_NUMERO, "
                    + "CLIENTE_COMPLEMENTO, "
                    + "CLIENTE_BAIRRO, "
                    + "CLIENTE_MUNICIPIO, "
                    + "CLIENTE_ESTADO, "
                    + "CLIENTE_CEP, "
                    + "CLIENTE_TELEFONE, "
                    + "CLIENTE_CELULAR,"
                    + "CLIENTE_EMAIL,"
                    + "CLIENTE_DATA_ATIVIDADE,"
                    + "CLIENTE_UTMN,"
                    + "CLIENTE_UTME,"
                    + "CATEGORIA_ID)"
                    + "VALUES ('"
                    + cliente.getCliente_nome() + "', '"
                    + cliente.getCliente_fantasia() + "', '"
                    + cliente.getCliente_cpf() + "', '"
                    + cliente.getCliente_cnpj() + "', '"
                    + cliente.getCliente_insc_estadual() + "', '"
                    + cliente.getCliente_insc_municipal() + "', '"
                    + cliente.getCliente_endereco() + "', '"
                    + cliente.getCliente_numero() + "', '"
                    + cliente.getCliente_complemento() + "', '"
                    + cliente.getCliente_bairro() + "', '"
                    + cliente.getCliente_municipio() + "', '"
                    + cliente.getCliente_estado() + "', '"
                    + cliente.getCliente_cep() + "', '"
                    + cliente.getCliente_telefone() + "', '"
                    + cliente.getCliente_celular() + "', '"
                    + cliente.getCliente_email() + "', '"
                    + data + "', '"
                    + cliente.getCliente_utmn() + "', '"
                    + cliente.getCliente_utme() + "', "
                    + cliente.getCategoria_id() + ")";

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Nome: " + cliente.getCliente_nome() + " cadastrado com sucesso!", "Cadastro Finalizado", 3);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            desconectarBanco();
        }
    }

    public void removerCliente(int cliente_id, String cliente_nome) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "DELETE FROM RELATORIO WHERE cliente_id=" + cliente_id;

            stm.executeUpdate(sql);

            sql = "DELETE FROM TRA WHERE cliente_id=" + cliente_id;

            stm.executeUpdate(sql);

            sql = "DELETE FROM REQUERIMENTO_PLA WHERE cliente_id=" + cliente_id;

            stm.executeUpdate(sql);

            sql = "DELETE FROM RECIBO WHERE cliente_id=" + cliente_id;

            stm.executeUpdate(sql);

            sql = "DELETE FROM CLIENTE WHERE cliente_id=" + cliente_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Cliente " + cliente_nome + " foi deletado com sucesso!", "Aviso", 3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public void alterarCliente(int cliente_id, Cliente cliente) {
        try {
            conectarBanco();

            stm = con.createStatement();

            String data = cliente.getCliente_data_atividade().get(Calendar.YEAR) + "-" + (cliente.getCliente_data_atividade().get(Calendar.MONTH) + 1) + "-" + cliente.getCliente_data_atividade().get(Calendar.DAY_OF_MONTH);
            // CLIENTE_CONTRATANTE, CLIENTE_NOME, CLIENTE_FANTASIA, CLIENTE_CNPJ_CPF, CLIENTE_INSC_ESTADUAL, CLIENTE_INSC_MUNICIPAL, CLIENTE_ENDERECO, CLIENTE_NUMERO, CLIENTE_COMPLEMENTO, CLIENTE_BAIRRO, CLIENTE_MUNICIPIO, CLIENTE_ESTADO, CLIENTE_CEP, CLIENTE_TELEFONE, CLIENTE_CELULAR
            sql = "UPDATE CLIENTE "
                    + "SET CLIENTE_NOME='" + cliente.getCliente_nome() + "', CLIENTE_FANTASIA='" + cliente.getCliente_fantasia() + "', CLIENTE_CPF='" + cliente.getCliente_cpf() + "', CLIENTE_CNPJ='" + cliente.getCliente_cnpj() + "', CLIENTE_INSC_ESTADUAL='" + cliente.getCliente_insc_estadual() + "', CLIENTE_INSC_MUNICIPAL='" + cliente.getCliente_insc_municipal() + "', CLIENTE_ENDERECO='" + cliente.getCliente_endereco() + "', CLIENTE_NUMERO='" + cliente.getCliente_numero() + "', CLIENTE_COMPLEMENTO='" + cliente.getCliente_complemento() + "', CLIENTE_BAIRRO='" + cliente.getCliente_bairro() + "', CLIENTE_MUNICIPIO='" + cliente.getCliente_municipio() + "', CLIENTE_ESTADO='" + cliente.getCliente_estado() + "', CLIENTE_CEP='" + cliente.getCliente_cep() + "', CLIENTE_TELEFONE='" + cliente.getCliente_telefone() + "', CLIENTE_CELULAR='" + cliente.getCliente_celular() + "', CLIENTE_EMAIL='" + cliente.getCliente_email() + "', CLIENTE_DATA_ATIVIDADE='" + data + "', CLIENTE_UTMN='" + cliente.getCliente_utmn() + "', CLIENTE_UTME='" + cliente.getCliente_utme() + "', CATEGORIA_ID=" + cliente.getCategoria_id() + " "
                    + "WHERE cliente_id=" + cliente_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<Cliente> selecionarTodosClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList();
        Cliente cliente = new Cliente();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT * "
                    + "FROM CLIENTE ORDER BY cliente_id DESC";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                cliente = new Cliente();

                // CLIENTE_CONTRATANTE, CLIENTE_NOME, CLIENTE_FANTASIA, CLIENTE_CNPJ_CPF, CLIENTE_INSC_ESTADUAL, CLIENTE_INSC_MUNICIPAL, CLIENTE_ENDERECO, CLIENTE_NUMERO, CLIENTE_COMPLEMENTO, CLIENTE_BAIRRO, CLIENTE_MUNICIPIO, CLIENTE_ESTADO, CLIENTE_CEP, CLIENTE_TELEFONE, CLIENTE_CELULAR
                cliente.setCliente_id(tabelaRetornada.getInt("cliente_id"));
                cliente.setCliente_nome(tabelaRetornada.getString("CLIENTE_NOME"));
                cliente.setCliente_fantasia(tabelaRetornada.getString("CLIENTE_FANTASIA"));
                cliente.setCliente_cpf(tabelaRetornada.getString("CLIENTE_CPF"));
                cliente.setCliente_cnpj(tabelaRetornada.getString("CLIENTE_CNPJ"));
                cliente.setCliente_insc_estadual(tabelaRetornada.getString("CLIENTE_INSC_ESTADUAL"));
                cliente.setCliente_insc_municipal(tabelaRetornada.getString("CLIENTE_INSC_MUNICIPAL"));
                cliente.setCliente_endereco(tabelaRetornada.getString("CLIENTE_ENDERECO"));
                cliente.setCliente_numero(tabelaRetornada.getString("CLIENTE_NUMERO"));
                cliente.setCliente_complemento(tabelaRetornada.getString("CLIENTE_COMPLEMENTO"));
                cliente.setCliente_bairro(tabelaRetornada.getString("CLIENTE_BAIRRO"));
                cliente.setCliente_municipio(tabelaRetornada.getString("CLIENTE_MUNICIPIO"));
                cliente.setCliente_estado(tabelaRetornada.getString("CLIENTE_ESTADO"));
                cliente.setCliente_cep(tabelaRetornada.getString("CLIENTE_CEP"));
                cliente.setCliente_telefone(tabelaRetornada.getString("CLIENTE_TELEFONE"));
                cliente.setCliente_celular(tabelaRetornada.getString("CLIENTE_CELULAR"));
                cliente.setCliente_email(tabelaRetornada.getString("CLIENTE_EMAIL"));
                cliente.setCliente_utmn(tabelaRetornada.getString("CLIENTE_UTMN"));
                cliente.setCliente_utme(tabelaRetornada.getString("CLIENTE_UTME"));
                cliente.setCliente_data_atividade(retornaDateBanco(tabelaRetornada.getDate("CLIENTE_DATA_ATIVIDADE")));
                cliente.setCategoria_id(tabelaRetornada.getInt("CATEGORIA_ID"));

                listaClientes.add(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaClientes;
        }

    }

    public int pesquisarCpf(int id, String cpf) {
        int situacao = 0;
        System.out.println("ID = " + id);
        String variavel_id = "";

        try {
            conectarBanco();

            stm = con.createStatement();

            if (id == 0) {
                sql = "SELECT * "
                        + "FROM CLIENTE WHERE CLIENTE_CPF = '" + cpf + "'";
            } else {
                sql = "SELECT * "
                        + "FROM CLIENTE WHERE CLIENTE_ID != " + id + " AND CLIENTE_CPF = '" + cpf + "'";
            }
            tabelaRetornada = stm.executeQuery(sql);
            if (tabelaRetornada.next()) {
                situacao = 1;
            } else {
                situacao = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return situacao;
        }
    }

    public int pesquisarCnpj(int id, String cnpj) {
        int situacao = 0;

        try {
            conectarBanco();

            stm = con.createStatement();

            if (id == 0) {
                sql = "SELECT * "
                        + "FROM CLIENTE WHERE CLIENTE_CNPJ = '" + cnpj + "'";
            } else {
                sql = "SELECT * "
                        + "FROM CLIENTE WHERE CLIENTE_ID != " + id + " AND CLIENTE_CNPJ = '" + cnpj + "'";
            }

            tabelaRetornada = stm.executeQuery(sql);
            if (tabelaRetornada.next()) {
                situacao = 1;
            } else {
                situacao = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return situacao;
        }
    }

    public ArrayList<Cliente> selecionarClientesCategoria(int categoria_id) {
        ArrayList<Cliente> listaClientes = new ArrayList();
        Cliente cliente = new Cliente();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT c.* FROM cliente c "
                    + "INNER JOIN categoria cat ON cat.categoria_id = c.categoria_id "
                    + "WHERE cat.categoria_id = " + categoria_id + " ORDER by c.cliente_id DESC";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                cliente = new Cliente();

                // CLIENTE_CONTRATANTE, CLIENTE_NOME, CLIENTE_FANTASIA, CLIENTE_CNPJ_CPF, CLIENTE_INSC_ESTADUAL, CLIENTE_INSC_MUNICIPAL, CLIENTE_ENDERECO, CLIENTE_NUMERO, CLIENTE_COMPLEMENTO, CLIENTE_BAIRRO, CLIENTE_MUNICIPIO, CLIENTE_ESTADO, CLIENTE_CEP, CLIENTE_TELEFONE, CLIENTE_CELULAR
                cliente.setCliente_id(tabelaRetornada.getInt("cliente_id"));
                cliente.setCliente_nome(tabelaRetornada.getString("CLIENTE_NOME"));
                cliente.setCliente_fantasia(tabelaRetornada.getString("CLIENTE_FANTASIA"));
                cliente.setCliente_cpf(tabelaRetornada.getString("CLIENTE_CPF"));
                cliente.setCliente_cnpj(tabelaRetornada.getString("CLIENTE_CNPJ"));
                cliente.setCliente_insc_estadual(tabelaRetornada.getString("CLIENTE_INSC_ESTADUAL"));
                cliente.setCliente_insc_municipal(tabelaRetornada.getString("CLIENTE_INSC_MUNICIPAL"));
                cliente.setCliente_endereco(tabelaRetornada.getString("CLIENTE_ENDERECO"));
                cliente.setCliente_numero(tabelaRetornada.getString("CLIENTE_NUMERO"));
                cliente.setCliente_complemento(tabelaRetornada.getString("CLIENTE_COMPLEMENTO"));
                cliente.setCliente_bairro(tabelaRetornada.getString("CLIENTE_BAIRRO"));
                cliente.setCliente_municipio(tabelaRetornada.getString("CLIENTE_MUNICIPIO"));
                cliente.setCliente_estado(tabelaRetornada.getString("CLIENTE_ESTADO"));
                cliente.setCliente_cep(tabelaRetornada.getString("CLIENTE_CEP"));
                cliente.setCliente_telefone(tabelaRetornada.getString("CLIENTE_TELEFONE"));
                cliente.setCliente_celular(tabelaRetornada.getString("CLIENTE_CELULAR"));
                cliente.setCliente_email(tabelaRetornada.getString("CLIENTE_EMAIL"));
                cliente.setCliente_utmn(tabelaRetornada.getString("CLIENTE_UTMN"));
                cliente.setCliente_utme(tabelaRetornada.getString("CLIENTE_UTME"));
                cliente.setCliente_data_atividade(retornaDateBanco(tabelaRetornada.getDate("CLIENTE_DATA_ATIVIDADE")));

                listaClientes.add(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaClientes;
        }

    }

    public Calendar retornaDateBanco(Date dataBanco) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBanco);
        return cal;
    }
}
