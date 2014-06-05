package com.provas.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.provas.entity.Alternativa;
import com.provas.service.AlternativaService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@FacesConverter(value = "alternativaConverter", forClass = Alternativa.class)
@ManagedBean
public class AlternativaConverter implements Converter {

	@EJB
	private AlternativaService alternativaService;

	@Override
	public String getAsString(final FacesContext context,
			final UIComponent component, final Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Alternativa) value).getId());
		}
	}

	@Override
	public Object getAsObject(final FacesContext context,
			final UIComponent component, final String value) {
		if (value == null || value.equals("")) {
			return null;
		} else {
			return alternativaService.findById(Long.valueOf(value));
		}
	}

}
