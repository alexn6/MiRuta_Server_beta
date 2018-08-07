/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.services;

import com.alex.miruta2018.constants.OSRM;
import com.alex.miruta2018.model.support.RespDireccionPunto;
import com.alex.miruta2018.model.support.RespPuntoMasCercano;
import com.alex.miruta2018.model.support.RespRuta;
import com.alex.miruta2018.support.ConvertResponseWebSrevice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 *
 * @author alextc6
 */
@Service
public class ConsumeServiceWeb {
    
    private final WebClient webClientRouting;
    private final WebClient webClientDireccion;
    private final static String GOOGLE_KEY = "AIzaSyDti7qoPD_lFYuAGLk7EuXYcJr913cbkW8";

    public ConsumeServiceWeb(WebClient.Builder webClientBuilder) {
        this.webClientRouting = webClientBuilder.baseUrl("http://router.project-osrm.org/").build();
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

    public String getRuta(List<String> puntoIni, List<String> puntoFin) throws SQLException, JsonProcessingException{
        
        String urlService = OSRM.URL_RUTA + puntoIni.get(0) + "," + puntoIni.get(1) +
                ";" + puntoFin.get(0) + "," + puntoFin.get(1)+"?"+ OSRM.TIPO_DATO_RUTA_RECIBIDO;
        
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
}
