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
    
}
