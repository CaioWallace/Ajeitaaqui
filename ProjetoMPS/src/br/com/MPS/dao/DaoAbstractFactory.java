/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao;

import br.com.MPS.entity.Cidadao;
import br.com.MPS.entity.Ocorrencia;
import br.com.MPS.entity.Usuario;

/**
 *
 * @author Edvaldo
 */
public interface DaoAbstractFactory {
    
    CidadaoDao<Cidadao> getCidadaoDao();
    
    OcorrenciaDao<Ocorrencia> getOcorrenciaDao();
    
    UsuarioDao<Usuario> getUsuarioDao();
}
