package com.provas.dao;

import com.provas.criteria.CategoriaCriteria;
import com.provas.entity.Categoria;

import br.com.examserver.fwk.criteria.sServerQuery;
import br.com.examserver.fwk.dao.CrudBaseDAO;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class CategoriaDao extends CrudBaseDAO<Categoria, CategoriaCriteria> {

	@Override
	protected ServerQuery createWhereClause(final String entityAlias,
			final CategoriaCriteria criteria) {
		// TODO - Implementar
		return null;
	}

}