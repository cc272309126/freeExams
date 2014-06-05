package com.provas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.examserver.fwk.entity.BaseEntity;

@Entity
@Table(name = "resposta")
public class Resposta extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "aplicacao_id")
	private Aplicacao aplicacao;
	@OneToOne
	private Alternativa alternativa;
	private String conteudo;
	private Boolean correta;

	public Boolean getCorreta() {
		return correta;
	}

	public void setCorreta(final Boolean correta) {
		this.correta = correta;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(final Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(final Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(final String conteudo) {
		this.conteudo = conteudo;
	}

}
