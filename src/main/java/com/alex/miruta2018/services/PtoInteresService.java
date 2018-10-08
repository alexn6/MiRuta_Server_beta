/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.services;

import com.alex.miruta2018.model.PuntoInteres;
import com.alex.miruta2018.model.TipoInteres;
import com.alex.miruta2018.model.Usuario;
import com.alex.miruta2018.model.support.PuntoInteresCreate;
import com.alex.miruta2018.model.support.PuntoInteresUpdate;
import com.alex.miruta2018.repo.crud.RepositorioPtoInteresCrud;
import com.alex.miruta2018.repo.crud.RepositorioTipoInteresCrud;
import com.alex.miruta2018.repo.crud.RepositorioUsuarioCrud;
import com.alex.miruta2018.repo.queries.RepositorioPtoInteresJpa;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alextc6
 */
@Service
public class PtoInteresService {
    
    private final GeometryFactory fabricaGeom = new GeometryFactory();
    
    @Autowired
    private RepositorioPtoInteresCrud repoCrudPtoInteres;
    @Autowired
    private RepositorioPtoInteresJpa repoJpaPtoInteres;
    @Autowired
    private RepositorioUsuarioCrud repoCrudUsuario;
    @Autowired
    private RepositorioTipoInteresCrud repoCrudTipoInteres;
    
    public PuntoInteres getById(Long id){
        return repoCrudPtoInteres.findById(id).get();
    }
    
    public List<PuntoInteres> getAll(){
        Iterable<PuntoInteres> puntos = repoCrudPtoInteres.findAll();
        List<PuntoInteres> list = new ArrayList<PuntoInteres>();
        puntos.forEach(list::add);
        return list;
    }
    
    public PuntoInteres create(PuntoInteresCreate punto){
        Usuario usuario;
        PuntoInteres nuevoPunto;
        TipoInteres tipo_interes;
        
        tipo_interes = repoCrudTipoInteres.findById(punto.getIdTipo()).get();
        
        if(!repoCrudUsuario.existsById(punto.getIdUsuario())){
            System.err.println("El usuario ingresado no se encuentra en la DB.!!!!!");
//            tipo_interes = repoCrudTipoInteres.findById(punto.getIdTipo()).get();
            nuevoPunto = new PuntoInteres(punto.getNombre(), fabricaGeom.createPoint(new Coordinate(punto.getLat(), punto.getLon())), tipo_interes);
        }
        else{
            usuario = repoCrudUsuario.findById(punto.getIdUsuario()).get();
//            tipo_interes = repoCrudTipoInteres.findById(punto.getIdTipo()).get();
            nuevoPunto = new PuntoInteres(punto.getNombre(), fabricaGeom.createPoint(new Coordinate(punto.getLat(), punto.getLon())), usuario, tipo_interes);
        }
        return repoCrudPtoInteres.save(nuevoPunto);
    }
    
    // se actualizan solo los datos simples del punto, el id y el usuario no se cambian
    public PuntoInteres update(PuntoInteresUpdate punto){

        PuntoInteres puntoDB = repoCrudPtoInteres.findById(punto.getId()).get();

        // seteamos estos campos ya q los demas nunk se modifican
        puntoDB.setNombre(punto.getNombre());
        puntoDB.setCoordenada(fabricaGeom.createPoint(new Coordinate(punto.getLat(), punto.getLon())));
        puntoDB.setTipointeres(repoCrudTipoInteres.findById(punto.getIdTipointeres()).get());
        
        return repoCrudPtoInteres.save(puntoDB);
    }
    
    // ver si mandar algun mje cuando se elimina correctamente
    public void delete(Long id){
        repoCrudPtoInteres.deleteById(id);
    }
    
    // *****************************************************************************
    // ******************************** SOPORTE ************************************
    
    public List<PuntoInteres> getByType(Long tipo){
        
        Iterable<PuntoInteres> puntosTipo = repoJpaPtoInteres.findPtoInteresByType(tipo);
        List<PuntoInteres> listPtos = new ArrayList<PuntoInteres>();
        puntosTipo.forEach(listPtos::add);
        
        return listPtos;
    }
    
    public List<PuntoInteres> getByNameType(String tipo){
        
        TipoInteres tipoInteres = repoCrudTipoInteres.findByNombre(tipo).get();
        
        Iterable<PuntoInteres> puntosTipo = repoJpaPtoInteres.findPtoInteresByType(tipoInteres.getId());
        List<PuntoInteres> listPtos = new ArrayList<PuntoInteres>();
        puntosTipo.forEach(listPtos::add);
        
        return listPtos;
    }
    
//    public List<PuntoInteres> getByTipo(Long tipo){
//        Iterable<PuntoInteres> puntosTipo = repoJpaPtoInteres.findPtoInteresByType(tipo);
//        List<PuntoInteres> listPtos = new ArrayList<PuntoInteres>();
//        puntosTipo.forEach(listPtos::add);
//        
//        return listPtos;
//    }
    
}
