/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao.impl;

import br.com.MPS.dao.CidadaoDao;
import br.com.MPS.dao.DaoException;
import br.com.MPS.dao.DaoIterator;
import br.com.MPS.entity.Cidadao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Edvaldo
 */
public class CidadaoDaoImpl extends BaseDaoImpl<Cidadao> implements CidadaoDao<Cidadao>  {

    @Override
    public Cidadao get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Cidadao t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cidadao t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public DaoIterator<Cidadao> listarComecandoPor(String comeco) throws DaoException {
        try {
            String sql = "select * from tbcidadao where nomecid like ? ";
            
            PreparedStatement pst = conexao.prepareStatement(sql);
            
            // caixa de pesquisa para o ?
            //atenção ao "%" - continuação na String sql
            pst.setString(1, comeco + "%");
            ResultSet rs = pst.executeQuery();
            return new CidadaoIterator(rs);
            
        } catch (SQLException ex) {
            throw new DaoException("Falha ao listar cidadao", ex);
        }
    }
    
}
