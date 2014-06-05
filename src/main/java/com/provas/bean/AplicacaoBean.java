package com.provas.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.provas.entity.Aplicacao;
import com.provas.entity.Prova;
import com.provas.entity.Usuario;
import com.provas.enumerator.StatusProva;
import com.provas.service.AplicacaoService;
import com.provas.service.ProvaService;
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
@ManagedBean(name = "aplicacaoBean")
public class AplicacaoBean extends BaseBean {

	/**
	 *
	 */
	private static final long serialVersionUID = 2415953549406402709L;

	private Aplicacao aplicacao;
	private Aplicacao aplicacaoSelecionada;
	private List<Prova> provas;
	private List<Usuario> usuarios;
	private List<Aplicacao> aplicacoes;

	// Services
	@EJB
	private ProvaService provaService;

	@EJB
	private UsuarioService usuarioService;

	@EJB
	private AplicacaoService aplicacaoService;

	/**
	 *
	 * Construtor
	 *
	 */
	public AplicacaoBean() {

	}

	@PostConstruct
	private void init() {
		aplicacao = new Aplicacao();
		populateProvas();
		populateAlicacoes();
		populateUsuarios();
	}

	private void populateAlicacoes() {
		aplicacoes = aplicacaoService.findAll();
	}

	private void populateUsuarios() {
		usuarios = usuarioService.findAll();
	}

	private void populateProvas() {
		provas = provaService.findAll();
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void salvarAplicacao() {
		try {
			aplicacao.setStatus(StatusProva.ABERTA);
			aplicacoes.add(aplicacao);
			aplicacaoService.saveOrUpdate(aplicacao);
			MessageUtils.addInfoMessage("Aplicacao salva com sucesso");
			aplicacao = new Aplicacao();
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Aplicação não pode ser salva");
		}
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void removerAplicacao() {
		try {
			aplicacaoService.remove(getAplicacaoSelecionada());
			aplicacoes.remove(aplicacaoSelecionada);
			MessageUtils.addInfoMessage("Aplicação removida com sucesso");
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Aplicação não pode ser excluída pois possui respostas vinculadas");
		}
	}


	/**
	 * Getters and Setters
	 */

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(final Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(final List<Prova> provas) {
		this.provas = provas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(final List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Aplicacao> getAplicacoes() {
		return aplicacoes;
	}

	public void setAplicacoes(final List<Aplicacao> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	public Aplicacao getAplicacaoSelecionada() {
		return aplicacaoSelecionada;
	}

	public void setAplicacaoSelecionada(final Aplicacao aplicacaoSelecionada) {
		this.aplicacaoSelecionada = aplicacaoSelecionada;
	}

}
