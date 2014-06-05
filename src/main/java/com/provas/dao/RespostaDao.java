package com.provas.dao;

import com.provas.criteria.RespostaCriteria;
import com.provas.entity.Resposta;

import br.com.examserver.fwk.criteria.ServerQuery;
import br.com.examserver.fwk.dao.CrudBaseDAO;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class RespostaDao extends CrudBaseDAO<Resposta, RespostaCriteria> {

	@Override
	protected ServerQuery createWhereClause(final String entityAlias,
			final RespostaCriteria criteria) {
		final ServerQuery query = new ServerQuery();

		if (criteria.getAplicacao() != null && criteria.getAplicacao().getId() != null) {
			query.addWhereClause("AND", "UPPER(" + entityAlias + ".aplicacao.id) LIKE :id");
			query.addParameter("id", criteria.getAplicacao().getId());
		}

		return query;
	}


}
