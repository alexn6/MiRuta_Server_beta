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
    private List<Object> route;
    @JsonProperty("info")
    private List<Object> info;
    
    public RespRutaOptimized(){
    }

    public List<Object> getRoute() {
        return route;
    }

    public void setRoute(List<Object> route) {
        this.route = route;
    }

    public List<Object> getInfo() {
        return info;
    }

    public void setInfo(List<Object> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "RespRutaTransporte{" + "routes=" + route + ", info=" + info;
    }
}
