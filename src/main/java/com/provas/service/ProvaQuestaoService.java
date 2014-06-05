package com.provas.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.provas.criteria.ProvaQuestaoCriteria;
import com.provas.dao.ProvaQuestaoDao;
import com.provas.entity.Prova;
import com.provas.entity.ProvaQuestao;

import br.com.examserver.fwk.dao.CrudBaseDAO;
import br.com.examserver.fwk.service.CrudBaseService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@Stateless
public class ProvaQuestaoService extends CrudBaseService<ProvaQuestao, ProvaQuestaoCriteria> {

	/**
	 *
	 */
	private static final long serialVersionUID = -4616153216268229272L;

	@Inject
	private ProvaQuestaoDao dao;

	@Override
	protected CrudBaseDAO<ProvaQuestao, ProvaQuestaoCriteria> getDAO() {
		return dao;
	}

	public List<ProvaQuestao> listarProvaQuestaoPorProva(final Prova prova) {
		final ProvaQuestaoCriteria criteria = new ProvaQuestaoCriteria();
		criteria.setProva(prova);
		return dao.findBy(criteria , 0, Integer.MAX_VALUE);
	}

}
