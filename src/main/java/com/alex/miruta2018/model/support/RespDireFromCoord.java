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
public class RespDireFromCoord {
    
    @JsonProperty("info")
    private Object info;
    @JsonProperty("options")
    private Object options;
    @JsonProperty("results")
    private List<Object> results;
    
    public RespDireFromCoord(){
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Object getOptions() {
        return options;
    }

    public void setOptions(Object options) {
        this.options = options;
    }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }
    
    
    @Override
    public String toString() {
        return "RespDireccion{direccion=" + results + " }";
    }
}
