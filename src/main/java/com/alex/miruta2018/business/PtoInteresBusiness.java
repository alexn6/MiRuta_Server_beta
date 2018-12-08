/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.business;

import com.alex.miruta2018.model.PuntoInteres;
import com.alex.miruta2018.model.support.PuntoInteresCreate;
import com.alex.miruta2018.model.support.PuntoInteresUpdate;
import com.alex.miruta2018.services.PtoInteresService;
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
@RequestMapping("/miruta/ptoInteres")
public class PtoInteresBusiness {
    
    @Autowired
    private PtoInteresService servicePtoInteres;
    
    
    @RequestMapping(value = "/create", method = POST)
    public ResponseEntity<PuntoInteres> savePointInteres(@RequestBody PuntoInteresCreate punto) {
        return new ResponseEntity(servicePtoInteres.create(punto), HttpStatus.OK);
    }
    
    @RequestMapping(value = "", method = GET)
    public ResponseEntity<PuntoInteres> pointInteresById(@RequestParam(value = "id", required=false) Long id) {
        if(id == null){
            return new ResponseEntity(servicePtoInteres.getAll(), HttpStatus.OK);
        }
        return new ResponseEntity(servicePtoInteres.getById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/detete", method = POST)
    public ResponseEntity deletePointInteres(@RequestParam(value = "id") long id) {
        // ver como informar q se elimino correctamente al usuario
        servicePtoInteres.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<PuntoInteres> updatePtoInteres(@RequestBody PuntoInteresUpdate punto) {
        return new ResponseEntity(servicePtoInteres.update(punto), HttpStatus.OK);
    }
    
    // *****************************************************************************
    // ******************************** SOPORTE ************************************
    
    @RequestMapping(value = "/byType", method = GET)
    public ResponseEntity<PuntoInteres> ptoInteresByType(@RequestParam(value = "idtipo", required=false) Long idtipo) {
        if(idtipo == null){
            return new ResponseEntity(servicePtoInteres.getAll(), HttpStatus.OK);
        }
        return new ResponseEntity(servicePtoInteres.getByType(idtipo), HttpStatus.OK);
//        return new ResponseEntity(servicePtoInteres.getByTipo(idtipo), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/byNameType", method = GET)
    public ResponseEntity<PuntoInteres> ptoInteresByNameType(@RequestParam(value = "tipo", required=false) String tipo) {
        if(tipo.equals("todos")){
            return new ResponseEntity(servicePtoInteres.getAllByName(), HttpStatus.OK);
        }
        return new ResponseEntity(servicePtoInteres.getByNameType(tipo), HttpStatus.OK);
    }
}
