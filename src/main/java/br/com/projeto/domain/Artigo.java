package br.com.projeto.domain;

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
@Table(name = "artigo")
public class Artigo extends GenericDomain {
	@Column(length = 500, nullable = false)
	private String titulo;

	@Column(length = 300, nullable = false)
	private String palavraChave;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Nivel nivel;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Classificacao classificacao;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario autor;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column(nullable = false)
	private Boolean ativo;

	@Column(nullable = true)
	private String tipoAnexo;

	@Transient
	private String caminho;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = "Não";

		if (ativo) {
			ativoFormatado = "Sim";
		}

		return ativoFormatado;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	@Transient
	public String getNomeNivel() {
		return getNivel() == null ? null : getNivel().getNome();
	}

	@Transient
	public void setNomeNivel(String nomeNivel) {

	}

	@Transient
	public String getNomeClassificacao() {
		return getClassificacao() == null ? null : getClassificacao().getNome();
	}

	@Transient
	public void setNomeClassificacao(String nomeClassificacao) {

	}

	public String getTipoAnexo() {
		return tipoAnexo;
	}

	public void setTipoAnexo(String tipoAnexo) {
		this.tipoAnexo = tipoAnexo;
	}

}
