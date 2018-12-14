/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.repo.queries;

import com.alex.miruta2018.model.Empresa;
import com.alex.miruta2018.model.UnidadLinea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author alextc6
 */
public interface RepositorioEmpresaJpa extends JpaRepository<Empresa, Long>{
    
    @Query("SELECT nombre FROM empresa")
    List<String> findAllNames();
    
    @Query(value = "SELECT * FROM empresa WHERE nombre = ?1", nativeQuery = true)
    List<Empresa> existByName(String nombre);
    
    @Query(value = "SELECT * FROM empresa WHERE nombre = ?1 OR cuit = ?2 OR mail = ?3", nativeQuery = true)
    List<Empresa> exist(String nombre, String cuit, String mail);
}
