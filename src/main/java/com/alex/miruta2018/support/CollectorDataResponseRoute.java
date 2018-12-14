/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.support;

import com.alex.miruta2018.model.support.RespDataRute;
import com.vividsolutions.jts.geom.Coordinate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author alextc6
 */
public class CollectorDataResponseRoute {
    
    private static int CANTIDAD_INFO_LOCATION = 20;
    private static int INDEX_CALLE = 13;
    private static int INDEX_LON = 18;
    private static int INDEX_LAT = 19;
    
    public static List<RespDataRute> getDataLocations(String datosRuta){
        
        // respuesta
        List<RespDataRute> dataRuta = new ArrayList<>();
        
        List<String> listDataLocations = Arrays.asList(datosRuta.split(","));
        
        // con esto capturamos toda la info de cada location
        
        // ###################### FUNCIONA ######################
//        List<String> dataJsonLocationsResponse;
//        // separamos la info de cada location (el extremo superior no es incluido)
//        dataJsonLocationsResponse = listDataLocations.subList(0, CANTIDAD_INFO_LOCATION);
//        // creamos un nuevo objeto con los datos que necesitamos
//        RespDataRute location = getDataLocation(dataJsonLocationsResponse);
//        // despues de recuperar los valores lo agregamos al array que vamos a devolver
//        dataRuta.add(location);

        // ######################################################

        List<String> dataJsonLocationResponse;
        int i= 1;
        int j= 0;
        if(i*CANTIDAD_INFO_LOCATION <= listDataLocations.size()){
            // separamos la info de cada location
            dataJsonLocationResponse = listDataLocations.subList(j*CANTIDAD_INFO_LOCATION, i*CANTIDAD_INFO_LOCATION);
            // creamos un nuevo objeto con los datos que necesitamos
            RespDataRute location = getDataLocation(dataJsonLocationResponse);
            // despues de recuperar los valores lo agregamos al array que vamos a devolver
            dataRuta.add(location);
            i++;
            j++;
        }
        // recuperamos el ultimo objeto location
        dataJsonLocationResponse = listDataLocations.subList(j*CANTIDAD_INFO_LOCATION, listDataLocations.size());
        // creamos un nuevo objeto con los datos que necesitamos
        RespDataRute location = getDataLocation(dataJsonLocationResponse);
        // despues de recuperar los valores lo agregamos al array que vamos a devolver
        dataRuta.add(location);
            
        return dataRuta;
    }
    
    public static JSONObject getDataCoordinates(String dataJsonCoord){
        String lines[] = dataJsonCoord.split("\\r?\\n");
        
//        for (String line : lines) {
//            System.out.println(line);
//        }
        // devolvemos la linea completa de coordenadas
        String dataCoord = lines[3];
        List<String> partes = Arrays.asList(dataCoord.split(":"));
        
        String coordenadas = partes.get(1);
        String coordAjustadas = coordenadas.substring(3, coordenadas.length() - 1);
        
        // quitamos los espacios del string
        String coordSinEspacios = coordAjustadas.replaceAll("\\s+","");
        List<String> coordSeparadas = Arrays.asList(coordSinEspacios.split(","));
        
        // capturamos las coord
        List<double[]> coordinates = new ArrayList<>();
        for (int i = 0, j= 0; i < coordSeparadas.size(); i+=2) {
            double lat = Double.valueOf(coordSeparadas.get(i+1));
            double lon = Double.valueOf(coordSeparadas.get(i));
            double coord[] = { lat,lon };
            coordinates.add(coord);
        }
        
        JSONObject jsonRdo = new JSONObject();

        jsonRdo.put("coordinates", coordinates);
        return jsonRdo;
    }
    
    
    // ##############################################################################
    // ############################## metodos privados ##############################
    private static RespDataRute getDataLocation(List<String> dataJsonLocation){
        //        System.out.println("[getLocation]: lista cortada de locations recibida");
//        for(String dato : loc) {
//            System.out.println(dato);
//        }
        System.out.println("[getLocation]: vamos a separar la calle: "+dataJsonLocation.get(INDEX_CALLE));
        // recuperamos los datos
        String calleObtenida = getCalle(dataJsonLocation.get(INDEX_CALLE));
        String latObtenida = getLatitude(dataJsonLocation.get(INDEX_LAT));
        String lonObtenida = getLongitude(dataJsonLocation.get(INDEX_LON));
        
        RespDataRute location = new RespDataRute(calleObtenida, latObtenida, lonObtenida);
        
        return location;
    }
    
    private static String getCalle(String datoCalle){
        List<String> componentesCalle = Arrays.asList(datoCalle.split(":"));
        String calleAux = componentesCalle.get(1);
        String calleFinal = calleAux.substring(2, calleAux.length()-1);
        
        System.out.println("[getCalle(): calle original = ]"+calleAux);
        System.out.println("[getCalle(): calle final = ]"+calleFinal);
        
        return calleFinal;
    }
    
    private static String getLatitude(String lat){
        String latAux = lat.substring(0, lat.length() - 4);
        List<String> componentesLat = Arrays.asList(latAux.split(":"));
        String latAux1 = componentesLat.get(1);
        String latFinal = latAux1.substring(1, latAux1.length());
        
        System.out.println("[getLat(1): lat original = ]"+latAux1);
        System.out.println("[getLat(): lat final = ]"+latFinal);
        
        return latFinal;
    }
    
    private static String getLongitude(String lon){
//        String latAux = lat.substring(0, lat.length() - 4);
        List<String> componentesLon = Arrays.asList(lon.split(":"));
        String lonAux = componentesLon.get(2);
        String lonFinal = lonAux.substring(1, lonAux.length());
        
        System.out.println("[getLon(aux): lon original = ]"+lonAux);
        System.out.println("[getLon(): lon final = ]"+lonFinal);
        
        return lonFinal;
    }
    
}
