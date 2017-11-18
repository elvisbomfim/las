/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bancodedados.ClienteBD;
import bancodedados.ConexaoPDF;
import bancodedados.ContratanteBD;
import bancodedados.ProfissionalBD;
import bancodedados.ReciboBD;
import bancodedados.RepresentanteBD;
import bancodedados.RequerimentoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.tabelas.ModeloTabelaClientes;
import modelo.tabelas.ModeloTabelaContratantes;
import modelo.tabelas.ModeloTabelaProfissionais;
import modelo.tabelas.ModeloTabelaRecibos;
import modelo.tabelas.ModeloTabelaRequerimentos;
import modelos.Cliente;
import modelos.Contratante;
import modelos.Profissional;
import modelos.Recibo;
import modelos.Representante;
import modelos.Requerimento;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;

/**
 *
 * @author Jean
 */
public class jifFormRequerimento extends javax.swing.JInternalFrame {

    //DateField dataInicioOperacao = CalendarFactory.createDateField();
    DateField dataAtual = CalendarFactory.createDateField();
    /**
     * Definição de ArrayList's
     */
    ArrayList<Requerimento> listaOriginalTemporariaRequerimento = new ArrayList();

    /**
     * Objetos temporarios
     */
    Requerimento requerimentoCadastro = new Requerimento();
    Cliente clienteTemporario = new Cliente();
    Representante representanteTemporario1 = new Representante();
    Representante representanteTemporario2 = new Representante();
    Profissional profissionalTemporario1 = new Profissional();
    Profissional profissionalTemporario2 = new Profissional();

    /**
     * Classes de definição de modelos de tabela
     */
    ModeloTabelaRequerimentos modeloTabelaRequerimentos = new ModeloTabelaRequerimentos();
    ModeloTabelaClientes modeloTabelaClientes;
    ModeloTabelaContratantes modeloTabelaContratantes;
    ModeloTabelaProfissionais modeloTabelaProfissionais;

    //PDF Conexao
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Objetos temporarios
     */
    RequerimentoBD conexaoTabelaRequerimentos = new RequerimentoBD();
    ClienteBD conexaoTabelaClientes = new ClienteBD();
    ProfissionalBD conexaoTabelaProfissionais = new ProfissionalBD();
    RepresentanteBD conexaoTabelaRepresentante = new RepresentanteBD();

    public jifFormRequerimento() {
        initComponents();
        conexao = ConexaoPDF.conector();
        Calendar cal = Calendar.getInstance();
//        dataInicioOperacao.setBaseDate(cal.getTime());
 //       pnDataAtual.add(dataInicioOperacao);
        //Definindo o botão DateField (Data Inicio do Semestre) para seleção de uma data e atribuindo uma ação de mudança à ele.
 //       dataInicioOperacao.setSize((pnDataAtual.getWidth()), (pnDataAtual.getHeight()));

        tbRequerimento.setModel(modeloTabelaRequerimentos);

        buscarRequerimentosTabela();

    }

    public void buscarRequerimentosTabela() {

        modeloTabelaRequerimentos.inserirListaRequerimentos(conexaoTabelaRequerimentos.selecionarTodosRequerimentos());
        listaOriginalTemporariaRequerimento = modeloTabelaRequerimentos.retornaListaRequerimentos();
        tbRequerimento.updateUI();
    }

    public Requerimento preencherDadosCadastroRequerimento() {

        requerimentoCadastro.setCliente_id(clienteTemporario.getCliente_id());
        System.out.println(clienteTemporario.getCliente_id());
        requerimentoCadastro.setRepresentante_id1(representanteTemporario1.getRepresentante_id());
        requerimentoCadastro.setRepresentante_id2(representanteTemporario2.getRepresentante_id());
        requerimentoCadastro.setProfissional_id1(profissionalTemporario1.getProfissional_id());
        requerimentoCadastro.setProfissional_id2(profissionalTemporario2.getProfissional_id());

        if (rbAA.isSelected()) {
            requerimentoCadastro.setRequerimento_tipo(0);
        }
        if (rbLAS.isSelected()) {
            requerimentoCadastro.setRequerimento_tipo(1);
        }
        if (rbLMP.isSelected()) {
            requerimentoCadastro.setRequerimento_tipo(2);
        }
        if (rbLMI.isSelected()) {
            requerimentoCadastro.setRequerimento_tipo(3);
        }
        if (rbLMO.isSelected()) {
            requerimentoCadastro.setRequerimento_tipo(4);
        }
        if (rbLMU.isSelected()) {
            requerimentoCadastro.setRequerimento_tipo(5);
        }
        if (rbLAR.isSelected()) {
            requerimentoCadastro.setRequerimento_tipo(6);
        }
        if (rbRLMO.isSelected()) {
            requerimentoCadastro.setRequerimento_tipo(7);
        }
        if (rbPlanejamento.isSelected()) {
            requerimentoCadastro.setRequerimento_fase_empreendimento(0);
        }
        if (rbInstalacao.isSelected()) {
            requerimentoCadastro.setRequerimento_fase_empreendimento(1);
        }
        if (rbOperacao.isSelected()) {
            requerimentoCadastro.setRequerimento_fase_empreendimento(2);
        }
        requerimentoCadastro.setRequerimento_cliente(tfRequerimentoCliente.getText());
        requerimentoCadastro.setRequerimento_representante1(tfRequerimentoRepresentante1.getText());
        requerimentoCadastro.setRequerimento_representante2(tfRequerimentoRepresentante2.getText());
        requerimentoCadastro.setRequerimento_profissional1(tfRequerimentoProfissional1.getText());
        requerimentoCadastro.setRequerimento_profissional2(tfRequerimentoProfissional2.getText());
//        Calendar cal = Calendar.getInstance();
//        cal.setTime((Date) dataInicioOperacao.getValue());
//        requerimentoCadastro.setRequerimento_fase_empreendimento_data(cal);
        Calendar cal1 = Calendar.getInstance();
        dataAtual.setBaseDate(cal1.getTime());
 //       requerimentoCadastro.setRequerimento_data(cal);
        requerimentoCadastro.setRequerimento_num_processo_protocolo(tfRequerimentoProtocolo.getText());
        requerimentoCadastro.setRequerimento_num_licenca_anterior(tfRequerimentoLicencaiAnterior.getText());
        requerimentoCadastro.setRequerimento_latn(tfRequerimentoLatN.getText());
        requerimentoCadastro.setRequerimento_late(tfRequerimentoLatE.getText());
        requerimentoCadastro.setRequerimento_sema(tfRequerimentoSema.getText());
        //DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        // Date d = new Date();
        //Calendar c = Calendar.getInstance();

        //traCadastro.setTra_data(df.format(c.getTime()));
        return requerimentoCadastro;

    }

    public void limparCamposCadastroRequerimento() {
        tfRequerimentoCliente.setText("");
        tfRequerimentoProfissional1.setText("");
        tfRequerimentoProfissional2.setText("");
        tfRequerimentoRepresentante1.setText("");
        tfRequerimentoRepresentante2.setText("");
        //tfRequerimentoDataOperacao.setText("");
        tfRequerimentoProtocolo.setText("");
        tfRequerimentoLicencaiAnterior.setText("");
        rbAA.setSelected(false);
        rbInstalacao.setSelected(false);
        rbLAR.setSelected(false);
        rbLAS.setSelected(false);
        rbLMI.setSelected(false);
        rbLMO.setSelected(false);
        rbLMP.setSelected(false);
        rbLMU.setSelected(false);
        rbOperacao.setSelected(false);
        rbPlanejamento.setSelected(false);
        rbRLMO.setSelected(false);
        //cbTraEstado.setSelectedIndex(0);
        tfRequerimentoLatN.setText("");
        tfRequerimentoLatE.setText("");
        tfRequerimentoSema.setText("");

        tpnAbasRequerimentos.setTitleAt(0, "Cadastrar Novo Requerimento");
        btFinalizarCadastroRequerimento.setToolTipText("Cadastrar");
        tpnAbasRequerimentos.setEnabledAt(1, true);
        tpnAbasRequerimentos.setSelectedIndex(1);
        btCancelarAtualizacaoRequerimento.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        tpnAbasRequerimentos = new javax.swing.JTabbedPane();
        pnCadastrarNovoRequerimento = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfRequerimentoRepresentante1 = new javax.swing.JTextField();
        tfRequerimentoRepresentante2 = new javax.swing.JTextField();
        tfRequerimentoCliente = new javax.swing.JTextField();
        tfRequerimentoProfissional1 = new javax.swing.JTextField();
        tfRequerimentoProfissional2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rbAA = new javax.swing.JRadioButton();
        rbLAS = new javax.swing.JRadioButton();
        rbLMP = new javax.swing.JRadioButton();
        rbLMI = new javax.swing.JRadioButton();
        rbLMO = new javax.swing.JRadioButton();
        rbLMU = new javax.swing.JRadioButton();
        rbLAR = new javax.swing.JRadioButton();
        rbRLMO = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        rbPlanejamento = new javax.swing.JRadioButton();
        rbInstalacao = new javax.swing.JRadioButton();
        rbOperacao = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        tfRequerimentoProtocolo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfRequerimentoLicencaiAnterior = new javax.swing.JTextField();
        btFinalizarCadastroRequerimento = new javax.swing.JButton();
        btCancelarAtualizacaoRequerimento = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        tfRequerimentoLatN = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfRequerimentoLatE = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfRequerimentoSema = new javax.swing.JTextField();
        pnGerenciarRequerimentos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tfPalavraChaveRequerimento = new javax.swing.JTextField();
        ckbTraCliente = new javax.swing.JCheckBox();
        ckbTraProfissional = new javax.swing.JCheckBox();
        ckbTraRepresentante = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRequerimento = new javax.swing.JTable();
        btEditarRequerimento = new javax.swing.JButton();
        btExcluirRequerimento = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setTitle("Requerimento");

        jLabel1.setText("Cliente:");

        jLabel2.setText("1° Representante:");

        jLabel3.setText("2° Representante:");

        jLabel4.setText("1° Profissional:");

        jLabel5.setText("2° Profissional:");

        tfRequerimentoRepresentante1.setEditable(false);
        tfRequerimentoRepresentante1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRequerimentoRepresentante1MouseClicked(evt);
            }
        });

        tfRequerimentoRepresentante2.setEditable(false);
        tfRequerimentoRepresentante2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRequerimentoRepresentante2MouseClicked(evt);
            }
        });

        tfRequerimentoCliente.setEditable(false);
        tfRequerimentoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRequerimentoClienteMouseClicked(evt);
            }
        });

        tfRequerimentoProfissional1.setEditable(false);
        tfRequerimentoProfissional1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRequerimentoProfissional1MouseClicked(evt);
            }
        });

        tfRequerimentoProfissional2.setEditable(false);
        tfRequerimentoProfissional2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRequerimentoProfissional2MouseClicked(evt);
            }
        });

        jLabel6.setText("Tipo:");

        rbAA.setText("Autorização Ambiental – A.A ");
        rbAA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAAActionPerformed(evt);
            }
        });

        rbLAS.setText("Licença Ambiental Simplificada - LAS ");
        rbLAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLASActionPerformed(evt);
            }
        });

        rbLMP.setText("Licença Municipal Prévia - LMP ");
        rbLMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLMPActionPerformed(evt);
            }
        });

        rbLMI.setText("Licença Municipal de Instalação - LMI ");
        rbLMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLMIActionPerformed(evt);
            }
        });

        rbLMO.setText("Licença Municipal Operação - LMO ");
        rbLMO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLMOActionPerformed(evt);
            }
        });

        rbLMU.setText("Licença Municipal Única - LMU ");
        rbLMU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLMUActionPerformed(evt);
            }
        });

        rbLAR.setText("Licença de Ambiental de Regularização - LAR");
        rbLAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLARActionPerformed(evt);
            }
        });

        rbRLMO.setText("Renovação de Licença Municipal de Operação - LMO ");
        rbRLMO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbRLMOActionPerformed(evt);
            }
        });

        jLabel7.setText("Fase do Empreendimento:");

        rbPlanejamento.setText("Planejamento");
        rbPlanejamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPlanejamentoActionPerformed(evt);
            }
        });

        rbInstalacao.setText("Instalaçao");
        rbInstalacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInstalacaoActionPerformed(evt);
            }
        });

        rbOperacao.setText("Operação");
        rbOperacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOperacaoActionPerformed(evt);
            }
        });

        jLabel8.setText("Nϊmero do processo/protocolo:");

        jLabel9.setText("Número da Licenηa Anterior:");

        btFinalizarCadastroRequerimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/complete-file.png"))); // NOI18N
        btFinalizarCadastroRequerimento.setToolTipText("Cadastrar");
        btFinalizarCadastroRequerimento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFinalizarCadastroRequerimento.setPreferredSize(new java.awt.Dimension(150, 150));
        btFinalizarCadastroRequerimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarCadastroRequerimentoActionPerformed(evt);
            }
        });

        btCancelarAtualizacaoRequerimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_edit-not-validated_85308.png"))); // NOI18N
        btCancelarAtualizacaoRequerimento.setToolTipText("Cancelar");
        btCancelarAtualizacaoRequerimento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelarAtualizacaoRequerimento.setPreferredSize(new java.awt.Dimension(150, 150));
        btCancelarAtualizacaoRequerimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarAtualizacaoRequerimentoActionPerformed(evt);
            }
        });

        jLabel12.setText("Coordenadas Geogrαficas/UTM: LAT(N):");

        tfRequerimentoLatN.setEditable(false);

        jLabel13.setText("Coordenadas Geogrαficas/UTM: LAT(E):");

        tfRequerimentoLatE.setEditable(false);

        jLabel14.setText("(Espaço reservado a SEMA):");

        javax.swing.GroupLayout pnCadastrarNovoRequerimentoLayout = new javax.swing.GroupLayout(pnCadastrarNovoRequerimento);
        pnCadastrarNovoRequerimento.setLayout(pnCadastrarNovoRequerimentoLayout);
        pnCadastrarNovoRequerimentoLayout.setHorizontalGroup(
            pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                        .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfRequerimentoCliente)
                            .addComponent(tfRequerimentoRepresentante1)))
                    .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                        .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfRequerimentoRepresentante2)
                            .addComponent(tfRequerimentoProfissional1)
                            .addComponent(tfRequerimentoProfissional2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                        .addComponent(btCancelarAtualizacaoRequerimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btFinalizarCadastroRequerimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                        .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbAA)
                                    .addComponent(rbLMP)
                                    .addComponent(rbLMO)
                                    .addComponent(rbLAR))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbRLMO)
                                    .addComponent(rbLMU)
                                    .addComponent(rbLMI)
                                    .addComponent(rbLAS)))
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                        .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfRequerimentoProtocolo))
                            .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(14, 14, 14)
                                .addComponent(tfRequerimentoLicencaiAnterior))
                            .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                                        .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfRequerimentoSema)
                                            .addComponent(tfRequerimentoLatE)))
                                    .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                                            .addComponent(rbPlanejamento)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rbInstalacao)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rbOperacao))
                                        .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfRequerimentoLatN, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(83, 83, 83)))
                        .addGap(181, 181, 181)))
                .addContainerGap())
        );
        pnCadastrarNovoRequerimentoLayout.setVerticalGroup(
            pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarNovoRequerimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfRequerimentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfRequerimentoRepresentante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfRequerimentoRepresentante2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfRequerimentoProfissional1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfRequerimentoProfissional2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAA)
                    .addComponent(rbLAS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLMP)
                    .addComponent(rbLMI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLMO)
                    .addComponent(rbLMU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLAR)
                    .addComponent(rbRLMO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbPlanejamento)
                    .addComponent(rbInstalacao)
                    .addComponent(rbOperacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfRequerimentoProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfRequerimentoLicencaiAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tfRequerimentoLatN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfRequerimentoLatE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfRequerimentoSema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(pnCadastrarNovoRequerimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btFinalizarCadastroRequerimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancelarAtualizacaoRequerimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tpnAbasRequerimentos.addTab("Cadastrar Novos Requerimentos", pnCadastrarNovoRequerimento);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro de Busca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel10.setText("Palavra-chave:");

        tfPalavraChaveRequerimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPalavraChaveRequerimentoKeyReleased(evt);
            }
        });

        ckbTraCliente.setText("Cliente");

        ckbTraProfissional.setText("Profissional");

        ckbTraRepresentante.setText("Representante");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ckbTraCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbTraProfissional)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbTraRepresentante)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tfPalavraChaveRequerimento))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfPalavraChaveRequerimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbTraCliente)
                    .addComponent(ckbTraProfissional)
                    .addComponent(ckbTraRepresentante))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tbRequerimento.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbRequerimento);

        btEditarRequerimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit-file.png"))); // NOI18N
        btEditarRequerimento.setToolTipText("Editar");
        btEditarRequerimento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditarRequerimento.setPreferredSize(new java.awt.Dimension(150, 150));
        btEditarRequerimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarRequerimentoActionPerformed(evt);
            }
        });

        btExcluirRequerimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete-file.png"))); // NOI18N
        btExcluirRequerimento.setToolTipText("Excluir");
        btExcluirRequerimento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluirRequerimento.setPreferredSize(new java.awt.Dimension(150, 150));
        btExcluirRequerimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirRequerimentoActionPerformed(evt);
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

        javax.swing.GroupLayout pnGerenciarRequerimentosLayout = new javax.swing.GroupLayout(pnGerenciarRequerimentos);
        pnGerenciarRequerimentos.setLayout(pnGerenciarRequerimentosLayout);
        pnGerenciarRequerimentosLayout.setHorizontalGroup(
            pnGerenciarRequerimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGerenciarRequerimentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnGerenciarRequerimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnGerenciarRequerimentosLayout.createSequentialGroup()
                        .addComponent(btExcluirRequerimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(btEditarRequerimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnGerenciarRequerimentosLayout.setVerticalGroup(
            pnGerenciarRequerimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGerenciarRequerimentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnGerenciarRequerimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btExcluirRequerimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditarRequerimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tpnAbasRequerimentos.addTab("Gerenciar Requerimentos", pnGerenciarRequerimentos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnAbasRequerimentos)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnAbasRequerimentos)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfRequerimentoClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRequerimentoClienteMouseClicked
        // TODO add your handling code here:
        TelaRelatorioCliente telaRelatorioCliente = new TelaRelatorioCliente(null, true, 0);
        telaRelatorioCliente.setVisible(true);
        // Criando o contratante à receber o contratante da tela relatoriorecibo
        Cliente clienteSelecionado = new Cliente();
        clienteSelecionado = telaRelatorioCliente.retornarClienteSelecionado();
        if (clienteSelecionado != null) {
            tfRequerimentoCliente.setText(clienteSelecionado.getCliente_nome());
            tfRequerimentoLatN.setText(clienteSelecionado.getCliente_utmn());
            tfRequerimentoLatE.setText(clienteSelecionado.getCliente_utme());
            clienteTemporario = telaRelatorioCliente.retornarClienteSelecionado();
        }
    }//GEN-LAST:event_tfRequerimentoClienteMouseClicked

    private void tfRequerimentoRepresentante1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRequerimentoRepresentante1MouseClicked
        // TODO add your handling code here:
        TelaRelatorioRepresentante telaRelatorioRepresentante1 = new TelaRelatorioRepresentante(null, true);
        telaRelatorioRepresentante1.setVisible(true);
        // Criando o cliente à receber o cliente da tela relatoriorecibo
        Representante representanteSelecionado = new Representante();
        representanteSelecionado = telaRelatorioRepresentante1.retornarRepresentanteSelecionado();
        if (representanteSelecionado != null) {
            tfRequerimentoRepresentante1.setText(representanteSelecionado.getRepresentante_nome());
            representanteTemporario1 = telaRelatorioRepresentante1.retornarRepresentanteSelecionado();
        }
    }//GEN-LAST:event_tfRequerimentoRepresentante1MouseClicked

    private void tfRequerimentoRepresentante2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRequerimentoRepresentante2MouseClicked
        // TODO add your handling code here:
        TelaRelatorioRepresentante telaRelatorioRepresentante2 = new TelaRelatorioRepresentante(null, true);
        telaRelatorioRepresentante2.setVisible(true);
        // Criando o cliente à receber o cliente da tela relatoriorecibo
        Representante representanteSelecionado = new Representante();
        representanteSelecionado = telaRelatorioRepresentante2.retornarRepresentanteSelecionado();
        if (representanteSelecionado != null) {
            tfRequerimentoRepresentante2.setText(representanteSelecionado.getRepresentante_nome());
            representanteTemporario2 = telaRelatorioRepresentante2.retornarRepresentanteSelecionado();
        }
    }//GEN-LAST:event_tfRequerimentoRepresentante2MouseClicked

    private void tfRequerimentoProfissional1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRequerimentoProfissional1MouseClicked
        // TODO add your handling code here:
        TelaRelatorioProfissional telaRelatorioProfissional1 = new TelaRelatorioProfissional(null, true);
        telaRelatorioProfissional1.setVisible(true);
        // Criando o cliente à receber o cliente da tela relatoriorecibo
        Profissional profissionalSelecionado = new Profissional();
        profissionalSelecionado = telaRelatorioProfissional1.retornarProfissionalSelecionado();
        if (profissionalSelecionado != null) {
            tfRequerimentoProfissional1.setText(profissionalSelecionado.getProfissional_nome());
            profissionalTemporario1 = telaRelatorioProfissional1.retornarProfissionalSelecionado();
        }
    }//GEN-LAST:event_tfRequerimentoProfissional1MouseClicked

    private void tfRequerimentoProfissional2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRequerimentoProfissional2MouseClicked
        // TODO add your handling code here:
        TelaRelatorioProfissional telaRelatorioProfissional2 = new TelaRelatorioProfissional(null, true);
        telaRelatorioProfissional2.setVisible(true);
        // Criando o cliente à receber o cliente da tela relatoriorecibo
        Profissional profissionalSelecionado = new Profissional();
        profissionalSelecionado = telaRelatorioProfissional2.retornarProfissionalSelecionado();
        if (profissionalSelecionado != null) {
            tfRequerimentoProfissional2.setText(profissionalSelecionado.getProfissional_nome());
            profissionalTemporario2 = telaRelatorioProfissional2.retornarProfissionalSelecionado();
        }
    }//GEN-LAST:event_tfRequerimentoProfissional2MouseClicked

    private void tfPalavraChaveRequerimentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPalavraChaveRequerimentoKeyReleased
        // TODO add your handling code here:
        if (tfPalavraChaveRequerimento.getText().isEmpty()) {
            modeloTabelaRequerimentos.inserirListaRequerimentos(conexaoTabelaRequerimentos.selecionarTodosRequerimentos());
        } else {

            modeloTabelaRequerimentos.inserirListaRequerimentos(conexaoTabelaRequerimentos.selecionarTodosRequerimentos());
            listaOriginalTemporariaRequerimento.clear();

            if (ckbTraCliente.isSelected()) {
                for (int i = 0; i < modeloTabelaRequerimentos.retornaListaRequerimentos().size(); i++) {
                    if (modeloTabelaRequerimentos.retornaListaRequerimentos().get(i).getRequerimento_cliente().toLowerCase().contains(tfPalavraChaveRequerimento.getText().toLowerCase())) {
                        listaOriginalTemporariaRequerimento.add(modeloTabelaRequerimentos.retornaListaRequerimentos().get(i));
                    }
                }
            } else if (ckbTraRepresentante.isSelected()) {
                for (int i = 0; i < modeloTabelaRequerimentos.retornaListaRequerimentos().size(); i++) {
                    if (modeloTabelaRequerimentos.retornaListaRequerimentos().get(i).getRequerimento_representante1().toLowerCase().contains(tfPalavraChaveRequerimento.getText().toLowerCase())) {
                        listaOriginalTemporariaRequerimento.add(modeloTabelaRequerimentos.retornaListaRequerimentos().get(i));
                    }
                }
            } else if (ckbTraProfissional.isSelected()) {
                for (int i = 0; i < modeloTabelaRequerimentos.retornaListaRequerimentos().size(); i++) {
                    if (modeloTabelaRequerimentos.retornaListaRequerimentos().get(i).getRequerimento_profissional1().toLowerCase().contains(tfPalavraChaveRequerimento.getText().toLowerCase())) {
                        listaOriginalTemporariaRequerimento.add(modeloTabelaRequerimentos.retornaListaRequerimentos().get(i));
                    }
                }
            }

            modeloTabelaRequerimentos.inserirListaRequerimentos(listaOriginalTemporariaRequerimento);

        }

        tbRequerimento.updateUI();
    }//GEN-LAST:event_tfPalavraChaveRequerimentoKeyReleased

    private void btEditarRequerimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarRequerimentoActionPerformed
        // TODO add your handling code here:

        if (tbRequerimento.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem editados");
        } else {

            Requerimento requerimento = modeloTabelaRequerimentos.retornaListaRequerimentos().get(tbRequerimento.getSelectedRow());
            //Contratante contratant =  new Contratante();
            // ArrayList<Contratante> contratante = conexaoTabelaContratante.selecionarContratanteEspecifico(recibo.getContratante_id());
            // ArrayList<Profissional> profissional = conexaoTabelaProfissionais.selecionarProfissionalEspecifico(recibo.getProfissional_id());

            //System.out.println(contratante.get(0).getContratante_empresa());
            // tfReciboContratante.setText();
            //  requerimentoCadastro.setCliente_id(clienteTemporario.getCliente_id());
            // System.out.println(requerimento.getCliente_id());
            // requerimentoCadastro.setRepresentante_id1(requerimento.getRepresentante_id1());
            // requerimentoCadastro.setRepresentante_id2(representanteTemporario2.getRepresentante_id());
            //  requerimentoCadastro.setProfissional_id1(profissionalTemporario1.getProfissional_id());
            // requerimentoCadastro.setProfissional_id2(profissionalTemporario2.getProfissional_id());
            tfRequerimentoCliente.setText(requerimento.getRequerimento_cliente());
            tfRequerimentoProfissional1.setText(requerimento.getRequerimento_profissional1());
            tfRequerimentoProfissional2.setText(requerimento.getRequerimento_profissional2());
            tfRequerimentoRepresentante1.setText(requerimento.getRequerimento_representante1());
            tfRequerimentoRepresentante2.setText(requerimento.getRequerimento_representante2());
            clienteTemporario.setCliente_id(requerimento.getCliente_id());
            representanteTemporario1.setRepresentante_id(requerimento.getRepresentante_id1());
            representanteTemporario2.setRepresentante_id(requerimento.getRepresentante_id2());
            profissionalTemporario1.setProfissional_id(requerimento.getProfissional_id1());
            profissionalTemporario2.setProfissional_id(requerimento.getProfissional_id2());
            tfRequerimentoProtocolo.setText(requerimento.getRequerimento_num_processo_protocolo());
            tfRequerimentoLicencaiAnterior.setText(requerimento.getRequerimento_num_licenca_anterior());
          //  Date data1 = requerimento.getRequerimento_fase_empreendimento_data().getTime();

           // dataInicioOperacao.setValue(data1);

            tfRequerimentoLatN.setText(requerimento.getRequerimento_latn());
            tfRequerimentoLatE.setText(requerimento.getRequerimento_late());
            tfRequerimentoSema.setText(requerimento.getRequerimento_sema());

            //cbTraEstado.setSelectedItem(tra.getTra_estado());
            if (requerimento.getRequerimento_tipo() == 0) {
                rbAA.setSelected(true);
            }
            if (requerimento.getRequerimento_tipo() == 1) {
                rbLAS.setSelected(true);
            }
            if (requerimento.getRequerimento_tipo() == 2) {
                rbLMP.setSelected(true);
            }
            if (requerimento.getRequerimento_tipo() == 3) {
                rbLMI.setSelected(true);
            }
            if (requerimento.getRequerimento_tipo() == 4) {
                rbLMO.setSelected(true);
            }
            if (requerimento.getRequerimento_tipo() == 5) {
                rbLMU.setSelected(true);
            }
            if (requerimento.getRequerimento_tipo() == 6) {
                rbLAR.setSelected(true);
            }
            if (requerimento.getRequerimento_tipo() == 7) {
                rbLMO.setSelected(true);
            }
            if (requerimento.getRequerimento_fase_empreendimento() == 0) {
                rbPlanejamento.setSelected(true);
            }
            if (requerimento.getRequerimento_fase_empreendimento() == 1) {
                rbInstalacao.setSelected(true);
            }
            if (requerimento.getRequerimento_fase_empreendimento() == 2) {
                rbOperacao.setSelected(true);
            }

            tpnAbasRequerimentos.setSelectedIndex(0); // Mudando para a PRIMEIRA aba

            btFinalizarCadastroRequerimento.setToolTipText("Atualizar");
            tpnAbasRequerimentos.setTitleAt(0, "Atualizar dados");
            tpnAbasRequerimentos.setEnabledAt(1, false);
            btCancelarAtualizacaoRequerimento.setVisible(true);
    }//GEN-LAST:event_btEditarRequerimentoActionPerformed
    }
    private void btFinalizarCadastroRequerimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarCadastroRequerimentoActionPerformed
        if (tfRequerimentoCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tpnAbasRequerimentos, "Por favor selecione o cliente", "Aviso", 2);
            tfRequerimentoCliente.requestFocus();
        } else {
            if (tfRequerimentoRepresentante1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(tpnAbasRequerimentos, "Por favor selecione o representante", "Aviso", 2);
                tfRequerimentoRepresentante1.requestFocus();
            } else {
                if (tfRequerimentoProfissional1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(tpnAbasRequerimentos, "Por favor selecione o profissional", "Aviso", 2);
                    tfRequerimentoProfissional1.requestFocus();
                } else {
                    if (btFinalizarCadastroRequerimento.getToolTipText().equals("Cadastrar")) {
                        conexaoTabelaRequerimentos.inserirNovoRequerimento(preencherDadosCadastroRequerimento());
                        buscarRequerimentosTabela();
                        limparCamposCadastroRequerimento();
                    } else {
                        conexaoTabelaRequerimentos.alterarRequerimento(modeloTabelaRequerimentos.retornaListaRequerimentos().get(tbRequerimento.getSelectedRow()).getRequerimento_id(), preencherDadosCadastroRequerimento());
                        buscarRequerimentosTabela();
                        limparCamposCadastroRequerimento();
                    }
                }
            }
        }

    }//GEN-LAST:event_btFinalizarCadastroRequerimentoActionPerformed

    private void btExcluirRequerimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirRequerimentoActionPerformed
        // TODO add your handling code here:
        if (tbRequerimento.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem deletados");
        } else if (tbRequerimento.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Não existem mais relatorios para serem deletados");
        } else if (JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir esse relatorio?", "Excluir relatorio", 0) == 0) {
            if (tbRequerimento.getSelectedRow() != -1) {
                conexaoTabelaRequerimentos.removerRequerimento(modeloTabelaRequerimentos.retornaListaRequerimentos().get(tbRequerimento.getSelectedRow()).getRequerimento_id());
                modeloTabelaRequerimentos.inserirListaRequerimentos(conexaoTabelaRequerimentos.selecionarTodosRequerimentos());

                tbRequerimento.updateUI();
            }
        }
    }//GEN-LAST:event_btExcluirRequerimentoActionPerformed

    private void rbAAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAAActionPerformed
        // TODO add your handling code here:
        rbLAR.setSelected(false);
        rbLAS.setSelected(false);
        rbLMI.setSelected(false);
        rbLMO.setSelected(false);
        rbLMP.setSelected(false);
        rbLMU.setSelected(false);
        rbRLMO.setSelected(false);
    }//GEN-LAST:event_rbAAActionPerformed

    private void rbLASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLASActionPerformed
        // TODO add your handling code here:
        rbLAR.setSelected(false);
        rbAA.setSelected(false);
        rbLMI.setSelected(false);
        rbLMO.setSelected(false);
        rbLMP.setSelected(false);
        rbLMU.setSelected(false);
        rbRLMO.setSelected(false);
    }//GEN-LAST:event_rbLASActionPerformed

    private void rbLMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLMPActionPerformed
        // TODO add your handling code here:
        rbLAR.setSelected(false);
        rbLAS.setSelected(false);
        rbLMI.setSelected(false);
        rbLMO.setSelected(false);
        rbAA.setSelected(false);
        rbLMU.setSelected(false);
        rbRLMO.setSelected(false);
    }//GEN-LAST:event_rbLMPActionPerformed

    private void rbLMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLMIActionPerformed
        // TODO add your handling code here:
        rbLAR.setSelected(false);
        rbLAS.setSelected(false);
        rbAA.setSelected(false);
        rbLMO.setSelected(false);
        rbLMP.setSelected(false);
        rbLMU.setSelected(false);
        rbRLMO.setSelected(false);
    }//GEN-LAST:event_rbLMIActionPerformed

    private void rbLMOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLMOActionPerformed
        // TODO add your handling code here:
        rbLAR.setSelected(false);
        rbLAS.setSelected(false);
        rbLMI.setSelected(false);
        rbAA.setSelected(false);
        rbLMP.setSelected(false);
        rbLMU.setSelected(false);
        rbRLMO.setSelected(false);
    }//GEN-LAST:event_rbLMOActionPerformed

    private void rbLMUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLMUActionPerformed
        // TODO add your handling code here:
        rbLAR.setSelected(false);
        rbLAS.setSelected(false);
        rbLMI.setSelected(false);
        rbLMO.setSelected(false);
        rbLMP.setSelected(false);
        rbAA.setSelected(false);
        rbRLMO.setSelected(false);
    }//GEN-LAST:event_rbLMUActionPerformed

    private void rbLARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLARActionPerformed
        // TODO add your handling code here:
        rbAA.setSelected(false);
        rbLAS.setSelected(false);
        rbLMI.setSelected(false);
        rbLMO.setSelected(false);
        rbLMP.setSelected(false);
        rbLMU.setSelected(false);
        rbRLMO.setSelected(false);
    }//GEN-LAST:event_rbLARActionPerformed

    private void rbRLMOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRLMOActionPerformed
        // TODO add your handling code here:
        rbLAR.setSelected(false);
        rbLAS.setSelected(false);
        rbLMI.setSelected(false);
        rbLMO.setSelected(false);
        rbLMP.setSelected(false);
        rbLMU.setSelected(false);
        rbAA.setSelected(false);
    }//GEN-LAST:event_rbRLMOActionPerformed

    private void rbPlanejamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPlanejamentoActionPerformed
        // TODO add your handling code here:
        rbOperacao.setSelected(false);
        rbInstalacao.setSelected(false);
    }//GEN-LAST:event_rbPlanejamentoActionPerformed

    private void rbInstalacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInstalacaoActionPerformed
        // TODO add your handling code here:
        rbOperacao.setSelected(false);
        rbPlanejamento.setSelected(false);
    }//GEN-LAST:event_rbInstalacaoActionPerformed

    private void rbOperacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOperacaoActionPerformed
        // TODO add your handling code here:
        rbPlanejamento.setSelected(false);
        rbInstalacao.setSelected(false);
    }//GEN-LAST:event_rbOperacaoActionPerformed

    private void btCancelarAtualizacaoRequerimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarAtualizacaoRequerimentoActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(rootPane, "Deseja mesmo cancelar as alterações", "Aviso", 0) == 0) {
            btFinalizarCadastroRequerimento.setToolTipText("Cadastrar");
            btCancelarAtualizacaoRequerimento.setVisible(false);
            tpnAbasRequerimentos.setEnabledAt(1, true);
            tpnAbasRequerimentos.setSelectedIndex(1);
            tpnAbasRequerimentos.setTitleAt(0, "Gerar Requerimento");
            tfRequerimentoCliente.setText("");
            tfRequerimentoProfissional1.setText("");
            tfRequerimentoProfissional2.setText("");
            tfRequerimentoRepresentante1.setText("");
            tfRequerimentoRepresentante2.setText("");
            //tfRequerimentoDataOperacao.setText("");
            tfRequerimentoProtocolo.setText("");
            tfRequerimentoLicencaiAnterior.setText("");
            rbAA.setSelected(false);
            rbInstalacao.setSelected(false);
            rbLAR.setSelected(false);
            rbLAS.setSelected(false);
            rbLMI.setSelected(false);
            rbLMO.setSelected(false);
            rbLMP.setSelected(false);
            rbLMU.setSelected(false);
            rbOperacao.setSelected(false);
            rbPlanejamento.setSelected(false);
            rbRLMO.setSelected(false);
            //cbTraEstado.setSelectedIndex(0);
            tfRequerimentoLatN.setText("");
            tfRequerimentoLatE.setText("");
            tfRequerimentoSema.setText("");
        }
    }//GEN-LAST:event_btCancelarAtualizacaoRequerimentoActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:ddd
        if (tbRequerimento.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem imprimidos");
        } else {

            Requerimento requerimento = modeloTabelaRequerimentos.retornaListaRequerimentos().get(tbRequerimento.getSelectedRow());
            requerimento.getRequerimento_id();

            Date data1 = requerimento.getRequerimento_data().getTime();

            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

            DecimalFormat form = new DecimalFormat("00");
            String dataFaseEmpreendimento;
            dataFaseEmpreendimento = form.format(requerimento.getRequerimento_fase_empreendimento_data().get(Calendar.DAY_OF_MONTH)) + "/" + form.format(requerimento.getRequerimento_fase_empreendimento_data().get(Calendar.MONTH) + 1) + "/" + requerimento.getRequerimento_fase_empreendimento_data().get(Calendar.YEAR);

            
            
            try {
                //usando a clsse HashMap para criar um filtro
                //  JOptionPane.showMessageDialog(rootPane, recibo.getRecibo_id());
                HashMap filtro = new HashMap();
                filtro.put("id", requerimento.getRequerimento_id());
                filtro.put("data", df.format(data1.getTime()));
                filtro.put("dataFaseEmpreendimento", dataFaseEmpreendimento);
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("build/classes/reports/Requerimento.jasper", filtro, conexao);
                //a linha abaixo exibe o relatório através da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }


    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelarAtualizacaoRequerimento;
    private javax.swing.JButton btEditarRequerimento;
    private javax.swing.JButton btExcluirRequerimento;
    private javax.swing.JButton btFinalizarCadastroRequerimento;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JCheckBox ckbTraCliente;
    private javax.swing.JCheckBox ckbTraProfissional;
    private javax.swing.JCheckBox ckbTraRepresentante;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnCadastrarNovoRequerimento;
    private javax.swing.JPanel pnGerenciarRequerimentos;
    private javax.swing.JRadioButton rbAA;
    private javax.swing.JRadioButton rbInstalacao;
    private javax.swing.JRadioButton rbLAR;
    private javax.swing.JRadioButton rbLAS;
    private javax.swing.JRadioButton rbLMI;
    private javax.swing.JRadioButton rbLMO;
    private javax.swing.JRadioButton rbLMP;
    private javax.swing.JRadioButton rbLMU;
    private javax.swing.JRadioButton rbOperacao;
    private javax.swing.JRadioButton rbPlanejamento;
    private javax.swing.JRadioButton rbRLMO;
    private javax.swing.JTable tbRequerimento;
    private javax.swing.JTextField tfPalavraChaveRequerimento;
    private javax.swing.JTextField tfRequerimentoCliente;
    private javax.swing.JTextField tfRequerimentoLatE;
    private javax.swing.JTextField tfRequerimentoLatN;
    private javax.swing.JTextField tfRequerimentoLicencaiAnterior;
    private javax.swing.JTextField tfRequerimentoProfissional1;
    private javax.swing.JTextField tfRequerimentoProfissional2;
    private javax.swing.JTextField tfRequerimentoProtocolo;
    private javax.swing.JTextField tfRequerimentoRepresentante1;
    private javax.swing.JTextField tfRequerimentoRepresentante2;
    private javax.swing.JTextField tfRequerimentoSema;
    private javax.swing.JTabbedPane tpnAbasRequerimentos;
    // End of variables declaration//GEN-END:variables
}
