package br.com.gerenciamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gerenciamento.model.Grupo;
import br.com.gerenciamento.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
	
	public List<Permissao> findByGruposIn(Grupo ... grupo);

}
