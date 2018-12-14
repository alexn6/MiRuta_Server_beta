/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model.support;

/**
 *
 * @author alextc6
 */
public class RespDataRute {
    
    private String calle;
    private String lat;
    private String lon;
    
    public RespDataRute(){}
    
    public RespDataRute(String calle, String lat, String lon){
        this.calle = calle;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "RespDataRute{" + "calle=" + calle + ", lat=" + lat + ", lon=" + lon + '}';
    }
    
    
}
