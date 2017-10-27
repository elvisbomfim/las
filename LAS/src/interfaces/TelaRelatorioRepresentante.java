/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bancodedados.RepresentanteBD;
import java.util.ArrayList;
import modelo.tabelas.ModeloTabelaRepresentantes;
import modelos.Representante;

/**
 *
 * @author Jean
 */
public class TelaRelatorioRepresentante extends javax.swing.JDialog {

    ModeloTabelaRepresentantes modeloTabelaRepresentante = new ModeloTabelaRepresentantes();
    RepresentanteBD conexaoTabelaRepresentante = new RepresentanteBD();
    ArrayList<Representante> listaOriginalTemporariaRepresentante = new ArrayList();
    Representante representanteSelecionado = new Representante();

    /**
     * Creates new form TelaRelatorioRecibo
     */
    public TelaRelatorioRepresentante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(parent);
        this.setTitle("Gerencia de representantes");
        tfBuscarRepresentante.requestFocus();
        tbListaRepresentantes.setModel(modeloTabelaRepresentante);
        tbListaRepresentantes.getTableHeader().setReorderingAllowed(false);

        buscarRepresentantesNaTabela();
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
        tfBuscarRepresentante = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListaRepresentantes = new javax.swing.JTable();
        btCancelarSelecaoRepresentante = new javax.swing.JButton();
        btSelecionarRepresentante = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Representante", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Nome do Representante:");

        tfBuscarRepresentante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBuscarRepresentanteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBuscarRepresentante)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfBuscarRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbListaRepresentantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbListaRepresentantes);

        btCancelarSelecaoRepresentante.setText("Cancelar");
        btCancelarSelecaoRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarSelecaoRepresentanteActionPerformed(evt);
            }
        });

        btSelecionarRepresentante.setText("Selecionar");
        btSelecionarRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionarRepresentanteActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btCancelarSelecaoRepresentante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btSelecionarRepresentante))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancelarSelecaoRepresentante)
                    .addComponent(btSelecionarRepresentante))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfBuscarRepresentanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscarRepresentanteKeyReleased
        if (tfBuscarRepresentante.getText().isEmpty()) {
            modeloTabelaRepresentante.inserirListaRepresentantes(conexaoTabelaRepresentante.selecionarTodosRepresentantes());
        } else {
            modeloTabelaRepresentante.inserirListaRepresentantes(conexaoTabelaRepresentante.selecionarTodosRepresentantes());
            listaOriginalTemporariaRepresentante.clear();

            for (int i = 0; i < modeloTabelaRepresentante.retornaListaRepresentantes().size(); i++) {
                if (modeloTabelaRepresentante.retornaListaRepresentantes().get(i).getRepresentante_nome().toLowerCase().contains(tfBuscarRepresentante.getText().toLowerCase())) {
                    listaOriginalTemporariaRepresentante.add(modeloTabelaRepresentante.retornaListaRepresentantes().get(i));
                }
            }

            modeloTabelaRepresentante.inserirListaRepresentantes(listaOriginalTemporariaRepresentante);

        }

        tbListaRepresentantes.updateUI();
    }//GEN-LAST:event_tfBuscarRepresentanteKeyReleased

    private void btCancelarSelecaoRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarSelecaoRepresentanteActionPerformed
       
        this.setVisible(false);
    }//GEN-LAST:event_btCancelarSelecaoRepresentanteActionPerformed

    private void btSelecionarRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarRepresentanteActionPerformed
        // TODO add your handling code here:
        if (tbListaRepresentantes.getSelectedRow() != -1) {
            representanteSelecionado = modeloTabelaRepresentante.retornaListaRepresentantes().get(tbListaRepresentantes.getSelectedRow());
        }

        this.setVisible(false);
        /*   if(tbCursosCadastrados.getSelectedRow()!=-1)
        {
            cursoSelecionado = modeloTabelaCursos.retornaListaCursos().get(tbCursosCadastrados.getSelectedRow());
        }
    }//GEN-LAST:event_btSelecionarRepresentanteActionPerformed

    private void btSelecionarActionPerformed(java.awt.event.ActionEvent evt) {

        if (tbListaClientes.getSelectedRow() != -1) {
            clienteSelecionado = modeloTabelaCliente.retornaListaClientes().get(tbListaClientes.getSelectedRow());
        }

        this.setVisible(false);
        /*   if(tbCursosCadastrados.getSelectedRow()!=-1)
        {
            cursoSelecionado = modeloTabelaCursos.retornaListaCursos().get(tbCursosCadastrados.getSelectedRow());
        }
        
        this.setVisible(false);*/
// TODO add your handling code here:
    }

    public void buscarRepresentantesNaTabela() {

        modeloTabelaRepresentante.inserirListaRepresentantes(conexaoTabelaRepresentante.selecionarTodosRepresentantes());
        listaOriginalTemporariaRepresentante = modeloTabelaRepresentante.retornaListaRepresentantes();
        tbListaRepresentantes.updateUI();

    }

    public Representante retornarRepresentanteSelecionado() {
        return representanteSelecionado;
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
            java.util.logging.Logger.getLogger(TelaRelatorioRepresentante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioRepresentante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioRepresentante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorioRepresentante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                TelaRelatorioRepresentante dialog = new TelaRelatorioRepresentante(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btCancelarSelecaoRepresentante;
    private javax.swing.JButton btSelecionarRepresentante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbListaRepresentantes;
    private javax.swing.JTextField tfBuscarRepresentante;
    // End of variables declaration//GEN-END:variables
}
