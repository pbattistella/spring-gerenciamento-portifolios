package br.com.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gerenciamento.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByLogin(String login);

}
