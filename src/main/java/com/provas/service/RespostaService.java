package com.provas.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.provas.criteria.RespostaCriteria;
import com.provas.dao.RespostaDao;
import com.provas.entity.Aplicacao;
import com.provas.entity.Resposta;

import br.com.examserver.fwk.dao.CrudBaseDAO;
import br.com.examserver.fwk.service.CrudBaseService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@Stateless
public class RespostaService extends CrudBaseService<Resposta, RespostaCriteria> {

	/**
	 *
	 */
	private static final long serialVersionUID = 3944395562914022498L;

	@Inject
	private RespostaDao dao;

	@Override
	protected CrudBaseDAO<Resposta, RespostaCriteria> getDAO() {
		return dao;
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param aplicacao
	 * @return
	 */
	public List<Resposta> listarRespostasPorAplicacao(final Aplicacao aplicacao) {
		final RespostaCriteria criteria = new RespostaCriteria();
		criteria.setAplicacao(aplicacao);
		return dao.findBy(criteria , 0, Integer.MAX_VALUE);
	}

}
