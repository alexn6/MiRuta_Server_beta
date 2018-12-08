/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.repo.queries;

import com.alex.miruta2018.model.PuntoInteres;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alextc6
 */
public interface RepositorioPtoInteresJpa extends JpaRepository<PuntoInteres, Long>{
    
    // queries como objetos(no funciona)
//    @Query("SELECT pi FROM puntointeres pi WHERE (pi.tipointeres) = (:idTipo)")
//    List<PuntoInteres> findPtoInteresByType(@Param("idTipo") Long idTipo);
    
    // queries nativas de sql    
    @Query(value = "SELECT * FROM puntointeres WHERE tipointeres_id = ?1 ORDER BY nombre", nativeQuery = true)
    List<PuntoInteres> findPtoInteresByType(Long tipointeres_id);
    
    @Query(value = "SELECT * FROM puntointeres ORDER BY nombre", nativeQuery = true)
    List<PuntoInteres> getAllByName();
    
}
