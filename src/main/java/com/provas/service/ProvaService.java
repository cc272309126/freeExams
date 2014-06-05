package com.provas.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.provas.criteria.ProvaCriteria;
import com.provas.dao.ProvaDao;
import com.provas.entity.Prova;

import br.com.examserver.fwk.dao.CrudBaseDAO;
import br.com.examserver.fwk.service.CrudBaseService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@Stateless
public class ProvaService extends CrudBaseService<Prova, ProvaCriteria> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1506375430620716980L;

	@Inject
	private ProvaDao dao;

	@Override
	protected CrudBaseDAO<Prova, ProvaCriteria> getDAO() {
		return dao;
	}

}
