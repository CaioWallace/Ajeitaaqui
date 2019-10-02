/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao.impl;

import br.com.MPS.dao.DaoIterator;
import br.com.MPS.entity.Cidadao;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Edvaldo
 */
public class CidadaoIterator implements DaoIterator<Cidadao>{

    private final ResultSet resultSet;
    
    public CidadaoIterator(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
    
    @Override
    public boolean hasNext() {
        try {
            return resultSet.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public Cidadao next() {
        try {
            Cidadao cidadao = new Cidadao();
            cidadao.setId(resultSet.getInt(1));
            cidadao.setNome(resultSet.getString(2));
            cidadao.setCpf(resultSet.getString(3));
            cidadao.setCep(resultSet.getString(4));
            cidadao.setEmail(resultSet.getString(5));
            cidadao.setFone(resultSet.getString(6));
            return cidadao;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
