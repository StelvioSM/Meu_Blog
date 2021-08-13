package org.generetion.blogpessoal.Servico;

import java.util.Optional;

import org.generetion.blogpessoal.model.Tema;
import org.generetion.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaServico {
	@Autowired
	private  TemaRepository repositorioT;
	
	public Optional<Tema> alterarTema(Tema temaParaAlterar) {
		return repositorioT.findById(temaParaAlterar.getId()).map(temaExistente -> {
			temaExistente.setTexto(temaParaAlterar.getTexto());
			return Optional.ofNullable(repositorioT.save(temaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
