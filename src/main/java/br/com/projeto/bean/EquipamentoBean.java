package br.com.projeto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.EquipamentoDAO;
import br.com.projeto.dao.LocalEquipamentoDAO;
import br.com.projeto.dao.MarcaDAO;
import br.com.projeto.dao.TipoEquipamentoDAO;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Equipamento;
import br.com.projeto.domain.LocalEquipamento;
import br.com.projeto.domain.Marca;
import br.com.projeto.domain.TipoEquipamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EquipamentoBean implements Serializable {
	private Equipamento equipamento;
	private TipoEquipamento tipoEquipamento;
	private LocalEquipamento localEquipamento;
	private Marca marca;
	private Departamento departamento;

	private List<Equipamento> equipamentos;
	private List<TipoEquipamento> tipoEquipamentos;
	private List<LocalEquipamento> localEquipamentos;
	private List<Marca> marcas;
	private List<Departamento> departamentos;

	private FacesMessage message;
	private String busca;
	private String buscaDepartamento;
	private String buscaTipoEquipamento;
	private String buscaLocalEquipamento;
	private String buscaMarca;

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
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

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public List<TipoEquipamento> getTipoEquipamentos() {
		return tipoEquipamentos;
	}

	public void setTipoEquipamentos(List<TipoEquipamento> tipoEquipamentos) {
		this.tipoEquipamentos = tipoEquipamentos;
	}

	public LocalEquipamento getLocalEquipamento() {
		return localEquipamento;
	}

	public void setLocalEquipamento(LocalEquipamento localEquipamento) {
		this.localEquipamento = localEquipamento;
	}

	public List<LocalEquipamento> getLocalEquipamentos() {
		return localEquipamentos;
	}

	public void setLocalEquipamentos(List<LocalEquipamento> localEquipamentos) {
		this.localEquipamentos = localEquipamentos;
	}

	public String getBuscaDepartamento() {
		return buscaDepartamento;
	}

	public void setBuscaDepartamento(String buscaDepartamento) {
		this.buscaDepartamento = buscaDepartamento;
	}

	public String getBuscaTipoEquipamento() {
		return buscaTipoEquipamento;
	}

	public void setBuscaTipoEquipamento(String buscaTipoEquipamento) {
		this.buscaTipoEquipamento = buscaTipoEquipamento;
	}

	public String getBuscaLocalEquipamento() {
		return buscaLocalEquipamento;
	}

	public void setBuscaLocalEquipamento(String buscaLocalEquipamento) {
		this.buscaLocalEquipamento = buscaLocalEquipamento;
	}

	public String getBuscaMarca() {
		return buscaMarca;
	}

	public void setBuscaMarca(String buscaMarca) {
		this.buscaMarca = buscaMarca;
	}

	@PostConstruct
	public void abrirTabelas() {
		try {
			TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
			tipoEquipamentos = tipoEquipamentoDAO.listar("nome");

			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listar("nome");

			MarcaDAO marcaDAO = new MarcaDAO();
			marcas = marcaDAO.listar("nome");

			LocalEquipamentoDAO localEquipamentoDAO = new LocalEquipamentoDAO();
			localEquipamentos = localEquipamentoDAO.listar("nome");

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Abrir Tabelas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentos = equipamentoDAO.pesquisarEquipamento(busca);

			departamento = new Departamento();
			marca = new Marca();
			tipoEquipamento = new TipoEquipamento();
			localEquipamento = new LocalEquipamento();

			equipamento = null;

			if (equipamentos.isEmpty() == true) {
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
		equipamento = new Equipamento();
		departamento = new Departamento();
		marca = new Marca();
		tipoEquipamento = new TipoEquipamento();
		localEquipamento = new LocalEquipamento();
	}

	public void salvar() {
		try {
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentoDAO.merge(equipamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			equipamentos = equipamentoDAO.pesquisarEquipamento("");

			equipamento = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentoDAO.excluir(equipamento);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + equipamento.getTipoEquipamento().getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			equipamentos = equipamentoDAO.pesquisarEquipamento("");

			equipamento = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarMarca() {
		try {
			MarcaDAO marcaDAO = new MarcaDAO();
			marcaDAO.merge(marca);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoMarca').hide();");

			marca = new Marca();

			marcas = marcaDAO.listar("nome");

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

			departamento = new Departamento();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarTipo() {
		try {
			TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
			tipoEquipamentoDAO.merge(tipoEquipamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoTipo').hide();");

			tipoEquipamento = new TipoEquipamento();

			tipoEquipamentos = tipoEquipamentoDAO.listar("nome");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarLocal() {
		try {
			LocalEquipamentoDAO localEquipamentoDAO = new LocalEquipamentoDAO();
			localEquipamentoDAO.merge(localEquipamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoLocal').hide();");

			localEquipamentos = localEquipamentoDAO.listar("nome");

			localEquipamento = new LocalEquipamento();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {

	}

	public void onRowUnselect(UnselectEvent event) {
		equipamento = null;
	}

	public void pesquisarDepartamento() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.pesquisar(buscaDepartamento);

			if (departamentos.isEmpty() == true) {
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

	public void selecionarDepartamento(ActionEvent evento) {
		try {

			Departamento departamentoPesq = (Departamento) evento.getComponent().getAttributes()
					.get("departamentoSelecionado");

			equipamento.setDepartamento(departamentoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqDepartamento').hide();");
			org.primefaces.context.DefaultRequestContext.getCurrentInstance().update(":formCadastro:departamento");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarTipoEquipamento() {
		try {
			TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
			tipoEquipamentos = tipoEquipamentoDAO.pesquisar(buscaTipoEquipamento);

			if (tipoEquipamentos.isEmpty() == true) {
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

	public void selecionarTipoEquipamento(ActionEvent evento) {
		try {

			TipoEquipamento tipoEquipamentoPesq = (TipoEquipamento) evento.getComponent().getAttributes()
					.get("tipoEquipamentoSelecionado");

			equipamento.setTipoEquipamento(tipoEquipamentoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance()
					.execute("PF('dialogoPesqTipoEquipamento').hide();");
			org.primefaces.context.DefaultRequestContext.getCurrentInstance().update(":formCadastro:tipoEquipamento");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarMarca() {
		try {
			MarcaDAO marcaDAO = new MarcaDAO();
			marcas = marcaDAO.pesquisar(buscaMarca);

			if (marcas.isEmpty() == true) {
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

	public void selecionarMarca(ActionEvent evento) {
		try {

			Marca marcaPesq = (Marca) evento.getComponent().getAttributes().get("marcaSelecionado");

			equipamento.setMarca(marcaPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqMarca').hide();");
			org.primefaces.context.DefaultRequestContext.getCurrentInstance().update(":formCadastro:marca");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarLocalEquipamento() {
		try {
			LocalEquipamentoDAO localEquipamentoDAO = new LocalEquipamentoDAO();
			localEquipamentos = localEquipamentoDAO.pesquisar(buscaLocalEquipamento);

			if (localEquipamentos.isEmpty() == true) {
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

	public void selecionarLocalEquipamento(ActionEvent evento) {
		try {

			LocalEquipamento localEquipamentoPesq = (LocalEquipamento) evento.getComponent().getAttributes()
					.get("localEquipamentoSelecionado");

			equipamento.setLocalEquipamento(localEquipamentoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance()
					.execute("PF('dialogoPesqLocalEquipamento').hide();");
			org.primefaces.context.DefaultRequestContext.getCurrentInstance().update(":formCadastro:localEquipamento");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarLocalEquipamento() {
		try {
			LocalEquipamentoDAO localEquipamentoDAO = new LocalEquipamentoDAO();
			localEquipamentoDAO.merge(localEquipamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoLocalEquipamento').hide();");

			localEquipamento = new LocalEquipamento();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
}
