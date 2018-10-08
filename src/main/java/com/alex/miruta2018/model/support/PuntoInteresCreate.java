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
public class PuntoInteresCreate implements Serializable{
    
    private String nombre;
    private double lat;
    private double lon;
    private long idUsuario;
    private long idTipo;
    
    public PuntoInteresCreate(){        
    }
    
    public PuntoInteresCreate(String nombre, double lat, double lon, long idUsuario, long idTipo){
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.idUsuario = idUsuario;
        this.idTipo = idTipo;
    }
    
    public PuntoInteresCreate(String nombre, double lat, double lon, long idTipo){
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.idTipo = idTipo;
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

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(long idTipo) {
        this.idTipo = idTipo;
    }
    
}