/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model.support;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.LineString;

/**
 *
 * @author alextc6
 */
public class RespRecorridoUnidadLinea {
    
    @JsonProperty("nombre_unidad")
    private String nombreUnidad;
    @JsonProperty("recorrido_ida")
    private LineString recorrido_ida;
    @JsonProperty("recorrido_vuelta")
    private LineString recorrido_vuelta;
    
    public RespRecorridoUnidadLinea(){
        
    }
    
    public RespRecorridoUnidadLinea(String nombreUnidad, LineString reco_ida, LineString reco_vuelta){
        this.nombreUnidad = nombreUnidad;
        this.recorrido_ida = reco_ida;
        this.recorrido_vuelta = reco_vuelta;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public LineString getRecorrido_ida() {
        return recorrido_ida;
    }

    public void setRecorrido_ida(LineString recorrido_ida) {
        this.recorrido_ida = recorrido_ida;
    }

    public LineString getRecorrido_vuelta() {
        return recorrido_vuelta;
    }

    public void setRecorrido_vuelta(LineString recorrido_vuelta) {
        this.recorrido_vuelta = recorrido_vuelta;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
        
}
