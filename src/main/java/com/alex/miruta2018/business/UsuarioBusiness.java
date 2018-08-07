/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.business;

import com.alex.miruta2018.model.Usuario;
import com.alex.miruta2018.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alextc6
 */
@RestController
@RequestMapping("/miruta/users")
public class UsuarioBusiness {
    
    @Autowired
    private UsuarioService serviceUsuario;

    
    @RequestMapping(value = "/create", method = POST)
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario usuario) {
        return new ResponseEntity(serviceUsuario.create(usuario), HttpStatus.OK);
    }
    
    @RequestMapping(value = "", method = GET)
    public ResponseEntity<Usuario> userById(@RequestParam(value = "id", required=false) Long id) {
        if(id == null){
            return new ResponseEntity(serviceUsuario.getAll(), HttpStatus.OK);
        }
        return new ResponseEntity(serviceUsuario.getById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete", method = POST)
    public ResponseEntity deleteUser(@RequestParam(value = "id") long id) {
        // ver como informar q se elimino correctamente al usuario
        serviceUsuario.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<Usuario> updateUser(@RequestBody Usuario usuario) {
        return new ResponseEntity(serviceUsuario.update(usuario), HttpStatus.OK);
    }
}

