package com.provas.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.provas.criteria.CategoriaCriteria;
import com.provas.dao.CategoriaDao;
import com.provas.entity.Categoria;

import br.com.examserver.fwk.dao.CrudBaseDAO;
import br.com.examserver.fwk.service.CrudBaseService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@Stateless
public class CategoriaService extends CrudBaseService<Categoria, CategoriaCriteria> {

	/**
	 *
	 */
	private static final long serialVersionUID = 3778097101090808032L;

	@Inject
	private CategoriaDao dao;

	@Override
	protected CrudBaseDAO<Categoria, CategoriaCriteria> getDAO() {
		return dao;
	}

}
