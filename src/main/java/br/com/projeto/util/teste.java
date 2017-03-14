package br.com.projeto.util;

import java.util.Properties;

import java.util.*;
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;

public class teste {

	public static void main(String args[]) throws Exception {

		  String host = "srv01.facema.edu.br";
		  String username = "helpdesk@facema.edu.br";
		  String passwoed = "F1b()rHud@@!";

		  Properties properties = System.getProperties();
		  Session session = Session.getDefaultInstance(properties);

		  Store store = session.getStore("pop3");
		  store.connect(host, username, passwoed);

		  Folder folder = store.getFolder("inbox");

		  if (!folder.exists()) {
		  System.out.println("No INBOX...");
		  System.exit(0);
		  }
		  folder.open(Folder.READ_WRITE);
		  Message[] msg = folder.getMessages();

		  for (int i = 0; i < msg.length; i++) {
		  System.out.println("------------ Message " + (i + 1) + " ------------");
		  String from = InternetAddress.toString(msg[i].getFrom());
		  if (from != null) {
		  System.out.println("From: " + from);
		  }

		  String replyTo = InternetAddress.toString(
		  msg[i].getReplyTo());
		  if (replyTo != null) {
		  System.out.println("Reply-to: " + replyTo);
		  }
		  String to = InternetAddress.toString(
		  msg[i].getRecipients(Message.RecipientType.TO));
		  if (to != null) {
		  System.out.println("To: " + to);
		  }

		  String subject = msg[i].getSubject();
		  if (subject != null) {
		  System.out.println("Subject: " + subject);
		  }
		  Date sent = msg[i].getSentDate();
		  if (sent != null) {
		  System.out.println("Sent: " + sent);
		  }

		  System.out.println();
		  System.out.println("Message : ");

		  Multipart multipart = (Multipart) msg[i].getContent();
		  
		  for (int x = 0; x < multipart.getCount(); x++) {
		  BodyPart bodyPart = multipart.getBodyPart(x);

		  String disposition = bodyPart.getDisposition();

		  if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
		  System.out.println("Mail have some attachment : ");

		  DataHandler handler = bodyPart.getDataHandler();
		  System.out.println("file name : " + handler.getName());
		  } else {
		  System.out.println(bodyPart.getContent());
		  }
		  }
		  System.out.println();
		  }
		  folder.close(true);
		  store.close();
		  }
		} 