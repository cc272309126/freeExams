package com.provas.dao;

import com.provas.criteria.QuestaoCriteria;
import com.provas.entity.Questao;

import br.com.examserver.fwk.criteria.ServerQuery;
import br.com.examserver.fwk.dao.CrudBaseDAO;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class QuestaoDao extends CrudBaseDAO<Questao, QuestaoCriteria> {

	@Override
	protected ServerQuery createWhereClause(final String entityAlias,
			final QuestaoCriteria criteria) {
		final ServerQuery Query = new ServerQuery();

		if (criteria.getCategoria() != null && criteria.getCategoria().getId() != null) {
			Query.addWhereClause("AND", "UPPER(" + entityAlias + ".categoria.id) LIKE :id");
			Query.addParameter("id", criteria.getCategoria().getId());
		}

		return Query;
	}

}
