/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.repo.queries;

import com.alex.miruta2018.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author alextc6
 */
public interface RepositorioPostgisJpa extends JpaRepository<Empresa, Long>{
    
    @Query("SELECT nombre FROM empresa")
    List<String> findAllNames();
}
