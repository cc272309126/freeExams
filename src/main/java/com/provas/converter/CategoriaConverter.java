package com.provas.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.provas.entity.Categoria;
import com.provas.service.CategoriaService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@FacesConverter(value = "categoriaConverter", forClass = Categoria.class)
@ManagedBean
public class CategoriaConverter implements Converter {

	@EJB
	private CategoriaService categoriaService;

	@Override
	public String getAsString(final FacesContext context,
			final UIComponent component, final Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Categoria) value).getId());
		}
	}

	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String value) {
		if (value == null || value.equals("")) {
			return null;
		} else {
			return categoriaService.findById(Long.valueOf(value));
		}
	}

}
