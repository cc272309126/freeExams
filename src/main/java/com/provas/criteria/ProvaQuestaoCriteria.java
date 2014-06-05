package com.provas.criteria;

import com.provas.entity.Prova;

import br.com.examserver.fwk.criteria.BaseCriteria;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class ProvaQuestaoCriteria extends BaseCriteria {

	private Prova prova;

	public Prova getProva() {
		return prova;
	}

	public void setProva(final Prova prova) {
		this.prova = prova;
	}

}
