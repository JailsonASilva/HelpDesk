package br.com.projeto.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.primefaces.component.datatable.DataTable;
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
import br.com.projeto.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProximaManutencaoBean implements Serializable {
	private Manutencao manutencao;
	private List<Manutencao> manutencoes;

	private Equipamento equipamento;
	private List<Equipamento> equipamentos;

	private List<TipoEquipamento> tipoEquipamentos;
	private List<Departamento> departamentos;
	private List<Marca> marcas;
	private List<Usuario> usuarios;

	private FacesMessage message;

	private Date dataInicial;
	private Date dataFinal;
	private String tipoEquipamento;
	private String tipoManutencao;
	private String departamento;
	private String marca;
	private String usuario;

	public List<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public void setManutencoes(List<Manutencao> manutencoes) {
		this.manutencoes = manutencoes;
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

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public String getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(String tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public String getTipoManutencao() {
		return tipoManutencao;
	}

	public void setTipoManutencao(String tipoManutencao) {
		this.tipoManutencao = tipoManutencao;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

			manutencoes = manutencaoEquipamentoDAO.proximaManutencao(dataInicial, dataFinal, tipoEquipamento,
					departamento, marca, usuario, tipoManutencao);

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

	@SuppressWarnings("unused")
	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();

			String caminho = Faces.getRealPath("/relatórios/proximaManutencao.jasper");

			String caminhoBanner = Faces.getRealPath("/relatórios/Logo.png");

			Map<String, Object> parametros = new HashMap<>();

			// parametros.put("CAMINHO_BANNER", caminhoBanner);

			parametros.put("DEPARTAMENTO", "%" + departamento + "%");
			parametros.put("EQUIPAMENTO", "%" + tipoEquipamento + "%");
			parametros.put("MARCA", "%" + marca + "%");
			parametros.put("TIPO", "%" + tipoManutencao + "%");
			parametros.put("USUARIO", "%" + usuario + "%");
			parametros.put("DATAINICIAL", dataInicial);
			parametros.put("DATAFINAL", dataFinal);

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);

		} catch (JRException erro) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um Erro ao Tentar Imprimir Relatório",
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
}
