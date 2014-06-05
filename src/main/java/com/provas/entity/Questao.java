package com.provas.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.provas.enumerator.TipoQuestao;

import br.com.examserver.fwk.entity.BaseEntity;

@Entity
@Table(name = "questao")
public class Questao extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String enunciado;

	@Enumerated(EnumType.STRING)
	private TipoQuestao tipo;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Alternativa> alternativas;

	@Transient
	private List<Alternativa> alternativasEscolhidas;
	@Transient
	private Alternativa alternativaEscolhida;
	@Transient
	private String alternativaTexto;
	@Transient
	private Alternativa alternativaTextoCorreta;
	@Transient
	private Resposta respostaTexto;
	@Transient
	private int numeroDaQuestao;
	@Transient
	private int peso;
	@Transient
	private boolean correta;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(final String enunciado) {
		this.enunciado = enunciado;
	}

	public TipoQuestao getTipo() {
		return tipo;
	}

	public void setTipo(final TipoQuestao tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(final Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(final List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public List<Alternativa> getAlternativasEscolhidas() {
		return alternativasEscolhidas;
	}

	public void setAlternativasEscolhidas(
			final List<Alternativa> alternativasEscolhidas) {
		this.alternativasEscolhidas = alternativasEscolhidas;
	}

	public String getAlternativaTexto() {
		return alternativaTexto;
	}

	public void setAlternativaTexto(final String alternativaTexto) {
		this.alternativaTexto = alternativaTexto;
	}

	public Alternativa getAlternativaEscolhida() {
		return alternativaEscolhida;
	}

	public void setAlternativaEscolhida(final Alternativa alternativaEscolhida) {
		this.alternativaEscolhida = alternativaEscolhida;
	}

	public Alternativa getAlternativaTextoCorreta() {
		return alternativaTextoCorreta;
	}

	public void setAlternativaTextoCorreta(final Alternativa alternativaTextoCorreta) {
		this.alternativaTextoCorreta = alternativaTextoCorreta;
	}

	public Resposta getRespostaTexto() {
		return respostaTexto;
	}

	public void setRespostaTexto(final Resposta respostaTexto) {
		this.respostaTexto = respostaTexto;
	}

	public int getNumeroDaQuestao() {
		return numeroDaQuestao;
	}

	public void setNumeroDaQuestao(final int numeroDaQuestao) {
		this.numeroDaQuestao = numeroDaQuestao;
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

	public boolean isCorreta() {
		return correta;
	}

	public void setCorreta(final boolean correta) {
		this.correta = correta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		final Questao other = (Questao) obj;
		if (enunciado == null) {
			if (other.enunciado != null) {
				return false;
			}
		} else if (!enunciado.equals(other.enunciado)) {
			return false;
		}
		if (tipo != other.tipo) {
			return false;
		}
		return true;
	}

}
