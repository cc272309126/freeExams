package com.provas.enumerator;

public enum StatusProva {

	ABERTA("Aberta"), CONCLUIDA("Concluída"), CORRIGIDA("Corrigida");

	private String descricao;

	private StatusProva() {

	}

	private StatusProva(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
