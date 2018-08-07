/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alextc6
 */
@Entity(name = "empresa")
@Table(name = "empresa")
public class Empresa implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
//    @GeneratedValue(strategy=GenerationType.AUTO)
    @GeneratedValue
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "cuit")
    private String cuit;
    
    @Column(name = "mail")
    private String mail;
    
    @Column(name = "telefono")
    private String telefono;
    
    public Empresa(){
    }
    
    public Empresa(String nombre, String cuit, String mail, String telefono){
        this.nombre = nombre;
        this.cuit = cuit;
        this.mail = mail;
        this.telefono = telefono;
    }
    
    public Empresa(long id, String nombre, String cuit, String mail, String telefono){
        this.id = id;
        this.nombre = nombre;
        this.cuit = cuit;
        this.mail = mail;
        this.telefono = telefono;
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

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String toString() {
        return " Empresa "+ nombre +"\n cuit= " + cuit + "\n mail=" + mail + "\n telefono=" + telefono;
    }

}
