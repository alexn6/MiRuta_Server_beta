/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.business;

import com.alex.miruta2018.model.TipoInteres;
import com.alex.miruta2018.services.TipoInteresService;
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
@RequestMapping("/miruta/tipoInteres")
public class TipoPuntoInteresBusiness {
    
    @Autowired
    private TipoInteresService serviceTipoPtoInteres;
    
    @RequestMapping(value = "/create", method = POST)
    public ResponseEntity<TipoInteres> saveTipoInteres(@RequestBody TipoInteres tipo) {
        TipoInteres rdoTipoInteres = serviceTipoPtoInteres.create(tipo);
        if(rdoTipoInteres == null){
            return new ResponseEntity("El tipo ya existe", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(rdoTipoInteres, HttpStatus.OK);
    }
    
    @RequestMapping(value = "", method = GET)
    public ResponseEntity<TipoInteres> tipoInteresById(@RequestParam(value = "id", required=false) Long id) {
        if(id == null){
            return new ResponseEntity(serviceTipoPtoInteres.getAll(), HttpStatus.OK);
        }
        return new ResponseEntity(serviceTipoPtoInteres.getById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete", method = POST)
    public ResponseEntity deleteTipoInteres(@RequestParam(value = "id") long id) {
        // ver como informar q se elimino correctamente al usuario
        serviceTipoPtoInteres.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<TipoInteres> updateTipoInteres(@RequestBody TipoInteres tipo) {
        TipoInteres rdoTipoInteres = serviceTipoPtoInteres.update(tipo);
        if(rdoTipoInteres == null){
            return new ResponseEntity("El tipo ya existe", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(rdoTipoInteres, HttpStatus.OK);
    }
    
    // ************************************ SOPORTE ************************************
    // *********************************************************************************
    
    @RequestMapping(value = "/names", method = GET)
    public ResponseEntity<String> nombresTipoInteres() {
        return new ResponseEntity(serviceTipoPtoInteres.getAllNames(), HttpStatus.OK);
    }
}
