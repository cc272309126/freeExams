package com.provas.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.provas.criteria.AplicacaoCriteria;
import com.provas.dao.AplicacaoDao;
import com.provas.entity.Aplicacao;
import com.provas.entity.Usuario;
import com.provas.enumerator.StatusProva;

import br.com.examserver.fwk.dao.CrudBaseDAO;
import br.com.examserver.fwk.service.CrudBaseService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@Stateless
public class AplicacaoService extends CrudBaseService<Aplicacao, AplicacaoCriteria> {

	/**
	 *
	 */
	private static final long serialVersionUID = -9046330806482474543L;

	@Inject
	private AplicacaoDao dao;

	@Override
	protected CrudBaseDAO<Aplicacao, AplicacaoCriteria> getDAO() {
		return dao;
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param status
	 * @param usuario
	 * @return
	 */
	public List<Aplicacao> listarAplicacaoPorStatus(final StatusProva status, final Usuario usuario) {
		final AplicacaoCriteria criteria = new AplicacaoCriteria();
		criteria.setStatus(status);
		if (StatusProva.ABERTA.equals(status)) {
			criteria.setUsuario(usuario);
		}
		return dao.findBy(criteria , 0, Integer.MAX_VALUE);
	}

}
