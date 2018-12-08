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
import com.fasterxml.jackson.core.JsonProcessingException;
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
    @Autowired
    private ConsumeServiceWeb consumerServicesWeb;
    
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
        unidad.setRecorridoIda(recorridoGeom);
        System.out.println(recorridoGeom);
        
        coordenadas = RecorridoService.getRecorridoPointJTS(recorrido.getPuntos_vuelta());
        // creamos el recorrido de ida
        recorridoGeom = factoryGeom.createLineString(coordenadas);
        recorridoGeom.setSRID(4326);
        System.out.println(recorridoGeom);
        unidad.setRecorridoVuelta(recorridoGeom);
        
        return repoUniLinea.save(unidad);
    }
    
    
    public List<RespRecorridoUnidadLinea> getAllRecorrido() throws JsonProcessingException{
        
        List<RespRecorridoUnidadLinea> recorridoUnidades = new ArrayList<>();
        
        // recuperamos todas las unidades y le sacamos los datos requeridos para enviar al cliente
        Iterable<UnidadLinea> unidades = repoUniLinea.findAll();
        List<UnidadLinea> listUnidades = new ArrayList<>();
        // buscamos el recorrido ruta de cada recorrido
        String jsonResponseRecorridoRuta;
        unidades.forEach(listUnidades::add);
        LineString recorridoIdaAux, recorridoVueltaAux;
        LineString recorridoIdaNuevo, recorridoVueltaNuevo;
        
        RespRecorridoUnidadLinea datosRecorridoAux;
        
        // vamos creando los nuevos recorrido para mandarlos
        for (UnidadLinea unidad : listUnidades) {
            recorridoIdaAux = unidad.getRecorridoIda();
            recorridoVueltaAux = unidad.getRecorridoVuelta();
            
            if(recorridoIdaAux != null){
                System.out.println("Recorrido ida recuperado DB:");
                System.out.println(recorridoIdaAux.toString());
                System.out.println("Recorrido vuelta recuperado DB:");
                System.out.println(recorridoVueltaAux.toString());
                jsonResponseRecorridoRuta = consumerServicesWeb.getRecorridoRuta(recorridoIdaAux);
                recorridoIdaNuevo = factoryGeom.createLineString(RecorridoService.getCoordenadas(jsonResponseRecorridoRuta));
                jsonResponseRecorridoRuta = consumerServicesWeb.getRecorridoRuta(recorridoVueltaAux);
                recorridoVueltaNuevo = factoryGeom.createLineString(RecorridoService.getCoordenadas(jsonResponseRecorridoRuta));
                datosRecorridoAux = new RespRecorridoUnidadLinea(unidad.getNombre(), recorridoIdaNuevo, recorridoVueltaNuevo);
                recorridoUnidades.add(datosRecorridoAux);
            }
        }
        
        System.out.println("Recorrido del servicio: ");
        System.out.println(recorridoUnidades.toArray());
        return recorridoUnidades;
    }
    
    public RespRecorridoUnidadLinea getRecorridoById(Long idUnidad) throws JsonProcessingException{
        
        RespRecorridoUnidadLinea recorridoUnidadSeleccionada = null ;
        
        // recuperamos todas las unidades y le sacamos los datos requeridos para enviar al cliente
        UnidadLinea unidadSeleccionada = repoUniLinea.findById(idUnidad).get();
        // buscamos el recorrido ruta de cada recorrido
        String jsonResponseRecorridoRuta;
        LineString recorridoIdaAux, recorridoVueltaAux;
        LineString recorridoIdaNuevo, recorridoVueltaNuevo;
        
        recorridoIdaAux = unidadSeleccionada.getRecorridoIda();
        recorridoVueltaAux = unidadSeleccionada.getRecorridoVuelta();
        
        if((recorridoIdaAux != null) && (recorridoVueltaAux != null)){
            System.out.println("Recorrido ida recuperado DB:");
            System.out.println(recorridoIdaAux.toString());
            System.out.println("Recorrido vuelta recuperado DB:");
            System.out.println(recorridoVueltaAux.toString());

            jsonResponseRecorridoRuta = consumerServicesWeb.getRecorridoRuta(recorridoIdaAux);
            recorridoIdaNuevo = factoryGeom.createLineString(RecorridoService.getCoordenadas(jsonResponseRecorridoRuta));
            jsonResponseRecorridoRuta = consumerServicesWeb.getRecorridoRuta(recorridoVueltaAux);
            recorridoVueltaNuevo = factoryGeom.createLineString(RecorridoService.getCoordenadas(jsonResponseRecorridoRuta));

            recorridoUnidadSeleccionada = new RespRecorridoUnidadLinea(unidadSeleccionada.getNombre(), recorridoIdaNuevo, recorridoVueltaNuevo);
        }
        
//        System.out.println("Recorrido del servicio: ");
//        System.out.println(recorridoUnidades.toArray());
        return recorridoUnidadSeleccionada;
    }
    
    public void deleteRecorrido(String nombreUnidad){
        System.out.println("nombre de la unidad a borrar: "+nombreUnidad);
        
//        Iterable<UnidadLinea> unidades = repoUniLinea.findAll();
//        List<UnidadLinea> listUnidades = new ArrayList<>();
//        unidades.forEach(listUnidades::add);
//        for (UnidadLinea unidad : listUnidades) {
//            System.out.println(unidad.toString());
//        }
        
        UnidadLinea unidad = repoUniLinea.findByNombre(nombreUnidad).get();
        if(unidad == null){
            System.out.println("La unidad del recorrido no existe");
        }
        
        unidad.setRecorridoIda(null);
        unidad.setRecorridoVuelta(null);
        
        repoUniLinea.save(unidad);
    }
    
    // ************************************ SOPORTE ************************************
    // *********************************************************************************
    
    public List<String> getAllNames(){
        return repoUniTransporteQueries.findAllNames();
    }
}
