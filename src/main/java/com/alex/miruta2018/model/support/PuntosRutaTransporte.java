/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model.support;

import java.util.List;

/**
 *
 * @author alextc6
 */
public class PuntosRutaTransporte {
    
    List<String> coordIni;
    List<String> coordFin;
    String transporte;
    
    public PuntosRutaTransporte(){
    }

    public List<String> getCoordIni() {
        return coordIni;
    }

    public void setCoordIni(List<String> coordIni) {
        this.coordIni = coordIni;
    }

    public List<String> getCoordFin() {
        return coordFin;
    }

    public void setCoordFin(List<String> coordFin) {
        this.coordFin = coordFin;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }
    
}
