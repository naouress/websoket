package showroom.persistence.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	public void envoyer(String username,String password,String subjectS, String messageS){
		// Etape 1 : Création de la session
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");
		Session session = Session.getInstance(props,
		new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
		}
		});
		try {
		// Etape 2 : Création de l'objet Message
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO,
		InternetAddress.parse("naoures.mahfoudh@gmail.com"));
		message.setSubject(subjectS);
		message.setText(messageS);
		// Etape 3 : Envoyer le message
		Transport.send(message);
		System.out.println("Message_envoye");
		} catch (MessagingException e) {
		throw new RuntimeException(e);
		} }

		
	}


