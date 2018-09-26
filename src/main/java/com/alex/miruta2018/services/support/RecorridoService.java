/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.services.support;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
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
    
    // recibe un geom como string y devuelve el array decoordenadas correspondiente
    public static Coordinate[] getCoordenadas(String geometry){
        
        Coordinate[] coordenadas;
        
        String[] coordString = filtrarCoordenadas(geometry);
        
        coordenadas = stringToCoordinates(coordString);
        
//        System.out.println("String coord filtrada: "+coordenadasString);
        
//        String[] partesCoord = coordenadasString.split(",");
//        
//        for (int i = 0; i < partesCoord.length ; i++) {
//            System.out.println("Elem n° "+i+ ": "+partesCoord[i]);
//        }
        
        return coordenadas;
    }
    
    private static Coordinate[] stringToCoordinates(String[] coordenadas){
        List<Coordinate> coordenadasList = new ArrayList<>();
        Coordinate[] arrayCoordenadas = new Coordinate[]{};
        Coordinate coordenadaGenerada;
        String latCoord, lonCoord;
        
        
        // recorremos el array generando las coordenadas
        for (int i = 0; i < (coordenadas.length - 1); i+=2) {
            latCoord = coordenadas[i];
            lonCoord = coordenadas[i+1];
            coordenadaGenerada = new Coordinate(Double.valueOf(latCoord), Double.valueOf(lonCoord));
            coordenadasList.add(coordenadaGenerada);
        }
        
        return coordenadasList.toArray(arrayCoordenadas);
    }
    
    private static String[] filtrarCoordenadas(String geomString){
        
        // el 18 serian los caracteres q sobran del string recibido
        int finCoordenadas = geomString.length() - 26;
        int iniCoordenadas = 20;
        // recortamos el string de ambos lados
        String coordenadasString = geomString.substring(iniCoordenadas, finCoordenadas);
        
        String coordFiltrado = coordenadasString.replace(" ","");
        coordFiltrado = coordFiltrado.replace("[","");
        coordFiltrado = coordFiltrado.replace("]","");
        
        String[] partesCoord = coordFiltrado.split(",");
//        
//        for (int i = 0; i < partesCoord.length ; i++) {
//            System.out.println("Elem n° "+i+ ": "+partesCoord[i]);
//        }
        
        return partesCoord;
    }
}
