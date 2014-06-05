package com.provas.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;
import org.primefaces.model.DualListModel;

import com.provas.entity.Categoria;
import com.provas.entity.Prova;
import com.provas.entity.ProvaQuestao;
import com.provas.entity.Questao;
import com.provas.service.CategoriaService;
import com.provas.service.ProvaQuestaoService;
import com.provas.service.ProvaService;
import com.provas.service.QuestaoService;
import com.provas.util.MessageUtils;

import br.com.examserver.fwk.view.BaseBean;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@ViewScoped
@ManagedBean(name = "provaBean")
public class ProvaBean extends BaseBean {

	private Prova prova;
	private DualListModel<Questao> questoesModel;
	private List<Questao> questoes;
	private Prova provaSelecionada;
	private List<Prova> provas;
	private Categoria categoria;
	private List<Categoria> categorias;

	// Services
	@EJB
	private QuestaoService questaoService;

	@EJB
	private ProvaService provaService;

	@EJB
	private ProvaQuestaoService provaQuestaoService;

	@EJB
	private CategoriaService categoriaService;

	/**
	 *
	 * Construtor
	 *
	 */
	public ProvaBean() {

	}

	@PostConstruct
	private void init() {
		prova = new Prova();
		populateCategorias();
		populateQuestoes();
		populateProvas();
	}

	private void populateCategorias() {
		try {
			categorias = categoriaService.findAll();
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("As categorias não puderam ser carregadas, motivo: " + e.getCause());
		}
	}

	private void populateProvas() {
		try {
			provas = provaService.findAll();
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("As provas não puderam ser carregadas, motivo: " + e.getCause());
		}
	}

	private void populateQuestoes() {
		if (questoes == null) {
			try {
				questoes = questaoService.findAll();
			} catch (final Exception e) {
				MessageUtils.addErrorMessage("As questões não puderam ser carregadas, motivo: " + e.getCause());
			}
		}
		questoesModel = new DualListModel<Questao>(questoes,
				new ArrayList<Questao>());
	}

	public void salvarProva() {
		final List<Questao> questoesProva = questoesModel.getTarget();
		try {
			provaService.saveOrUpdate(prova);
			for (final Questao questao : questoesProva) {
				final ProvaQuestao provaQuestao = new ProvaQuestao();
				provaQuestao.setProva(prova);
				provaQuestao.setQuestao(questao);
				provaQuestao.setPeso(questao.getPeso());
				provaQuestaoService.saveOrUpdate(provaQuestao);
			}
			provas.add(prova);
			prova = new Prova();
			MessageUtils.addInfoMessage("Prova salva com sucesso");
			populateQuestoes();
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Prova não pode ser salva");
		}
	}

	public void removerProva() {
		try {
			provaService.remove(provaSelecionada);
			provas.remove(provaSelecionada);
			MessageUtils.addInfoMessage("Prova removida com sucesso");
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Prova não pode ser excluída pois possui respostas vinculadas");
		}
	}

	public void onCellEdit(final CellEditEvent event) {
		final Prova editada = provas.get(event.getRowIndex());
		try {
			provaService.saveOrUpdate(editada);
			MessageUtils.addInfoMessage("Prova editada com sucesso");
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Prova não pode ser alterada");
		}
	}

	public void changeCategoria() {
		List<Questao> questoesFiltradas;
		if (categoria != null) {
			questoesFiltradas = questaoService
					.listarQuestaoPorCategoria(categoria);
		} else {
			questoesFiltradas = questoes;
		}
		questoesModel.setSource(questoesFiltradas);
	}

	// TODO - Por alguma razão o picklist só atualiza o target com uma função
	// transfer...
	public void transfer() {
		updateNumeroDaQuestao();
	}

	private void updateNumeroDaQuestao() {
		// int numeroDaQuestao = 1;
		// for (Questao questao : questoesModel.getTarget()) {
		// questao.setNumeroDaQuestao(numeroDaQuestao);
		// numeroDaQuestao++;
		// }
	}

	/**
	 * Getters and Setters
	 */

	public Prova getProva() {
		return prova;
	}

	public void setProva(final Prova prova) {
		this.prova = prova;
	}

	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(final List<Prova> provas) {
		this.provas = provas;
	}

	public Prova getProvaSelecionada() {
		return provaSelecionada;
	}

	public void setProvaSelecionada(final Prova provaSelecionada) {
		this.provaSelecionada = provaSelecionada;
	}

	public DualListModel<Questao> getQuestoesModel() {
		return questoesModel;
	}

	public void setQuestoesModel(final DualListModel<Questao> questoesModel) {
		this.questoesModel = questoesModel;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(final List<Questao> questoes) {
		this.questoes = questoes;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(final Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(final List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
