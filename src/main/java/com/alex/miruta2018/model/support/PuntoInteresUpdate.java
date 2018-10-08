/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model.support;

import java.io.Serializable;

/**
 *
 * @author alextc6
 */
public class PuntoInteresUpdate implements Serializable{
    
    private long id;
    private String nombre;
    private double lat;
    private double lon;
    private long idTipointeres;
    
    public PuntoInteresUpdate(){
        
    }
    
    public PuntoInteresUpdate(long id, String nombre, double  lat, double lon, long idTipo){
        this.id = id;
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.idTipointeres = idTipo;
    }

    
    public long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getIdTipointeres() {
        return idTipointeres;
    }

    public void setIdTipointeres(long idTipointeres) {
        this.idTipointeres = idTipointeres;
    }
    
}
