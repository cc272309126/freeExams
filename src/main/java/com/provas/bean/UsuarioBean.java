package com.provas.bean;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jboss.security.auth.spi.Util;
import org.primefaces.event.CellEditEvent;

import com.provas.entity.Usuario;
import com.provas.enumerator.TipoUsuario;
import com.provas.service.UsuarioService;
import com.provas.util.MessageUtils;

import br.com.examserver.fwk.view.BaseBean;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean extends BaseBean {

	/**
	 *
	 */
	private static final long serialVersionUID = 7505163780600911397L;

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<TipoUsuario> tipos;
	private Usuario usuarioSelecionado;

	// Services
	@EJB
	private UsuarioService usuarioService;

	/**
	 *
	 * Construtor
	 *
	 */
	public UsuarioBean() {

	}

	@PostConstruct
	private void init() {
		usuario = new Usuario();
		usuarioSelecionado = new Usuario();
		tipos = Arrays.asList(TipoUsuario.values());
		populateCategorias();
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void salvarUsuario() {
		try {
			if (usuario != null) {
				usuario.setPassword(Util.createPasswordHash("SHA-256",
						"BASE64", null, null, usuario.getPassword()));
			}
			usuarioService.saveOrUpdate(usuario);
			usuarios.add(usuario);
			MessageUtils.addInfoMessage("Usuário salvo com sucesso");
			usuario = new Usuario();
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Usuário não pode ser salvo");
		}
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param event
	 */
	public void onCellEdit(final CellEditEvent event) {
		final Usuario usuario = usuarios.get(event.getRowIndex());
		try {
			usuarioService.saveOrUpdate(usuario);
			MessageUtils.addInfoMessage("Usuário alterado com sucesso");
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Usuário não pode ser alterado");
		}
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void removerUsuario() {
		try {
			usuarioService.remove(usuarioSelecionado);
			usuarios.remove(usuarioSelecionado);
			usuario = new Usuario();
			MessageUtils.addInfoMessage("Usuário removido com sucesso");
		} catch (final Exception e) {
			MessageUtils
					.addErrorMessage("Usuário não pode ser excluído pois possui dependências vinculadas");
		}
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void populateCategorias() {
		usuarios = usuarioService.findAll();
	}

	/**
	 * Getters and Setters
	 */

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

	public List<TipoUsuario> getTipos() {
		return tipos;
	}

	public void setTipos(final List<TipoUsuario> tipos) {
		this.tipos = tipos;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(final List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(final Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
