package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.dao.CargoDAO;
import br.com.projeto.dao.ColaboradorDAO;
import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.EstabilidadeDAO;
import br.com.projeto.dao.HorarioDAO;
import br.com.projeto.dao.ProfissaoDAO;
import br.com.projeto.dao.SalarioDAO;
import br.com.projeto.dao.SituacaoDAO;
import br.com.projeto.dao.UnidadeDAO;
import br.com.projeto.domain.Cargo;
import br.com.projeto.domain.Colaborador;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Estabilidade;
import br.com.projeto.domain.Horario;
import br.com.projeto.domain.Profissao;
import br.com.projeto.domain.Salario;
import br.com.projeto.domain.Situacao;
import br.com.projeto.domain.Unidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ColaboradorBean implements Serializable {

	private Colaborador colaborador;
	private List<Colaborador> colaboradores;

	private Profissao profissao;
	private List<Profissao> profissoes;

	private Cargo cargo;
	private List<Cargo> cargos;

	private Salario salario;
	private List<Salario> salarios;

	private Departamento departamento;
	private List<Departamento> departamentos;

	private Situacao situacao;
	private List<Situacao> situacoes;

	private Horario horario;
	private List<Horario> horarios;

	private Unidade unidade;
	private List<Unidade> unidades;

	private Estabilidade estabilidade;
	private List<Estabilidade> estabilidades;

	private FacesMessage message;
	private String busca;

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
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

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public List<Profissao> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Salario getSalario() {
		return salario;
	}

	public void setSalario(Salario salario) {
		this.salario = salario;
	}

	public List<Salario> getSalarios() {
		return salarios;
	}

	public void setSalarios(List<Salario> salarios) {
		this.salarios = salarios;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Situacao> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(List<Situacao> situacoes) {
		this.situacoes = situacoes;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	public Estabilidade getEstabilidade() {
		return estabilidade;
	}

	public void setEstabilidade(Estabilidade estabilidade) {
		this.estabilidade = estabilidade;
	}

	public List<Estabilidade> getEstabilidades() {
		return estabilidades;
	}

	public void setEstabilidades(List<Estabilidade> estabilidades) {
		this.estabilidades = estabilidades;
	}

	@PostConstruct
	public void inicializarTabelas() {
		try {
			ProfissaoDAO profissaoDAO = new ProfissaoDAO();
			profissoes = profissaoDAO.listar("nome");

			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.listar("nome");

			SalarioDAO salarioDAO = new SalarioDAO();
			salarios = salarioDAO.listar("nome");

			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listar("nome");

			SituacaoDAO situacaoDAO = new SituacaoDAO();
			situacoes = situacaoDAO.listar("nome");

			HorarioDAO horarioDAO = new HorarioDAO();
			horarios = horarioDAO.listar("nome");

			UnidadeDAO unidadeDAO = new UnidadeDAO();
			unidades = unidadeDAO.listar("nome");
			
			EstabilidadeDAO estabilidadeDAO = new EstabilidadeDAO();
			estabilidades = estabilidadeDAO.listar("nome");

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Inicializar Tabelas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradores = colaboradorDAO.pesquisar(busca);

			colaborador = null;

			profissao = new Profissao();
			cargo = new Cargo();
			salario = new Salario();
			departamento = new Departamento();
			situacao = new Situacao();
			horario = new Horario();
			unidade = new Unidade();
			estabilidade = new Estabilidade();

			if (colaboradores.isEmpty() == true) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Nenhum Registro foi Encontrado! Por favor Tente Novamente.", "Registro não Encontrado!");

				RequestContext.getCurrentInstance().showMessageInDialog(message);
			}

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Pesquisar Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void novo() {
		colaborador = new Colaborador();

		profissao = new Profissao();
		cargo = new Cargo();
		salario = new Salario();
		departamento = new Departamento();
		situacao = new Situacao();
		horario = new Horario();
		unidade = new Unidade();
		estabilidade = new Estabilidade();
	}

	public void salvar() {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.merge(colaborador);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			colaboradores = colaboradorDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			colaborador = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.excluir(colaborador);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + colaborador.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			colaboradores = colaboradorDAO.listar("nome");

			colaborador = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluirAtalho(ActionEvent evento) {
		try {
			colaborador = (Colaborador) evento.getComponent().getAttributes().get("registroSelecionado");

			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.excluir(colaborador);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + colaborador.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			colaboradores = colaboradorDAO.listar("nome");

			colaborador = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {

	}

	public void onRowUnselect(UnselectEvent event) {
		colaborador = null;
	}

	public void editar(ActionEvent evento) {
		try {
			colaborador = (Colaborador) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void duploClique(SelectEvent evento) {
		try {

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').show();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarProfissao() {
		try {
			ProfissaoDAO profissaoDAO = new ProfissaoDAO();
			profissaoDAO.merge(profissao);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoProfissao').hide();");

			profissoes = profissaoDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			profissao = new Profissao();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Salvar Profissão este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarCargo() {
		try {
			CargoDAO cargoDAO = new CargoDAO();
			cargoDAO.merge(cargo);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoCargo').hide();");

			cargos = cargoDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			cargo = new Cargo();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarSalario() {
		try {
			SalarioDAO salarioDAO = new SalarioDAO();
			salarioDAO.merge(salario);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoSalario').hide();");

			salarios = salarioDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			salario = new Salario();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarDepartamento() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentoDAO.merge(departamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoDepartamento').hide();");

			departamentos = departamentoDAO.listar("nome");

			if (departamento.getCodigo() == null) {

				AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
				auditoriaDAO.auditar("Cadastro um Novo Departamento: " + departamento.getNome());

			} else {

				AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
				auditoriaDAO.auditar("Alterou um Departamento: " + departamento.getNome());
			}

			departamento = new Departamento();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarSituacao() {
		try {
			SituacaoDAO situacaoDAO = new SituacaoDAO();
			situacaoDAO.merge(situacao);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoSituacao').hide();");

			situacoes = situacaoDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			situacao = new Situacao();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarHorario() {
		try {
			HorarioDAO horarioDAO = new HorarioDAO();
			horarioDAO.merge(horario);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoHorario').hide();");

			horarios = horarioDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			horario = new Horario();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarUnidade() {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			unidadeDAO.merge(unidade);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoUnidade').hide();");

			unidades = unidadeDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			unidade = new Unidade();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarEstabilidade() {
		try {
			EstabilidadeDAO estabilidadeDAO = new EstabilidadeDAO();
			estabilidadeDAO.merge(estabilidade);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoEstabilidade').hide();");

			estabilidades = estabilidadeDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			estabilidade = new Estabilidade();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}