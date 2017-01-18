package br.com.projeto.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.domain.Departamento;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class PesquisaDepartamentoBean implements Serializable {

	private Departamento departamento;
	private String nome;
	private List<Departamento> departamentos;
	private FacesMessage message;

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();

		opcoes.put("modal", false);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);

		RequestContext.getCurrentInstance().openDialog("marca", opcoes, null);
	}

	public void pesquisar() {
		try {
			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.pesquisar(nome);

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

	public void selecionar(Departamento departamento) {
		RequestContext.getCurrentInstance().closeDialog(departamento);
	}
}
