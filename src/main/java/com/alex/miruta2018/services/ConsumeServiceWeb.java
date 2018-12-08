/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.services;

import com.alex.miruta2018.constants.MAPQUEST;
import com.alex.miruta2018.constants.OSRM;
import com.alex.miruta2018.model.support.Location;
import com.alex.miruta2018.model.support.RespDireFromCoord;
import com.alex.miruta2018.model.support.RespDireccionPunto;
import com.alex.miruta2018.model.support.RespPuntoMasCercano;
import com.alex.miruta2018.model.support.RespRuta;
import com.alex.miruta2018.model.support.RespRutaOptimized;
import com.alex.miruta2018.support.ConvertResponseWebSrevice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.simple.JSONObject;
import reactor.core.publisher.Mono;

/**
 *
 * @author alextc6
 */
@Service
public class ConsumeServiceWeb {
    
    private final WebClient webClientRouting;
    private final WebClient webClientDireccion;
    private final WebClient webClientMapquest;
    private final static String GOOGLE_KEY = "AIzaSyDti7qoPD_lFYuAGLk7EuXYcJr913cbkW8";
    private final static String MAPQUEST_KEY = "5vdimmIWvyFNFS2Ykt6vMJJMquhXNny1";
    private ObjectMapper mapeador = new ObjectMapper();
    private JSONObject adminJson = new JSONObject();

    public ConsumeServiceWeb(WebClient.Builder webClientBuilder) {
        this.webClientRouting = webClientBuilder.baseUrl("http://router.project-osrm.org/").build();
        this.webClientMapquest = webClientBuilder.baseUrl("http://www.mapquestapi.com/").build();
        this.webClientDireccion = webClientBuilder.baseUrl("https://maps.googleapis.com/maps/api/geocode/").build();
    }
    
    
    // dado una coordenada devuelve el punto(coordenadas) mas cercano en la calle y su direccion
    // devuelve un array de string
    public List<String> getCoordPuntoMasCercano(String lon, String lat){
        
        // se arma la url para realizar la peticion con los datos recibidos
        String uriService = OSRM.URL_PUNTO_MAS_CERCANO +lon+","+lat;
        
        Mono<RespPuntoMasCercano> result = webClientRouting.get().uri(uriService)
                                     .retrieve()
                                     .bodyToMono(RespPuntoMasCercano.class);
        RespPuntoMasCercano response = result.block();

        System.out.println("Resp del servicio: "+response.toString());
        
        // hacemos un casteo a la clase de mapeo para poder acceder a los atributos recibidos
        LinkedHashMap wayp = (LinkedHashMap)response.getWaypoints().get(0);
        String ubicacion = wayp.get("location").toString();

        List<String> ubicacion_final = ConvertResponseWebSrevice.stringToCoordenate(ubicacion);
            
        System.out.println(ubicacion_final);
        
        return ubicacion_final;
    }
    
    // dado una coordenada devuelve el punto(coordenadas) mas cercano en la calle y su direccion
    // devuelve un array de string
    public List<String> getDirePuntoMasCercano(String lon, String lat){
                
        // se arma la url para realizar la peticion con los datos recibidos
        String uriService = "json?latlng=" +lat+ "," +lon+ "&key=" + GOOGLE_KEY;
        
        Mono<RespDireccionPunto> respServicio = webClientDireccion.get().uri(uriService)
                                     .retrieve()
                                     .bodyToMono(RespDireccionPunto.class);
        RespDireccionPunto datosServicio = respServicio.block();

        // se debe hacer algo similar para la direccion 
        LinkedHashMap direccionMapping = (LinkedHashMap)datosServicio.getResults().get(0);
        String direccion = direccionMapping.get("formatted_address").toString();

        System.out.println("Resultado direccion: "+direccion);

        List<String> arrayDireccion = Arrays.asList(direccion.split(","));
        
        return arrayDireccion;
    }
    
    // dado una coordenada devuelve el punto(coordenadas) mas cercano en la calle y su direccion
    // devuelve un array de string
    public JSONObject getDireccionCoordenadas(String lon, String lat){
                
        // se arma la url para realizar la peticion con los datos recibidos
        String uriService = MAPQUEST.ADRESS_FROM_COORD + "?key=" +MAPQUEST_KEY+ "&location="
                +lat+ "," +lon+ "&includeRoadMetadata=false&includeNearestIntersection=false";
        
        System.out.println("Url service Mapquest geocoding: " +uriService);
        
        Mono<RespDireFromCoord> respServicio = webClientMapquest.get().uri(uriService)
                                     .retrieve()
                                     .bodyToMono(RespDireFromCoord.class);
        RespDireFromCoord datosServicio = respServicio.block();

        // se debe hacer algo similar para la direccion 
        LinkedHashMap direccionMapping = (LinkedHashMap)datosServicio.getResults().get(0);
        String direccion = direccionMapping.get("locations").toString();
        direccion = direccion.substring(2, direccion.length() - 2);

        System.out.println("Resultado direccion: "+direccion);

        List<String> arrayDireccion = Arrays.asList(direccion.split(","));
        
        // tomamos solo la info que necesitams
        String datosCalle = arrayDireccion.get(0);
        List<String> arrayCalle = Arrays.asList(datosCalle.split("="));
        String datosCiudad = arrayDireccion.get(3);
        List<String> arrayCiudad = Arrays.asList(datosCiudad.split("="));
        String datosProvincia = arrayDireccion.get(7);
        List<String> arrayProvincia = Arrays.asList(datosProvincia.split("="));
        String datosPais = arrayDireccion.get(9);
        List<String> arrayPais = Arrays.asList(datosPais.split("="));
        
        // creamos el object json
        JSONObject jsonDireccion = new JSONObject();

        jsonDireccion.put("calle", arrayCalle.get(1));
        jsonDireccion.put("ciudad", arrayCiudad.get(1));
        jsonDireccion.put("provincia", arrayProvincia.get(1));
        jsonDireccion.put("pais", arrayPais.get(1));
        
        return jsonDireccion;
    }

    public String getRuta(List<String> puntoIni, List<String> puntoFin) throws SQLException, JsonProcessingException{
        
        String urlService = OSRM.URL_RUTA + puntoIni.get(0) + "," + puntoIni.get(1) +
                ";" + puntoFin.get(0) + "," + puntoFin.get(1)+"?"+ OSRM.TIPO_DATO_RUTA_RECIBIDO;
        
        System.out.println("Url service RUTA: " +urlService);
        
        Mono<RespRuta> result = webClientRouting.get().uri(urlService)
                                     .retrieve()
                                     .bodyToMono(RespRuta.class);
        RespRuta response = result.block();
        
        // obtenemos la 1ra ruta de la resp del servicio web
        LinkedHashMap routes = (LinkedHashMap)response.getRoutes().get(0);

        System.out.println("Coord de la ruta: "+routes.get("geometry"));
        
        // pasamos el dato a json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonResponse = ow.writeValueAsString(routes.get("geometry"));
        
        // ############################# PRUEBA ####################################
//        System.out.println("JsonResponse: "+jsonResponse);
//        System.out.println("Tamaño JsonResponse: "+jsonResponse.length());
//        // el 18 serian los caracteres q sobran del string recibido
//        int finCoordenadas = jsonResponse.length() -26;
//        int iniCoordenadas = 20;
//        // recortamos el string de ambos lados
//        String coordenadasString = jsonResponse.substring(iniCoordenadas, finCoordenadas);
//        System.out.println("String coord: "+coordenadasString);
        //##########################################################################
        
        return jsonResponse;
        
    }
    
    public String getRutaTransporte(List<String> puntoIni, List<String> puntoFin, String transport) throws SQLException, JsonProcessingException{
        
//        String urlService = OSRM.URL +transport+ "/"+ puntoIni.get(0) + "," + puntoIni.get(1) +
//                ";" + puntoFin.get(0) + "," + puntoFin.get(1)+"?"+ OSRM.PARAM_STEP + "&" +OSRM.TIPO_DATO_RUTA_RECIBIDO;
//        String urlService = MAPQUEST.RUTA_OPTIMIZADA + "?key=" +MAPQUEST_KEY+ "&json={\"locations\":[\""
//                +puntoIni.get(1)+ "," +puntoIni.get(0)+ "\",\"" +puntoFin.get(1)+ "," +puntoFin.get(0)+ "\"]}";
        String urlService = MAPQUEST.RUTA + "?key=" +MAPQUEST_KEY+ "&from="
                +puntoIni.get(1)+ "," +puntoIni.get(0)+ "&to=" +puntoFin.get(1)+ "," +puntoFin.get(0);
        
        System.out.println("Url service RUTA TRANSPORTE: " +urlService);
        
        Mono<RespRutaOptimized> result = webClientMapquest.get().uri(urlService)
                                     .retrieve()
                                     .bodyToMono(RespRutaOptimized.class);
        RespRutaOptimized response = result.block();
        
        // obtenemos la 1ra ruta de la resp del servicio web
        LinkedHashMap datosRuta = (LinkedHashMap)response.getRoute();

        System.out.println("Locations TODO de la ruta: "+datosRuta);
        System.out.println("Locations distance de la ruta: "+datosRuta.get("distance"));
//        System.out.println("Locations de la ruta: "+datosRuta.get("locations"));
        
        // pasamos el dato a json
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String jsonResponse = ow.writeValueAsString(datosRuta.get("locations"));
        
        // ############################# PRUEBA ####################################
//        System.out.println("JsonResponse: "+jsonResponse);
//        System.out.println("Tamaño JsonResponse: "+jsonResponse.length());
        //##########################################################################
        
        String jsonResponse = "datojson";
        
        return jsonResponse;
        
    }
    
    public String getRutaTransporteOsrm(List<String> puntoIni, List<String> puntoFin, String transporte) throws SQLException, JsonProcessingException{
        
//        String urlService = OSRM.URL_RUTA + puntoIni.get(0) + "," + puntoIni.get(1) +
//                ";" + puntoFin.get(0) + "," + puntoFin.get(1)+"?"+ OSRM.TIPO_DATO_RUTA_RECIBIDO;
        String urlService = OSRM.URL +transporte+ "/"+ puntoIni.get(0) + "," + puntoIni.get(1) +
                ";" + puntoFin.get(0) + "," + puntoFin.get(1)+"?"+ OSRM.PARAM_STEP + "&" +OSRM.TIPO_DATO_RUTA_RECIBIDO;
        
        System.out.println("Url service RUTA: " +urlService);
        
        Mono<RespRuta> result = webClientRouting.get().uri(urlService)
                                     .retrieve()
                                     .bodyToMono(RespRuta.class);
        RespRuta response = result.block();
        
        // obtenemos la 1ra ruta de la resp del servicio web
        LinkedHashMap routes = (LinkedHashMap)response.getRoutes().get(0);

        System.out.println("Coord de la ruta: "+routes.get("geometry"));
        
        // pasamos el dato a json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonResponse = ow.writeValueAsString(routes.get("geometry"));
        
        return jsonResponse;
        
    }
    
    public String getRecorridoRuta(LineString recorrido) throws JsonProcessingException{
        
        String setCoordenadas = getStringCoordenadas(recorrido);
        
        String urlService = OSRM.URL_RUTA + setCoordenadas + OSRM.TIPO_DATO_RUTA_RECIBIDO;
        
        System.out.println("Url service RECORRIDO RUTA: " +urlService);
        
        Mono<RespRuta> result = webClientRouting.get().uri(urlService)
                                     .retrieve()
                                     .bodyToMono(RespRuta.class);
        RespRuta response = result.block();
        
        // obtenemos la 1ra ruta de la resp del servicio web
        LinkedHashMap routes = (LinkedHashMap)response.getRoutes().get(0);

        System.out.println("RUTA RECORRIDO: "+routes.get("geometry"));
        
        // pasamos el dato a json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonResponse = ow.writeValueAsString(routes.get("geometry"));
        
        return jsonResponse;
    }
    
    // ####################################################################
    // ######################### SOPORTE ##################################
    
    private String getStringCoordenadas(LineString ruta){
        
        String coordenadas = "";
        Coordinate[] coordRuta = ruta.getCoordinates();
        Coordinate coordAux;
        
        
        for (int i = 0; i < (coordRuta.length - 1); i++) {
            coordAux = coordRuta[i];
            coordenadas += coordAux.getOrdinate(0) + "," + coordAux.getOrdinate(1) +";";
        }
        
        // agregamos la ultima coordenada
        coordAux = coordRuta[coordRuta.length - 1];
        coordenadas += coordAux.getOrdinate(0) + "," + coordAux.getOrdinate(1) +"?";
        
        return coordenadas;
    }
}
