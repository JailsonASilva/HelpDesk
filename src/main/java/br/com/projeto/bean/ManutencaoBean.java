package br.com.projeto.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.EquipamentoDAO;
import br.com.projeto.dao.ManutencaoDAO;
import br.com.projeto.dao.TecnicoDAO;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Equipamento;
import br.com.projeto.domain.Manutencao;
import br.com.projeto.domain.Marca;
import br.com.projeto.domain.Tecnico;
import br.com.projeto.domain.TipoEquipamento;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class ManutencaoBean implements Serializable {
	private Manutencao manutencao;
	private Equipamento equipamento;
	private TipoEquipamento tipoEquipamento;
	private Tecnico tecnico;

	private List<Manutencao> manutencoes;
	private List<Equipamento> equipamentos;
	private List<TipoEquipamento> tipoEquipamentos;
	private List<Departamento> departamentos;
	private List<Marca> marcas;
	private List<Tecnico> tecnicos;

	private FacesMessage message;

	private String buscaEquipamento;
	private String buscaTecnico;

	private Date dataInicial;
	private Date dataFinal;

	public Manutencao getManutencao() {
		return manutencao;
	}

	public void setManutencao(Manutencao manutencao) {
		this.manutencao = manutencao;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public List<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public void setManutencoes(List<Manutencao> manutencoes) {
		this.manutencoes = manutencoes;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public List<TipoEquipamento> getTipoEquipamentos() {
		return tipoEquipamentos;
	}

	public void setTipoEquipamentos(List<TipoEquipamento> tipoEquipamentos) {
		this.tipoEquipamentos = tipoEquipamentos;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;  
	}

	public String getBuscaEquipamento() {
		return buscaEquipamento;
	}

	public void setBuscaEquipamento(String buscaEquipamento) {
		this.buscaEquipamento = buscaEquipamento;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Tecnico> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public String getBuscaTecnico() {
		return buscaTecnico;
	}

	public void setBuscaTecnico(String buscaTecnico) {
		this.buscaTecnico = buscaTecnico;
	}

	public void pesquisar() {
		try {
			ManutencaoDAO manutencaoEquipamentoDAO = new ManutencaoDAO();

			manutencoes = manutencaoEquipamentoDAO.pesquisarEquipamento(dataInicial, dataFinal);

			equipamento = new Equipamento();
			tecnico = new Tecnico();

			manutencao = null;

			if (manutencoes.isEmpty() == true) {
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
		tecnico = new Tecnico();

		manutencao = new Manutencao();
	}

	public void salvar() {
		try {
			ManutencaoDAO manutencaoEquipamentoDAO = new ManutencaoDAO();
			manutencaoEquipamentoDAO.merge(manutencao);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			manutencoes = manutencaoEquipamentoDAO.pesquisarEquipamento(dataInicial, dataFinal);

			manutencao = null;

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ManutencaoDAO manutencaoEquipamentoDAO = new ManutencaoDAO();
			manutencaoEquipamentoDAO.excluir(manutencao);

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Excluído com Sucesso!",
					"Registro: " + equipamento.getTipoEquipamento().getNome());

			RequestContext.getCurrentInstance().showMessageInDialog(message);

			manutencoes = manutencaoEquipamentoDAO.pesquisarEquipamento(dataInicial, dataFinal);

			manutencao = null;

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
		manutencao = null;
	}

	public void salvarEquipamento() {
		try {
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentoDAO.merge(equipamento);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoEquipamento').hide();");

			equipamento = new Equipamento();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarEquipamento() {
		try {
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentos = equipamentoDAO.pesquisarEquipamento(buscaEquipamento);

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

	public void selecionarEquipamento(ActionEvent evento) {
		try {
			Equipamento equipamentoPesq = (Equipamento) evento.getComponent().getAttributes()
					.get("equipamentoSelecionado");

			manutencao.setEquipamento(equipamentoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqEquipamento').hide();");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void pesquisarTecnico() {
		try {
			TecnicoDAO tecnicoDAO = new TecnicoDAO();
			tecnicos = tecnicoDAO.pesquisar(buscaTecnico);

			if (tecnicos.isEmpty() == true) {
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

	public void selecionarTecnico(ActionEvent evento) {
		try {
			Tecnico tecnicoPesq = (Tecnico) evento.getComponent().getAttributes().get("tecnicoSelecionado");

			manutencao.setTecnico(tecnicoPesq);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoPesqTecnico').hide();");
			org.primefaces.context.DefaultRequestContext.getCurrentInstance().update(":formCadastro:tecnico");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Selecionar Registro.",
					"Erro Inesperado!");

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarTecnico() {
		try {
			TecnicoDAO tecnicoDAO = new TecnicoDAO();
			tecnicoDAO.merge(tecnico);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoTecnico').hide();");

			tecnico = new Tecnico();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

}
