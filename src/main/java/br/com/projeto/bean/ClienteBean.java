
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

import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.domain.Cliente;
import br.com.projeto.domain.Departamento;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class ClienteBean implements Serializable {
	private Cliente cliente;
	private List<Cliente> clientes;

	private Departamento departamento;
	private List<Departamento> departamentos;

	private FacesMessage message;
	private String busca;
	private String buscaDepartamento;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getBuscaDepartamento() {
		return buscaDepartamento;
	}

	public void setBuscaDepartamento(String buscaDepartamento) {
		this.buscaDepartamento = buscaDepartamento;
	}

	@PostConstruct
	public void carregarTabelas() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listar("nome");

		} catch (

		RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Abrir as Tabelas.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			erro.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.pesquisar(busca);

			departamento = new Departamento();

			cliente = null;

			if (clientes.isEmpty() == true) {
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
		cliente = new Cliente();
		departamento = new Departamento();
	}

	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			clientes = clienteDAO.listar("nome");

			cliente = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + cliente.getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			clientes = clienteDAO.listar("nome");

			cliente = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Excluir este Registro.",
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

			departamentos = departamentoDAO.listar("nome");

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
		cliente = null;
	}

	public void pesquisarDepartamento() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.pesquisarAtendimento(buscaDepartamento);

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

			cliente.setDepartamento(departamentoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqDepartamento').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}
