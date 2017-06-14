package br.com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class Acesso extends GenericDomain {
	@Column(length = 100, nullable = false)
	private String nome;

	@Column(nullable = true)
	private Boolean acesso;

	@Column(nullable = true)
	private Boolean categoria;

	@Column(nullable = true)
	private Boolean cliente;

	@Column(nullable = true)
	private Boolean departamento;

	@Column(nullable = true)
	private Boolean equipamento;

	@Column(nullable = true)
	private Boolean manutencao;

	@Column(nullable = true)
	private Boolean marca;

	@Column(nullable = true)
	private Boolean nivel;

	@Column(nullable = true)
	private Boolean proximaManutencao;

	@Column(nullable = true)
	private Boolean ticketExterno;

	@Column(nullable = true)
	private Boolean ticketInterno;

	@Column(nullable = true)
	private Boolean tipoEquipamento;

	@Column(nullable = true)
	private Boolean usuario;

	@Column(nullable = true)
	private Boolean local;

	@Column(nullable = true)
	private Boolean classificacao;

	@Column(nullable = true)
	private Boolean artigo;

	@Column(nullable = true)
	private Boolean baseConhecimento;

	@Column(nullable = true)
	private Boolean ticketDepartamento;

	@Column(nullable = true)
	private Boolean ticketUsuario;

	@Column(nullable = true)
	private Boolean tipoEvento;

	@Column(nullable = true)
	private Boolean material;

	@Column(nullable = true)
	private Boolean evento;

	@Column(nullable = true)
	private Boolean localEquipamento;

	@Column(nullable = true)
	private Boolean tecnico;

	@Column(nullable = true)
	private Boolean aberturaEmail;

	@Column(nullable = true)
	private Boolean aberturaDepartamento;

	@Column(nullable = true)
	private Boolean aberturaUsuario;

	@Column(nullable = true)
	private Boolean auditoria;

	@Column(nullable = true)
	private Boolean mensagem;

	@Column(nullable = true)
	private Boolean sistemaMenu;

	@Column(nullable = true)
	private Boolean ticketMenu;

	@Column(nullable = true)
	private Boolean eventoMenu;

	@Column(nullable = true)
	private Boolean equipamentoMenu;

	@Column(nullable = true)
	private Boolean baseConhecimentoMenu;

	@Column(nullable = true)
	private Boolean rhMenu;

	@Column(nullable = true)
	private Boolean rh;

	@Column(nullable = true)
	private Boolean portalGestor;

	@Column(nullable = true)
	private Boolean portalColaborador;

	@Column(nullable = true)
	private Boolean cargo;

	@Column(nullable = true)
	private Boolean salarios;

	@Column(nullable = true)
	private Boolean unidade;

	@Column(nullable = true)
	private Boolean colaborador;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAcesso() {
		return acesso;
	}

	public void setAcesso(Boolean acesso) {
		this.acesso = acesso;
	}

	public Boolean getCategoria() {
		return categoria;
	}

	public void setCategoria(Boolean categoria) {
		this.categoria = categoria;
	}

	public Boolean getCliente() {
		return cliente;
	}

	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}

	public Boolean getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Boolean departamento) {
		this.departamento = departamento;
	}

	public Boolean getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Boolean equipamento) {
		this.equipamento = equipamento;
	}

	public Boolean getManutencao() {
		return manutencao;
	}

	public void setManutencao(Boolean manutencao) {
		this.manutencao = manutencao;
	}

	public Boolean getMarca() {
		return marca;
	}

	public void setMarca(Boolean marca) {
		this.marca = marca;
	}

	public Boolean getNivel() {
		return nivel;
	}

	public void setNivel(Boolean nivel) {
		this.nivel = nivel;
	}

	public Boolean getProximaManutencao() {
		return proximaManutencao;
	}

	public void setProximaManutencao(Boolean proximaManutencao) {
		this.proximaManutencao = proximaManutencao;
	}

	public Boolean getTicketExterno() {
		return ticketExterno;
	}

	public void setTicketExterno(Boolean ticketExterno) {
		this.ticketExterno = ticketExterno;
	}

	public Boolean getTicketInterno() {
		return ticketInterno;
	}

	public void setTicketInterno(Boolean ticketInterno) {
		this.ticketInterno = ticketInterno;
	}

	public Boolean getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(Boolean tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public Boolean getUsuario() {
		return usuario;
	}

	public void setUsuario(Boolean usuario) {
		this.usuario = usuario;
	}

	public Boolean getLocal() {
		return local;
	}

	public void setLocal(Boolean local) {
		this.local = local;
	}

	public Boolean getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Boolean classificacao) {
		this.classificacao = classificacao;
	}

	public Boolean getArtigo() {
		return artigo;
	}

	public void setArtigo(Boolean artigo) {
		this.artigo = artigo;
	}

	public Boolean getBaseConhecimento() {
		return baseConhecimento;
	}

	public void setBaseConhecimento(Boolean baseConhecimento) {
		this.baseConhecimento = baseConhecimento;
	}

	public Boolean getTicketDepartamento() {
		return ticketDepartamento;
	}

	public void setTicketDepartamento(Boolean ticketDepartamento) {
		this.ticketDepartamento = ticketDepartamento;
	}

	public Boolean getTicketUsuario() {
		return ticketUsuario;
	}

	public void setTicketUsuario(Boolean ticketUsuario) {
		this.ticketUsuario = ticketUsuario;
	}

	public Boolean getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(Boolean tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Boolean getMaterial() {
		return material;
	}

	public void setMaterial(Boolean material) {
		this.material = material;
	}

	public Boolean getEvento() {
		return evento;
	}

	public void setEvento(Boolean evento) {
		this.evento = evento;
	}

	public Boolean getLocalEquipamento() {
		return localEquipamento;
	}

	public void setLocalEquipamento(Boolean localEquipamento) {
		this.localEquipamento = localEquipamento;
	}

	public Boolean getTecnico() {
		return tecnico;
	}

	public void setTecnico(Boolean tecnico) {
		this.tecnico = tecnico;
	}

	public Boolean getAberturaEmail() {
		return aberturaEmail;
	}

	public void setAberturaEmail(Boolean aberturaEmail) {
		this.aberturaEmail = aberturaEmail;
	}

	public Boolean getAberturaDepartamento() {
		return aberturaDepartamento;
	}

	public void setAberturaDepartamento(Boolean aberturaDepartamento) {
		this.aberturaDepartamento = aberturaDepartamento;
	}

	public Boolean getAberturaUsuario() {
		return aberturaUsuario;
	}

	public void setAberturaUsuario(Boolean aberturaUsuario) {
		this.aberturaUsuario = aberturaUsuario;
	}

	public Boolean getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Boolean auditoria) {
		this.auditoria = auditoria;
	}

	public Boolean getMensagem() {
		return mensagem;
	}

	public void setMensagem(Boolean mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getSistemaMenu() {
		return sistemaMenu;
	}

	public void setSistemaMenu(Boolean sistemaMenu) {
		this.sistemaMenu = sistemaMenu;
	}

	public Boolean getTicketMenu() {
		return ticketMenu;
	}

	public void setTicketMenu(Boolean ticketMenu) {
		this.ticketMenu = ticketMenu;
	}

	public Boolean getEventoMenu() {
		return eventoMenu;
	}

	public void setEventoMenu(Boolean eventoMenu) {
		this.eventoMenu = eventoMenu;
	}

	public Boolean getEquipamentoMenu() {
		return equipamentoMenu;
	}

	public void setEquipamentoMenu(Boolean equipamentoMenu) {
		this.equipamentoMenu = equipamentoMenu;
	}

	public Boolean getBaseConhecimentoMenu() {
		return baseConhecimentoMenu;
	}

	public void setBaseConhecimentoMenu(Boolean baseConhecimentoMenu) {
		this.baseConhecimentoMenu = baseConhecimentoMenu;
	}

	public Boolean getRhMenu() {
		return rhMenu;
	}

	public void setRhMenu(Boolean rhMenu) {
		this.rhMenu = rhMenu;
	}

	public Boolean getRh() {
		return rh;
	}

	public void setRh(Boolean rh) {
		this.rh = rh;
	}

	public Boolean getPortalGestor() {
		return portalGestor;
	}

	public void setPortalGestor(Boolean portalGestor) {
		this.portalGestor = portalGestor;
	}

	public Boolean getPortalColaborador() {
		return portalColaborador;
	}

	public void setPortalColaborador(Boolean portalColaborador) {
		this.portalColaborador = portalColaborador;
	}

	public Boolean getCargo() {
		return cargo;
	}

	public void setCargo(Boolean cargo) {
		this.cargo = cargo;
	}

	public Boolean getSalarios() {
		return salarios;
	}

	public void setSalarios(Boolean salarios) {
		this.salarios = salarios;
	}

	public Boolean getUnidade() {
		return unidade;
	}

	public void setUnidade(Boolean unidade) {
		this.unidade = unidade;
	}

	public Boolean getColaborador() {
		return colaborador;
	}

	public void setColaborador(Boolean colaborador) {
		this.colaborador = colaborador;
	}

}
