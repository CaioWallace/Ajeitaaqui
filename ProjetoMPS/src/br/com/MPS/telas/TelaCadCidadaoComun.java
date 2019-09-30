/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.telas;

import java.sql.*;
import br.com.MPS.dao.ConnectionFactory;
import javax.swing.JOptionPane;
//a linha abaixo importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Edvaldo
 */
public class TelaCadCidadaoComun extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCadCidadaoComun
     */
    public TelaCadCidadaoComun() {
        initComponents();
        conexao = new ConnectionFactory().getConnection();
    }

    //metodo para adicionar cidadao comun
    private void adicionar() {
        String sql = "insert into tbcidadao(nomecid, cpfcid, cepcid, emailcid, fonecid, logincid, senhacid) values(?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtComunNome.getText());
            pst.setString(2, txtComunCEP.getText());
            pst.setString(3, txtComunCPF.getText());
            pst.setString(4, txtComunFone.getText());
            pst.setString(5, txtComunEmail.getText());
            pst.setString(6, txtComunLogin.getText());
            pst.setString(7, txtComunSenha.getText());

            // validação dos campos obrigatorios
            if (txtComunNome.getText().isEmpty() || (txtComunCEP.getText().isEmpty() || (txtComunCPF.getText().isEmpty() || (txtComunFone.getText().isEmpty()
                    || (txtComunLogin.getText().isEmpty() || (txtComunSenha.getText().isEmpty())))))) {

                JOptionPane.showMessageDialog(null, " Preencha todos os Campos Obrigatório ");

            } else {

                //a linha abaixo atualiza a tabela usuarios com os dados do formulario
                // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                //a linha abaixo serve de apoio para entedimendo da logica
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, " Cidadao Cadastrado com Sucesso ");
                    
                    txtComunNome.setText(null);
                    txtComunCEP.setText(null);
                    txtComunCPF.setText(null);
                    txtComunFone.setText(null);
                    txtComunEmail.setText(null);
                    txtComunLogin.setText(null);
                    txtComunSenha.setText(null);
                    ;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //metodo para pesquisar clientes pelo nome com filtro
    private void pesquisar_cidadao() {
        String sql = "select * from tbcidadao where nomecid like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            // caixa de pesquisa para o ?
            //atenção ao "%" - continuação na String sql
            pst.setString(1, txtComunPesquisar.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para prencher a tabela

            tblCidadao.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // método para setar os campos
    public void setar_campos() {
        int setar = tblCidadao.getSelectedRow();
        txtIdCid.setText(tblCidadao.getModel().getValueAt(setar, 0).toString());
        txtComunNome.setText(tblCidadao.getModel().getValueAt(setar, 1).toString());
        txtComunCPF.setText(tblCidadao.getModel().getValueAt(setar, 2).toString());
        txtComunCEP.setText(tblCidadao.getModel().getValueAt(setar, 3).toString());
        txtComunEmail.setText(tblCidadao.getModel().getValueAt(setar, 4).toString());
        txtComunFone.setText(tblCidadao.getModel().getValueAt(setar, 5).toString());
        txtComunLogin.setText(tblCidadao.getModel().getValueAt(setar, 6).toString());
        txtComunSenha.setText(tblCidadao.getModel().getValueAt(setar, 7).toString());

        btnAdicionar.setEnabled(false);
    }
        // a linha abaio desabilita  o botão adicionar
        
        

    //metodo para alterar dados do cliente
    private void alterar() {
        
        String sql = "update tbcidadao set nomecid=?, cpfcid=?, cepcid=?, emailcid=?, fonecid=?, logincid=?, senhacid=?  where idcid=? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtComunNome.getText());
            pst.setString(2, txtComunCPF.getText());
            pst.setString(3, txtComunCEP.getText());
            pst.setString(4, txtComunEmail.getText());
            pst.setString(5, txtComunFone.getText());
            pst.setString(6, txtComunLogin.getText());
            pst.setString(7, txtComunSenha.getText());

            if (txtComunNome.getText().isEmpty() || (txtComunCPF.getText().isEmpty() || (txtComunCEP.getText().isEmpty()
                    || (txtComunFone.getText().isEmpty() || (txtComunLogin.getText().isEmpty() || (txtComunSenha.getText().isEmpty())))))) {

                JOptionPane.showMessageDialog(null, " Preencha todos os Campos Obrigatório ");

            } else {

                //a linha abaixo atualiza a tabela usuarios com os dados do formulario
                // a estrutura abaixo é usada para confirmar a auteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                //a linha abaixo serve de apoio para entedimendo da logica
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    
                    JOptionPane.showMessageDialog(null, " Dados do Cidadão Modificado  com Sucesso ");
                    
                    txtComunNome.setText(null);
                    txtComunCPF.setText(null);
                    txtComunCEP.setText(null);
                    txtComunEmail.setText(null);
                    txtComunFone.setText(null);
                    txtComunLogin.setText(null);
                    txtComunSenha.setText(null);
                    btnAdicionar.setEnabled(true);

                    
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtComunNome = new javax.swing.JTextField();
        txtComunCPF = new javax.swing.JTextField();
        txtComunCEP = new javax.swing.JTextField();
        txtComunEmail = new javax.swing.JTextField();
        txtComunFone = new javax.swing.JTextField();
        txtComunLogin = new javax.swing.JTextField();
        txtComunSenha = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        txtComunPesquisar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCidadao = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtIdCid = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Cidadao");
        setResizable(false);

        jLabel2.setText("* Nome");

        jLabel3.setText("* CPF");

        jLabel4.setText("* CEP");

        jLabel5.setText("E-mail");

        jLabel6.setText("* Fone");

        jLabel7.setText("* Login");

        jLabel8.setText("* Senha");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/create.png"))); // NOI18N
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/update.png"))); // NOI18N
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        txtComunPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComunPesquisarActionPerformed(evt);
            }
        });
        txtComunPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtComunPesquisarKeyReleased(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/pesquisa.png"))); // NOI18N

        tblCidadao.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCidadao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCidadaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCidadao);

        jLabel1.setText("ID");

        txtIdCid.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(btnAdicionar)
                .addGap(73, 73, 73)
                .addComponent(btnAlterar)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtComunPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addComponent(jLabel9))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtComunNome, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdCid, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtComunFone))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtComunCEP))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtComunLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtComunEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtComunCPF))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtComunSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtComunPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtComunCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtComunCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtComunNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(txtIdCid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtComunFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtComunEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtComunLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtComunSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // colocando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    // o evento abaixo é do tipo "enquanto for digitando"
    private void txtComunPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComunPesquisarKeyReleased
        // chamar o metodo pesquisar cidadao
        pesquisar_cidadao();
    }//GEN-LAST:event_txtComunPesquisarKeyReleased

    private void txtComunPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComunPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComunPesquisarActionPerformed

    private void tblCidadaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCidadaoMouseClicked
        // setar os dados
        setar_campos();
    }//GEN-LAST:event_tblCidadaoMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // chamando o metodo para alterar cidadao
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadCidadaoComun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadCidadaoComun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadCidadaoComun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadCidadaoComun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadCidadaoComun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCidadao;
    private javax.swing.JTextField txtComunCEP;
    private javax.swing.JTextField txtComunCPF;
    private javax.swing.JTextField txtComunEmail;
    private javax.swing.JTextField txtComunFone;
    private javax.swing.JTextField txtComunLogin;
    private javax.swing.JTextField txtComunNome;
    private javax.swing.JTextField txtComunPesquisar;
    private javax.swing.JTextField txtComunSenha;
    private javax.swing.JTextField txtIdCid;
    // End of variables declaration//GEN-END:variables
}
