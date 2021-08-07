package org.generetion.blogpessoal.Security;

import java.util.Optional;

import org.generetion.blogpessoal.model.Usuario;
import org.generetion.blogpessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByEmail(username);

		if (usuario.isPresent()) {
			return new UserDetailsIml(usuario.get());
		} else {
			throw new UsernameNotFoundException(username + "not fround.");
		}
	}

}
