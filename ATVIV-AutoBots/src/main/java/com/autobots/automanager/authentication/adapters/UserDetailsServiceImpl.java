package com.autobots.automanager.authentication.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	private Usuario obterPorNome(String nomeUsuario) {
		List<Usuario> usuarios = repository.findAll();
		Usuario selecionado = null;
		for (Usuario usuario : usuarios) {
			if (usuario.getCredencial().getNomeUsuario().equals(nomeUsuario)) {
				selecionado = usuario;
				break;
			}
		}
		return selecionado;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario selecionado = this.obterPorNome(username);
		if (selecionado == null) {
			throw new UsernameNotFoundException(username);
		}
		UserDetailsImpl usuario = new UserDetailsImpl(selecionado);
		return usuario;
	}
}
