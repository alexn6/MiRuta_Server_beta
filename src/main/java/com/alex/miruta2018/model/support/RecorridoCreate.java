/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model.support;

import com.vividsolutions.jts.geom.Coordinate;
import java.util.List;

/**
 *
 * @author alextc6
 */
public class RecorridoCreate {

    private List<Coordinate> puntos_ida;
    private List<Coordinate> puntos_vuelta;
    private String nombreLinea;
    
    public RecorridoCreate(){
    }
    
    public RecorridoCreate(List<Coordinate> puntos_ida, List<Coordinate> puntos_vuelta, String nombreLinea){
        this.puntos_ida = puntos_ida;
        this.puntos_vuelta = puntos_vuelta;
        this.nombreLinea = nombreLinea;
        
    }

    public List<Coordinate> getPuntos_ida() {
        return puntos_ida;
    }

    public void setPuntos_ida(List<Coordinate> puntos_ida) {
        this.puntos_ida = puntos_ida;
    }
    
    public List<Coordinate> getPuntos_vuelta() {
        return puntos_vuelta;
    }

    public void setPuntos_vuelta(List<Coordinate> puntos_vuelta) {
        this.puntos_vuelta = puntos_vuelta;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    } 
       
}
