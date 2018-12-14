/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.business;

import com.alex.miruta2018.model.support.PuntosRuta;
import com.alex.miruta2018.model.support.PuntosRutaTransporte;
import com.alex.miruta2018.services.ConsumeServiceWeb;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.SQLException;
import org.postgis.LineString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;

/**
 *
 * @author alextc6
 */
@RestController
@RequestMapping("/miruta/routing")
public class ApiRoutingBusiness {
    
    @Autowired
    private ConsumeServiceWeb serviceRouting;
    
    
    @RequestMapping(value = "/nearestPoint", method = GET)
    public ResponseEntity<String> getCoord(@RequestParam(value = "lon", required=false) String lon, @RequestParam(value = "lat", required=false) String lat) {
        return new ResponseEntity(serviceRouting.getCoordPuntoMasCercano(lon, lat), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/addressPoint", method = GET)
    public ResponseEntity<String> getAddressPoint(@RequestParam(value = "lon", required=false) String lon, @RequestParam(value = "lat", required=false) String lat) {
        return new ResponseEntity(serviceRouting.getDirePuntoMasCercano(lon, lat), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/addressCoord", method = GET)
    public ResponseEntity<JSONObject> getAddressPointFromCoord(@RequestParam(value = "lon", required=true) String lon, @RequestParam(value = "lat", required=true) String lat) {
        return new ResponseEntity(serviceRouting.getDireccionCoordenadas(lon, lat), HttpStatus.OK);
    }

    @RequestMapping(value = "/route", method = POST, produces = "application/json")
    public ResponseEntity<LineString> getRoute(@RequestBody PuntosRuta puntos) throws SQLException, JsonProcessingException {
        System.out.println("Entro a getRoute() ----> datos: "+puntos.toString());
        System.out.println("Coord ini: "+puntos.getCoordIni().toString()+" - Coord fin: "+puntos.getCoordFin().toString());
        return new ResponseEntity(serviceRouting.getRuta(puntos.getCoordIni(), puntos.getCoordFin()), HttpStatus.OK);
    }
    
    // devuelve el camino con el medio de transporte seleccionada
    @RequestMapping(value = "/routeTransport", method = POST, produces = "application/json")
    public ResponseEntity<JSONObject> getRouteByTransport(@RequestBody PuntosRutaTransporte datos) throws SQLException, JsonProcessingException {
        System.out.println("Entro a routetransport() ----> datos: "+datos.toString());
        System.out.println("Coord ini: "+datos.getCoordIni().toString()+" - Coord fin: "+datos.getCoordFin().toString());
        return new ResponseEntity(serviceRouting.getRutaTransporte(datos.getCoordIni(), datos.getCoordFin(), datos.getTransporte()), HttpStatus.OK);
    }
}
