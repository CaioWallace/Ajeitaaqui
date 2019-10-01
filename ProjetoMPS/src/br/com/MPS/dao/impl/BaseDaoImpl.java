/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao.impl;

import br.com.MPS.dao.BaseDao;
import br.com.MPS.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Edvaldo
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T>{
    
    protected Connection conexao;
    
    public BaseDaoImpl() {
        conexao = new ConnectionFactory().getConnection();
    }
    
    public void closeConexao() {
        try {
            conexao.close();
        } catch (SQLException ex) {}
    }
}
