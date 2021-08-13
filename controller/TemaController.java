package org.generetion.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generetion.blogpessoal.model.Tema;
import org.generetion.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class TemaController {

	@Autowired
	private TemaRepository repository;

	@GetMapping
	public ResponseEntity<List<Tema>> GetAll() {
		return ResponseEntity.status(200).body(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Tema>> GetByTexto(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(titulo));
	}

	@PostMapping
	public ResponseEntity<Tema> post(@Valid @RequestBody Tema titulo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(titulo));
	}

	@PutMapping
	public ResponseEntity<Tema> put(@Valid @RequestBody Tema titulo) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(titulo));

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
