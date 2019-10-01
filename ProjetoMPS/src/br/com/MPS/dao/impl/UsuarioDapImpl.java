/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao.impl;

import br.com.MPS.dao.DaoException;
import br.com.MPS.dao.UsuarioDao;
import br.com.MPS.entity.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Edvaldo
 */
public class UsuarioDapImpl extends BaseDaoImpl<Usuario> implements UsuarioDao<Usuario> {

    @Override
    public Usuario getByLoginESenha(String login, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(int id) throws DaoException {
        //metodo para consultar usuario
        String sql = "select * from tbusuarios where iduser = ?";
        ResultSet rs = null;
        
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                
                Usuario usuario = new Usuario.Builder()
                .setId(rs.getInt(1))
                .setUsuario(rs.getString(2))
                .setCpf(rs.getString(3))
                .setCep(rs.getString(4))
                .setEmail(rs.getString(5))
                .setFone(rs.getString(6))
                .setLogin(rs.getString(7))
                .setSenha(rs.getString(8))
                .setPerfil(rs.getString(9))
                .build();
                 
                return usuario;
            } else {
                throw new DaoException("Usuario não encontado ");
            }
        } catch (Exception e) {
           throw new DaoException("Falha ao obter usuario. ", e);
        } finally {
            closeConexao();
        }
    }

    @Override
    public void save(Usuario usuario) throws DaoException {
        String sql = "insert into tbusuarios(iduser,usuario,cpf,cep,email,fone,login,senha,perfil) values(?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, usuario.getId());
            pst.setString(2, usuario.getUsuario());
            pst.setString(3, usuario.getCpf());
            pst.setString(4, usuario.getCep());
            pst.setString(5, usuario.getEmail());
            pst.setString(6, usuario.getFone());
            pst.setString(7, usuario.getLogin());
            pst.setString(8, usuario.getSenha());
            pst.setString(9, usuario.getPerfil());
            
            pst.executeUpdate();
            
        } catch (Exception e) {
            throw new DaoException("Falha ao salvar usuario", e);
        } finally {
            closeConexao();
        }
    }

    @Override
    public void update(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
