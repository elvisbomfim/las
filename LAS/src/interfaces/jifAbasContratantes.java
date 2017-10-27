/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bancodedados.ContratanteBD;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.tabelas.ModeloTabelaContratantes;
import modelos.Contratante;

/**
 *
 * @author Jean
 */
public class jifAbasContratantes extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifAbasClientes
     */
    /**
     * Objetos temporarios
     */
    Contratante contratanteCadastro = new Contratante();

    /**
     * Definição de ArrayList's
     */
    ArrayList<Contratante> listaOriginalTemporariaContratante = new ArrayList();

    /**
     * Classes de definição de modelos de tabela
     */
    ModeloTabelaContratantes modeloTabelaContratante = new ModeloTabelaContratantes();

    /**
     * Classes de conexão com o banco
     */
    ContratanteBD conexaoTabelaContratantes = new ContratanteBD();

    public jifAbasContratantes() {
        initComponents();

        tbContratantesCadastrados.setModel(modeloTabelaContratante);

        //----------------buscas na tabela--------------------------------------
        buscarContratantesTabela();

        //----------------Desabilitar bostoes cancelar atualização--------------
        btCancelarAtualizacaoContratante.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tpnAbasContratantes = new javax.swing.JTabbedPane();
        pnCadastrarNovoContratante = new javax.swing.JPanel();
        btCancelarAtualizacaoContratante = new javax.swing.JButton();
        btFinalizarCadastroContratante = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfContratanteNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfContratanteCpf = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfContratanteCnpj = new javax.swing.JFormattedTextField();
        tfContratanteTelefone = new javax.swing.JFormattedTextField();
        tfContratanteCelular = new javax.swing.JFormattedTextField();
        pnGerenciarContratantes = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfPalavraChaveContratante = new javax.swing.JTextField();
        ckbContratanteCpf = new javax.swing.JCheckBox();
        ckbContratanteCnpj = new javax.swing.JCheckBox();
        ckbContratanteNome = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbContratantesCadastrados = new javax.swing.JTable();
        btExcluirCliente = new javax.swing.JButton();
        btEditarContratante = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Contratante");
        setPreferredSize(new java.awt.Dimension(600, 600));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tpnAbasContratantes.setPreferredSize(new java.awt.Dimension(600, 580));

        pnCadastrarNovoContratante.setPreferredSize(new java.awt.Dimension(600, 550));

        btCancelarAtualizacaoContratante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_edit-not-validated_85308.png"))); // NOI18N
        btCancelarAtualizacaoContratante.setToolTipText("Cancelar Atualização");
        btCancelarAtualizacaoContratante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCancelarAtualizacaoContratante.setPreferredSize(new java.awt.Dimension(150, 150));
        btCancelarAtualizacaoContratante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarAtualizacaoContratanteActionPerformed(evt);
            }
        });

        btFinalizarCadastroContratante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/complete-file.png"))); // NOI18N
        btFinalizarCadastroContratante.setToolTipText("Cadastrar");
        btFinalizarCadastroContratante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btFinalizarCadastroContratante.setPreferredSize(new java.awt.Dimension(150, 150));
        btFinalizarCadastroContratante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarCadastroContratanteActionPerformed(evt);
            }
        });

        jLabel3.setText("Contratante:");

        jLabel2.setText("CPF:");

        try {
            tfContratanteCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfContratanteCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfContratanteCpfActionPerformed(evt);
            }
        });

        jLabel4.setText("Telefone:");

        jLabel5.setText("Celular:");

        jLabel6.setText("CNPJ:");

        try {
            tfContratanteCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfContratanteCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfContratanteCnpjActionPerformed(evt);
            }
        });

        try {
            tfContratanteTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfContratanteCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pnCadastrarNovoContratanteLayout = new javax.swing.GroupLayout(pnCadastrarNovoContratante);
        pnCadastrarNovoContratante.setLayout(pnCadastrarNovoContratanteLayout);
        pnCadastrarNovoContratanteLayout.setHorizontalGroup(
            pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarNovoContratanteLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btCancelarAtualizacaoContratante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(btFinalizarCadastroContratante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(pnCadastrarNovoContratanteLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfContratanteCpf, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfContratanteNome, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(tfContratanteCnpj)
                    .addComponent(tfContratanteTelefone)
                    .addComponent(tfContratanteCelular))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnCadastrarNovoContratanteLayout.setVerticalGroup(
            pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastrarNovoContratanteLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfContratanteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfContratanteCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfContratanteCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(tfContratanteTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfContratanteCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(pnCadastrarNovoContratanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancelarAtualizacaoContratante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFinalizarCadastroContratante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        tpnAbasContratantes.addTab("Cadastrar Novos Contratantes", pnCadastrarNovoContratante);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro de Busca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Palavra-chave:");

        tfPalavraChaveContratante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPalavraChaveContratanteKeyReleased(evt);
            }
        });

        ckbContratanteCpf.setText("CPF");

        ckbContratanteCnpj.setText("CNPJ");

        ckbContratanteNome.setText("Nome");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfPalavraChaveContratante)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(ckbContratanteCpf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbContratanteCnpj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckbContratanteNome)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfPalavraChaveContratante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbContratanteCpf)
                    .addComponent(ckbContratanteCnpj)
                    .addComponent(ckbContratanteNome))
                .addContainerGap())
        );

        tbContratantesCadastrados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbContratantesCadastrados);

        btExcluirCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete-file.png"))); // NOI18N
        btExcluirCliente.setToolTipText("Excluir");
        btExcluirCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExcluirCliente.setPreferredSize(new java.awt.Dimension(150, 150));
        btExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirClienteActionPerformed(evt);
            }
        });

        btEditarContratante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit-file.png"))); // NOI18N
        btEditarContratante.setToolTipText("Editar");
        btEditarContratante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEditarContratante.setPreferredSize(new java.awt.Dimension(150, 150));
        btEditarContratante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarContratanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnGerenciarContratantesLayout = new javax.swing.GroupLayout(pnGerenciarContratantes);
        pnGerenciarContratantes.setLayout(pnGerenciarContratantesLayout);
        pnGerenciarContratantesLayout.setHorizontalGroup(
            pnGerenciarContratantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGerenciarContratantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnGerenciarContratantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnGerenciarContratantesLayout.createSequentialGroup()
                        .addComponent(btExcluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btEditarContratante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnGerenciarContratantesLayout.setVerticalGroup(
            pnGerenciarContratantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGerenciarContratantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(pnGerenciarContratantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEditarContratante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExcluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

        tpnAbasContratantes.addTab("Gerenciar Contratantes", pnGerenciarContratantes);

        jScrollPane1.setViewportView(tpnAbasContratantes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void buscarContratantesTabela() {
        modeloTabelaContratante.inserirListaContratantes(conexaoTabelaContratantes.selecionarTodosContratantes());
        listaOriginalTemporariaContratante = modeloTabelaContratante.retornarListaContratantes();
        tbContratantesCadastrados.updateUI();
    }

    private void btFinalizarCadastroContratanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarCadastroContratanteActionPerformed
        // TODO add your handling code here:

        if (tfContratanteNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(pnCadastrarNovoContratante, "Por favor insira o nome do contratante", "Aviso", 2);
            tfContratanteNome.requestFocus();
        } else {
            if ((tfContratanteCpf.getValue()==null) && (tfContratanteCnpj.getValue()==null)) {
                JOptionPane.showMessageDialog(pnCadastrarNovoContratante, "Por favor insira o cnpj ou cpf", "Aviso", 2);
                tfContratanteCpf.requestFocus();
            } else {
                if (btFinalizarCadastroContratante.getToolTipText().equals("Cadastrar")) {

                    conexaoTabelaContratantes.inserirNovoContratante(preencherDadosCadastroContratante());
                    //conexaoTabelaContratantes.inserirNovoContratante(contratanteCadastro);
                    buscarContratantesTabela();
                    limparCamposCadastroContratante();

                } else {
                    conexaoTabelaContratantes.alterarContratante(modeloTabelaContratante.retornarListaContratantes().get(tbContratantesCadastrados.getSelectedRow()).getContratante_id(), preencherDadosCadastroContratante());

                    buscarContratantesTabela();

                    limparCamposCadastroContratante();
                }
            }
        }


    }//GEN-LAST:event_btFinalizarCadastroContratanteActionPerformed

    public Contratante preencherDadosCadastroContratante() {

        contratanteCadastro.setContratante_empresa(tfContratanteNome.getText());
        contratanteCadastro.setContratante_cpf(tfContratanteCpf.getText());
        contratanteCadastro.setContratante_cnpj(tfContratanteCnpj.getText());
        contratanteCadastro.setContratante_telefone(tfContratanteTelefone.getText());
        contratanteCadastro.setContratante_celular(tfContratanteCelular.getText());

        return contratanteCadastro;

    }

    public void limparCamposCadastroContratante() {
        tfContratanteNome.setText("");
        tfContratanteCpf.setText("");
        tfContratanteTelefone.setText("");
        tfContratanteCelular.setText("");
        tfContratanteCnpj.setText("");
        tpnAbasContratantes.setTitleAt(0, "Cadastrar Novos Contratantes");
        btFinalizarCadastroContratante.setToolTipText("Cadastrar");
        tpnAbasContratantes.setEnabledAt(1, true);
        tpnAbasContratantes.setSelectedIndex(1);
        btCancelarAtualizacaoContratante.setVisible(false);

    }

    private void tfPalavraChaveContratanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPalavraChaveContratanteKeyReleased
        // TODO add your handling code here:
        if (tfPalavraChaveContratante.getText().isEmpty()) {
            modeloTabelaContratante.inserirListaContratantes(conexaoTabelaContratantes.selecionarTodosContratantes());
        } else {

            modeloTabelaContratante.inserirListaContratantes(conexaoTabelaContratantes.selecionarTodosContratantes());
            listaOriginalTemporariaContratante.clear();

            if (ckbContratanteCpf.isSelected()) {
                for (int i = 0; i < modeloTabelaContratante.retornarListaContratantes().size(); i++) {
                    if (modeloTabelaContratante.retornarListaContratantes().get(i).getContratante_cpf().toLowerCase().contains(tfPalavraChaveContratante.getText().toLowerCase())) {
                        listaOriginalTemporariaContratante.add(modeloTabelaContratante.retornarListaContratantes().get(i));
                    }
                }
            } else if (ckbContratanteNome.isSelected()) {
                for (int i = 0; i < modeloTabelaContratante.retornarListaContratantes().size(); i++) {
                    if (modeloTabelaContratante.retornarListaContratantes().get(i).getContratante_empresa().toLowerCase().contains(tfPalavraChaveContratante.getText().toLowerCase())) {
                        listaOriginalTemporariaContratante.add(modeloTabelaContratante.retornarListaContratantes().get(i));
                    }
                }
            } else if (ckbContratanteCnpj.isSelected()) {
                for (int i = 0; i < modeloTabelaContratante.retornarListaContratantes().size(); i++) {
                    if (modeloTabelaContratante.retornarListaContratantes().get(i).getContratante_cnpj().toLowerCase().contains(tfPalavraChaveContratante.getText().toLowerCase())) {
                        listaOriginalTemporariaContratante.add(modeloTabelaContratante.retornarListaContratantes().get(i));
                    }
                }
            }

            modeloTabelaContratante.inserirListaContratantes(listaOriginalTemporariaContratante);

        }

        tbContratantesCadastrados.updateUI();
    }//GEN-LAST:event_tfPalavraChaveContratanteKeyReleased

    private void btExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirClienteActionPerformed
        // TODO add your handling code here:
        if (tbContratantesCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem contratantes selecionados para serem deletados");
        } else if (tbContratantesCadastrados.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Não existem mais contratantes para serem deletados");
        } else if (JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir esse aluno?", "Excluir Aluno", 0) == 0) {
            if (tbContratantesCadastrados.getSelectedRow() != -1) {
                conexaoTabelaContratantes.removerContratante(modeloTabelaContratante.retornarListaContratantes().get(tbContratantesCadastrados.getSelectedRow()).getContratante_id(), tbContratantesCadastrados.getValueAt(tbContratantesCadastrados.getSelectedRow(), 0).toString());
                modeloTabelaContratante.inserirListaContratantes(conexaoTabelaContratantes.selecionarTodosContratantes());

                tbContratantesCadastrados.updateUI();
            }
        }
    }//GEN-LAST:event_btExcluirClienteActionPerformed

    private void btEditarContratanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarContratanteActionPerformed
        // TODO add your handling code here:
        if (tbContratantesCadastrados.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Não existem contratantes selecionados para serem editados");
        } else {

            Contratante contratante = modeloTabelaContratante.retornarListaContratantes().get(tbContratantesCadastrados.getSelectedRow());

            tfContratanteNome.setText(contratante.getContratante_empresa());
            tfContratanteCpf.setText(contratante.getContratante_cpf());
            tfContratanteCnpj.setText(contratante.getContratante_cnpj());
            tfContratanteTelefone.setText(contratante.getContratante_telefone());
            tfContratanteCelular.setText(contratante.getContratante_celular());

            tpnAbasContratantes.setSelectedIndex(0); // Mudando para a PRIMEIRA aba

            btFinalizarCadastroContratante.setToolTipText("Atualizar");
            tpnAbasContratantes.setTitleAt(0, "Atualizar dados");
            tpnAbasContratantes.setEnabledAt(1, false);
            btCancelarAtualizacaoContratante.setVisible(true);
        }
    }//GEN-LAST:event_btEditarContratanteActionPerformed

    private void btCancelarAtualizacaoContratanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarAtualizacaoContratanteActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(rootPane, "Deseja mesmo cancelar as alterações", "Aviso", 0) == 0) {
            btFinalizarCadastroContratante.setToolTipText("Cadastrar");
            btCancelarAtualizacaoContratante.setVisible(false);
            tpnAbasContratantes.setEnabledAt(1, true);
            tpnAbasContratantes.setSelectedIndex(1);
            tpnAbasContratantes.setTitleAt(0, "Cadastrar Novos Contratantes");
            tfContratanteNome.setText("");
            tfContratanteCpf.setText("");
            tfContratanteCnpj.setText("");
            tfContratanteTelefone.setText("");
            tfContratanteCelular.setText("");

        }
    }//GEN-LAST:event_btCancelarAtualizacaoContratanteActionPerformed

    private void tfContratanteCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfContratanteCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfContratanteCpfActionPerformed

    private void tfContratanteCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfContratanteCnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfContratanteCnpjActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelarAtualizacaoContratante;
    private javax.swing.JButton btEditarContratante;
    private javax.swing.JButton btExcluirCliente;
    private javax.swing.JButton btFinalizarCadastroContratante;
    private javax.swing.JCheckBox ckbContratanteCnpj;
    private javax.swing.JCheckBox ckbContratanteCpf;
    private javax.swing.JCheckBox ckbContratanteNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnCadastrarNovoContratante;
    private javax.swing.JPanel pnGerenciarContratantes;
    private javax.swing.JTable tbContratantesCadastrados;
    private javax.swing.JFormattedTextField tfContratanteCelular;
    private javax.swing.JFormattedTextField tfContratanteCnpj;
    private javax.swing.JFormattedTextField tfContratanteCpf;
    private javax.swing.JTextField tfContratanteNome;
    private javax.swing.JFormattedTextField tfContratanteTelefone;
    private javax.swing.JTextField tfPalavraChaveContratante;
    private javax.swing.JTabbedPane tpnAbasContratantes;
    // End of variables declaration//GEN-END:variables
}
