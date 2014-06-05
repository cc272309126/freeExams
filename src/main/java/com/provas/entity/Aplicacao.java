package com.provas.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.provas.enumerator.StatusProva;
import com.provas.enumerator.TipoUsuario;
import com.provas.util.CommonHelper;
import com.provas.util.EncryptionUtils;

import br.com.examserver.fwk.entity.BaseEntity;

@Entity
@Table(name = "aplicacao")
public class Aplicacao extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "prova_id")
	private Prova prova;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Enumerated(EnumType.STRING)
	private StatusProva status;

	@OneToMany(mappedBy = "aplicacao", cascade = CascadeType.ALL)
	private List<Resposta> respostas;

	@ManyToOne
	@JoinColumn(name = "corretor_id")
	private Usuario corretor;
	private double resultado;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(final Prova prova) {
		this.prova = prova;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(final Date data) {
		this.data = data;
	}

	public StatusProva getStatus() {
		return status;
	}

	public void setStatus(final StatusProva status) {
		this.status = status;
	}

	public Usuario getCorretor() {
		return corretor;
	}

	public void setCorretor(final Usuario corretor) {
		this.corretor = corretor;
	}

	public double getResultado() {
		return CommonHelper.formatarResultado(resultado);
	}

	public void setResultado(final double resultado) {
		this.resultado = resultado;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(final List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public String getUrl() {
		final StringBuilder parametros = new StringBuilder();
		parametros.append(id);
		parametros.append(";");
		if (status.equals(StatusProva.ABERTA)) {
			parametros.append(TipoUsuario.USUARIO);
		}
		if (status.equals(StatusProva.CONCLUIDA)) {
			parametros.append(TipoUsuario.CORRETOR);
		}
		if (status.equals(StatusProva.CORRIGIDA)) {
			parametros.append(TipoUsuario.ADMIN);
		}
		return EncryptionUtils.encriptar(parametros.toString());
	}

}
