/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao;

/**
 *
 * @author Edvaldo
 * @param <T>
 */
public interface BaseDao<T> {
    
    T get(int id) throws DaoException;
    
    void save(T t) throws DaoException;
    
    void update(T t) throws DaoException;
    
    void delete(int id) throws DaoException;
}
