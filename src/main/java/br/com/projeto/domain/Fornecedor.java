package br.com.projeto.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "fornecedor")
public class Fornecedor extends GenericDomain {

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 60, nullable = true)
	private String naturalidade;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dataNascimento;

	@Column(length = 10, nullable = true)
	private String sexo;

	@Column(length = 50, nullable = true)
	private String estadoCivil;

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

	@Column(length = 100, nullable = true)
	private String pai;

	@Column(length = 100, nullable = true)
	private String mae;

	@Column(length = 80, nullable = false)
	private String rg;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date emissaoRg;

	@Column(length = 20, nullable = true)
	private String orgaoEmissor;

	@Column(length = 20, nullable = true)
	private String cpf;

	@Column(length = 30, nullable = true)
	private String pis;

	@Column(length = 40, nullable = true)
	private String tituloEleitoral;

	@Column(length = 10, nullable = true)
	private String zona;

	@Column(length = 10, nullable = true)
	private String secao;

	@Column(length = 25, nullable = false)
	private String telefone_1;

	@Column(length = 25, nullable = true)
	private String telefone_2;

	@Column(length = 80, nullable = true)
	private String email;

	@Column(length = 100, nullable = false)
	private String banco;

	@Column(length = 30, nullable = false)
	private String agencia;

	@Column(length = 30, nullable = false)
	private String numeroConta;

	@Column(length = 30, nullable = false)
	private String tipoConta;

	@Column(length = 20, nullable = true)
	private String operacaoConta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Transient
	public String getDataNascimentoFormatada() {
		String dataNascimentoFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento);

		return dataNascimentoFormatada;
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

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getOperacaoConta() {
		return operacaoConta;
	}

	public void setOperacaoConta(String operacaoConta) {
		this.operacaoConta = operacaoConta;
	}

}
