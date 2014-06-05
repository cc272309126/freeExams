package com.provas.criteria;

import com.provas.entity.Aplicacao;

import br.com.examserver.fwk.criteria.BaseCriteria;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class RespostaCriteria extends BaseCriteria {

	private Aplicacao aplicacao;

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(final Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

}
