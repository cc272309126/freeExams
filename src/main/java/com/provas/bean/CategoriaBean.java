package com.provas.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;

import com.provas.entity.Categoria;
import com.provas.service.CategoriaService;
import com.provas.util.MessageUtils;

import br.com.examserver.fwk.view.BaseBean;

/**
*
* TODO - Preencher javaDoc
*
* @author tiagok
*/
@ViewScoped
@ManagedBean(name = "categoriaBean")
public class CategoriaBean extends BaseBean {

	/**
	 *
	 */
	private static final long serialVersionUID = 2022064939098781885L;

	private Categoria entity;
	private Categoria categoriaSelecionada;
	private List<Categoria> categorias;

	// Services
	@EJB
	private CategoriaService categoriaService;

	/**
	 *
	 * Construtor
	 *
	 */
	public CategoriaBean() {

	}

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		entity = new Categoria();
		categoriaSelecionada = new Categoria();
		populateCategorias();
	}

	private void populateCategorias() {
		categorias = this.getCategoriaService().findAll();
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void salvarCategoria() {
		try {
			categoriaService.saveOrUpdate(entity);
			MessageUtils.addInfoMessage("Categoria salva com sucesso");
			categorias.add(entity);
			entity = new Categoria();
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Cagoria não pode ser salva");
		}
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 */
	public void removerCategoria() {
		try {
			categoriaService.remove(categoriaSelecionada);
			categorias.remove(categoriaSelecionada);
			MessageUtils.addInfoMessage("Categoria removida com sucesso");
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Categoria não pode ser excluída pois possui questões vinculadas");
		}
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param event
	 */
	public void onCellEdit(final CellEditEvent event) {
		final Categoria categoria = categorias.get(event.getRowIndex());
		try {
			categoriaService.saveOrUpdate(categoria);
			MessageUtils.addInfoMessage("Categoria alterada com sucesso");
		} catch (final Exception e) {
			MessageUtils.addErrorMessage("Categoria não pode ser alterada");
		}
	}

	/**
	 * Getters and Setters
	 */

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(final CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(final List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getEntity() {
		return entity;
	}

	public void setEntity(final Categoria entity) {
		this.entity = entity;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(final Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

}
