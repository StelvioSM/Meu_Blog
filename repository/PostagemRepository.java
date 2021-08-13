package org.generetion.blogpessoal.repository;

import java.util.List;

import org.generetion.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

}
