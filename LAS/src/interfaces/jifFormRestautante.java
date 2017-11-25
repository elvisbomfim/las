/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bancodedados.ClienteBD;
import bancodedados.ConexaoPDF;
import bancodedados.ProfissionalBD;
import bancodedados.RelatorioPrincipalBD;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelo.tabelas.ModeloTabelaClientes;
import modelo.tabelas.ModeloTabelaProfissionais;
import modelo.tabelas.ModeloTabelaRelatorios;
import modelos.Cliente;
import modelos.LetrasMaiusculas;
import modelos.ManipularImagem;
import modelos.Profissional;
import modelos.Recibo;
import modelos.RelatorioPrincipal;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;

/**
 *
 * @author Jean
 */
public class jifFormRestautante extends javax.swing.JInternalFrame {

    DateField dataAtual = CalendarFactory.createDateField();
    DateField dataISA = CalendarFactory.createDateField();
    /**
     * Strings concatenadas
     */
    String CheckBoxFAA = "";
    String CheckBoxFGE = "";
    String CheckBoxGR = "";

    /**
     * Creates new form jifFormRestaurante
     */
    RelatorioPrincipal restauranteCadastro = new RelatorioPrincipal();
    Cliente clienteTemporario = new Cliente();
    Profissional profissionalTemporario = new Profissional();
    RelatorioPrincipal relatorioExcluir = new RelatorioPrincipal();

    /**
     * Classes de definição de modelos de tabela
     */
    ModeloTabelaRelatorios modeloTabelaRestaurante = new ModeloTabelaRelatorios();
    ModeloTabelaClientes modeloTabelaClientes;
    ModeloTabelaProfissionais modeloTabelaProfissionais;

    /**
     * Objetos temporarios
     */
    RelatorioPrincipalBD conexaoTabelaRelatorio = new RelatorioPrincipalBD();
    ClienteBD conexaoTabelaClientes = new ClienteBD();
    ProfissionalBD conexaoTabelaProfissionais = new ProfissionalBD();

    //PDF Conexao
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Definição de ArrayList's
     */
    ArrayList<RelatorioPrincipal> listaOriginalTemporariaRelatorio = new ArrayList();

    BufferedImage imagem;

    public jifFormRestautante() {
        initComponents();
        conexao = ConexaoPDF.conector();
        Calendar cal = Calendar.getInstance();
        dataAtual.setBaseDate(cal.getTime());
        pnDataAtual.add(dataAtual);
        //Definindo o botão DateField (Data Inicio do Semestre) para seleção de uma data e atribuindo uma ação de mudança à ele.
        dataAtual.setSize((pnDataAtual.getWidth()), (pnDataAtual.getHeight()));
        setFrameIcon(new ImageIcon(this.getClass().getResource("../imagens/icon.png")));
        btCancelarAtualizacao.setVisible(false);

        Calendar calISA = Calendar.getInstance();
        dataISA.setBaseDate(calISA.getTime());
        pnISAData.add(dataISA);
        // Definindo o botão DateField (Data Inicio do Semestre) para seleção de uma data e atribuindo uma ação de mudança à ele.
        dataISA.setSize((pnISAData.getWidth()), (pnISAData.getHeight()));
        tbRelatoriosCadastrados.setModel(modeloTabelaRestaurante);
        buscarRelatoriosTabela();

        tfNomeCliente.setDocument(new LetrasMaiusculas());
        tfNomeFantasia.setDocument(new LetrasMaiusculas());
        tfEndereco.setDocument(new LetrasMaiusculas());
        tfCidade.setDocument(new LetrasMaiusculas());
        tfCpfCnpj.setDocument(new LetrasMaiusculas());
        tfNumero.setDocument(new LetrasMaiusculas());
        tfBairro.setDocument(new LetrasMaiusculas());
        tfTelefone.setDocument(new LetrasMaiusculas());
        tfCep.setDocument(new LetrasMaiusculas());
        tfCoordenadasUtmN.setDocument(new LetrasMaiusculas());
        tfCoordenadasUtmE.setDocument(new LetrasMaiusculas());

        tfInseridoEmAreaOutraEspecificar.setDocument(new LetrasMaiusculas());

        tfAreaDaUcNDocumento.setDocument(new LetrasMaiusculas());
        tfAreaDaUcNome.setDocument(new LetrasMaiusculas());
        tfSuprecaoVegetacaoDocumentoIdaf.setDocument(new LetrasMaiusculas());
        tfCoordenadasUtmN.setDocument(new LetrasMaiusculas());
        tfCoordenadasUtmE.setDocument(new LetrasMaiusculas());
        tfISAPrevisaoVegetacao.setDocument(new LetrasMaiusculas());
        //pnISAData.setDocument(new LetrasMaiusculas());
        tfNEmpregados.setDocument(new LetrasMaiusculas());
        tfFAAConsumoDeAgua.setDocument(new LetrasMaiusculas());
        tfFAANDocumentoOutorgaRh.setDocument(new LetrasMaiusculas());
        tfFAANDocumentoCertidaoDo.setDocument(new LetrasMaiusculas());
        tfFAAEmpresa.setDocument(new LetrasMaiusculas());
        tfFAAPocoTipo.setDocument(new LetrasMaiusculas());
        tfFAAPocoQtd.setDocument(new LetrasMaiusculas());
        tfFAANumeroLicenca.setDocument(new LetrasMaiusculas());
        tfFAACursoDaguaNome.setDocument(new LetrasMaiusculas());
        tfFAALagoNome.setDocument(new LetrasMaiusculas());
        tfFAAAguasCosteirasEspecificar.setDocument(new LetrasMaiusculas());
        tfFAAOutrasEspecificar.setDocument(new LetrasMaiusculas());
        tfFGEEspecificar1.setDocument(new LetrasMaiusculas());
        tfFGEEspecificar2.setDocument(new LetrasMaiusculas());
        tfMSTEspecificar1.setDocument(new LetrasMaiusculas());
        tfMSTEspecificar2.setDocument(new LetrasMaiusculas());
        tfGREspecificar1.setDocument(new LetrasMaiusculas());
        tfGRNome1.setDocument(new LetrasMaiusculas());
        tfGRNumeroLicenca1.setDocument(new LetrasMaiusculas());
        tfGREspecificar2.setDocument(new LetrasMaiusculas());
        tfGRNome2.setDocument(new LetrasMaiusculas());
        tfGRNumeroLicenca2.setDocument(new LetrasMaiusculas());
        tfGRNome3.setDocument(new LetrasMaiusculas());
        tfGRNumeroLicenca3.setDocument(new LetrasMaiusculas());
        tfGREspecificar3.setDocument(new LetrasMaiusculas());
        taRoteiroAcesso.setDocument(new LetrasMaiusculas());
        taTextoAnexo.setDocument(new LetrasMaiusculas());
        //tfDataAtual.setDocument(new LetrasMaiusculas());
        tfProfissional.setDocument(new LetrasMaiusculas());

    }

    public void buscarRelatoriosTabela() {

        modeloTabelaRestaurante.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(1));
        listaOriginalTemporariaRelatorio = modeloTabelaRestaurante.retornaListaRelatorioPrincipal();
        tbRelatoriosCadastrados.updateUI();
    }

    public RelatorioPrincipal preencherDadosCadastroRestaurante() {

        restauranteCadastro.setRELATORIO_AREA_UTIL(tfAreaUtil.getText());

        restauranteCadastro.setCLIENTE_ID(clienteTemporario.getCliente_id());

        if (rbLocalizacaoZonaUrbana.isSelected()) {
            restauranteCadastro.setRELATORIO_LOCALIZACAO(0);
        }
        if (rbLocalizacaoZonaRural.isSelected()) {
            restauranteCadastro.setRELATORIO_LOCALIZACAO(0);
        }
        if (rbInseridoEmAreaIndustrial.isSelected()) {
            restauranteCadastro.setRELATORIO_INSERIDO_EM_AREA(0);
        }
        if (rbInseridoEmAreaResidencial.isSelected()) {
            restauranteCadastro.setRELATORIO_INSERIDO_EM_AREA(1);
        }
        if (rbInseridoEmAreaComercial.isSelected()) {
            restauranteCadastro.setRELATORIO_INSERIDO_EM_AREA(2);
        }
        if (rbInseridoEmAreaMista.isSelected()) {
            restauranteCadastro.setRELATORIO_INSERIDO_EM_AREA(3);
        }
        if (rbInseridoEmAreaOutra.isSelected()) {
            restauranteCadastro.setRELATORIO_INSERIDO_EM_AREA(4);
        }
        restauranteCadastro.setRELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR(tfInseridoEmAreaOutraEspecificar.getText());
        if (rbHaResidenciasSim.isSelected()) {
            restauranteCadastro.setRELATORIO_HA_RESIDENCIAS(1);
        }
        if (rbHaResidenciasNao.isSelected()) {
            restauranteCadastro.setRELATORIO_HA_RESIDENCIAS(0);
        }
        if (rbAreaDaUcSim.isSelected()) {
            restauranteCadastro.setRELATORIO_AREA_DA_UC(1);
        }
        if (rbAreaDaUcNao.isSelected()) {
            restauranteCadastro.setRELATORIO_AREA_DA_UC(0);
        }
        restauranteCadastro.setRELATORIO_AREA_DA_UC_NOME(tfAreaDaUcNome.getText());
        restauranteCadastro.setRELATORIO_AREA_DA_UC_N_DOCUMENTO(tfAreaDaUcNDocumento.getText());
        if (rbSuprecaoVegetacaoSim.isSelected()) {
            restauranteCadastro.setRELATORIO_SUPRECAO_VEGETACAO(1);
        }
        if (rbSuprecaoVegetacaoNao.isSelected()) {
            restauranteCadastro.setRELATORIO_SUPRECAO_VEGETACAO(0);
        }
        restauranteCadastro.setRELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF(tfSuprecaoVegetacaoDocumentoIdaf.getText());
        restauranteCadastro.setRELATORIO_COORDENADAS_UTM_N(tfCoordenadasUtmN.getText());
        restauranteCadastro.setRELATORIO_COORDENADAS_UTM_E(tfCoordenadasUtmN.getText());
        if (rbISAPlanejamento.isSelected()) {
            restauranteCadastro.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(0);
        }
        if (rbISAInstalacao.isSelected()) {
            restauranteCadastro.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(1);
        }
        if (rbISAOperacao.isSelected()) {
            restauranteCadastro.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(2);
        }
        Calendar calISA = Calendar.getInstance();
        calISA.setTime((Date) dataISA.getValue());
        restauranteCadastro.setRELATORIO_I_S_A_DATA(calISA);
        restauranteCadastro.setRELATORIO_I_S_A_PREVISAO_OPERACAO(tfISAPrevisaoVegetacao.getText());
        restauranteCadastro.setRELATORIO_I_S_A_N_EMPREGADOS(tfNEmpregados.getText());
        if (ckbFAARedePublica.isSelected()) {
            CheckBoxFAA += "ckbFAARedePublica-";
        }
        if (ckbFAAPoco.isSelected()) {
            CheckBoxFAA += "ckbFAAPoco-";
        }
        if (ckbFAAReservatorios.isSelected()) {
            CheckBoxFAA += "ckbFAAReservatorios-";
        }
        if (ckbFAACursoDagua.isSelected()) {
            CheckBoxFAA += "ckbFAACursoDagua-";
        }
        if (ckbFAALago.isSelected()) {
            CheckBoxFAA += "ckbFAALago-";
        }
        if (ckbFAACaptacao.isSelected()) {
            CheckBoxFAA += "ckbFAACaptacao-";
        }
        if (ckbFAAAguasCosteiras.isSelected()) {
            CheckBoxFAA += "ckbFAAAguasCosteiras-";
        }
        if (ckbFAANascente.isSelected()) {
            CheckBoxFAA += "ckbFAANascente-";
        }
        if (ckbFAAOutros.isSelected()) {
            CheckBoxFAA += "ckbFAAOutros-";
        }
        if (ckbFAAReutilizacao.isSelected()) {
            CheckBoxFAA += "ckbFAAReutilizacao-";
        }
        if (ckbFAANaoRealiza.isSelected()) {
            CheckBoxFAA += "ckbFAANaoRealiza-";
        }
        if (ckbFAAOutorga.isSelected()) {
            CheckBoxFAA += "ckbFAAOutorga-";
        }
        if (ckbFAACertidao.isSelected()) {
            CheckBoxFAA += "ckbFAACertidao-";
        }
        if (ckbFAAOpcaoLonga.isSelected()) {
            CheckBoxFAA += "ckbFAAOpcaoLonga-";
        }
        if (ckbFAAFederal.isSelected()) {
            CheckBoxFAA += "ckbFAAFederal-";
        }
        if (ckbFAAEstadual.isSelected()) {
            CheckBoxFAA += "ckbFAAEstadual-";
        }
        restauranteCadastro.setRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA(CheckBoxFAA);
        restauranteCadastro.setRELATORIO_F_A_A_EMPRESA(tfFAAEmpresa.getText());
        restauranteCadastro.setRELATORIO_F_A_A_POCO_TIPO(tfFAAPocoTipo.getText());
        restauranteCadastro.setRELATORIO_F_A_A_POCO_QTD(tfFAAPocoQtd.getText());
        restauranteCadastro.setRELATORIO_F_A_A_NUMERO_LICENCA(tfFAANumeroLicenca.getText());
        restauranteCadastro.setRELATORIO_F_A_A_CURSO_DAGUA_NOME(tfFAACursoDaguaNome.getText());
        restauranteCadastro.setRELATORIO_F_A_A_LAGO_NOME(tfFAALagoNome.getText());
        restauranteCadastro.setRELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR(tfFAAAguasCosteirasEspecificar.getText());
        restauranteCadastro.setRELATORIO_F_A_A_OUTRAS_ESPECIFICAR(tfFAAOutrasEspecificar.getText());
        restauranteCadastro.setRELATORIO_F_A_A_CONSUMO_DE_AGUA(tfFAAConsumoDeAgua.getText());
        restauranteCadastro.setRELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H(tfFAANDocumentoOutorgaRh.getText());
        restauranteCadastro.setRELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O(tfFAANDocumentoCertidaoDo.getText());
        if (ckbFGEEfluentesdomésticos.isSelected()) {
            CheckBoxFGE += "ckbFGEEfluentesdomésticos-";
        }
        if (ckbFGENaohageracao1.isSelected()) {
            CheckBoxFGE += "ckbFGENaohageracao1-";
        }
        if (ckbFGERededecoletapublica1.isSelected()) {
            CheckBoxFGE += "ckbFGERededecoletapublica1-";
        }
        if (ckbFGESistemadeFossa1.isSelected()) {
            CheckBoxFGE += "ckbFGESistemadeFossa1-";
        }
        if (ckbFGECorpodagua1.isSelected()) {
            CheckBoxFGE += "ckbFGECorpodagua1-";
        }
        if (ckbFGESumidouro.isSelected()) {
            CheckBoxFGE += "ckbFGESumidouro-";
        }
        if (ckbFGEDeLavagensdePisosEEquipamentos.isSelected()) {
            CheckBoxFGE += "ckbFGEDeLavagensdePisosEEquipamentos-";
        }
        if (ckbFGENaoHaGeracao2.isSelected()) {
            CheckBoxFGE += "ckbFGENaoHaGeracao2-";
        }
        if (ckbFGERededecoletapublica2.isSelected()) {
            CheckBoxFGE += "ckbFGERededecoletapublica2-";
        }
        if (ckbFGESistemadeFossa2.isSelected()) {
            CheckBoxFGE += "ckbFGESistemadeFossa2-";
        }
        if (ckbFGELavagemdevasilhames.isSelected()) {
            CheckBoxFGE += "ckbFGELavagemdevasilhames-";
        }
        if (ckbFGENaoHaGeracao3.isSelected()) {
            CheckBoxFGE += "ckbFGENaoHaGeracao3-";
        }
        if (ckbFGERededecoletapublica3.isSelected()) {
            CheckBoxFGE += "ckbFGERededecoletapublica3-";
        }
        if (ckbFGESistemadeFossa3.isSelected()) {
            CheckBoxFGE += "ckbFGESistemadeFossa3-";
        }
        if (ckbFGEEfluentesAtmosfericos.isSelected()) {
            CheckBoxFGE += "ckbFGEEfluentesAtmosfericos-";
        }
        if (ckbFGENaoPossuiFornoLenha.isSelected()) {
            CheckBoxFGE += "ckbFGENaoPossuiFornoLenha-";
        }
        if (ckbFGESistemaDeLavador.isSelected()) {
            CheckBoxFGE += "ckbFGESistemaDeLavador-";
        }
        if (ckbFGEOutros1.isSelected()) {
            CheckBoxFGE += "ckbFGEOutros1-";
        }
        if (ckbFGERededecoletapublica4.isSelected()) {
            CheckBoxFGE += "ckbFGERededecoletapublica4-";
        }
        if (ckbFGECorpodagua2.isSelected()) {
            CheckBoxFGE += "ckbFGECorpodagua2-";
        }
        if (ckbFGESistemadeFossa4.isSelected()) {
            CheckBoxFGE += "ckbFGESistemadeFossa4-";
        }
        if (ckbFGEOutros2.isSelected()) {
            CheckBoxFGE += "ckbFGEOutros2-";
        }
        restauranteCadastro.setRELATORIO_FONTES_DE_GERACAO_EFLUENTES(CheckBoxFGE);
        restauranteCadastro.setRELATORIO_F_G_E_OUTRO_ESPECIFICAR_1(tfFGEEspecificar1.getText());
        restauranteCadastro.setRELATORIO_F_G_E_OUTRO_ESPECIFICAR_2(tfFGEEspecificar2.getText());
        if (ckbMSTSemestral.isSelected()) {
            restauranteCadastro.setRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO(ckbMSTSemestral.getText());
        }
        if (ckbMSTAnual.isSelected()) {
            restauranteCadastro.setRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO(ckbMSTAnual.getText());
        }
        if (ckbMSTOutroPeriodoManutencao1.isSelected()) {
            restauranteCadastro.setRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO(ckbMSTOutroPeriodoManutencao1.getText());
        }
        if (ckbMSTNaoRealiza.isSelected()) {
            restauranteCadastro.setRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO(ckbMSTNaoRealiza.getText());
        }
        if (ckbMSTSemanal.isSelected()) {
            restauranteCadastro.setRELATORIO_M_S_T_CAIXA_DE_GORDURA(ckbMSTSemanal.getText());
        }
        if (ckbMSTMensal.isSelected()) {
            restauranteCadastro.setRELATORIO_M_S_T_CAIXA_DE_GORDURA(ckbMSTMensal.getText());
        }
        if (ckbMSTOutroPeriodoManutencao2.isSelected()) {
            restauranteCadastro.setRELATORIO_M_S_T_CAIXA_DE_GORDURA(ckbMSTOutroPeriodoManutencao2.getText());
        }
        restauranteCadastro.setRELATORIO_M_S_T_ESPECIFICAR(tfMSTEspecificar1.getText());
        restauranteCadastro.setRELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR(tfMSTEspecificar2.getText());
        if (ckbGRColetaPublicaMunicipal.isSelected()) {
            CheckBoxGR += "ckbGRColetaPublicaMunicipal-";
        }
        if (ckbGRReaproveitamento.isSelected()) {
            CheckBoxGR += "ckbGRReaproveitamento-";
        }
        if (ckbGROutros1.isSelected()) {
            CheckBoxGR += "ckbGROutros1-";
        }
        if (ckbGRVasilhames.isSelected()) {
            CheckBoxGR += "ckbGRVasilhames-";
        }
        if (ckbGRPapelPapelao.isSelected()) {
            CheckBoxGR += "ckbGRPapelPapelao-";
        }
        if (ckbGREmbalagensPlasticas.isSelected()) {
            CheckBoxGR += "ckbGREmbalagensPlasticas-";
        }
        if (ckbGRAcondicionadosemsacos.isSelected()) {
            CheckBoxGR += "ckbGRAcondicionadosemsacos-";
        }
        if (ckbGROutros2.isSelected()) {
            CheckBoxGR += "ckbGROutros2-";
        }
        if (ckbGRColetaPublicaMunicipal2.isSelected()) {
            CheckBoxGR += "ckbGRColetaPublicaMunicipal2-";
        }
        if (ckbGRReaproveitamento2.isSelected()) {
            CheckBoxGR += "ckbGRReaproveitamento2-";
        }
        if (ckbGROutros3.isSelected()) {
            CheckBoxGR += "ckbGROutros3-";
        }
        if (ckbGRLodoSistema.isSelected()) {
            CheckBoxGR += "ckbGRLodoSistema-";
        }
        if (ckbGRNaohaGeracao1.isSelected()) {
            CheckBoxGR += "ckbGRNaohaGeracao1-";
        }
        if (ckbGRResiduosDomesticos.isSelected()) {
            CheckBoxGR += "ckbGRResiduosDomesticos-";
        }
        if (ckbGRNaohaGeracao2.isSelected()) {
            CheckBoxGR += "ckbGRNaohaGeracao2-";
        }
        if (ckbGRColetaPublicaMunicipal3.isSelected()) {
            CheckBoxGR += "ckbGRColetaPublicaMunicipal3-";
        }
        if (ckbGROutra.isSelected()) {
            CheckBoxGR += "ckbGROutra-";
        }
        if (ckbGRResiduosConstrucao.isSelected()) {
            CheckBoxGR += "ckbGRResiduosConstrucao-";
        }
        if (ckbGRNaohaGeracao3.isSelected()) {
            CheckBoxGR += "ckbGRNaohaGeracao3-";
        }
        if (ckbGRToneisBombados.isSelected()) {
            CheckBoxGR += "ckbGRToneisBombados-";
        }
        if (ckbGREmpresaLicenciada.isSelected()) {
            CheckBoxGR += "ckbGREmpresaLicenciada-";
        }
        restauranteCadastro.setRELATORIO_GERENCIAMENTO_DE_RESIDUOS(CheckBoxGR);
        restauranteCadastro.setRELATORIO_G_R_ESPECIFICAR_1(tfGREspecificar1.getText());
        restauranteCadastro.setRELATORIO_G_R_ESPECIFICAR_2(tfGREspecificar2.getText());
        restauranteCadastro.setRELATORIO_G_R_NOME_1(tfGRNome1.getText());
        restauranteCadastro.setRELATORIO_G_R_NOME_2(tfGRNome2.getText());
        restauranteCadastro.setRELATORIO_G_R_NOME_3(tfGRNome3.getText());
        restauranteCadastro.setRELATORIO_G_R_NUMERO_LICENCA_1(tfGRNumeroLicenca1.getText());
        restauranteCadastro.setRELATORIO_G_R_NUMERO_LICENCA_2(tfGRNumeroLicenca2.getText());
        restauranteCadastro.setRELATORIO_G_R_NUMERO_LICENCA_3(tfGRNumeroLicenca3.getText());
        restauranteCadastro.setRELATORIO_G_R_ESPECIFICAR_3(tfGREspecificar3.getText());
        restauranteCadastro.setRELATORIO_ROTEIRO_DE_ACESSO(taRoteiroAcesso.getText());
        //restauranteCadastro.setRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO();
        if (rbCLENadamaisexisteadeclarar.isSelected()) {
            restauranteCadastro.setRELATORIO_CROQUI_PERGUNTA(0);
        }
        if (rbCLEDeclaramosoqueconstaemanexo.isSelected()) {
            restauranteCadastro.setRELATORIO_CROQUI_PERGUNTA(1);
        }
        restauranteCadastro.setRELATORIO_TEXTO_ANEXO(taTextoAnexo.getText());
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) dataAtual.getValue());
        restauranteCadastro.setRELATORIO_DATA_ATUAL(cal);
        restauranteCadastro.setPROFISSIONAL_ID(profissionalTemporario.getProfissional_id());
        restauranteCadastro.setCATEGORIA_ID(1);
        System.out.println(imagem);
        if (imagem != null) {
            try {
                String caminho = "build/classes/imagens/";
                System.out.println(caminho);
                SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                //System.out.println(d.format(new Date()));
                File outputfile = new File(caminho + "image" + d.format(new Date()) + ".jpg");
                restauranteCadastro.setRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO(outputfile.getName());
                System.out.println(outputfile.getName());
                ImageIO.write(imagem, "jpg", outputfile);
                //JOptionPane.showMessageDialog(rootPane, "Imagem enviada com sucesso");
                imagem = null;
            } catch (IOException ex) {
                Logger.getLogger(jifFormRestautante.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            restauranteCadastro.setRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO("sem_imagem.jpg");
            imagem = null;
        }
        return restauranteCadastro;

    }

    public void limparCamposCadastroRestaurante() {

        CheckBoxGR = "";
        CheckBoxFGE = "";
        CheckBoxFAA = "";
        tfAreaUtil.setText("");
        tfNomeCliente.setText("");
        tfNomeFantasia.setText("");
        tfEndereco.setText("");
        tfCidade.setText("");
        tfCpfCnpj.setText("");
        tfNumero.setText("");
        tfBairro.setText("");
        tfTelefone.setText("");
        tfCep.setText("");
        tfCoordenadasUtmN.setText("");
        tfCoordenadasUtmE.setText("");
        rbLocalizacaoZonaUrbana.setSelected(false);
        rbLocalizacaoZonaRural.setSelected(false);
        rbInseridoEmAreaIndustrial.setSelected(false);
        rbInseridoEmAreaResidencial.setSelected(false);
        rbInseridoEmAreaComercial.setSelected(false);
        rbInseridoEmAreaMista.setSelected(false);
        rbInseridoEmAreaOutra.setSelected(false);
        tfInseridoEmAreaOutraEspecificar.setText("");
        jLabel12 = new javax.swing.JLabel();
        rbHaResidenciasSim.setSelected(false);
        rbHaResidenciasNao.setSelected(false);
        rbAreaDaUcSim.setSelected(false);
        tfAreaDaUcNDocumento.setText("");
        tfAreaDaUcNome.setText("");
        rbAreaDaUcNao.setSelected(false);
        rbSuprecaoVegetacaoSim.setSelected(false);
        tfSuprecaoVegetacaoDocumentoIdaf.setText("");
        rbSuprecaoVegetacaoNao.setSelected(false);
        tfCoordenadasUtmN.setText("");
        tfCoordenadasUtmE.setText("");
        rbISAPlanejamento.setSelected(false);
        rbISAInstalacao.setSelected(false);
        rbISAOperacao.setSelected(false);
        tfISAPrevisaoVegetacao.setText("");
        //pnISAData.setText("");
        tfNEmpregados.setText("");
        ckbFAARedePublica.setSelected(false);
        ckbFAAPoco.setSelected(false);
        ckbFAAReservatorios.setSelected(false);
        ckbFAACursoDagua.setSelected(false);
        ckbFAALago.setSelected(false);
        ckbFAACaptacao.setSelected(false);
        ckbFAAAguasCosteiras.setSelected(false);
        ckbFAANascente.setSelected(false);
        ckbFAAOutros.setSelected(false);
        ckbFAAReutilizacao.setSelected(false);
        ckbFAANaoRealiza.setSelected(false);
        tfFAAConsumoDeAgua.setText("");
        ckbFAAOutorga.setSelected(false);
        tfFAANDocumentoOutorgaRh.setText("");
        ckbFAACertidao.setSelected(false);
        ckbFAAFederal.setSelected(false);
        ckbFAAEstadual.setSelected(false);
        tfFAANDocumentoCertidaoDo.setText("");
        ckbFAAOpcaoLonga.setSelected(false);
        tfFAAEmpresa.setText("");
        tfFAAPocoTipo.setText("");
        tfFAAPocoQtd.setText("");
        tfFAANumeroLicenca.setText("");
        tfFAACursoDaguaNome.setText("");
        tfFAALagoNome.setText("");
        tfFAAAguasCosteirasEspecificar.setText("");
        tfFAAOutrasEspecificar.setText("");
        ckbFGERededecoletapublica2.setSelected(false);
        ckbFGESistemadeFossa2.setSelected(false);
        ckbFGEDeLavagensdePisosEEquipamentos.setSelected(false);
        ckbFGENaoHaGeracao2.setSelected(false);
        ckbFGEEfluentesAtmosfericos.setSelected(false);
        ckbFGESistemaDeLavador.setSelected(false);
        ckbFGENaoPossuiFornoLenha.setSelected(false);
        ckbFGEOutros1.setSelected(false);
        tfFGEEspecificar1.setText("");
        tfFGEEspecificar2.setText("");
        ckbFGERededecoletapublica4.setSelected(false);
        ckbFGECorpodagua2.setSelected(false);
        ckbFGESistemadeFossa4.setSelected(false);
        ckbFGEOutros2.setSelected(false);
        ckbFGEEfluentesdomésticos.setSelected(false);
        ckbFGERededecoletapublica1.setSelected(false);
        ckbFGESistemadeFossa1.setSelected(false);
        ckbFGENaohageracao1.setSelected(false);
        ckbFGECorpodagua1.setSelected(false);
        ckbFGESumidouro.setSelected(false);
        ckbFGELavagemdevasilhames.setSelected(false);
        ckbFGERededecoletapublica3.setSelected(false);
        ckbFGESistemadeFossa3.setSelected(false);
        ckbFGENaoHaGeracao3.setSelected(false);
        ckbMSTSemestral.setSelected(false);
        ckbMSTAnual.setSelected(false);
        ckbMSTOutroPeriodoManutencao1.setSelected(false);
        tfMSTEspecificar1.setText("");
        ckbMSTNaoRealiza.setSelected(false);
        ckbMSTSemanal.setSelected(false);
        ckbMSTMensal.setSelected(false);
        ckbMSTOutroPeriodoManutencao2.setSelected(false);
        tfMSTEspecificar2.setText("");
        ckbGRColetaPublicaMunicipal.setSelected(false);
        ckbGRReaproveitamento.setSelected(false);
        ckbGROutros1.setSelected(false);
        tfGREspecificar1.setText("");
        tfGRNome1.setText("");
        tfGRNumeroLicenca1.setText("");
        ckbGREmbalagensPlasticas.setSelected(false);
        ckbGRPapelPapelao.setSelected(false);
        ckbGRVasilhames.setSelected(false);
        ckbGRAcondicionadosemsacos.setSelected(false);
        ckbGROutros2.setSelected(false);
        ckbGRColetaPublicaMunicipal2.setSelected(false);
        ckbGRReaproveitamento2.setSelected(false);
        ckbGROutros3.setSelected(false);
        tfGREspecificar2.setText("");
        ckbGRLodoSistema.setSelected(false);
        tfGRNome2.setText("");
        tfGRNumeroLicenca2.setText("");
        ckbGRNaohaGeracao1.setSelected(false);
        ckbGRResiduosDomesticos.setSelected(false);
        ckbGRColetaPublicaMunicipal3.setSelected(false);
        ckbGROutra.setSelected(false);
        ckbGRNaohaGeracao2.setSelected(false);
        ckbGRResiduosConstrucao.setSelected(false);
        ckbGRToneisBombados.setSelected(false);
        ckbGREmpresaLicenciada.setSelected(false);
        ckbGRNaohaGeracao3.setSelected(false);
        tfGRNome3.setText("");
        tfGRNumeroLicenca3.setText("");
        tfGREspecificar3.setText("");
        taRoteiroAcesso.setText("");
        rbCLENadamaisexisteadeclarar.setSelected(false);
        rbCLEDeclaramosoqueconstaemanexo.setSelected(false);
        taTextoAnexo.setText("");
        //tfDataAtual.setText("");
        tfProfissional.setText("");
        imagem = null;
        lblImagem.setIcon(new ImageIcon(""));

        tpnAbasRestaurante.setSelectedIndex(1); // Mudando para a PRIMEIRA aba

        tpnAbasRestaurante.setTitleAt(0, "Cadastrar Novo Restaurante");
        btFinalizarCadastro.setToolTipText("Cadastrar");
        tpnAbasRestaurante.setEnabledAt(1, true);
        tpnAbasRestaurante.setSelectedIndex(1);
        btCancelarAtualizacao.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpnAbasRestaurante = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnRestaurante = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfAreaUtil = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfNomeCliente = new javax.swing.JTextField();
        tfNomeFantasia = new javax.swing.JTextField();
        tfEndereco = new javax.swing.JTextField();
        tfCidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfCpfCnpj = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfNumero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tfCep = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfTelefone = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        rbLocalizacaoZonaUrbana = new javax.swing.JRadioButton();
        rbLocalizacaoZonaRural = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rbInseridoEmAreaIndustrial = new javax.swing.JRadioButton();
        rbInseridoEmAreaResidencial = new javax.swing.JRadioButton();
        rbInseridoEmAreaComercial = new javax.swing.JRadioButton();
        rbInseridoEmAreaMista = new javax.swing.JRadioButton();
        rbInseridoEmAreaOutra = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        tfInseridoEmAreaOutraEspecificar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rbHaResidenciasSim = new javax.swing.JRadioButton();
        rbHaResidenciasNao = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        rbAreaDaUcSim = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfAreaDaUcNDocumento = new javax.swing.JTextField();
        tfAreaDaUcNome = new javax.swing.JTextField();
        rbAreaDaUcNao = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        rbSuprecaoVegetacaoSim = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        tfSuprecaoVegetacaoDocumentoIdaf = new javax.swing.JTextField();
        rbSuprecaoVegetacaoNao = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        tfCoordenadasUtmN = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tfCoordenadasUtmE = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        rbISAPlanejamento = new javax.swing.JRadioButton();
        rbISAInstalacao = new javax.swing.JRadioButton();
        rbISAOperacao = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tfISAPrevisaoVegetacao = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        tfNEmpregados = new javax.swing.JTextField();
        pnISAData = new com.toedter.calendar.JDayChooser();
        jPanel6 = new javax.swing.JPanel();
        ckbFAARedePublica = new javax.swing.JCheckBox();
        ckbFAAPoco = new javax.swing.JCheckBox();
        ckbFAAReservatorios = new javax.swing.JCheckBox();
        ckbFAACursoDagua = new javax.swing.JCheckBox();
        ckbFAALago = new javax.swing.JCheckBox();
        ckbFAACaptacao = new javax.swing.JCheckBox();
        ckbFAAAguasCosteiras = new javax.swing.JCheckBox();
        ckbFAANascente = new javax.swing.JCheckBox();
        ckbFAAOutros = new javax.swing.JCheckBox();
        ckbFAAReutilizacao = new javax.swing.JCheckBox();
        ckbFAANaoRealiza = new javax.swing.JCheckBox();
        jLabel26 = new javax.swing.JLabel();
        tfFAAConsumoDeAgua = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        ckbFAAOutorga = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        tfFAANDocumentoOutorgaRh = new javax.swing.JTextField();
        ckbFAACertidao = new javax.swing.JCheckBox();
        ckbFAAFederal = new javax.swing.JCheckBox();
        ckbFAAEstadual = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        tfFAANDocumentoCertidaoDo = new javax.swing.JTextField();
        ckbFAAOpcaoLonga = new javax.swing.JCheckBox();
        jLabel58 = new javax.swing.JLabel();
        tfFAAEmpresa = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        tfFAAPocoTipo = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        tfFAAPocoQtd = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        tfFAANumeroLicenca = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        tfFAACursoDaguaNome = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        tfFAALagoNome = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        tfFAAAguasCosteirasEspecificar = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        tfFAAOutrasEspecificar = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        ckbFGERededecoletapublica2 = new javax.swing.JCheckBox();
        ckbFGESistemadeFossa2 = new javax.swing.JCheckBox();
        ckbFGEDeLavagensdePisosEEquipamentos = new javax.swing.JCheckBox();
        ckbFGENaoHaGeracao2 = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        ckbFGEEfluentesAtmosfericos = new javax.swing.JCheckBox();
        ckbFGESistemaDeLavador = new javax.swing.JCheckBox();
        ckbFGENaoPossuiFornoLenha = new javax.swing.JCheckBox();
        ckbFGEOutros1 = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        tfFGEEspecificar1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tfFGEEspecificar2 = new javax.swing.JTextField();
        ckbFGERededecoletapublica4 = new javax.swing.JCheckBox();
        ckbFGECorpodagua2 = new javax.swing.JCheckBox();
        ckbFGESistemadeFossa4 = new javax.swing.JCheckBox();
        ckbFGEOutros2 = new javax.swing.JCheckBox();
        jPanel15 = new javax.swing.JPanel();
        ckbFGEEfluentesdomésticos = new javax.swing.JCheckBox();
        ckbFGERededecoletapublica1 = new javax.swing.JCheckBox();
        ckbFGESistemadeFossa1 = new javax.swing.JCheckBox();
        ckbFGENaohageracao1 = new javax.swing.JCheckBox();
        ckbFGECorpodagua1 = new javax.swing.JCheckBox();
        ckbFGESumidouro = new javax.swing.JCheckBox();
        jPanel16 = new javax.swing.JPanel();
        ckbFGELavagemdevasilhames = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        ckbFGERededecoletapublica3 = new javax.swing.JCheckBox();
        ckbFGESistemadeFossa3 = new javax.swing.JCheckBox();
        ckbFGENaoHaGeracao3 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        ckbMSTSemestral = new javax.swing.JCheckBox();
        ckbMSTAnual = new javax.swing.JCheckBox();
        ckbMSTOutroPeriodoManutencao1 = new javax.swing.JCheckBox();
        ckbMSTEspecificar = new javax.swing.JLabel();
        tfMSTEspecificar1 = new javax.swing.JTextField();
        ckbMSTNaoRealiza = new javax.swing.JCheckBox();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        ckbMSTSemanal = new javax.swing.JCheckBox();
        ckbMSTMensal = new javax.swing.JCheckBox();
        ckbMSTOutroPeriodoManutencao2 = new javax.swing.JCheckBox();
        jLabel37 = new javax.swing.JLabel();
        tfMSTEspecificar2 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        ckbGRColetaPublicaMunicipal = new javax.swing.JCheckBox();
        ckbGRReaproveitamento = new javax.swing.JCheckBox();
        ckbGROutros1 = new javax.swing.JCheckBox();
        jLabel41 = new javax.swing.JLabel();
        tfGREspecificar1 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        tfGRNome1 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        tfGRNumeroLicenca1 = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        ckbGREmbalagensPlasticas = new javax.swing.JCheckBox();
        ckbGRPapelPapelao = new javax.swing.JCheckBox();
        ckbGRVasilhames = new javax.swing.JCheckBox();
        ckbGRAcondicionadosemsacos = new javax.swing.JCheckBox();
        ckbGROutros2 = new javax.swing.JCheckBox();
        ckbGRColetaPublicaMunicipal2 = new javax.swing.JCheckBox();
        ckbGRReaproveitamento2 = new javax.swing.JCheckBox();
        ckbGROutros3 = new javax.swing.JCheckBox();
        jLabel46 = new javax.swing.JLabel();
        tfGREspecificar2 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        ckbGRLodoSistema = new javax.swing.JCheckBox();
        jLabel47 = new javax.swing.JLabel();
        tfGRNome2 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        tfGRNumeroLicenca2 = new javax.swing.JTextField();
        ckbGRNaohaGeracao1 = new javax.swing.JCheckBox();
        jLabel34 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        ckbGRResiduosDomesticos = new javax.swing.JCheckBox();
        jLabel50 = new javax.swing.JLabel();
        ckbGRColetaPublicaMunicipal3 = new javax.swing.JCheckBox();
        ckbGROutra = new javax.swing.JCheckBox();
        tfGREspecificar3 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        ckbGRNaohaGeracao2 = new javax.swing.JCheckBox();
        jPanel21 = new javax.swing.JPanel();
        ckbGRResiduosConstrucao = new javax.swing.JCheckBox();
        ckbGRToneisBombados = new javax.swing.JCheckBox();
        ckbGREmpresaLicenciada = new javax.swing.JCheckBox();
        ckbGRNaohaGeracao3 = new javax.swing.JCheckBox();
        jLabel52 = new javax.swing.JLabel();
        tfGRNome3 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        tfGRNumeroLicenca3 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taRoteiroAcesso = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        rbCLENadamaisexisteadeclarar = new javax.swing.JRadioButton();
        rbCLEDeclaramosoqueconstaemanexo = new javax.swing.JRadioButton();
        jLabel57 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taTextoAnexo = new javax.swing.JTextArea();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        tfProfissional = new javax.swing.JTextField();
        pnDataAtual = new com.toedter.calendar.JDayChooser();
        pnCROQUI = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblImagem = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btCancelarAtualizacao = new javax.swing.JButton();
        btFinalizarCadastro = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        tfPalavraChaveRestaurante = new javax.swing.JTextField();
        ckbCliente = new javax.swing.JCheckBox();
        ckbProfissional = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbRelatoriosCadastrados = new javax.swing.JTable();
        btExcluirProfissional = new javax.swing.JButton();
        btEditarProfissional = new javax.swing.JButton();
        btImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Restaurante");
        setPreferredSize(new java.awt.Dimension(800, 600));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel1.setText("Área útil:");

        tfAreaUtil.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfAreaUtilFocusLost(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFORMAÇÕES DO EMPREENDIMENTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setText("Razão Social/Nome:");

        jLabel3.setText("Nome Fantasia:");

        jLabel4.setText("Endereço:");

        jLabel5.setText("Cidade:");

        tfNomeCliente.setEditable(false);
        tfNomeCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfNomeClienteMouseClicked(evt);
            }
        });

        tfNomeFantasia.setEditable(false);

        tfEndereco.setEditable(false);

        tfCidade.setEditable(false);

        jLabel6.setText("CNPJ/CPF:");

        tfCpfCnpj.setEditable(false);

        jLabel7.setText("N°:");

        tfNumero.setEditable(false);

        jLabel8.setText("Bairro:");

        tfBairro.setEditable(false);

        jLabel18.setText(" CEP:");

        tfCep.setEditable(false);

        jLabel19.setText("Tel:");

        tfTelefone.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfNomeCliente)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfNomeFantasia)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCep, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                            .addComponent(tfEndereco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCpfCnpj))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfBairro, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTelefone)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(tfNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CARACTERÍSTICAS DA ÁREA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel9.setText("Localização:");

        rbLocalizacaoZonaUrbana.setText("Zona Urbana");
        rbLocalizacaoZonaUrbana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbLocalizacaoZonaUrbanaMouseClicked(evt);
            }
        });

        rbLocalizacaoZonaRural.setText("Zona Rural");
        rbLocalizacaoZonaRural.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbLocalizacaoZonaRuralMouseClicked(evt);
            }
        });

        jLabel10.setText("Inserida em área:");

        rbInseridoEmAreaIndustrial.setText("Industrial");
        rbInseridoEmAreaIndustrial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbInseridoEmAreaIndustrialMouseClicked(evt);
            }
        });

        rbInseridoEmAreaResidencial.setText("Residencial");
        rbInseridoEmAreaResidencial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbInseridoEmAreaResidencialMouseClicked(evt);
            }
        });

        rbInseridoEmAreaComercial.setText("Comercial");
        rbInseridoEmAreaComercial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbInseridoEmAreaComercialMouseClicked(evt);
            }
        });

        rbInseridoEmAreaMista.setText("Mista");
        rbInseridoEmAreaMista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbInseridoEmAreaMistaMouseClicked(evt);
            }
        });

        rbInseridoEmAreaOutra.setText("Outra");
        rbInseridoEmAreaOutra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbInseridoEmAreaOutraMouseClicked(evt);
            }
        });

        jLabel11.setText("Especificar:");

        jLabel12.setText("Há residência(s) no entorno (raio de 100m)?");

        rbHaResidenciasSim.setText("Sim");
        rbHaResidenciasSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbHaResidenciasSimMouseClicked(evt);
            }
        });

        rbHaResidenciasNao.setText("Não");
        rbHaResidenciasNao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbHaResidenciasNaoMouseClicked(evt);
            }
        });

        jLabel13.setText("A área está inserida em Unidade de Conservação (UC) ou em sua zona de amortecimento?");

        rbAreaDaUcSim.setText("Sim");
        rbAreaDaUcSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbAreaDaUcSimMouseClicked(evt);
            }
        });

        jLabel14.setText("Nome da unidade de conservação:");

        jLabel15.setText("N° do documento referente à anuência:");

        rbAreaDaUcNao.setText("Não");
        rbAreaDaUcNao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbAreaDaUcNaoMouseClicked(evt);
            }
        });

        jLabel16.setText("Haverá supressão de vegetação?");

        rbSuprecaoVegetacaoSim.setText("Sim");
        rbSuprecaoVegetacaoSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbSuprecaoVegetacaoSimMouseClicked(evt);
            }
        });

        jLabel17.setText("Nº do documento referente à autorização expedida pelo IDAF:");

        rbSuprecaoVegetacaoNao.setText("Não se aplica");
        rbSuprecaoVegetacaoNao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbSuprecaoVegetacaoNaoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(rbLocalizacaoZonaUrbana)
                            .addComponent(rbLocalizacaoZonaRural))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rbInseridoEmAreaOutra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfInseridoEmAreaOutraEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rbInseridoEmAreaIndustrial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbInseridoEmAreaResidencial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbInseridoEmAreaComercial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbInseridoEmAreaMista))
                            .addComponent(jLabel10)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSuprecaoVegetacaoDocumentoIdaf))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbSuprecaoVegetacaoNao)
                            .addComponent(rbSuprecaoVegetacaoSim)
                            .addComponent(rbAreaDaUcNao)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(rbAreaDaUcSim)
                                .addComponent(jLabel12)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(rbHaResidenciasSim)
                                    .addGap(180, 180, 180)
                                    .addComponent(rbHaResidenciasNao))
                                .addComponent(jLabel13)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel14))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfAreaDaUcNDocumento)
                                        .addComponent(tfAreaDaUcNome))))
                            .addComponent(jLabel16))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLocalizacaoZonaUrbana)
                    .addComponent(rbInseridoEmAreaIndustrial)
                    .addComponent(rbInseridoEmAreaResidencial)
                    .addComponent(rbInseridoEmAreaComercial)
                    .addComponent(rbInseridoEmAreaMista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLocalizacaoZonaRural)
                    .addComponent(rbInseridoEmAreaOutra)
                    .addComponent(jLabel11)
                    .addComponent(tfInseridoEmAreaOutraEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbHaResidenciasSim)
                    .addComponent(rbHaResidenciasNao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbAreaDaUcSim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfAreaDaUcNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tfAreaDaUcNDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbAreaDaUcNao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSuprecaoVegetacaoSim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tfSuprecaoVegetacaoDocumentoIdaf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSuprecaoVegetacaoNao))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "COORDENADAS UTM", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel20.setText("UTM (N):");

        tfCoordenadasUtmN.setEditable(false);

        jLabel21.setText("UTM (E):");

        tfCoordenadasUtmE.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCoordenadasUtmN, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCoordenadasUtmE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(tfCoordenadasUtmN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(tfCoordenadasUtmE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFORMAÇÕES SOBRE A ATIVIDADE ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel24.setText("Fase do empreendimento:");

        rbISAPlanejamento.setText("Planejamento");
        rbISAPlanejamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbISAPlanejamentoMouseClicked(evt);
            }
        });

        rbISAInstalacao.setText("Instalação");
        rbISAInstalacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbISAInstalacaoMouseClicked(evt);
            }
        });

        rbISAOperacao.setText("Operação");
        rbISAOperacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbISAOperacaoMouseClicked(evt);
            }
        });

        jLabel22.setText("Previsão de início da operação:");

        jLabel23.setText("Data de início da atividade:");

        jLabel25.setText("N° de funcionários:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(rbISAPlanejamento)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbISAInstalacao)
                                    .addComponent(rbISAOperacao))
                                .addGap(109, 109, 109)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfNEmpregados, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfISAPrevisaoVegetacao)
                            .addComponent(pnISAData, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbISAPlanejamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbISAInstalacao)
                    .addComponent(jLabel22)
                    .addComponent(tfISAPrevisaoVegetacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbISAOperacao)
                        .addComponent(jLabel23))
                    .addComponent(pnISAData, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(tfNEmpregados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FONTES DE ABASTECIMENTO DE ÁGUA ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ckbFAARedePublica.setText("Rede Pública.");

        ckbFAAPoco.setText("Poço(s).");

        ckbFAAReservatorios.setText("Reservatórios, represas ou barragens.");

        ckbFAACursoDagua.setText("Curso d’ água (rios, córrego e riachos).");

        ckbFAALago.setText("Lago/lagoa");

        ckbFAACaptacao.setText("Captação de água pluvial.");

        ckbFAAAguasCosteiras.setText("Águas costeiras.");

        ckbFAANascente.setText("Nascente.");

        ckbFAAOutros.setText("Outros.");

        ckbFAAReutilizacao.setText("Reutilização de água do processo produtivo.");

        ckbFAANaoRealiza.setText("Não realiza.");

        jLabel26.setText("Consumo de água:");

        jLabel27.setText("m3/dia.");

        ckbFAAOutorga.setText("Outorga para uso de recurso hídrico.");

        jLabel28.setText("N° do documento:");

        ckbFAACertidao.setText("Certidão de dispensa de outorga.");

        ckbFAAFederal.setText("Federal.");

        ckbFAAEstadual.setText("Estadual.");

        jLabel29.setText("N° do documento:");

        ckbFAAOpcaoLonga.setText("<html>Não passível atualmente de outorga ou dispensa: realiza captação de águas subterrâneas, pluviais, não utiliza recurso hídrico<br> diretamente para abastecimento próprio e não realiza lançamento de efluentes em corpos de água (serviços disponibilizados pela<br> concessionária de água e esgoto).</html>");

        jLabel58.setText("Informar nome da Concessionária / Empresa:");

        tfFAAEmpresa.setText("CESAN");

        jLabel61.setText("Informar: Tipo:");

        jLabel62.setText("Quantidade:");

        jLabel59.setText("Nº da licença/autorização:");

        jLabel60.setText("Nome:");

        jLabel63.setText("Nome:");

        jLabel64.setText("Especificar:");

        jLabel65.setText("Especificar:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbFAAOpcaoLonga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbFAAOutorga)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAANDocumentoCertidaoDo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(ckbFAARedePublica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel58))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(ckbFAAReservatorios)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel59))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(ckbFAALago)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAALagoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ckbFAACaptacao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(ckbFAAAguasCosteiras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAAAguasCosteirasEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ckbFAANascente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(ckbFAAOutros)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAAOutrasEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ckbFAANaoRealiza, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAAConsumoDeAgua))
                            .addComponent(ckbFAACertidao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAANDocumentoOutorgaRh))
                            .addComponent(ckbFAAReutilizacao, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ckbFAAFederal)
                                    .addComponent(ckbFAAEstadual)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAAEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAANumeroLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(ckbFAAPoco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfFAAPocoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfFAAPocoQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(ckbFAACursoDagua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfFAACursoDaguaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbFAARedePublica)
                    .addComponent(jLabel58)
                    .addComponent(tfFAAEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbFAAPoco)
                    .addComponent(jLabel61)
                    .addComponent(tfFAAPocoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(tfFAAPocoQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbFAAReservatorios)
                    .addComponent(jLabel59)
                    .addComponent(tfFAANumeroLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbFAACursoDagua)
                    .addComponent(jLabel60)
                    .addComponent(tfFAACursoDaguaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbFAALago)
                    .addComponent(jLabel63)
                    .addComponent(tfFAALagoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbFAACaptacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbFAAAguasCosteiras)
                    .addComponent(jLabel64)
                    .addComponent(tfFAAAguasCosteirasEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbFAANascente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbFAAOutros)
                    .addComponent(jLabel65)
                    .addComponent(tfFAAOutrasEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbFAAReutilizacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbFAANaoRealiza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(tfFAAConsumoDeAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbFAAOutorga)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(tfFAANDocumentoOutorgaRh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbFAACertidao))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(ckbFAAFederal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbFAAEstadual)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(tfFAANDocumentoCertidaoDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbFAAOpcaoLonga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FONTES DE GERAÇÃO DE EFLUENTES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ckbFGERededecoletapublica2.setText("Rede de coleta pública.");

        ckbFGESistemadeFossa2.setText("<html>Sistema de Fossa<br>Séptica/ Filtro Anaeróbio.</html>");

        ckbFGEDeLavagensdePisosEEquipamentos.setText("<html>De lavagem de<br>pisos e equipamentos.</html>");

        ckbFGENaoHaGeracao2.setText("Não há geração.");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbFGENaoHaGeracao2)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(ckbFGEDeLavagensdePisosEEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbFGESistemadeFossa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbFGERededecoletapublica2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ckbFGERededecoletapublica2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbFGESistemadeFossa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(ckbFGEDeLavagensdePisosEEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(ckbFGENaoHaGeracao2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ckbFGEEfluentesAtmosfericos.setText("<html>Efluentes atmosféricos<br>(provenientes de fornos à<br>lenha/chaminés).</html>");

        ckbFGESistemaDeLavador.setText("<html>Sistema de lavador de<br>gases/retenção de<br>material particulado.</html>");

        ckbFGENaoPossuiFornoLenha.setText("Não possui forno a lenha.");

        ckbFGEOutros1.setText("Outro.");

        jLabel31.setText("Especificar:");

        jLabel32.setText("Especificar:");

        ckbFGERededecoletapublica4.setText("Rede de coleta pública.");

        ckbFGECorpodagua2.setText("Corpo d’água.");

        ckbFGESistemadeFossa4.setText("<html>Sistema de Fossa<br>Séptica/ Filtro Anaeróbio.</html>");

        ckbFGEOutros2.setText("Outro.");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbFGEEfluentesAtmosfericos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbFGENaoPossuiFornoLenha))
                .addGap(68, 68, 68)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfFGEEspecificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ckbFGEOutros1)
                    .addComponent(ckbFGESistemaDeLavador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfFGEEspecificar2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ckbFGEOutros2)
                    .addComponent(ckbFGESistemadeFossa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbFGECorpodagua2)
                    .addComponent(ckbFGERededecoletapublica4))
                .addGap(100, 100, 100))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(ckbFGERededecoletapublica4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbFGECorpodagua2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbFGESistemadeFossa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbFGEOutros2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(tfFGEEspecificar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckbFGEEfluentesAtmosfericos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbFGESistemaDeLavador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckbFGENaoPossuiFornoLenha)
                            .addComponent(ckbFGEOutros1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(tfFGEEspecificar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ckbFGEEfluentesdomésticos.setText("<html>Efluentes domésticos<br>(esgoto sanitário).</html>");

        ckbFGERededecoletapublica1.setText("Rede de coleta pública.");

        ckbFGESistemadeFossa1.setText("<html>Sistema de Fossa<br>Séptica/ Filtro Anaeróbio.</html>");

        ckbFGENaohageracao1.setText(" Não há geração.");

        ckbFGECorpodagua1.setText("Corpo d’água.");

        ckbFGESumidouro.setText("Sumidouro.");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbFGEEfluentesdomésticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbFGENaohageracao1))
                .addGap(83, 83, 83)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbFGERededecoletapublica1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(ckbFGESistemadeFossa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbFGESumidouro)
                            .addComponent(ckbFGECorpodagua1))))
                .addGap(77, 77, 77))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(ckbFGEEfluentesdomésticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ckbFGENaohageracao1))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(ckbFGERededecoletapublica1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckbFGESistemadeFossa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbFGECorpodagua1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbFGESumidouro)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ckbFGELavagemdevasilhames.setText("<html>Lavagem de<br>vasilhames e afins<br>(efluentes gerados em<br>cozinhas etc.).</html>");

        jLabel30.setText("Caixa de gordura.");

        ckbFGERededecoletapublica3.setText("<html>Rede de coleta<br>pública.</html>");

        ckbFGESistemadeFossa3.setText("<html>Sistema de Fossa<br>Séptica/ Filtro<br>Anaeróbio.</html>");

        ckbFGENaoHaGeracao3.setText("Não há geração.");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(ckbFGELavagemdevasilhames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(jLabel30))
                    .addComponent(ckbFGENaoHaGeracao3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbFGERededecoletapublica3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbFGESistemadeFossa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ckbFGERededecoletapublica3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbFGESistemadeFossa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ckbFGELavagemdevasilhames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbFGENaoHaGeracao3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MANUTENÇÃO DO SISTEMA DE TRATAMENTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel33.setText("Sistema de Fossa Séptica/ Filtro Anaeróbio.");

        ckbMSTSemestral.setText("Semestral.");
        ckbMSTSemestral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ckbMSTSemestralMouseClicked(evt);
            }
        });

        ckbMSTAnual.setText("Anual.");
        ckbMSTAnual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ckbMSTAnualMouseClicked(evt);
            }
        });

        ckbMSTOutroPeriodoManutencao1.setText("Outro período de manutenção.");
        ckbMSTOutroPeriodoManutencao1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ckbMSTOutroPeriodoManutencao1MouseClicked(evt);
            }
        });

        ckbMSTEspecificar.setText(" Especificar:");

        ckbMSTNaoRealiza.setText("Não realiza.");
        ckbMSTNaoRealiza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ckbMSTNaoRealizaMouseClicked(evt);
            }
        });

        jLabel35.setText("(Em caso de lançamento em rede pública de coleta).");

        jLabel36.setText("Caixa de gordura:");

        ckbMSTSemanal.setText(" Semanal.");
        ckbMSTSemanal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ckbMSTSemanalMouseClicked(evt);
            }
        });

        ckbMSTMensal.setText("Mensal.");
        ckbMSTMensal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ckbMSTMensalMouseClicked(evt);
            }
        });

        ckbMSTOutroPeriodoManutencao2.setText("Outro período de manutenção.");
        ckbMSTOutroPeriodoManutencao2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ckbMSTOutroPeriodoManutencao2MouseClicked(evt);
            }
        });

        jLabel37.setText("Especificar:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ckbMSTSemanal)
                        .addGap(18, 18, 18)
                        .addComponent(ckbMSTMensal)
                        .addGap(18, 18, 18)
                        .addComponent(ckbMSTOutroPeriodoManutencao2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMSTEspecificar2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ckbMSTNaoRealiza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ckbMSTSemestral)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbMSTAnual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbMSTOutroPeriodoManutencao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbMSTEspecificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMSTEspecificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel33)
                    .addComponent(jLabel36))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbMSTSemestral)
                    .addComponent(ckbMSTAnual)
                    .addComponent(ckbMSTOutroPeriodoManutencao1)
                    .addComponent(ckbMSTEspecificar)
                    .addComponent(tfMSTEspecificar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbMSTNaoRealiza)
                    .addComponent(jLabel35))
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbMSTSemanal)
                    .addComponent(ckbMSTMensal)
                    .addComponent(ckbMSTOutroPeriodoManutencao2)
                    .addComponent(jLabel37)
                    .addComponent(tfMSTEspecificar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GERENCIAMENTO DE RESÍDUOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel40.setText("alimentos.");

        jLabel38.setText("Resíduos orgânicos");

        jLabel39.setText("provenientes de sobras de");

        ckbGRColetaPublicaMunicipal.setText("Coleta Pública Municipal.");

        ckbGRReaproveitamento.setText("Reaproveitamento.");

        ckbGROutros1.setText("Outros.");

        jLabel41.setText("Especificar:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40))
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(ckbGROutros1)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfGREspecificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ckbGRReaproveitamento)
                    .addComponent(ckbGRColetaPublicaMunicipal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(ckbGRColetaPublicaMunicipal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(ckbGRReaproveitamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(ckbGROutros1)
                    .addComponent(jLabel41)
                    .addComponent(tfGREspecificar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel43.setText("<html>Empresa(s) licenciada(s) para coleta, transporte e destinação<br>final:</html>");

        jLabel42.setText("<html>Escuma e lodo gerados na<br>caixa de gordura</html>");

        jLabel44.setText("Nome:");

        jLabel45.setText("Nº(s) da Licença(s) de Operação:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfGRNumeroLicenca1))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfGRNome1))
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(tfGRNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(tfGRNumeroLicenca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ckbGREmbalagensPlasticas.setText("Embalagens plásticas.");

        ckbGRPapelPapelao.setText("<html>Papel/papelão (rótulos<br>e embalagens, etc.).</html>");

        ckbGRVasilhames.setText("Vasilhames quebrados.");

        ckbGRAcondicionadosemsacos.setText("<html>Acondicionados em sacos<br>plásticos, tonéis,<br>bombonas, tambores<br>e similares, em local<br>próprio e coberto.</html>");

        ckbGROutros2.setText("Outros.");

        ckbGRColetaPublicaMunicipal2.setText("Coleta Pública Municipal.");

        ckbGRReaproveitamento2.setText("Reaproveitamento.");

        ckbGROutros3.setText("Outros.");

        jLabel46.setText("Especificar:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbGRVasilhames)
                    .addComponent(ckbGRPapelPapelao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbGREmbalagensPlasticas))
                .addGap(34, 34, 34)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbGRAcondicionadosemsacos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbGROutros2))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbGRColetaPublicaMunicipal2)
                    .addComponent(ckbGRReaproveitamento2)
                    .addComponent(ckbGROutros3)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfGREspecificar2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(ckbGRColetaPublicaMunicipal2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbGRReaproveitamento2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbGROutros3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(tfGREspecificar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(ckbGRAcondicionadosemsacos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbGROutros2))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(ckbGRVasilhames)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbGRPapelPapelao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbGREmbalagensPlasticas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ckbGRLodoSistema.setText("<html>Lodo do sistema<br>de tratamento de efluentes<br>domésticos (fossa/filtro).</html>");

        jLabel47.setText("<html>Empresa(s) licenciada(s) para coleta, transporte e destinação<br>final:</html>");

        jLabel48.setText("Nome(s):");

        jLabel49.setText("Nº(s) da Licença(s) de Operação:");

        ckbGRNaohaGeracao1.setText("Não há geração.");

        jLabel34.setText("(Em caso de lançamento em rede pública de coleta).");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbGRLodoSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbGRNaohaGeracao1)
                    .addComponent(jLabel34))
                .addGap(34, 34, 34)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfGRNome2))
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfGRNumeroLicenca2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(tfGRNome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(tfGRNumeroLicenca2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(ckbGRLodoSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbGRNaohaGeracao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ckbGRResiduosDomesticos.setText("<html>Resíduos domésticos,<br>de varrição e<br>administrativos.</html>");

        jLabel50.setText("Destinação final:");

        ckbGRColetaPublicaMunicipal3.setText("Coleta pública.");

        ckbGROutra.setText("Outra.");

        jLabel51.setText("<html>* É vedada a queima a céu aberto de material potencialmente poluidor conforme Decreto Estadual nº 2299-N de<br>09/06/1986;</html>");

        ckbGRNaohaGeracao2.setText("Não há geração.");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbGRResiduosDomesticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbGRNaohaGeracao2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(ckbGRColetaPublicaMunicipal3)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(ckbGROutra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfGREspecificar3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbGRColetaPublicaMunicipal3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckbGROutra)
                            .addComponent(tfGREspecificar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(ckbGRResiduosDomesticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbGRNaohaGeracao2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ckbGRResiduosConstrucao.setText("<html>Resíduos de<br>construção civil<br>(reformas e reparações).</html>");

        ckbGRToneisBombados.setText("<html>Tonéis, bombonas,<br>big bags e similares,<br>em local coberto.</html>");

        ckbGREmpresaLicenciada.setText("<html>Empresa licenciada para coleta,<br>transporte e destinação final:</html>");

        ckbGRNaohaGeracao3.setText("Não há geração.");

        jLabel52.setText("Nome:");

        jLabel53.setText("Nº da Licença de Operação:");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(ckbGRResiduosConstrucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(ckbGRToneisBombados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ckbGREmpresaLicenciada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(ckbGRNaohaGeracao3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfGRNome3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfGRNumeroLicenca3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbGRResiduosConstrucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbGRToneisBombados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbGREmpresaLicenciada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(tfGRNome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbGRNaohaGeracao3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(tfGRNumeroLicenca3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ROTEIRO DE ACESSO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel54.setText("Principais vias de acesso e pontos de referência.");

        taRoteiroAcesso.setColumns(20);
        taRoteiroAcesso.setLineWrap(true);
        taRoteiroAcesso.setRows(5);
        jScrollPane2.setViewportView(taRoteiroAcesso);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel54)
                .addGap(238, 238, 238))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel56.setText("Informamos ainda que:");

        rbCLENadamaisexisteadeclarar.setText("Nada mais existe a declarar.");
        rbCLENadamaisexisteadeclarar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbCLENadamaisexisteadeclararMouseClicked(evt);
            }
        });

        rbCLEDeclaramosoqueconstaemanexo.setText("Declaramos o que consta em anexo.");
        rbCLEDeclaramosoqueconstaemanexo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbCLEDeclaramosoqueconstaemanexoMouseClicked(evt);
            }
        });

        jLabel57.setText("Anexo*");

        taTextoAnexo.setColumns(20);
        taTextoAnexo.setLineWrap(true);
        taTextoAnexo.setRows(5);
        jScrollPane3.setViewportView(taTextoAnexo);

        jLabel67.setText("Data:");

        jLabel68.setText("Responsável Técnico:");

        tfProfissional.setEditable(false);
        tfProfissional.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfProfissionalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbCLEDeclaramosoqueconstaemanexo)
                                    .addComponent(jLabel56)
                                    .addComponent(rbCLENadamaisexisteadeclarar)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(333, 333, 333)
                                .addComponent(jLabel57)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68)
                                    .addComponent(jLabel67))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfProfissional, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(pnDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbCLENadamaisexisteadeclarar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbCLEDeclaramosoqueconstaemanexo)
                .addGap(54, 54, 54)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel67)
                    .addComponent(pnDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(tfProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnCROQUI.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CROQUI DE LOCALIZAÇÃO DO EMPREENDIMENTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pnCROQUI.setAutoscrolls(true);

        jLabel55.setText("<html>Indicar, em um raio de 100 (cem) metros, a situação de ocupação da área (habitação, serviço público, arruamento, atividades produtivas locais<br>e outros) e recursos hídricos e florestais.</html>");

        jButton1.setText("Selecionar Imagem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCROQUILayout = new javax.swing.GroupLayout(pnCROQUI);
        pnCROQUI.setLayout(pnCROQUILayout);
        pnCROQUILayout.setHorizontalGroup(
            pnCROQUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCROQUILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCROQUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCROQUILayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(pnCROQUILayout.createSequentialGroup()
                        .addGroup(pnCROQUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImagem))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnCROQUILayout.setVerticalGroup(
            pnCROQUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCROQUILayout.createSequentialGroup()
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        btCancelarAtualizacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_edit-not-validated_85308.png"))); // NOI18N
        btCancelarAtualizacao.setToolTipText("Cancelar");
        btCancelarAtualizacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelarAtualizacao.setPreferredSize(new java.awt.Dimension(150, 150));
        btCancelarAtualizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarAtualizacaoActionPerformed(evt);
            }
        });

        btFinalizarCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/complete-file.png"))); // NOI18N
        btFinalizarCadastro.setToolTipText("Cadastrar");
        btFinalizarCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFinalizarCadastro.setPreferredSize(new java.awt.Dimension(150, 150));
        btFinalizarCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarCadastroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btCancelarAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 421, Short.MAX_VALUE)
                .addComponent(btFinalizarCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btFinalizarCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancelarAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnRestauranteLayout = new javax.swing.GroupLayout(pnRestaurante);
        pnRestaurante.setLayout(pnRestauranteLayout);
        pnRestauranteLayout.setHorizontalGroup(
            pnRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRestauranteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnRestauranteLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAreaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnCROQUI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 170, Short.MAX_VALUE))
        );
        pnRestauranteLayout.setVerticalGroup(
            pnRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnRestauranteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfAreaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnCROQUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(pnRestaurante);

        tpnAbasRestaurante.addTab("Cadastrar Novos Restaurantes", jScrollPane1);

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro de Busca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel86.setText("Palavra-chave:");

        tfPalavraChaveRestaurante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPalavraChaveRestauranteKeyReleased(evt);
            }
        });

        ckbCliente.setText("CLIENTE");

        ckbProfissional.setText("PROFISSIONAL");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPalavraChaveRestaurante)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap(265, Short.MAX_VALUE)
                .addComponent(ckbCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbProfissional)
                .addGap(314, 314, 314))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(tfPalavraChaveRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbCliente)
                    .addComponent(ckbProfissional))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tbRelatoriosCadastrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tbRelatoriosCadastrados);

        btExcluirProfissional.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete-file.png"))); // NOI18N
        btExcluirProfissional.setToolTipText("Excluir");
        btExcluirProfissional.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluirProfissional.setPreferredSize(new java.awt.Dimension(150, 150));
        btExcluirProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirProfissionalActionPerformed(evt);
            }
        });

        btEditarProfissional.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit-file.png"))); // NOI18N
        btEditarProfissional.setToolTipText("Editar");
        btEditarProfissional.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditarProfissional.setPreferredSize(new java.awt.Dimension(150, 150));
        btEditarProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarProfissionalActionPerformed(evt);
            }
        });

        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/printer-128.png"))); // NOI18N
        btImprimir.setToolTipText("Editar");
        btImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btImprimir.setPreferredSize(new java.awt.Dimension(150, 150));
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(btExcluirProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(btEditarProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btEditarProfissional, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btExcluirProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnAbasRestaurante.addTab("Gerenciar Restaurantes", jPanel23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 784, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tpnAbasRestaurante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tpnAbasRestaurante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
        );

        setBounds(0, 0, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void tfPalavraChaveRestauranteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPalavraChaveRestauranteKeyReleased
        // TODO add your handling code here:
        if (tfPalavraChaveRestaurante.getText().isEmpty()) {
            modeloTabelaRestaurante.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(1));
        } else {
            modeloTabelaRestaurante.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(1));
            listaOriginalTemporariaRelatorio.clear();

            if (ckbCliente.isSelected()) {
                for (int i = 0; i < modeloTabelaRestaurante.retornaListaRelatorioPrincipal().size(); i++) {
                    if (modeloTabelaRestaurante.retornaListaRelatorioPrincipal().get(i).getCLIENTE_NOME().toLowerCase().contains(tfPalavraChaveRestaurante.getText().toLowerCase())) {
                        listaOriginalTemporariaRelatorio.add(modeloTabelaRestaurante.retornaListaRelatorioPrincipal().get(i));
                    }
                }
            } else if (ckbProfissional.isSelected()) {
                for (int i = 0; i < modeloTabelaRestaurante.retornaListaRelatorioPrincipal().size(); i++) {
                    if (modeloTabelaRestaurante.retornaListaRelatorioPrincipal().get(i).getPROFISSIONAL_NOME().toLowerCase().contains(tfPalavraChaveRestaurante.getText().toLowerCase())) {
                        listaOriginalTemporariaRelatorio.add(modeloTabelaRestaurante.retornaListaRelatorioPrincipal().get(i));
                    }
                }
            }

            modeloTabelaRestaurante.inserirListaRelatorioPrincipal(listaOriginalTemporariaRelatorio);

        }

        tbRelatoriosCadastrados.updateUI();
    }//GEN-LAST:event_tfPalavraChaveRestauranteKeyReleased

    private void btExcluirProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirProfissionalActionPerformed
        // TODO add your handling code here:
        if (tbRelatoriosCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatórios selecionados para serem deletados");
        } else if (tbRelatoriosCadastrados.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Não existem mais relatórios para serem deletados");
        } else if (JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir esse relatorio?", "Excluir cliente", 0) == 0) {
            if (tbRelatoriosCadastrados.getSelectedRow() != -1) {

                relatorioExcluir = modeloTabelaRestaurante.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow());
                if (!"sem_imagem.jpg".equals(relatorioExcluir.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO())) {
                    File file = new File("build/classes/imagens/" + relatorioExcluir.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO());
                    file.delete();
                }

                conexaoTabelaRelatorio.removerRelatorio(modeloTabelaRestaurante.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow()).getRELATORIO_ID());
                modeloTabelaRestaurante.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(1));

                tbRelatoriosCadastrados.updateUI();
            }
        }
    }//GEN-LAST:event_btExcluirProfissionalActionPerformed

    private void btEditarProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarProfissionalActionPerformed
        // TODO add your handling code here:
        if (tbRelatoriosCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem editados");
        } else {

            RelatorioPrincipal relatorioPrincipal = modeloTabelaRestaurante.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow());
            System.out.println("RELATORIO_ID = " + relatorioPrincipal.getRELATORIO_ID());

            tfAreaUtil.setText(relatorioPrincipal.getRELATORIO_AREA_UTIL());
            tfNomeCliente.setText(relatorioPrincipal.getCLIENTE_NOME());
            tfNomeFantasia.setText(relatorioPrincipal.getCLIENTE_FANTASIA());
            tfEndereco.setText(relatorioPrincipal.getCLIENTE_ENDERECO());
            tfCidade.setText(relatorioPrincipal.getCLIENTE_MUNICIPIO());
            tfNumero.setText(relatorioPrincipal.getCLIENTE_NUMERO());
            tfBairro.setText(relatorioPrincipal.getCLIENTE_BAIRRO());
            tfTelefone.setText(relatorioPrincipal.getCLIENTE_TELEFONE());
            tfCep.setText(relatorioPrincipal.getCLIENTE_CEP());
            tfCoordenadasUtmN.setText(relatorioPrincipal.getCLIENTE_UTMN());
            tfCoordenadasUtmE.setText(relatorioPrincipal.getCLIENTE_UTME());

            if ((relatorioPrincipal.getCLIENTE_CPF().equals("   .   .   -  ") == false) && (relatorioPrincipal.getCLIENTE_CNPJ().equals("  .   .   /    -  ") == false)) {
                tfCpfCnpj.setText(relatorioPrincipal.getCLIENTE_CPF() + " - " + relatorioPrincipal.getCLIENTE_CNPJ());
            } else {
                if (relatorioPrincipal.getCLIENTE_CPF().equals("   .   .   -  ") == false) {
                    tfCpfCnpj.setText(relatorioPrincipal.getCLIENTE_CPF());
                } else {
                    if (relatorioPrincipal.getCLIENTE_CNPJ().equals("  .   .   /    -  ") == false) {
                        tfCpfCnpj.setText(relatorioPrincipal.getCLIENTE_CNPJ());
                    }
                }
            }

            clienteTemporario.setCliente_id(relatorioPrincipal.getCLIENTE_ID());
            if (relatorioPrincipal.getRELATORIO_LOCALIZACAO() == 0) {
                rbLocalizacaoZonaUrbana.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_LOCALIZACAO() == 1) {
                rbLocalizacaoZonaRural.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_INSERIDO_EM_AREA() == 0) {
                rbInseridoEmAreaIndustrial.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_INSERIDO_EM_AREA() == 1) {
                rbInseridoEmAreaResidencial.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_INSERIDO_EM_AREA() == 2) {
                rbInseridoEmAreaComercial.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_INSERIDO_EM_AREA() == 3) {
                rbInseridoEmAreaMista.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_INSERIDO_EM_AREA() == 4) {
                rbInseridoEmAreaOutra.setSelected(true);
            }
            tfInseridoEmAreaOutraEspecificar.setText(relatorioPrincipal.getRELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR());
            if (relatorioPrincipal.getRELATORIO_HA_RESIDENCIAS() == 1) {
                rbHaResidenciasSim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_HA_RESIDENCIAS() == 0) {
                rbHaResidenciasNao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_AREA_DA_UC() == 1) {
                rbAreaDaUcSim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_AREA_DA_UC() == 0) {
                rbAreaDaUcNao.setSelected(true);
            }
            tfAreaDaUcNome.setText(relatorioPrincipal.getRELATORIO_AREA_DA_UC_NOME());
            tfAreaDaUcNDocumento.setText(relatorioPrincipal.getRELATORIO_AREA_DA_UC_N_DOCUMENTO());
            if (relatorioPrincipal.getRELATORIO_SUPRECAO_VEGETACAO() == 1) {
                rbSuprecaoVegetacaoSim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_SUPRECAO_VEGETACAO() == 0) {
                rbSuprecaoVegetacaoNao.setSelected(true);
            }
            tfSuprecaoVegetacaoDocumentoIdaf.setText(relatorioPrincipal.getRELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF());
            tfCoordenadasUtmN.setText(relatorioPrincipal.getRELATORIO_COORDENADAS_UTM_N());
            tfCoordenadasUtmN.setText(relatorioPrincipal.getRELATORIO_COORDENADAS_UTM_E());
            if (relatorioPrincipal.getRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE() == 0) {
                rbISAPlanejamento.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE() == 1) {
                rbISAInstalacao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE() == 2) {
                rbISAOperacao.setSelected(true);
            }
            Date data = relatorioPrincipal.getRELATORIO_I_S_A_DATA().getTime();
            dataISA.setValue(data);
            tfISAPrevisaoVegetacao.setText(relatorioPrincipal.getRELATORIO_I_S_A_PREVISAO_OPERACAO());
            tfNEmpregados.setText(relatorioPrincipal.getRELATORIO_I_S_A_N_EMPREGADOS());
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAARedePublica")) {
                ckbFAARedePublica.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAPoco")) {

                ckbFAAPoco.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAReservatorios")) {

                ckbFAAReservatorios.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAACursoDagua")) {

                ckbFAACursoDagua.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAALago")) {

                ckbFAALago.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAACaptacao")) {

                ckbFAACaptacao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAAguasCosteiras")) {

                ckbFAAAguasCosteiras.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAANascente")) {

                ckbFAANascente.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAOutros")) {

                ckbFAAOutros.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAReutilizacao")) {

                ckbFAAReutilizacao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAANaoRealiza")) {

                ckbFAANaoRealiza.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAOutorga")) {

                ckbFAAOutorga.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAACertidao")) {

                ckbFAACertidao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAOpcaoLonga")) {

                ckbFAAOpcaoLonga.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAFederal")) {
                ckbFAAFederal.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAEstadual")) {
                ckbFAAEstadual.setSelected(true);
            }
            tfFAAEmpresa.setText(relatorioPrincipal.getRELATORIO_F_A_A_EMPRESA());
            tfFAAPocoTipo.setText(relatorioPrincipal.getRELATORIO_F_A_A_POCO_TIPO());
            tfFAAPocoQtd.setText(relatorioPrincipal.getRELATORIO_F_A_A_POCO_QTD());
            tfFAANumeroLicenca.setText(relatorioPrincipal.getRELATORIO_F_A_A_NUMERO_LICENCA());
            tfFAACursoDaguaNome.setText(relatorioPrincipal.getRELATORIO_F_A_A_CURSO_DAGUA_NOME());
            tfFAALagoNome.setText(relatorioPrincipal.getRELATORIO_F_A_A_LAGO_NOME());
            tfFAAAguasCosteirasEspecificar.setText(relatorioPrincipal.getRELATORIO_F_A_A_AGUAS_COSTEIRAS_ESPECIFICAR());
            tfFAAOutrasEspecificar.setText(relatorioPrincipal.getRELATORIO_F_A_A_OUTRAS_ESPECIFICAR());
            tfFAAConsumoDeAgua.setText(relatorioPrincipal.getRELATORIO_F_A_A_CONSUMO_DE_AGUA());
            tfFAANDocumentoOutorgaRh.setText(relatorioPrincipal.getRELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H());
            tfFAANDocumentoCertidaoDo.setText(relatorioPrincipal.getRELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O());
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGEEfluentesdomésticos")) {

                ckbFGEEfluentesdomésticos.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGENaohageracao1")) {

                ckbFGENaohageracao1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGERededecoletapublica1")) {

                ckbFGERededecoletapublica1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGESistemadeFossa1")) {

                ckbFGESistemadeFossa1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGECorpodagua1")) {

                ckbFGECorpodagua1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGESumidouro")) {

                ckbFGESumidouro.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGEDeLavagensdePisosEEquipamentos")) {

                ckbFGEDeLavagensdePisosEEquipamentos.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGENaoHaGeracao2")) {

                ckbFGENaoHaGeracao2.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGERededecoletapublica2")) {

                ckbFGERededecoletapublica2.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGESistemadeFossa2")) {

                ckbFGESistemadeFossa2.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGELavagemdevasilhames")) {

                ckbFGELavagemdevasilhames.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGENaoHaGeracao3")) {

                ckbFGENaoHaGeracao3.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGERededecoletapublica3")) {

                ckbFGERededecoletapublica3.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGESistemadeFossa3")) {

                ckbFGESistemadeFossa3.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGEEfluentesAtmosfericos")) {

                ckbFGEEfluentesAtmosfericos.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGENaoPossuiFornoLenha")) {

                ckbFGENaoPossuiFornoLenha.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGESistemaDeLavador")) {

                ckbFGESistemaDeLavador.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGEOutros1")) {

                ckbFGEOutros1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGERededecoletapublica4")) {

                ckbFGERededecoletapublica4.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGECorpodagua2")) {

                ckbFGECorpodagua2.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGESistemadeFossa4")) {

                ckbFGESistemadeFossa4.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_GERACAO_EFLUENTES().contains("ckbFGEOutros2")) {

                ckbFGEOutros2.setSelected(true);
            }
            tfFGEEspecificar1.setText(relatorioPrincipal.getRELATORIO_F_G_E_OUTRO_ESPECIFICAR_1());
            tfFGEEspecificar2.setText(relatorioPrincipal.getRELATORIO_F_G_E_OUTRO_ESPECIFICAR_2());
            if (relatorioPrincipal.getRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO().equals(ckbMSTSemestral.getText())) {
                ckbMSTSemestral.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO().equals(ckbMSTAnual.getText())) {
                ckbMSTAnual.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO().equals(ckbMSTOutroPeriodoManutencao1.getText())) {
                ckbMSTOutroPeriodoManutencao1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_MANUTENCAO_DO_SISTEMA_DE_TRATAMENTO().equals(ckbMSTNaoRealiza.getText())) {
                ckbMSTNaoRealiza.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_M_S_T_CAIXA_DE_GORDURA().equals(ckbMSTSemanal.getText())) {
                ckbMSTSemanal.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_M_S_T_CAIXA_DE_GORDURA().equals(ckbMSTMensal.getText())) {
                ckbMSTMensal.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_M_S_T_CAIXA_DE_GORDURA().equals(ckbMSTOutroPeriodoManutencao2.getText())) {
                ckbMSTOutroPeriodoManutencao2.setSelected(true);
            }
            tfMSTEspecificar1.setText(relatorioPrincipal.getRELATORIO_M_S_T_ESPECIFICAR());
            tfMSTEspecificar2.setText(relatorioPrincipal.getRELATORIO_M_S_T_CAIXA_DE_GORDURA_ESPECIFICAR());
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRColetaPublicaMunicipal")) {
                ckbGRColetaPublicaMunicipal.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRReaproveitamento")) {
                ckbGRReaproveitamento.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGROutros1")) {
                ckbGROutros1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRVasilhames")) {
                ckbGRVasilhames.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRPapelPapelao")) {
                ckbGRPapelPapelao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGREmbalagensPlasticas")) {
                ckbGREmbalagensPlasticas.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRAcondicionadosemsacos")) {
                ckbGRAcondicionadosemsacos.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGROutros2")) {
                ckbGROutros2.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRColetaPublicaMunicipal2")) {
                ckbGRColetaPublicaMunicipal2.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRReaproveitamento2")) {
                ckbGRReaproveitamento2.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGROutros3")) {
                ckbGROutros3.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRLodoSistema")) {
                ckbGRLodoSistema.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRNaohaGeracao1")) {
                ckbGRNaohaGeracao1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRResiduosDomesticos")) {
                ckbGRResiduosDomesticos.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRNaohaGeracao2")) {
                ckbGRNaohaGeracao2.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRColetaPublicaMunicipal3")) {
                ckbGRColetaPublicaMunicipal3.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGROutra")) {
                ckbGROutra.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRResiduosConstrucao")) {
                ckbGRResiduosConstrucao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRNaohaGeracao3")) {
                ckbGRNaohaGeracao3.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRToneisBombados")) {
                ckbGRToneisBombados.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGREmpresaLicenciada")) {
                ckbGREmpresaLicenciada.setSelected(true);
            }
            tfGREspecificar1.setText(relatorioPrincipal.getRELATORIO_G_R_ESPECIFICAR_1());
            tfGREspecificar2.setText(relatorioPrincipal.getRELATORIO_G_R_ESPECIFICAR_2());
            tfGRNome1.setText(relatorioPrincipal.getRELATORIO_G_R_NOME_1());
            tfGRNome2.setText(relatorioPrincipal.getRELATORIO_G_R_NOME_2());
            tfGRNome3.setText(relatorioPrincipal.getRELATORIO_G_R_NOME_3());
            tfGRNumeroLicenca1.setText(relatorioPrincipal.getRELATORIO_G_R_NUMERO_LICENCA_1());
            tfGRNumeroLicenca2.setText(relatorioPrincipal.getRELATORIO_G_R_NUMERO_LICENCA_2());
            tfGRNumeroLicenca3.setText(relatorioPrincipal.getRELATORIO_G_R_NUMERO_LICENCA_3());
            tfGREspecificar3.setText(restauranteCadastro.getRELATORIO_G_R_NUMERO_LICENCA_3());
            taRoteiroAcesso.setText(relatorioPrincipal.getRELATORIO_ROTEIRO_DE_ACESSO());
            if (relatorioPrincipal.getRELATORIO_CROQUI_PERGUNTA() == 0) {
                rbCLENadamaisexisteadeclarar.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_CROQUI_PERGUNTA() == 1) {
                rbCLEDeclaramosoqueconstaemanexo.setSelected(true);
            }
            taTextoAnexo.setText(relatorioPrincipal.getRELATORIO_TEXTO_ANEXO());
            Date data1 = relatorioPrincipal.getRELATORIO_DATA_ATUAL().getTime();
            dataAtual.setValue(data1);
            profissionalTemporario.setProfissional_id(relatorioPrincipal.getPROFISSIONAL_ID());
            tfProfissional.setText(relatorioPrincipal.getPROFISSIONAL_NOME());
            relatorioPrincipal.setCATEGORIA_ID(1);
            System.out.println(relatorioPrincipal.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO());

            try {
                String caminho = "build/classes/imagens/";
                //SimpleDateFormat d = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
                //System.out.println(d.format(new Date()));
                File outputfile = new File(caminho + relatorioPrincipal.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO());
                imagem = ManipularImagem.setImagemDimensao(outputfile.getAbsolutePath(), 600, 600);

                lblImagem.setIcon(new ImageIcon(imagem));

            } catch (Exception ex) {
                // System.out.println(ex.printStackTrace().toString());
            }

            tpnAbasRestaurante.setSelectedIndex(0); // Mudando para a PRIMEIRA aba

            btFinalizarCadastro.setToolTipText("Atualizar");
            tpnAbasRestaurante.setTitleAt(0, "Atualizar dados");
            tpnAbasRestaurante.setEnabledAt(1, false);
            btCancelarAtualizacao.setVisible(true);

        }
    }//GEN-LAST:event_btEditarProfissionalActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
        // TODO add your handling code here:ddd
        if (tbRelatoriosCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem imprimidos");
        } else {

            RelatorioPrincipal restaurante = modeloTabelaRestaurante.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow());
            restaurante.getRELATORIO_ID();

            DecimalFormat form = new DecimalFormat("00");
            String data;
            data = form.format(restaurante.getRELATORIO_DATA_ATUAL().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(restaurante.getRELATORIO_DATA_ATUAL().get(Calendar.MONTH) + 1) + "/" + restaurante.getRELATORIO_DATA_ATUAL().get(Calendar.YEAR);

            try {
                //usando a clsse HashMap para criar um filtro
                //  JOptionPane.showMessageDialog(rootPane, recibo.getRecibo_id());
                HashMap filtro = new HashMap();
                filtro.put("id", restaurante.getRELATORIO_ID());
                filtro.put("data", data);
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("build/classes/reports/Restaurante.jasper", filtro, conexao);
                //a linha abaixo exibe o relatório através da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_btImprimirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fc = new JFileChooser();

        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();
            System.out.println(fc.getSelectedFile());

            try {
                imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 1024, 768);

                lblImagem.setIcon(new ImageIcon(new javax.swing.ImageIcon(imagem).getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH)));

            } catch (Exception ex) {
                // System.out.println(ex.printStackTrace().toString());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Você não selecionou nenhum arquivo.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btFinalizarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarCadastroActionPerformed
        // TODO add your handling code here:
        if (tfNomeCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tpnAbasRestaurante, "Por favor escolha o cliente", "Aviso", 2);
            tfNomeCliente.requestFocus();
        } else {
            if (tfProfissional.getText().isEmpty()) {
                JOptionPane.showMessageDialog(tpnAbasRestaurante, "Por favor escolha o profissional", "Aviso", 2);
                tfProfissional.requestFocus();
            } else {
                if (btFinalizarCadastro.getToolTipText().equals("Cadastrar")) {
                    conexaoTabelaRelatorio.inserirNovoRPrincipal(preencherDadosCadastroRestaurante());
                    buscarRelatoriosTabela();
                    limparCamposCadastroRestaurante();
                } else {
                    conexaoTabelaRelatorio.alterarRelatorio(modeloTabelaRestaurante.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow()).getRELATORIO_ID(), preencherDadosCadastroRestaurante());
                    buscarRelatoriosTabela();
                    limparCamposCadastroRestaurante();
                }
            }
        }
    }//GEN-LAST:event_btFinalizarCadastroActionPerformed

    private void btCancelarAtualizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarAtualizacaoActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(rootPane, "Deseja mesmo cancelar as alterações", "Aviso", 0) == 0) {
            btFinalizarCadastro.setToolTipText("Cadastrar");
            btCancelarAtualizacao.setVisible(false);
            //tpnAbasRequerimentos.setEnabledAt(1, true);
            //tpnAbasRequerimentos.setSelectedIndex(1);
            //tpnAbasRequerimentos.setTitleAt(0, "Gerar Requerimento");
            CheckBoxGR = "";
            CheckBoxFGE = "";
            CheckBoxFAA = "";
            tfAreaUtil.setText("");
            tfNomeCliente.setText("");
            tfNomeFantasia.setText("");
            tfEndereco.setText("");
            tfCidade.setText("");
            tfCpfCnpj.setText("");
            tfNumero.setText("");
            tfBairro.setText("");
            tfTelefone.setText("");
            tfCep.setText("");
            tfCoordenadasUtmN.setText("");
            tfCoordenadasUtmE.setText("");
            rbLocalizacaoZonaUrbana.setSelected(false);
            rbLocalizacaoZonaRural.setSelected(false);
            rbInseridoEmAreaIndustrial.setSelected(false);
            rbInseridoEmAreaResidencial.setSelected(false);
            rbInseridoEmAreaComercial.setSelected(false);
            rbInseridoEmAreaMista.setSelected(false);
            rbInseridoEmAreaOutra.setSelected(false);
            tfInseridoEmAreaOutraEspecificar.setText("");
            jLabel12 = new javax.swing.JLabel();
            rbHaResidenciasSim.setSelected(false);
            rbHaResidenciasNao.setSelected(false);
            rbAreaDaUcSim.setSelected(false);
            tfAreaDaUcNDocumento.setText("");
            tfAreaDaUcNome.setText("");
            rbAreaDaUcNao.setSelected(false);
            rbSuprecaoVegetacaoSim.setSelected(false);
            tfSuprecaoVegetacaoDocumentoIdaf.setText("");
            rbSuprecaoVegetacaoNao.setSelected(false);
            tfCoordenadasUtmN.setText("");
            tfCoordenadasUtmE.setText("");
            rbISAPlanejamento.setSelected(false);
            rbISAInstalacao.setSelected(false);
            rbISAOperacao.setSelected(false);
            tfISAPrevisaoVegetacao.setText("");
            //pnISAData.setText("");
            tfNEmpregados.setText("");
            ckbFAARedePublica.setSelected(false);
            ckbFAAPoco.setSelected(false);
            ckbFAAReservatorios.setSelected(false);
            ckbFAACursoDagua.setSelected(false);
            ckbFAALago.setSelected(false);
            ckbFAACaptacao.setSelected(false);
            ckbFAAAguasCosteiras.setSelected(false);
            ckbFAANascente.setSelected(false);
            ckbFAAOutros.setSelected(false);
            ckbFAAReutilizacao.setSelected(false);
            ckbFAANaoRealiza.setSelected(false);
            tfFAAConsumoDeAgua.setText("");
            ckbFAAOutorga.setSelected(false);
            tfFAANDocumentoOutorgaRh.setText("");
            ckbFAACertidao.setSelected(false);
            ckbFAAFederal.setSelected(false);
            ckbFAAEstadual.setSelected(false);
            tfFAANDocumentoCertidaoDo = new javax.swing.JTextField();
            ckbFAAOpcaoLonga.setSelected(false);
            tfFAAEmpresa.setText("");
            tfFAAPocoTipo.setText("");
            tfFAAPocoQtd.setText("");
            tfFAANumeroLicenca.setText("");
            tfFAACursoDaguaNome.setText("");
            tfFAALagoNome.setText("");
            tfFAAAguasCosteirasEspecificar.setText("");
            tfFAAOutrasEspecificar.setText("");
            ckbFGERededecoletapublica2.setSelected(false);
            ckbFGESistemadeFossa2.setSelected(false);
            ckbFGEDeLavagensdePisosEEquipamentos.setSelected(false);
            ckbFGENaoHaGeracao2.setSelected(false);
            ckbFGEEfluentesAtmosfericos.setSelected(false);
            ckbFGESistemaDeLavador.setSelected(false);
            ckbFGENaoPossuiFornoLenha.setSelected(false);
            ckbFGEOutros1.setSelected(false);
            tfFGEEspecificar1.setText("");
            tfFGEEspecificar2.setText("");
            ckbFGERededecoletapublica4.setSelected(false);
            ckbFGECorpodagua2.setSelected(false);
            ckbFGESistemadeFossa4.setSelected(false);
            ckbFGEOutros2.setSelected(false);
            ckbFGEEfluentesdomésticos.setSelected(false);
            ckbFGERededecoletapublica1.setSelected(false);
            ckbFGESistemadeFossa1.setSelected(false);
            ckbFGENaohageracao1.setSelected(false);
            ckbFGECorpodagua1.setSelected(false);
            ckbFGESumidouro.setSelected(false);
            ckbFGELavagemdevasilhames.setSelected(false);
            ckbFGERededecoletapublica3.setSelected(false);
            ckbFGESistemadeFossa3.setSelected(false);
            ckbFGENaoHaGeracao3.setSelected(false);
            ckbMSTSemestral.setSelected(false);
            ckbMSTAnual.setSelected(false);
            ckbMSTOutroPeriodoManutencao1.setSelected(false);
            tfMSTEspecificar1.setText("");
            ckbMSTNaoRealiza.setSelected(false);
            ckbMSTSemanal.setSelected(false);
            ckbMSTMensal.setSelected(false);
            ckbMSTOutroPeriodoManutencao2.setSelected(false);
            tfMSTEspecificar2.setText("");
            ckbGRColetaPublicaMunicipal.setSelected(false);
            ckbGRReaproveitamento.setSelected(false);
            ckbGROutros1.setSelected(false);
            tfGREspecificar1.setText("");
            tfGRNome1.setText("");
            tfGRNumeroLicenca1.setText("");
            ckbGREmbalagensPlasticas.setSelected(false);
            ckbGRPapelPapelao.setSelected(false);
            ckbGRVasilhames.setSelected(false);
            ckbGRAcondicionadosemsacos.setSelected(false);
            ckbGROutros2.setSelected(false);
            ckbGRColetaPublicaMunicipal2.setSelected(false);
            ckbGRReaproveitamento2.setSelected(false);
            ckbGROutros3.setSelected(false);
            tfGREspecificar2.setText("");
            ckbGRLodoSistema.setSelected(false);
            tfGRNome2.setText("");
            tfGRNumeroLicenca2.setText("");
            ckbGRNaohaGeracao1.setSelected(false);
            ckbGRResiduosDomesticos.setSelected(false);
            ckbGRColetaPublicaMunicipal3.setSelected(false);
            ckbGROutra.setSelected(false);
            ckbGRNaohaGeracao2.setSelected(false);
            ckbGRResiduosConstrucao.setSelected(false);
            ckbGRToneisBombados.setSelected(false);
            ckbGREmpresaLicenciada.setSelected(false);
            ckbGRNaohaGeracao3.setSelected(false);
            tfGRNome3.setText("");
            tfGRNumeroLicenca3.setText("");
            tfGREspecificar3.setText("");
            taRoteiroAcesso.setText("");
            rbCLENadamaisexisteadeclarar.setSelected(false);
            rbCLEDeclaramosoqueconstaemanexo.setSelected(false);
            taTextoAnexo.setText("");
            //tfDataAtual.setText("");
            tfProfissional.setText("");
            imagem = null;
            lblImagem.setIcon(new ImageIcon(""));

            tpnAbasRestaurante.setSelectedIndex(1); // Mudando para a PRIMEIRA aba

            tpnAbasRestaurante.setTitleAt(0, "Cadastrar Novo Restaurante");
            btFinalizarCadastro.setToolTipText("Cadastrar");
            tpnAbasRestaurante.setEnabledAt(1, true);
            btCancelarAtualizacao.setVisible(false);
        }
    }//GEN-LAST:event_btCancelarAtualizacaoActionPerformed

    private void tfProfissionalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfProfissionalMouseClicked
        TelaRelatorioProfissional telaRelatorioProfissional1 = new TelaRelatorioProfissional(null, true);
        telaRelatorioProfissional1.setVisible(true);
        // Criando o cliente à receber o cliente da tela relatoriorecibo
        Profissional profissionalSelecionado = new Profissional();
        profissionalSelecionado = telaRelatorioProfissional1.retornarProfissionalSelecionado();
        if (profissionalSelecionado != null) {
            tfProfissional.setText(profissionalSelecionado.getProfissional_nome());
            profissionalTemporario = telaRelatorioProfissional1.retornarProfissionalSelecionado();
        }
    }//GEN-LAST:event_tfProfissionalMouseClicked

    private void rbCLEDeclaramosoqueconstaemanexoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCLEDeclaramosoqueconstaemanexoMouseClicked
        rbCLENadamaisexisteadeclarar.setSelected(false);
    }//GEN-LAST:event_rbCLEDeclaramosoqueconstaemanexoMouseClicked

    private void rbCLENadamaisexisteadeclararMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCLENadamaisexisteadeclararMouseClicked
        rbCLEDeclaramosoqueconstaemanexo.setSelected(false);
    }//GEN-LAST:event_rbCLENadamaisexisteadeclararMouseClicked

    private void ckbMSTOutroPeriodoManutencao2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbMSTOutroPeriodoManutencao2MouseClicked
        ckbMSTMensal.setSelected(false);
        ckbMSTSemanal.setSelected(false);
    }//GEN-LAST:event_ckbMSTOutroPeriodoManutencao2MouseClicked

    private void ckbMSTMensalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbMSTMensalMouseClicked
        ckbMSTSemanal.setSelected(false);
        ckbMSTOutroPeriodoManutencao2.setSelected(false);
    }//GEN-LAST:event_ckbMSTMensalMouseClicked

    private void ckbMSTSemanalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbMSTSemanalMouseClicked
        ckbMSTMensal.setSelected(false);
        ckbMSTOutroPeriodoManutencao2.setSelected(false);
    }//GEN-LAST:event_ckbMSTSemanalMouseClicked

    private void ckbMSTNaoRealizaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbMSTNaoRealizaMouseClicked
        ckbMSTAnual.setSelected(false);
        ckbMSTOutroPeriodoManutencao1.setSelected(false);
        ckbMSTSemestral.setSelected(false);
    }//GEN-LAST:event_ckbMSTNaoRealizaMouseClicked

    private void ckbMSTOutroPeriodoManutencao1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbMSTOutroPeriodoManutencao1MouseClicked
        ckbMSTAnual.setSelected(false);
        ckbMSTSemestral.setSelected(false);
        ckbMSTNaoRealiza.setSelected(false);
    }//GEN-LAST:event_ckbMSTOutroPeriodoManutencao1MouseClicked

    private void ckbMSTAnualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbMSTAnualMouseClicked
        ckbMSTSemestral.setSelected(false);
        ckbMSTOutroPeriodoManutencao1.setSelected(false);
        ckbMSTNaoRealiza.setSelected(false);
    }//GEN-LAST:event_ckbMSTAnualMouseClicked

    private void ckbMSTSemestralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbMSTSemestralMouseClicked
        ckbMSTAnual.setSelected(false);
        ckbMSTOutroPeriodoManutencao1.setSelected(false);
        ckbMSTNaoRealiza.setSelected(false);
    }//GEN-LAST:event_ckbMSTSemestralMouseClicked

    private void rbISAOperacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbISAOperacaoMouseClicked
        rbISAPlanejamento.setSelected(false);
        rbISAInstalacao.setSelected(false);
    }//GEN-LAST:event_rbISAOperacaoMouseClicked

    private void rbISAInstalacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbISAInstalacaoMouseClicked
        rbISAPlanejamento.setSelected(false);
        rbISAOperacao.setSelected(false);
    }//GEN-LAST:event_rbISAInstalacaoMouseClicked

    private void rbISAPlanejamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbISAPlanejamentoMouseClicked
        rbISAInstalacao.setSelected(false);
        rbISAOperacao.setSelected(false);
    }//GEN-LAST:event_rbISAPlanejamentoMouseClicked

    private void rbSuprecaoVegetacaoNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSuprecaoVegetacaoNaoMouseClicked
        rbSuprecaoVegetacaoSim.setSelected(false);
    }//GEN-LAST:event_rbSuprecaoVegetacaoNaoMouseClicked

    private void rbSuprecaoVegetacaoSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSuprecaoVegetacaoSimMouseClicked
        rbSuprecaoVegetacaoNao.setSelected(false);
    }//GEN-LAST:event_rbSuprecaoVegetacaoSimMouseClicked

    private void rbAreaDaUcNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbAreaDaUcNaoMouseClicked
        rbAreaDaUcSim.setSelected(false);
    }//GEN-LAST:event_rbAreaDaUcNaoMouseClicked

    private void rbAreaDaUcSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbAreaDaUcSimMouseClicked
        rbAreaDaUcNao.setSelected(false);
    }//GEN-LAST:event_rbAreaDaUcSimMouseClicked

    private void rbHaResidenciasNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbHaResidenciasNaoMouseClicked
        rbHaResidenciasSim.setSelected(false);
    }//GEN-LAST:event_rbHaResidenciasNaoMouseClicked

    private void rbHaResidenciasSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbHaResidenciasSimMouseClicked
        rbHaResidenciasNao.setSelected(false);
    }//GEN-LAST:event_rbHaResidenciasSimMouseClicked

    private void rbInseridoEmAreaOutraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaOutraMouseClicked
        rbInseridoEmAreaComercial.setSelected(false);
        rbInseridoEmAreaResidencial.setSelected(false);
        rbInseridoEmAreaMista.setSelected(false);
        rbInseridoEmAreaIndustrial.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaOutraMouseClicked

    private void rbInseridoEmAreaMistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaMistaMouseClicked
        rbInseridoEmAreaComercial.setSelected(false);
        rbInseridoEmAreaResidencial.setSelected(false);
        rbInseridoEmAreaOutra.setSelected(false);
        rbInseridoEmAreaIndustrial.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaMistaMouseClicked

    private void rbInseridoEmAreaComercialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaComercialMouseClicked
        rbInseridoEmAreaResidencial.setSelected(false);
        rbInseridoEmAreaMista.setSelected(false);
        rbInseridoEmAreaOutra.setSelected(false);
        rbInseridoEmAreaIndustrial.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaComercialMouseClicked

    private void rbInseridoEmAreaResidencialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaResidencialMouseClicked
        rbInseridoEmAreaComercial.setSelected(false);
        rbInseridoEmAreaMista.setSelected(false);
        rbInseridoEmAreaOutra.setSelected(false);
        rbInseridoEmAreaIndustrial.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaResidencialMouseClicked

    private void rbInseridoEmAreaIndustrialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaIndustrialMouseClicked
        rbInseridoEmAreaComercial.setSelected(false);
        rbInseridoEmAreaMista.setSelected(false);
        rbInseridoEmAreaOutra.setSelected(false);
        rbInseridoEmAreaResidencial.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaIndustrialMouseClicked

    private void rbLocalizacaoZonaRuralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbLocalizacaoZonaRuralMouseClicked
        rbLocalizacaoZonaUrbana.setSelected(false);
    }//GEN-LAST:event_rbLocalizacaoZonaRuralMouseClicked

    private void rbLocalizacaoZonaUrbanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbLocalizacaoZonaUrbanaMouseClicked
        rbLocalizacaoZonaRural.setSelected(false);
    }//GEN-LAST:event_rbLocalizacaoZonaUrbanaMouseClicked

    private void tfNomeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNomeClienteMouseClicked
        TelaRelatorioCliente telaRelatorioCliente = new TelaRelatorioCliente(null, true, 1);
        telaRelatorioCliente.setVisible(true);
        // Criando o contratante à receber o contratante da tela relatoriorecibo
        Cliente clienteSelecionado = new Cliente();
        clienteSelecionado = telaRelatorioCliente.retornarClienteSelecionado();
        if (clienteSelecionado != null) {
            tfNomeCliente.setText(clienteSelecionado.getCliente_nome());
            tfNomeFantasia.setText(clienteSelecionado.getCliente_fantasia());
            tfEndereco.setText(clienteSelecionado.getCliente_endereco());
            tfCidade.setText(clienteSelecionado.getCliente_municipio());
            tfNumero.setText(clienteSelecionado.getCliente_numero());
            tfBairro.setText(clienteSelecionado.getCliente_bairro());
            tfTelefone.setText(clienteSelecionado.getCliente_telefone());
            tfCep.setText(clienteSelecionado.getCliente_cep());
            tfCoordenadasUtmN.setText(clienteSelecionado.getCliente_utmn());
            tfCoordenadasUtmE.setText(clienteSelecionado.getCliente_utme());
            if ((clienteSelecionado.getCliente_cnpj().equals("  .   .   /    -  ") == false) && (clienteSelecionado.getCliente_cpf().equals("   .   .   -  ") == false)) {
                tfCpfCnpj.setText(clienteSelecionado.getCliente_cpf() + " - " + clienteSelecionado.getCliente_cnpj());
            } else {
                if (clienteSelecionado.getCliente_cpf().equals("   .   .   -  ") == false) {
                    tfCpfCnpj.setText(clienteSelecionado.getCliente_cpf());
                } else {
                    if (clienteSelecionado.getCliente_cnpj().equals("  .   .   /    -  ") == false) {
                        tfCpfCnpj.setText(clienteSelecionado.getCliente_cnpj());
                    }
                }
            }
            clienteTemporario = telaRelatorioCliente.retornarClienteSelecionado();
        }


    }//GEN-LAST:event_tfNomeClienteMouseClicked

    private void tfAreaUtilFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAreaUtilFocusLost
        String a = tfAreaUtil.getText();
        String d = a.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".").replace("m²", "");
        BigDecimal valor = new BigDecimal(d);
        //NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat nf = new DecimalFormat();
        nf.applyPattern("#,##0.00");
        String formatado = nf.format(valor);
        tfAreaUtil.setText(formatado + " m²");
    }//GEN-LAST:event_tfAreaUtilFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelarAtualizacao;
    private javax.swing.JButton btEditarProfissional;
    private javax.swing.JButton btExcluirProfissional;
    private javax.swing.JButton btFinalizarCadastro;
    private javax.swing.JButton btImprimir;
    private javax.swing.JCheckBox ckbCliente;
    private javax.swing.JCheckBox ckbFAAAguasCosteiras;
    private javax.swing.JCheckBox ckbFAACaptacao;
    private javax.swing.JCheckBox ckbFAACertidao;
    private javax.swing.JCheckBox ckbFAACursoDagua;
    private javax.swing.JCheckBox ckbFAAEstadual;
    private javax.swing.JCheckBox ckbFAAFederal;
    private javax.swing.JCheckBox ckbFAALago;
    private javax.swing.JCheckBox ckbFAANaoRealiza;
    private javax.swing.JCheckBox ckbFAANascente;
    private javax.swing.JCheckBox ckbFAAOpcaoLonga;
    private javax.swing.JCheckBox ckbFAAOutorga;
    private javax.swing.JCheckBox ckbFAAOutros;
    private javax.swing.JCheckBox ckbFAAPoco;
    private javax.swing.JCheckBox ckbFAARedePublica;
    private javax.swing.JCheckBox ckbFAAReservatorios;
    private javax.swing.JCheckBox ckbFAAReutilizacao;
    private javax.swing.JCheckBox ckbFGECorpodagua1;
    private javax.swing.JCheckBox ckbFGECorpodagua2;
    private javax.swing.JCheckBox ckbFGEDeLavagensdePisosEEquipamentos;
    private javax.swing.JCheckBox ckbFGEEfluentesAtmosfericos;
    private javax.swing.JCheckBox ckbFGEEfluentesdomésticos;
    private javax.swing.JCheckBox ckbFGELavagemdevasilhames;
    private javax.swing.JCheckBox ckbFGENaoHaGeracao2;
    private javax.swing.JCheckBox ckbFGENaoHaGeracao3;
    private javax.swing.JCheckBox ckbFGENaoPossuiFornoLenha;
    private javax.swing.JCheckBox ckbFGENaohageracao1;
    private javax.swing.JCheckBox ckbFGEOutros1;
    private javax.swing.JCheckBox ckbFGEOutros2;
    private javax.swing.JCheckBox ckbFGERededecoletapublica1;
    private javax.swing.JCheckBox ckbFGERededecoletapublica2;
    private javax.swing.JCheckBox ckbFGERededecoletapublica3;
    private javax.swing.JCheckBox ckbFGERededecoletapublica4;
    private javax.swing.JCheckBox ckbFGESistemaDeLavador;
    private javax.swing.JCheckBox ckbFGESistemadeFossa1;
    private javax.swing.JCheckBox ckbFGESistemadeFossa2;
    private javax.swing.JCheckBox ckbFGESistemadeFossa3;
    private javax.swing.JCheckBox ckbFGESistemadeFossa4;
    private javax.swing.JCheckBox ckbFGESumidouro;
    private javax.swing.JCheckBox ckbGRAcondicionadosemsacos;
    private javax.swing.JCheckBox ckbGRColetaPublicaMunicipal;
    private javax.swing.JCheckBox ckbGRColetaPublicaMunicipal2;
    private javax.swing.JCheckBox ckbGRColetaPublicaMunicipal3;
    private javax.swing.JCheckBox ckbGREmbalagensPlasticas;
    private javax.swing.JCheckBox ckbGREmpresaLicenciada;
    private javax.swing.JCheckBox ckbGRLodoSistema;
    private javax.swing.JCheckBox ckbGRNaohaGeracao1;
    private javax.swing.JCheckBox ckbGRNaohaGeracao2;
    private javax.swing.JCheckBox ckbGRNaohaGeracao3;
    private javax.swing.JCheckBox ckbGROutra;
    private javax.swing.JCheckBox ckbGROutros1;
    private javax.swing.JCheckBox ckbGROutros2;
    private javax.swing.JCheckBox ckbGROutros3;
    private javax.swing.JCheckBox ckbGRPapelPapelao;
    private javax.swing.JCheckBox ckbGRReaproveitamento;
    private javax.swing.JCheckBox ckbGRReaproveitamento2;
    private javax.swing.JCheckBox ckbGRResiduosConstrucao;
    private javax.swing.JCheckBox ckbGRResiduosDomesticos;
    private javax.swing.JCheckBox ckbGRToneisBombados;
    private javax.swing.JCheckBox ckbGRVasilhames;
    private javax.swing.JCheckBox ckbMSTAnual;
    private javax.swing.JLabel ckbMSTEspecificar;
    private javax.swing.JCheckBox ckbMSTMensal;
    private javax.swing.JCheckBox ckbMSTNaoRealiza;
    private javax.swing.JCheckBox ckbMSTOutroPeriodoManutencao1;
    private javax.swing.JCheckBox ckbMSTOutroPeriodoManutencao2;
    private javax.swing.JCheckBox ckbMSTSemanal;
    private javax.swing.JCheckBox ckbMSTSemestral;
    private javax.swing.JCheckBox ckbProfissional;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JPanel pnCROQUI;
    private com.toedter.calendar.JDayChooser pnDataAtual;
    private com.toedter.calendar.JDayChooser pnISAData;
    private javax.swing.JPanel pnRestaurante;
    private javax.swing.JRadioButton rbAreaDaUcNao;
    private javax.swing.JRadioButton rbAreaDaUcSim;
    private javax.swing.JRadioButton rbCLEDeclaramosoqueconstaemanexo;
    private javax.swing.JRadioButton rbCLENadamaisexisteadeclarar;
    private javax.swing.JRadioButton rbHaResidenciasNao;
    private javax.swing.JRadioButton rbHaResidenciasSim;
    private javax.swing.JRadioButton rbISAInstalacao;
    private javax.swing.JRadioButton rbISAOperacao;
    private javax.swing.JRadioButton rbISAPlanejamento;
    private javax.swing.JRadioButton rbInseridoEmAreaComercial;
    private javax.swing.JRadioButton rbInseridoEmAreaIndustrial;
    private javax.swing.JRadioButton rbInseridoEmAreaMista;
    private javax.swing.JRadioButton rbInseridoEmAreaOutra;
    private javax.swing.JRadioButton rbInseridoEmAreaResidencial;
    private javax.swing.JRadioButton rbLocalizacaoZonaRural;
    private javax.swing.JRadioButton rbLocalizacaoZonaUrbana;
    private javax.swing.JRadioButton rbSuprecaoVegetacaoNao;
    private javax.swing.JRadioButton rbSuprecaoVegetacaoSim;
    private javax.swing.JTextArea taRoteiroAcesso;
    private javax.swing.JTextArea taTextoAnexo;
    private javax.swing.JTable tbRelatoriosCadastrados;
    private javax.swing.JTextField tfAreaDaUcNDocumento;
    private javax.swing.JTextField tfAreaDaUcNome;
    private javax.swing.JTextField tfAreaUtil;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCep;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfCoordenadasUtmE;
    private javax.swing.JTextField tfCoordenadasUtmN;
    private javax.swing.JTextField tfCpfCnpj;
    private javax.swing.JTextField tfEndereco;
    private javax.swing.JTextField tfFAAAguasCosteirasEspecificar;
    private javax.swing.JTextField tfFAAConsumoDeAgua;
    private javax.swing.JTextField tfFAACursoDaguaNome;
    private javax.swing.JTextField tfFAAEmpresa;
    private javax.swing.JTextField tfFAALagoNome;
    private javax.swing.JTextField tfFAANDocumentoCertidaoDo;
    private javax.swing.JTextField tfFAANDocumentoOutorgaRh;
    private javax.swing.JTextField tfFAANumeroLicenca;
    private javax.swing.JTextField tfFAAOutrasEspecificar;
    private javax.swing.JTextField tfFAAPocoQtd;
    private javax.swing.JTextField tfFAAPocoTipo;
    private javax.swing.JTextField tfFGEEspecificar1;
    private javax.swing.JTextField tfFGEEspecificar2;
    private javax.swing.JTextField tfGREspecificar1;
    private javax.swing.JTextField tfGREspecificar2;
    private javax.swing.JTextField tfGREspecificar3;
    private javax.swing.JTextField tfGRNome1;
    private javax.swing.JTextField tfGRNome2;
    private javax.swing.JTextField tfGRNome3;
    private javax.swing.JTextField tfGRNumeroLicenca1;
    private javax.swing.JTextField tfGRNumeroLicenca2;
    private javax.swing.JTextField tfGRNumeroLicenca3;
    private javax.swing.JTextField tfISAPrevisaoVegetacao;
    private javax.swing.JTextField tfInseridoEmAreaOutraEspecificar;
    private javax.swing.JTextField tfMSTEspecificar1;
    private javax.swing.JTextField tfMSTEspecificar2;
    private javax.swing.JTextField tfNEmpregados;
    private javax.swing.JTextField tfNomeCliente;
    private javax.swing.JTextField tfNomeFantasia;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfPalavraChaveRestaurante;
    private javax.swing.JTextField tfProfissional;
    private javax.swing.JTextField tfSuprecaoVegetacaoDocumentoIdaf;
    private javax.swing.JTextField tfTelefone;
    private javax.swing.JTabbedPane tpnAbasRestaurante;
    // End of variables declaration//GEN-END:variables
}
