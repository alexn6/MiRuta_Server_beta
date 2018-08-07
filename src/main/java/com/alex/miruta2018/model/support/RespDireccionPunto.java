/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.model.support;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author alextc6
 */
public class RespDireccionPunto {
    
    @JsonProperty("results")
    private List<Object> results;
    @JsonProperty("status")
    private String status;
    
    public RespDireccionPunto(){
    }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "RespDireccion{" + "direcciones=" + results + ", estado=" + status + '}';
    }
}
