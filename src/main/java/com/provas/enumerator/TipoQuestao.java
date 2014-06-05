package com.provas.enumerator;

public enum TipoQuestao {

	TEXTO("Texto"), ALTERNATIVA("Alternativa"), MULTIPLA_ESCOLHA(
			"M�ltipla Escolha");

	private String descricao;

	private TipoQuestao() {

	}

	private TipoQuestao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
