/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.services;

import com.alex.miruta2018.model.Empresa;
import com.alex.miruta2018.model.UnidadLinea;
import com.alex.miruta2018.model.support.RecorridoCreate;
import com.alex.miruta2018.model.support.RespRecorridoUnidadLinea;
import com.alex.miruta2018.model.support.UnidadTransporteCreate;
import com.alex.miruta2018.model.support.UnidadTransporteUpdate;
import com.alex.miruta2018.repo.crud.RepositorioEmpresaCrud;
import com.alex.miruta2018.repo.crud.RepositorioUnidadLineaCrud;
import com.alex.miruta2018.repo.queries.RepositorioUnidadLineaJpa;
import com.alex.miruta2018.services.support.RecorridoService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alextc6
 */
@Service
public class UnidadLineaService {
    
    @Autowired
    private RepositorioUnidadLineaCrud repoUniLinea;
    @Autowired
    private RepositorioUnidadLineaJpa repoUniTransporteQueries;
    @Autowired
    private RepositorioEmpresaCrud repoEmpresa;
    
    private static final GeometryFactory factoryGeom = new GeometryFactory();
    
    
    // ************************************ ABMC ************************************
    // ******************************************************************************
    
    public UnidadLinea getById(long id){
        return repoUniLinea.findById(id).get();
    }
    
    public List<UnidadLinea> getAll(){
        Iterable<UnidadLinea> unidades = repoUniLinea.findAll();
        List<UnidadLinea> list = new ArrayList<>();
        unidades.forEach(list::add);
        return list;
    }
    
    public UnidadLinea create(UnidadTransporteCreate unidad){
        Empresa emp = repoEmpresa.findById(unidad.getIdEmpresa()).get();
        UnidadLinea nuevaUnidad = new UnidadLinea(unidad.getNombre(), LocalTime.of(unidad.getHoraInicio(), unidad.getMinInicio()), LocalTime.of(unidad.getHoraFin(), unidad.getMinFin()), unidad.getFrecuencia(), unidad.getPrecioBoleto(), emp);
        System.out.println("idUniTransporte creado: "+nuevaUnidad.getId());
        return repoUniLinea.save(nuevaUnidad);
    }
    
    public UnidadLinea update(UnidadTransporteUpdate unidad){
        // asegurar desde el cliente que estos campos correspondan siempre a datos existentes
        UnidadLinea unidadDB = repoUniLinea.findById(unidad.getId()).get();
        Empresa emp = repoEmpresa.findById(unidad.getIdEmpresa()).get();
        
        unidadDB.setNombre(unidad.getNombre());
        unidadDB.setHoraInicio(LocalTime.of(unidad.getHoraInicio(), unidad.getMinInicio()));
        unidadDB.setHoraFin(LocalTime.of(unidad.getHoraFin(), unidad.getMinFin()));
        unidadDB.setFrecuencia(unidad.getFrecuencia());
        unidadDB.setPrecioBoleto(unidad.getPrecioBoleto());
        unidadDB.setEmpresa(emp);
        
        return repoUniLinea.save(unidadDB);
    }
    
    // ver si mandar algun mje cuando se elimina correctamente
    public void delete(Long id){
        repoUniLinea.deleteById(id);
    }
    
    // ******************************** Secundarias ************************************
    // *********************************************************************************
    
    public UnidadLinea updateRecorrido(RecorridoCreate recorrido) throws NoSuchElementException{
        
        System.out.println("Entro a la capa de SERVICIO");
        System.out.println(recorrido.toString());
        
        UnidadLinea unidad = repoUniLinea.findByNombre(recorrido.getNombreLinea()).get();
        System.out.println("RecorridoNuevoService ---> unidad: "+unidad.toString());
        Coordinate[] coordenadas = RecorridoService.getRecorridoPointJTS(recorrido.getPuntos_ida());
        // creamos el recorrido de ida
        LineString recorridoGeom = factoryGeom.createLineString(coordenadas);
        recorridoGeom.setSRID(4326);
//        LineString recorridoGeom = new LineString(coordenadas);
//        System.out.println("RecorridoNuevoService ---> Se creo el reco IDA");
        unidad.setRecorridoIda(recorridoGeom);
//        System.out.println("RecorridoNuevoService ---> Se seteo el reco IDA");
        System.out.println(recorridoGeom);
        
        coordenadas = RecorridoService.getRecorridoPointJTS(recorrido.getPuntos_vuelta());
        // creamos el recorrido de ida
        recorridoGeom = factoryGeom.createLineString(coordenadas);
        recorridoGeom.setSRID(4326);
//        System.out.println("RecorridoNuevoService ---> Se creo el reco VUELTA");
        System.out.println(recorridoGeom);
        unidad.setRecorridoVuelta(recorridoGeom);
//        System.out.println("RecorridoNuevoService ---> Se seteo el reco VUELTA");
        
        return repoUniLinea.save(unidad);
    }
    
    
    public List<RespRecorridoUnidadLinea> getAllRecorrido(){
        
        List<RespRecorridoUnidadLinea> recorridoUnidades = new ArrayList<>();
        
        // recuperamos todas las unidades y le sacamos los datos requeridos para enviar al cliente
        Iterable<UnidadLinea> unidades = repoUniLinea.findAll();
        List<UnidadLinea> listUnidades = new ArrayList<>();
        unidades.forEach(listUnidades::add);
        
        RespRecorridoUnidadLinea datosRecorridoAux;
        
        for (UnidadLinea unidad : listUnidades) {
            datosRecorridoAux = new RespRecorridoUnidadLinea(unidad.getNombre(), unidad.getRecorridoIda(), unidad.getRecorridoVuelta());
            recorridoUnidades.add(datosRecorridoAux);
        }
        
        System.out.println("Recorrido del servicio: ");
        System.out.println(recorridoUnidades.toArray());
        return recorridoUnidades;
    }
    
    public RespRecorridoUnidadLinea getRecorridoById(Long idUnidad){
        
        RespRecorridoUnidadLinea recorridoUnidad;
        UnidadLinea unidad = repoUniLinea.findById(idUnidad).get();
        
        recorridoUnidad = new RespRecorridoUnidadLinea(unidad.getNombre(), unidad.getRecorridoIda(), unidad.getRecorridoVuelta());
        
        return recorridoUnidad;
    }
    
    // ************************************ SOPORTE ************************************
    // *********************************************************************************
    
    public List<String> getAllNames(){
        return repoUniTransporteQueries.findAllNames();
    }
}
