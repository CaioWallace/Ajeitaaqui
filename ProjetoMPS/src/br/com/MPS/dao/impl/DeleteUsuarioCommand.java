/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao.impl;

import br.com.MPS.dao.DaoCommand;
import br.com.MPS.dao.DaoException;
import br.com.MPS.dao.UsuarioDao;

/**
 * Classe que implementa um commando de deletar usuario.
 * 
 * @author Edvaldo
 */
public class DeleteUsuarioCommand implements DaoCommand {

    private UsuarioDao usuarioDao;
    private int usuarioId;
    
    public DeleteUsuarioCommand(UsuarioDao usuarioDao, int usuarioId) {
        this.usuarioDao = usuarioDao;
        this.usuarioId = usuarioId;
    }
            
    @Override
    public void execute() throws DaoException {
        usuarioDao.delete(usuarioId);
    }
    
}
