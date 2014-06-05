package com.provas.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;

import com.provas.entity.Alternativa;
import com.provas.entity.Categoria;
import com.provas.entity.Questao;
import com.provas.enumerator.TipoQuestao;
import com.provas.service.CategoriaService;
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
@ManagedBean(name = "questaoBean")
public class QuestaoBean extends BaseBean {

	/**
	 *
	 */
	private static final long serialVersionUID = -4551279656870836935L;

	private Questao questao;
	private List<TipoQuestao> tipos;
	private Alternativa alternativa;
	private List<Categoria> categorias;
	private List<Questao> questoes;
	private Questao questaoSelecionada;
	private Alternativa alternativaSelecionada;

	// Services
	@EJB
	private CategoriaService categoriaService;

	@EJB
	private QuestaoService questaoService;

	/**
	 *
	 * Construtor
	 *
	 */
	public QuestaoBean() {

	}

	@PostConstruct
	private void init() {
		questao = new Questao();
		questao.setAlternativas(new ArrayList<Alternativa>());
		alternativa = new Alternativa();
		tipos = Arrays.asList(TipoQuestao.values());
		populaQuestoes();
		populateCategorias();
	}

	private void populaQuestoes() {
		questoes = questaoService.findAll();

	}

	private void populateCategorias() {
		categorias = categoriaService.findAll();
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void salvarAlternativa() {
		questao.getAlternativas().add(alternativa);
		alternativa.setQuestao(questao);
		alternativa = new Alternativa();
		MessageUtils.addInfoMessage("Alternativa criada com sucesso");
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void salvarQuestao() {
		try {
			if (isValid(questao)) {
				questaoService.saveOrUpdate(questao);
				questoes.add(questao);
				MessageUtils.addInfoMessage("Questao salva com sucesso");
				questao = new Questao();
				questao.setAlternativas(new ArrayList<Alternativa>());
			}
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Questao não pode ser salvo");
		}
	}

	private boolean isValid(final Questao questaoSalva) {
		if (questaoSalva != null) {
			if (questaoSalva.getAlternativas() == null
					|| questaoSalva.getAlternativas().isEmpty()) {
				MessageUtils
						.addErrorMessage("Você não pode salvar uma questao sem alternativas");
				return false;
			}
			if (countCorretas(questaoSalva) == 0) {
				MessageUtils
						.addErrorMessage("Você não pode salvar uma questao sem alternativa correta");
				return false;
			}
			if (questaoSalva.getTipo().equals(TipoQuestao.TEXTO)
					&& questaoSalva.getAlternativas().size() > 1) {
				MessageUtils
						.addErrorMessage("Questões texto só podem conter uma alternativa");
				return false;
			}
			if (questaoSalva.getTipo().equals(TipoQuestao.ALTERNATIVA)
					&& countCorretas(questaoSalva) > 1) {
				MessageUtils
						.addErrorMessage("Questões alternativa só podem conter uma alternativa correta");
				return false;
			}
		}
		return true;
	}

	private int countCorretas(final Questao questaoSalva) {
		final List<Alternativa> alternativas = questaoSalva.getAlternativas();
		int containsCorreta = 0;
		for (final Alternativa alternativaQuestao : alternativas) {
			if (alternativaQuestao.getCorreta()) {
				containsCorreta++;
			}
		}
		return containsCorreta;
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param event
	 */
	public void onCellEdit(final CellEditEvent event) {
		final Questao questao = getQuestoes().get(event.getRowIndex());
		try {
			questaoService.saveOrUpdate(questao);
			MessageUtils.addInfoMessage("Questão alterada com sucesso");
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Questão não pode ser alterada");
		}
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void removerQuestao() {
		try {
			questaoService.remove(getQuestaoSelecionada());
			questoes.remove(questaoSelecionada);
			MessageUtils.addInfoMessage("Questão removida com sucesso");
		} catch (final Exception e) {
			MessageUtils
					.addErrorMessage("Questão não pode ser excluída pois possui respostas vinculadas");
		}
	}

	public void removerAlternativa() {
		questao.getAlternativas().remove(alternativaSelecionada);
	}

	/**
	 * Getters and Setters
	 */

	public List<TipoQuestao> getTipos() {
		return tipos;
	}

	public void setTipos(final List<TipoQuestao> tipos) {
		this.tipos = tipos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(final List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(final Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(final Questao questao) {
		this.questao = questao;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(final List<Questao> questoes) {
		this.questoes = questoes;
	}

	public Questao getQuestaoSelecionada() {
		return questaoSelecionada;
	}

	public void setQuestaoSelecionada(final Questao questaoSelecionada) {
		this.questaoSelecionada = questaoSelecionada;
	}

	public Alternativa getAlternativaSelecionada() {
		return alternativaSelecionada;
	}

	public void setAlternativaSelecionada(final Alternativa alternativaSelecionada) {
		this.alternativaSelecionada = alternativaSelecionada;
	}

}
