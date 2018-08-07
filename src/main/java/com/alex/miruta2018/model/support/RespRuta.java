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
public class RespRuta {
    
    @JsonProperty("routes")
    private List<Object> routes;
    @JsonProperty("waypoints")
    private List<Object> waypoints;
    @JsonProperty("code")
    private String code;
    
    public RespRuta(){
    }

    public List<Object> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Object> routes) {
        this.routes = routes;
    }

    public List<Object> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Object> waypoints) {
        this.waypoints = waypoints;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RespRuta{" + "routes=" + routes + ", waypoints=" + waypoints + ", code=" + code + '}';
    }
}
