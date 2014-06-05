package com.provas.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.examserver.fwk.entity.BaseEntity;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@Entity
@Table(name = "prova")
public class Prova extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String nome;

	@OneToMany(mappedBy = "prova", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ProvaQuestao> questoes;

	@OneToMany(mappedBy = "prova", cascade = CascadeType.ALL)
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

	public List<ProvaQuestao> getQuestoes() {
		final List<ProvaQuestao> questoes = new ArrayList<ProvaQuestao>();
        if (this.questoes == null)
        {
            return questoes;
        }
        else
        {
            for(final ProvaQuestao f : this.questoes)
            {
            	questoes.add(f);
            }
        }

        return questoes;
	}

	public void setQuestoes(final Set<ProvaQuestao> questoes) {
		this.questoes = questoes;
	}

	public List<Aplicacao> getAplicacoes() {
		return aplicacoes;
	}

	public void setAplicacoes(final List<Aplicacao> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		final Prova other = (Prova) obj;
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

}
