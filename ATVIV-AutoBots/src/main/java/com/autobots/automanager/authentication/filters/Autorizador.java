package com.autobots.automanager.authentication.filters;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.autobots.automanager.authentication.jwt.ProvedorJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Autorizador extends BasicAuthenticationFilter {

	private ProvedorJWT provedorJWT;
	private UserDetailsService service;
	private ValidadorCabecalho validador;
	private AnalisadorCabecalho analisador;
	private AutenticadorJWT autenticadorJWT;

	public Autorizador(AuthenticationManager gerenciadorAutenticacao, ProvedorJWT provedorJWT,
			UserDetailsService service) {
		super(gerenciadorAutenticacao);
		this.service = service;
		this.provedorJWT = provedorJWT;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String cabecalho = request.getHeader("Authorization");

		validador = new ValidadorCabecalho(cabecalho);
		if (validador.validar()) {
			analisador = new AnalisadorCabecalho(cabecalho);
			String jwt = analisador.obterJwt();
			autenticadorJWT = new AutenticadorJWT(jwt, provedorJWT, service);
			UsernamePasswordAuthenticationToken autenticacao = autenticadorJWT.obterAutenticacao();
			if (autenticacao != null) {
				SecurityContextHolder.getContext().setAuthentication(autenticacao);
			}
		}
		chain.doFilter(request, response);
	}
}
