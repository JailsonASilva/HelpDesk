package br.com.projeto.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class BarraFerramentaBean implements Serializable{
	
	public void ticketInterno() {
		try {
			Faces.redirect("./pages/ticketInterno.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}
	
	
	public void ticketExterno() {
		try {
			Faces.redirect("./pages/ticketExterno.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}
	
	public void ticketDepartamento() {
		try {
			Faces.redirect("./pages/ticketDepartamento.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}
	
	public void ticketUsuario() {
		try {
			Faces.redirect("./pages/ticketUsuario.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("Não foi possível Desconectar. Erro: " + erro.getMessage());
		}
	}		

}
