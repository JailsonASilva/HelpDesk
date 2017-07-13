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

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean acesso;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean categoria;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean cliente;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean departamento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean equipamento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean manutencao;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean marca;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean nivel;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean proximaManutencao;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean ticketExterno;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean ticketInterno;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean tipoEquipamento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean usuario;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean local;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean classificacao;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean artigo;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean baseConhecimento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean ticketDepartamento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean ticketUsuario;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean tipoEvento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean material;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean evento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean localEquipamento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean tecnico;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean aberturaEmail;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean aberturaDepartamento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean aberturaUsuario;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean auditoria;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean mensagem;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean sistemaMenu;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean ticketMenu;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean eventoMenu;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean equipamentoMenu;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean baseConhecimentoMenu;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean rhMenu;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean portalRh;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean portalGestor;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean portalColaborador;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean cargo;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean salarios;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean unidade;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean colaborador;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean profissao;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean situacao;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean horario;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean estabilidade;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean ferias;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean ocorrenciaProntuario;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean prontuario;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean parentesco;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean dependente;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean fornecedor;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean solicitacaoRPA;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean rpaPendente;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean programacaoFerias;

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

	public Boolean getProfissao() {
		return profissao;
	}

	public void setProfissao(Boolean profissao) {
		this.profissao = profissao;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public Boolean getHorario() {
		return horario;
	}

	public void setHorario(Boolean horario) {
		this.horario = horario;
	}

	public Boolean getEstabilidade() {
		return estabilidade;
	}

	public void setEstabilidade(Boolean estabilidade) {
		this.estabilidade = estabilidade;
	}

	public Boolean getFerias() {
		return ferias;
	}

	public void setFerias(Boolean ferias) {
		this.ferias = ferias;
	}

	public Boolean getPortalRh() {
		return portalRh;
	}

	public void setPortalRh(Boolean portalRh) {
		this.portalRh = portalRh;
	}

	public Boolean getOcorrenciaProntuario() {
		return ocorrenciaProntuario;
	}

	public void setOcorrenciaProntuario(Boolean ocorrenciaProntuario) {
		this.ocorrenciaProntuario = ocorrenciaProntuario;
	}

	public Boolean getProntuario() {
		return prontuario;
	}

	public void setProntuario(Boolean prontuario) {
		this.prontuario = prontuario;
	}

	public Boolean getParentesco() {
		return parentesco;
	}

	public void setParentesco(Boolean parentesco) {
		this.parentesco = parentesco;
	}

	public Boolean getDependente() {
		return dependente;
	}

	public void setDependente(Boolean dependente) {
		this.dependente = dependente;
	}

	public Boolean getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Boolean fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Boolean getSolicitacaoRPA() {
		return solicitacaoRPA;
	}

	public void setSolicitacaoRPA(Boolean solicitacaoRPA) {
		this.solicitacaoRPA = solicitacaoRPA;
	}

	public Boolean getRpaPendente() {
		return rpaPendente;
	}

	public void setRpaPendente(Boolean rpaPendente) {
		this.rpaPendente = rpaPendente;
	}

	public Boolean getProgramacaoFerias() {
		return programacaoFerias;
	}

	public void setProgramacaoFerias(Boolean programacaoFerias) {
		this.programacaoFerias = programacaoFerias;
	}

}
