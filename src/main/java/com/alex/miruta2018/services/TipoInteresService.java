/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.services;

import com.alex.miruta2018.model.TipoInteres;
import com.alex.miruta2018.repo.crud.RepositorioTipoInteresCrud;
import com.alex.miruta2018.repo.queries.RepositorioTipoInteresJpa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alextc6
 */
@Service
public class TipoInteresService {
    
    @Autowired
    private RepositorioTipoInteresCrud repoTipoInteres;
    @Autowired
    private RepositorioTipoInteresJpa repoJpaTipoInteres;
    
    public TipoInteres getById(long id){
        return repoTipoInteres.findById(id).get();
    }
    
    public List<TipoInteres> getAll(){
        Iterable<TipoInteres> empresas = repoTipoInteres.findAll();
        List<TipoInteres> list = new ArrayList<TipoInteres>();
        empresas.forEach(list::add);
        return list;
    }
    
    public TipoInteres create(TipoInteres tipo){
        return repoTipoInteres.save(tipo);
    }
    
    public TipoInteres update(TipoInteres tipo){
        return repoTipoInteres.save(tipo);
    }
    
    // ver si mandar algun mje cuando se elimina correctamente
    public void delete(Long id){
        repoTipoInteres.deleteById(id);
    }
    
    // ************************************ SOPORTE ************************************
    // *********************************************************************************
    
    public List<String> getAllNames(){
        return repoJpaTipoInteres.findAllNames();
    }
}
