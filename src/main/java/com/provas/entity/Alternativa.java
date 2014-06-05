package com.provas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.examserver.fwk.entity.BaseEntity;

@Entity
@Table(name = "alternativa")
public class Alternativa extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "questao_id")
	private Questao questao;
	private String conteudo;
	private boolean correta;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(final Questao questao) {
		this.questao = questao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(final String conteudo) {
		this.conteudo = conteudo;
	}

	public boolean getCorreta() {
		return correta;
	}

	public void setCorreta(final boolean correta) {
		this.correta = correta;
	}

	@Override
	public String toString() {
		return conteudo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((conteudo == null) ? 0 : conteudo.hashCode());
		result = prime * result + (correta ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Alternativa other = (Alternativa) obj;
		if (conteudo == null) {
			if (other.conteudo != null) {
				return false;
			}
		} else if (!conteudo.equals(other.conteudo)) {
			return false;
		}
		if (correta != other.correta) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (questao == null) {
			if (other.questao != null) {
				return false;
			}
		} else if (!questao.equals(other.questao)) {
			return false;
		}
		return true;
	}

}
