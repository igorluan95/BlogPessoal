package com.generation.BlogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.BlogPessoal.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	/** Método para verificar se o usuário já existe no banco de dados */
	public Optional<UsuarioModel> findByUsuario(String usuario);

	/* Testes */
	public List<UsuarioModel> findAllByNomeContainingIgnoreCase(String nome);

}