package com.provas.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.provas.criteria.UsuarioCriteria;
import com.provas.dao.UsuarioDao;
import com.provas.entity.Usuario;

import br.com.examserver.fwk.dao.CrudBaseDAO;
import br.com.examserver.fwk.service.CrudBaseService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@Stateless
public class UsuarioService extends CrudBaseService<Usuario, UsuarioCriteria> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5181752329501550142L;

	@Inject
	private UsuarioDao dao;

	@Override
	protected CrudBaseDAO<Usuario, UsuarioCriteria> getDAO() {
		return dao;
	}

}
