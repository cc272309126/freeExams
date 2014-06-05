package com.provas.dao;

import com.provas.criteria.ProvaQuestaoCriteria;
import com.provas.entity.ProvaQuestao;

import br.com.examserver.fwk.criteria.ServerQuery;
import br.com.examserver.fwk.dao.CrudBaseDAO;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class ProvaQuestaoDao extends
		CrudBaseDAO<ProvaQuestao, ProvaQuestaoCriteria> {

	@Override
	protected ServerQuery createWhereClause(final String entityAlias,
			final ProvaQuestaoCriteria criteria) {
		// TODO - Implementar
		return null;
	}

}