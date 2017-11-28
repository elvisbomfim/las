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
import modelos.RelatorioPrincipal;
import modelos.Representante;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;

/**
 *
 * @author Jean
 */
public class jifFormTerraplanagem extends javax.swing.JInternalFrame {

    DateField dataAtual = CalendarFactory.createDateField();
    DateField dataISA = CalendarFactory.createDateField();

    /**
     * Strings concatenadas
     */
    String CheckBoxFAA = "";
    String CheckBoxFGE = "";
    String CheckBoxGR = "";
    String CheckBoxEA = "";

    /**
     * Creates new form jifFormRestaurante
     */
    int condicao = 0;

    RelatorioPrincipal terraplanagemCadastro = new RelatorioPrincipal();
    Cliente clienteTemporario = new Cliente();
    Profissional profissionalTemporario = new Profissional();
    Representante representanteTemporario = new Representante();
    RelatorioPrincipal relatorioExcluir = new RelatorioPrincipal();

    /**
     * Classes de definição de modelos de tabela
     */
    ModeloTabelaRelatorios modeloTabelaTerraplanagem = new ModeloTabelaRelatorios();
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

    public jifFormTerraplanagem() {
        initComponents();
        conexao = ConexaoPDF.conector();
        Calendar cal = Calendar.getInstance();
        dataAtual.setBaseDate(cal.getTime());
        pnDataAtual.add(dataAtual);
        setFrameIcon(new ImageIcon(this.getClass().getResource("/imagens/icon.png")));
        //Definindo o botão DateField (Data Inicio do Semestre) para seleção de uma data e atribuindo uma ação de mudança à ele.
        dataAtual.setSize((pnDataAtual.getWidth()), (pnDataAtual.getHeight()));
        btCancelarAtualizacao.setVisible(false);
//        Calendar calISA = Calendar.getInstance();
        //       dataISA.setBaseDate(calISA.getTime());
        //       pnISAData.add(dataISA);
        // Definindo o botão DateField (Data Inicio do Semestre) para seleção de uma data e atribuindo uma ação de mudança à ele.
        //       dataISA.setSize((pnISAData.getWidth()), (pnISAData.getHeight()));
        tbRelatoriosCadastrados.setModel(modeloTabelaTerraplanagem);
        buscarRelatoriosTabela();

        tfAreaIntervencao.setDocument(new LetrasMaiusculas());
        tfAlturaTalude.setDocument(new LetrasMaiusculas());
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
        tfCoordenadasUtmN.setDocument(new LetrasMaiusculas());
        tfCoordenadasUtmE.setDocument(new LetrasMaiusculas());
        tfISAPrevisaoVegetacao.setDocument(new LetrasMaiusculas());
        tfISAAreaTotalMovimentacaoTerra.setDocument(new LetrasMaiusculas());
        tfISAAreaCorte.setDocument(new LetrasMaiusculas());
        tfISAAreaAterro.setDocument(new LetrasMaiusculas());
        tfISATaludesFormados.setDocument(new LetrasMaiusculas());
        tfRHNome1.setDocument(new LetrasMaiusculas());
        tfRHDistancia1.setDocument(new LetrasMaiusculas());
        tfBotaForaUtmN1.setDocument(new LetrasMaiusculas());
        tfBotaForaUtmE1.setDocument(new LetrasMaiusculas());
        tfBotaForaNDaUc1.setDocument(new LetrasMaiusculas());
        tfRHNome2.setDocument(new LetrasMaiusculas());
        tfRHDistancia2.setDocument(new LetrasMaiusculas());
        tfBotaForaUtmN2.setDocument(new LetrasMaiusculas());
        tfBotaForaUtmE2.setDocument(new LetrasMaiusculas());
        tfBotaForaAreaEmprestimo.setDocument(new LetrasMaiusculas());
        tfBotaForaNDaUc2.setDocument(new LetrasMaiusculas());
        tfRHNome3.setDocument(new LetrasMaiusculas());
        tfRHDistancia3.setDocument(new LetrasMaiusculas());
        tfNEmpregados.setDocument(new LetrasMaiusculas());
        taRoteiroAcesso.setDocument(new LetrasMaiusculas());
        taTextoAnexo.setDocument(new LetrasMaiusculas());
        tfProfissional.setDocument(new LetrasMaiusculas());

    }

    public void buscarRelatoriosTabela() {

        modeloTabelaTerraplanagem.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(4));
        listaOriginalTemporariaRelatorio = modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal();
        tbRelatoriosCadastrados.updateUI();
    }

    public RelatorioPrincipal preencherDadosCadastroTuristico() {

        terraplanagemCadastro.setRELATORIO_AREA_INTERVENCAO(tfAreaIntervencao.getText());
        terraplanagemCadastro.setRELATORIO_ALTURA_DO_TALUDE(tfAlturaTalude.getText());
        terraplanagemCadastro.setCLIENTE_ID(clienteTemporario.getCliente_id());
        if (rbLocalizacaoZonaUrbana.isSelected()) {
            terraplanagemCadastro.setRELATORIO_LOCALIZACAO(0);
        }
        if (rbLocalizacaoZonaRural.isSelected()) {
            terraplanagemCadastro.setRELATORIO_LOCALIZACAO(1);
        }
        if (rbInseridoEmAreaIndustrial.isSelected()) {
            terraplanagemCadastro.setRELATORIO_INSERIDO_EM_AREA(0);
        }
        if (rbInseridoEmAreaResidencial.isSelected()) {
            terraplanagemCadastro.setRELATORIO_INSERIDO_EM_AREA(1);
        }
        if (rbInseridoEmAreaComercial.isSelected()) {
            terraplanagemCadastro.setRELATORIO_INSERIDO_EM_AREA(2);
        }
        if (rbInseridoEmAreaMista.isSelected()) {
            terraplanagemCadastro.setRELATORIO_INSERIDO_EM_AREA(3);
        }
        if (rbInseridoEmAreaOutra.isSelected()) {
            terraplanagemCadastro.setRELATORIO_INSERIDO_EM_AREA(4);
        }
        terraplanagemCadastro.setRELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR(tfInseridoEmAreaOutraEspecificar.getText());
        if (rbHaResidenciasSim.isSelected()) {
            terraplanagemCadastro.setRELATORIO_HA_RESIDENCIAS(1);
        }
        if (rbHaResidenciasNao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_HA_RESIDENCIAS(1);
        }
        if (rbAreaDaUcSim.isSelected()) {
            terraplanagemCadastro.setRELATORIO_AREA_DA_UC(1);
        }
        if (rbAreaDaUcNao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_AREA_DA_UC(0);
        }
        terraplanagemCadastro.setRELATORIO_AREA_DA_UC_NOME(tfAreaDaUcNome.getText());
        terraplanagemCadastro.setRELATORIO_AREA_DA_UC_N_DOCUMENTO(tfAreaDaUcNDocumento.getText());
        if (rbSuprecaoVegetacaoSim.isSelected()) {
            terraplanagemCadastro.setRELATORIO_SUPRECAO_VEGETACAO(1);
        }
        if (rbSuprecaoVegetacaoNao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_SUPRECAO_VEGETACAO(0);
        }
        terraplanagemCadastro.setRELATORIO_COORDENADAS_UTM_N(tfCoordenadasUtmN.getText());
        terraplanagemCadastro.setRELATORIO_COORDENADAS_UTM_E(tfCoordenadasUtmN.getText());
        if (rbISAPlanejamento.isSelected()) {
            terraplanagemCadastro.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(0);
        }
        if (rbISAInstalacao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(1);
        }
        if (rbISAOperacao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(2);
        }
        Calendar calISA = Calendar.getInstance();
        calISA.setTime((Date) dataISA.getValue());
        terraplanagemCadastro.setRELATORIO_I_S_A_DATA(calISA);
        terraplanagemCadastro.setRELATORIO_I_S_A_PREVISAO_OPERACAO(tfISAPrevisaoVegetacao.getText());
        terraplanagemCadastro.setRELATORIO_I_S_A_N_EMPREGADOS(tfNEmpregados.getText());
        terraplanagemCadastro.setRELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA(tfISAAreaTotalMovimentacaoTerra.getText());
        terraplanagemCadastro.setRELATORIO_I_S_A_AREA_DE_CORTE(tfISAAreaCorte.getText());
        terraplanagemCadastro.setRELATORIO_I_S_A_AREA_DE_ATERRO(tfISAAreaAterro.getText());
        terraplanagemCadastro.setRELATORIO_I_S_A_TALUDES_FORMADOS(tfISATaludesFormados.getText());
        if (rbRHNao1.isSelected()) {
            terraplanagemCadastro.setRELATORIO_RECURSOS_HIDRICOS_1(0);
        }
        if (rbRHSim1.isSelected()) {
            terraplanagemCadastro.setRELATORIO_RECURSOS_HIDRICOS_1(1);
        }

        terraplanagemCadastro.setRELATORIO_R_H_NOME_1(tfRHNome1.getText());
        terraplanagemCadastro.setRELATORIO_R_H_DISTANCIA_1(tfRHDistancia1.getText());
        if (rbBotaFora1Nao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA1(0);
        }
        if (rbBotaFora1Sim.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA1(1);
        }
        terraplanagemCadastro.setRELATORIO_BOTA_FORA_UTM_N1(tfBotaForaUtmE1.getText());
        terraplanagemCadastro.setRELATORIO_BOTA_FORA_UTM_E1(tfBotaForaUtmN1.getText());
        if (rbBotaForaOpcao1Nao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA_OPCAO_1(0);
        }
        if (rbBotaForaOpcao1Sim.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA_OPCAO_1(1);
        }
        if (rbBotaForaOpcao2Nao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA_OPCAO_2(0);
        }
        if (rbBotaForaOpcao2Sim.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA_OPCAO_2(1);
        }
        if (rbBotaForaOpcao3Nao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA_OPCAO_3(0);
        }
        if (rbBotaForaOpcao3Sim.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA_OPCAO_3(1);
        }
        if (rbBotaForaOpcao4Nao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA_OPCAO_4(0);
        }
        if (rbBotaForaOpcao4Sim.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA_OPCAO_4(1);
        }
        terraplanagemCadastro.setRELATORIO_BOTA_FORA_N_DA_UC1(tfBotaForaNDaUc1.getText());
        terraplanagemCadastro.setRELATORIO_BOTA_FORA_N_DA_UC2(tfBotaForaNDaUc2.getText());
        if (rbBotaFora2Nao.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA2(0);
        }
        if (rbBotaFora2Sim.isSelected()) {
            terraplanagemCadastro.setRELATORIO_BOTA_FORA2(1);
        }
        terraplanagemCadastro.setRELATORIO_BOTA_FORA_UTM_N2(tfBotaForaUtmN2.getText());
        terraplanagemCadastro.setRELATORIO_BOTA_FORA_UTM_E2(tfBotaForaUtmE2.getText());
        terraplanagemCadastro.setRELATORIO_BOTA_FORA_AREA_EMPRESTIMO(tfBotaForaAreaEmprestimo.getText());
        if (rbRHNao2.isSelected()) {
            terraplanagemCadastro.setRELATORIO_RECURSOS_HIDRICOS_2(0);
        }
        if (rbRHSim2.isSelected()) {
            terraplanagemCadastro.setRELATORIO_RECURSOS_HIDRICOS_2(1);
        }
        terraplanagemCadastro.setRELATORIO_R_H_NOME_2(tfRHNome2.getText());
        terraplanagemCadastro.setRELATORIO_R_H_DISTANCIA_2(tfRHDistancia2.getText());
        if (rbRHNao3.isSelected()) {
            terraplanagemCadastro.setRELATORIO_RECURSOS_HIDRICOS_3(0);
        }
        if (rbRHSim3.isSelected()) {
            terraplanagemCadastro.setRELATORIO_RECURSOS_HIDRICOS_3(1);
        }
        terraplanagemCadastro.setRELATORIO_R_H_NOME_3(tfRHNome3.getText());
        terraplanagemCadastro.setRELATORIO_R_H_DISTANCIA_3(tfRHDistancia3.getText());
        terraplanagemCadastro.setRELATORIO_ROTEIRO_DE_ACESSO(taRoteiroAcesso.getText());
        //terraplanagemCadastro.setRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO();
        if (rbCLENadamaisexisteadeclarar.isSelected()) {
            terraplanagemCadastro.setRELATORIO_CROQUI_PERGUNTA(0);
        }
        if (rbCLEDeclaramosoqueconstaemanexo.isSelected()) {
            terraplanagemCadastro.setRELATORIO_CROQUI_PERGUNTA(1);
        }
        terraplanagemCadastro.setRELATORIO_TEXTO_ANEXO(taTextoAnexo.getText());
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) dataAtual.getValue());
        terraplanagemCadastro.setRELATORIO_DATA_ATUAL(cal);
        terraplanagemCadastro.setPROFISSIONAL_ID(profissionalTemporario.getProfissional_id());
        terraplanagemCadastro.setREPRESENTANTE_ID(representanteTemporario.getRepresentante_id());
        terraplanagemCadastro.setCATEGORIA_ID(4);

        System.out.println("imagem fora do  if:" + imagem);
        if (imagem != null) {
            try {

                // TODO add your handling code here:
                String caminho = "build/classes/imagens/";
                System.out.println(caminho);
                SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                //System.out.println(d.format(new Date()));
                File outputfile = new File(caminho + "image" + d.format(new Date()) + ".jpg");
                terraplanagemCadastro.setRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO(outputfile.getName());
                System.out.println(outputfile.getName());
                ImageIO.write(imagem, "jpg", outputfile);
                //JOptionPane.showMessageDialog(rootPane, "Imagem enviada com sucesso");

                imagem = null;
                System.out.println("imagem dentro do  if:" + imagem);
            } catch (IOException ex) {
                Logger.getLogger(jifFormRestautante.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            terraplanagemCadastro.setRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO("sem_imagem.jpg");
            imagem = null;
        }
        return terraplanagemCadastro;

    }

    public void limparCamposCadastroTuristico() {

        CheckBoxFAA = "";
        CheckBoxFGE = "";
        CheckBoxGR = "";
        CheckBoxEA = "";
        tfRepresentante.setText("");
        tfAreaIntervencao.setText("");
        tfAlturaTalude.setText("");
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
        rbHaResidenciasSim.setSelected(false);
        rbHaResidenciasNao.setSelected(false);
        rbAreaDaUcSim.setSelected(false);
        tfAreaDaUcNDocumento.setText("");
        tfAreaDaUcNome.setText("");
        rbAreaDaUcNao.setSelected(false);
        rbSuprecaoVegetacaoSim.setSelected(false);
        rbSuprecaoVegetacaoNao.setSelected(false);
        tfCoordenadasUtmN.setText("");
        tfCoordenadasUtmE.setText("");
        rbISAPlanejamento.setSelected(false);
        rbISAInstalacao.setSelected(false);
        rbISAOperacao.setSelected(false);
        tfISAPrevisaoVegetacao.setText("");
        tfISAAreaTotalMovimentacaoTerra.setText("");
        tfISAAreaCorte.setText("");
        tfISAAreaAterro.setText("");
        tfISATaludesFormados.setText("");
        tfRHNome1.setText("");
        tfRHDistancia1.setText("");
        rbRHSim1.setSelected(false);
        rbRHNao1.setSelected(false);
        rbRHNao2.setSelected(false);
        rbRHSim2.setSelected(false);
        rbRHNao3.setSelected(false);
        rbRHSim3.setSelected(false);
        rbBotaFora1Nao.setSelected(false);
        rbBotaFora1Sim.setSelected(false);
        tfBotaForaUtmN1.setText("");
        tfBotaForaUtmE1.setText("");
        rbBotaForaOpcao1Nao.setSelected(false);
        rbBotaForaOpcao1Sim.setSelected(false);
        tfBotaForaNDaUc1.setText("");
        rbBotaForaOpcao2Sim.setSelected(false);
        rbBotaForaOpcao2Nao.setSelected(false);
        rbRHNao2.setSelected(false);
        rbRHSim2.setSelected(false);
        tfRHNome2.setText("");
        tfRHDistancia2.setText("");
        tfBotaForaUtmN2.setText("");
        tfBotaForaUtmE2.setText("");
        tfBotaForaAreaEmprestimo.setText("");
        rbBotaForaOpcao3Sim.setSelected(false);
        rbBotaForaOpcao3Nao.setSelected(false);
        tfBotaForaNDaUc2.setText("");
        rbRHNao3.setSelected(false);
        rbRHSim3.setSelected(false);
        tfRHNome3.setText("");
        tfRHDistancia3.setText("");
        tfNEmpregados.setText("");
        taRoteiroAcesso.setText("");
        rbCLENadamaisexisteadeclarar.setSelected(false);
        rbCLEDeclaramosoqueconstaemanexo.setSelected(false);
        taTextoAnexo.setText("");
        tfProfissional.setText("");
        Date d = new Date();
        dataAtual.setValue(d);
        imagem = null;
        lblImagem.setIcon(new ImageIcon(""));

        tpnAbasTerraplanagem.setSelectedIndex(1); // Mudando para a PRIMEIRA aba

        tpnAbasTerraplanagem.setTitleAt(0, "Cadastrar Relatório Terraplanagem");
        btFinalizarCadastro.setToolTipText("Cadastrar");
        tpnAbasTerraplanagem.setEnabledAt(1, true);
        tpnAbasTerraplanagem.setSelectedIndex(1);
        btCancelarAtualizacao.setSelected(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpnAbasTerraplanagem = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnTerraplanagem = new javax.swing.JPanel();
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
        rbSuprecaoVegetacaoNao = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        tfCoordenadasUtmN = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        tfCoordenadasUtmE = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        rbISAPlanejamento = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        rbISAInstalacao = new javax.swing.JRadioButton();
        rbISAOperacao = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        tfNEmpregados = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tfISAPrevisaoVegetacao = new javax.swing.JTextField();
        tfISAData = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        tfISAAreaTotalMovimentacaoTerra = new javax.swing.JTextField();
        tfISATaludesFormados = new javax.swing.JTextField();
        tfISAAreaCorte = new javax.swing.JTextField();
        tfISAAreaAterro = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        btCancelarAtualizacao = new javax.swing.JButton();
        btFinalizarCadastro = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        tfRepresentante = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        tfAreaIntervencao = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        tfAlturaTalude = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        rbRHNao1 = new javax.swing.JRadioButton();
        rbRHSim1 = new javax.swing.JRadioButton();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        tfRHDistancia1 = new javax.swing.JTextField();
        tfRHNome1 = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        rbBotaFora1Nao = new javax.swing.JRadioButton();
        rbBotaFora1Sim = new javax.swing.JRadioButton();
        jLabel88 = new javax.swing.JLabel();
        tfBotaForaUtmN1 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        tfBotaForaUtmE1 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        tfBotaForaNDaUc1 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        rbBotaForaOpcao2Sim = new javax.swing.JRadioButton();
        rbBotaForaOpcao2Nao = new javax.swing.JRadioButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        rbRHNao2 = new javax.swing.JRadioButton();
        rbRHSim2 = new javax.swing.JRadioButton();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        tfRHDistancia2 = new javax.swing.JTextField();
        tfRHNome2 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        rbBotaFora2Nao = new javax.swing.JRadioButton();
        rbBotaFora2Sim = new javax.swing.JRadioButton();
        jLabel97 = new javax.swing.JLabel();
        tfBotaForaUtmN2 = new javax.swing.JTextField();
        tfBotaForaUtmE2 = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        rbBotaForaOpcao3Sim = new javax.swing.JRadioButton();
        jLabel99 = new javax.swing.JLabel();
        tfBotaForaNDaUc2 = new javax.swing.JTextField();
        rbBotaForaOpcao3Nao = new javax.swing.JRadioButton();
        jLabel100 = new javax.swing.JLabel();
        rbBotaForaOpcao4Sim = new javax.swing.JRadioButton();
        rbBotaForaOpcao4Nao = new javax.swing.JRadioButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        rbRHNao3 = new javax.swing.JRadioButton();
        rbRHSim3 = new javax.swing.JRadioButton();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        tfRHDistancia3 = new javax.swing.JTextField();
        tfRHNome3 = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        tfBotaForaAreaEmprestimo = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        rbBotaForaOpcao1Sim = new javax.swing.JRadioButton();
        rbBotaForaOpcao1Nao = new javax.swing.JRadioButton();
        pnCROQUI = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblImagem = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        tfPalavraChaveTuristico = new javax.swing.JTextField();
        ckbCliente = new javax.swing.JCheckBox();
        ckbProfissional = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbRelatoriosCadastrados = new javax.swing.JTable();
        btExcluirProfissional = new javax.swing.JButton();
        btEditarProfissional = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Terraplanagem");
        setPreferredSize(new java.awt.Dimension(800, 600));

        tpnAbasTerraplanagem.setPreferredSize(new java.awt.Dimension(871, 3313));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(873, 3283));

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

        jLabel6.setText("CPF/CNPJ:");

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

        jLabel16.setText("Em caso de instalação da atividade, haverá  supressão de vegetação?");

        rbSuprecaoVegetacaoSim.setText("Sim");
        rbSuprecaoVegetacaoSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbSuprecaoVegetacaoSimMouseClicked(evt);
            }
        });

        rbSuprecaoVegetacaoNao.setText("Não");
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
                .addComponent(rbSuprecaoVegetacaoNao)
                .addContainerGap())
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

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rbISAPlanejamento.setText("Planejamento");
        rbISAPlanejamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbISAPlanejamentoMouseClicked(evt);
            }
        });

        jLabel24.setText("Fase do empreendimento:");

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

        jLabel25.setText("N° de funcionários:");

        jLabel23.setText("Data de início da atividade:");

        jLabel22.setText("Previsão de início da operação:");

        tfISAData.setEditable(false);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(rbISAPlanejamento)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbISAInstalacao)
                                    .addComponent(rbISAOperacao))
                                .addGap(109, 109, 109)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23)))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfNEmpregados, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfISAPrevisaoVegetacao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfISAData, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbISAPlanejamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbISAInstalacao)
                    .addComponent(jLabel22)
                    .addComponent(tfISAPrevisaoVegetacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbISAOperacao)
                    .addComponent(jLabel23)
                    .addComponent(tfISAData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(tfNEmpregados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setText("<html>Caso seja necessário destinar volume excedente ou seja necessário importar material, utilizar os quadros relativos a  área de bota-fora e área<br>de empréstimo, respectivamente.</html>");

        jLabel73.setText("Área Total de movimentação de terra:");

        jLabel74.setText("Área de corte:");

        jLabel75.setText("Área de aterro:");

        jLabel76.setText("Altura máxima dos Taludes formados:");

        jLabel77.setText("OBS. Caso haja excedente, referenciar área de bota-fora. Caso haja déficit, referenciar área de empréstimo.");

        jLabel78.setText(" m². (Somar as áreas de terra movimentada)");

        jLabel79.setText("m². (Origem do material)");

        jLabel80.setText("m². (Destino do material)");

        jLabel81.setText("metros.");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75)
                            .addComponent(jLabel76))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfISAAreaTotalMovimentacaoTerra, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(tfISAAreaCorte)
                            .addComponent(tfISAAreaAterro)
                            .addComponent(tfISATaludesFormados))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78)
                            .addComponent(jLabel79)
                            .addComponent(jLabel80)
                            .addComponent(jLabel81))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(tfISAAreaTotalMovimentacaoTerra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(tfISAAreaCorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(tfISAAreaAterro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(tfISATaludesFormados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel77)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btCancelarAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btFinalizarCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancelarAtualizacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFinalizarCadastro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel66.setText("Representante:");

        tfRepresentante.setEditable(false);
        tfRepresentante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRepresentanteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(tfProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
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
                        .addGap(0, 361, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel67)
                    .addComponent(pnDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel66)
                        .addComponent(tfRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel68)
                        .addComponent(tfProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("<html>Terraplanagem, corte, aterro, áreas de empréstimo e, ou bota-fora em áreas urbanas (taludes 3 a 6 metros de altura com área de intervenção<br>entre 1.000 e 10.000 m2).</html>");

        jLabel69.setText("Área de intervenção:");

        jLabel70.setText("m²");

        jLabel71.setText(" Altura do talude:");

        jLabel72.setText("Metros.");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAreaIntervencao, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel70)
                        .addGap(210, 210, 210)
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAlturaTalude, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel72)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(tfAreaIntervencao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70)
                    .addComponent(jLabel71)
                    .addComponent(tfAlturaTalude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RECUROS  HÍDRICOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel82.setText("Existe Corpo Hídrico próximo da área?");

        rbRHNao1.setText("Não");
        rbRHNao1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbRHNao1MouseClicked(evt);
            }
        });

        rbRHSim1.setText("Sim");
        rbRHSim1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbRHSim1MouseClicked(evt);
            }
        });

        jLabel83.setText("Nome do Corpo Hídrico:");

        jLabel84.setText("Distância do local da atividade:");

        jLabel85.setText("metros");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRHNao1)
                    .addComponent(jLabel82)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(rbRHSim1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel84)
                            .addComponent(jLabel83))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(tfRHDistancia1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel85))
                            .addComponent(tfRHNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbRHNao1)
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRHSim1)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(tfRHNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel84)
                            .addComponent(tfRHDistancia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel85)))))
        );

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BOTA-FORA E ÁREA DE EMPRÉSTIMO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel87.setText("Haverá necessidade de área de Bota-Fora?");

        rbBotaFora1Nao.setText("Não");
        rbBotaFora1Nao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaFora1NaoMouseClicked(evt);
            }
        });

        rbBotaFora1Sim.setText("Sim");
        rbBotaFora1Sim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaFora1SimMouseClicked(evt);
            }
        });

        jLabel88.setText("Coordenada do local: UTM(N):");

        tfBotaForaUtmN1.setEditable(false);

        jLabel89.setText("e UTM(E):");

        tfBotaForaUtmE1.setEditable(false);

        jLabel90.setText(" n o do documento referente `a anuência da UC");

        jLabel91.setText("O local onde será disposto o material demanda supressão vegetacional:");

        rbBotaForaOpcao2Sim.setText("Sim");
        rbBotaForaOpcao2Sim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaForaOpcao2SimMouseClicked(evt);
            }
        });

        rbBotaForaOpcao2Nao.setText("Não");
        rbBotaForaOpcao2Nao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaForaOpcao2NaoMouseClicked(evt);
            }
        });

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RECUROS  HÍDRICOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel92.setText("Existe Corpo Hídrico próximo da área?");

        rbRHNao2.setText("Não");
        rbRHNao2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbRHNao2MouseClicked(evt);
            }
        });

        rbRHSim2.setText("Sim");
        rbRHSim2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbRHSim2MouseClicked(evt);
            }
        });

        jLabel93.setText("Nome do Corpo Hídrico:");

        jLabel94.setText("Distância do local da atividade:");

        jLabel95.setText("metros");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRHNao2)
                    .addComponent(jLabel92)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(rbRHSim2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel94)
                            .addComponent(jLabel93))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(tfRHDistancia2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel95))
                            .addComponent(tfRHNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbRHNao2)
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRHSim2)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel93)
                            .addComponent(tfRHNome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel94)
                            .addComponent(tfRHDistancia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95)))))
        );

        jLabel96.setText("Haverá necessidade de áreas de empréstimo para complementação do serviço de Terraplenagem?");

        rbBotaFora2Nao.setText("Não");
        rbBotaFora2Nao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaFora2NaoMouseClicked(evt);
            }
        });

        rbBotaFora2Sim.setText("Sim");
        rbBotaFora2Sim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaFora2SimMouseClicked(evt);
            }
        });

        jLabel97.setText("Coordenada do local: UTM(N):");

        tfBotaForaUtmN2.setEditable(false);

        tfBotaForaUtmE2.setEditable(false);

        jLabel98.setText("e UTM(E):");

        rbBotaForaOpcao3Sim.setText("Sim:");

        jLabel99.setText(" n o do documento referente `a anuência da UC");

        rbBotaForaOpcao3Nao.setText("Não");

        jLabel100.setText("O local onde será disposto o material demanda supressão vegetacional:");

        rbBotaForaOpcao4Sim.setText("Sim");
        rbBotaForaOpcao4Sim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaForaOpcao4SimMouseClicked(evt);
            }
        });

        rbBotaForaOpcao4Nao.setText("Não");
        rbBotaForaOpcao4Nao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaForaOpcao4NaoMouseClicked(evt);
            }
        });

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RECUROS  HÍDRICOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel101.setText("Existe Corpo Hídrico próximo da área?");

        rbRHNao3.setText("Não");
        rbRHNao3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbRHNao3MouseClicked(evt);
            }
        });

        rbRHSim3.setText("Sim");
        rbRHSim3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbRHSim3MouseClicked(evt);
            }
        });

        jLabel102.setText("Nome do Corpo Hídrico:");

        jLabel103.setText("Distância do local da atividade:");

        jLabel104.setText("metros");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRHNao3)
                    .addComponent(jLabel101)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(rbRHSim3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103)
                            .addComponent(jLabel102))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(tfRHDistancia3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel104))
                            .addComponent(tfRHNome3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbRHNao3)
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRHSim3)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel102)
                            .addComponent(tfRHNome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel103)
                            .addComponent(tfRHDistancia3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel104)))))
        );

        jLabel105.setText("Área estimada de Empréstimo:");

        jLabel106.setText("m²");

        jLabel107.setText("<html>O local onde será disposto o material de Bota-Fora está em Unidade de<br>Conservação:</html>");

        rbBotaForaOpcao1Sim.setText("Sim");
        rbBotaForaOpcao1Sim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaForaOpcao1SimMouseClicked(evt);
            }
        });

        rbBotaForaOpcao1Nao.setText("Não");
        rbBotaForaOpcao1Nao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbBotaForaOpcao1NaoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(rbBotaFora1Sim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(rbBotaForaOpcao1Sim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel90))
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfBotaForaNDaUc1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel28Layout.createSequentialGroup()
                                        .addComponent(jLabel88)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfBotaForaUtmN1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel89)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfBotaForaUtmE1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel91)
                            .addComponent(rbBotaForaOpcao2Sim)
                            .addComponent(rbBotaForaOpcao2Nao)
                            .addComponent(rbBotaForaOpcao1Nao))
                        .addGap(38, 38, 38))
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(rbBotaFora2Sim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(rbBotaForaOpcao3Sim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel99))
                            .addComponent(rbBotaForaOpcao3Nao)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel97)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfBotaForaUtmN2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel98)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfBotaForaUtmE2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel100)
                            .addComponent(rbBotaForaOpcao4Sim)
                            .addComponent(rbBotaForaOpcao4Nao)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel105)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfBotaForaAreaEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel106))
                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfBotaForaNDaUc2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbBotaFora1Nao)
                            .addComponent(jLabel87)
                            .addComponent(rbBotaFora2Nao)
                            .addComponent(jLabel96))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbBotaFora1Nao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbBotaFora1Sim)
                    .addComponent(jLabel88)
                    .addComponent(tfBotaForaUtmN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89)
                    .addComponent(tfBotaForaUtmE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(rbBotaForaOpcao1Sim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBotaForaNDaUc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbBotaForaOpcao1Nao)
                .addGap(13, 13, 13)
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbBotaForaOpcao2Sim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbBotaForaOpcao2Nao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbBotaFora2Nao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbBotaFora2Sim)
                    .addComponent(jLabel97)
                    .addComponent(tfBotaForaUtmN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel98)
                    .addComponent(tfBotaForaUtmE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105)
                    .addComponent(tfBotaForaAreaEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel106))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbBotaForaOpcao3Sim)
                    .addComponent(jLabel99))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBotaForaNDaUc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbBotaForaOpcao3Nao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel100)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbBotaForaOpcao4Sim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbBotaForaOpcao4Nao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnCROQUI.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CROQUI DE LOCALIZAÇÃO DO EMPREENDIMENTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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
                        .addGap(0, 0, Short.MAX_VALUE))))
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

        javax.swing.GroupLayout pnTerraplanagemLayout = new javax.swing.GroupLayout(pnTerraplanagem);
        pnTerraplanagem.setLayout(pnTerraplanagemLayout);
        pnTerraplanagemLayout.setHorizontalGroup(
            pnTerraplanagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTerraplanagemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTerraplanagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnCROQUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 147, Short.MAX_VALUE))
        );
        pnTerraplanagemLayout.setVerticalGroup(
            pnTerraplanagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTerraplanagemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnCROQUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jScrollPane1.setViewportView(pnTerraplanagem);

        tpnAbasTerraplanagem.addTab("Cadastrar Relatório de Terraplanagem", jScrollPane1);

        jPanel23.setPreferredSize(new java.awt.Dimension(871, 563));

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro de Busca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel86.setText("Palavra-chave:");

        tfPalavraChaveTuristico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPalavraChaveTuristicoKeyReleased(evt);
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
                .addComponent(tfPalavraChaveTuristico)
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
                    .addComponent(tfPalavraChaveTuristico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/printer-128.png"))); // NOI18N
        btnImprimir.setToolTipText("Editar");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setPreferredSize(new java.awt.Dimension(150, 150));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
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
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(btExcluirProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154)
                        .addComponent(btEditarProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExcluirProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditarProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tpnAbasTerraplanagem.addTab("Gerenciar Relatórios de Terraplanagem", jPanel23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnAbasTerraplanagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnAbasTerraplanagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        setBounds(0, 0, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNomeClienteMouseClicked
        TelaRelatorioCliente telaRelatorioCliente = new TelaRelatorioCliente(null, true, 4);
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
            tfBotaForaUtmN1.setText(clienteSelecionado.getCliente_utmn());
            tfBotaForaUtmN2.setText(clienteSelecionado.getCliente_utmn());
            tfBotaForaUtmE1.setText(clienteSelecionado.getCliente_utme());
            tfBotaForaUtmE2.setText(clienteSelecionado.getCliente_utme());
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
            DecimalFormat form = new DecimalFormat("00");
            String dataInicioAtividade;
            dataInicioAtividade = form.format(clienteSelecionado.getCliente_data_atividade().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(clienteSelecionado.getCliente_data_atividade().get(Calendar.MONTH) + 1) + "/" + clienteSelecionado.getCliente_data_atividade().get(Calendar.YEAR);
            tfISAData.setText(dataInicioAtividade);
            clienteTemporario = telaRelatorioCliente.retornarClienteSelecionado();
        }
    }//GEN-LAST:event_tfNomeClienteMouseClicked

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

    private void tfPalavraChaveTuristicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPalavraChaveTuristicoKeyReleased
        // TODO add your handling code here:
        if (tfPalavraChaveTuristico.getText().isEmpty()) {
            modeloTabelaTerraplanagem.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(4));
        } else {
            modeloTabelaTerraplanagem.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(4));
            listaOriginalTemporariaRelatorio.clear();

            if (ckbCliente.isSelected()) {
                for (int i = 0; i < modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().size(); i++) {
                    if (modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().get(i).getCLIENTE_NOME().toLowerCase().contains(tfPalavraChaveTuristico.getText().toLowerCase())) {
                        listaOriginalTemporariaRelatorio.add(modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().get(i));
                    }
                }
            } else if (ckbProfissional.isSelected()) {
                for (int i = 0; i < modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().size(); i++) {
                    if (modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().get(i).getPROFISSIONAL_NOME().toLowerCase().contains(tfPalavraChaveTuristico.getText().toLowerCase())) {
                        listaOriginalTemporariaRelatorio.add(modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().get(i));
                    }
                }
            }

            modeloTabelaTerraplanagem.inserirListaRelatorioPrincipal(listaOriginalTemporariaRelatorio);

        }

        tbRelatoriosCadastrados.updateUI();
    }//GEN-LAST:event_tfPalavraChaveTuristicoKeyReleased

    private void btCancelarAtualizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarAtualizacaoActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(rootPane, "Deseja mesmo cancelar as alterações", "Aviso", 0) == 0) {
            btFinalizarCadastro.setToolTipText("Cadastrar");
            btCancelarAtualizacao.setSelected(false);
            //tpnAbasRequerimentos.setEnabledAt(1, true);
            //tpnAbasRequerimentos.setSelectedIndex(1);
            //tpnAbasRequerimentos.setTitleAt(0, "Gerar Requerimento");
            CheckBoxFAA = "";
            CheckBoxFGE = "";
            CheckBoxGR = "";
            CheckBoxEA = "";
            tfRepresentante.setText("");
            tfAreaIntervencao.setText("");
            tfAlturaTalude.setText("");
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
            rbHaResidenciasSim.setSelected(false);
            rbHaResidenciasNao.setSelected(false);
            rbAreaDaUcSim.setSelected(false);
            tfAreaDaUcNDocumento.setText("");
            tfAreaDaUcNome.setText("");
            rbAreaDaUcNao.setSelected(false);
            rbSuprecaoVegetacaoSim.setSelected(false);
            rbSuprecaoVegetacaoNao.setSelected(false);
            tfCoordenadasUtmN.setText("");
            tfCoordenadasUtmE.setText("");
            rbISAPlanejamento.setSelected(false);
            rbISAInstalacao.setSelected(false);
            rbISAOperacao.setSelected(false);
            tfISAPrevisaoVegetacao.setText("");
            tfISAAreaTotalMovimentacaoTerra.setText("");
            tfISAAreaCorte.setText("");
            tfISAAreaAterro.setText("");
            tfISATaludesFormados.setText("");
            tfRHNome1.setText("");
            tfRHDistancia1.setText("");
            rbRHSim1.setSelected(false);
            rbRHNao1.setSelected(false);
            rbRHNao2.setSelected(false);
            rbRHSim2.setSelected(false);
            rbRHNao3.setSelected(false);
            rbRHSim3.setSelected(false);
            rbBotaFora1Nao.setSelected(false);
            rbBotaFora1Sim.setSelected(false);
            tfBotaForaUtmN1.setText("");
            tfBotaForaUtmE1.setText("");
            rbBotaForaOpcao1Nao.setSelected(false);
            rbBotaForaOpcao1Sim.setSelected(false);
            tfBotaForaNDaUc1.setText("");
            rbBotaForaOpcao2Sim.setSelected(false);
            rbBotaForaOpcao2Nao.setSelected(false);
            rbBotaFora2Nao.setSelected(false);
            rbBotaFora2Sim.setSelected(false);
            rbBotaForaOpcao4Sim.setSelected(false);
            rbBotaForaOpcao4Nao.setSelected(false);
            rbBotaFora2Nao.setSelected(false);
            rbBotaFora2Sim.setSelected(false);
            rbBotaForaOpcao4Sim.setSelected(false);
            rbBotaForaOpcao4Nao.setSelected(false);
            rbRHNao2.setText("");
            rbRHSim2.setText("");
            tfRHNome2.setText("");
            tfRHDistancia2.setText("");
            tfBotaForaUtmN2.setText("");
            tfBotaForaUtmE2.setText("");
            tfBotaForaAreaEmprestimo.setText("");
            rbBotaForaOpcao3Sim.setSelected(false);
            rbBotaForaOpcao3Nao.setSelected(false);
            tfBotaForaNDaUc2.setText("");
            rbRHNao3.setText("");
            rbRHSim3.setText("");
            tfRHNome3.setText("");
            tfRHDistancia3.setText("");
            tfNEmpregados.setText("");
            taRoteiroAcesso.setText("");
            rbCLENadamaisexisteadeclarar.setSelected(false);
            rbCLEDeclaramosoqueconstaemanexo.setSelected(false);
            taTextoAnexo.setText("");
            tfProfissional.setText("");
            Date d = new Date();
            dataAtual.setValue(d);
            imagem = null;
            lblImagem.setIcon(new ImageIcon(""));
            tpnAbasTerraplanagem.setSelectedIndex(1); // Mudando para a PRIMEIRA aba

            tpnAbasTerraplanagem.setTitleAt(0, "Cadastrar Relatório Terraplanagem");
            btFinalizarCadastro.setToolTipText("Cadastrar");
            tpnAbasTerraplanagem.setEnabledAt(1, true);
            btCancelarAtualizacao.setSelected(false);
        }
    }//GEN-LAST:event_btCancelarAtualizacaoActionPerformed

    private void btFinalizarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarCadastroActionPerformed
        // TODO add your handling code here:
        if (tfNomeCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tpnAbasTerraplanagem, "Por favor escolha o cliente", "Aviso", 2);
            tfNomeCliente.requestFocus();
        } else {
            if (tfProfissional.getText().isEmpty()) {
                JOptionPane.showMessageDialog(tpnAbasTerraplanagem, "Por favor escolha o profissional", "Aviso", 2);
                tfProfissional.requestFocus();
            } else {
                if (tfRepresentante.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(tpnAbasTerraplanagem, "Por favor escolha o represetante", "Aviso", 2);
                    tfRepresentante.requestFocus();
                } else {
                    if (btFinalizarCadastro.getToolTipText().equals("Cadastrar")) {
                        conexaoTabelaRelatorio.inserirNovoRPrincipal(preencherDadosCadastroTuristico());
                        buscarRelatoriosTabela();
                        limparCamposCadastroTuristico();
                    } else {
                        conexaoTabelaRelatorio.alterarRelatorio(modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow()).getRELATORIO_ID(), preencherDadosCadastroTuristico());
                        buscarRelatoriosTabela();
                        limparCamposCadastroTuristico();
                    }
                }
            }
        }

    }//GEN-LAST:event_btFinalizarCadastroActionPerformed

    private void btExcluirProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirProfissionalActionPerformed
        // TODO add your handling code here:
        if (tbRelatoriosCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatórios selecionados para serem deletados");
        } else if (tbRelatoriosCadastrados.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Não existem mais relatórios para serem deletados");
        } else if (JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir esse relatorio?", "Excluir cliente", 0) == 0) {
            if (tbRelatoriosCadastrados.getSelectedRow() != -1) {

                relatorioExcluir = modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow());
                if (!"sem_imagem.jpg".equals(relatorioExcluir.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO())) {
                    File file = new File("build/classes/imagens/" + relatorioExcluir.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO());
                    file.delete();
                }

                conexaoTabelaRelatorio.removerRelatorio(modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow()).getRELATORIO_ID());
                modeloTabelaTerraplanagem.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(4));

                tbRelatoriosCadastrados.updateUI();
            }
        }
    }//GEN-LAST:event_btExcluirProfissionalActionPerformed

    private void btEditarProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarProfissionalActionPerformed
        // TODO add your handling code here:
        if (tbRelatoriosCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem editados");
        } else {

            RelatorioPrincipal relatorioPrincipal = modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow());

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
            tfBotaForaUtmN1.setText(relatorioPrincipal.getCLIENTE_UTMN());
            tfBotaForaUtmN2.setText(relatorioPrincipal.getCLIENTE_UTMN());
            tfBotaForaUtmE1.setText(relatorioPrincipal.getCLIENTE_UTME());
            tfBotaForaUtmE2.setText(relatorioPrincipal.getCLIENTE_UTME());
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
            DecimalFormat form = new DecimalFormat("00");
            String dataInicioAtividade;
            dataInicioAtividade = form.format(relatorioPrincipal.getCLIENTE_DATA().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(relatorioPrincipal.getCLIENTE_DATA().get(Calendar.MONTH) + 1) + "/" + relatorioPrincipal.getCLIENTE_DATA().get(Calendar.YEAR);
            tfISAData.setText(dataInicioAtividade);
            clienteTemporario.setCliente_id(relatorioPrincipal.getCLIENTE_ID());
            tfAreaIntervencao.setText(relatorioPrincipal.getRELATORIO_AREA_INTERVENCAO());
            tfAlturaTalude.setText(relatorioPrincipal.getRELATORIO_ALTURA_DO_TALUDE());
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
            tfISAPrevisaoVegetacao.setText(relatorioPrincipal.getRELATORIO_I_S_A_PREVISAO_OPERACAO());
            tfNEmpregados.setText(relatorioPrincipal.getRELATORIO_I_S_A_N_EMPREGADOS());
            tfISAAreaTotalMovimentacaoTerra.setText(relatorioPrincipal.getRELATORIO_I_S_A_AREA_TOTAL_MOVIMENTACAO_TERRA());
            tfISAAreaCorte.setText(relatorioPrincipal.getRELATORIO_I_S_A_AREA_DE_CORTE());
            tfISAAreaAterro.setText(relatorioPrincipal.getRELATORIO_I_S_A_AREA_DE_ATERRO());
            tfISATaludesFormados.setText(relatorioPrincipal.getRELATORIO_I_S_A_TALUDES_FORMADOS());

            if (relatorioPrincipal.getRELATORIO_RECURSOS_HIDRICOS_1() == 0) {
                rbRHNao1.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_RECURSOS_HIDRICOS_1() == 1) {
                rbRHSim1.setSelected(true);
            }

            tfRHNome1.setText(relatorioPrincipal.getRELATORIO_R_H_NOME_1());
            tfRHDistancia1.setText(relatorioPrincipal.getRELATORIO_R_H_DISTANCIA_1());

            if (relatorioPrincipal.getRELATORIO_BOTA_FORA1() == 0) {
                rbBotaFora1Nao.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_BOTA_FORA1() == 1) {
                rbBotaFora1Sim.setSelected(true);
            }
            tfBotaForaUtmE1.setText(relatorioPrincipal.getRELATORIO_BOTA_FORA_UTM_N1());
            tfBotaForaUtmN1.setText(relatorioPrincipal.getRELATORIO_BOTA_FORA_UTM_E1());

            if (relatorioPrincipal.getRELATORIO_BOTA_FORA_OPCAO_1() == 0) {
                rbBotaForaOpcao1Nao.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_BOTA_FORA_OPCAO_1() == 1) {
                rbBotaForaOpcao1Sim.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_BOTA_FORA_OPCAO_2() == 0) {
                rbBotaForaOpcao2Nao.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_BOTA_FORA_OPCAO_2() == 1) {
                rbBotaForaOpcao2Sim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_BOTA_FORA_OPCAO_3() == 0) {
                rbBotaForaOpcao3Nao.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_BOTA_FORA_OPCAO_3() == 1) {
                rbBotaForaOpcao3Sim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_BOTA_FORA_OPCAO_4() == 0) {
                rbBotaForaOpcao4Nao.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_BOTA_FORA_OPCAO_4() == 1) {
                rbBotaForaOpcao4Sim.setSelected(true);
            }
            tfBotaForaNDaUc1.setText(relatorioPrincipal.getRELATORIO_BOTA_FORA_N_DA_UC1());
            tfBotaForaNDaUc2.setText(relatorioPrincipal.getRELATORIO_BOTA_FORA_N_DA_UC2());
            if (relatorioPrincipal.getRELATORIO_BOTA_FORA2() == 0) {
                rbBotaFora2Nao.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_BOTA_FORA2() == 1) {
                rbBotaFora2Sim.setSelected(true);
            }
            tfBotaForaUtmN2.setText(relatorioPrincipal.getRELATORIO_BOTA_FORA_UTM_N2());
            tfBotaForaUtmE2.setText(relatorioPrincipal.getRELATORIO_BOTA_FORA_UTM_E2());
            tfBotaForaAreaEmprestimo.setText(relatorioPrincipal.getRELATORIO_BOTA_FORA_AREA_EMPRESTIMO());

            if (relatorioPrincipal.getRELATORIO_RECURSOS_HIDRICOS_2() == 0) {
                rbRHNao2.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_RECURSOS_HIDRICOS_2() == 1) {
                rbRHSim2.setSelected(true);
            }
            tfRHNome2.setText(relatorioPrincipal.getRELATORIO_R_H_NOME_2());
            tfRHDistancia2.setText(relatorioPrincipal.getRELATORIO_R_H_DISTANCIA_2());

            if (relatorioPrincipal.getRELATORIO_RECURSOS_HIDRICOS_3() == 0) {
                rbRHNao3.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_RECURSOS_HIDRICOS_3() == 1) {
                rbRHSim3.setSelected(true);
            }
            tfRHNome3.setText(relatorioPrincipal.getRELATORIO_R_H_NOME_3());
            tfRHDistancia3.setText(relatorioPrincipal.getRELATORIO_R_H_DISTANCIA_3());

            taRoteiroAcesso.setText(relatorioPrincipal.getRELATORIO_ROTEIRO_DE_ACESSO());
            //relatorioPrincipal.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO();

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
            representanteTemporario.setRepresentante_id(relatorioPrincipal.getREPRESENTANTE_ID());
            tfRepresentante.setText(relatorioPrincipal.getREPRESENTANTE_NOME());
            relatorioPrincipal.setCATEGORIA_ID(4);

            try {
                String caminho = "build/classes/imagens/";

                File outputfile = new File(caminho + relatorioPrincipal.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO());
                imagem = ManipularImagem.setImagemDimensao(outputfile.getAbsolutePath(), 600, 600);

                lblImagem.setIcon(new ImageIcon(imagem));

            } catch (Exception ex) {
                // System.out.println(ex.printStackTrace().toString());
            }

            tpnAbasTerraplanagem.setSelectedIndex(0); // Mudando para a PRIMEIRA aba

            btFinalizarCadastro.setToolTipText("Atualizar");
            tpnAbasTerraplanagem.setTitleAt(0, "Atualizar dados");
            tpnAbasTerraplanagem.setEnabledAt(1, false);
            btCancelarAtualizacao.setVisible(true);

        }
    }//GEN-LAST:event_btEditarProfissionalActionPerformed

    private void rbLocalizacaoZonaUrbanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbLocalizacaoZonaUrbanaMouseClicked
        rbLocalizacaoZonaRural.setSelected(false);
    }//GEN-LAST:event_rbLocalizacaoZonaUrbanaMouseClicked

    private void rbLocalizacaoZonaRuralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbLocalizacaoZonaRuralMouseClicked
        rbLocalizacaoZonaUrbana.setSelected(false);
    }//GEN-LAST:event_rbLocalizacaoZonaRuralMouseClicked

    private void rbInseridoEmAreaIndustrialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaIndustrialMouseClicked
        rbInseridoEmAreaResidencial.setSelected(false);
        rbInseridoEmAreaComercial.setSelected(false);
        rbInseridoEmAreaMista.setSelected(false);
        rbInseridoEmAreaOutra.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaIndustrialMouseClicked

    private void rbInseridoEmAreaResidencialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaResidencialMouseClicked
        rbInseridoEmAreaIndustrial.setSelected(false);
        rbInseridoEmAreaComercial.setSelected(false);
        rbInseridoEmAreaMista.setSelected(false);
        rbInseridoEmAreaOutra.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaResidencialMouseClicked

    private void rbInseridoEmAreaComercialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaComercialMouseClicked
        rbInseridoEmAreaResidencial.setSelected(false);
        rbInseridoEmAreaIndustrial.setSelected(false);
        rbInseridoEmAreaMista.setSelected(false);
        rbInseridoEmAreaOutra.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaComercialMouseClicked

    private void rbInseridoEmAreaMistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaMistaMouseClicked
        rbInseridoEmAreaResidencial.setSelected(false);
        rbInseridoEmAreaComercial.setSelected(false);
        rbInseridoEmAreaIndustrial.setSelected(false);
        rbInseridoEmAreaOutra.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaMistaMouseClicked

    private void rbInseridoEmAreaOutraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbInseridoEmAreaOutraMouseClicked
        rbInseridoEmAreaResidencial.setSelected(false);
        rbInseridoEmAreaComercial.setSelected(false);
        rbInseridoEmAreaMista.setSelected(false);
        rbInseridoEmAreaIndustrial.setSelected(false);
    }//GEN-LAST:event_rbInseridoEmAreaOutraMouseClicked

    private void rbHaResidenciasSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbHaResidenciasSimMouseClicked
        rbHaResidenciasNao.setSelected(false);
    }//GEN-LAST:event_rbHaResidenciasSimMouseClicked

    private void rbHaResidenciasNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbHaResidenciasNaoMouseClicked
        rbHaResidenciasSim.setSelected(false);
    }//GEN-LAST:event_rbHaResidenciasNaoMouseClicked

    private void rbAreaDaUcSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbAreaDaUcSimMouseClicked
        rbAreaDaUcNao.setSelected(false);
    }//GEN-LAST:event_rbAreaDaUcSimMouseClicked

    private void rbAreaDaUcNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbAreaDaUcNaoMouseClicked
        rbAreaDaUcSim.setSelected(false);
    }//GEN-LAST:event_rbAreaDaUcNaoMouseClicked

    private void rbSuprecaoVegetacaoSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSuprecaoVegetacaoSimMouseClicked
        rbSuprecaoVegetacaoNao.setSelected(false);
    }//GEN-LAST:event_rbSuprecaoVegetacaoSimMouseClicked

    private void rbSuprecaoVegetacaoNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSuprecaoVegetacaoNaoMouseClicked
        rbSuprecaoVegetacaoSim.setSelected(false);
    }//GEN-LAST:event_rbSuprecaoVegetacaoNaoMouseClicked

    private void rbISAPlanejamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbISAPlanejamentoMouseClicked
        rbISAInstalacao.setSelected(false);
        rbISAOperacao.setSelected(false);
    }//GEN-LAST:event_rbISAPlanejamentoMouseClicked

    private void rbISAInstalacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbISAInstalacaoMouseClicked
        rbISAPlanejamento.setSelected(false);
        rbISAOperacao.setSelected(false);
    }//GEN-LAST:event_rbISAInstalacaoMouseClicked

    private void rbISAOperacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbISAOperacaoMouseClicked
        rbISAInstalacao.setSelected(false);
        rbISAPlanejamento.setSelected(false);
    }//GEN-LAST:event_rbISAOperacaoMouseClicked

    private void rbRHSim1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbRHSim1MouseClicked
        rbRHNao1.setSelected(false);
    }//GEN-LAST:event_rbRHSim1MouseClicked

    private void rbRHNao1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbRHNao1MouseClicked
        rbRHSim1.setSelected(false);
    }//GEN-LAST:event_rbRHNao1MouseClicked

    private void rbBotaFora1SimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaFora1SimMouseClicked
        rbBotaFora1Nao.setSelected(false);
    }//GEN-LAST:event_rbBotaFora1SimMouseClicked

    private void rbBotaFora1NaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaFora1NaoMouseClicked
        rbBotaFora1Sim.setSelected(false);
    }//GEN-LAST:event_rbBotaFora1NaoMouseClicked

    private void rbBotaForaOpcao2SimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaForaOpcao2SimMouseClicked
        rbBotaForaOpcao2Nao.setSelected(false);
    }//GEN-LAST:event_rbBotaForaOpcao2SimMouseClicked

    private void rbBotaForaOpcao2NaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaForaOpcao2NaoMouseClicked
        rbBotaForaOpcao2Sim.setSelected(false);
    }//GEN-LAST:event_rbBotaForaOpcao2NaoMouseClicked

    private void rbRHSim2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbRHSim2MouseClicked
        rbRHNao2.setSelected(false);
    }//GEN-LAST:event_rbRHSim2MouseClicked

    private void rbRHNao2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbRHNao2MouseClicked
        rbRHSim2.setSelected(false);
    }//GEN-LAST:event_rbRHNao2MouseClicked

    private void rbBotaFora2SimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaFora2SimMouseClicked
        rbBotaFora2Nao.setSelected(false);
    }//GEN-LAST:event_rbBotaFora2SimMouseClicked

    private void rbBotaFora2NaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaFora2NaoMouseClicked
        rbBotaFora2Sim.setSelected(false);
    }//GEN-LAST:event_rbBotaFora2NaoMouseClicked

    private void rbBotaForaOpcao4SimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaForaOpcao4SimMouseClicked
        rbBotaForaOpcao4Nao.setSelected(false);
    }//GEN-LAST:event_rbBotaForaOpcao4SimMouseClicked

    private void rbBotaForaOpcao4NaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaForaOpcao4NaoMouseClicked
        rbBotaForaOpcao4Sim.setSelected(false);
    }//GEN-LAST:event_rbBotaForaOpcao4NaoMouseClicked

    private void rbRHSim3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbRHSim3MouseClicked
        rbRHNao3.setSelected(false);
    }//GEN-LAST:event_rbRHSim3MouseClicked

    private void rbRHNao3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbRHNao3MouseClicked
        rbRHSim3.setSelected(false);
    }//GEN-LAST:event_rbRHNao3MouseClicked

    private void rbCLENadamaisexisteadeclararMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCLENadamaisexisteadeclararMouseClicked
        rbCLEDeclaramosoqueconstaemanexo.setSelected(false);
    }//GEN-LAST:event_rbCLENadamaisexisteadeclararMouseClicked

    private void rbCLEDeclaramosoqueconstaemanexoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCLEDeclaramosoqueconstaemanexoMouseClicked
        rbCLENadamaisexisteadeclarar.setSelected(false);
    }//GEN-LAST:event_rbCLEDeclaramosoqueconstaemanexoMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        if (tbRelatoriosCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem imprimidos");
        } else {

            RelatorioPrincipal terraplanagem = modeloTabelaTerraplanagem.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow());
            terraplanagem.getRELATORIO_ID();

            DecimalFormat form = new DecimalFormat("00");
            String data;
            data = form.format(terraplanagem.getRELATORIO_DATA_ATUAL().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(terraplanagem.getRELATORIO_DATA_ATUAL().get(Calendar.MONTH) + 1) + "/" + terraplanagem.getRELATORIO_DATA_ATUAL().get(Calendar.YEAR);

            try {
                //usando a clsse HashMap para criar um filtro
                //  JOptionPane.showMessageDialog(rootPane, recibo.getRecibo_id());
                HashMap filtro = new HashMap();
                filtro.put("id", terraplanagem.getRELATORIO_ID());
                filtro.put("data", data);
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("build/classes/reports/Terraplanagem.jasper", filtro, conexao);
                //a linha abaixo exibe o relatório através da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_btnImprimirActionPerformed

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

    private void rbBotaForaOpcao1SimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaForaOpcao1SimMouseClicked
        rbBotaForaOpcao1Nao.setSelected(false);
    }//GEN-LAST:event_rbBotaForaOpcao1SimMouseClicked

    private void rbBotaForaOpcao1NaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbBotaForaOpcao1NaoMouseClicked
        rbBotaForaOpcao1Sim.setSelected(false);
    }//GEN-LAST:event_rbBotaForaOpcao1NaoMouseClicked

    private void tfRepresentanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRepresentanteMouseClicked
        TelaRelatorioRepresentante telaRelatorioRepresentante = new TelaRelatorioRepresentante(null, true);
        telaRelatorioRepresentante.setVisible(true);
        // Criando o cliente à receber o cliente da tela relatorioprocuracao
        Representante representanteSelecionado = new Representante();
        representanteSelecionado = telaRelatorioRepresentante.retornarRepresentanteSelecionado();
        if (representanteSelecionado != null) {
            tfRepresentante.setText(representanteSelecionado.getRepresentante_nome());
            representanteTemporario = telaRelatorioRepresentante.retornarRepresentanteSelecionado();
        }
    }//GEN-LAST:event_tfRepresentanteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelarAtualizacao;
    private javax.swing.JButton btEditarProfissional;
    private javax.swing.JButton btExcluirProfissional;
    private javax.swing.JButton btFinalizarCadastro;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JCheckBox ckbCliente;
    private javax.swing.JCheckBox ckbProfissional;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JPanel pnCROQUI;
    private com.toedter.calendar.JDayChooser pnDataAtual;
    private javax.swing.JPanel pnTerraplanagem;
    private javax.swing.JRadioButton rbAreaDaUcNao;
    private javax.swing.JRadioButton rbAreaDaUcSim;
    private javax.swing.JRadioButton rbBotaFora1Nao;
    private javax.swing.JRadioButton rbBotaFora1Sim;
    private javax.swing.JRadioButton rbBotaFora2Nao;
    private javax.swing.JRadioButton rbBotaFora2Sim;
    private javax.swing.JRadioButton rbBotaForaOpcao1Nao;
    private javax.swing.JRadioButton rbBotaForaOpcao1Sim;
    private javax.swing.JRadioButton rbBotaForaOpcao2Nao;
    private javax.swing.JRadioButton rbBotaForaOpcao2Sim;
    private javax.swing.JRadioButton rbBotaForaOpcao3Nao;
    private javax.swing.JRadioButton rbBotaForaOpcao3Sim;
    private javax.swing.JRadioButton rbBotaForaOpcao4Nao;
    private javax.swing.JRadioButton rbBotaForaOpcao4Sim;
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
    private javax.swing.JRadioButton rbRHNao1;
    private javax.swing.JRadioButton rbRHNao2;
    private javax.swing.JRadioButton rbRHNao3;
    private javax.swing.JRadioButton rbRHSim1;
    private javax.swing.JRadioButton rbRHSim2;
    private javax.swing.JRadioButton rbRHSim3;
    private javax.swing.JRadioButton rbSuprecaoVegetacaoNao;
    private javax.swing.JRadioButton rbSuprecaoVegetacaoSim;
    private javax.swing.JTextArea taRoteiroAcesso;
    private javax.swing.JTextArea taTextoAnexo;
    private javax.swing.JTable tbRelatoriosCadastrados;
    private javax.swing.JTextField tfAlturaTalude;
    private javax.swing.JTextField tfAreaDaUcNDocumento;
    private javax.swing.JTextField tfAreaDaUcNome;
    private javax.swing.JTextField tfAreaIntervencao;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfBotaForaAreaEmprestimo;
    private javax.swing.JTextField tfBotaForaNDaUc1;
    private javax.swing.JTextField tfBotaForaNDaUc2;
    private javax.swing.JTextField tfBotaForaUtmE1;
    private javax.swing.JTextField tfBotaForaUtmE2;
    private javax.swing.JTextField tfBotaForaUtmN1;
    private javax.swing.JTextField tfBotaForaUtmN2;
    private javax.swing.JTextField tfCep;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfCoordenadasUtmE;
    private javax.swing.JTextField tfCoordenadasUtmN;
    private javax.swing.JTextField tfCpfCnpj;
    private javax.swing.JTextField tfEndereco;
    private javax.swing.JTextField tfISAAreaAterro;
    private javax.swing.JTextField tfISAAreaCorte;
    private javax.swing.JTextField tfISAAreaTotalMovimentacaoTerra;
    private javax.swing.JTextField tfISAData;
    private javax.swing.JTextField tfISAPrevisaoVegetacao;
    private javax.swing.JTextField tfISATaludesFormados;
    private javax.swing.JTextField tfInseridoEmAreaOutraEspecificar;
    private javax.swing.JTextField tfNEmpregados;
    private javax.swing.JTextField tfNomeCliente;
    private javax.swing.JTextField tfNomeFantasia;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfPalavraChaveTuristico;
    private javax.swing.JTextField tfProfissional;
    private javax.swing.JTextField tfRHDistancia1;
    private javax.swing.JTextField tfRHDistancia2;
    private javax.swing.JTextField tfRHDistancia3;
    private javax.swing.JTextField tfRHNome1;
    private javax.swing.JTextField tfRHNome2;
    private javax.swing.JTextField tfRHNome3;
    private javax.swing.JTextField tfRepresentante;
    private javax.swing.JTextField tfTelefone;
    private javax.swing.JTabbedPane tpnAbasTerraplanagem;
    // End of variables declaration//GEN-END:variables
}
