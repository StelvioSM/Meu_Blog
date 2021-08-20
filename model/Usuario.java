package org.generetion.blogpessoal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "td_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo não pode ser vazio")
	private String nome;

	@NotBlank(message = "O campo não pode ser vazio")
	private String usuario;

	@NotBlank(message = "Minimo 8 Caracteres")
	@Size(min = 8, max = 100)
	private String senha;

	@Email(message = "O campo não pode ser vazio")
	private String email;

	@OneToMany(mappedBy = "criador", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"criador"})
	private List<Postagem> minhasPostagem = new ArrayList<>();
	
	public Usuario(Long id,  String nome,String usuario,String senha,String email) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.email = email;
	}

	public Usuario() {
		
		// TODO Auto-generated constructor stub
	}
	public List<Postagem> getMinhasPostagem() {
		return minhasPostagem;
	}

	public void setMinhasPostagem(List<Postagem> minhasPostagem) {
		this.minhasPostagem = minhasPostagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setusuario(String usuario)
	{
		this.usuario = usuario;
	}


	public List<Postagem> getMinhasPostagens() {
		return minhasPostagem;
	}


	public void setMinhasPostagens(List<Postagem> minhasPostagens) {
		this.minhasPostagem = minhasPostagens;
	}
	 
	
	
}
