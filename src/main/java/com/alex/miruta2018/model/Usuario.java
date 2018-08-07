/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alextc6
 */
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario implements Serializable{
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonManagedReference
    private List<PuntoInteres> ptosInteres;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "pass")
    private String pass;
    
    @Column(name = "mail")
    private String mail;
    
    public Usuario(){
        
    }
    
    public Usuario(String nombre, String pass, String mail){
        this.nombre = nombre;
        this.pass = pass;
        this.mail = mail;
        this.ptosInteres = new ArrayList<>();
    }
    
    public Usuario(long id, String nombre, String pass, String mail){
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
        this.mail = mail;
        this.ptosInteres = new ArrayList<>();
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<PuntoInteres> getPtosInteres() {
        return ptosInteres;
    }

    public void setPtosInteres(List<PuntoInteres> ptosInteres) {
        this.ptosInteres = ptosInteres;
    }
    
    @Override
    public String toString() {
        return "Usuario " + nombre + ", mail=" + mail;
    }

}
