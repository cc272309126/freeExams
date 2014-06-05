package com.provas.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.provas.criteria.AlternativaCriteria;
import com.provas.dao.AlternativaDao;
import com.provas.entity.Alternativa;

import br.com.examserver.fwk.dao.CrudBaseDAO;
import br.com.examserver.fwk.service.CrudBaseService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@Stateless
public class AlternativaService extends CrudBaseService<Alternativa, AlternativaCriteria> {

	/**
	 *
	 */
	private static final long serialVersionUID = -7607774928078261743L;

	@Inject
	private AlternativaDao dao;

	@Override
	protected CrudBaseDAO<Alternativa, AlternativaCriteria> getDAO() {
		return dao;
	}

}
