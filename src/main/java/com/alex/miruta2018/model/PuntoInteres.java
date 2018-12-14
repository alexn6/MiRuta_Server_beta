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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


/**
 *
 * @author alextc6
 */
@Entity(name = "puntointeres")
@Table(name = "puntointeres")
public class PuntoInteres implements Serializable{
    
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipointeres_id", nullable = false)
    private TipoInteres tipointeres;
    
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
    @JoinColumn(name="Usuario_ID", nullable = true)
    @JsonBackReference
    private Usuario usuario;
    
    public PuntoInteres(){        
    }
    
    public PuntoInteres(String nombre, Point coordenada, Usuario usuario, TipoInteres tipo){
        this.nombre = nombre;
        this.coordenada = coordenada;
        this.usuario = usuario;
        this.tipointeres = tipo;
    }
    
    public PuntoInteres(String nombre, Point coordenada, TipoInteres tipo){
        this.nombre = nombre;
        this.coordenada = coordenada;
        this.tipointeres = tipo;
    }
    
    public PuntoInteres(long id, String nombre, Point coordenada, Usuario usuario, TipoInteres tipo){
        this.id = id;
        this.nombre = nombre;
        this.coordenada = coordenada;
        this.usuario = usuario;
        this.tipointeres = tipo;
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

    public TipoInteres getTipointeres() {
        return tipointeres;
    }

    public void setTipointeres(TipoInteres tipointeres) {
        this.tipointeres = tipointeres;
    }
    
    @Override
    public String toString() {
        return this.coordenada.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
