package com.generation.BlogPessoal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity

public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override // primeira configuração de longin
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService); // login com usuario cadastrado no banco de dados

		// ou

		auth.inMemoryAuthentication() // só para indicar que root é um usuario, entao nao precisa estar no banco de
										// dados
				.withUser("root").password(passwordEncoder().encode("root")).authorities("ROLE_USER");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/usuario/logar")
		.permitAll().antMatchers("/usuario/cadastrar")
		.permitAll().antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest().authenticated() // qualquer outra
																										// requisição
																										// precisa estar
																										// autenticada
				.and().httpBasic() // requisições vao vir por http
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and()
				.csrf().disable();
	}

}
