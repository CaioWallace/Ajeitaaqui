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
    
    T get(int id);
    
    void save(T t);
    
    void update(T t);
}
