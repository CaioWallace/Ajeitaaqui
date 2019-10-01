/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao;

/**
 *
 * @author Edvaldo
 * @param <Usuario>
 */
public interface UsuarioDao<Usuario> extends BaseDao<Usuario> {
    
    Usuario getByLoginESenha(String login, String senha);
}
