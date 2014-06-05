package com.provas.dao;

import com.provas.criteria.ProvaCriteria;
import com.provas.entity.Prova;

import br.com.examserver.fwk.criteria.ServerQuery;
import br.com.examserver.fwk.dao.CrudBaseDAO;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class ProvaDao extends CrudBaseDAO<Prova, ProvaCriteria> {

	@Override
	protected ServerQuery createWhereClause(final String entityAlias,
			final ProvaCriteria criteria) {
		// TODO - Implementar
		return null;
	}

}