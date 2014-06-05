package com.provas.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class MessageUtils {

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param message
	 */
	public static void addInfoMessage(final String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param message
	 */
	public static void addWarningMessage(final String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param message
	 */
	public static void addErrorMessage(final String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

}
