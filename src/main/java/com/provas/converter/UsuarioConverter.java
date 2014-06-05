package com.provas.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.provas.entity.Usuario;
import com.provas.service.UsuarioService;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@FacesConverter(value = "usuarioConverter", forClass = Usuario.class)
@ManagedBean
public class UsuarioConverter implements Converter {

	@EJB
	private UsuarioService usuarioService;

	@Override
	public String getAsString(final FacesContext context, final UIComponent component,
			final Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Usuario) value).getId());
		}
	}

	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component,
			final String value) {
		if (value == null || value.equals("")) {
			return null;
		} else {
			return usuarioService.findById(Long.valueOf(value));
		}
	}

}
