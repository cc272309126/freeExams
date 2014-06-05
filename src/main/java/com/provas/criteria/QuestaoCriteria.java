package com.provas.criteria;

import com.provas.entity.Categoria;

import br.com.examserver.fwk.criteria.BaseCriteria;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class QuestaoCriteria extends BaseCriteria {

	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(final Categoria categoria) {
		this.categoria = categoria;
	}


}
