package com.provas.enumerator;

public enum TipoUsuario {

	ADMIN("Administrador"), CORRETOR("Corretor"), USUARIO("Usuario");

	private String descricao;

	private TipoUsuario() {

	}

	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
