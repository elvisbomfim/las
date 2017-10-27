/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bancodedados.ClienteBD;
import bancodedados.ConexaoBanco;
import bancodedados.ConexaoPDF;
import bancodedados.ContratanteBD;
import bancodedados.ProfissionalBD;
import bancodedados.ReciboBD;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.tabelas.ModeloTabelaContratantes;
import modelo.tabelas.ModeloTabelaProfissionais;
import modelo.tabelas.ModeloTabelaRecibos;
import modelos.Cliente;
import modelos.Contratante;
import modelos.Profissional;
import modelos.Recibo;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;

/**
 *
 * @author Jean
 */
public class jifFormRecibo extends javax.swing.JInternalFrame {

    DateField dataAtual = CalendarFactory.createDateField();
    /**
     * Creates new form jifFormRecibo
     */
    /**
     * Definição de ArrayList's
     */
    ArrayList<Recibo> listaOriginalTemporariaRecibo = new ArrayList();

    /**
     * Objetos temporarios
     */
    Recibo reciboCadastro = new Recibo();
    Contratante contratanteTemporario = new Contratante();
    Profissional profissionalTemporario = new Profissional();

    /**
     * Classes de definição de modelos de tabela
     */
    ModeloTabelaRecibos modeloTabelaRecibos = new ModeloTabelaRecibos();

    ModeloTabelaContratantes modeloTabelaContratantes;
    ModeloTabelaProfissionais modeloTabelaProfissionais;

    /**
     * Objetos temporarios
     */
    ReciboBD conexaoTabelaRecibos = new ReciboBD();
    ClienteBD conexaoTabelaClientes = new ClienteBD();
    ProfissionalBD conexaoTabelaProfissionais = new ProfissionalBD();
    ContratanteBD conexaoTabelaContratante = new ContratanteBD();

    //PDF Conexao
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public jifFormRecibo() {
        initComponents();
        conexao = ConexaoPDF.conector();
        Calendar cal = Calendar.getInstance();
        dataAtual.setBaseDate(cal.getTime());
        pnDataAtual.add(dataAtual);
        //Definindo o botão DateField (Data Inicio do Semestre) para seleção de uma data e atribuindo uma ação de mudança à ele.
        dataAtual.setSize((pnDataAtual.getWidth()), (pnDataAtual.getHeight()));
        /**
         * Definição de modelos de tabela e buscas na tabela
         */
        tbRecibo.setModel(modeloTabelaRecibos);

        //----------------buscas na tabela--------------------------------------
        buscarRecibosTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        tpnAbasRecibos = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfReciboCidade = new javax.swing.JTextField();
        cbReciboEstado = new javax.swing.JComboBox<>();
        btFinalizarCadastroRecibo = new javax.swing.JButton();
        btCancelarAtualizacaoRecibo = new javax.swing.JButton();
        tfReciboContratante = new javax.swing.JTextField();
        tfReciboProfissional = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfReciboValor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pnDataAtual = new com.toedter.calendar.JDayChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRecibo = new javax.swing.JTable();
        btExcluirRecibo = new javax.swing.JButton();
        btEditarRecibo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tfPalavraChaveRecibo = new javax.swing.JTextField();
        ckbReciboContratante = new javax.swing.JCheckBox();
        ckbTraProfissional = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setTitle("Recibo");

        jLabel1.setText("Contratante:");

        jLabel2.setText("Profissional");

        jLabel3.setText("Cidade:");

        jLabel4.setText("Estado:");

        cbReciboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Selecionar---", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        btFinalizarCadastroRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/complete-file.png"))); // NOI18N
        btFinalizarCadastroRecibo.setToolTipText("Cadastrar");
        btFinalizarCadastroRecibo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFinalizarCadastroRecibo.setPreferredSize(new java.awt.Dimension(150, 150));
        btFinalizarCadastroRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarCadastroReciboActionPerformed(evt);
            }
        });

        btCancelarAtualizacaoRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_edit-not-validated_85308.png"))); // NOI18N
        btCancelarAtualizacaoRecibo.setToolTipText("Cancelar");
        btCancelarAtualizacaoRecibo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelarAtualizacaoRecibo.setPreferredSize(new java.awt.Dimension(150, 150));
        btCancelarAtualizacaoRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarAtualizacaoReciboActionPerformed(evt);
            }
        });

        tfReciboContratante.setEditable(false);
        tfReciboContratante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfReciboContratanteMouseClicked(evt);
            }
        });

        tfReciboProfissional.setEditable(false);
        tfReciboProfissional.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfReciboProfissionalMouseClicked(evt);
            }
        });

        jLabel5.setText("Valor:");

        jLabel7.setText("Data:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btCancelarAtualizacaoRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                        .addComponent(btFinalizarCadastroRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfReciboCidade)
                            .addComponent(tfReciboContratante)
                            .addComponent(tfReciboProfissional)
                            .addComponent(tfReciboValor)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pnDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbReciboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfReciboContratante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfReciboProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfReciboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfReciboValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbReciboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(pnDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btFinalizarCadastroRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancelarAtualizacaoRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        tpnAbasRecibos.addTab("Gerar Recibos", jPanel1);

        tbRecibo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbRecibo);

        btExcluirRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete-file.png"))); // NOI18N
        btExcluirRecibo.setToolTipText("Excluir");
        btExcluirRecibo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluirRecibo.setPreferredSize(new java.awt.Dimension(150, 150));
        btExcluirRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirReciboActionPerformed(evt);
            }
        });

        btEditarRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit-file.png"))); // NOI18N
        btEditarRecibo.setToolTipText("Editar");
        btEditarRecibo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditarRecibo.setPreferredSize(new java.awt.Dimension(150, 150));
        btEditarRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarReciboActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro de Busca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel6.setText("Palavra-Chave:");

        tfPalavraChaveRecibo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPalavraChaveReciboKeyReleased(evt);
            }
        });

        ckbReciboContratante.setText("Contratante");

        ckbTraProfissional.setText("Profissional");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfPalavraChaveRecibo, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(ckbReciboContratante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbTraProfissional)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfPalavraChaveRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbReciboContratante)
                    .addComponent(ckbTraProfissional))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/printer-128.png"))); // NOI18N
        jButton1.setToolTipText("Imprimir");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setPreferredSize(new java.awt.Dimension(150, 150));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btExcluirRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btEditarRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btExcluirRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditarRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        tpnAbasRecibos.addTab("Gerenciar Recibos", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnAbasRecibos, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnAbasRecibos, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        setBounds(0, 0, 600, 600);
    }// </editor-fold>//GEN-END:initComponents

    public void buscarRecibosTabela() {

        modeloTabelaRecibos.inserirListaRecibos(conexaoTabelaRecibos.selecionarTodosRecibos());
        listaOriginalTemporariaRecibo = modeloTabelaRecibos.retornaListaRecibos();
        tbRecibo.updateUI();
    }

    public Recibo preencherDadosCadastroRecibo() {

        reciboCadastro.setContratante_id(contratanteTemporario.getContratante_id());
        System.out.println(contratanteTemporario.getContratante_id());
        System.out.println(profissionalTemporario.getProfissional_id());
        reciboCadastro.setProfissional_id(profissionalTemporario.getProfissional_id());
        reciboCadastro.setRecibo_profissional(tfReciboProfissional.getText());
        reciboCadastro.setRecibo_contratante(tfReciboContratante.getText());
        reciboCadastro.setRecibo_estado(cbReciboEstado.getSelectedItem().toString());
        reciboCadastro.setRecibo_cidade(tfReciboCidade.getText());
        reciboCadastro.setRecibo_valor(tfReciboValor.getText());

        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) dataAtual.getValue());
        reciboCadastro.setRecibo_data(cal);
        //DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        // Date d = new Date();
        //Calendar c = Calendar.getInstance();
        //reciboCadastro.setRecibo_data(df.format(c.getTime()));
        return reciboCadastro;

    }

    public void limparCamposCadastroCliente() {
        tfReciboContratante.setText("");
        tfReciboProfissional.setText("");
        tfReciboCidade.setText("");
        tfReciboValor.setText("");
        cbReciboEstado.setSelectedIndex(0);

        tpnAbasRecibos.setTitleAt(0, "Cadastrar Novos Recibos");
        btFinalizarCadastroRecibo.setToolTipText("Cadastrar");
        tpnAbasRecibos.setEnabledAt(1, true);
        tpnAbasRecibos.setSelectedIndex(1);
        btCancelarAtualizacaoRecibo.setVisible(false);

    }

    private void btFinalizarCadastroReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarCadastroReciboActionPerformed
        // TODO add your handling code here:
        if (tfReciboContratante.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tpnAbasRecibos, "Por favor selecione o contratente", "Aviso", 2);
            tfReciboContratante.requestFocus();
        } else {
            if (tfReciboProfissional.getText().isEmpty()) {
                JOptionPane.showMessageDialog(tpnAbasRecibos, "Por favor selecione o profissional", "Aviso", 2);
                tfReciboContratante.requestFocus();
            } else {
                if (btFinalizarCadastroRecibo.getToolTipText().equals("Cadastrar")) {
                    conexaoTabelaRecibos.inserirNovoRecibo(preencherDadosCadastroRecibo());
                    buscarRecibosTabela();
                    limparCamposCadastroCliente();

                    //Chamar Ireport
                } else {
                    conexaoTabelaRecibos.alterarRecibo(modeloTabelaRecibos.retornaListaRecibos().get(tbRecibo.getSelectedRow()).getRecibo_id(), preencherDadosCadastroRecibo());
                    buscarRecibosTabela();
                    limparCamposCadastroCliente();
                }
            }
        }


    }//GEN-LAST:event_btFinalizarCadastroReciboActionPerformed

    private void tfReciboContratanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfReciboContratanteMouseClicked
        // TODO add your handling code here:
        TelaRelatorioContrante telaRelatorioContratante = new TelaRelatorioContrante(null, true);
        telaRelatorioContratante.setVisible(true);
        // Criando o contratante à receber o contratante da tela relatoriorecibo
        Contratante contratanteSelecionado = new Contratante();
        contratanteSelecionado = telaRelatorioContratante.retornarContratanteSelecionado();
        if (contratanteSelecionado != null) {
            tfReciboContratante.setText(contratanteSelecionado.getContratante_empresa());
            contratanteTemporario = telaRelatorioContratante.retornarContratanteSelecionado();
        }
    }//GEN-LAST:event_tfReciboContratanteMouseClicked

    private void tfReciboProfissionalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfReciboProfissionalMouseClicked
        // TODO add your handling code here:
        TelaRelatorioProfissional telaRelatorioReciboProfissional = new TelaRelatorioProfissional(null, true);
        telaRelatorioReciboProfissional.setVisible(true);
        // Criando o cliente à receber o cliente da tela relatoriorecibo
        Profissional profissionalSelecionado = new Profissional();
        profissionalSelecionado = telaRelatorioReciboProfissional.retornarProfissionalSelecionado();
        if (profissionalSelecionado != null) {
            tfReciboProfissional.setText(profissionalSelecionado.getProfissional_nome());
            profissionalTemporario = telaRelatorioReciboProfissional.retornarProfissionalSelecionado();
        }
    }//GEN-LAST:event_tfReciboProfissionalMouseClicked

    private void btEditarReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarReciboActionPerformed
        // TODO add your handling code here:
        if (tbRecibo.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem editados");
        } else {

            Recibo recibo = modeloTabelaRecibos.retornaListaRecibos().get(tbRecibo.getSelectedRow());
            recibo.getRecibo_id();
            //Contratante contratant =  new Contratante();
            //ArrayList<Contratante> contratante = conexaoTabelaContratante.selecionarContratanteEspecifico(recibo.getContratante_id());
            //ArrayList<Profissional> profissional = conexaoTabelaProfissionais.selecionarProfissionalEspecifico(recibo.getProfissional_id());

            System.out.println(recibo.getRecibo_contratante());
            // tfReciboContratante.setText();
            tfReciboContratante.setText(recibo.getRecibo_contratante());
            tfReciboProfissional.setText(recibo.getRecibo_profissional());
            tfReciboCidade.setText(recibo.getRecibo_cidade());
            String valor;
            valor = "" + recibo.getRecibo_valor() + "";
            tfReciboValor.setText(valor);
            cbReciboEstado.setSelectedItem(recibo.getRecibo_estado());
            contratanteTemporario.setContratante_id(recibo.getContratante_id());
            profissionalTemporario.setProfissional_id(recibo.getProfissional_id());

            Date data1 = recibo.getRecibo_data().getTime();

            dataAtual.setValue(data1);

            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
            // Date d = new Date();
            //traCadastro.setTra_data(df.format(c.getTime()));
            System.out.println("TESTE" + df.format(data1.getTime()));

            tpnAbasRecibos.setSelectedIndex(0); // Mudando para a PRIMEIRA aba

            btFinalizarCadastroRecibo.setToolTipText("Atualizar");
            tpnAbasRecibos.setTitleAt(0, "Atualizar dados");
            tpnAbasRecibos.setEnabledAt(1, false);
            btCancelarAtualizacaoRecibo.setVisible(true);
    }//GEN-LAST:event_btEditarReciboActionPerformed
    }

    private void btCancelarAtualizacaoReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarAtualizacaoReciboActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(rootPane, "Deseja mesmo cancelar as alterações", "Aviso", 0) == 0) {
            btFinalizarCadastroRecibo.setToolTipText("Cadastrar");
            btCancelarAtualizacaoRecibo.setVisible(false);
            tpnAbasRecibos.setEnabledAt(1, true);
            tpnAbasRecibos.setSelectedIndex(1);
            tpnAbasRecibos.setTitleAt(0, "Gerar Recibos");
            tfReciboContratante.setText("");
            tfReciboProfissional.setText("");
            tfReciboCidade.setText("");
            tfReciboValor.setText("");
            cbReciboEstado.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btCancelarAtualizacaoReciboActionPerformed

    private void btExcluirReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirReciboActionPerformed
        // TODO add your handling code here:
        if (tbRecibo.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem deletados");
        } else if (tbRecibo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Não existem mais relatorios para serem deletados");
        } else if (JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir esse relatorio?", "Excluir relatorio", 0) == 0) {
            if (tbRecibo.getSelectedRow() != -1) {
                conexaoTabelaRecibos.removerRecibo(modeloTabelaRecibos.retornaListaRecibos().get(tbRecibo.getSelectedRow()).getRecibo_id());
                modeloTabelaRecibos.inserirListaRecibos(conexaoTabelaRecibos.selecionarTodosRecibos());

                tbRecibo.updateUI();
            }
        }
    }//GEN-LAST:event_btExcluirReciboActionPerformed

    private void tfPalavraChaveReciboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPalavraChaveReciboKeyReleased
        // TODO add your handling code here:
        if (tfPalavraChaveRecibo.getText().isEmpty()) {
            modeloTabelaRecibos.inserirListaRecibos(conexaoTabelaRecibos.selecionarTodosRecibos());
        } else {

            modeloTabelaRecibos.inserirListaRecibos(conexaoTabelaRecibos.selecionarTodosRecibos());
            listaOriginalTemporariaRecibo.clear();

            if (ckbReciboContratante.isSelected()) {
                for (int i = 0; i < modeloTabelaRecibos.retornaListaRecibos().size(); i++) {
                    if (modeloTabelaRecibos.retornaListaRecibos().get(i).getRecibo_contratante().toLowerCase().contains(tfPalavraChaveRecibo.getText().toLowerCase())) {
                        listaOriginalTemporariaRecibo.add(modeloTabelaRecibos.retornaListaRecibos().get(i));
                    }
                }
            } else if (ckbTraProfissional.isSelected()) {
                for (int i = 0; i < modeloTabelaRecibos.retornaListaRecibos().size(); i++) {
                    if (modeloTabelaRecibos.retornaListaRecibos().get(i).getRecibo_profissional().toLowerCase().contains(tfPalavraChaveRecibo.getText().toLowerCase())) {
                        listaOriginalTemporariaRecibo.add(modeloTabelaRecibos.retornaListaRecibos().get(i));
                    }
                }
            }

            modeloTabelaRecibos.inserirListaRecibos(listaOriginalTemporariaRecibo);

        }

        tbRecibo.updateUI();
    }//GEN-LAST:event_tfPalavraChaveReciboKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:ddd
        if (tbRecibo.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem relatorios selecionados para serem imprimidos");
        } else {

            Recibo recibo = modeloTabelaRecibos.retornaListaRecibos().get(tbRecibo.getSelectedRow());
            recibo.getRecibo_id();

            Date data1 = recibo.getRecibo_data().getTime();

            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

           

            try {
                //usando a clsse HashMap para criar um filtro
                //  JOptionPane.showMessageDialog(rootPane, recibo.getRecibo_id());
                HashMap filtro = new HashMap();
                filtro.put("id", recibo.getRecibo_id());
                filtro.put("data", df.format(data1.getTime()));
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("build/classes/reports/Recibo.jasper", filtro, conexao);
                //a linha abaixo exibe o relatório através da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelarAtualizacaoRecibo;
    private javax.swing.JButton btEditarRecibo;
    private javax.swing.JButton btExcluirRecibo;
    private javax.swing.JButton btFinalizarCadastroRecibo;
    private javax.swing.JComboBox<String> cbReciboEstado;
    private javax.swing.JCheckBox ckbReciboContratante;
    private javax.swing.JCheckBox ckbTraProfissional;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JDayChooser pnDataAtual;
    private javax.swing.JTable tbRecibo;
    private javax.swing.JTextField tfPalavraChaveRecibo;
    private javax.swing.JTextField tfReciboCidade;
    private javax.swing.JTextField tfReciboContratante;
    private javax.swing.JTextField tfReciboProfissional;
    private javax.swing.JTextField tfReciboValor;
    private javax.swing.JTabbedPane tpnAbasRecibos;
    // End of variables declaration//GEN-END:variables
}