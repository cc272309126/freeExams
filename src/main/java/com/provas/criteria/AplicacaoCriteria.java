package com.provas.criteria;

import com.provas.entity.Usuario;
import com.provas.enumerator.StatusProva;

import br.com.examserver.fwk.criteria.BaseCriteria;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class AplicacaoCriteria extends BaseCriteria {

	private StatusProva status;
	private Usuario usuario;

	public StatusProva getStatus() {
		return status;
	}

	public void setStatus(final StatusProva string) {
		this.status = string;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

}
