package com.provas.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.provas.criteria.UsuarioCriteria;
import com.provas.entity.Alternativa;
import com.provas.entity.Aplicacao;
import com.provas.entity.ProvaQuestao;
import com.provas.entity.Questao;
import com.provas.entity.Resposta;
import com.provas.entity.Usuario;
import com.provas.enumerator.StatusProva;
import com.provas.enumerator.TipoQuestao;
import com.provas.enumerator.TipoUsuario;
import com.provas.service.AplicacaoService;
import com.provas.service.ProvaQuestaoService;
import com.provas.service.RespostaService;
import com.provas.service.UsuarioService;
import com.provas.util.CommonHelper;
import com.provas.util.EncryptionUtils;
import com.provas.util.MessageUtils;

import br.com.examserver.fwk.view.BaseBean;

/**
*
* TODO - Preencher javaDoc
*
* @author tiagok
*/
@ViewScoped
@ManagedBean(name = "iniciarProvaBean")
public class IniciarProvaBean extends BaseBean {

	/**
	 *
	 */
	private static final long serialVersionUID = 1452537615041009689L;

	private Aplicacao aplicacao;
	private StatusProva status;
	private TipoUsuario usuario;
	private List<Aplicacao> aplicacoes;
	private List<Questao> questoesTexto;

	// Services
	@EJB
	private static AplicacaoService aplicacaoService;

	@EJB
	private static RespostaService respostaService;

	@EJB
	private static ProvaQuestaoService provaQuestaoService;

	@EJB
	private static UsuarioService usuarioService;

	/**
	 *
	 * Construtor
	 *
	 */
	public IniciarProvaBean() {

	}

	@PostConstruct
	private void init() {
		String data = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("data");
		String role = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("role");
		String aplicacaoId = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");
		if (data != null && !data.isEmpty()) {
			data = EncryptionUtils.decriptar(data);
			final String[] dados = data.split(";");
			aplicacaoId = dados[0];
			role = dados[1];
		}

		if (role != null && !role.isEmpty()) {
			usuario = TipoUsuario.valueOf(role);

			if (aplicacaoId != null && !aplicacaoId.isEmpty()) {
				loadProva(aplicacaoId);
				if (aplicacao != null) {
					status = aplicacao.getStatus();
					if (status.equals(StatusProva.ABERTA)
							&& usuario.equals(TipoUsuario.USUARIO)) {
						randomizarQuestoes();
						iniciarAlternativas();
					}
					if (status.equals(StatusProva.CONCLUIDA)
							&& usuario.equals(TipoUsuario.CORRETOR)) {
						iniciarCamposCorrecao();
					}
					if (status.equals(StatusProva.CORRIGIDA)
							&& usuario.equals(TipoUsuario.ADMIN)) {
						iniciarCamposVisualizar();
					}
				} else {
					status = null;
				}
			} else {
				StatusProva statusAplicacoes = null;
				if (usuario.equals(TipoUsuario.USUARIO)) {
					statusAplicacoes = StatusProva.ABERTA;
				}
				if (usuario.equals(TipoUsuario.CORRETOR)) {
					statusAplicacoes = StatusProva.CONCLUIDA;
				}
				aplicacoes = aplicacaoService
						.listarAplicacaoPorStatus(statusAplicacoes, getUsuarioLogado());
			}
		} else {
			usuario = null;
		}
	}

	private Usuario getUsuarioLogado() {
		final HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		final String nomeUsuarioLogado = request.getUserPrincipal().getName();

		final UsuarioCriteria criteria = new UsuarioCriteria();
		criteria.setUsername(nomeUsuarioLogado);

		return usuarioService.findBy(criteria).get(0);
	}

	private void randomizarQuestoes() {
//		Collections.shuffle(aplicacao.getProva().getQuestoes());
	}

	private void iniciarCamposVisualizar() {
		if (aplicacao != null) {
			final List<Resposta> respostas = respostaService
					.listarRespostasPorAplicacao(aplicacao);
			final List<Questao> questoes = CommonHelper.getQuestoes(aplicacao.getProva()
					.getQuestoes());
			for (final Questao questao : questoes) {
				questao.setCorreta(true);
				final List<Alternativa> alternativasPorQuestao = buscaAlternativasPorQuestao(
						respostas, questao);
				ajustarRespostasQuestao(alternativasPorQuestao, questao, respostas);
				if (!questaoCorreta(alternativasPorQuestao, respostas, questao)) {
					questao.setCorreta(false);
					if (questao.getTipo().equals(TipoQuestao.TEXTO)) {
						questao.setAlternativaTextoCorreta(questao.getAlternativas().get(0));
					}
				}
			}
		}

	}

	private void ajustarRespostasQuestao(final List<Alternativa> alternativas, final Questao questao, final List<Resposta> respostas) {
		if (questao.getTipo().equals(TipoQuestao.MULTIPLA_ESCOLHA)) {
			questao.setAlternativasEscolhidas(alternativas);
		}
		if (questao.getTipo().equals(TipoQuestao.ALTERNATIVA)) {
			questao.setAlternativaEscolhida(alternativas.get(0));
		}

		if (questao.getTipo().equals(TipoQuestao.TEXTO)) {
			questao.setRespostaTexto(buscaRespostaPorQuestao(respostas, questao));
		}
	}

	private void iniciarCamposCorrecao() {
		if (aplicacao != null) {
			final List<Resposta> respostas = respostaService
					.listarRespostasPorAplicacao(aplicacao);
			questoesTexto = getQuestoesTexto(aplicacao.getProva().getQuestoes());
			for (final Questao questao : questoesTexto) {
				questao.setAlternativaTextoCorreta(questao.getAlternativas()
						.get(0));
				final Resposta resposta = buscaRespostaPorQuestao(respostas, questao);
				resposta.setCorreta(null);
				questao.setRespostaTexto(resposta);
			}
		}
	}

	private List<Questao> getQuestoesTexto(final List<ProvaQuestao> provaQuestoes) {
		final List<Questao> questoesTexto = new ArrayList<Questao>();
		for (final ProvaQuestao provaQuestao : provaQuestoes) {
			if (provaQuestao.getQuestao().getTipo().equals(TipoQuestao.TEXTO)) {
				questoesTexto.add(provaQuestao.getQuestao());
			}
		}
		return questoesTexto;
	}

	private Resposta buscaRespostaPorQuestao(final List<Resposta> respostas,
			final Questao questao) {
		Resposta respostaQuestao = null;
		for (final Resposta resposta : respostas) {
			if (resposta.getAlternativa().getQuestao().equals(questao)) {
				respostaQuestao = resposta;
			}
		}
		return respostaQuestao;
	}

	private void iniciarAlternativas() {
		if (aplicacao != null) {
			final List<Questao> questoes = CommonHelper.getQuestoes(aplicacao.getProva()
					.getQuestoes());
			for (final Questao questao : questoes) {
				questao.setAlternativasEscolhidas(new ArrayList<Alternativa>());
				questao.setAlternativaEscolhida(null);
				questao.setAlternativaTexto(null);
			}
		}
	}

	private void loadProva(final String aplicacaoId) {
		if (aplicacaoId != null && !aplicacaoId.isEmpty()) {
			aplicacao = aplicacaoService.findById(Long.valueOf(aplicacaoId));
//			// TODO - Por algum motivo, as questões não são carregadas algumas
//			// vezes
//			if (aplicacao.getProva().getQuestoes() == null
//					|| aplicacao.getProva().getQuestoes().isEmpty()) {
//				aplicacao.getProva().setQuestoes(
//						provaQuestaoService
//								.listarProvaQuestaoPorProva(aplicacao
//										.getProva()));
//			}
			if (aplicacao != null) {
				ajustarNumeroDasQuestoes();
			}
		}
	}

	private void ajustarNumeroDasQuestoes() {
		final List<Questao> questoes = CommonHelper.getQuestoes(aplicacao.getProva()
				.getQuestoes());
		int numeroDaQuestao = 1;
		for (final Questao questao : questoes) {
			questao.setNumeroDaQuestao(numeroDaQuestao);
			numeroDaQuestao++;
		}
	}

	public String salvarProva() {
		final List<Questao> questoes = CommonHelper.getQuestoes(aplicacao.getProva()
				.getQuestoes());
		boolean necessitaCorrecao = false;

		for (final Questao questao : questoes) {
			// Questao questao = provaQuestao.getQuestao();
			if (questao.getTipo().equals(TipoQuestao.MULTIPLA_ESCOLHA)) {
				salvarQuestaoMultiplaEscolha(questao);
			}
			if (questao.getTipo().equals(TipoQuestao.ALTERNATIVA)) {
				salvarQuestaoAlternativa(questao);
			}

			if (questao.getTipo().equals(TipoQuestao.TEXTO)) {
				necessitaCorrecao = true;
				salvarQuestaoTexto(questao);
			}
		}
		if (necessitaCorrecao) {
			aplicacao.setStatus(StatusProva.CONCLUIDA);
		} else {
			aplicacao.setStatus(StatusProva.CORRIGIDA);
			corrigirQuestoes();
		}
		aplicacao.setData(Calendar.getInstance().getTime());

		aplicacaoService.saveOrUpdate(aplicacao);
		MessageUtils.addInfoMessage("Prova completa! Aguarde pela correção!");
		return "iniciar.xhtml?faces-redirect=true&role=USUARIO";
	}

	private void salvarQuestaoTexto(final Questao questao) {
		if (questao.getAlternativas() != null
				&& !questao.getAlternativas().isEmpty()) {
			final Alternativa alternativa = questao.getAlternativas().get(0);
			final Resposta resposta = new Resposta();
			resposta.setAlternativa(alternativa);
			resposta.setAplicacao(aplicacao);
			resposta.setConteudo(questao.getAlternativaTexto());
			resposta.setCorreta(false);
			respostaService.saveOrUpdate(resposta);
		}
	}

	private void salvarQuestaoAlternativa(final Questao questao) {
		final Alternativa alternativa = questao.getAlternativaEscolhida();
		if (alternativa != null) {
			final Resposta resposta = new Resposta();
			resposta.setAlternativa(alternativa);
			resposta.setAplicacao(aplicacao);
			resposta.setCorreta(false);
			respostaService.saveOrUpdate(resposta);
		}
	}

	private void salvarQuestaoMultiplaEscolha(final Questao questao) {
		final List<Alternativa> alternativas = questao.getAlternativasEscolhidas();
		if (alternativas != null && !alternativas.isEmpty()) {
			for (final Alternativa alternativa : alternativas) {
				final Resposta resposta = new Resposta();
				resposta.setAlternativa(alternativa);
				resposta.setAplicacao(aplicacao);
				resposta.setCorreta(false);
				respostaService.saveOrUpdate(resposta);
			}
		}
	}

	private void corrigirQuestoes() {
		final double resultado = calcularResultado(aplicacao);
		aplicacao.setResultado(resultado);
		MessageUtils.addInfoMessage("Prova corrigida!");
	}

	/**
	 *
	 * TODO - preencher javaDoc
	 *
	 * @return
	 */
	public String corrigirProva() {
		aplicacao.setStatus(StatusProva.CORRIGIDA);
		for (final Questao questao : questoesTexto) {
			if (questao.getTipo().equals((TipoQuestao.TEXTO))) {
				final Resposta resposta = questao.getRespostaTexto();
				respostaService.saveOrUpdate(resposta);
			}
		}
		corrigirQuestoes();
		aplicacao.setCorretor(getUsuarioLogado());
		aplicacaoService.saveOrUpdate(aplicacao);
		return "iniciar.xhtml?faces-redirect=true&role=CORRETOR";
	}

	private double calcularResultado(final Aplicacao apl) {
		final List<Resposta> respostas = respostaService
				.listarRespostasPorAplicacao(apl);
		final List<Questao> questoes = CommonHelper.getQuestoes(apl.getProva()
				.getQuestoes());
		double resultado = 0;
		double pesoTotal = 0;
		for (final Questao questao : questoes) {
			pesoTotal += questao.getPeso();
			final List<Alternativa> alternativasPorQuestao = buscaAlternativasPorQuestao(
					respostas, questao);
			if (alternativasPorQuestao != null
					&& !alternativasPorQuestao.isEmpty()) {
				if (questaoCorreta(alternativasPorQuestao, respostas, questao)) {
					resultado += questao.getPeso();
				}
			}
		}
		return (resultado / pesoTotal) * 10.0;
	}

	private boolean questaoCorreta(final List<Alternativa> alternativas, final List<Resposta> respostas, final Questao questao) {
		if (questao.getTipo().equals(TipoQuestao.TEXTO)) {
			final Resposta respostaTexto = buscaRespostasPorQuestao(
					respostas, questao);
			if (respostaTexto.getCorreta()) {
				return true;
			}
		}
		if (questao.getTipo().equals(TipoQuestao.ALTERNATIVA)) {
			final Alternativa alternativa = alternativas.get(0);
			if (alternativa.equals(getAlternativasCorretas(questao)
					.get(0))) {
				return true;
			}
		}
		if (questao.getTipo().equals(TipoQuestao.MULTIPLA_ESCOLHA)) {
			if (Arrays.equals(alternativas.toArray(),
					getAlternativasCorretas(questao).toArray())) {
				return true;
			}
		}
		return false;
	}

	private Resposta buscaRespostasPorQuestao(final List<Resposta> respostas,
			final Questao questao) {
		Resposta respostaQuestao = null;
		for (final Resposta resposta : respostas) {
			if (resposta.getAlternativa().getQuestao().equals(questao)) {
				respostaQuestao = resposta;
			}
		}
		return respostaQuestao;
	}

	private List<Alternativa> getAlternativasCorretas(final Questao questao) {
		final List<Alternativa> alternativas = new ArrayList<Alternativa>();
		for (final Alternativa alternativa : questao.getAlternativas()) {
			if (alternativa.getCorreta()) {
				alternativas.add(alternativa);
			}
		}
		return alternativas;
	}

	private List<Alternativa> buscaAlternativasPorQuestao(
			final List<Resposta> respostas, final Questao questao) {
		final List<Alternativa> alternativasPorQuestao = new ArrayList<Alternativa>();
		for (final Resposta resposta : respostas) {
			if (resposta.getAlternativa().getQuestao().equals(questao)) {
				alternativasPorQuestao.add(resposta.getAlternativa());
			}
		}
		return alternativasPorQuestao;
	}

	/**
	 * Getters and Setters
	 */

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(final Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	public StatusProva getStatus() {
		return status;
	}

	public void setStatus(final StatusProva status) {
		this.status = status;
	}

	public TipoUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(final TipoUsuario usuario) {
		this.usuario = usuario;
	}

	public List<Aplicacao> getAplicacoes() {
		return aplicacoes;
	}

	public void setAplicacoes(final List<Aplicacao> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	public List<Questao> getQuestoesTexto() {
		return questoesTexto;
	}

	public void setQuestoesTexto(final List<Questao> questoesTexto) {
		this.questoesTexto = questoesTexto;
	}

}
