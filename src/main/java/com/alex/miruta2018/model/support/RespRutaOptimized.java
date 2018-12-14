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
public class RespRutaOptimized {
    
    @JsonProperty("route")
    private Object route;
    @JsonProperty("options")
    private Object options;
    @JsonProperty("results")
    private List<Object> results;
    
    public RespRutaOptimized(){
    }

    public Object getRoute() {
        return route;
    }

    public void setRoute(Object route) {
        this.route = route;
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
        return "RespRutaTransporte{" + "routes=" + route + ", options=" + options;
    }
}
