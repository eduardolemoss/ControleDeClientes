package com.api.ady.seguranca;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.ady.usuarios.UsuarioRepository;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AutenticacaoService  implements UserDetailsService{
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		return usuarioRepository.findByLogin(username)
				.map(Usuario -> new User (Usuario.getLogin(), Usuario.getSenha(), Collections.emptyList()))
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}

}
