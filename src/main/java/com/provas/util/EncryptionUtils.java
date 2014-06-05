package com.provas.util;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class EncryptionUtils {

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param parameters
	 * @return
	 */
	public static String encriptar(final String parameters) {
		final byte[] encoded = Base64.encodeBase64(parameters.getBytes());
		return new String(encoded);
		// return "";
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param url
	 * @return
	 */
	public static String decriptar(final String url) {
		final byte[] decoded = Base64.decodeBase64(url.getBytes());
		return new String(decoded);
		// return "";
	}

}
