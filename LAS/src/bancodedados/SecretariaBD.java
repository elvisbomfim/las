/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.Secretaria;

/**
 *
 * @author Jean
 */
public class SecretariaBD extends ConexaoBanco {

    String sql;

    public void alterarSecretaria(int secretaria_id, Secretaria secretaria) {
        try {
            conectarBanco();

            stm = con.createStatement();

            //String dtNasc = aluno.getDataNascimento().get(Calendar.YEAR)+"-"+(aluno.getDataNascimento().get(Calendar.MONTH)+1)+"-"+aluno.getDataNascimento().get(Calendar.DAY_OF_MONTH);
            // SECRETARIA_CONTRATANTE, SECRETARIA_NOME, SECRETARIA_FANTASIA, SECRETARIA_CNPJ_CPF, SECRETARIA_INSC_ESTADUAL, SECRETARIA_INSC_MUNICIPAL, SECRETARIA_ENDERECO, SECRETARIA_NUMERO, SECRETARIA_COMPLEMENTO, SECRETARIA_BAIRRO, SECRETARIA_MUNICIPIO, SECRETARIA_ESTADO, SECRETARIA_CEP, SECRETARIA_TELEFONE, SECRETARIA_CELULAR
            sql = "UPDATE SECRETARIA "
                    + "SET SECRETARIA_ENDERECO='" + secretaria.getSecretaria_endereco() + "', SECRETARIA_BAIRRO='" + secretaria.getSecretaria_bairro() + "', SECRETARIA_CEP='" + secretaria.getSecretaria_cep() + "', SECRETARIA_CIDADE='" + secretaria.getSecretaria_cidade() + "', SECRETARIA_ESTADO='" + secretaria.getSecretaria_estado() + "', SECRETARIA_TELEFONE='" + secretaria.getSecretaria_telefone() + "', SECRETARIA_EMAIL='" + secretaria.getSecretaria_email() + "' "
                    + "WHERE secretaria_id=" + secretaria_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<Secretaria> selecionarSecretarias() {
        ArrayList<Secretaria> listaSecretarias = new ArrayList();
        Secretaria secretaria = new Secretaria();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT * "
                    + "FROM SECRETARIA";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                secretaria = new Secretaria();

                secretaria.setSecretaria_id(tabelaRetornada.getInt("secretaria_id"));
                secretaria.setSecretaria_endereco(tabelaRetornada.getString("SECRETARIA_ENDERECO"));
                secretaria.setSecretaria_numero(tabelaRetornada.getString("SECRETARIA_NUMERO"));
                secretaria.setSecretaria_bairro(tabelaRetornada.getString("SECRETARIA_BAIRRO"));
                secretaria.setSecretaria_cidade(tabelaRetornada.getString("SECRETARIA_CIDADE"));
                secretaria.setSecretaria_estado(tabelaRetornada.getString("SECRETARIA_ESTADO"));
                secretaria.setSecretaria_cep(tabelaRetornada.getString("SECRETARIA_CEP"));
                secretaria.setSecretaria_telefone(tabelaRetornada.getString("SECRETARIA_TELEFONE"));
                secretaria.setSecretaria_email(tabelaRetornada.getString("SECRETARIA_EMAIL"));

                listaSecretarias.add(secretaria);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaSecretarias;
        }

    }

}
