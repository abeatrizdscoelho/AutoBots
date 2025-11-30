package com.autobots.automanager.authentication.filters;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.autobots.automanager.authentication.jwt.ProvedorJWT;
import com.autobots.automanager.entities.Credencial;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Autenticador extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager gerenciadorAutenticacao;
	private ProvedorJWT provedorJWT;

	public Autenticador(AuthenticationManager gerenciadorAutenticacao, ProvedorJWT provedorJWT) {
		this.gerenciadorAutenticacao = gerenciadorAutenticacao;
		this.provedorJWT = provedorJWT;
		setFilterProcessesUrl("/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Credencial credencial = null;
		try {
			credencial = new ObjectMapper().readValue(request.getInputStream(), Credencial.class);
		} catch (IOException e) {
			credencial = new Credencial();
			credencial.setNomeUsuario("");
			credencial.setSenha("");
		}
		UsernamePasswordAuthenticationToken dadosAutenticacao = new UsernamePasswordAuthenticationToken(
				credencial.getNomeUsuario(), credencial.getSenha(), new ArrayList<>());
		Authentication autenticacao = gerenciadorAutenticacao.authenticate(dadosAutenticacao);
		return autenticacao;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication autenticacao) throws IOException, ServletException {
		UserDetails usuario = (UserDetails) autenticacao.getPrincipal();
		String nomeUsuario = usuario.getUsername();
		String jwt = provedorJWT.proverJWT(nomeUsuario);
		response.addHeader("Authorization", "Bearer " + jwt);
	}
}
