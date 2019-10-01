/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao.impl;

import br.com.MPS.dao.CidadaoDao;
import br.com.MPS.dao.DaoAbstractFactory;
import br.com.MPS.dao.OcorrenciaDao;
import br.com.MPS.dao.UsuarioDao;

/**
 *
 * @author Edvaldo
 */
public class DaoFactoryImpl implements DaoAbstractFactory {

    @Override
    public CidadaoDao getCidadaoDao() {
        return new CidadaoDaoImpl();
    }

    @Override
    public OcorrenciaDao getOcorrenciaDao() {
        return new OcorrenciaDaoImpl();
    }

    @Override
    public UsuarioDao getUsuarioDao() {
        return new UsuarioDapImpl();
    }
    
}
