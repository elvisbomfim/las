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
import modelos.RelatorioPrincipal;

/**
 *
 * @author Jean
 */
public class RelatorioPrincipalBD extends ConexaoBanco {

    String sql;

    public void inserirNovoRPrincipal(RelatorioPrincipal r) {
        try {

            conectarBanco();

            stm = con.createStatement();

            String isaData = r.getRELATORIO_I_S_A_DATA().get(Calendar.YEAR) + "-" + (r.getRELATORIO_I_S_A_DATA().get(Calendar.MONTH) + 1) + "-" + r.getRELATORIO_I_S_A_DATA().get(Calendar.DAY_OF_MONTH);
            String dataAtual = r.getRELATORIO_DATA_ATUAL().get(Calendar.YEAR) + "-" + (r.getRELATORIO_DATA_ATUAL().get(Calendar.MONTH) + 1) + "-" + r.getRELATORIO_DATA_ATUAL().get(Calendar.DAY_OF_MONTH);

            //novo sql
            sql = "INSERT INTO RELATORIO "
                    + "(RELATORIO_AREA_UTIL, "
                    + "RELATORIO_AREA_COBERTA, "
                    + "RELATORIO_AREA_DESCOBERTA, "
                    + "RELATORIO_N_HABITACOES, "
                    + "RELATORIO_AREA_INTERVENCAO, "
                    + "RELATORIO_ALTURA_DO_TALUDE, "
                    + "CLIENTE_ID, "
                    + "RELATORIO_TIPO_DE_FINANCIAMENTO, "
                    + "RELATORIO_LOCALIZACAO, "
                    + "RELATORIO_INSERIDO_EM_AREA, "
                    + "RELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR, "
                    + "RELATORIO_LOCALIZACAO_LEI, "
                    + "RELATORIO_LOCALIZACAO_PLANO, "
                    + "RELATORIO_HA_RESIDENCIAS, "
                    + "RELATORIO_AREA_DA_UC, "
                    + "RELATORIO_AREA_DA_UC_NOME, "
                    + "RELATORIO_AREA_DA_UC_ADMINISTRADOR, "
                    + "RELATORIO_AREA_DA_UC_N_DOCUMENTO, "
                    + "RELATORIO_HA_PATRIMONIO, "
                    + "RELATORIO_N_IPHAN, "
                    + "RELATORIO_SUPRECAO_VEGETACAO, "
                    + "RELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF, "
                    + "RELATORIO_LOTE_DE_MENOR_AREA,"
                    + "RELATORIO_CONDICOES_DO_TERRENO, "
                    + "RELATORIO_CONDICOES_DO_TERRENO_OPCOES, "
                    + "RELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR, "
                    + "RELATORIO_DECLIVIDADE, RELATORIO_DECLIVIDADE_ESPECIFICAR, "
                    + "RELATORIO_COORDENADAS_UTM_N, "
                    + "RELATORIO_COORDENADAS_UTM_E, "
                    + "RELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE, "
                    + "RELATORIO_I_S_A_DATA, "
                    + "RELATORIO_I_S_A_PREVISAO_OPERACAO, "
                    + "RELATORIO_I_S_A_N_EMPREGADOS, "
                    + "RELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA, "
                    + "RELATORIO_I_S_A_AREA_DE_CORTE, "
                    + "RELATORIO_I_S_A_AREA_DE_ATERRO, "
                    + "RELATORIO_I_S_A_TALUDES_FORMADOS, "
                    + "RELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE, "
                    + "RELATORIO_S_D_P_E_ESPECIFICAR, "
                    //coluna sistema de drenagem
                    //coluna s d p e especificar
                    + "RELATORIO_RECURSOS_HIDRICOS_1, "
                    + "RELATORIO_R_H_NOME_1, "
                    + "RELATORIO_R_H_DISTANCIA_1, "
                    + "RELATORIO_BOTA_FORA1, "
                    + "RELATORIO_BOTA_FORA_UTM_N1, "
                    + "RELATORIO_BOTA_FORA_UTM_E1, "
                    + "RELATORIO_BOTA_FORA_OPCAO_1, "
                    + "RELATORIO_BOTA_FORA_OPCAO_2, "
                    + "RELATORIO_BOTA_FORA_N_DA_UC1, "
                    + "RELATORIO_BOTA_FORA2, "
                    + "RELATORIO_BOTA_FORA_UTM_N2, "
                    + "RELATORIO_BOTA_FORA_UTM_E2, "
                    + "RELATORIO_BOTA_FORA_AREA_EMPRESTIMO, "
                    + "RELATORIO_BOTA_FORA_OPCAO_3, "
                    + "RELATORIO_BOTA_FORA_OPCAO_4, "
                    + "RELATORIO_BOTA_FORA_N_DA_UC2, "
                    + "RELATORIO_RECURSOS_HIDRICOS_2, "
                    + "RELATORIO_R_H_NOME_2, " //TESTE
                    + "RELATORIO_R_H_DISTANCIA_2, "
                    + "RELATORIO_COMPLEMENTACAO_DO_SERVICO_TERRAPLANAGEM, "
                    + "RELATORIO_C_S_T_UTM_N, "
                    + "RELATORIO_C_S_T_UTM_E, "
                    + "RELATORIO_C_S_T_AREA_EMPRESTIMO,"
                    + "RELATORIO_C_S_T_OPCAO_1, "
                    + "RELATORIO_C_S_T_OPCAO_2, "
                    + "RELATORIO_C_S_T_N_DA_UC, "
                    + "RELATORIO_RECURSOS_HIDRICOS_3, "
                    + "RELATORIO_R_H_NOME_3, "
                    + "RELATORIO_R_H_DISTANCIA_3, "
                    + "RELATORIO_FONTES_DE_ABASTECIMENTO_AGUA, "
                    + "RELATORIO_F_A_A_EMPRESA, "
                    + "RELATORIO_F_A_A_POCO_TIPO,"
                    + "RELATORIO_F_A_A_POCO_QTD,"
                    + "RELATORIO_F_A_A_NUMERO_LICENCA,"
                    + "RELATORIO_F_A_A_CURSO_DAGUA_NOME,"
                    + "RELATORIO_F_A_A_LAGO_NOME,"
                    + "RELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR,"
                    + "RELATORIO_F_A_A_OUTRAS_ESPECIFICAR,"
                    + "RELATORIO_F_A_A_CONSUMO_DE_AGUA,"
                    + "RELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H,"
                    + "RELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O,"
                    + "RELATORIO_FONTES_DE_GERACAO_EFLUENTES,"
                    + "RELATORIO_MOVIMENTACAO_DE_TERRA,"
                    + "RELATORIO_F_G_E_OUTRO_ESPECIFICAR_1,"
                    + "RELATORIO_F_G_E_OUTRO_ESPECIFICAR_2,"
                    + "RELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO,"
                    + "RELATORIO_M_S_T_ESPECIFICAR,"
                    + "RELATORIO_M_S_T_CAIXA_DE_GORDURA,"
                    + "RELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR,"
                    + "RELATORIO_GERENCIAMENTO_DE_RESIDUOS,"
                    + "RELATORIO_G_R_ESPECIFICAR_1,"
                    + "RELATORIO_G_R_ESPECIFICAR_2,"
                    + "RELATORIO_G_R_ESPECIFICAR_3,"
                    + "RELATORIO_G_R_NOME_1,"
                    + "RELATORIO_G_R_NOME_2,"
                    + "RELATORIO_G_R_NOME_3,"
                    + "RELATORIO_G_R_NUMERO_LICENCA_1,"
                    + "RELATORIO_G_R_NUMERO_LICENCA_2,"
                    + "RELATORIO_G_R_NUMERO_LICENCA_3,"
                    + "RELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA,"
                    + "RELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES,"
                    + "RELATORIO_EMISSOES_ATMOSFERICAS,"
                    + "RELATORIO_E_A_ESPECIFICAR,"
                    + "RELATORIO_ROTEIRO_DE_ACESSO,"
                    + "RELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO,"
                    + "RELATORIO_CROQUI_PERGUNTA,"
                    + "RELATORIO_TEXTO_ANEXO,"
                    + "RELATORIO_DATA_ATUAL,"
                    + "PROFISSIONAL_ID,"
                    + "CATEGORIA_ID) "
                    + "VALUES ('"
                    + r.getRELATORIO_AREA_UTIL() + "', '"
                    + r.getRELATORIO_AREA_COBERTA() + "', '"
                    + r.getRELATORIO_AREA_DESCOBERTA() + "', '"
                    + r.getRELATORIO_N_HABITACOES() + "', '"
                    + r.getRELATORIO_AREA_INTERVENCAO() + "', '"
                    + r.getRELATORIO_ALTURA_DO_TALUDE() + "', "
                    + r.getCLIENTE_ID() + ", "
                    + r.getRELATORIO_TIPO_DE_FINANCIAMENTO() + ", "
                    + r.getRELATORIO_LOCALIZACAO() + ", "
                    + r.getRELATORIO_INSERIDO_EM_AREA() + ", '"
                    + r.getRELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR() + "', '"
                    + r.getRELATORIO_LOCALIZACAO_LEI() + "', '"
                    + r.getRELATORIO_LOCALIZACAO_PLANO() + "', "
                    + r.getRELATORIO_HA_RESIDENCIAS() + ", "
                    + r.getRELATORIO_AREA_DA_UC() + ", '"
                    + r.getRELATORIO_AREA_DA_UC_NOME() + "', '"
                    + r.getRELATORIO_AREA_DA_UC_ADMINISTRADOR() + "', '"
                    + r.getRELATORIO_AREA_DA_UC_N_DOCUMENTO() + "', "
                    + r.getRELATORIO_HA_PATRIMONIO() + ", '"
                    + r.getRELATORIO_N_IPHAN() + "', "
                    + r.getRELATORIO_SUPRECAO_VEGETACAO() + ", '"
                    + r.getRELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF() + "', '"
                    + r.getRELATORIO_LOTE_DE_MENOR_AREA() + "', "
                    + r.getRELATORIO_CONDICOES_DO_TERRENO() + ", "
                    + r.getRELATORIO_CONDICOES_DO_TERRENO_OPCOES() + ", '"
                    + r.getRELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR() + "', "
                    + r.getRELATORIO_DECLIVIDADE() + ", '"
                    + r.getRELATORIO_DECLIVIDADE_ESPECIFICAR() + "', '"
                    + r.getRELATORIO_COORDENADAS_UTM_N() + "', '"
                    + r.getRELATORIO_COORDENADAS_UTM_E() + "', "
                    + r.getRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE() + ", '"
                    + isaData + "', '"
                    + r.getRELATORIO_I_S_A_PREVISAO_OPERACAO() + "', '"
                    + r.getRELATORIO_I_S_A_N_EMPREGADOS() + "', '"
                    + r.getRELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA() + "', '"
                    + r.getRELATORIO_I_S_A_AREA_DE_CORTE() + "', '"
                    + r.getRELATORIO_I_S_A_AREA_DE_ATERRO() + "', '"
                    + r.getRELATORIO_I_S_A_TALUDES_FORMADOS() + "', "
                    + r.getRELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE() + ", '"
                    + r.getRELATORIO_S_D_P_E_ESPECIFICAR() + "', "
                    //coluna sistema de drenagem
                    //coluna s d p e especificar
                    + r.getRELATORIO_RECURSOS_HIDRICOS_1() + ", '"
                    + r.getRELATORIO_R_H_NOME_1() + "', '"
                    + r.getRELATORIO_R_H_DISTANCIA_1() + "', "
                    + r.getRELATORIO_BOTA_FORA1() + ", '"
                    + r.getRELATORIO_BOTA_FORA_UTM_N1() + "', '"
                    + r.getRELATORIO_BOTA_FORA_UTM_E1() + "', "
                    + r.getRELATORIO_BOTA_FORA_OPCAO_1() + ", "
                    + r.getRELATORIO_BOTA_FORA_OPCAO_2() + ", '"
                    + r.getRELATORIO_BOTA_FORA_N_DA_UC1() + "', "
                    + r.getRELATORIO_BOTA_FORA2() + ", '"
                    + r.getRELATORIO_BOTA_FORA_UTM_N2() + "', '"
                    + r.getRELATORIO_BOTA_FORA_UTM_E2() + "', '"
                    + r.getRELATORIO_BOTA_FORA_AREA_EMPRESTIMO() + "', "
                    + r.getRELATORIO_BOTA_FORA_OPCAO_3() + ", "
                    + r.getRELATORIO_BOTA_FORA_OPCAO_4() + ", '"
                    + r.getRELATORIO_BOTA_FORA_N_DA_UC2() + "', "
                    + r.getRELATORIO_RECURSOS_HIDRICOS_2() + ", '"
                    + r.getRELATORIO_R_H_NOME_2() + "', '" //TESTE
                    + r.getRELATORIO_R_H_DISTANCIA_2() + "', "
                    + r.getRELATORIO_COMPLEMENTACAO_DO_SERVICO_TERRAPLANAGEM() + ", '"
                    + r.getRELATORIO_C_S_T_UTM_N() + "', '"
                    + r.getRELATORIO_C_S_T_UTM_E() + "', '"
                    + r.getRELATORIO_C_S_T_AREA_EMPRESTIMO() + "', "
                    + r.getRELATORIO_C_S_T_OPCAO_1() + ", "
                    + r.getRELATORIO_C_S_T_OPCAO_2() + ", '"
                    + r.getRELATORIO_C_S_T_N_DA_UC() + "', "
                    + r.getRELATORIO_RECURSOS_HIDRICOS_3() + ", '"
                    + r.getRELATORIO_R_H_NOME_3() + "', '"
                    + r.getRELATORIO_R_H_DISTANCIA_3() + "', '"
                    + r.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA() + "', '"
                    + r.getRELATORIO_F_A_A_EMPRESA() + "', '"
                    + r.getRELATORIO_F_A_A_POCO_TIPO() + "', '"
                    + r.getRELATORIO_F_A_A_POCO_QTD() + "', '"
                    + r.getRELATORIO_F_A_A_NUMERO_LICENCA() + "', '"
                    + r.getRELATORIO_F_A_A_CURSO_DAGUA_NOME() + "', '"
                    + r.getRELATORIO_F_A_A_LAGO_NOME() + "', '"
                    + r.getRELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR() + "', '"
                    + r.getRELATORIO_F_A_A_OUTRAS_ESPECIFICAR() + "', '"
                    + r.getRELATORIO_F_A_A_CONSUMO_DE_AGUA() + "', '"
                    + r.getRELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H() + "', '"
                    + r.getRELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O() + "', '"
                    + r.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES() + "', "
                    + r.getRELATORIO_MOVIMENTACAO_DE_TERRA() + ", '"
                    + r.getRELATORIO_F_G_E_OUTRO_ESPECIFICAR_1() + "', '"
                    + r.getRELATORIO_F_G_E_OUTRO_ESPECIFICAR_2() + "', '"
                    + r.getRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO() + "', '"
                    + r.getRELATORIO_M_S_T_ESPECIFICAR() + "', '"
                    + r.getRELATORIO_M_S_T_CAIXA_DE_GORDURA() + "', '"
                    + r.getRELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR() + "', '"
                    + r.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS() + "', '"
                    + r.getRELATORIO_G_R_ESPECIFICAR_1() + "', '"
                    + r.getRELATORIO_G_R_ESPECIFICAR_2() + "', '"
                    + r.getRELATORIO_G_R_ESPECIFICAR_3() + "', '"
                    + r.getRELATORIO_G_R_NOME_1() + "', '"
                    + r.getRELATORIO_G_R_NOME_2() + "', '"
                    + r.getRELATORIO_G_R_NOME_3() + "', '"
                    + r.getRELATORIO_G_R_NUMERO_LICENCA_1() + "', '"
                    + r.getRELATORIO_G_R_NUMERO_LICENCA_2() + "', '"
                    + r.getRELATORIO_G_R_NUMERO_LICENCA_3() + "', '"
                    + r.getRELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA() + "', '"
                    + r.getRELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES() + "', '"
                    + r.getRELATORIO_EMISSOES_ATMOSFERICAS() + "', '"
                    + r.getRELATORIO_E_A_ESPECIFICAR() + "', '"
                    + r.getRELATORIO_ROTEIRO_DE_ACESSO() + "', '"
                    + r.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO() + "', "
                    + r.getRELATORIO_CROQUI_PERGUNTA() + ", '"
                    + r.getRELATORIO_TEXTO_ANEXO() + "', '"
                    + dataAtual + "', "
                    + r.getPROFISSIONAL_ID() + ", "
                    + r.getCATEGORIA_ID() + ")";

            // String com aspas simples e inteiros sem aspas simples.
            //        sql = "INSERT INTO RELATORIO (RELATORIO_AREA_UTIL, RELATORIO_AREA_COBERTA, RELATORIO_AREA_DESCOBERTA, RELATORIO_N_HABITACOES, RELATORIO_AREA_INTERVENCAO, RELATORIO_ALTURA_DO_TALUDE, CLIENTE_ID, RELATORIO_TIPO_DE_FINANCIAMENTO, RELATORIO_LOCALIZACAO, RELATORIO_INSERIDO_EM_AREA, RELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR, RELATORIO_LOCALIZACAO_LEI, RELATORIO_LOCALIZACAO_PLANO, RELATORIO_HA_RESIDENCIAS, RELATORIO_AREA_DA_UC, RELATORIO_AREA_DA_UC_NOME, RELATORIO_AREA_DA_UC_ADMINISTRADOR, RELATORIO_AREA_DA_UC_N_DOCUMENTO, RELATORIO_HA_PATRIMONIO, RELATORIO_N_IPHAN, RELATORIO_SUPRECAO_VEGETACAO, RELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF, RELATORIO_LOTE_DE_MENOR_AREA, RELATORIO_CONDICOES_DO_TERRENO, RELATORIO_CONDICOES_DO_TERRENO_OPCOES, RELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR, RELATORIO_DECLIVIDADE, RELATORIO_DECLIVIDADE_ESPECIFICAR, RELATORIO_COORDENADAS_UTM_N, RELATORIO_COORDENADAS_UTM_E, RELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE, RELATORIO_I_S_A_DATA, RELATORIO_I_S_A_PREVISAO_OPERACAO, RELATORIO_I_S_A_N_EMPREGADOS, RELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA, RELATORIO_I_S_A_AREA_DE_CORTE, RELATORIO_I_S_A_AREA_DE_ATERRO, RELATORIO_I_S_A_TALUDES_FORMADOS, RELATORIO_RECURSOS_HIDRICOS_1, RELATORIO_R_H_NOME_1, RELATORIO_R_H_DISTANCIA_1, RELATORIO_BOTA_FORA, RELATORIO_BOTA_FORA_UTM_N, RELATORIO_BOTA_FORA_UTM_E, RELATORIO_BOTA_FORA_OPCAO_1, RELATORIO_BOTA_FORA_OPCAO_2, RELATORIO_BOTA_FORA_N_DA_UC, RELATORIO_RECURSOS_HIDRICOS_2, RELATORIO_R_H_DISTANCIA_2, RELATORIO_COMPLEMENTACAO_DO_SERVICO_TERRAPLANAGEM, RELATORIO_C_S_T_UTM_N, RELATORIO_C_S_T_UTM_E, RELATORIO_C_S_T_AREA_EMPRESTIMO, RELATORIO_C_S_T_OPCAO_1, RELATORIO_C_S_T_OPCAO_2, RELATORIO_C_S_T_N_DA_UC, RELATORIO_RECURSOS_HIDRICOS_3, RELATORIO_R_H_NOME_3, RELATORIO_R_H_DISTANCIA_3, RELATORIO_FONTES_DE_ABASTECIMENTO_AGUA, RELATORIO_F_A_A_EMPRESA, RELATORIO_F_A_A_POCO_TIPO, RELATORIO_F_A_A_POCO_QTD, RELATORIO_F_A_A_NUMERO_LICENCA, RELATORIO_F_A_A_CURSO_DAGUA_NOME, RELATORIO_F_A_A_LAGO_NOME, RELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR, RELATORIO_F_A_A_OUTRAS_ESPECIFICAR, RELATORIO_F_A_A_CONSUMO_DE_AGUA, RELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H, RELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O, RELATORIO_FONTES_DE_GERACAO_EFLUENTES, RELATORIO_MOVIMENTACAO_DE_TERRA, RELATORIO_F_G_E_OUTRO_ESPECIFICAR_1, RELATORIO_F_G_E_OUTRO_ESPECIFICAR_2, RELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO, RELATORIO_M_S_T_ESPECIFICAR, RELATORIO_M_S_T_CAIXA_DE_GORDURA,	RELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR, RELATORIO_GERENCIAMENTO_DE_RESIDUOS, RELATORIO_G_R_ESPECIFICAR_1, RELATORIO_G_R_ESPECIFICAR_2, RELATORIO_G_R_ESPECIFICAR_3, RELATORIO_G_R_NOME_1, RELATORIO_G_R_NOME_2, RELATORIO_G_R_NOME_3, RELATORIO_G_R_NUMERO_LICENCA_1, RELATORIO_G_R_NUMERO_LICENCA_2, RELATORIO_G_R_NUMERO_LICENCA_3, RELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA, RELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES, RELATORIO_EMISSOES_ATMOSFERICAS, RELATORIO_E_A_ESPECIFICAR, RELATORIO_ROTEIRO_DE_ACESSO, RELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO, RELATORIO_CROQUI_PERGUNTA, RELATORIO_TEXTO_ANEXO, RELATORIO_DATA_ATUAL, PROFISSIONAL_ID, CATEGORIA_ID) "
            //                + " VALUES ('"+ r.getRELATORIO_AREA_UTIL() + "', '"+ r.getRELATORIO_AREA_COBERTA() + "', '"+ r.getRELATORIO_AREA_DESCOBERTA() + "', '" + r.getRELATORIO_N_HABITACOES() + "', '"+ r.getRELATORIO_AREA_INTERVENCAO() + "', '" + r.getRELATORIO_ALTURA_DO_TALUDE() + "', '" + r.getCLIENTE_ID() + ", " + r.getRELATORIO_TIPO_DE_FINANCIAMENTO() + ", " + r.getRELATORIO_LOCALIZACAO() + ", " + r.getRELATORIO_INSERIDO_EM_AREA() + ", '" + r.getRELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR() + "', '" + r.getRELATORIO_LOCALIZACAO_LEI() + "', '" + r.getRELATORIO_LOCALIZACAO_PLANO() + "', " + r.getRELATORIO_HA_RESIDENCIAS() + ", " + r.getRELATORIO_AREA_DA_UC() + ", " + r.getRELATORIO_AREA_DA_UC_NOME() + ", '" + r.getRELATORIO_AREA_DA_UC_ADMINISTRADOR() + "', '" + r.getRELATORIO_AREA_DA_UC_N_DOCUMENTO() + "', " + r.getRELATORIO_HA_PATRIMONIO() + ", '" + r.getRELATORIO_N_IPHAN() + "', " + r.getRELATORIO_SUPRECAO_VEGETACAO() + ", '" + r.getRELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF() + "', '" + r.getRELATORIO_LOTE_DE_MENOR_AREA() + "', " + r.getRELATORIO_CONDICOES_DO_TERRENO() + ", " + r.getRELATORIO_CONDICOES_DO_TERRENO_OPCOES() + ", '" + r.getRELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR() + "', " + r.getRELATORIO_DECLIVIDADE() + ", '" + r.getRELATORIO_DECLIVIDADE_ESPECIFICAR() + "', '" + r.getRELATORIO_COORDENADAS_UTM_N() + "', '" + r.getRELATORIO_COORDENADAS_UTM_E() + "', " + r.getRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE() + ", '" + r.getRELATORIO_I_S_A_DATA() + "', '" + r.getRELATORIO_I_S_A_PREVISAO_OPERACAO() + "', '" + r.getRELATORIO_I_S_A_N_EMPREGADOS() + ", " + r.getRELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA() + "', '" + r.getRELATORIO_I_S_A_AREA_DE_CORTE() + "', '" + r.getRELATORIO_I_S_A_AREA_DE_ATERRO() + "', '" + r.getRELATORIO_I_S_A_TALUDES_FORMADOS() + "', " + r.getRELATORIO_RECURSOS_HIDRICOS_1() + ", '" + r.getRELATORIO_R_H_NOME_1() + "', '" + r.getRELATORIO_R_H_DISTANCIA_1() + "', " + r.getRELATORIO_BOTA_FORA() + ", '" + r.getRELATORIO_BOTA_FORA_UTM_N() + "', '" + r.getRELATORIO_BOTA_FORA_UTM_E() + "', " + r.getRELATORIO_BOTA_FORA_OPCAO_1() + ", " + r.getRELATORIO_BOTA_FORA_OPCAO_2() + ", '" + r.getRELATORIO_BOTA_FORA_N_DA_UC() + "', " + r.getRELATORIO_RECURSOS_HIDRICOS_2() + ", '" + r.getRELATORIO_R_H_NOME_2() + "', '" + r.getRELATORIO_R_H_DISTANCIA_2() + "', " + r.getRELATORIO_COMPLEMENTACAO_DO_SERVICO_TERRAPLANAGEM() + ", '" + r.getRELATORIO_C_S_T_UTM_N() + "', '" + r.getRELATORIO_C_S_T_UTM_E() + "', '" + r.getRELATORIO_C_S_T_AREA_EMPRESTIMO() + "', " + r.getRELATORIO_C_S_T_OPCAO_1() + ", " + r.getRELATORIO_C_S_T_OPCAO_2() + ", '" + r.getRELATORIO_C_S_T_N_DA_UC() + ", " + r.getRELATORIO_RECURSOS_HIDRICOS_3() + ", '" + r.getRELATORIO_R_H_NOME_3() + "', '" + r.getRELATORIO_R_H_DISTANCIA_3() + "', '" + r.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA() + "', '" + r.getRELATORIO_F_A_A_EMPRESA() + "', '" + r.getRELATORIO_F_A_A_POCO_TIPO() + "', '" + r.getRELATORIO_F_A_A_POCO_QTD() + "', '" + r.getRELATORIO_F_A_A_NUMERO_LICENCA() + "', '" + r.getRELATORIO_F_A_A_CURSO_DAGUA_NOME() + "', '" + r.getRELATORIO_F_A_A_LAGO_NOME() + "', '" + r.getRELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR() + "', '" + r.getRELATORIO_F_A_A_OUTRAS_ESPECIFICAR() + "', '" + r.getRELATORIO_F_A_A_CONSUMO_DE_AGUA() + "', '" + r.getRELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H() + "', '" + r.getRELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O() + "', '" + r.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES() + "', " + r.getRELATORIO_MOVIMENTACAO_DE_TERRA() + ", '" + r.getRELATORIO_F_G_E_OUTRO_ESPECIFICAR_1() + "', '" + r.getRELATORIO_F_G_E_OUTRO_ESPECIFICAR_2() + "', '" + r.getRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO() + "', '" + r.getRELATORIO_M_S_T_ESPECIFICAR() + "', '" + r.getRELATORIO_M_S_T_CAIXA_DE_GORDURA() + "', '" + r.getRELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR() + "', '" + r.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS() + "', '" + r.getRELATORIO_G_R_ESPECIFICAR_1() + "', '" + r.getRELATORIO_G_R_ESPECIFICAR_2() + "', '" + r.getRELATORIO_G_R_ESPECIFICAR_3() + "', '" + r.getRELATORIO_G_R_NOME_1() + "', '" + r.getRELATORIO_G_R_NOME_2() + "', '" + r.getRELATORIO_G_R_NOME_3() + "', '" + r.getRELATORIO_G_R_NUMERO_LICENCA_1() + "', '" + r.getRELATORIO_G_R_NUMERO_LICENCA_2() + "', '" + r.getRELATORIO_G_R_NUMERO_LICENCA_3() + "', '" + r.getRELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA() + "', '" + r.getRELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES() + "', '" + r.getRELATORIO_EMISSOES_ATMOSFERICAS() + "', '" + r.getRELATORIO_E_A_ESPECIFICAR() + "', '" + r.getRELATORIO_ROTEIRO_DE_ACESSO() + "', '" + r.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO() + "', " + r.getRELATORIO_CROQUI_PERGUNTA() + ", '" + r.getRELATORIO_TEXTO_ANEXO() + "', '" + r.getRELATORIO_DATA_ATUAL() + "', " + r.getPROFISSIONAL_ID() + ", " + r.getCATEGORIA_ID() + ")";
            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Relatório cadastrado com sucesso!", "Cadastro Finalizado", 3);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            desconectarBanco();
        }
    }

    public void removerRelatorio(int relatorio_id) {
        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "DELETE FROM relatorio WHERE relatorio_id=" + relatorio_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "O relatorio foi deletado com sucesso!", "Aviso", 3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public void alterarRelatorio(int relatorio_id, RelatorioPrincipal r) {
        try {
            conectarBanco();

            stm = con.createStatement();
            
            String isaData = r.getRELATORIO_I_S_A_DATA().get(Calendar.YEAR) + "-" + (r.getRELATORIO_I_S_A_DATA().get(Calendar.MONTH) + 1) + "-" + r.getRELATORIO_I_S_A_DATA().get(Calendar.DAY_OF_MONTH);
            String dataAtual = r.getRELATORIO_DATA_ATUAL().get(Calendar.YEAR) + "-" + (r.getRELATORIO_DATA_ATUAL().get(Calendar.MONTH) + 1) + "-" + r.getRELATORIO_DATA_ATUAL().get(Calendar.DAY_OF_MONTH);

            sql = "UPDATE RELATORIO SET "
                    + " RELATORIO_AREA_UTIL='" + r.getRELATORIO_AREA_UTIL() + "',"
                    + "	RELATORIO_AREA_COBERTA='" + r.getRELATORIO_AREA_COBERTA() + "',"
                    + "	RELATORIO_AREA_DESCOBERTA='" + r.getRELATORIO_AREA_DESCOBERTA() + "',"
                    + "	RELATORIO_N_HABITACOES='" + r.getRELATORIO_N_HABITACOES() + "',"
                    + "	RELATORIO_AREA_INTERVENCAO='" + r.getRELATORIO_AREA_INTERVENCAO() + "',"
                    + "	RELATORIO_ALTURA_DO_TALUDE='" + r.getRELATORIO_ALTURA_DO_TALUDE() + "',"
                    + "	CLIENTE_ID=" + r.getCLIENTE_ID() + ","
                    + "	RELATORIO_TIPO_DE_FINANCIAMENTO=" + r.getRELATORIO_TIPO_DE_FINANCIAMENTO() + ","
                    + "	RELATORIO_LOCALIZACAO=" + r.getRELATORIO_LOCALIZACAO() + ","
                    + "	RELATORIO_INSERIDO_EM_AREA=" + r.getRELATORIO_INSERIDO_EM_AREA() + ","
                    + "	RELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR='" + r.getRELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR() + "',"
                    + "	RELATORIO_LOCALIZACAO_LEI='" + r.getRELATORIO_LOCALIZACAO_LEI() + "',"
                    + "	RELATORIO_LOCALIZACAO_PLANO='" + r.getRELATORIO_LOCALIZACAO_PLANO() + "',"
                    + "	RELATORIO_HA_RESIDENCIAS=" + r.getRELATORIO_HA_RESIDENCIAS() + ","
                    + "	RELATORIO_AREA_DA_UC=" + r.getRELATORIO_AREA_DA_UC() + ","
                    + "	RELATORIO_AREA_DA_UC_NOME='" + r.getRELATORIO_AREA_DA_UC_NOME() + "',"
                    + "	RELATORIO_AREA_DA_UC_ADMINISTRADOR='" + r.getRELATORIO_AREA_DA_UC_ADMINISTRADOR() + "',"
                    + "	RELATORIO_AREA_DA_UC_N_DOCUMENTO='" + r.getRELATORIO_AREA_DA_UC_N_DOCUMENTO() + "',"
                    + "	RELATORIO_HA_PATRIMONIO=" + r.getRELATORIO_HA_PATRIMONIO() + ","
                    + "	RELATORIO_N_IPHAN='" + r.getRELATORIO_N_IPHAN() + "',"
                    + "	RELATORIO_SUPRECAO_VEGETACAO=" + r.getRELATORIO_SUPRECAO_VEGETACAO() + ","
                    + "	RELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF='" + r.getRELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF() + "',"
                    + "	RELATORIO_LOTE_DE_MENOR_AREA='" + r.getRELATORIO_LOTE_DE_MENOR_AREA() + "',"
                    + "	RELATORIO_CONDICOES_DO_TERRENO=" + r.getRELATORIO_CONDICOES_DO_TERRENO() + ","
                    + "	RELATORIO_CONDICOES_DO_TERRENO_OPCOES=" + r.getRELATORIO_CONDICOES_DO_TERRENO_OPCOES() + ","
                    + "	RELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR='" + r.getRELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR() + "',"
                    + "	RELATORIO_DECLIVIDADE=" + r.getRELATORIO_DECLIVIDADE() + ","
                    + "	RELATORIO_DECLIVIDADE_ESPECIFICAR='" + r.getRELATORIO_DECLIVIDADE_ESPECIFICAR() + "',"
                    + "	RELATORIO_COORDENADAS_UTM_N='" + r.getRELATORIO_COORDENADAS_UTM_N() + "',"
                    + "	RELATORIO_COORDENADAS_UTM_E='" + r.getRELATORIO_COORDENADAS_UTM_E() + "',"
                    + "	RELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE=" + r.getRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE() + ","
                    + "	RELATORIO_I_S_A_DATA='" + isaData + "',"
                    + "	RELATORIO_I_S_A_PREVISAO_OPERACAO='" + r.getRELATORIO_I_S_A_PREVISAO_OPERACAO() + "',"
                    + "	RELATORIO_I_S_A_N_EMPREGADOS='" + r.getRELATORIO_I_S_A_N_EMPREGADOS() + "',"
                    + "	RELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA='" + r.getRELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA() + "',"
                    + "	RELATORIO_I_S_A_AREA_DE_CORTE='" + r.getRELATORIO_I_S_A_AREA_DE_CORTE() + "',"
                    + "	RELATORIO_I_S_A_AREA_DE_ATERRO='" + r.getRELATORIO_I_S_A_AREA_DE_ATERRO() + "',"
                    + "	RELATORIO_I_S_A_TALUDES_FORMADOS='" + r.getRELATORIO_I_S_A_TALUDES_FORMADOS() + "',"
                    + "	RELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE=" + r.getRELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE() + ","
                    + "	RELATORIO_S_D_P_E_ESPECIFICAR='" + r.getRELATORIO_S_D_P_E_ESPECIFICAR() + "',"
                    + "	RELATORIO_RECURSOS_HIDRICOS_1=" + r.getRELATORIO_RECURSOS_HIDRICOS_1() + ","
                    + "	RELATORIO_R_H_NOME_1='" + r.getRELATORIO_R_H_NOME_1() + "',"
                    + "	RELATORIO_R_H_DISTANCIA_1='" + r.getRELATORIO_R_H_DISTANCIA_1() + "',"
                    + " RELATORIO_BOTA_FORA1=" + r.getRELATORIO_BOTA_FORA1() + ","
                    + " RELATORIO_BOTA_FORA_UTM_N1='" + r.getRELATORIO_BOTA_FORA_UTM_N1() + "',"
                    + " RELATORIO_BOTA_FORA_UTM_E1='" + r.getRELATORIO_BOTA_FORA_UTM_E1() + "',"
                    + " RELATORIO_BOTA_FORA_OPCAO_1=" + r.getRELATORIO_BOTA_FORA_OPCAO_1() + ","
                    + " RELATORIO_BOTA_FORA_OPCAO_2=" + r.getRELATORIO_BOTA_FORA_OPCAO_2() + ","
                    + " RELATORIO_BOTA_FORA_N_DA_UC1='" + r.getRELATORIO_BOTA_FORA_N_DA_UC1() + "',"
                    + " RELATORIO_BOTA_FORA2=" + r.getRELATORIO_BOTA_FORA2() + ","
                    + " RELATORIO_BOTA_FORA_UTM_N2='" + r.getRELATORIO_BOTA_FORA_UTM_N2() + "',"
                    + " RELATORIO_BOTA_FORA_UTM_E2='" + r.getRELATORIO_BOTA_FORA_UTM_E2() + "',"
                    + " RELATORIO_BOTA_FORA_AREA_EMPRESTIMO='" + r.getRELATORIO_BOTA_FORA_AREA_EMPRESTIMO() + "',"
                    + " RELATORIO_BOTA_FORA_OPCAO_3=" + r.getRELATORIO_RECURSOS_HIDRICOS_2() + ","
                    + " RELATORIO_BOTA_FORA_OPCAO_4=" + r.getRELATORIO_RECURSOS_HIDRICOS_2() + ","
                    + " RELATORIO_BOTA_FORA_N_DA_UC2='" + r.getRELATORIO_RECURSOS_HIDRICOS_2() + "',"
                    + "	RELATORIO_RECURSOS_HIDRICOS_2=" + r.getRELATORIO_RECURSOS_HIDRICOS_2() + ","
                    + "	RELATORIO_R_H_NOME_2='" + r.getRELATORIO_R_H_NOME_2() + "',"
                    + "	RELATORIO_R_H_DISTANCIA_2='" + r.getRELATORIO_R_H_DISTANCIA_2() + "',"
                    + "	RELATORIO_COMPLEMENTACAO_DO_SERVICO_TERRAPLANAGEM=" + r.getRELATORIO_COMPLEMENTACAO_DO_SERVICO_TERRAPLANAGEM() + ","
                    + "	RELATORIO_C_S_T_UTM_N='" + r.getRELATORIO_C_S_T_UTM_N() + "',"
                    + "	RELATORIO_C_S_T_UTM_E='" + r.getRELATORIO_C_S_T_UTM_E() + "',\n"
                    + "	RELATORIO_C_S_T_AREA_EMPRESTIMO='" + r.getRELATORIO_C_S_T_AREA_EMPRESTIMO() + "',"
                    + "	RELATORIO_C_S_T_OPCAO_1=" + r.getRELATORIO_C_S_T_OPCAO_1() + ","
                    + "	RELATORIO_C_S_T_OPCAO_2=" + r.getRELATORIO_C_S_T_OPCAO_2() + ","
                    + "	RELATORIO_C_S_T_N_DA_UC='" + r.getRELATORIO_C_S_T_N_DA_UC() + "',"
                    + "	RELATORIO_RECURSOS_HIDRICOS_3=" + r.getRELATORIO_RECURSOS_HIDRICOS_3() + ","
                    + "	RELATORIO_R_H_NOME_3='" + r.getRELATORIO_R_H_NOME_3() + "',"
                    + "	RELATORIO_R_H_DISTANCIA_3='" + r.getRELATORIO_R_H_DISTANCIA_3() + "',"
                    + "	RELATORIO_FONTES_DE_ABASTECIMENTO_AGUA='" + r.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA() + "',"
                    + "	RELATORIO_F_A_A_EMPRESA='" + r.getRELATORIO_F_A_A_EMPRESA() + "',"
                    + "	RELATORIO_F_A_A_POCO_TIPO='" + r.getRELATORIO_F_A_A_POCO_TIPO() + "',"
                    + "	RELATORIO_F_A_A_POCO_QTD='" + r.getRELATORIO_F_A_A_POCO_QTD() + "',"
                    + "	RELATORIO_F_A_A_NUMERO_LICENCA='" + r.getRELATORIO_F_A_A_NUMERO_LICENCA() + "',"
                    + "	RELATORIO_F_A_A_CURSO_DAGUA_NOME='" + r.getRELATORIO_F_A_A_CURSO_DAGUA_NOME() + "',"
                    + "	RELATORIO_F_A_A_LAGO_NOME='" + r.getRELATORIO_F_A_A_LAGO_NOME() + "',"
                    + "	RELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR='" + r.getRELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR() + "',"
                    + "	RELATORIO_F_A_A_OUTRAS_ESPECIFICAR='" + r.getRELATORIO_F_A_A_OUTRAS_ESPECIFICAR() + "',"
                    + "	RELATORIO_F_A_A_CONSUMO_DE_AGUA='" + r.getRELATORIO_F_A_A_CONSUMO_DE_AGUA() + "',"
                    + "	RELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H='" + r.getRELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H() + "',"
                    + "	RELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O='" + r.getRELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O() + "',"
                    + "	RELATORIO_FONTES_DE_GERACAO_EFLUENTES='" + r.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES() + "',"
                    + "	RELATORIO_MOVIMENTACAO_DE_TERRA=" + r.getRELATORIO_MOVIMENTACAO_DE_TERRA() + ","
                    + "	RELATORIO_F_G_E_OUTRO_ESPECIFICAR_1='" + r.getRELATORIO_F_G_E_OUTRO_ESPECIFICAR_1() + "',"
                    + "	RELATORIO_F_G_E_OUTRO_ESPECIFICAR_2='" + r.getRELATORIO_F_G_E_OUTRO_ESPECIFICAR_2() + "',"
                    + "	RELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO='" + r.getRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO() + "',"
                    + "	RELATORIO_M_S_T_ESPECIFICAR='" + r.getRELATORIO_M_S_T_ESPECIFICAR() + "',"
                    + "	RELATORIO_M_S_T_CAIXA_DE_GORDURA='" + r.getRELATORIO_M_S_T_CAIXA_DE_GORDURA() + "',"
                    + "	RELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR='" + r.getRELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR() + "',"
                    + "	RELATORIO_GERENCIAMENTO_DE_RESIDUOS='" + r.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS() + "',"
                    + "	RELATORIO_G_R_ESPECIFICAR_1='" + r.getRELATORIO_G_R_ESPECIFICAR_1() + "',"
                    + "	RELATORIO_G_R_ESPECIFICAR_2='" + r.getRELATORIO_G_R_ESPECIFICAR_2() + "',"
                    + "	RELATORIO_G_R_ESPECIFICAR_3='" + r.getRELATORIO_G_R_ESPECIFICAR_3() + "',"
                    + "	RELATORIO_G_R_NOME_1='" + r.getRELATORIO_G_R_NOME_1() + "',"
                    + "	RELATORIO_G_R_NOME_2='" + r.getRELATORIO_G_R_NOME_2() + "',"
                    + "	RELATORIO_G_R_NOME_3='" + r.getRELATORIO_G_R_NOME_3() + "',"
                    + "	RELATORIO_G_R_NUMERO_LICENCA_1='" + r.getRELATORIO_G_R_NUMERO_LICENCA_1() + "',"
                    + "	RELATORIO_G_R_NUMERO_LICENCA_2='" + r.getRELATORIO_G_R_NUMERO_LICENCA_2() + "',"
                    + "	RELATORIO_G_R_NUMERO_LICENCA_3='" + r.getRELATORIO_G_R_NUMERO_LICENCA_3() + "',"
                    + "	RELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA='" + r.getRELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA() + "',"
                    + "	RELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES='" + r.getRELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES() + "',"
                    + "	RELATORIO_EMISSOES_ATMOSFERICAS='" + r.getRELATORIO_EMISSOES_ATMOSFERICAS() + "',"
                    + "	RELATORIO_E_A_ESPECIFICAR='" + r.getRELATORIO_E_A_ESPECIFICAR() + "',"
                    + "	RELATORIO_ROTEIRO_DE_ACESSO='" + r.getRELATORIO_ROTEIRO_DE_ACESSO() + "',"
                    + "	RELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO='" + r.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO() + "',"
                    + "	RELATORIO_CROQUI_PERGUNTA=" + r.getRELATORIO_CROQUI_PERGUNTA() + ","
                    + "	RELATORIO_TEXTO_ANEXO='" + r.getRELATORIO_TEXTO_ANEXO() + "',"
                    + "	RELATORIO_DATA_ATUAL='" + dataAtual + "',"
                    + "	PROFISSIONAL_ID=" + r.getPROFISSIONAL_ID() + ","
                    + "	CATEGORIA_ID=" + r.getCATEGORIA_ID()
                    + " WHERE relatorio_id= " + relatorio_id;

            stm.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
        }
    }

    public ArrayList<RelatorioPrincipal> selecionarTodosRelatorios(int relatorio_id) {
        ArrayList<RelatorioPrincipal> listaR = new ArrayList();
        RelatorioPrincipal r = new RelatorioPrincipal();

        try {
            conectarBanco();

            stm = con.createStatement();

            sql = "SELECT r.*, cat.*, c.*, p.* FROM relatorio r "
                    + "INNER JOIN categoria cat ON cat.categoria_id = r.categoria_id "
                    + "INNER JOIN cliente c ON c.cliente_id = r.cliente_id "
                    + "INNER JOIN profissional p ON p.profissional_id = r.profissional_id "
                    + "WHERE cat.CATEGORIA_ID = " + relatorio_id +" ORDER by r.relatorio_id DESC";

            tabelaRetornada = stm.executeQuery(sql);

            while (tabelaRetornada.next()) // Enquanto houverem linhas na tabela para serem lidas
            {
                r = new RelatorioPrincipal();

                r.setRELATORIO_ID(tabelaRetornada.getInt("RELATORIO_ID"));
                r.setRELATORIO_AREA_UTIL(tabelaRetornada.getString("RELATORIO_AREA_UTIL"));
                r.setRELATORIO_AREA_COBERTA(tabelaRetornada.getString("RELATORIO_AREA_COBERTA"));
                r.setRELATORIO_AREA_DESCOBERTA(tabelaRetornada.getString("RELATORIO_AREA_DESCOBERTA"));
                r.setRELATORIO_N_HABITACOES(tabelaRetornada.getString("RELATORIO_N_HABITACOES"));
                r.setRELATORIO_AREA_INTERVENCAO(tabelaRetornada.getString("RELATORIO_AREA_INTERVENCAO"));
                r.setRELATORIO_ALTURA_DO_TALUDE(tabelaRetornada.getString("RELATORIO_ALTURA_DO_TALUDE"));
                r.setCLIENTE_ID(tabelaRetornada.getInt("CLIENTE_ID"));
                r.setRELATORIO_TIPO_DE_FINANCIAMENTO(tabelaRetornada.getInt("RELATORIO_TIPO_DE_FINANCIAMENTO"));
                r.setRELATORIO_LOCALIZACAO(tabelaRetornada.getInt("RELATORIO_LOCALIZACAO"));
                r.setRELATORIO_INSERIDO_EM_AREA(tabelaRetornada.getInt("RELATORIO_INSERIDO_EM_AREA"));
                r.setRELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR(tabelaRetornada.getString("RELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR"));
                r.setRELATORIO_LOCALIZACAO_LEI(tabelaRetornada.getString("RELATORIO_LOCALIZACAO_LEI"));
                r.setRELATORIO_LOCALIZACAO_PLANO(tabelaRetornada.getString("RELATORIO_LOCALIZACAO_PLANO"));
                r.setRELATORIO_HA_RESIDENCIAS(tabelaRetornada.getInt("RELATORIO_HA_RESIDENCIAS"));
                r.setRELATORIO_AREA_DA_UC(tabelaRetornada.getInt("RELATORIO_AREA_DA_UC"));
                r.setRELATORIO_AREA_DA_UC_NOME(tabelaRetornada.getString("RELATORIO_AREA_DA_UC_NOME"));
                r.setRELATORIO_AREA_DA_UC_ADMINISTRADOR(tabelaRetornada.getString("RELATORIO_AREA_DA_UC_ADMINISTRADOR"));
                r.setRELATORIO_AREA_DA_UC_N_DOCUMENTO(tabelaRetornada.getString("RELATORIO_AREA_DA_UC_N_DOCUMENTO"));
                r.setRELATORIO_HA_PATRIMONIO(tabelaRetornada.getInt("RELATORIO_HA_PATRIMONIO"));
                r.setRELATORIO_N_IPHAN(tabelaRetornada.getString("RELATORIO_N_IPHAN"));
                r.setRELATORIO_SUPRECAO_VEGETACAO(tabelaRetornada.getInt("RELATORIO_SUPRECAO_VEGETACAO"));
                r.setRELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF(tabelaRetornada.getString("RELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF"));
                r.setRELATORIO_LOTE_DE_MENOR_AREA(tabelaRetornada.getString("RELATORIO_LOTE_DE_MENOR_AREA"));
                r.setRELATORIO_CONDICOES_DO_TERRENO(tabelaRetornada.getInt("RELATORIO_CONDICOES_DO_TERRENO"));
                r.setRELATORIO_CONDICOES_DO_TERRENO_OPCOES(tabelaRetornada.getInt("RELATORIO_CONDICOES_DO_TERRENO_OPCOES"));
                r.setRELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR(tabelaRetornada.getString("RELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR"));
                r.setRELATORIO_DECLIVIDADE(tabelaRetornada.getInt("RELATORIO_DECLIVIDADE"));
                r.setRELATORIO_DECLIVIDADE_ESPECIFICAR(tabelaRetornada.getString("RELATORIO_DECLIVIDADE_ESPECIFICAR"));
                r.setRELATORIO_COORDENADAS_UTM_N(tabelaRetornada.getString("RELATORIO_COORDENADAS_UTM_N"));
                r.setRELATORIO_COORDENADAS_UTM_E(tabelaRetornada.getString("RELATORIO_COORDENADAS_UTM_E"));
                r.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(tabelaRetornada.getInt("RELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE"));
                r.setRELATORIO_I_S_A_DATA(retornaDateBanco(tabelaRetornada.getDate("RELATORIO_I_S_A_DATA")));
                r.setRELATORIO_I_S_A_PREVISAO_OPERACAO(tabelaRetornada.getString("RELATORIO_I_S_A_PREVISAO_OPERACAO"));
                r.setRELATORIO_I_S_A_N_EMPREGADOS(tabelaRetornada.getString("RELATORIO_I_S_A_N_EMPREGADOS"));
                r.setRELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA(tabelaRetornada.getString("RELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA"));
                r.setRELATORIO_I_S_A_AREA_DE_CORTE(tabelaRetornada.getString("RELATORIO_I_S_A_AREA_DE_CORTE"));
                r.setRELATORIO_I_S_A_AREA_DE_ATERRO(tabelaRetornada.getString("RELATORIO_I_S_A_AREA_DE_ATERRO"));
                r.setRELATORIO_I_S_A_TALUDES_FORMADOS(tabelaRetornada.getString("RELATORIO_I_S_A_TALUDES_FORMADOS"));
                r.setRELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE(tabelaRetornada.getInt("RELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE"));
                r.setRELATORIO_S_D_P_E_ESPECIFICAR(tabelaRetornada.getString("RELATORIO_S_D_P_E_ESPECIFICAR"));
                r.setRELATORIO_RECURSOS_HIDRICOS_1(tabelaRetornada.getInt("RELATORIO_RECURSOS_HIDRICOS_1"));
                r.setRELATORIO_R_H_NOME_1(tabelaRetornada.getString("RELATORIO_R_H_NOME_1"));
                r.setRELATORIO_R_H_DISTANCIA_1(tabelaRetornada.getString("RELATORIO_R_H_DISTANCIA_1"));
                r.setRELATORIO_BOTA_FORA1(tabelaRetornada.getInt("RELATORIO_BOTA_FORA1"));
                r.setRELATORIO_BOTA_FORA_UTM_N1(tabelaRetornada.getString("RELATORIO_BOTA_FORA_UTM_N1"));
                r.setRELATORIO_BOTA_FORA_UTM_E1(tabelaRetornada.getString("RELATORIO_BOTA_FORA_UTM_E1"));
                r.setRELATORIO_BOTA_FORA_OPCAO_1(tabelaRetornada.getInt("RELATORIO_BOTA_FORA_OPCAO_1"));
                r.setRELATORIO_BOTA_FORA_OPCAO_2(tabelaRetornada.getInt("RELATORIO_BOTA_FORA_OPCAO_2"));
                r.setRELATORIO_BOTA_FORA_N_DA_UC1(tabelaRetornada.getString("RELATORIO_BOTA_FORA_N_DA_UC1"));
                r.setRELATORIO_BOTA_FORA2(tabelaRetornada.getInt("RELATORIO_BOTA_FORA2"));
                r.setRELATORIO_BOTA_FORA_UTM_N2(tabelaRetornada.getString("RELATORIO_BOTA_FORA_UTM_N2"));
                r.setRELATORIO_BOTA_FORA_UTM_E2(tabelaRetornada.getString("RELATORIO_BOTA_FORA_UTM_E2"));
                r.setRELATORIO_BOTA_FORA_AREA_EMPRESTIMO(tabelaRetornada.getString("RELATORIO_BOTA_FORA_AREA_EMPRESTIMO"));
                r.setRELATORIO_BOTA_FORA_OPCAO_3(tabelaRetornada.getInt("RELATORIO_BOTA_FORA_OPCAO_3"));
                r.setRELATORIO_BOTA_FORA_OPCAO_4(tabelaRetornada.getInt("RELATORIO_BOTA_FORA_OPCAO_4"));
                r.setRELATORIO_BOTA_FORA_N_DA_UC2(tabelaRetornada.getString("RELATORIO_BOTA_FORA_N_DA_UC2"));                
                r.setRELATORIO_RECURSOS_HIDRICOS_2(tabelaRetornada.getInt("RELATORIO_RECURSOS_HIDRICOS_2"));
                r.setRELATORIO_R_H_NOME_2(tabelaRetornada.getString("RELATORIO_R_H_NOME_2"));
                r.setRELATORIO_R_H_DISTANCIA_2(tabelaRetornada.getString("RELATORIO_R_H_DISTANCIA_2"));
                r.setRELATORIO_COMPLEMENTACAO_DO_SERVICO_TERRAPLANAGEM(tabelaRetornada.getInt("RELATORIO_COMPLEMENTACAO_DO_SERVICO_TERRAPLANAGEM"));
                r.setRELATORIO_C_S_T_UTM_N(tabelaRetornada.getString("RELATORIO_C_S_T_UTM_N"));
                r.setRELATORIO_C_S_T_UTM_E(tabelaRetornada.getString("RELATORIO_C_S_T_UTM_E"));
                r.setRELATORIO_C_S_T_AREA_EMPRESTIMO(tabelaRetornada.getString("RELATORIO_C_S_T_AREA_EMPRESTIMO"));
                r.setRELATORIO_C_S_T_OPCAO_1(tabelaRetornada.getInt("RELATORIO_C_S_T_OPCAO_1"));
                r.setRELATORIO_RECURSOS_HIDRICOS_3(tabelaRetornada.getInt("RELATORIO_RECURSOS_HIDRICOS_3"));
                r.setRELATORIO_R_H_NOME_3(tabelaRetornada.getString("RELATORIO_R_H_NOME_3"));
                r.setRELATORIO_R_H_DISTANCIA_3(tabelaRetornada.getString("RELATORIO_R_H_DISTANCIA_3"));
                r.setRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA(tabelaRetornada.getString("RELATORIO_FONTES_DE_ABASTECIMENTO_AGUA"));
                r.setRELATORIO_F_A_A_EMPRESA(tabelaRetornada.getString("RELATORIO_F_A_A_EMPRESA"));
                r.setRELATORIO_F_A_A_POCO_TIPO(tabelaRetornada.getString("RELATORIO_F_A_A_POCO_TIPO"));
                r.setRELATORIO_F_A_A_POCO_QTD(tabelaRetornada.getString("RELATORIO_F_A_A_POCO_QTD"));
                r.setRELATORIO_F_A_A_NUMERO_LICENCA(tabelaRetornada.getString("RELATORIO_F_A_A_NUMERO_LICENCA"));
                r.setRELATORIO_F_A_A_CURSO_DAGUA_NOME(tabelaRetornada.getString("RELATORIO_F_A_A_CURSO_DAGUA_NOME"));
                r.setRELATORIO_F_A_A_LAGO_NOME(tabelaRetornada.getString("RELATORIO_F_A_A_LAGO_NOME"));
                r.setRELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR(tabelaRetornada.getString("RELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR"));
                r.setRELATORIO_F_A_A_OUTRAS_ESPECIFICAR(tabelaRetornada.getString("RELATORIO_F_A_A_OUTRAS_ESPECIFICAR"));
                r.setRELATORIO_F_A_A_CONSUMO_DE_AGUA(tabelaRetornada.getString("RELATORIO_F_A_A_CONSUMO_DE_AGUA"));
                r.setRELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H(tabelaRetornada.getString("RELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H"));
                r.setRELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O(tabelaRetornada.getString("RELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O"));
                r.setRELATORIO_FONTES_DE_GERACAO_EFLUENTES(tabelaRetornada.getString("RELATORIO_FONTES_DE_GERACAO_EFLUENTES"));
                r.setRELATORIO_MOVIMENTACAO_DE_TERRA(tabelaRetornada.getInt("RELATORIO_MOVIMENTACAO_DE_TERRA"));
                r.setRELATORIO_F_G_E_OUTRO_ESPECIFICAR_1(tabelaRetornada.getString("RELATORIO_F_G_E_OUTRO_ESPECIFICAR_1"));
                r.setRELATORIO_F_G_E_OUTRO_ESPECIFICAR_2(tabelaRetornada.getString("RELATORIO_F_G_E_OUTRO_ESPECIFICAR_2"));
                r.setRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO(tabelaRetornada.getString("RELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO"));
                r.setRELATORIO_M_S_T_ESPECIFICAR(tabelaRetornada.getString("RELATORIO_M_S_T_ESPECIFICAR"));
                r.setRELATORIO_M_S_T_CAIXA_DE_GORDURA(tabelaRetornada.getString("RELATORIO_M_S_T_CAIXA_DE_GORDURA"));
                r.setRELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR(tabelaRetornada.getString("RELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR"));
                r.setRELATORIO_GERENCIAMENTO_DE_RESIDUOS(tabelaRetornada.getString("RELATORIO_GERENCIAMENTO_DE_RESIDUOS"));
                r.setRELATORIO_G_R_ESPECIFICAR_1(tabelaRetornada.getString("RELATORIO_G_R_ESPECIFICAR_1"));
                r.setRELATORIO_G_R_ESPECIFICAR_2(tabelaRetornada.getString("RELATORIO_G_R_ESPECIFICAR_2"));
                r.setRELATORIO_G_R_ESPECIFICAR_3(tabelaRetornada.getString("RELATORIO_G_R_ESPECIFICAR_3"));
                r.setRELATORIO_G_R_NOME_1(tabelaRetornada.getString("RELATORIO_G_R_NOME_1"));
                r.setRELATORIO_G_R_NOME_2(tabelaRetornada.getString("RELATORIO_G_R_NOME_2"));
                r.setRELATORIO_G_R_NOME_3(tabelaRetornada.getString("RELATORIO_G_R_NOME_3"));
                r.setRELATORIO_G_R_NOME_1(tabelaRetornada.getString("RELATORIO_G_R_NOME_1"));
                r.setRELATORIO_G_R_NOME_2(tabelaRetornada.getString("RELATORIO_G_R_NOME_2"));
                r.setRELATORIO_G_R_NOME_3(tabelaRetornada.getString("RELATORIO_G_R_NOME_3"));
                r.setRELATORIO_G_R_NUMERO_LICENCA_1(tabelaRetornada.getString("RELATORIO_G_R_NUMERO_LICENCA_1"));
                r.setRELATORIO_G_R_NUMERO_LICENCA_2(tabelaRetornada.getString("RELATORIO_G_R_NUMERO_LICENCA_2"));
                r.setRELATORIO_G_R_NUMERO_LICENCA_3(tabelaRetornada.getString("RELATORIO_G_R_NUMERO_LICENCA_3"));
                r.setRELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA(tabelaRetornada.getString("RELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA"));
                r.setRELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES(tabelaRetornada.getString("RELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES"));
                r.setRELATORIO_EMISSOES_ATMOSFERICAS(tabelaRetornada.getString("RELATORIO_EMISSOES_ATMOSFERICAS"));
                r.setRELATORIO_E_A_ESPECIFICAR(tabelaRetornada.getString("RELATORIO_E_A_ESPECIFICAR"));
                r.setRELATORIO_ROTEIRO_DE_ACESSO(tabelaRetornada.getString("RELATORIO_ROTEIRO_DE_ACESSO"));
                r.setRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO(tabelaRetornada.getString("RELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO"));
                r.setRELATORIO_CROQUI_PERGUNTA(tabelaRetornada.getInt("RELATORIO_CROQUI_PERGUNTA"));
                r.setRELATORIO_TEXTO_ANEXO(tabelaRetornada.getString("RELATORIO_TEXTO_ANEXO"));
                r.setRELATORIO_DATA_ATUAL(retornaDateBanco(tabelaRetornada.getDate("RELATORIO_DATA_ATUAL")));
                r.setPROFISSIONAL_ID(tabelaRetornada.getInt("PROFISSIONAL_ID"));
                r.setCATEGORIA_ID(tabelaRetornada.getInt("CATEGORIA_ID"));
                r.setCLIENTE_NOME(tabelaRetornada.getString("CLIENTE_NOME"));
                r.setCLIENTE_BAIRRO(tabelaRetornada.getString("CLIENTE_BAIRRO"));
                r.setCLIENTE_CELULAR(tabelaRetornada.getString("CLIENTE_CELULAR"));
                r.setCLIENTE_MUNICIPIO(tabelaRetornada.getString("CLIENTE_MUNICIPIO"));
                r.setCLIENTE_ENDERECO(tabelaRetornada.getString("CLIENTE_ENDERECO"));
                r.setCLIENTE_NUMERO(tabelaRetornada.getString("CLIENTE_NUMERO"));
                r.setCLIENTE_TELEFONE(tabelaRetornada.getString("CLIENTE_TELEFONE"));
                r.setCLIENTE_CEP(tabelaRetornada.getString("CLIENTE_CEP"));
                r.setCLIENTE_CPF(tabelaRetornada.getString("CLIENTE_CPF"));
                r.setCLIENTE_CNPJ(tabelaRetornada.getString("CLIENTE_CNPJ"));
                r.setCLIENTE_FANTASIA(tabelaRetornada.getString("CLIENTE_FANTASIA"));
                r.setPROFISSIONAL_NOME(tabelaRetornada.getString("PROFISSIONAL_NOME"));
                r.setCATEGORIA_NOME(tabelaRetornada.getString("CATEGORIA_NOME"));

                listaR.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectarBanco();
            return listaR;
        }

    }

    public Calendar retornaDateBanco(Date dataBanco) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataBanco);
        return cal;
    }

}
