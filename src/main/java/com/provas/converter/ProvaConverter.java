package com.provas.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.provas.entity.Prova;
import com.provas.service.ProvaService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@FacesConverter(value = "provaConverter", forClass = ProvaConverter.class)
@ManagedBean
public class ProvaConverter implements Converter {

	@EJB
	private ProvaService provaService;

	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
		// Convert the object to its unique String representation.
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Prova) value).getId());
		}
	}

	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
		if (value == null || value.equals("")) {
			return null;
		} else {
			return provaService.findById(Long.valueOf(value));
		}

	}
}
