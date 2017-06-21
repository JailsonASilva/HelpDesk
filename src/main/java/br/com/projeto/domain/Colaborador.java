package br.com.projeto.domain;

import java.math.BigDecimal;
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
@Table(name = "colaborador")
public class Colaborador extends GenericDomain {

	@Column(length = 100, nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataNascimento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Unidade unidade;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cargo cargo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Profissao profissao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Salario salario;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Situacao situacao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Horario horario;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Estabilidade estabilidade;

	@Column(length = 80, nullable = true)
	private String localNascimento;

	@Column(length = 100, nullable = true)
	private String pai;

	@Column(length = 100, nullable = false)
	private String mae;

	@Column(length = 10, nullable = true)
	private String sexo;

	@Column(length = 50, nullable = true)
	private String estadoCivil;

	@Column(length = 50, nullable = true)
	private String escolaridade;

	@Column(length = 50, nullable = true)
	private Long numeroRegistro;

	@Column(length = 25, nullable = false)
	private String telefone_1;

	@Column(length = 25, nullable = true)
	private String telefone_2;

	@Column(length = 25, nullable = true)
	private String telefone_3;

	@Column(length = 80, nullable = true)
	private String email;

	@Column(length = 80, nullable = false)
	private String rg;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date emissaoRg;

	@Column(length = 20, nullable = false)
	private String orgaoEmissor;

	@Column(length = 20, nullable = false)
	private String cpf;

	@Column(length = 30, nullable = false)
	private String pis;

	@Column(length = 40, nullable = false)
	private String ctps;

	@Column(length = 40, nullable = false)
	private String tituloEleitoral;

	@Column(length = 10, nullable = false)
	private String zona;

	@Column(length = 10, nullable = false)
	private String secao;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date emissaoCtps;

	@Column(length = 60, nullable = true)
	private String banco;

	@Column(length = 20, nullable = true)
	private String agencia;

	@Column(length = 20, nullable = true)
	private String conta;

	@Column(length = 20, nullable = true)
	private String tipoConta;

	@Column(length = 20, nullable = true)
	private String operacaoConta;

	@Column(length = 100, nullable = false)
	private String endereco;

	@Column(length = 10, nullable = false)
	private String numero;

	@Column(length = 100, nullable = true)
	private String complemento;

	@Column(length = 80, nullable = false)
	private String bairro;

	@Column(length = 80, nullable = false)
	private String cidade;

	@Column(length = 10, nullable = false)
	private String uf;

	@Column(length = 15, nullable = true)
	private String cep;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataAdmissao;

	@Column(length = 20, nullable = false)
	private String tipoSalario;

	@Column(length = 3, nullable = true)
	private String adicionalNoturno;

	@Column(length = 3, nullable = true)
	private String adiantamentoSalarial;

	@Column(length = 3, nullable = true)
	private String salarioFamilia;

	@Column(nullable = true)
	private Long quantidadeSalarioFamilia;

	@Column(length = 3, nullable = true)
	private String insalubridade;

	@Column(nullable = true, precision = 6, scale = 2)
	private BigDecimal insalubridadePorc;

	@Column(length = 3, nullable = true)
	private String periculosidade;

	@Column(nullable = true, precision = 6, scale = 2)
	private BigDecimal periculosidadePorc;

	@Column(length = 30, nullable = true)
	private String gratificacaoFuncao;

	@Column(nullable = true, precision = 6, scale = 2)
	private BigDecimal valorGratificacao;

	@Column(length = 3, nullable = true)
	private String adicionalTempoServico;

	@Column(length = 3, nullable = true)
	private String descontoBolsaEstudo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLocalNascimento() {
		return localNascimento;
	}

	public void setLocalNascimento(String localNascimento) {
		this.localNascimento = localNascimento;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public Long getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(Long numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public String getTelefone_1() {
		return telefone_1;
	}

	public void setTelefone_1(String telefone_1) {
		this.telefone_1 = telefone_1;
	}

	public String getTelefone_2() {
		return telefone_2;
	}

	public void setTelefone_2(String telefone_2) {
		this.telefone_2 = telefone_2;
	}

	public String getTelefone_3() {
		return telefone_3;
	}

	public void setTelefone_3(String telefone_3) {
		this.telefone_3 = telefone_3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getEmissaoRg() {
		return emissaoRg;
	}

	public void setEmissaoRg(Date emissaoRg) {
		this.emissaoRg = emissaoRg;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getTituloEleitoral() {
		return tituloEleitoral;
	}

	public void setTituloEleitoral(String tituloEleitoral) {
		this.tituloEleitoral = tituloEleitoral;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public Date getEmissaoCtps() {
		return emissaoCtps;
	}

	public void setEmissaoCtps(Date emissaoCtps) {
		this.emissaoCtps = emissaoCtps;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getTipoSalario() {
		return tipoSalario;
	}

	public void setTipoSalario(String tipoSalario) {
		this.tipoSalario = tipoSalario;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public String getAdicionalNoturno() {
		return adicionalNoturno;
	}

	public void setAdicionalNoturno(String adicionalNoturno) {
		this.adicionalNoturno = adicionalNoturno;
	}

	public String getAdiantamentoSalarial() {
		return adiantamentoSalarial;
	}

	public void setAdiantamentoSalarial(String adiantamentoSalarial) {
		this.adiantamentoSalarial = adiantamentoSalarial;
	}

	public String getSalarioFamilia() {
		return salarioFamilia;
	}

	public void setSalarioFamilia(String salarioFamilia) {
		this.salarioFamilia = salarioFamilia;
	}

	public Long getQuantidadeSalarioFamilia() {
		return quantidadeSalarioFamilia;
	}

	public void setQuantidadeSalarioFamilia(Long quantidadeSalarioFamilia) {
		this.quantidadeSalarioFamilia = quantidadeSalarioFamilia;
	}

	public String getInsalubridade() {
		return insalubridade;
	}

	public void setInsalubridade(String insalubridade) {
		this.insalubridade = insalubridade;
	}

	public BigDecimal getInsalubridadePorc() {
		return insalubridadePorc;
	}

	public void setInsalubridadePorc(BigDecimal insalubridadePorc) {
		this.insalubridadePorc = insalubridadePorc;
	}

	public String getPericulosidade() {
		return periculosidade;
	}

	public void setPericulosidade(String periculosidade) {
		this.periculosidade = periculosidade;
	}

	public BigDecimal getPericulosidadePorc() {
		return periculosidadePorc;
	}

	public void setPericulosidadePorc(BigDecimal periculosidadePorc) {
		this.periculosidadePorc = periculosidadePorc;
	}

	public String getGratificacaoFuncao() {
		return gratificacaoFuncao;
	}

	public void setGratificacaoFuncao(String gratificacaoFuncao) {
		this.gratificacaoFuncao = gratificacaoFuncao;
	}

	public BigDecimal getValorGratificacao() {
		return valorGratificacao;
	}

	public void setValorGratificacao(BigDecimal valorGratificacao) {
		this.valorGratificacao = valorGratificacao;
	}

	public String getAdicionalTempoServico() {
		return adicionalTempoServico;
	}

	public void setAdicionalTempoServico(String adicionalTempoServico) {
		this.adicionalTempoServico = adicionalTempoServico;
	}

	public String getDescontoBolsaEstudo() {
		return descontoBolsaEstudo;
	}

	public void setDescontoBolsaEstudo(String descontoBolsaEstudo) {
		this.descontoBolsaEstudo = descontoBolsaEstudo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Salario getSalario() {
		return salario;
	}

	public void setSalario(Salario salario) {
		this.salario = salario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Estabilidade getEstabilidade() {
		return estabilidade;
	}

	public void setEstabilidade(Estabilidade estabilidade) {
		this.estabilidade = estabilidade;
	}

	@Transient
	public String getDataNascimentoFormatada() {
		String dataNascimentoFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento);

		return dataNascimentoFormatada;
	}

	public String getOperacaoConta() {
		return operacaoConta;
	}

	public void setOperacaoConta(String operacaoConta) {
		this.operacaoConta = operacaoConta;
	}

}
