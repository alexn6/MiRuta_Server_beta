/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vividsolutions.jts.geom.Point;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 *
 * @author alextc6
 */
@Entity(name = "puntointeres")
@Table(name = "puntointeres")
public class PuntoInteres implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "coordenada", nullable = false)
    private Point coordenada;
    
    @ManyToOne
    @JoinColumn(name="Usuario_ID", nullable = false)
    @JsonBackReference
    private Usuario usuario;
    
    public PuntoInteres(){        
    }
    
    public PuntoInteres(String nombre, Point coordenada, Usuario usuario){
        this.nombre = nombre;
        this.coordenada = coordenada;
        this.usuario = usuario;
    }
    
    public PuntoInteres(long id, String nombre, Point coordenada, Usuario usuario){
        this.id = id;
        this.nombre = nombre;
        this.coordenada = coordenada;
        this.usuario = usuario;
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

    public Point getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Point coordenada) {
        this.coordenada = coordenada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString() {
        return this.coordenada.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
