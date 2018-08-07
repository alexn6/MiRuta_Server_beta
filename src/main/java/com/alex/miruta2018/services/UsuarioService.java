/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.services;

import com.alex.miruta2018.model.Usuario;
import com.alex.miruta2018.repo.crud.RepositorioUsuarioCrud;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author alextc6
 */
@Service
public class UsuarioService {
    
    @Autowired
    private RepositorioUsuarioCrud repoCrudUsuario;
    
    @Autowired
    private BCryptPasswordEncoder encriptadorPassword;
    
    public Usuario getById(Long id){
        return repoCrudUsuario.findById(id).get();
    }
    
    public List<Usuario> getAll(){
        Iterable<Usuario> users = repoCrudUsuario.findAll();
        List<Usuario> list = new ArrayList<Usuario>();
        users.forEach(list::add);
        return list;
    }
    
    public Usuario create(Usuario usuario){
        usuario.setPass(encriptadorPassword.encode(usuario.getPass()));
        return repoCrudUsuario.save(usuario);
    }
    
    public Usuario update(Usuario usuario){
        usuario.setPass(encriptadorPassword.encode(usuario.getPass()));
        return repoCrudUsuario.save(usuario);
    }
    
    // ver si mandar algun mje cuando se elimina correctamente
    public void delete(Long id){
        repoCrudUsuario.deleteById(id);
    }
}
