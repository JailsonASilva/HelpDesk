package br.com.projeto.bean;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.projeto.dao.DepartamentoDAO;
import br.com.projeto.dao.EquipamentoDAO;
import br.com.projeto.dao.ManutencaoDAO;
import br.com.projeto.dao.MarcaDAO;
import br.com.projeto.dao.TipoEquipamentoDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.domain.Departamento;
import br.com.projeto.domain.Equipamento;
import br.com.projeto.domain.Manutencao;
import br.com.projeto.domain.Marca;
import br.com.projeto.domain.TipoEquipamento;
import br.com.projeto.domain.Usuario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class ManutencaoBean implements Serializable {
	private Manutencao manutencao;
	private List<Manutencao> manutencoes;

	private Equipamento equipamento;
	private List<Equipamento> equipamentos;

	private TipoEquipamento tipoEquipamento;
	private List<TipoEquipamento> tipoEquipamentos;

	private List<Departamento> departamentos;
	private List<Marca> marcas;

	private Usuario usuario;
	private List<Usuario> usuarios;

	private FacesMessage message;

	private Date dataInicial;
	private Date dataFinal;

	public Manutencao getManutencao() {
		return manutencao;
	}

	public void setManutencao(Manutencao manutencao) {
		this.manutencao = manutencao;
	}

	public List<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public void setManutencoes(List<Manutencao> manutencoes) {
		this.manutencoes = manutencoes;
	}

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

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
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

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	@PostConstruct
	public void carregarTabelas() {
		try {
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentos = equipamentoDAO.listar();

			DepartamentoDAO departamentoDAO = new DepartamentoDAO();
			departamentos = departamentoDAO.listar("nome");

			MarcaDAO marcaDao = new MarcaDAO();
			marcas = marcaDao.listar("nome");

			TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
			tipoEquipamentos = tipoEquipamentoDAO.listar("nome");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("nome");

			dataInicial = new java.util.Date();
			dataFinal = new java.util.Date();

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
			ManutencaoDAO manutencaoEquipamentoDAO = new ManutencaoDAO();
			manutencoes = manutencaoEquipamentoDAO.pesquisarEquipamento(dataInicial, dataFinal,
					tipoEquipamento.getNome());

			equipamento = new Equipamento();
			usuario = new Usuario();

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
		usuario = new Usuario();

		manutencao = new Manutencao();
	}

	public void salvar() {
		try {
			ManutencaoDAO manutencaoEquipamentoDAO = new ManutencaoDAO();
			manutencaoEquipamentoDAO.merge(manutencao);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogo').hide();");

			manutencoes = manutencaoEquipamentoDAO.pesquisarEquipamento(dataInicial, dataFinal, "");

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

			manutencoes = manutencaoEquipamentoDAO.pesquisarEquipamento(dataInicial, dataFinal, "");

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

			equipamentos = equipamentoDAO.pesquisarEquipamento("");

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}

	public void salvarUsuario() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dialogoUsuario').hide();");
			
			usuarios = usuarioDAO.listar("nome");

			usuario = new Usuario();

		} catch (RuntimeException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Salvar este Registro.",
					"Erro: " + erro.getMessage());

			RequestContext.getCurrentInstance().showMessageInDialog(message);
			erro.printStackTrace();
		}
	}
}
