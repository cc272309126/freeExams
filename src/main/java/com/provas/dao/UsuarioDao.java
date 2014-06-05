package com.provas.dao;

import com.provas.criteria.UsuarioCriteria;
import com.provas.entity.Usuario;

import br.com.examserver.fwk.criteria.ServerQuery;
import br.com.examserver.fwk.dao.CrudBaseDAO;
import br.com.examserver.fwk.utils.StringUtils;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class UsuarioDao extends CrudBaseDAO<Usuario, UsuarioCriteria> {

	@Override
	protected ServerQuery createWhereClause(final String entityAlias,
			final UsuarioCriteria criteria) {
		final ServerQuery Query = new ServerQuery();

		if (!StringUtils.isBlank(criteria.getUsername())) {
			Query.addWhereClause("AND", "UPPER(" + entityAlias + ".nome) LIKE :nome");
			Query.addParameter("nome", "%" + criteria.getUsername().toUpperCase() + "%");
		}

		return Query;
	}

}
