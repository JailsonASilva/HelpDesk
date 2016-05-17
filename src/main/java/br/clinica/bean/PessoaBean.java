package br.clinica.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.primefaces.context.RequestContext;

import br.clinica.dao.PessoaDAO;
import br.clinica.dao.PessoaFisicaDAO;
import br.clinica.domain.Cidade;
import br.clinica.domain.EstadoCivil;
import br.clinica.domain.Pessoa;
import br.clinica.domain.PessoaFisica;
import br.clinica.domain.PessoaJuridica;
import br.clinica.domain.Profissao;
import br.clinica.domain.Ramo;
import br.clinica.util.HibernateUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	private List<Pessoa> pessoas;
	private List<PessoaFisica> pessoaFisicaList;
	private List<Profissao> profissoes;
	private List<EstadoCivil> estadoCivis;
	private List<Cidade> cidades;
	private List<Pessoa> pais;
	private List<Pessoa> maes;
	private List<Pessoa> conjuges;
	private List<Ramo> ramos;

	private FacesMessage message;
	private String busca;

	private Session sessao;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public List<Profissao> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	public List<EstadoCivil> getEstadoCivis() {
		return estadoCivis;
	}

	public void setEstadoCivis(List<EstadoCivil> estadoCivis) {
		this.estadoCivis = estadoCivis;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Pessoa> getPais() {
		return pais;
	}

	public void setPais(List<Pessoa> pais) {
		this.pais = pais;
	}

	public List<Pessoa> getMaes() {
		return maes;
	}

	public void setMaes(List<Pessoa> maes) {
		this.maes = maes;
	}

	public List<Pessoa> getConjuges() {
		return conjuges;
	}

	public void setConjuges(List<Pessoa> conjuges) {
		this.conjuges = conjuges;
	}

	public List<Ramo> getRamos() {
		return ramos;
	}

	public void setRamos(List<Ramo> ramos) {
		this.ramos = ramos;
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public List<PessoaFisica> getPessoaFisicaList() {
		return pessoaFisicaList;
	}

	public void setPessoaFisicaList(List<PessoaFisica> pessoaFisicaList) {
		this.pessoaFisicaList = pessoaFisicaList;
	}

	public void pesquisar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.pesquisar(busca);

			if (pessoas.isEmpty() == true) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Nenhum Registro foi Encontrado! Por favor Tente Novamente.", "Registro não Encontrado!");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Pesquisar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void novo() {

		pessoa = new Pessoa();
		pessoa.setDataCadastro(new Date());

		pessoaFisica = new PessoaFisica();

		pessoaJuridica = new PessoaJuridica();
	}

	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();

			if (pessoa.getCodigo() == null) {
				pessoa.setCodigo(pessoaDAO.proximoID());
			}

			pessoaDAO.merge(pessoa);

			pessoaFisica.setPessoa(pessoa);
			// pessoaJuridica.setPessoa(pessoa);

			PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
			pessoaFisicaDAO.merge(pessoaFisica);

			// PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
			// pessoaJuridicaDAO.merge(pessoaJuridica);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Salvo com Sucesso!",
					"Registro: " + pessoa.getCodigo());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			pessoa = new Pessoa();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionado");

			PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
			pessoaFisica = pessoaFisicaDAO.dadosPessoaFisica(pessoa.getCodigo());

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + pessoa.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro!",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void abrirTabelas() {
		try {
			if (sessao.isOpen() == false) {
				sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			}

			Criteria consulta = sessao.createCriteria(EstadoCivil.class);
			consulta.addOrder(Order.asc("nome"));

			estadoCivis = consulta.list();
			
			System.out.println(estadoCivis);

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Abrir as Tabelas!",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		} finally {
			sessao.close();
		}
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
}
