package br.com.projeto.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.projeto.dao.AuditoriaDAO;
import br.com.projeto.dao.CargoDAO;
import br.com.projeto.dao.ColaboradorDAO;
import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.DependenteDAO;
import br.com.projeto.dao.EstabilidadeDAO;
import br.com.projeto.dao.FeriasDAO;
import br.com.projeto.dao.HorarioDAO;
import br.com.projeto.dao.OcorrenciaProntuarioDAO;
import br.com.projeto.dao.ParentescoDAO;
import br.com.projeto.dao.ProfissaoDAO;
import br.com.projeto.dao.ProntuarioDAO;
import br.com.projeto.dao.SalarioDAO;
import br.com.projeto.dao.SituacaoDAO;
import br.com.projeto.dao.UnidadeDAO;
import br.com.projeto.domain.Cargo;
import br.com.projeto.domain.Colaborador;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Dependente;
import br.com.projeto.domain.Estabilidade;
import br.com.projeto.domain.Ferias;
import br.com.projeto.domain.Horario;
import br.com.projeto.domain.OcorrenciaProntuario;
import br.com.projeto.domain.Parentesco;
import br.com.projeto.domain.Profissao;
import br.com.projeto.domain.Prontuario;
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

	private Ferias ferias;
	private List<Ferias> feriasLista;

	private Prontuario prontuario;
	private List<Prontuario> prontuarios;

	private StreamedContent foto;
	private String tipoArquivo;

	private Dependente dependente;
	private List<Dependente> dependentes;

	private OcorrenciaProntuario ocorrenciaProntuario;
	private List<OcorrenciaProntuario> ocorrenciaProntuarios;

	private Parentesco parentesco;
	private List<Parentesco> parentescos;

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

	public List<Ferias> getFeriasLista() {
		return feriasLista;
	}

	public void setFeriasLista(List<Ferias> feriasLista) {
		this.feriasLista = feriasLista;
	}

	public Ferias getFerias() {
		return ferias;
	}

	public void setFerias(Ferias ferias) {
		this.ferias = ferias;
	}

	public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public List<Prontuario> getProntuarios() {
		return prontuarios;
	}

	public void setProntuarios(List<Prontuario> prontuarios) {
		this.prontuarios = prontuarios;
	}

	public OcorrenciaProntuario getOcorrenciaProntuario() {
		return ocorrenciaProntuario;
	}

	public void setOcorrenciaProntuario(OcorrenciaProntuario ocorrenciaProntuario) {
		this.ocorrenciaProntuario = ocorrenciaProntuario;
	}

	public List<OcorrenciaProntuario> getOcorrenciaProntuarios() {
		return ocorrenciaProntuarios;
	}

	public void setOcorrenciaProntuarios(List<OcorrenciaProntuario> ocorrenciaProntuarios) {
		this.ocorrenciaProntuarios = ocorrenciaProntuarios;
	}

	public Dependente getDependente() {
		return dependente;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public List<Parentesco> getParentescos() {
		return parentescos;
	}

	public void setParentescos(List<Parentesco> parentescos) {
		this.parentescos = parentescos;
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

			OcorrenciaProntuarioDAO ocorrenciaProntuarioDAO = new OcorrenciaProntuarioDAO();
			ocorrenciaProntuarios = ocorrenciaProntuarioDAO.listar("nome");

			ParentescoDAO parentescoDAO = new ParentescoDAO();
			parentescos = parentescoDAO.listar("nome");

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

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Registro não Encontrado!",
						"Nenhum Registro foi Encontrado! Por favor Tente Novamente."));
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

		colaborador.setCaminho("C:/Fotos/branco.png");
		colaborador.setAdicionalNoturno("NÃO");
		colaborador.setAdiantamentoSalarial("NÃO");
		colaborador.setSalarioFamilia("NÃO");
		colaborador.setInsalubridade("NÃO");
		colaborador.setPericulosidade("NÃO");
		colaborador.setGratificacaoFuncao("NÃO");
		colaborador.setAdicionalTempoServico("NÃO");
		colaborador.setDescontoBolsaEstudo("NÃO");
		colaborador.setQuantidadeSalarioFamilia(0L);
		colaborador.setInsalubridadePorc(BigDecimal.ZERO);
		colaborador.setPericulosidadePorc(BigDecimal.ZERO);
		colaborador.setValorGratificacao(BigDecimal.ZERO);

		profissao = new Profissao();
		cargo = new Cargo();
		salario = new Salario();
		departamento = new Departamento();
		situacao = new Situacao();
		horario = new Horario();
		unidade = new Unidade();
		estabilidade = new Estabilidade();
		ocorrenciaProntuario = new OcorrenciaProntuario();
		parentesco = new Parentesco();

	}

	public void novoFerias() {
		ferias = new Ferias();
		ferias.setColaborador(colaborador);
	}

	public void novoProntuario() {
		prontuario = new Prontuario();
		prontuario.setColaborador(colaborador);

		ocorrenciaProntuario = new OcorrenciaProntuario();
	}

	public void novoDependente() {
		dependente = new Dependente();

		dependente.setColaborador(colaborador);
		dependente.setSalarioEducacao(BigDecimal.ZERO);
		dependente.setSalarioFamilia(BigDecimal.ZERO);
		dependente.setImpostoRenda(BigDecimal.ZERO);

		parentesco = new Parentesco();
	}

	public void salvar() {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.merge(colaborador);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			colaboradores = colaboradorDAO.listar("nome");

			Path origem = Paths.get(colaborador.getCaminho());
			Path destino = Paths.get("C:/Fotos/" + colaborador.getCpf() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			colaborador = null;

		} catch (RuntimeException | IOException erro) {
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

			carregarFoto();

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

			carregarFoto();

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

	public void exibirFerias(ActionEvent evento) {
		try {
			colaborador = (Colaborador) evento.getComponent().getAttributes().get("registroSelecionado");

			FeriasDAO feriasDAO = new FeriasDAO();
			feriasLista = feriasDAO.exibirFerias(colaborador.getCodigo());

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoFeriasLista').show();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Exibir Férias.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editarFerias(ActionEvent evento) {
		try {
			ferias = (Ferias) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluirFeriasAtalho(ActionEvent evento) {
		try {
			ferias = (Ferias) evento.getComponent().getAttributes().get("registroSelecionado");

			FeriasDAO feriasDAO = new FeriasDAO();
			feriasDAO.excluir(ferias);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + ferias.getColaborador().getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			feriasLista = feriasDAO.pesquisarFerias("");

			ferias = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarFerias() {
		try {
			FeriasDAO feriasDAO = new FeriasDAO();
			feriasDAO.merge(ferias);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoFerias').hide();");

			feriasLista = feriasDAO.pesquisarFerias("");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			ferias = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void exibirProntuario(ActionEvent evento) {
		try {
			colaborador = (Colaborador) evento.getComponent().getAttributes().get("registroSelecionado");

			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarios = prontuarioDAO.exibirProntuario(colaborador.getCodigo());

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoProntuarioLista').show();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Exibir Prontuário.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editarProntuario(ActionEvent evento) {
		try {
			prontuario = (Prontuario) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluirProntuarioAtalho(ActionEvent evento) {
		try {
			prontuario = (Prontuario) evento.getComponent().getAttributes().get("registroSelecionado");

			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarioDAO.excluir(prontuario);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + prontuario.getColaborador().getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			prontuarios = prontuarioDAO.pesquisarProntuario("");

			prontuario = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarProntuario() {
		try {
			ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
			prontuarioDAO.merge(prontuario);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoProntuario').hide();");

			prontuarios = prontuarioDAO.pesquisarProntuario("");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			prontuario = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void exibirDependente(ActionEvent evento) {
		try {
			colaborador = (Colaborador) evento.getComponent().getAttributes().get("registroSelecionado");

			DependenteDAO dependenteDAO = new DependenteDAO();
			dependentes = dependenteDAO.exibirDependente(colaborador.getCodigo());

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoDependenteLista').show();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Exibir Prontuário.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void editarDependente(ActionEvent evento) {
		try {
			dependente = (Dependente) evento.getComponent().getAttributes().get("registroSelecionado");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Selecionar este Registro.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluirDependenteAtalho(ActionEvent evento) {
		try {
			dependente = (Dependente) evento.getComponent().getAttributes().get("registroSelecionado");

			DependenteDAO dependenteDAO = new DependenteDAO();
			dependenteDAO.excluir(dependente);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + dependente.getColaborador().getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			dependentes = dependenteDAO.listar("nome");

			dependente = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarDependente() {
		try {
			DependenteDAO dependenteDAO = new DependenteDAO();
			dependenteDAO.merge(dependente);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoDependente').hide();");

			dependentes = dependenteDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			dependente = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void upload(FileUploadEvent evento) {
		try {

			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			colaborador.setCaminho(arquivoTemp.toString());

		} catch (IOException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um Erro ao Tentar Realizar Upload da Foto.", "Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void carregarFoto() {

		File file = new File("C:/Fotos/" + colaborador.getCpf() + ".png");

		if (file.exists()) {
			colaborador.setCaminho("C:/Fotos/" + colaborador.getCpf() + ".png");

		} else {
			colaborador.setCaminho("C:/Fotos/branco.png");
		}
	}

	public void salvarOcorrencia() {
		try {
			OcorrenciaProntuarioDAO ocorrenciaProntuarioDAO = new OcorrenciaProntuarioDAO();
			ocorrenciaProntuarioDAO.merge(ocorrenciaProntuario);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoOcorrencia').hide();");

			ocorrenciaProntuarios = ocorrenciaProntuarioDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			ocorrenciaProntuario = new OcorrenciaProntuario();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarParentesco() {
		try {
			ParentescoDAO parentescoDAO = new ParentescoDAO();
			parentescoDAO.merge(parentesco);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoParentesco').hide();");

			parentescos = parentescoDAO.listar("nome");

			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Aviso!", "Registro Salvo com Sucesso"));

			parentesco = new Parentesco();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}