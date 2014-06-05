package com.provas.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.provas.entity.Aplicacao;
import com.provas.enumerator.StatusProva;
import com.provas.service.AplicacaoService;
import com.provas.util.MessageUtils;

import br.com.examserver.fwk.view.BaseBean;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@ViewScoped
@ManagedBean(name = "resultadoBean")
public class ResultadoBean extends BaseBean {

	/**
	 *
	 */
	private static final long serialVersionUID = 8761811058055430029L;

	private List<Aplicacao> aplicacoes;
	private List<Aplicacao> aplicacoesTemp;
	private SelectItem[] status;
	private Aplicacao aplicacaoSelecionada;

	// Filtros
	private Date minDate;
	private Date maxDate;

	// Services
	@EJB
	private AplicacaoService aplicacaoService;

	/**
	 *
	 * Construtor
	 *
	 */
	public ResultadoBean() {

	}

	@PostConstruct
	private void init() {
		status = createFilterOptions(StatusProva.values());
		populateAplicacoes();
	}

	private SelectItem[] createFilterOptions(final StatusProva[] statusProvas) {
		final SelectItem[] options = new SelectItem[statusProvas.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < statusProvas.length; i++) {
			options[i + 1] = new SelectItem(statusProvas[i].toString(),
					statusProvas[i].toString());
		}

		return options;
	}

	private void populateAplicacoes() {
		aplicacoes = aplicacaoService.findAll();
		aplicacoesTemp = new ArrayList<Aplicacao>(aplicacoes);
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param event
	 */
	public void filtrarPorData(final SelectEvent event) {
		aplicacoes = getAplicacoesPorData(minDate, maxDate);
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	private List<Aplicacao> getAplicacoesPorData(Date min, Date max) {
		aplicacoes = new ArrayList<Aplicacao>();
		if (min == null) {
			min = new Date(Long.MIN_VALUE);
		}
		if (max == null) {
			max = new Date(Long.MAX_VALUE);
		}
		for (final Aplicacao aplicacao : aplicacoesTemp) {
			if (aplicacao.getData().after(min)
					&& aplicacao.getData().before(max)) {
				aplicacoes.add(aplicacao);
			}
		}

		return aplicacoes;
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void removerResultado() {
		try {
			aplicacaoService.remove(aplicacaoSelecionada);
			aplicacoes.remove(aplicacaoSelecionada);
			populateAplicacoes();
			MessageUtils.addInfoMessage("Aplicacao removida com sucesso");
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Aplicacao não pode ser excluída pois possui respostas vinculadas");
		}
	}

	/**
	 * Getters and Setters
	 */

	public List<Aplicacao> getAplicacoes() {
		return aplicacoes;
	}

	public void setAplicacoes(final List<Aplicacao> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	public SelectItem[] getStatus() {
		return status;
	}

	public void setStatus(final SelectItem[] status) {
		this.status = status;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(final Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(final Date maxDate) {
		this.maxDate = maxDate;
	}

	public List<Aplicacao> getAplicacoesTemp() {
		return aplicacoesTemp;
	}

	public void setAplicacoesTemp(final List<Aplicacao> aplicacoesTemp) {
		this.aplicacoesTemp = aplicacoesTemp;
	}

	public Aplicacao getAplicacaoSelecionada() {
		return aplicacaoSelecionada;
	}

	public void setAplicacaoSelecionada(final Aplicacao aplicacaoSelecionada) {
		this.aplicacaoSelecionada = aplicacaoSelecionada;
	}

}
