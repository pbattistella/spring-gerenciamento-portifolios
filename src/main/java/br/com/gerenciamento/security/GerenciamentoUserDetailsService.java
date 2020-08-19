package br.com.gerenciamento.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.gerenciamento.model.Grupo;
import br.com.gerenciamento.model.Permissao;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.repository.GrupoRepository;
import br.com.gerenciamento.repository.PermissaoRepository;
import br.com.gerenciamento.repository.UsuarioRepository;

@Component
public class GerenciamentoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private PermissaoRepository permissoes;
	
	@Autowired
	private GrupoRepository grupos;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Usuario usuario = usuarios.findByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return new UsuarioSistema(usuario.getNome(), usuario.getLogin(), usuario.getSenha(), authorities(usuario));
	}
	
	public Collection<? extends GrantedAuthority> authorities(Usuario usuario){
		return authorities(grupos.findByUsuariosIn(usuario));
	}
	
	public Collection<? extends GrantedAuthority> authorities(List<Grupo> grupos){
		Collection<GrantedAuthority> auths = new ArrayList<>();
		
		for (Grupo grupo: grupos) {
			List<Permissao> listapermissoes = permissoes.findByGruposIn(grupo);
			
			for(Permissao permissao: listapermissoes) {
				auths.add(new SimpleGrantedAuthority("ROLE_"+permissao.getNome()));
			}
		}
		return auths;
		
	}
	
	

}
