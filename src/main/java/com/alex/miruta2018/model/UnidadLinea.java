/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model;

import com.alex.miruta2018.support.ConverterTime;
import com.vividsolutions.jts.geom.LineString;
import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alextc6
 */
@Entity(name = "unidadlinea")
@Table(name = "unidadlinea")
public class UnidadLinea implements Serializable{
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    
    // anotacion propia de hibernate-spatial 5 para especificar el tipo de la columna en la DB
    @Column(name = "recorridoida", nullable = true)
    private LineString recorridoIda;
    
    @Column(name = "recorridovuelta", nullable = true)
    private LineString recorridoVuelta;
    
    @Column(name = "horainicio")
    @Convert(converter = ConverterTime.class)
    private LocalTime horaInicio;
    
    @Column(name = "horafin")
    @Convert(converter = ConverterTime.class)
    private LocalTime horaFin;
    
    @Column(name = "frecuencia")
    private int frecuencia;
    
    @Column(name = "precioboleto")
    private float precioBoleto;
    
    public UnidadLinea(){        
    }
    
    public UnidadLinea(String nombre, LocalTime horaInicio, LocalTime horaFin, int frecuenacia, float precioBoleto, Empresa empresa){
        this.nombre = nombre;
        this.empresa = empresa;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.frecuencia = frecuenacia;
        this.precioBoleto = precioBoleto;
    }
    
    public UnidadLinea(long id, String nombre, LocalTime horaInicio, LocalTime horaFin, int frecuenacia, float precioBoleto, Empresa empresa){
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.frecuencia = frecuenacia;
        this.precioBoleto = precioBoleto;
    }

    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LineString getRecorridoIda() {
        return recorridoIda;
    }

    public void setRecorridoIda(LineString recorridoIda) {
        this.recorridoIda = recorridoIda;
    }

    public LineString getRecorridoVuelta() {
        return recorridoVuelta;
    }

    public void setRecorridoVuelta(LineString recorridoVuelta) {
        this.recorridoVuelta = recorridoVuelta;
    }
            
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    @Override
    public String toString() {
        return nombre + "\n horaInicio=" + horaInicio + "\n horaFin=" + horaFin + "\n frecuencia=" + frecuencia + "\n precioBoleto=" + precioBoleto;
    }

}
