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
        List<TipoInteres> rdoExiste = repoJpaTipoInteres.existByName(tipo.getNombre());
        System.out.println("Rdo create TipoInteres");
        System.out.println(rdoExiste.toString());
        if(rdoExiste.size() > 0){
            System.out.println("El tipo de punto EXISTE!!");
            // trabajar en los datos que devuelve Mje de error al cliente
            return null;
        }
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
