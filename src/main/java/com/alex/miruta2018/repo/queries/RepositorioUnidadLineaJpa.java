/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.repo.queries;

import com.alex.miruta2018.model.UnidadLinea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author alextc6
 */
public interface RepositorioUnidadLineaJpa extends JpaRepository<UnidadLinea, Long>{
    
    @Query("select nombre from unidadlinea")
    List<String> findAllNames();
    
    UnidadLinea findByNombre(String nombre);
    
    // #############################################################################
    // ############################## queries postgis ##############################
//    @Query(value = "SELECT * FROM unidadlinea WHERE nombre = ?1", nativeQuery = true)
    // nativeQuery: permite hacer y usar funciones propias de la db usada
    @Query(value = "SELECT ST_AsText(recorridoida) FROM unidadlinea", nativeQuery = true)
    List<String> allRecorridoIda();
    
    @Query(value = "SELECT * FROM unidadlinea WHERE nombre = ?1", nativeQuery = true)
    List<UnidadLinea> existByName(String nombre);
    
    @Query(value = "SELECT ST_AsText(ST_ClosestPoint(line,pt))" +
                   "FROM (SELECT ST_Point(?1,?2) As pt," +
                    "	(SELECT ST_AsText(recorridoida) FROM unidadlinea where id=8) As line) As foo;", nativeQuery = true)
    String puntoMasCercanoLinea(double lon, double lat);
    
}
