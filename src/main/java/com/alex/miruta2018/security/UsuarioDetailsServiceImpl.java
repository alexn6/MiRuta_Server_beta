/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.security;

import com.alex.miruta2018.model.Usuario;
import com.alex.miruta2018.repo.queries.RepositorioUsuarioJpa;
import static java.util.Collections.emptyList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author alextc6
 */
@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	private RepositorioUsuarioJpa repoUsuarioJpa;

	public UsuarioDetailsServiceImpl(RepositorioUsuarioJpa repoUsuarioJpa) {
		this.repoUsuarioJpa = repoUsuarioJpa;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repoUsuarioJpa.findByNombre(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(usuario.getNombre(), usuario.getPass(), emptyList());
	}
}