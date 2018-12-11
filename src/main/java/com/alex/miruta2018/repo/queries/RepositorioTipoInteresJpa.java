/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.repo.queries;

import com.alex.miruta2018.model.TipoInteres;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author alextc6
 */
public interface RepositorioTipoInteresJpa extends JpaRepository<TipoInteres, Long>{
    
    @Query("SELECT nombre FROM tipointeres")
    List<String> findAllNames();
    
    // 
    @Query(value = "SELECT * FROM tipointeres WHERE nombre = ?1", nativeQuery = true)
    List<TipoInteres> existByName(String nombre);
    
//    @Query(value = "SELECT * FROM puntointeres WHERE tipointeres_id = ?1 ORDER BY nombre", nativeQuery = true)
//    List<PuntoInteres> findPtoInteresByType(Long tipointeres_id);
    
    TipoInteres findByNombre(String nombre);
}
