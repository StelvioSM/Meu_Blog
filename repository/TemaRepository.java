package org.generetion.blogpessoal.repository;

import java.util.List;

import org.generetion.blogpessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
	public List<Tema> findAllByTextoContainingIgnoreCase(String texto);
}
