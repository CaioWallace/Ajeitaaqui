/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MPS.dao;

/**
 * Padrão Command para executar operações SQL.
 * 
 * @author Edvaldo
 */
public interface DaoCommand {
 
    void execute() throws DaoException;
}
