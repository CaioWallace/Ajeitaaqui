/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao;

/**
 *
 * @author Edvaldo
 * @param <Cidadao>
 */
public interface CidadaoDao<Cidadao> extends BaseDao<Cidadao> {

    DaoIterator<Cidadao> listarComecandoPor(String comeco) throws DaoException;
}
