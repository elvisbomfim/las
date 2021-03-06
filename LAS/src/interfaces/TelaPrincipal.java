/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bancodedados.ConexaoBanco;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

/**
 *
 * @author Jean
 */
public class TelaPrincipal extends javax.swing.JFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        setIcon();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/bg.jpg"));
        Image image = icon.getImage();
        jdptelaPrincipal = new javax.swing.JDesktopPane(){

            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }

        };
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiCadastrarCliente = new javax.swing.JMenuItem();
        jmiCadastrarContratante = new javax.swing.JMenuItem();
        jmiCadastrarProfissional = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiRecibo = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        miRelatorios = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" Sistema de Licenciamento Ambiental");

        javax.swing.GroupLayout jdptelaPrincipalLayout = new javax.swing.GroupLayout(jdptelaPrincipal);
        jdptelaPrincipal.setLayout(jdptelaPrincipalLayout);
        jdptelaPrincipalLayout.setHorizontalGroup(
            jdptelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        jdptelaPrincipalLayout.setVerticalGroup(
            jdptelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );

        getContentPane().add(jdptelaPrincipal, java.awt.BorderLayout.CENTER);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons/reseller_programm.png"))); // NOI18N
        jMenu1.setText("Entidades");

        jmiCadastrarCliente.setText("Cadastrar Cliente");
        jmiCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCadastrarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(jmiCadastrarCliente);

        jmiCadastrarContratante.setText("Cadastrar Contratante");
        jmiCadastrarContratante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCadastrarContratanteActionPerformed(evt);
            }
        });
        jMenu1.add(jmiCadastrarContratante);

        jmiCadastrarProfissional.setText("Cadastrar Profissional");
        jmiCadastrarProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCadastrarProfissionalActionPerformed(evt);
            }
        });
        jMenu1.add(jmiCadastrarProfissional);

        jMenuItem1.setText("Cadastrar Representante");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem8.setText("Atualizar Secretaria");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons/paste_plain.png"))); // NOI18N
        jMenu2.setText("Relatorios");

        jmiRecibo.setText("Recibo");
        jmiRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReciboActionPerformed(evt);
            }
        });
        jMenu2.add(jmiRecibo);

        jMenuItem2.setText("TRA");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Requerimento");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Procuração");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenu3.setText("Caracterização");

        jMenuItem5.setText("Restaurante");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        miRelatorios.setText("Condomínio");
        miRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRelatoriosActionPerformed(evt);
            }
        });
        jMenu3.add(miRelatorios);

        jMenuItem6.setText("Turísticos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Terraplanagem");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmiCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCadastrarClienteActionPerformed
        Fechar();
        jifAbasClientes obj = new jifAbasClientes();
        jdptelaPrincipal.add(obj);
        obj.setVisible(true);
        
        Dimension d = obj.getDesktopPane().getSize();
        obj.setLocation((d.width - obj.getSize().width) / 2, (d.height - obj.getSize().height) / 2);

    }//GEN-LAST:event_jmiCadastrarClienteActionPerformed

    private void jmiCadastrarContratanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCadastrarContratanteActionPerformed
        Fechar();
        jifAbasContratantes obj = new jifAbasContratantes();
        jdptelaPrincipal.add(obj);
        obj.setVisible(true);
        setPosicao(obj);
    }//GEN-LAST:event_jmiCadastrarContratanteActionPerformed

    private void jmiCadastrarProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCadastrarProfissionalActionPerformed
        Fechar();
        jifAbasProfissionais obj = new jifAbasProfissionais();
        jdptelaPrincipal.add(obj);
        obj.setVisible(true);
        setPosicao(obj);
    }//GEN-LAST:event_jmiCadastrarProfissionalActionPerformed

    private void jmiReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReciboActionPerformed
        Fechar();
        jifFormRecibo recibo = new jifFormRecibo();
        recibo.setVisible(true);
        jdptelaPrincipal.add(recibo);
        setPosicao(recibo);
    }//GEN-LAST:event_jmiReciboActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Fechar();
        jifAbasRepresentantes representante = new jifAbasRepresentantes();
        representante.setVisible(true);
        jdptelaPrincipal.add(representante);
        setPosicao(representante);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Fechar();
        jifFormTra tra = new jifFormTra();
        tra.setVisible(true);
        jdptelaPrincipal.add(tra);
        setPosicao(tra);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Fechar();
        jifFormRequerimento requerimento = new jifFormRequerimento();
        requerimento.setVisible(true);
        jdptelaPrincipal.add(requerimento);
        setPosicao(requerimento);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Fechar();
        jifFormProcuracao procuracao = new jifFormProcuracao();
        procuracao.setVisible(true);
        jdptelaPrincipal.add(procuracao);
        setPosicao(procuracao);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Fechar();
        jifFormRestautante obj = new jifFormRestautante();
        jdptelaPrincipal.add(obj);
        obj.setVisible(true);
        setPosicao(obj);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void miRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRelatoriosActionPerformed
        Fechar();
        jifFormCondominio obj = new jifFormCondominio();
        jdptelaPrincipal.add(obj);
        obj.setVisible(true);
        setPosicao(obj);
    }//GEN-LAST:event_miRelatoriosActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Fechar();
        jifFormTuristico obj = new jifFormTuristico();
        jdptelaPrincipal.add(obj);
        obj.setVisible(true);
        setPosicao(obj);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Fechar();
        jifFormTerraplanagem obj = new jifFormTerraplanagem();
        jdptelaPrincipal.add(obj);
        obj.setVisible(true);
        setPosicao(obj);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Fechar();
        jifAbasSecretarias obj = new jifAbasSecretarias();
        jdptelaPrincipal.add(obj);
        obj.setVisible(true);
        setPosicao(obj);
    }//GEN-LAST:event_jMenuItem8ActionPerformed
    
    public void Fechar() {
        JInternalFrame[] frames = jdptelaPrincipal.getAllFrames();
        for (int i = 0; i < frames.length; i++) {
            frames[i].dispose();
        }
    }
    
    public void setPosicao(JInternalFrame fr) {
        Dimension d = fr.getDesktopPane().getSize();
        fr.setLocation((d.width - fr.getSize().width) / 2, (d.height - fr.getSize().height) / 2);
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JDesktopPane jdptelaPrincipal;
    private javax.swing.JMenuItem jmiCadastrarCliente;
    private javax.swing.JMenuItem jmiCadastrarContratante;
    private javax.swing.JMenuItem jmiCadastrarProfissional;
    private javax.swing.JMenuItem jmiRecibo;
    private javax.swing.JMenuItem miRelatorios;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icon.png")));
    }
}
