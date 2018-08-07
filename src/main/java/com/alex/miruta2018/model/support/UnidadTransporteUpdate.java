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
public class UnidadTransporteUpdate implements Serializable{
    
    private long id;
    private long idEmpresa;
    private String nombre;
    private int horaInicio;
    private int horaFin;
    private int minInicio;
    private int minFin;
    private int frecuencia;
    private float precioBoleto;
    
    public UnidadTransporteUpdate(){        
    }
    
    public UnidadTransporteUpdate(long id, String nombre, int horaInicio, int minInicio, int horaFin, int minFin, int frecuenacia, float precioBoleto, long idEmpresa){
        this.id = id;
        this.nombre = nombre;
        this.idEmpresa = idEmpresa;
        this.horaInicio = horaInicio;
        this.minInicio = minInicio;
        this.horaFin = horaFin;
        this.minFin = minFin;
        this.frecuencia = frecuenacia;
        this.precioBoleto = precioBoleto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public int getMinInicio() {
        return minInicio;
    }

    public void setMinInicio(int minInicio) {
        this.minInicio = minInicio;
    }

    public int getMinFin() {
        return minFin;
    }

    public void setMinFin(int minFin) {
        this.minFin = minFin;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public float getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(float precioBoleto) {
        this.precioBoleto = precioBoleto;
    }
    
    
}
