package org.generetion.blogpessoal.repository;

import java.util.List;
import java.util.Optional;
import org.generetion.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


	/**** Metodo utilizado para selecionar apenas um Usuario pelo email (Chave unica)
	 * 
	 * @param email
	 * @return Optional com Usuario unico
	 * @since 1.0
	 * @author blog pessoal
	 */
	public Optional<Usuario> findByEmail(String email);
	
	/** * Metodo utilizado para pesquisar coluna nome da tabela Usuario
	 * 
	 * @param nome
	 * @return Lista de Usuario
	 * @since 1.0
	 * @author blog pessoal
	 */

	List<Usuario> findAllByNomeContainingIgnoreCase(String nome);	
	
}
