package br.com.projeto.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UtilitariosBean implements Serializable {

	private StreamedContent arquivo;

	public StreamedContent getArquivo() {
		return arquivo;
	}

	public void setArquivo(StreamedContent arquivo) {
		this.arquivo = arquivo;
	}

	public void gvHelp() {
		try {
			InputStream stream = new FileInputStream("C:/Utilitários/GVHELP.exe");
			arquivo = new DefaultStreamedContent(stream, "exe/exe", "GVHELP.exe");

		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar efetuar o download da foto");
			erro.printStackTrace();
		}
	}
	
	public void ammy() {
		try {
			InputStream stream = new FileInputStream("C:/Utilitários/Ammyy Admin 3.5.exe");
			arquivo = new DefaultStreamedContent(stream, "exe/exe", "Ammyy Admin 3.5.exe");

		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar efetuar o download da foto");
			erro.printStackTrace();
		}
	}	
	
	public void winbox() {
		try {
			InputStream stream = new FileInputStream("C:/Utilitários/winbox.exe");
			arquivo = new DefaultStreamedContent(stream, "exe/exe", "winbox.exe");

		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar efetuar o download da foto");
			erro.printStackTrace();
		}
	}	
	
	public void xenCenter() {
		try {
			InputStream stream = new FileInputStream("C:/Utilitários/XenServer-6.5.0-XenCenterSetup.exe");
			arquivo = new DefaultStreamedContent(stream, "exe/exe", "XenServer-6.5.0-XenCenterSetup.exe");

		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar efetuar o download da foto");
			erro.printStackTrace();
		}
	}		

}
