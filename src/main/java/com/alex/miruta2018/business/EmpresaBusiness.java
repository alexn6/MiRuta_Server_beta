/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.business;

import com.alex.miruta2018.model.Empresa;
import com.alex.miruta2018.model.TipoInteres;
import com.alex.miruta2018.services.EmpresaService;
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
@RequestMapping("/miruta/company")
public class EmpresaBusiness {
    @Autowired
    private EmpresaService serviceEmpresa;

    
    @RequestMapping(value = "/create", method = POST)
    public ResponseEntity<Empresa> saveCompany(@RequestBody Empresa empresa) {
        Empresa rdoEmpresa = serviceEmpresa.create(empresa);
        if(rdoEmpresa == null){
            return new ResponseEntity("La empresa ya existe", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(rdoEmpresa, HttpStatus.OK);
    }
    
    @RequestMapping(value = "", method = GET)
    public ResponseEntity<Empresa> companyById(@RequestParam(value = "id", required=false) Long id) {
        if(id == null){
            return new ResponseEntity(serviceEmpresa.getAll(), HttpStatus.OK);
        }
        return new ResponseEntity(serviceEmpresa.getById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete", method = POST)
    public ResponseEntity deleteCompany(@RequestParam(value = "id") long id) {
        // ver como informar q se elimino correctamente al usuario
        serviceEmpresa.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<Empresa> updateCompany(@RequestBody Empresa empresa) {
        Empresa rdoEmpresa = serviceEmpresa.update(empresa);
        if(rdoEmpresa == null){
            return new ResponseEntity("La empresa ya existe", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(rdoEmpresa, HttpStatus.OK);
    }
}

