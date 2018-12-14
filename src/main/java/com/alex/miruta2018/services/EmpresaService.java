/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.services;

import com.alex.miruta2018.model.Empresa;
import com.alex.miruta2018.repo.crud.RepositorioEmpresaCrud;
import com.alex.miruta2018.repo.queries.RepositorioEmpresaJpa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alextc6
 */
@Service
public class EmpresaService {
    
    @Autowired
    private RepositorioEmpresaCrud repoEmpresa;
    @Autowired
    private RepositorioEmpresaJpa repoJpaEmpresa;
    
    public Empresa getById(long id){
        return repoEmpresa.findById(id).get();
    }
    
    public List<Empresa> getAll(){
        Iterable<Empresa> empresas = repoEmpresa.findAll();
        List<Empresa> list = new ArrayList<Empresa>();
        empresas.forEach(list::add);
        return list;
    }
    
    public Empresa create(Empresa empresa){
        List<Empresa> rdoExiste = repoJpaEmpresa.exist(empresa.getNombre(), empresa.getCuit(), empresa.getMail());
        System.out.println("EMPRESA: nombre "+empresa.getNombre()+ " - "+ " cuit: "+empresa.getCuit()+ " mail: "+empresa.getMail());
//        System.out.println("Rdo create Empresa");
//        System.out.println(rdoExiste.toString());
        if(rdoExiste.size() > 0){
            System.out.println("La empresa ya EXISTE!!");
            // trabajar en los datos que devuelve Mje de error al cliente
            return null;
        }
        return repoEmpresa.save(empresa);
    }
    
    public Empresa update(Empresa empresa){
        List<Empresa> rdoExiste = repoJpaEmpresa.exist(empresa.getNombre(), empresa.getCuit(), empresa.getMail());
        if(rdoExiste.size() > 0){
            System.out.println("La empresa ya EXISTE!!");
            // trabajar en los datos que devuelve Mje de error al cliente
            return null;
        }
        return repoEmpresa.save(empresa);
    }
    
    // ver si mandar algun mje cuando se elimina correctamente
    public void delete(Long id){
        repoEmpresa.deleteById(id);
    }
}
