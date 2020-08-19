package br.com.gerenciamento.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSistema extends User{
	
	private static final long serialVersionUID = 6982625737396601437L;
	private String nome;
	
	public UsuarioSistema(String nome, String username, String password,
							Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.nome = nome;
	}
	
	public String getName() {
		return nome;
	}
	 

}
