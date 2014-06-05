package com.provas.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.provas.criteria.UsuarioCriteria;
import com.provas.service.UsuarioService;

import br.com.examserver.fwk.view.BaseBean;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@ManagedBean
@RequestScoped
public class LoginBean extends BaseBean {

	private static final long serialVersionUID = 655406693777428188L;

	HttpServletRequest request = (HttpServletRequest) FacesContext
			.getCurrentInstance().getExternalContext().getRequest();
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
			.getExternalContext().getSession(false);

	/**
	 *
	 * Construtor
	 *
	 */
	public LoginBean() {
		// verificar se esse método é necessário.
		if (session != null) {
			session.invalidate();
		}
	}

	@Inject
	private UsuarioService usuarioService;

	private String username;
	private String password;

	private String usuarioLogado;

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String lostPassword() {
		return "lostPassword";
	}

	public String login() {
		try {
			request.login(username, password);
			return "pages/main.xhtml?faces-redirect=true";
		} catch (final ServletException e) {
			FacesContext.getCurrentInstance().addMessage(
					"username",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Os dados estão incorretos.", ""));
			e.printStackTrace();
		}

		return null;
	}

	public String logout() {
		return "logout";
	}

	public String getUsuarioLogado() {
		if (request != null) {
			usuarioLogado = request.getUserPrincipal().getName();

			final UsuarioCriteria criteria = new UsuarioCriteria();
			criteria.setUsername(usuarioLogado);

			return usuarioService.findBy(criteria).get(0).getNome();
		}
		return "failure";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

}
