/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.services.support;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alextc6
 */
public class RecorridoService {
    
    private static GeometryFactory factoryGeom = new GeometryFactory();
    
    
    // se encarga de crear los tipos de puntos especificados por el cliente y en base a eso devolver la lista de puntos correspondientes
    public static Coordinate[] getRecorridoPointJTS(List<Coordinate> puntosCrudos){
        
        List<Coordinate> coordinates = new ArrayList<>();
        
        Coordinate coordTemp;
        
        for (Coordinate puntoCrudo : puntosCrudos) {
            coordTemp = new Coordinate(puntoCrudo.x, puntoCrudo.y);
            coordinates.add(coordTemp);
        }
        
        // pasamos la lista a un array
        Coordinate[] coordArray = new Coordinate[coordinates.size()];
        coordArray = coordinates.toArray(coordArray);

        return coordArray;
    }
    
}
