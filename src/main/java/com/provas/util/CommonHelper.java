package com.provas.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.provas.entity.ProvaQuestao;
import com.provas.entity.Questao;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
public class CommonHelper {

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @param provaQuestoes
	 * @return
	 */
	public static List<Questao> getQuestoes(final List<ProvaQuestao> provaQuestoes) {
		final List<Questao> questoes = new ArrayList<Questao>();
		for (final ProvaQuestao provaQuestao : provaQuestoes) {
			provaQuestao.getQuestao().setPeso(provaQuestao.getPeso());
			questoes.add(provaQuestao.getQuestao());
		}
		return questoes;
	}

	public static double formatarResultado(final double resultado) {
		final DecimalFormat decimalFormat = new DecimalFormat("#.00");
		return Double.valueOf(decimalFormat.format(resultado));
	}

}
