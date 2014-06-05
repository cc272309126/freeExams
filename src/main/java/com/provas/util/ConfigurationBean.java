package com.provas.util;

import javax.enterprise.context.ApplicationScoped;

import br.com.examserver.fwk.configuration.ApplicationConfiguration;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@ApplicationScoped
public class ConfigurationBean extends ApplicationConfiguration {

	/**
	 *
	 */
	private static final long serialVersionUID = 3524233904232756950L;

	private static String SIGLA_SISTEMA = "Provas";

	@Override
	public String getSystemId() {
		return SIGLA_SISTEMA;
	}

}