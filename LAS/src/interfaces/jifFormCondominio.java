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
import java.text.NumberFormat;
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
public class jifFormCondominio extends javax.swing.JInternalFrame {

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
    RelatorioPrincipal condominioCadastro = new RelatorioPrincipal();
    Cliente clienteTemporario = new Cliente();
    Profissional profissionalTemporario = new Profissional();
    Representante representanteTemporario = new Representante();
    RelatorioPrincipal relatorioExcluir = new RelatorioPrincipal();

    /**
     * Classes de definição de modelos de tabela
     */
    ModeloTabelaRelatorios modeloTabelaCondominio = new ModeloTabelaRelatorios();
    ModeloTabelaClientes modeloTabelaClientes;
    ModeloTabelaProfissionais modeloTabelaProfissionais;

    /**
     * Definição de hora e data
     */
    DecimalFormat formatador = new DecimalFormat("00");
    Calendar calendario;

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

    public jifFormCondominio() {

        initComponents();
        conexao = ConexaoPDF.conector();
        Calendar cal = Calendar.getInstance();
        dataAtual.setBaseDate(cal.getTime());
        pnDataAtual.add(dataAtual);

        setFrameIcon(new ImageIcon(this.getClass().getResource("/imagens/icon.png")));

        // Definindo o botão DateField (Data Inicio do Semestre) para seleção de uma data e atribuindo uma ação de mudança à ele.
        dataAtual.setSize((pnDataAtual.getWidth()), (pnDataAtual.getHeight()));

        btCancelarAtualizacao.setVisible(false);
        //  Calendar calISA = Calendar.getInstance();
        //  dataISA.setBaseDate(calISA.getTime());
        //  pnISAData.add(dataISA);
        // Definindo o botão DateField (Data Inicio do Semestre) para seleção de uma data e atribuindo uma ação de mudança à ele.
        //  dataISA.setSize((pnISAData.getWidth()), (pnISAData.getHeight()));
        tbRelatoriosCadastrados.setModel(modeloTabelaCondominio);
        buscarRelatoriosTabela();

        tfNHabitacoes.setDocument(new LetrasMaiusculas());
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
        tfAreaDaUcAdministrador.setDocument(new LetrasMaiusculas());
        tfAreaDaUcNome.setDocument(new LetrasMaiusculas());
        tfNIphan.setDocument(new LetrasMaiusculas());
        tfSuprecaoVegetacaoDocumentoIdaf.setDocument(new LetrasMaiusculas());
        tfLoteMenorArea.setDocument(new LetrasMaiusculas());
        tfCondicoesTerrenoEspecificar.setDocument(new LetrasMaiusculas());
        tfDeclividadeEspecificar.setDocument(new LetrasMaiusculas());
        tfCoordenadasUtmN.setDocument(new LetrasMaiusculas());
        tfCoordenadasUtmE.setDocument(new LetrasMaiusculas());
        tfISAPrevisaoVegetacao.setDocument(new LetrasMaiusculas());
        tfNEmpregados.setDocument(new LetrasMaiusculas());
        tfSDPEEspecificar.setDocument(new LetrasMaiusculas());
        //tfFAAConsumoDeAgua.setDocument(new LetrasMaiusculas());
        tfFAANDocumentoOutorgaRh.setDocument(new LetrasMaiusculas());
        tfFAANDocumentoCertidaoDo.setDocument(new LetrasMaiusculas());
        tfFAAEmpresa.setDocument(new LetrasMaiusculas());
        tfFAAPocoTipo.setDocument(new LetrasMaiusculas());
        tfFAAPocoQtd.setDocument(new LetrasMaiusculas());
        tfFAANumeroLicenca.setDocument(new LetrasMaiusculas());
        tfFAACursoDaguaNome.setDocument(new LetrasMaiusculas());
        tfFAALagoNome.setDocument(new LetrasMaiusculas());
        tfFAAOutrasEspecificar.setDocument(new LetrasMaiusculas());
        taRoteiroAcesso.setDocument(new LetrasMaiusculas());
        taTextoAnexo.setDocument(new LetrasMaiusculas());
        tfProfissional.setDocument(new LetrasMaiusculas());
        tfLeiPerimetroUrbano.setDocument(new LetrasMaiusculas());
        tfPlanoDiretorUrbano.setDocument(new LetrasMaiusculas());
        GRConsumoAguaLDia.setDocument(new LetrasMaiusculas());
        GRConsumoAguaMMes.setDocument(new LetrasMaiusculas());
        tfGREspecificar1.setDocument(new LetrasMaiusculas());
        tfGREspecificar2.setDocument(new LetrasMaiusculas());
        tfGRNome1.setDocument(new LetrasMaiusculas());
        tfGRNome2.setDocument(new LetrasMaiusculas());
        tfGRNumeroLicenca1.setDocument(new LetrasMaiusculas());
        tfGRNumeroLicenca2.setDocument(new LetrasMaiusculas());
        tfGRNDeclaracao.setDocument(new LetrasMaiusculas());
        tfEAEspecificar.setDocument(new LetrasMaiusculas());
        tfFAAEmpresa.setText("CESAN");
        tfFAAConsumoDeAgua.setText("150 L/hab.dia");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btEditarProfissional1 = new javax.swing.JButton();
        tpnAbasCondominios = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnCondominio = new javax.swing.JPanel();
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
        jLabel70 = new javax.swing.JLabel();
        rbTipoFinanciamentoSim = new javax.swing.JRadioButton();
        rbTipoFinanciamentoNao = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        rbCondicoesTerrenoNao = new javax.swing.JRadioButton();
        rbCondicoesTerrenoSim = new javax.swing.JRadioButton();
        rbCondicoesTerrenoOpcoesExecucao = new javax.swing.JRadioButton();
        rbCondicoesTerrenoOpcoesOutraSolucao = new javax.swing.JRadioButton();
        jLabel78 = new javax.swing.JLabel();
        tfCondicoesTerrenoEspecificar = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        tfLoteMenorArea = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        rbSuprecaoVegetacaoSim = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        tfSuprecaoVegetacaoDocumentoIdaf = new javax.swing.JTextField();
        rbSuprecaoVegetacaoNao = new javax.swing.JRadioButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        rbHaPatrimonioNao = new javax.swing.JRadioButton();
        rbHaPatrimonioSim = new javax.swing.JRadioButton();
        jLabel73 = new javax.swing.JLabel();
        tfNIphan = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        rbAreaDaUcSim = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        rbAreaDaUcNao = new javax.swing.JRadioButton();
        tfAreaDaUcNDocumento = new javax.swing.JTextField();
        tfAreaDaUcAdministrador = new javax.swing.JTextField();
        tfAreaDaUcNome = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        rbHaResidenciasSim = new javax.swing.JRadioButton();
        rbHaResidenciasNao = new javax.swing.JRadioButton();
        jPanel29 = new javax.swing.JPanel();
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
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        tfLeiPerimetroUrbano = new javax.swing.JTextField();
        tfPlanoDiretorUrbano = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        rbDeclividadeNao = new javax.swing.JRadioButton();
        rbDeclividadeSim = new javax.swing.JRadioButton();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        tfDeclividadeEspecificar = new javax.swing.JTextField();
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
        tfISAData = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        ckbFAARedePublica = new javax.swing.JCheckBox();
        ckbFAAPoco = new javax.swing.JCheckBox();
        ckbFAAReservatorios = new javax.swing.JCheckBox();
        ckbFAACursoDagua = new javax.swing.JCheckBox();
        ckbFAALago = new javax.swing.JCheckBox();
        ckbFAACaptacao = new javax.swing.JCheckBox();
        ckbFAANascente = new javax.swing.JCheckBox();
        ckbFAAOutros = new javax.swing.JCheckBox();
        ckbFAANaoSeAplica = new javax.swing.JCheckBox();
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
        jLabel65 = new javax.swing.JLabel();
        tfFAAOutrasEspecificar = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        GRConsumoAguaLDia = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        GRConsumoAguaMMes = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        ckbGRSanitarioQuimico = new javax.swing.JCheckBox();
        jLabel42 = new javax.swing.JLabel();
        ckbGRAlojamento = new javax.swing.JCheckBox();
        ckbGRRefeitoria = new javax.swing.JCheckBox();
        ckbGRAreaManutencao = new javax.swing.JCheckBox();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        ckbGRSistemaFossa1 = new javax.swing.JCheckBox();
        ckbGRRedeColetaDistribuicao = new javax.swing.JCheckBox();
        ckbGRNaoHaveraInstalacao = new javax.swing.JCheckBox();
        tfGRNumeroLicenca1 = new javax.swing.JTextField();
        tfGRNome1 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        ckbGRResiduosConstrucao = new javax.swing.JCheckBox();
        ckbGRNaoHaGeracao1 = new javax.swing.JCheckBox();
        jLabel46 = new javax.swing.JLabel();
        ckbGRToneisBombados = new javax.swing.JCheckBox();
        ckbGROutraFormaArmazenamento = new javax.swing.JCheckBox();
        jLabel47 = new javax.swing.JLabel();
        tfGREspecificar1 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        tfGRNumeroLicenca2 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        ckbGRColetaPublica1 = new javax.swing.JCheckBox();
        jLabel53 = new javax.swing.JLabel();
        tfGRNDeclaracao = new javax.swing.JTextField();
        ckbGROutra = new javax.swing.JCheckBox();
        jLabel85 = new javax.swing.JLabel();
        tfGREspecificar2 = new javax.swing.JTextField();
        tfGRNome2 = new javax.swing.JTextField();
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
        jLabel66 = new javax.swing.JLabel();
        tfRepresentante = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        tfNHabitacoes = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        rbSDPEEscoamentoSuperficial = new javax.swing.JRadioButton();
        rbSDPERedeColetora = new javax.swing.JRadioButton();
        rbSDPEOutros = new javax.swing.JRadioButton();
        jLabel83 = new javax.swing.JLabel();
        tfSDPEEspecificar = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        rbMovimentacaoTerraNao = new javax.swing.JRadioButton();
        rbMovimentacaoTerraSim = new javax.swing.JRadioButton();
        jLabel84 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ckbEAProveniente = new javax.swing.JCheckBox();
        ckbEACirculacao = new javax.swing.JCheckBox();
        ckbEANaoHaGeracao = new javax.swing.JCheckBox();
        ckbEAUmectacao = new javax.swing.JCheckBox();
        ckbEAOutro = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        tfEAEspecificar = new javax.swing.JTextField();
        pnCROQUI = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblImagem = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btCancelarAtualizacao = new javax.swing.JButton();
        btFinalizarCadastro = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        tfPalavraChaveCondominio = new javax.swing.JTextField();
        ckbCliente = new javax.swing.JCheckBox();
        ckbProfissional = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbRelatoriosCadastrados = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        btEditarProfissional = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btExcluirProfissional = new javax.swing.JButton();

        btEditarProfissional1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit-file.png"))); // NOI18N
        btEditarProfissional1.setToolTipText("Editar");
        btEditarProfissional1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditarProfissional1.setPreferredSize(new java.awt.Dimension(150, 150));
        btEditarProfissional1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarProfissional1ActionPerformed(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setTitle("Condomínios");
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

        jLabel70.setText("O empreendimento requer algum tipo de financiamento?");

        rbTipoFinanciamentoSim.setText("Sim");

        rbTipoFinanciamentoNao.setText("Não");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                                        .addComponent(tfTelefone))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbTipoFinanciamentoSim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbTipoFinanciamentoNao)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(rbTipoFinanciamentoSim)
                    .addComponent(rbTipoFinanciamentoNao)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CARACTERÍSTICAS DA ÁREA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel76.setText("Condições do terreno:");

        jLabel77.setText("Existe na gleba, ou em parte dela, terrenos alagadiços e sujeitos as inundações?");

        rbCondicoesTerrenoNao.setText("Não");
        rbCondicoesTerrenoNao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbCondicoesTerrenoNaoMouseClicked(evt);
            }
        });

        rbCondicoesTerrenoSim.setText("Sim");
        rbCondicoesTerrenoSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbCondicoesTerrenoSimMouseClicked(evt);
            }
        });

        rbCondicoesTerrenoOpcoesExecucao.setText("Execução de Drenagem.");
        rbCondicoesTerrenoOpcoesExecucao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbCondicoesTerrenoOpcoesExecucaoMouseClicked(evt);
            }
        });

        rbCondicoesTerrenoOpcoesOutraSolucao.setText("Outra solução.");
        rbCondicoesTerrenoOpcoesOutraSolucao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbCondicoesTerrenoOpcoesOutraSolucaoMouseClicked(evt);
            }
        });

        jLabel78.setText("Especificar:");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76)
                    .addComponent(jLabel77)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbCondicoesTerrenoSim)
                            .addComponent(rbCondicoesTerrenoNao))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbCondicoesTerrenoOpcoesExecucao)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(rbCondicoesTerrenoOpcoesOutraSolucao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel78)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCondicoesTerrenoEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel76)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbCondicoesTerrenoNao)
                        .addGap(18, 18, 18)
                        .addComponent(rbCondicoesTerrenoSim))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(rbCondicoesTerrenoOpcoesExecucao)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbCondicoesTerrenoOpcoesOutraSolucao)
                            .addComponent(jLabel78)
                            .addComponent(tfCondicoesTerrenoEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel74.setText("Áreas:");

        jLabel75.setText("Lote de de menor área:");

        tfLoteMenorArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfLoteMenorAreaFocusLost(evt);
            }
        });

        jLabel35.setText("m²");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfLoteMenorArea, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)))
                .addContainerGap(462, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(tfLoteMenorArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSuprecaoVegetacaoDocumentoIdaf))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbSuprecaoVegetacaoNao)
                            .addComponent(rbSuprecaoVegetacaoSim)
                            .addComponent(jLabel16))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSuprecaoVegetacaoSim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tfSuprecaoVegetacaoDocumentoIdaf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSuprecaoVegetacaoNao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel72.setText("Há patrimônio histórico na área útil?");

        rbHaPatrimonioNao.setText("Não");
        rbHaPatrimonioNao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbHaPatrimonioNaoMouseClicked(evt);
            }
        });

        rbHaPatrimonioSim.setText("Sim");
        rbHaPatrimonioSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbHaPatrimonioSimMouseClicked(evt);
            }
        });

        jLabel73.setText("Número do documento com a anuência do IPHAN:");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbHaPatrimonioNao)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rbHaPatrimonioSim, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNIphan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbHaPatrimonioNao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbHaPatrimonioSim)
                    .addComponent(jLabel73)
                    .addComponent(tfNIphan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setText("A área está inserida em Unidade de Conservação (UC) ou em sua zona de amortecimento?");

        rbAreaDaUcSim.setText("Sim");
        rbAreaDaUcSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbAreaDaUcSimMouseClicked(evt);
            }
        });

        jLabel14.setText("Nome da unidade de conservação:");

        jLabel71.setText("Administrador da UC:");

        jLabel15.setText("N° do documento referente à anuência:");

        rbAreaDaUcNao.setText("Não");
        rbAreaDaUcNao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbAreaDaUcNaoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAreaDaUcNDocumento))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(rbAreaDaUcSim)
                                .addComponent(jLabel13)
                                .addGroup(jPanel27Layout.createSequentialGroup()
                                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel71))
                                    .addGap(29, 29, 29)
                                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfAreaDaUcNome)
                                        .addComponent(tfAreaDaUcAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(rbAreaDaUcNao))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbAreaDaUcSim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfAreaDaUcNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(tfAreaDaUcAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tfAreaDaUcNDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbAreaDaUcNao)
                .addContainerGap())
        );

        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(rbHaResidenciasSim)
                        .addGap(180, 180, 180)
                        .addComponent(rbHaResidenciasNao)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbHaResidenciasSim)
                    .addComponent(rbHaResidenciasNao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        jLabel32.setText("Lei de perímetro urbado:");

        jLabel33.setText("Plano Diretor Urbano Lei Nº:");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfPlanoDiretorUrbano))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfLeiPerimetroUrbano, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(rbLocalizacaoZonaUrbana)
                            .addComponent(rbLocalizacaoZonaRural))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(rbInseridoEmAreaOutra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfInseridoEmAreaOutraEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(rbInseridoEmAreaIndustrial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbInseridoEmAreaResidencial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbInseridoEmAreaComercial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbInseridoEmAreaMista))
                    .addComponent(jLabel10))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLocalizacaoZonaUrbana)
                    .addComponent(rbInseridoEmAreaIndustrial)
                    .addComponent(rbInseridoEmAreaResidencial)
                    .addComponent(rbInseridoEmAreaComercial)
                    .addComponent(rbInseridoEmAreaMista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbInseridoEmAreaOutra)
                    .addComponent(jLabel11)
                    .addComponent(tfInseridoEmAreaOutraEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(tfLeiPerimetroUrbano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(tfPlanoDiretorUrbano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(rbLocalizacaoZonaRural)
                .addContainerGap())
        );

        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel79.setText("A gleba, ou parte dela apresenta DECLIVIDADE igual ou superior a 30% (trinta por cento)?");

        rbDeclividadeNao.setText("Não.");
        rbDeclividadeNao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbDeclividadeNaoMouseClicked(evt);
            }
        });

        rbDeclividadeSim.setText("Sim.");
        rbDeclividadeSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbDeclividadeSimMouseClicked(evt);
            }
        });

        jLabel80.setText("Diretrizes e exigências específicas definidas pela Prefeitura Municipal.");

        jLabel81.setText("Especificar:");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbDeclividadeSim)
                            .addComponent(jLabel79)
                            .addComponent(rbDeclividadeNao)))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel81)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfDeclividadeEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel80))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbDeclividadeNao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDeclividadeSim)
                    .addComponent(jLabel81)
                    .addComponent(tfDeclividadeEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel25.setText("Nº de empregados (na fase de instalação): ");

        tfISAData.setEditable(false);

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
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfISAPrevisaoVegetacao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfISAData, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbISAOperacao)
                    .addComponent(jLabel23)
                    .addComponent(tfISAData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(tfNEmpregados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FONTES DE ABASTECIMENTO DE ÁGUA ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ckbFAARedePublica.setText("Rede Pública.");

        ckbFAAPoco.setText("Poço(s).");

        ckbFAAReservatorios.setText("Reservatórios, represas ou barragens.");

        ckbFAACursoDagua.setText("Curso d’ água (rios, córrego e riachos).");

        ckbFAALago.setText("Lago/lagoa");

        ckbFAACaptacao.setText("Captação de água pluvial.");

        ckbFAANascente.setText("Nascente.");

        ckbFAAOutros.setText("Outros.");

        ckbFAANaoSeAplica.setText("Não se aplica.");

        jLabel26.setText("Previsão de Consumo de água:");

        tfFAAConsumoDeAgua.setText("150");
        tfFAAConsumoDeAgua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFAAConsumoDeAguaFocusLost(evt);
            }
        });

        jLabel27.setText("L/hab.dia.");

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
                            .addComponent(ckbFAANascente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(ckbFAAOutros)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAAOutrasEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ckbFAANaoSeAplica, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAAConsumoDeAgua))
                            .addComponent(ckbFAACertidao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFAANDocumentoOutorgaRh)))
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
                .addComponent(ckbFAANascente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbFAAOutros)
                    .addComponent(jLabel65)
                    .addComponent(tfFAAOutrasEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbFAANaoSeAplica)
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

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GERENCIAMENTO DE RESÍDUOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel34.setText("Canteiro/Frente de obras:");

        jLabel39.setText("Consumo de água:");

        jLabel40.setText("L/dia ou");

        jLabel41.setText("m³/mês.");

        jLabel38.setText("Estruturas instaladas:");

        ckbGRSanitarioQuimico.setText("Sanitário Químico (móvel).");

        jLabel42.setText("Empresa licenciada para coleta,transporte e destinação final:");

        ckbGRAlojamento.setText("Alojamento.");

        ckbGRRefeitoria.setText("Refeitório");

        ckbGRAreaManutencao.setText("Área de manutenção");

        jLabel43.setText("Nome:");

        jLabel44.setText("Nº da Licença:");

        ckbGRSistemaFossa1.setText("Sistema de Fossa Séptica/ Filtro Anaeróbio/Sumidouro.");

        ckbGRRedeColetaDistribuicao.setText("Rede de coleta pública.");

        ckbGRNaoHaveraInstalacao.setText("Não haverá instalação de canteiro/ Frente de obras");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbGRAreaManutencao)
                    .addComponent(ckbGRRefeitoria)
                    .addComponent(jLabel34)
                    .addComponent(jLabel38)
                    .addComponent(ckbGRAlojamento)
                    .addComponent(ckbGRNaoHaveraInstalacao)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbGRSanitarioQuimico)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GRConsumoAguaLDia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GRConsumoAguaMMes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel41))
                            .addComponent(jLabel42)
                            .addComponent(ckbGRSistemaFossa1)
                            .addComponent(ckbGRRedeColetaDistribuicao)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel43))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfGRNumeroLicenca1)
                                    .addComponent(tfGRNome1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addGap(5, 5, 5)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(GRConsumoAguaLDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(GRConsumoAguaMMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addGap(19, 19, 19)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(ckbGRSanitarioQuimico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addGap(11, 11, 11)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbGRAlojamento)
                    .addComponent(jLabel43)
                    .addComponent(tfGRNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbGRRefeitoria)
                    .addComponent(jLabel44)
                    .addComponent(tfGRNumeroLicenca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbGRAreaManutencao)
                    .addComponent(ckbGRSistemaFossa1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbGRRedeColetaDistribuicao)
                .addGap(18, 18, 18)
                .addComponent(ckbGRNaoHaveraInstalacao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel45.setText("Resíduos Sólidos gerados durante a Obra:");

        ckbGRResiduosConstrucao.setText("<html>Resíduos de Construção Civil<br>(Entulho, Madeiras, Vergalhões,<br>etc.)</html>");

        ckbGRNaoHaGeracao1.setText("Não há geração");

        jLabel46.setText("Forma de armazenamento:");

        ckbGRToneisBombados.setText("<html>Tonéis, bombonas, big bags e<br>similares, em local coberto.</html>");

        ckbGROutraFormaArmazenamento.setText("Outra forma de armazenamento.");

        jLabel47.setText("Especificar:");

        jLabel48.setText("<html>Empresa(s) licenciada(s) para coleta, transporte e destinação<br>final:</html>");

        jLabel49.setText("Nome(s):");

        jLabel50.setText("Nº(s) da Licença(s) de Operação:");

        jLabel51.setText("Resíduos sólidos gerados a partir da ocupação");

        jLabel52.setText("Destinação final:");

        ckbGRColetaPublica1.setText("Coleta pública.");

        jLabel53.setText("<html>N° da declaração da municipalidade assumindo a coleta e destinação dos resíduos<br>gerados:</html>");

        ckbGROutra.setText("Outra.");

        jLabel85.setText("Especificar:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbGRNaoHaGeracao1)
                            .addComponent(ckbGRResiduosConstrucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfGREspecificar1))
                            .addComponent(ckbGRToneisBombados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)
                            .addComponent(ckbGROutraFormaArmazenamento)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel49))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfGRNumeroLicenca2)
                                    .addComponent(tfGRNome2)))))
                    .addComponent(jLabel51)
                    .addComponent(jLabel52)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(ckbGRColetaPublica1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(ckbGROutra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfGRNDeclaracao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfGREspecificar2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addGap(5, 5, 5)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckbGRResiduosConstrucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbGRToneisBombados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckbGRNaoHaGeracao1)
                            .addComponent(ckbGROutraFormaArmazenamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(tfGREspecificar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49))
                    .addComponent(tfGRNome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(tfGRNumeroLicenca2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbGRColetaPublica1)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfGRNDeclaracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbGROutra)
                    .addComponent(jLabel85)
                    .addComponent(tfGREspecificar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(tfProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnDataAtual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel66)
                        .addComponent(tfRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel68)
                        .addComponent(tfProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(231, 231, 231))
        );

        jLabel69.setText("N° de Habitações:");

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SISTEMA DE DRENAGEM PLUVIAL EXISTENTE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel82.setText("Fase do empreendimento:");

        rbSDPEEscoamentoSuperficial.setText("Escoamento superficial.");
        rbSDPEEscoamentoSuperficial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbSDPEEscoamentoSuperficialMouseClicked(evt);
            }
        });

        rbSDPERedeColetora.setText("Rede coletora.");
        rbSDPERedeColetora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbSDPERedeColetoraMouseClicked(evt);
            }
        });

        rbSDPEOutros.setText("Outros.");
        rbSDPEOutros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbSDPEOutrosMouseClicked(evt);
            }
        });

        jLabel83.setText("Especificar:");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82)
                    .addComponent(rbSDPEEscoamentoSuperficial)
                    .addComponent(rbSDPERedeColetora)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(rbSDPEOutros)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSDPEEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSDPEEscoamentoSuperficial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSDPERedeColetora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSDPEOutros)
                    .addComponent(jLabel83)
                    .addComponent(tfSDPEEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MOVIMENTAÇÃO DE TERRA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel64.setText("Haverá movimentação de terra na área?");

        rbMovimentacaoTerraNao.setText("Não.");
        rbMovimentacaoTerraNao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbMovimentacaoTerraNaoMouseClicked(evt);
            }
        });

        rbMovimentacaoTerraSim.setText("Sim.");
        rbMovimentacaoTerraSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbMovimentacaoTerraSimMouseClicked(evt);
            }
        });

        jLabel84.setText("Preencher FCE referente à atividade de movimentação de terra.");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbMovimentacaoTerraNao)
                            .addComponent(jLabel64))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(rbMovimentacaoTerraSim)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel84)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbMovimentacaoTerraNao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMovimentacaoTerraSim)
                    .addComponent(jLabel84))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EMISSÕES ATMOSFÉRICAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ckbEAProveniente.setText("<html>Provenientes da<br>terraplanagem.</html>");

        ckbEACirculacao.setText("<html>Circulação de máquinas e<br>Automóveis.</html>");

        ckbEANaoHaGeracao.setText("Não há geração");

        ckbEAUmectacao.setText("Umectação de vias");

        ckbEAOutro.setText("Outro.");

        jLabel30.setText("Especificar:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbEANaoHaGeracao)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbEAProveniente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckbEACirculacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(209, 209, 209)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ckbEAOutro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfEAEspecificar))
                            .addComponent(ckbEAUmectacao))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbEAProveniente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbEAUmectacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbEACirculacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbEAOutro)
                    .addComponent(jLabel30)
                    .addComponent(tfEAEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbEANaoHaGeracao))
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
                        .addGap(0, 21, Short.MAX_VALUE)))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btCancelarAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel31.setText("m²");

        javax.swing.GroupLayout pnCondominioLayout = new javax.swing.GroupLayout(pnCondominio);
        pnCondominio.setLayout(pnCondominioLayout);
        pnCondominioLayout.setHorizontalGroup(
            pnCondominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnCondominioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCondominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnCondominioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAreaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNHabitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnCROQUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 148, Short.MAX_VALUE))
        );
        pnCondominioLayout.setVerticalGroup(
            pnCondominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCondominioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCondominioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfAreaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69)
                    .addComponent(tfNHabitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnCROQUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(pnCondominio);

        tpnAbasCondominios.addTab("Cadastrar Novos Condomínios", jScrollPane1);

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro de Busca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel86.setText("Palavra-chave:");

        tfPalavraChaveCondominio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPalavraChaveCondominioKeyReleased(evt);
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
                .addComponent(tfPalavraChaveCondominio)
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
                    .addComponent(tfPalavraChaveCondominio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        btExcluirProfissional.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete-file.png"))); // NOI18N
        btExcluirProfissional.setToolTipText("Excluir");
        btExcluirProfissional.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluirProfissional.setPreferredSize(new java.awt.Dimension(150, 150));
        btExcluirProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirProfissionalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btExcluirProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btEditarProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btExcluirProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditarProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpnAbasCondominios.addTab("Gerenciar Condomínios", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 784, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tpnAbasCondominios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tpnAbasCondominios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
        );

        setBounds(0, 0, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfNomeClienteMouseClicked
        TelaRelatorioCliente telaRelatorioCliente = new TelaRelatorioCliente(null, true, 2);
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
            DecimalFormat form = new DecimalFormat("00");
            String dataInicioAtividade;
            dataInicioAtividade = form.format(clienteSelecionado.getCliente_data_atividade().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(clienteSelecionado.getCliente_data_atividade().get(Calendar.MONTH) + 1) + "/" + clienteSelecionado.getCliente_data_atividade().get(Calendar.YEAR);
            tfISAData.setText(dataInicioAtividade);
            clienteTemporario = telaRelatorioCliente.retornarClienteSelecionado();
        }
    }//GEN-LAST:event_tfNomeClienteMouseClicked

    public void buscarRelatoriosTabela() {

        modeloTabelaCondominio.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(2));
        listaOriginalTemporariaRelatorio = modeloTabelaCondominio.retornaListaRelatorioPrincipal();
        tbRelatoriosCadastrados.updateUI();
    }

    public RelatorioPrincipal preencherDadosCadastroCondominio() {

        condominioCadastro.setRELATORIO_AREA_UTIL(tfAreaUtil.getText());
        condominioCadastro.setRELATORIO_N_HABITACOES(tfNHabitacoes.getText());
        System.out.println(clienteTemporario.getCliente_id());
        condominioCadastro.setCLIENTE_ID(clienteTemporario.getCliente_id());
        condominioCadastro.setREPRESENTANTE_ID(representanteTemporario.getRepresentante_id());
        if (rbTipoFinanciamentoSim.isSelected()) {
            condominioCadastro.setRELATORIO_TIPO_DE_FINANCIAMENTO(1);
        }
        if (rbTipoFinanciamentoNao.isSelected()) {
            condominioCadastro.setRELATORIO_TIPO_DE_FINANCIAMENTO(0);
        }
        if (rbLocalizacaoZonaUrbana.isSelected()) {
            condominioCadastro.setRELATORIO_LOCALIZACAO(0);
        }
        if (rbLocalizacaoZonaRural.isSelected()) {
            condominioCadastro.setRELATORIO_LOCALIZACAO(1);
        }
        if (rbInseridoEmAreaIndustrial.isSelected()) {
            condominioCadastro.setRELATORIO_INSERIDO_EM_AREA(0);
        }
        if (rbInseridoEmAreaResidencial.isSelected()) {
            condominioCadastro.setRELATORIO_INSERIDO_EM_AREA(1);
        }
        if (rbInseridoEmAreaComercial.isSelected()) {
            condominioCadastro.setRELATORIO_INSERIDO_EM_AREA(2);
        }
        if (rbInseridoEmAreaMista.isSelected()) {
            condominioCadastro.setRELATORIO_INSERIDO_EM_AREA(3);
        }
        if (rbInseridoEmAreaOutra.isSelected()) {
            condominioCadastro.setRELATORIO_INSERIDO_EM_AREA(4);
        }
        condominioCadastro.setRELATORIO_INSERIDO_EM_AREA_OUTRA_ESPECIFICAR(tfInseridoEmAreaOutraEspecificar.getText());
        condominioCadastro.setRELATORIO_LOCALIZACAO_LEI(tfLeiPerimetroUrbano.getText());
        condominioCadastro.setRELATORIO_LOCALIZACAO_PLANO(tfPlanoDiretorUrbano.getText());
        if (rbHaResidenciasSim.isSelected()) {
            condominioCadastro.setRELATORIO_HA_RESIDENCIAS(1);
        }
        if (rbHaResidenciasNao.isSelected()) {
            condominioCadastro.setRELATORIO_HA_RESIDENCIAS(0);
        }
        if (rbAreaDaUcSim.isSelected()) {
            condominioCadastro.setRELATORIO_AREA_DA_UC(1);
        }
        if (rbAreaDaUcNao.isSelected()) {
            condominioCadastro.setRELATORIO_AREA_DA_UC(0);
        }
        condominioCadastro.setRELATORIO_AREA_DA_UC_NOME(tfAreaDaUcNome.getText());
        condominioCadastro.setRELATORIO_AREA_DA_UC_ADMINISTRADOR(tfAreaDaUcAdministrador.getText());
        condominioCadastro.setRELATORIO_AREA_DA_UC_N_DOCUMENTO(tfAreaDaUcNDocumento.getText());
        if (rbHaPatrimonioSim.isSelected()) {
            condominioCadastro.setRELATORIO_HA_PATRIMONIO(1);
        }
        if (rbHaPatrimonioNao.isSelected()) {
            condominioCadastro.setRELATORIO_HA_PATRIMONIO(0);
        }
        condominioCadastro.setRELATORIO_N_IPHAN(tfNIphan.getText());
        if (rbSuprecaoVegetacaoSim.isSelected()) {
            condominioCadastro.setRELATORIO_SUPRECAO_VEGETACAO(1);
        }
        if (rbSuprecaoVegetacaoNao.isSelected()) {
            condominioCadastro.setRELATORIO_SUPRECAO_VEGETACAO(0);
        }
        condominioCadastro.setRELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF(tfSuprecaoVegetacaoDocumentoIdaf.getText());
        condominioCadastro.setRELATORIO_LOTE_DE_MENOR_AREA(tfLoteMenorArea.getText());
        if (rbCondicoesTerrenoSim.isSelected()) {
            condominioCadastro.setRELATORIO_CONDICOES_DO_TERRENO(1);
        }
        if (rbCondicoesTerrenoNao.isSelected()) {
            condominioCadastro.setRELATORIO_CONDICOES_DO_TERRENO(0);
        }
        if (rbCondicoesTerrenoOpcoesExecucao.isSelected()) {
            condominioCadastro.setRELATORIO_CONDICOES_DO_TERRENO_OPCOES(1);
        }
        if (rbCondicoesTerrenoOpcoesExecucao.isSelected()) {
            condominioCadastro.setRELATORIO_CONDICOES_DO_TERRENO_OPCOES(0);
        }
        condominioCadastro.setRELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR(tfCondicoesTerrenoEspecificar.getText());
        if (rbDeclividadeSim.isSelected()) {
            condominioCadastro.setRELATORIO_DECLIVIDADE(1);
        }
        if (rbDeclividadeNao.isSelected()) {
            condominioCadastro.setRELATORIO_DECLIVIDADE(0);
        }
        condominioCadastro.setRELATORIO_DECLIVIDADE_ESPECIFICAR(tfDeclividadeEspecificar.getText());
        condominioCadastro.setRELATORIO_COORDENADAS_UTM_N(tfCoordenadasUtmN.getText());
        condominioCadastro.setRELATORIO_COORDENADAS_UTM_E(tfCoordenadasUtmE.getText());
        if (rbISAPlanejamento.isSelected()) {
            condominioCadastro.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(0);
        }
        if (rbISAInstalacao.isSelected()) {
            condominioCadastro.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(1);
        }
        if (rbISAOperacao.isSelected()) {
            condominioCadastro.setRELATORIO_INFORMACOES_SOBRE_A_ATIVIDADE(2);
        }
        condominioCadastro.setRELATORIO_TEXTO_ANEXO(taTextoAnexo.getText());
        Calendar calISA = Calendar.getInstance();
        calISA.setTime((Date) dataISA.getValue());
        condominioCadastro.setRELATORIO_I_S_A_DATA(calISA);
        condominioCadastro.setRELATORIO_I_S_A_PREVISAO_OPERACAO(tfISAPrevisaoVegetacao.getText());
        condominioCadastro.setRELATORIO_I_S_A_N_EMPREGADOS(tfNEmpregados.getText());
        if (rbSDPEEscoamentoSuperficial.isSelected()) {
            condominioCadastro.setRELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE(0);
        }
        if (rbSDPERedeColetora.isSelected()) {
            condominioCadastro.setRELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE(1);
        }
        if (rbSDPEOutros.isSelected()) {
            condominioCadastro.setRELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE(2);
        }
        condominioCadastro.setRELATORIO_S_D_P_E_ESPECIFICAR(tfSDPEEspecificar.getText());
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
        if (ckbFAANascente.isSelected()) {
            CheckBoxFAA += "ckbFAANascente-";
        }
        if (ckbFAAOutros.isSelected()) {
            CheckBoxFAA += "ckbFAAOutros-";
        }
        if (ckbFAANaoSeAplica.isSelected()) {
            CheckBoxFAA += "ckbFAANaoSeAplica-";
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
        condominioCadastro.setRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA(CheckBoxFAA);
        condominioCadastro.setRELATORIO_F_A_A_EMPRESA(tfFAAEmpresa.getText());
        condominioCadastro.setRELATORIO_F_A_A_POCO_TIPO(tfFAAPocoTipo.getText());
        condominioCadastro.setRELATORIO_F_A_A_POCO_QTD(tfFAAPocoQtd.getText());
        condominioCadastro.setRELATORIO_F_A_A_NUMERO_LICENCA(tfFAANumeroLicenca.getText());
        condominioCadastro.setRELATORIO_F_A_A_CURSO_DAGUA_NOME(tfFAACursoDaguaNome.getText());
        condominioCadastro.setRELATORIO_F_A_A_LAGO_NOME(tfFAALagoNome.getText());
        condominioCadastro.setRELATORIO_F_A_A_OUTRAS_ESPECIFICAR(tfFAAOutrasEspecificar.getText());
        condominioCadastro.setRELATORIO_F_A_A_CONSUMO_DE_AGUA(tfFAAConsumoDeAgua.getText());
        condominioCadastro.setRELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H(tfFAANDocumentoOutorgaRh.getText());
        condominioCadastro.setRELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O(tfFAANDocumentoCertidaoDo.getText());
        if (rbMovimentacaoTerraNao.isSelected()) {
            condominioCadastro.setRELATORIO_MOVIMENTACAO_DE_TERRA(1);
        }
        if (rbMovimentacaoTerraSim.isSelected()) {
            condominioCadastro.setRELATORIO_MOVIMENTACAO_DE_TERRA(0);
        }
        if (ckbGRAlojamento.isSelected()) {
            CheckBoxGR += "ckbGRAlojamento-";
        }
        if (ckbGRRefeitoria.isSelected()) {
            CheckBoxGR += "ckbGRRefeitoria-";
        }
        if (ckbGRAreaManutencao.isSelected()) {
            CheckBoxGR += "ckbGRAreaManutencao-";
        }
        if (ckbGRNaoHaveraInstalacao.isSelected()) {
            CheckBoxGR += "ckbGRNaoHaveraInstalacao-";
        }
        if (ckbGRSanitarioQuimico.isSelected()) {
            CheckBoxGR += "ckbGRSanitarioQuimico-";
        }
        if (ckbGRSistemaFossa1.isSelected()) {
            CheckBoxGR += "ckbGRSistemaFossa1-";
        }
        if (ckbGRRedeColetaDistribuicao.isSelected()) {
            CheckBoxGR += "ckbGRRedeColetaDistribuicao-";
        }
        if (ckbGRResiduosConstrucao.isSelected()) {
            CheckBoxGR += "ckbGRResiduosConstrucao-";
        }
        if (ckbGRNaoHaGeracao1.isSelected()) {
            CheckBoxGR += "ckbGRNaoHaGeracao1-";
        }
        if (ckbGRColetaPublica1.isSelected()) {
            CheckBoxGR += "ckbGRColetaPublica1-";
        }
        if (ckbGROutra.isSelected()) {
            CheckBoxGR += "ckbGROutra-";
        }
        if (ckbGROutraFormaArmazenamento.isSelected()) {
            CheckBoxGR += "ckbGROutraFormaArmazenamento-";
        }
        if (ckbGROutra.isSelected()) {
            CheckBoxGR += "ckbGROutra-";
        }
        if (ckbGRResiduosConstrucao.isSelected()) {
            CheckBoxGR += "ckbGRResiduosConstrucao-";
        }
        if (ckbGRToneisBombados.isSelected()) {
            CheckBoxGR += "ckbGRToneisBombados-";
        }
        condominioCadastro.setRELATORIO_GERENCIAMENTO_DE_RESIDUOS(CheckBoxGR);
        condominioCadastro.setRELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA(GRConsumoAguaLDia.getText());
        condominioCadastro.setRELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES(GRConsumoAguaMMes.getText());
        condominioCadastro.setRELATORIO_G_R_ESPECIFICAR_1(tfGREspecificar1.getText());
        condominioCadastro.setRELATORIO_G_R_ESPECIFICAR_2(tfGREspecificar2.getText());
        condominioCadastro.setRELATORIO_G_R_NOME_1(tfGRNome1.getText());
        condominioCadastro.setRELATORIO_G_R_NOME_2(tfGRNome2.getText());
        condominioCadastro.setRELATORIO_G_R_NUMERO_LICENCA_1(tfGRNumeroLicenca1.getText());
        condominioCadastro.setRELATORIO_G_R_NUMERO_LICENCA_2(tfGRNumeroLicenca2.getText());
        condominioCadastro.setRELATORIO_G_R_NUMERO_LICENCA_3(tfGRNDeclaracao.getText());
        if (ckbEAProveniente.isSelected()) {
            CheckBoxEA += "ckbEAProveniente";
        }
        if (ckbEACirculacao.isSelected()) {
            CheckBoxEA += "ckbEACirculacao";
        }
        if (ckbEANaoHaGeracao.isSelected()) {
            CheckBoxEA += "ckbEANaoHaGeracao";
        }
        if (ckbEAUmectacao.isSelected()) {
            CheckBoxEA += "ckbEAUmectacao";
        }
        if (ckbEAOutro.isSelected()) {
            CheckBoxEA += "ckbEAOutro";
        }
        condominioCadastro.setRELATORIO_EMISSOES_ATMOSFERICAS(CheckBoxEA);
        condominioCadastro.setRELATORIO_E_A_ESPECIFICAR(tfEAEspecificar.getText());
        condominioCadastro.setRELATORIO_ROTEIRO_DE_ACESSO(taRoteiroAcesso.getText());
        if (rbCLENadamaisexisteadeclarar.isSelected()) {
            condominioCadastro.setRELATORIO_CROQUI_PERGUNTA(0);
        }
        if (rbCLEDeclaramosoqueconstaemanexo.isSelected()) {
            condominioCadastro.setRELATORIO_CROQUI_PERGUNTA(1);
        }
        condominioCadastro.setRELATORIO_TEXTO_ANEXO(taTextoAnexo.getText());
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) dataAtual.getValue());
        condominioCadastro.setRELATORIO_DATA_ATUAL(cal);
        System.out.println(profissionalTemporario.getProfissional_id());
        condominioCadastro.setPROFISSIONAL_ID(profissionalTemporario.getProfissional_id());
        condominioCadastro.setCATEGORIA_ID(2);
        if (imagem != null) {
            try {
                // TODO add your handling code here:
                String caminho = "build/classes/imagens/";
                System.out.println(caminho);
                SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                //System.out.println(d.format(new Date()));
                File outputfile = new File(caminho + "image" + d.format(new Date()) + ".jpg");
                condominioCadastro.setRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO(outputfile.getName());
                System.out.println(outputfile.getName());
                ImageIO.write(imagem, "jpg", outputfile);
                //JOptionPane.showMessageDialog(rootPane, "Imagem enviada com sucesso");
                imagem = null;
            } catch (IOException ex) {
                Logger.getLogger(jifFormRestautante.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            condominioCadastro.setRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO("sem_imagem.jpg");
            imagem = null;
        }
        return condominioCadastro;

    }
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

    private void btCancelarAtualizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarAtualizacaoActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(rootPane, "Deseja mesmo cancelar as alterações", "Aviso", 0) == 0) {
            tpnAbasCondominios.setSelectedIndex(1); // Mudando para a PRIMEIRA aba
            btCancelarAtualizacao.setVisible(false);
            tpnAbasCondominios.setTitleAt(0, "Cadastrar Relatório Condomínio");
            btFinalizarCadastro.setToolTipText("Cadastrar");
            tpnAbasCondominios.setEnabledAt(1, true);
            btCancelarAtualizacao.setVisible(false);
            imagem = null;
            CheckBoxFAA = "";
            CheckBoxFGE = "";
            CheckBoxGR = "";
            CheckBoxEA = "";
            tfAreaUtil.setDocument(new LetrasMaiusculas());
            tfNHabitacoes.setText("");
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
            rbTipoFinanciamentoSim.setSelected(false);
            rbTipoFinanciamentoNao.setSelected(false);
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
            tfAreaDaUcAdministrador.setText("");
            tfAreaDaUcNome.setText("");
            rbAreaDaUcNao.setSelected(false);
            rbHaPatrimonioSim.setSelected(false);
            rbSuprecaoVegetacaoSim.setSelected(false);
            rbSuprecaoVegetacaoNao.setSelected(false);
            tfNIphan.setText("");
            tfSuprecaoVegetacaoDocumentoIdaf.setText("");
            rbSuprecaoVegetacaoNao.setSelected(false);
            tfLoteMenorArea.setText("");
            rbCondicoesTerrenoNao.setSelected(false);
            rbCondicoesTerrenoSim.setSelected(false);
            rbCondicoesTerrenoOpcoesExecucao.setSelected(false);
            rbCondicoesTerrenoOpcoesOutraSolucao.setSelected(false);
            tfCondicoesTerrenoEspecificar.setText("");
            rbDeclividadeNao.setSelected(false);
            rbDeclividadeSim.setSelected(false);
            tfDeclividadeEspecificar.setText("");
            tfCoordenadasUtmN.setText("");
            tfCoordenadasUtmE.setText("");
            rbISAPlanejamento.setSelected(false);
            rbISAInstalacao.setSelected(false);
            rbISAOperacao.setSelected(false);
            tfISAPrevisaoVegetacao.setText("");
            tfISAData.setText("");
            tfNEmpregados.setText("");
            rbSDPEEscoamentoSuperficial.setSelected(false);
            rbSDPERedeColetora.setSelected(false);
            rbSDPEOutros.setSelected(false);
            tfSDPEEspecificar.setText("");
            ckbFAARedePublica.setSelected(false);
            ckbFAAPoco.setSelected(false);
            ckbFAAReservatorios.setSelected(false);
            ckbFAACursoDagua.setSelected(false);
            ckbFAALago.setSelected(false);
            ckbFAACaptacao.setSelected(false);
            ckbFAANascente.setSelected(false);
            ckbFAAOutros.setSelected(false);
            ckbFAANaoSeAplica.setSelected(false);
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
            tfFAAOutrasEspecificar.setText("");
            rbMovimentacaoTerraNao.setSelected(false);
            rbMovimentacaoTerraSim.setSelected(false);
            taRoteiroAcesso.setText("");
            rbCLENadamaisexisteadeclarar.setSelected(false);
            rbCLEDeclaramosoqueconstaemanexo.setSelected(false);
            taTextoAnexo.setText("");
            tfProfissional.setText("");
            tfLeiPerimetroUrbano.setText("");
            tfPlanoDiretorUrbano.setText("");
            lblImagem.setIcon(new ImageIcon(""));
            GRConsumoAguaLDia.setText("");
            GRConsumoAguaMMes.setText("");
            ckbGRAlojamento.setSelected(false);
            ckbGRRefeitoria.setSelected(false);
            ckbGRAreaManutencao.setSelected(false);
            ckbGRNaoHaveraInstalacao.setSelected(false);
            ckbGRSanitarioQuimico.setSelected(false);
            ckbGRSistemaFossa1.setSelected(false);
            ckbGRRedeColetaDistribuicao.setSelected(false);
            ckbGRResiduosConstrucao.setSelected(false);
            ckbGRNaoHaGeracao1.setSelected(false);
            ckbGRColetaPublica1.setSelected(false);
            ckbGROutra.setSelected(false);
            ckbGROutraFormaArmazenamento.setSelected(false);
            ckbGROutra.setSelected(false);
            ckbGRResiduosConstrucao.setSelected(false);
            ckbGRToneisBombados.setSelected(false);
            tfGREspecificar1.setText("");
            tfGREspecificar2.setText("");
            tfGRNome1.setText("");
            tfGRNome2.setText("");
            tfGRNumeroLicenca1.setText("");
            tfGRNumeroLicenca2.setText("");
            tfGRNDeclaracao.setText("");
            ckbEAProveniente.setSelected(false);
            ckbEACirculacao.setSelected(false);
            ckbEANaoHaGeracao.setSelected(false);
            ckbEAUmectacao.setSelected(false);
            ckbEAOutro.setSelected(false);
            tfEAEspecificar.setText("");
            Date d = new Date();
            dataAtual.setValue(d);

        }
    }//GEN-LAST:event_btCancelarAtualizacaoActionPerformed

    public void limparCamposCadastroCondominio() {

        imagem = null;
        CheckBoxFAA = "";
        CheckBoxFGE = "";
        CheckBoxGR = "";
        CheckBoxEA = "";
        tfAreaUtil.setText("");
        tfNHabitacoes.setText("");
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
        rbTipoFinanciamentoSim.setSelected(false);
        rbTipoFinanciamentoNao.setSelected(false);
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
        tfAreaDaUcAdministrador.setText("");
        tfAreaDaUcNome.setText("");
        rbAreaDaUcNao.setSelected(false);
        rbHaPatrimonioSim.setSelected(false);
        rbSuprecaoVegetacaoSim.setSelected(false);
        rbSuprecaoVegetacaoNao.setSelected(false);
        tfNIphan.setText("");
        tfSuprecaoVegetacaoDocumentoIdaf.setText("");
        rbSuprecaoVegetacaoNao.setSelected(false);
        tfLoteMenorArea.setText("");
        rbCondicoesTerrenoNao.setSelected(false);
        rbCondicoesTerrenoSim.setSelected(false);
        rbCondicoesTerrenoOpcoesExecucao.setSelected(false);
        rbCondicoesTerrenoOpcoesOutraSolucao.setSelected(false);
        tfCondicoesTerrenoEspecificar.setText("");
        rbDeclividadeNao.setSelected(false);
        rbDeclividadeSim.setSelected(false);
        tfDeclividadeEspecificar.setText("");
        tfCoordenadasUtmN.setText("");
        tfCoordenadasUtmE.setText("");
        rbISAPlanejamento.setSelected(false);
        rbISAInstalacao.setSelected(false);
        rbISAOperacao.setSelected(false);
        tfISAPrevisaoVegetacao.setText("");
        tfISAData.setText("");
        tfNEmpregados.setText("");
        rbSDPEEscoamentoSuperficial.setSelected(false);
        rbSDPERedeColetora.setSelected(false);
        rbSDPEOutros.setSelected(false);
        tfSDPEEspecificar.setText("");
        ckbFAARedePublica.setSelected(false);
        ckbFAAPoco.setSelected(false);
        ckbFAAReservatorios.setSelected(false);
        ckbFAACursoDagua.setSelected(false);
        ckbFAALago.setSelected(false);
        ckbFAACaptacao.setSelected(false);
        ckbFAANascente.setSelected(false);
        ckbFAAOutros.setSelected(false);
        ckbFAANaoSeAplica.setSelected(false);
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
        tfFAAOutrasEspecificar.setText("");
        rbMovimentacaoTerraNao.setSelected(false);
        rbMovimentacaoTerraSim.setSelected(false);
        taRoteiroAcesso.setText("");
        rbCLENadamaisexisteadeclarar.setSelected(false);
        rbCLEDeclaramosoqueconstaemanexo.setSelected(false);
        taTextoAnexo.setText("");
        tfProfissional.setText("");
        tfLeiPerimetroUrbano.setText("");
        tfPlanoDiretorUrbano.setText("");
        lblImagem.setIcon(new ImageIcon(""));
        GRConsumoAguaLDia.setText("");
        GRConsumoAguaMMes.setText("");
        ckbGRAlojamento.setSelected(false);
        ckbGRRefeitoria.setSelected(false);
        ckbGRAreaManutencao.setSelected(false);
        ckbGRNaoHaveraInstalacao.setSelected(false);
        ckbGRSanitarioQuimico.setSelected(false);
        ckbGRSistemaFossa1.setSelected(false);
        ckbGRRedeColetaDistribuicao.setSelected(false);
        ckbGRResiduosConstrucao.setSelected(false);
        ckbGRNaoHaGeracao1.setSelected(false);
        ckbGRColetaPublica1.setSelected(false);
        ckbGROutra.setSelected(false);
        ckbGROutraFormaArmazenamento.setSelected(false);
        ckbGROutra.setSelected(false);
        ckbGRResiduosConstrucao.setSelected(false);
        ckbGRToneisBombados.setSelected(false);
        tfGREspecificar1.setText("");
        tfGREspecificar2.setText("");
        tfGRNome1.setText("");
        tfGRNome2.setText("");
        tfGRNumeroLicenca1.setText("");
        tfGRNumeroLicenca2.setText("");
        tfGRNDeclaracao.setText("");
        ckbEAProveniente.setSelected(false);
        ckbEACirculacao.setSelected(false);
        ckbEANaoHaGeracao.setSelected(false);
        ckbEAUmectacao.setSelected(false);
        ckbEAOutro.setSelected(false);
        tfEAEspecificar.setText("");
        Date d = new Date();
        dataAtual.setValue(d);

        tpnAbasCondominios.setSelectedIndex(0); // Mudando para a PRIMEIRA aba

        tpnAbasCondominios.setTitleAt(0, "Cadastrar Relatório Condomínio");
        btFinalizarCadastro.setToolTipText("Cadastrar");
        tpnAbasCondominios.setEnabledAt(1, true);
        tpnAbasCondominios.setSelectedIndex(1);
        btCancelarAtualizacao.setVisible(false);
    }

    private void btFinalizarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarCadastroActionPerformed
        if (tfNomeCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tpnAbasCondominios, "Por favor escolha o cliente", "Aviso", 2);
            tfNomeCliente.requestFocus();
        } else {
            if (tfProfissional.getText().isEmpty()) {
                JOptionPane.showMessageDialog(tpnAbasCondominios, "Por favor escolha o profissional", "Aviso", 2);
                tfProfissional.requestFocus();
            } else {
                if (tfRepresentante.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(tpnAbasCondominios, "Por favor escolha o represetante", "Aviso", 2);
                    tfRepresentante.requestFocus();
                } else {
                    if (btFinalizarCadastro.getToolTipText().equals("Cadastrar")) {
                        conexaoTabelaRelatorio.inserirNovoRPrincipal(preencherDadosCadastroCondominio());
                        buscarRelatoriosTabela();
                        limparCamposCadastroCondominio();
                    } else {
                        conexaoTabelaRelatorio.alterarRelatorio(modeloTabelaCondominio.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow()).getRELATORIO_ID(), preencherDadosCadastroCondominio());
                        buscarRelatoriosTabela();
                        limparCamposCadastroCondominio();
                    }
                }
            }
        }
    }//GEN-LAST:event_btFinalizarCadastroActionPerformed

    private void tfPalavraChaveCondominioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPalavraChaveCondominioKeyReleased
        // TODO add your handling code here:
        if (tfPalavraChaveCondominio.getText().isEmpty()) {
            modeloTabelaCondominio.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(2));
        } else {
            modeloTabelaCondominio.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(2));
            listaOriginalTemporariaRelatorio.clear();

            if (ckbCliente.isSelected()) {
                for (int i = 0; i < modeloTabelaCondominio.retornaListaRelatorioPrincipal().size(); i++) {
                    if (modeloTabelaCondominio.retornaListaRelatorioPrincipal().get(i).getCLIENTE_NOME().toLowerCase().contains(tfPalavraChaveCondominio.getText().toLowerCase())) {
                        listaOriginalTemporariaRelatorio.add(modeloTabelaCondominio.retornaListaRelatorioPrincipal().get(i));
                    }
                }
            } else if (ckbProfissional.isSelected()) {
                for (int i = 0; i < modeloTabelaCondominio.retornaListaRelatorioPrincipal().size(); i++) {
                    if (modeloTabelaCondominio.retornaListaRelatorioPrincipal().get(i).getPROFISSIONAL_NOME().toLowerCase().contains(tfPalavraChaveCondominio.getText().toLowerCase())) {
                        listaOriginalTemporariaRelatorio.add(modeloTabelaCondominio.retornaListaRelatorioPrincipal().get(i));
                    }
                }
            }

            modeloTabelaCondominio.inserirListaRelatorioPrincipal(listaOriginalTemporariaRelatorio);

        }

        tbRelatoriosCadastrados.updateUI();
    }//GEN-LAST:event_tfPalavraChaveCondominioKeyReleased

    private void btExcluirProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirProfissionalActionPerformed
        // TODO add your handling code here:
        if (tbRelatoriosCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatórios selecionados para serem deletados");
        } else if (tbRelatoriosCadastrados.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Não existem mais relatórios para serem deletados");
        } else if (JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir esse relatorio?", "Excluir cliente", 0) == 0) {
            if (tbRelatoriosCadastrados.getSelectedRow() != -1) {

                relatorioExcluir = modeloTabelaCondominio.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow());
                if (!"sem_imagem.jpg".equals(relatorioExcluir.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO())) {
                    File file = new File("build/classes/imagens/" + relatorioExcluir.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO());
                    file.delete();
                }

                conexaoTabelaRelatorio.removerRelatorio(modeloTabelaCondominio.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow()).getRELATORIO_ID());
                modeloTabelaCondominio.inserirListaRelatorioPrincipal(conexaoTabelaRelatorio.selecionarTodosRelatorios(2));

                tbRelatoriosCadastrados.updateUI();
            }
        }
    }//GEN-LAST:event_btExcluirProfissionalActionPerformed

    private void btEditarProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarProfissionalActionPerformed
        // TODO add your handling code here:
        if (tbRelatoriosCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem editados");
        } else {

            RelatorioPrincipal relatorioPrincipal = modeloTabelaCondominio.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow());

            tfAreaUtil.setText(relatorioPrincipal.getRELATORIO_AREA_UTIL());
            //relatorioPrincipal.getRELATORIO_AREA_COBERTA();
            //relatorioPrincipal.getRELATORIO_AREA_DESCOBERTA();
            tfNHabitacoes.setText(relatorioPrincipal.getRELATORIO_N_HABITACOES());
            //relatorioPrincipal.getRELATORIO_AREA_INTERVENCAO();
            //relatorioPrincipal.getRELATORIO_ALTURA_DO_TALUDE();
            tfLeiPerimetroUrbano.setText(relatorioPrincipal.getRELATORIO_LOCALIZACAO_LEI());
            tfPlanoDiretorUrbano.setText(relatorioPrincipal.getRELATORIO_LOCALIZACAO_PLANO());
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
            DecimalFormat form = new DecimalFormat("00");
            String dataInicioAtividade;
            dataInicioAtividade = form.format(relatorioPrincipal.getCLIENTE_DATA().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(relatorioPrincipal.getCLIENTE_DATA().get(Calendar.MONTH) + 1) + "/" + relatorioPrincipal.getCLIENTE_DATA().get(Calendar.YEAR);
            tfISAData.setText(dataInicioAtividade);
            clienteTemporario.setCliente_id(relatorioPrincipal.getCLIENTE_ID());
            if (relatorioPrincipal.getRELATORIO_TIPO_DE_FINANCIAMENTO() == 1) {
                rbTipoFinanciamentoSim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_TIPO_DE_FINANCIAMENTO() == 0) {
                rbTipoFinanciamentoNao.setSelected(true);
            }
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
            //relatorioPrincipal.getRELATORIO_LOCALIZACAO_LEI();
            //relatorioPrincipal.getRELATORIO_LOCALIZACAO_PLANO();
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
            tfAreaDaUcAdministrador.setText(relatorioPrincipal.getRELATORIO_AREA_DA_UC_ADMINISTRADOR());
            tfAreaDaUcNDocumento.setText(relatorioPrincipal.getRELATORIO_AREA_DA_UC_N_DOCUMENTO());
            if (relatorioPrincipal.getRELATORIO_HA_PATRIMONIO() == 1) {
                rbHaPatrimonioSim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_HA_PATRIMONIO() == 0) {
                rbHaPatrimonioNao.setSelected(true);
            }
            tfNIphan.setText(relatorioPrincipal.getRELATORIO_N_IPHAN());
            if (relatorioPrincipal.getRELATORIO_SUPRECAO_VEGETACAO() == 1) {
                rbSuprecaoVegetacaoSim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_SUPRECAO_VEGETACAO() == 0) {
                rbSuprecaoVegetacaoNao.setSelected(true);
            }
            tfSuprecaoVegetacaoDocumentoIdaf.setText(relatorioPrincipal.getRELATORIO_SUPRECAO_VEGETACAO_DOCUMENTO_IDAF());
            tfLoteMenorArea.setText(relatorioPrincipal.getRELATORIO_LOTE_DE_MENOR_AREA());
            if (relatorioPrincipal.getRELATORIO_CONDICOES_DO_TERRENO() == 1) {

                rbCondicoesTerrenoSim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_CONDICOES_DO_TERRENO() == 0) {

                rbCondicoesTerrenoNao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_CONDICOES_DO_TERRENO_OPCOES() == 0) {

                rbCondicoesTerrenoOpcoesExecucao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_CONDICOES_DO_TERRENO_OPCOES() == 1) {

                rbCondicoesTerrenoOpcoesOutraSolucao.setSelected(true);
            }
            tfCondicoesTerrenoEspecificar.setText(relatorioPrincipal.getRELATORIO_CONDICOES_DO_TERRENO_ESPECIFICAR());
            if (relatorioPrincipal.getRELATORIO_DECLIVIDADE() == 1) {

                rbDeclividadeSim.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_DECLIVIDADE() == 0) {

                rbDeclividadeNao.setSelected(true);
            }
            tfDeclividadeEspecificar.setText(relatorioPrincipal.getRELATORIO_DECLIVIDADE_ESPECIFICAR());
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

            if (relatorioPrincipal.getRELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE() == 0) {
                rbSDPEEscoamentoSuperficial.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE() == 1) {
                rbSDPERedeColetora.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_SISTEMA_DE_DRENAGEM_PLUVIAL_EXISTENTE() == 2) {
                rbSDPEOutros.setSelected(true);
            }
            tfSDPEEspecificar.setText(relatorioPrincipal.getRELATORIO_S_D_P_E_ESPECIFICAR());
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
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAANascente")) {
                ckbFAANascente.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAAOutros")) {
                ckbFAAOutros.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_FONTES_DE_ABASTECIMENTO_AGUA().contains("ckbFAANaoSeAplica")) {
                ckbFAANaoSeAplica.setSelected(true);
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
            tfFAAOutrasEspecificar.setText(relatorioPrincipal.getRELATORIO_F_A_A_OUTRAS_ESPECIFICAR());
            tfFAAConsumoDeAgua.setText(relatorioPrincipal.getRELATORIO_F_A_A_CONSUMO_DE_AGUA());
            tfFAANDocumentoOutorgaRh.setText(relatorioPrincipal.getRELATORIO_F_A_A_N_DOCUMENTO_OUTORGA_R_H());
            tfFAANDocumentoCertidaoDo.setText(relatorioPrincipal.getRELATORIO_F_A_A_N_DOCUMENTO_CERTIDAO_D_O());

            if (relatorioPrincipal.getRELATORIO_MOVIMENTACAO_DE_TERRA() == 1) {
                rbMovimentacaoTerraNao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_MOVIMENTACAO_DE_TERRA() == 0) {
                rbMovimentacaoTerraSim.setSelected(true);
            }

            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRAlojamento")) {
                ckbGRAlojamento.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRRefeitoria")) {
                ckbGRRefeitoria.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRAreaManutencao")) {
                ckbGRAreaManutencao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRNaoHaveraInstalacao")) {
                ckbGRNaoHaveraInstalacao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRSanitarioQuimico")) {
                ckbGRSanitarioQuimico.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRSistemaFossa1")) {
                ckbGRSistemaFossa1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRRedeColetaDistribuicao")) {
                ckbGRRedeColetaDistribuicao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRResiduosConstrucao")) {
                ckbGRResiduosConstrucao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRNaoHaGeracao1")) {
                ckbGRNaoHaGeracao1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRColetaPublica1")) {
                ckbGRColetaPublica1.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGROutra")) {
                ckbGROutra.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGROutraFormaArmazenamento")) {
                ckbGROutraFormaArmazenamento.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGROutra")) {
                ckbGROutra.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRResiduosConstrucao")) {
                ckbGRResiduosConstrucao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_GERENCIAMENTO_DE_RESIDUOS().contains("ckbGRToneisBombados")) {
                ckbGRToneisBombados.setSelected(true);
            }
            GRConsumoAguaLDia.setText(relatorioPrincipal.getRELATORIO_G_R_CONSUMEO_DE_AGUA_L_DIA());
            GRConsumoAguaMMes.setText(relatorioPrincipal.getRELATORIO_G_R_CONSUMEO_DE_AGUA_M_MES());
            tfGREspecificar1.setText(relatorioPrincipal.getRELATORIO_G_R_ESPECIFICAR_1());
            tfGREspecificar2.setText(relatorioPrincipal.getRELATORIO_G_R_ESPECIFICAR_2());
            tfGRNome1.setText(relatorioPrincipal.getRELATORIO_G_R_NOME_1());
            tfGRNome2.setText(relatorioPrincipal.getRELATORIO_G_R_NOME_2());
            tfGRNumeroLicenca1.setText(relatorioPrincipal.getRELATORIO_G_R_NUMERO_LICENCA_1());
            tfGRNumeroLicenca2.setText(relatorioPrincipal.getRELATORIO_G_R_NUMERO_LICENCA_2());
            tfGRNDeclaracao.setText(relatorioPrincipal.getRELATORIO_G_R_NUMERO_LICENCA_3());
            if (relatorioPrincipal.getRELATORIO_EMISSOES_ATMOSFERICAS().contains("ckbEAProveniente")) {
                ckbEAProveniente.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_EMISSOES_ATMOSFERICAS().contains("ckbEACirculacao")) {
                ckbEACirculacao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_EMISSOES_ATMOSFERICAS().contains("ckbEANaoHaGeracao")) {
                ckbEANaoHaGeracao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_EMISSOES_ATMOSFERICAS().contains("ckbEAUmectacao")) {
                ckbEAUmectacao.setSelected(true);
            }
            if (relatorioPrincipal.getRELATORIO_EMISSOES_ATMOSFERICAS().contains("ckbEAOutro")) {
                ckbEAOutro.setSelected(true);
            }
            tfEAEspecificar.setText(relatorioPrincipal.getRELATORIO_E_A_ESPECIFICAR());
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
            tfProfissional.setText(relatorioPrincipal.getPROFISSIONAL_NOME());
            profissionalTemporario.setProfissional_id(relatorioPrincipal.getPROFISSIONAL_ID());
            tfRepresentante.setText(relatorioPrincipal.getREPRESENTANTE_NOME());
            representanteTemporario.setRepresentante_id(relatorioPrincipal.getREPRESENTANTE_ID());
            relatorioPrincipal.setCATEGORIA_ID(2);
            if (relatorioPrincipal.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO() != null) {
                try {
                    String caminho = "build/classes/imagens/";

                    File outputfile = new File(caminho + relatorioPrincipal.getRELATORIO_CROQUI_DE_LOCALIZACAO_EMPREENDIMENTO());
                    imagem = ManipularImagem.setImagemDimensao(outputfile.getAbsolutePath(), 600, 600);

                    lblImagem.setIcon(new ImageIcon(imagem));

                } catch (Exception ex) {
                    // System.out.println(ex.printStackTrace().toString());
                }
            }
            tpnAbasCondominios.setSelectedIndex(0); // Mudando para a PRIMEIRA aba

            btFinalizarCadastro.setToolTipText("Atualizar");
            tpnAbasCondominios.setTitleAt(0, "Atualizar dados");
            tpnAbasCondominios.setEnabledAt(1, false);
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

    private void rbHaPatrimonioSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbHaPatrimonioSimMouseClicked
        rbHaPatrimonioNao.setSelected(false);
    }//GEN-LAST:event_rbHaPatrimonioSimMouseClicked

    private void rbHaPatrimonioNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbHaPatrimonioNaoMouseClicked
        rbHaPatrimonioSim.setSelected(false);
    }//GEN-LAST:event_rbHaPatrimonioNaoMouseClicked

    private void rbSuprecaoVegetacaoSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSuprecaoVegetacaoSimMouseClicked
        rbSuprecaoVegetacaoNao.setSelected(false);
    }//GEN-LAST:event_rbSuprecaoVegetacaoSimMouseClicked

    private void rbSuprecaoVegetacaoNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSuprecaoVegetacaoNaoMouseClicked
        rbSuprecaoVegetacaoSim.setSelected(false);
    }//GEN-LAST:event_rbSuprecaoVegetacaoNaoMouseClicked

    private void rbCondicoesTerrenoSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCondicoesTerrenoSimMouseClicked
        rbCondicoesTerrenoNao.setSelected(false);
    }//GEN-LAST:event_rbCondicoesTerrenoSimMouseClicked

    private void rbCondicoesTerrenoNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCondicoesTerrenoNaoMouseClicked
        rbCondicoesTerrenoSim.setSelected(false);
    }//GEN-LAST:event_rbCondicoesTerrenoNaoMouseClicked

    private void rbDeclividadeSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbDeclividadeSimMouseClicked
        rbDeclividadeNao.setSelected(false);
    }//GEN-LAST:event_rbDeclividadeSimMouseClicked

    private void rbDeclividadeNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbDeclividadeNaoMouseClicked
        rbDeclividadeSim.setSelected(false);
    }//GEN-LAST:event_rbDeclividadeNaoMouseClicked

    private void rbCondicoesTerrenoOpcoesExecucaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCondicoesTerrenoOpcoesExecucaoMouseClicked
        rbCondicoesTerrenoOpcoesOutraSolucao.setSelected(false);
    }//GEN-LAST:event_rbCondicoesTerrenoOpcoesExecucaoMouseClicked

    private void rbCondicoesTerrenoOpcoesOutraSolucaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCondicoesTerrenoOpcoesOutraSolucaoMouseClicked
        rbCondicoesTerrenoOpcoesExecucao.setSelected(false);
    }//GEN-LAST:event_rbCondicoesTerrenoOpcoesOutraSolucaoMouseClicked

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

    private void rbSDPEEscoamentoSuperficialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSDPEEscoamentoSuperficialMouseClicked
        rbSDPERedeColetora.setSelected(false);
        rbSDPEOutros.setSelected(false);
    }//GEN-LAST:event_rbSDPEEscoamentoSuperficialMouseClicked

    private void rbSDPERedeColetoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSDPERedeColetoraMouseClicked
        rbSDPEEscoamentoSuperficial.setSelected(false);
        rbSDPEOutros.setSelected(false);
    }//GEN-LAST:event_rbSDPERedeColetoraMouseClicked

    private void rbSDPEOutrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSDPEOutrosMouseClicked
        rbSDPERedeColetora.setSelected(false);
        rbSDPEEscoamentoSuperficial.setSelected(false);
    }//GEN-LAST:event_rbSDPEOutrosMouseClicked

    private void rbMovimentacaoTerraSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbMovimentacaoTerraSimMouseClicked
        rbMovimentacaoTerraNao.setSelected(false);
    }//GEN-LAST:event_rbMovimentacaoTerraSimMouseClicked

    private void rbMovimentacaoTerraNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbMovimentacaoTerraNaoMouseClicked
        rbMovimentacaoTerraSim.setSelected(false);
    }//GEN-LAST:event_rbMovimentacaoTerraNaoMouseClicked

    private void rbCLENadamaisexisteadeclararMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCLENadamaisexisteadeclararMouseClicked
        rbCLEDeclaramosoqueconstaemanexo.setSelected(false);
    }//GEN-LAST:event_rbCLENadamaisexisteadeclararMouseClicked

    private void rbCLEDeclaramosoqueconstaemanexoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCLEDeclaramosoqueconstaemanexoMouseClicked
        rbCLENadamaisexisteadeclarar.setSelected(false);
    }//GEN-LAST:event_rbCLEDeclaramosoqueconstaemanexoMouseClicked

    private void btEditarProfissional1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarProfissional1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btEditarProfissional1ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:ddd
        if (tbRelatoriosCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem imprimidos");
        } else {

            RelatorioPrincipal condominio = modeloTabelaCondominio.retornaListaRelatorioPrincipal().get(tbRelatoriosCadastrados.getSelectedRow());
            condominio.getRELATORIO_ID();

            DecimalFormat form = new DecimalFormat("00");
            String data;
            data = form.format(condominio.getRELATORIO_DATA_ATUAL().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(condominio.getRELATORIO_DATA_ATUAL().get(Calendar.MONTH) + 1) + "/" + condominio.getRELATORIO_DATA_ATUAL().get(Calendar.YEAR);

            DecimalFormat form2 = new DecimalFormat("00");
            String data_i_s_a_a;
            data_i_s_a_a = form2.format(condominio.getRELATORIO_I_S_A_DATA().get(Calendar.DAY_OF_MONTH)) + "/" + form2.format(condominio.getRELATORIO_I_S_A_DATA().get(Calendar.MONTH) + 1) + "/" + condominio.getRELATORIO_I_S_A_DATA().get(Calendar.YEAR);

            try {
                //usando a clsse HashMap para criar um filtro
                //  JOptionPane.showMessageDialog(rootPane, recibo.getRecibo_id());
                HashMap filtro = new HashMap();
                filtro.put("id", condominio.getRELATORIO_ID());
                filtro.put("data", data);
                filtro.put("data_i_s_a_a", data_i_s_a_a);
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("build/classes/reports/Condominio.jasper", filtro, conexao);
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

    private void tfLoteMenorAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLoteMenorAreaFocusLost
        String a = tfLoteMenorArea.getText();
        String d = a.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".").replace("m²", "");
        BigDecimal valor = new BigDecimal(d);
        //NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat nf = new DecimalFormat();
        nf.applyPattern("#,##0.00");
        String formatado = nf.format(valor);
        tfLoteMenorArea.setText(formatado + " m²");
    }//GEN-LAST:event_tfLoteMenorAreaFocusLost

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

    private void tfFAAConsumoDeAguaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFAAConsumoDeAguaFocusLost
        String a = tfFAAConsumoDeAgua.getText();
        String d = a.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".").replace("m²", "").replace("L/hab.dia", "");
        //BigDecimal valor = new BigDecimal(d);
        //NumberFormat nf = NumberFormat.getCurrencyInstance();
        //DecimalFormat nf = new DecimalFormat();
        //nf.applyPattern("#,##0.00");
        //String formatado = nf.format(valor);
        tfFAAConsumoDeAgua.setText(d + " L/hab.dia");         
    }//GEN-LAST:event_tfFAAConsumoDeAguaFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField GRConsumoAguaLDia;
    private javax.swing.JTextField GRConsumoAguaMMes;
    private javax.swing.JButton btCancelarAtualizacao;
    private javax.swing.JButton btEditarProfissional;
    private javax.swing.JButton btEditarProfissional1;
    private javax.swing.JButton btExcluirProfissional;
    private javax.swing.JButton btFinalizarCadastro;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JCheckBox ckbCliente;
    private javax.swing.JCheckBox ckbEACirculacao;
    private javax.swing.JCheckBox ckbEANaoHaGeracao;
    private javax.swing.JCheckBox ckbEAOutro;
    private javax.swing.JCheckBox ckbEAProveniente;
    private javax.swing.JCheckBox ckbEAUmectacao;
    private javax.swing.JCheckBox ckbFAACaptacao;
    private javax.swing.JCheckBox ckbFAACertidao;
    private javax.swing.JCheckBox ckbFAACursoDagua;
    private javax.swing.JCheckBox ckbFAAEstadual;
    private javax.swing.JCheckBox ckbFAAFederal;
    private javax.swing.JCheckBox ckbFAALago;
    private javax.swing.JCheckBox ckbFAANaoSeAplica;
    private javax.swing.JCheckBox ckbFAANascente;
    private javax.swing.JCheckBox ckbFAAOpcaoLonga;
    private javax.swing.JCheckBox ckbFAAOutorga;
    private javax.swing.JCheckBox ckbFAAOutros;
    private javax.swing.JCheckBox ckbFAAPoco;
    private javax.swing.JCheckBox ckbFAARedePublica;
    private javax.swing.JCheckBox ckbFAAReservatorios;
    private javax.swing.JCheckBox ckbGRAlojamento;
    private javax.swing.JCheckBox ckbGRAreaManutencao;
    private javax.swing.JCheckBox ckbGRColetaPublica1;
    private javax.swing.JCheckBox ckbGRNaoHaGeracao1;
    private javax.swing.JCheckBox ckbGRNaoHaveraInstalacao;
    private javax.swing.JCheckBox ckbGROutra;
    private javax.swing.JCheckBox ckbGROutraFormaArmazenamento;
    private javax.swing.JCheckBox ckbGRRedeColetaDistribuicao;
    private javax.swing.JCheckBox ckbGRRefeitoria;
    private javax.swing.JCheckBox ckbGRResiduosConstrucao;
    private javax.swing.JCheckBox ckbGRSanitarioQuimico;
    private javax.swing.JCheckBox ckbGRSistemaFossa1;
    private javax.swing.JCheckBox ckbGRToneisBombados;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
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
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
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
    private javax.swing.JPanel pnCondominio;
    private com.toedter.calendar.JDayChooser pnDataAtual;
    private javax.swing.JRadioButton rbAreaDaUcNao;
    private javax.swing.JRadioButton rbAreaDaUcSim;
    private javax.swing.JRadioButton rbCLEDeclaramosoqueconstaemanexo;
    private javax.swing.JRadioButton rbCLENadamaisexisteadeclarar;
    private javax.swing.JRadioButton rbCondicoesTerrenoNao;
    private javax.swing.JRadioButton rbCondicoesTerrenoOpcoesExecucao;
    private javax.swing.JRadioButton rbCondicoesTerrenoOpcoesOutraSolucao;
    private javax.swing.JRadioButton rbCondicoesTerrenoSim;
    private javax.swing.JRadioButton rbDeclividadeNao;
    private javax.swing.JRadioButton rbDeclividadeSim;
    private javax.swing.JRadioButton rbHaPatrimonioNao;
    private javax.swing.JRadioButton rbHaPatrimonioSim;
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
    private javax.swing.JRadioButton rbMovimentacaoTerraNao;
    private javax.swing.JRadioButton rbMovimentacaoTerraSim;
    private javax.swing.JRadioButton rbSDPEEscoamentoSuperficial;
    private javax.swing.JRadioButton rbSDPEOutros;
    private javax.swing.JRadioButton rbSDPERedeColetora;
    private javax.swing.JRadioButton rbSuprecaoVegetacaoNao;
    private javax.swing.JRadioButton rbSuprecaoVegetacaoSim;
    private javax.swing.JRadioButton rbTipoFinanciamentoNao;
    private javax.swing.JRadioButton rbTipoFinanciamentoSim;
    private javax.swing.JTextArea taRoteiroAcesso;
    private javax.swing.JTextArea taTextoAnexo;
    private javax.swing.JTable tbRelatoriosCadastrados;
    private javax.swing.JTextField tfAreaDaUcAdministrador;
    private javax.swing.JTextField tfAreaDaUcNDocumento;
    private javax.swing.JTextField tfAreaDaUcNome;
    private javax.swing.JTextField tfAreaUtil;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCep;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfCondicoesTerrenoEspecificar;
    private javax.swing.JTextField tfCoordenadasUtmE;
    private javax.swing.JTextField tfCoordenadasUtmN;
    private javax.swing.JTextField tfCpfCnpj;
    private javax.swing.JTextField tfDeclividadeEspecificar;
    private javax.swing.JTextField tfEAEspecificar;
    private javax.swing.JTextField tfEndereco;
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
    private javax.swing.JTextField tfGREspecificar1;
    private javax.swing.JTextField tfGREspecificar2;
    private javax.swing.JTextField tfGRNDeclaracao;
    private javax.swing.JTextField tfGRNome1;
    private javax.swing.JTextField tfGRNome2;
    private javax.swing.JTextField tfGRNumeroLicenca1;
    private javax.swing.JTextField tfGRNumeroLicenca2;
    private javax.swing.JTextField tfISAData;
    private javax.swing.JTextField tfISAPrevisaoVegetacao;
    private javax.swing.JTextField tfInseridoEmAreaOutraEspecificar;
    private javax.swing.JTextField tfLeiPerimetroUrbano;
    private javax.swing.JTextField tfLoteMenorArea;
    private javax.swing.JTextField tfNEmpregados;
    private javax.swing.JTextField tfNHabitacoes;
    private javax.swing.JTextField tfNIphan;
    private javax.swing.JTextField tfNomeCliente;
    private javax.swing.JTextField tfNomeFantasia;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfPalavraChaveCondominio;
    private javax.swing.JTextField tfPlanoDiretorUrbano;
    private javax.swing.JTextField tfProfissional;
    private javax.swing.JTextField tfRepresentante;
    private javax.swing.JTextField tfSDPEEspecificar;
    private javax.swing.JTextField tfSuprecaoVegetacaoDocumentoIdaf;
    private javax.swing.JTextField tfTelefone;
    private javax.swing.JTabbedPane tpnAbasCondominios;
    // End of variables declaration//GEN-END:variables
}
