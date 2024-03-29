/*
 *
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 */
package br.com.MPS.telas;

/**
 *
 * @author Edvaldo
 */
import br.com.MPS.dao.DaoAbstractFactory;
import br.com.MPS.dao.DaoCommand;
import br.com.MPS.dao.DaoException;
import br.com.MPS.dao.UsuarioDao;
import br.com.MPS.dao.impl.DaoFactoryImpl;
import br.com.MPS.dao.impl.DeleteUsuarioCommand;
import br.com.MPS.entity.Usuario;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TelaCadCidadao extends javax.swing.JFrame {

    DaoAbstractFactory daoFactory = new DaoFactoryImpl();
    List<Memento> savedStates = new ArrayList<>();

    /**
     * Creates new form TelaCadCidadao
     */
    public TelaCadCidadao() {
        initComponents();
        KeyListener listener = new OnKeyUpListener();
        
        txtUsuId.addKeyListener(listener);
        txtUsuNome.addKeyListener(listener);
        txtUsuCPF.addKeyListener(listener);
        txtUsuCEP.addKeyListener(listener);
        txtUsuEmail.addKeyListener(listener);
        txtUsuFone.addKeyListener(listener);
        txtUsuLogin.addKeyListener(listener);
        txtUsuSenha.addKeyListener(listener);
        
        //salvando estado inical do form (que é vazio)
        pushMemento(saveToMemento());
    }
    
    private void pushMemento(Memento memento) {
        savedStates.add(memento);
        if (savedStates.size() > 100) {
            savedStates.remove(0);
        }
    }
    
    private Memento popMemento() {
        return savedStates.remove(savedStates.size()-1);
    }
    
    private class OnKeyUpListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                if (!savedStates.isEmpty()) {
                    Memento memento = popMemento();
                    restoreFromMemento(memento);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pushMemento(saveToMemento());
        }
        
    }
    
    private void consultar() {
        
        UsuarioDao<Usuario> usuarioDao = daoFactory.getUsuarioDao();
        
        try {
            Usuario usuario = usuarioDao.get(Integer.parseInt(txtUsuId.getText()));
            
            txtUsuNome.setText(usuario.getUsuario());
            txtUsuCPF.setText(usuario.getCpf());
            txtUsuCEP.setText(usuario.getCep());
            txtUsuEmail.setText(usuario.getEmail());
            txtUsuFone.setText(usuario.getFone());
            txtUsuLogin.setText(usuario.getLogin());
            CboUsuPerfil.setSelectedItem(usuario.getPerfil());
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(null, ex);
            // as linhas abaixo "limpam" os campos
            txtUsuNome.setText(null);
            txtUsuCPF.setText(null);
            txtUsuCEP.setText(null);
            txtUsuEmail.setText(null);
            txtUsuFone.setText(null);
            txtUsuLogin.setText(null);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um identificador válido");
        }
    }

    //metodo para adicionar usuários
    private void adicionar() {
        UsuarioDao<Usuario> usuarioDao = daoFactory.getUsuarioDao();
        
        try {
            Usuario usuario = new Usuario.Builder()
                .setId(Integer.parseInt(txtUsuId.getText()))
                .setUsuario(txtUsuNome.getText())
                .setCpf(txtUsuCPF.getText())
                .setCep(txtUsuCEP.getText())
                .setEmail(txtUsuEmail.getText())
                .setFone(txtUsuFone.getText())
                .setLogin(txtUsuLogin.getText())
                .setSenha(txtUsuSenha.getText())
                .setPerfil(CboUsuPerfil.getSelectedItem().toString())
                .build();
            
            usuarioDao.save(usuario);
            
            JOptionPane.showMessageDialog(null, " Usuario Cadastrado com Sucesso ");
            txtUsuId.setText(null);
            txtUsuNome.setText(null);
            txtUsuCPF.setText(null);
            txtUsuCEP.setText(null);
            txtUsuEmail.setText(null);
            txtUsuFone.setText(null);
            txtUsuLogin.setText(null);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, txtUsuId.getText() + " não é um identificador válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Criando o metodo para alterar dados do usuario
    private void alterar(){
        UsuarioDao<Usuario> usuarioDao = daoFactory.getUsuarioDao();
        
        try {
            Usuario usuario = new Usuario.Builder()
                .setId(Integer.parseInt(txtUsuId.getText()))
                .setUsuario(txtUsuNome.getText())
                .setCpf(txtUsuCPF.getText())
                .setCep(txtUsuCEP.getText())
                .setEmail(txtUsuEmail.getText())
                .setFone(txtUsuFone.getText())
                .setLogin(txtUsuLogin.getText())
                .setSenha(txtUsuSenha.getText())
                .setPerfil(CboUsuPerfil.getSelectedItem().toString())
                .build();
            
            usuarioDao.update(usuario);
            
            JOptionPane.showMessageDialog(null, " Dados do usuário modificados com sucesso.");
            txtUsuId.setText(null);
            txtUsuNome.setText(null);
            txtUsuCPF.setText(null);
            txtUsuCEP.setText(null);
            txtUsuEmail.setText(null);
            txtUsuFone.setText(null);
            txtUsuLogin.setText(null);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, txtUsuId.getText() + " não é um identificador válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private Memento saveToMemento() {
        return new Memento(txtUsuId.getText(), txtUsuNome.getText(), txtUsuCPF.getText(), txtUsuCEP.getText(), txtUsuEmail.getText(), txtUsuFone.getText(), txtUsuLogin.getText(), txtUsuSenha.getText(), CboUsuPerfil.getSelectedItem().toString());
    }
    
    private void restoreFromMemento(Memento memento) {
        txtUsuId.setText(memento.id);
        txtUsuNome.setText(memento.nome);
        txtUsuCPF.setText(memento.cpf);
        txtUsuCEP.setText(memento.cep);
        txtUsuEmail.setText(memento.email);
        txtUsuFone.setText(memento.fone);
        txtUsuLogin.setText(memento.login);
        txtUsuSenha.setText(memento.senha);
        CboUsuPerfil.setSelectedItem(memento.perfil);
    }
    
    static class Memento {
        public String id;
        public String nome;
        public String cpf;
        public String cep;
        public String email;
        public String fone;
        public String login;
        public String senha;
        public String perfil;

        public Memento(String id, String nome, String cpf, String cep, String email, String fone, String login, String senha, String perfil) {
            this.id = id;
            this.nome = nome;
            this.cpf = cpf;
            this.cep = cep;
            this.email = email;
            this.fone = fone;
            this.login = login;
            this.senha = senha;
            this.perfil = perfil;
        }
    }
    
    //metodo responsavel pela remoção de usuarios
    private void remover() {
        //a etrutura abaixo confirma a remoão do usuario
        int confirma=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário? ", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma==JOptionPane.YES_NO_OPTION) {
            try {
                UsuarioDao<Usuario> usuarioDao = daoFactory.getUsuarioDao();
                DaoCommand deleteUsuarioCmd = new DeleteUsuarioCommand(usuarioDao, Integer.parseInt(txtUsuId.getText()));
                deleteUsuarioCmd.execute();
                
                JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, txtUsuId.getText() + " não é um identificador válido.");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuCPF = new javax.swing.JTextField();
        txtUsuCEP = new javax.swing.JTextField();
        txtUsuEmail = new javax.swing.JTextField();
        txtUsuFone = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CboUsuPerfil = new javax.swing.JComboBox<String>();
        jLabel10 = new javax.swing.JLabel();
        btnUsoCreate = new javax.swing.JButton();
        btnUsuRemove = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Usuario");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("* ID");

        jLabel2.setText("* Nome");

        jLabel3.setText("* CPF");

        jLabel4.setText("* CEP");

        jLabel5.setText("* E-mail");

        jLabel6.setText("* Fone");

        jLabel7.setText("* Login");

        jLabel8.setText("* Senha");

        jLabel9.setText("* Perfil");

        CboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "admin", "user", " " }));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/Marca.png"))); // NOI18N

        btnUsoCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/create.png"))); // NOI18N
        btnUsoCreate.setToolTipText("Adicionar");
        btnUsoCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsoCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsoCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoCreateActionPerformed(evt);
            }
        });

        btnUsuRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/remove.png"))); // NOI18N
        btnUsuRemove.setToolTipText("Remover");
        btnUsuRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsuRemove.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuRemoveActionPerformed(evt);
            }
        });

        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/read.png"))); // NOI18N
        btnUsuRead.setToolTipText("Consultar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/MPS/icones/update.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Editar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        jButton1.setText("Ctrl+Z");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUsuNome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(201, 201, 201))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsuLogin)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(46, 46, 46)
                        .addComponent(btnUsoCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnUsuRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUsoCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUsuRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(CboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtUsuEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(668, 499));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // Chamando o metodo consultar
        consultar();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnUsoCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoCreateActionPerformed
        // chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnUsoCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // chamando o metodo alterar
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuRemoveActionPerformed
        // chamar o metodo remover
        remover();
    }//GEN-LAST:event_btnUsuRemoveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!savedStates.isEmpty()) {
            Memento memento = popMemento();
            restoreFromMemento(memento);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadCidadao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadCidadao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadCidadao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadCidadao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadCidadao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboUsuPerfil;
    private javax.swing.JButton btnUsoCreate;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuRemove;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JTextField txtUsuCEP;
    private javax.swing.JTextField txtUsuCPF;
    private javax.swing.JTextField txtUsuEmail;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
