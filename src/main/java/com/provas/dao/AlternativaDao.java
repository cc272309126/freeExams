package com.provas.dao;

import com.provas.criteria.AlternativaCriteria;
import com.provas.entity.Alternativa;

import br.com.exanserver.fwk.criteria.ServerQuery;
import br.com.examserver.fwk.dao.CrudBaseDAO;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class AlternativaDao extends CrudBaseDAO<Alternativa, AlternativaCriteria> {

	@Override
	protected ServerQuery createWhereClause(final String entityAlias,
			final AlternativaCriteria criteria) {
		// TODO - Implementar
		return null;
	}

}
