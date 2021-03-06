/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bancodedados.ProfissionalBD;
import java.awt.Toolkit;
import java.util.ArrayList;
import modelo.tabelas.ModeloTabelaProfissionais;
import modelos.Profissional;

/**
 *
 * @author Jean
 */
public class TelaRelatorioProfissional extends javax.swing.JDialog {

    /**
     * Definição de modelo de tabela
     */
    ModeloTabelaProfissionais modeloTabelaProfissional = new ModeloTabelaProfissionais();

    /**
     * Definição conexão com o banco
     */
    ProfissionalBD conexaoTabelaProfissional = new ProfissionalBD();
    ArrayList<Profissional> listaOriginalTemporariaProfissional = new ArrayList();
    Profissional profissionalSelecionado = new Profissional();

    /**
     * Creates new form TelaRelatorioProfissional
     */
    public TelaRelatorioProfissional(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icon.png")));
        this.setLocationRelativeTo(parent);
        this.setTitle("Gerencia de profissionais");
        tfBuscarProfissional.requestFocus();
        tbListaProfissional.setModel(modeloTabelaProfissional); // trazendo modelo
        tbListaProfissional.getTableHeader().setReorderingAllowed(false);

        buscarProfissionaisNaTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfBuscarProfissional = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListaProfissional = new javax.swing.JTable();
        btCancelar = new javax.swing.JButton();
        btSelecionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa Profissional", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Nome do Profissional:");

        tfBuscarProfissional.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBuscarProfissionalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBuscarProfissional)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfBuscarProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tbListaProfissional.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbListaProfissional);

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btSelecionar.setText("Selecionar");
        btSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btSelecionar)))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancelar)
                    .addComponent(btSelecionar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfBuscarProfissionalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarProfissionalKeyReleased
        // TODO add your handling code here:
        if (tfBuscarProfissional.getText().isEmpty()) // se não tiver nada na tabela
        {
            modeloTabelaProfissional.inserirListaProfissionais(conexaoTabelaProfissional.selecionarTodosProfissionais());  // preencher tabela com o modelo
        } else {
            modeloTabelaProfissional.inserirListaProfissionais(conexaoTabelaProfissional.selecionarTodosProfissionais());// preencher dados
            listaOriginalTemporariaProfissional.clear();      // e limpar lista

            for (int i = 0; i < modeloTabelaProfissional.retornaListaProfissionais().size(); i++) //percorrer toda a tabela
            {
                if (modeloTabelaProfissional.retornaListaProfissionais().get(i).getProfissional_nome().toLowerCase().contains(tfBuscarProfissional.getText().toLowerCase())) // se o nome do usuario for igual o que ta digitado no campo
                {
                    listaOriginalTemporariaProfissional.add(modeloTabelaProfissional.retornaListaProfissionais().get(i)); //Preencher a lista com o usuario 
                }
            }
            modeloTabelaProfissional.inserirListaProfissionais(listaOriginalTemporariaProfissional); // preenchendo tabela ao finalizar
        }
        tbListaProfissional.updateUI();
    }//GEN-LAST:event_tfBuscarProfissionalKeyReleased

    private void btSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarActionPerformed
        // TODO add your handling code here:
        if (tbListaProfissional.getSelectedRow() != -1) // se tiver algum objeto selecionado na tabela
        {
            profissionalSelecionado = modeloTabelaProfissional.retornaListaProfissionais().get(tbListaProfissional.getSelectedRow()); // pegando o objeto há ser retornado à Telaprincipal
        }
        this.setVisible(false);
    }//GEN-LAST:event_btSelecionarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btCancelarActionPerformed

    public void buscarProfissionaisNaTabela() {
        modeloTabelaProfissional.inserirListaProfissionais(conexaoTabelaProfissional.selecionarTodosProfissionais());
        listaOriginalTemporariaProfissional = modeloTabelaProfissional.retornaListaProfissionais();
        tbListaProfissional.updateUI();
    }

    public Profissional retornarProfissionalSelecionado() {
        return profissionalSelecionado; // retornando usuario selecionado
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioProfissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioProfissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioProfissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioProfissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaRelatorioProfissional dialog = new TelaRelatorioProfissional(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btSelecionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbListaProfissional;
    private javax.swing.JTextField tfBuscarProfissional;
    // End of variables declaration//GEN-END:variables
}
