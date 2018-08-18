/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.business;

import com.alex.miruta2018.model.UnidadLinea;
import com.alex.miruta2018.model.support.RecorridoCreate;
import com.alex.miruta2018.model.support.RespRecorridoUnidadLinea;
import com.alex.miruta2018.model.support.UnidadTransporteCreate;
import com.alex.miruta2018.model.support.UnidadTransporteUpdate;
import com.alex.miruta2018.services.UnidadLineaService;
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
@RequestMapping("/miruta/unidadlinea")
public class UnidadLineaBusiness {
    
    @Autowired
    private UnidadLineaService serviceUnidadLinea;
    

    // ************************************ ABMC ************************************
    // ******************************************************************************
    @RequestMapping(value = "/create", method = POST)
    public ResponseEntity<UnidadLinea> saveUniTransporte(@RequestBody UnidadTransporteCreate unidad) {
        return new ResponseEntity(serviceUnidadLinea.create(unidad), HttpStatus.OK);
    }
    
    @RequestMapping(value = "", method = GET)
    public ResponseEntity<UnidadLinea> uniTransporteById(@RequestParam(value = "id", required = false) Long id) {
        if(id == null){
            return new ResponseEntity(serviceUnidadLinea.getAll(), HttpStatus.OK);
        }
        return new ResponseEntity(serviceUnidadLinea.getById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete", method = POST)
    public ResponseEntity deleteUniTransporte(@RequestParam(value = "id") long id) {
        // ver como informar q se elimino correctamente al usuario
        serviceUnidadLinea.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<UnidadLinea> updateUniTransporte(@RequestBody UnidadTransporteUpdate unidad) {
        return new ResponseEntity(serviceUnidadLinea.update(unidad), HttpStatus.OK);
    }
    
    // ******************************* *Secundarias ************************************
    // *********************************************************************************
    
    @RequestMapping(value = "/setRecorrido", method = POST)
    public ResponseEntity<UnidadLinea> setRecorrido(@RequestBody RecorridoCreate recorrido) {
        System.out.println(recorrido.toString());
        return new ResponseEntity(serviceUnidadLinea.updateRecorrido(recorrido), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/recorrido", method = GET)
    public ResponseEntity<RespRecorridoUnidadLinea> getRecorrido(@RequestParam(value = "id", required = false) Long id) {
        if(id == null){
            return new ResponseEntity(serviceUnidadLinea.getAllRecorrido(), HttpStatus.OK);
        }
        return new ResponseEntity(serviceUnidadLinea.getRecorridoById(id), HttpStatus.OK);
    }
    
    // ************************************ SOPORTE ************************************
    // *********************************************************************************
    
    @RequestMapping(value = "/names", method = GET)
    public ResponseEntity<String> nombreUniTransporte() {
        return new ResponseEntity(serviceUnidadLinea.getAllNames(), HttpStatus.OK);
    }
}
