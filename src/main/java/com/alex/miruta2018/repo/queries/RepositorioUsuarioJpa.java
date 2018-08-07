/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.repo.queries;

import com.alex.miruta2018.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alextc6
 */
public interface RepositorioUsuarioJpa extends JpaRepository<Usuario, Long>{
    
    // al colocar el byNombre se debe colocar el nombre de la propiedad(Nombre en este caso)
    // tal cual como especificado en la entidad
    Usuario findByNombre(String nombre);
    
}
