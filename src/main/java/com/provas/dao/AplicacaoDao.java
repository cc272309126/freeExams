package com.provas.dao;

import com.provas.criteria.AplicacaoCriteria;
import com.provas.entity.Aplicacao;

import br.com.examserver.fwk.criteria.ServerQuery;
import br.com.examserver.fwk.dao.CrudBaseDAO;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class AplicacaoDao extends CrudBaseDAO<Aplicacao, AplicacaoCriteria> {

	@Override
	protected ServerQuery createWhereClause(final String entityAlias,
			final AplicacaoCriteria criteria) {
		final ServerQuery query = new ServerQuery();

		query.addWhereClause("AND", "UPPER(" + entityAlias + ".status) LIKE :status");
		query.addParameter("status", criteria.getStatus());

		if (criteria.getUsuario() != null && criteria.getUsuario().getId() != null) {
			query.addWhereClause("AND", "UPPER(" + entityAlias + ".usuario.id) LIKE :id");
			query.addParameter("id", criteria.getUsuario().getId());
		}

		return query;
	}

}