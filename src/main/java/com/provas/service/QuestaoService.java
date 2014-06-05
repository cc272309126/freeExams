package com.provas.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.provas.criteria.QuestaoCriteria;
import com.provas.dao.QuestaoDao;
import com.provas.entity.Categoria;
import com.provas.entity.Questao;

import br.com.examserver.fwk.dao.CrudBaseDAO;
import br.com.examserver.fwk.service.CrudBaseService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@Stateless
public class QuestaoService extends CrudBaseService<Questao, QuestaoCriteria> {

	/**
	 *
	 */
	private static final long serialVersionUID = 5401345843251162332L;

	@Inject
	private QuestaoDao dao;

	@Override
	protected CrudBaseDAO<Questao, QuestaoCriteria> getDAO() {
		return dao;
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param categoria
	 * @return
	 */
	public List<Questao> listarQuestaoPorCategoria(final Categoria categoria) {
		final QuestaoCriteria criteria = new QuestaoCriteria();
		criteria.setCategoria(categoria);
		return dao.findBy(criteria , 0, Integer.MAX_VALUE);
	}

}
