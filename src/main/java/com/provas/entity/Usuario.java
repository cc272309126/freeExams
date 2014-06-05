package com.provas.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.provas.enumerator.TipoUsuario;

import br.com.examserver.fwk.entity.BaseEntity;

@Entity
@Table(name = "usuario")
public class Usuario extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	@OneToMany(mappedBy = "usuario")
	private List<Aplicacao> aplicacoes;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(final TipoUsuario tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return nome;
	}

	public List<Aplicacao> getAplicacoes() {
		return aplicacoes;
	}

	public void setAplicacoes(final List<Aplicacao> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

}
