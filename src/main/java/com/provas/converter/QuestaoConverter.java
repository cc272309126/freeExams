package com.provas.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.provas.entity.Questao;
import com.provas.service.QuestaoService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@FacesConverter(value = "questaoConverter", forClass = Questao.class)
@ManagedBean
public class QuestaoConverter implements Converter {

	@EJB
	private QuestaoService questaoService;

	@Override
	public String getAsString(final FacesContext context, final UIComponent component,
			final Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Questao) value).getId());
		}
	}

	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component,
			final String value) {
		if (value == null || value.equals("")) {
			return null;
		} else {
			return questaoService.findById(Long.valueOf(value));
		}
	}

}
