package br.com.projeto.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "ticket")
public class Ticket extends GenericDomain {
	@ManyToOne
	@JoinColumn(nullable = true)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Usuario usuarioAtendimento;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Equipamento equipamento;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;

	@Temporal(TemporalType.TIME)
	@Column(nullable = true)
	private Date hora;

	@Column(length = 40, nullable = false)
	private String status;

	@Column(length = 40, nullable = false)
	private String prioridade;

	@Column(length = 100, nullable = false)
	private String assunto;

	@Column(length = 10000, nullable = false)
	private String solicitacao;

	@Column(nullable = true, columnDefinition = "boolean default False")
	private Boolean emailDepartamento;

	@Column(nullable = true, columnDefinition = "boolean default False")
	private Boolean emailSolicitante;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date ultimaInteracao;

	@Column(nullable = true, columnDefinition = "boolean default False")
	private Boolean evento;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioAtendimento() {
		return usuarioAtendimento;
	}

	public void setUsuarioAtendimento(Usuario usuarioAtendimento) {
		this.usuarioAtendimento = usuarioAtendimento;
	}

	public Date getUltimaInteracao() {
		return ultimaInteracao;
	}

	public void setUltimaInteracao(Date ultimaInteracao) {
		this.ultimaInteracao = ultimaInteracao;
	}

	public Boolean getEmailDepartamento() {
		return emailDepartamento;
	}

	public void setEmailDepartamento(Boolean emailDepartamento) {
		this.emailDepartamento = emailDepartamento;
	}

	public Boolean getEmailSolicitante() {
		return emailSolicitante;
	}

	public void setEmailSolicitante(Boolean emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}

	@Transient
	public String getPrioridadeFormatada() {

		String prioridadeFormatada = "";

		if (prioridade.equals("1-Urgente")) {
			prioridadeFormatada = "Urgente";
		}

		if (prioridade.equals("2-Alta")) {
			prioridadeFormatada = "Alta";
		}

		if (prioridade.equals("3-Normal")) {
			prioridadeFormatada = "Normal";
		}

		if (prioridade.equals("4-Baixa")) {
			prioridadeFormatada = "Baixa";
		}

		return prioridadeFormatada;
	}

	@Transient
	public String getDataFormatada() {
		String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataAbertura);

		return dataFormatada;
	}

	@Transient
	public String getNomeDepartamento() {
		return getDepartamento() == null ? null : getDepartamento().getNome();
	}

	@Transient
	public void setNomeDepartamento(String nomeDepartamento) {
		departamento.setNome(nomeDepartamento);
	}

	@Transient
	public String getNomeCliente() {
		return getCliente() == null ? null : getCliente().getNome();
	}

	@Transient
	public void setNomeCliente(String nomeCliente) {
		cliente.setNome(nomeCliente);
	}

	@Transient
	public String getNomeCategoria() {
		return getCategoria() == null ? null : getCategoria().getNome();
	}

	@Transient
	public void setNomeCategoria(String nomeCategoria) {
		categoria.setNome(nomeCategoria);
	}

	@Transient
	public String getNomeUsuario() {
		return getUsuario() == null ? null : getUsuario().getNome();
	}

	@Transient
	public void setNomeUsuario(String nomeUsuario) {
		usuario.setNome(nomeUsuario);
	}

	@Transient
	public String getNomeEquipamento() {
		return getEquipamento() == null ? null
				: getEquipamento().getTipoEquipamento().getNome() + " / "
						+ getEquipamento().getLocalEquipamento().getNome();
	}

	@Transient
	public void setNomeEquipamento(String nomeEquipamento) {

	}

	@Transient
	public String getNomeUsuarioAtendimento() {
		return getUsuarioAtendimento() == null ? null : getUsuarioAtendimento().getNome();
	}

	@Transient
	public void setNomeUsuarioAtendimento(String nomeUsuarioAtendimento) {
		usuarioAtendimento.setNome(nomeUsuarioAtendimento);
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Boolean getEvento() {
		return evento;
	}

	public void setEvento(Boolean evento) {
		this.evento = evento;
	}

}
