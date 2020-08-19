package br.com.gerenciamento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.gerenciamento.security.GerenciamentoUserDetailsService;

@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private GerenciamentoUserDetailsService gerenciamentoUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			//habilitar ou desabilitar páginas
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/principal").hasRole("GERENCIAMENTO")
			//habilitar statics
			.antMatchers("/bootstrap-4.4.1/**").permitAll()
        	.antMatchers("/css/**").permitAll()
        	.antMatchers("/img/**").permitAll()
        	.antMatchers("/js/**").permitAll()
        	.antMatchers("/font-awesome-4.7.0/**").permitAll()
        	//demais páginas devem ser autenticadas
        	.anyRequest().authenticated()
        	.and()
        	//Definir página de login
        	.formLogin()
	        	.loginPage("/login")
	        	.defaultSuccessUrl("/principal", true)
	        	.permitAll()
        	.and()
        	//lembrar do usuário logado
        	.rememberMe();	    
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception{
		builder
			.userDetailsService(gerenciamentoUserDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	public static void main (String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

}
