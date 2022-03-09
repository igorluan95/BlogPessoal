package com.generation.BlogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.BlogPessoal.model.UsuarioModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {

		
		usuarioRepository.save(new UsuarioModel(0L, "Marcola Souza", "marcolasouza@gmail.com", "1434465278",
				"https://i.imgur.com/FETvs2O.jpg"));

		usuarioRepository.save(new UsuarioModel(0L, "Matheus Souza", "matheussilva@email.com.br", "135tr465278",
				"https://i.imgur.com/NtyGneo.jpg"));

		usuarioRepository.save(new UsuarioModel(0L, "Luiz Gustavo Souza", "luizgustavo@email.com.br", "1346rer5278",
				"https://i.imgur.com/mB3VM2N.jpg"));

		usuarioRepository.save(new UsuarioModel(0L, "Robson Caetano", "rcaetano@email.com.br", "13465278",
				"htvvdtps://i.imgur.com/JR7kUFU.jpg"));

	}

	@Test
	@DisplayName("Retorna apenas 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario("marcolasouza@gmail.com");

		assertTrue(usuario.get().getUsuario().equals("marcolasouza@gmail.com"));
	}

	@Test
	@DisplayName("Retorna 3 usuários")
	public void deveRetornarTresUsuarios() {

		List<UsuarioModel> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Souza");

		assertEquals(3, listaDeUsuarios.size());

		assertTrue(listaDeUsuarios.get(0).getNome().equals("Marcola Souza"));

		assertTrue(listaDeUsuarios.get(1).getNome().equals("Matheus Souza"));

		assertTrue(listaDeUsuarios.get(2).getNome().equals("Luiz Gustavo Souza"));

	}

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}

}
