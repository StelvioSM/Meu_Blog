package org.generetion.blogpessoal.Servico;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import org.generetion.blogpessoal.model.Usuario;
import org.generetion.blogpessoal.model.UsuarioDTO;
import org.generetion.blogpessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepository repository;

	/**
	 * Método utilizado para cadastrar um usuario no banco de dados, o mesmo é
	 * responsavel por retornar vazio caso Usuario exista
	 * 
	 * @param novoUsuario do tipo Usuario
	 * @return Usuario Criado quando não existir no banco
	 * @since 1.0
	 * @author Blog Pessoal
	 */

	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		return repository.findByEmail(novoUsuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(novoUsuario));
		});
	}

	public Optional<?> pegarCredenciais(UsuarioDTO usuarioParaAutenticar) {
		return repository.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {
				String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha();
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII")));
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);

				usuarioParaAutenticar.setToken(autorizacaoHeader);
				usuarioParaAutenticar.setId(usuarioExistente.getId());
				usuarioParaAutenticar.setUsuario(usuarioExistente.getUsuario());
				usuarioParaAutenticar.setSenha(usuarioExistente.getSenha());
				usuarioParaAutenticar.setEmail(usuarioExistente.getEmail());
				usuarioParaAutenticar.setNome(usuarioExistente.getNome());
				return Optional.ofNullable(usuarioParaAutenticar);
			} else {
				return Optional.empty();
			}
		});
	}
	
	/**
	 * Metodo utilizado para alterar um usuario fornecido pelo FRONT, O mesmo
	 * retorna um Optional com entidade Usuario dentro e senha criptografada. Caso
	 * falho retorna um Optional.empty()
	 * 
	 * @param usuarioParaAlterar do tipo Usuario
	 * @return Optional com Usuario Alterado
	 * @since 1.0
	 * @author Turma 28
	 */
	public Optional<?> alterarUsuario(UsuarioDTO usuarioParaAlterar) {
		return repository.findById(usuarioParaAlterar.getId()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioParaAlterar.getSenha());

			usuarioExistente.setNome(usuarioParaAlterar.getNome());
			usuarioExistente.setUsuario(usuarioParaAlterar.getUsuario());
			usuarioExistente.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(usuarioExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
