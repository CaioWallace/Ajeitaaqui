/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.telas;

import java.sql.*;
import br.com.MPS.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Edvaldo
 */
public class TelaOcorrencia extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaOcorrencia
     */
    public TelaOcorrencia() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //metodo pesquisar cliente
    private void pesquisar_cidadao() {
        String sql = "select idcid as Id, nomecid as Nome, fonecid as Fone from tbcidadao where nomecid like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCidPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblCidadao.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_campos() {
        int setar = tblCidadao.getSelectedRow();
        txtCidId.setText(tblCidadao.getModel().getValueAt(setar, 0).toString());
    }

    // metodo para cadastrar uma ocorrencia
    private void emitir_ocorrencia() {
        String sql = "insert into tbocorrencia(endereco,referencia,detalhes,idcidadao,Stts) values (?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            //pst.setString(1, cboStatus.getSelectedItem().toString() );
            pst.setString(1, txtEnd.getText());
            pst.setString(2, txtRefEnd.getText());
            pst.setString(3, txtDetOco.getText());
            pst.setString(4, txtCidId.getText());
            pst.setString(5, cboOco.getSelectedItem().toString());

            // validação dos campos obrigatorios
            if ((txtCidId.getText().isEmpty() || txtRefEnd.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios ");

            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Occorencia cadastrada com sucesso ");

                    txtCidId.setText(null);
                    txtEnd.setText(null);
                    txtRefEnd.setText(null);
                    txtDetOco.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo para pesquisar um ocorrencia
    private void pesquisar_ocorrencia() {
        // a linha abaixo cria uma caixa de entrada do tipo JOptionPane
        String num_oco = JOptionPane.showInputDialog(" Numero da Ocorrencia ");
        String sql = "select * from tbocorrencia where ocorrencia = " + num_oco;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                txtOco.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                txtEnd.setText(rs.getString(3));
                txtRefEnd.setText(rs.getString(4));
                txtDetOco.setText(rs.getString(5));
                txtCidId.setText(rs.getString(6));
                cboOco.setSelectedItem(rs.getString(7));
                //evitando problema
                btsOcoAdicionar.setEnabled(false);
                txtCidPesquisar.setEnabled(false);
                tblCidadao.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Ocorrencia não cadastrada ");
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Numero da Ocorrencia invalida");
            //System.out.println(e);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }
    }

    // metodo de alterar ocorrencia
    private void alterar_ocorrencia() {
        String sql = "update tbocorrencia set endereco=?, referencia=?, detalhes=?,Stts=? where ocorrencia = ? ";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEnd.getText());
            pst.setString(2, txtRefEnd.getText());
            pst.setString(3, txtDetOco.getText());
            pst.setString(4, cboOco.getSelectedItem().toString());
            pst.setString(5, txtOco.getText());

            if ((txtCidId.getText().isEmpty() || txtRefEnd.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios ");

            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Occorencia alterada com sucesso ");

                    txtOco.setText(null);
                    txtData.setText(null);
                    txtCidId.setText(null);
                    txtEnd.setText(null);
                    txtRefEnd.setText(null);
                    txtDetOco.setText(null);
                    //habilitar os objetos
                    btsOcoAdicionar.setEnabled(true);
                    txtCidPesquisar.setEnabled(true);
                    tblCidadao.setVisible(true);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo para excluir uma ocorrencia
    private void excluir_ocorrencia() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta Ocorrencia? ", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbocorrencia where ocorrencia=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtOco.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Ocorrencia apagada");

                    txtOco.setText(null);
                    txtData.setText(null);
                    txtCidId.setText(null);
                    txtEnd.setText(null);
                    txtRefEnd.setText(null);
                    txtDetOco.setText(null);
                    //habilitar os objetos
                    btsOcoAdicionar.setEnabled(true);
                    txtCidPesquisar.setEnabled(true);
                    tblCidadao.setVisible(true);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOco = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtCidPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCidId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCidadao = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtEnd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtRefEnd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDetOco = new javax.swing.JTextField();
        btsOcoAdicionar = new javax.swing.JButton();
        btnOcoPesquisar = new javax.swing.JButton();
        btnOcoAlterar = new javax.swing.JButton();
        btnOcoExcluir = new javax.swing.JButton();
        btnOcoImprimir = new javax.swing.JButton();
        cboOco = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ocorrencias");
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(640, 480));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº Ocorrencia");

        jLabel2.setText("Data");

        txtOco.setEditable(false);

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtOco)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel3.setText("Situação");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cidadão"));

        txtCidPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCidPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/pesquisa.png"))); // NOI18N

        jLabel5.setText("*ID");

        txtCidId.setEnabled(false);
        txtCidId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidIdActionPerformed(evt);
            }
        });

        tblCidadao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Fone"
            }
        ));
        tblCidadao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCidadaoMouseClicked(evt);
            }
        });
        tblCidadao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblCidadaoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblCidadao);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCidPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCidId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCidPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtCidId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setText("Endereço ");

        jLabel7.setText("Referencia de Endereço");

        jLabel8.setText("Detalhes da Ocorrencia ");

        btsOcoAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/create.png"))); // NOI18N
        btsOcoAdicionar.setToolTipText("Emitir uma Ocorrencia");
        btsOcoAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsOcoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsOcoAdicionarActionPerformed(evt);
            }
        });

        btnOcoPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/read.png"))); // NOI18N
        btnOcoPesquisar.setToolTipText("Procurar uma Ocorrencia");
        btnOcoPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOcoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcoPesquisarActionPerformed(evt);
            }
        });

        btnOcoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/update.png"))); // NOI18N
        btnOcoAlterar.setToolTipText("Editar uma Ocorrencia");
        btnOcoAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOcoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcoAlterarActionPerformed(evt);
            }
        });

        btnOcoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/remove.png"))); // NOI18N
        btnOcoExcluir.setToolTipText("Excluir uma Ocorrencia");
        btnOcoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcoExcluirActionPerformed(evt);
            }
        });

        btnOcoImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/printer.png"))); // NOI18N
        btnOcoImprimir.setToolTipText("Imprimir uma Ocorrencia");
        btnOcoImprimir.setPreferredSize(new java.awt.Dimension(80, 80));

        cboOco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aberto", "Em Andamento", "Fechado" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboOco, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRefEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDetOco)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btsOcoAdicionar)
                .addGap(18, 18, 18)
                .addComponent(btnOcoPesquisar)
                .addGap(33, 33, 33)
                .addComponent(btnOcoAlterar)
                .addGap(35, 35, 35)
                .addComponent(btnOcoExcluir)
                .addGap(37, 37, 37)
                .addComponent(btnOcoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboOco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtRefEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDetOco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btsOcoAdicionar)
                        .addComponent(btnOcoPesquisar)
                        .addComponent(btnOcoAlterar)
                        .addComponent(btnOcoExcluir))
                    .addComponent(btnOcoImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        setBounds(0, 0, 758, 495);
    }// </editor-fold>//GEN-END:initComponents

    private void tblCidadaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCidadaoKeyReleased


    }//GEN-LAST:event_tblCidadaoKeyReleased

    private void txtCidPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCidPesquisarKeyReleased
        // chamando o metodo pesquisar cidadao
        pesquisar_cidadao();
    }//GEN-LAST:event_txtCidPesquisarKeyReleased

    private void tblCidadaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCidadaoMouseClicked
        // chamando o metodo setar_campos
        setar_campos();
    }//GEN-LAST:event_tblCidadaoMouseClicked

    private void btsOcoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsOcoAdicionarActionPerformed
        // metodo de emitir ocorrencia
        emitir_ocorrencia();
    }//GEN-LAST:event_btsOcoAdicionarActionPerformed

    private void btnOcoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcoPesquisarActionPerformed
        // chamar o metodo pesquisar ocorrencia
        pesquisar_ocorrencia();
    }//GEN-LAST:event_btnOcoPesquisarActionPerformed

    private void txtCidIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidIdActionPerformed

    private void btnOcoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcoAlterarActionPerformed
        // chama o metodo de alterar ocorrencia
        alterar_ocorrencia();
    }//GEN-LAST:event_btnOcoAlterarActionPerformed

    private void btnOcoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcoExcluirActionPerformed
        // chamando o metodo para excluir uma ocorrencia
        excluir_ocorrencia();
    }//GEN-LAST:event_btnOcoExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOcoAlterar;
    private javax.swing.JButton btnOcoExcluir;
    private javax.swing.JButton btnOcoImprimir;
    private javax.swing.JButton btnOcoPesquisar;
    private javax.swing.JButton btsOcoAdicionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCidadao;
    private javax.swing.JTextField txtCidId;
    private javax.swing.JTextField txtCidPesquisar;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDetOco;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtOco;
    private javax.swing.JTextField txtRefEnd;
    // End of variables declaration//GEN-END:variables
}
