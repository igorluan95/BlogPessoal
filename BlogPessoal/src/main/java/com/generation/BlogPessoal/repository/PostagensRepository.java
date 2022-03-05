package com.generation.BlogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.BlogPessoal.model.PostagemModel;

@Repository
public interface PostagensRepository extends JpaRepository<PostagemModel, Long> {

	public List<PostagemModel> findAllByTituloContainingIgnoreCase(String titulo);
	
}
