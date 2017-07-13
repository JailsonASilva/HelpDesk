package br.com.projeto.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
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
@Table(name = "rpa")
public class Rpa extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column(nullable = false, length = 1000)
	private String referente;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date data;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataPrevistaFim;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal valor;

	@Column(length = 3, nullable = true)
	private String valorLiquido;

	@Column(length = 3, nullable = true)
	private String pagouISS;

	@Column(length = 1000, nullable = true)
	private String observacao;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dataPrevistaPagamento;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean carteiraIdentidade;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean cpf;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean tituloEleitor;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean comprovanteResidencia;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean cartaoPIS;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean contaBancaria;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean recebidoRH;

	@Column(nullable = false, columnDefinition = "boolean default False")
	private Boolean pendente;

	@Column(length = 10, nullable = false)
	private String aprovado;

	@Column(length = 1000, nullable = true)
	private String motivoReprovacao;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getReferente() {
		return referente;
	}

	public void setReferente(String referente) {
		this.referente = referente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(String valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public String getPagouISS() {
		return pagouISS;
	}

	public void setPagouISS(String pagouISS) {
		this.pagouISS = pagouISS;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataPrevistaPagamento() {
		return dataPrevistaPagamento;
	}

	public void setDataPrevistaPagamento(Date dataPrevistaPagamento) {
		this.dataPrevistaPagamento = dataPrevistaPagamento;
	}

	public Date getDataPrevistaFim() {
		return dataPrevistaFim;
	}

	public void setDataPrevistaFim(Date dataPrevistaFim) {
		this.dataPrevistaFim = dataPrevistaFim;
	}

	public Boolean getCarteiraIdentidade() {
		return carteiraIdentidade;
	}

	public void setCarteiraIdentidade(Boolean carteiraIdentidade) {
		this.carteiraIdentidade = carteiraIdentidade;
	}

	public Boolean getCpf() {
		return cpf;
	}

	public void setCpf(Boolean cpf) {
		this.cpf = cpf;
	}

	public Boolean getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(Boolean tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public Boolean getComprovanteResidencia() {
		return comprovanteResidencia;
	}

	public void setComprovanteResidencia(Boolean comprovanteResidencia) {
		this.comprovanteResidencia = comprovanteResidencia;
	}

	public Boolean getCartaoPIS() {
		return cartaoPIS;
	}

	public void setCartaoPIS(Boolean cartaoPIS) {
		this.cartaoPIS = cartaoPIS;
	}

	public Boolean getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(Boolean contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public String getAprovado() {
		return aprovado;
	}

	public void setAprovado(String aprovado) {
		this.aprovado = aprovado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getRecebidoRH() {
		return recebidoRH;
	}

	public void setRecebidoRH(Boolean recebidoRH) {
		this.recebidoRH = recebidoRH;
	}

	@Transient
	public String getRecebidoRHFormatado() {
		String recebidoRHFormatado = "Não";

		if (recebidoRH) {
			recebidoRHFormatado = "Sim";
		}

		return recebidoRHFormatado;
	}

	@Transient
	public String getPendenteFormatado() {
		String pendenteFormatado = "Não";

		if (pendente) {
			pendenteFormatado = "Sim";
		}

		return pendenteFormatado;
	}

	@Transient
	public String getValorFormatado() {

		String valorFormatado = NumberFormat.getCurrencyInstance().format(valor);

		return valorFormatado;

	}

	@Transient
	public String getDataFormatada() {
		String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(data);

		return dataFormatada;
	}

	@Transient
	public String getDataInicioFortamada() {
		String dataInicioFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataInicio);

		return dataInicioFormatada;
	}

	@Transient
	public String getDataPrevistaFimFortamada() {
		String dataPrevistaFimFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataPrevistaFim);

		return dataPrevistaFimFormatada;
	}

	public Boolean getPendente() {
		return pendente;
	}

	public void setPendente(Boolean pendente) {
		this.pendente = pendente;
	}

	public String getMotivoReprovacao() {
		return motivoReprovacao;
	}

	public void setMotivoReprovacao(String motivoReprovacao) {
		this.motivoReprovacao = motivoReprovacao;
	}

}
