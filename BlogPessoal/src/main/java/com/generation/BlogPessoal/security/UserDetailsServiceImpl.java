package com.generation.BlogPessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.BlogPessoal.model.UsuarioModel;
import com.generation.BlogPessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<UsuarioModel> usuario = userRepository.findByUsuario(userName);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		return usuario.map(UserDetailsImpl::new).get(); // referencia de metodo
	}
}