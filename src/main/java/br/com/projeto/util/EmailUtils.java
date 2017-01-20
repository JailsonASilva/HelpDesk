package br.com.projeto.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtils {
	private static final String HOSTNAME = "correio.facema.edu.br";
	private static final String USERNAME = "naoresponda@facema.edu.br";
	private static final String PASSWORD = "F1b()rHud@@!";
	private static final String EMAILORIGEM = "naoresponda@facema.edu.br";

	public static String getHostname() {
		return HOSTNAME;
	}

	public static String getUsername() {
		return USERNAME;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static String getEmailorigem() {
		return EMAILORIGEM;
	}

	@SuppressWarnings("deprecation")
	public static Email conectaEmail() throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setTLS(true);
		email.setFrom(EMAILORIGEM);
		return email;
	}

	@SuppressWarnings("unused")
	public static void enviaEmail(String titulo, String mensagem, String destino) throws EmailException {
		Email email = new SimpleEmail();
		
		email = conectaEmail();
		
		email.setSubject(titulo);
		email.setMsg(mensagem);
		email.addTo(destino);
		
		String resposta = email.send();
		
		System.out.println("E-mail enviado com sucesso para: " + destino);
	}

}
