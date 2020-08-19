package br.com.gerenciamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gerenciamento.model.Grupo;
import br.com.gerenciamento.model.Usuario;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	
	public List<Grupo> findByUsuariosIn(Usuario ... usuario);

}
