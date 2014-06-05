package com.provas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.examserver.fwk.entity.BaseEntity;

@Entity
@Table(name = "prova_questao")
public class ProvaQuestao extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "prova_id")
	private Prova prova;
	@ManyToOne
	@JoinColumn(name = "questao_id")
	private Questao questao;
	private int peso;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(final Prova prova) {
		this.prova = prova;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(final Questao questao) {
		this.questao = questao;
	}

	public int getPeso() {
		if (peso == 0) {
			return 1;
		}
		return peso;
	}

	public void setPeso(final int peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((prova == null) ? 0 : prova.hashCode());
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
		final ProvaQuestao other = (ProvaQuestao) obj;
		if (prova == null) {
			if (other.prova != null) {
				return false;
			}
		} else if (!prova.equals(other.prova)) {
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
