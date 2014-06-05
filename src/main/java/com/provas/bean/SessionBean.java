package com.provas.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.provas.criteria.UsuarioCriteria;
import com.provas.entity.Usuario;
import com.provas.enumerator.TipoUsuario;
import com.provas.service.UsuarioService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@ManagedBean
@SessionScoped
public class SessionBean {

	@EJB
	private static UsuarioService usuarioService;

	private Usuario usuarioLogado;

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @return
	 */
	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			final HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();

			if (request != null && request.getUserPrincipal() != null) {
				final String nomeUsuarioLogado = request.getUserPrincipal()
						.getName();

				final UsuarioCriteria criteria = new UsuarioCriteria();
				criteria.setUsername(nomeUsuarioLogado);

				usuarioLogado = usuarioService.findBy(criteria).get(0);
			}
		}
		return usuarioLogado;
	}

	public TipoUsuario getRole() {
		if (usuarioLogado == null) {
			final Usuario usuario = getUsuarioLogado();
			if (usuario == null) {
				return null;
			} else {
				return usuario.getTipo();
			}
		}
		return usuarioLogado.getTipo();
	}

	public boolean isUsuario() {
		return TipoUsuario.USUARIO.equals(getRole()) || isCorretor()
				|| isAdmin();
	}

	public boolean isCorretor() {
		return TipoUsuario.CORRETOR.equals(getRole()) || isAdmin();
	}

	public boolean isAdmin() {
		return TipoUsuario.ADMIN.equals(getRole());
	}

}
