package com.knowit.notificationService.services;

import org.springframework.stereotype.Service;
import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.knowit.notificationService.entities.EmailRequest;

@Service
public class EmailService {

	public String sendMail(EmailRequest emailRequest) {
		
		String host = "smtp.gmail.com";
		String from = "maheshpbharati@coep.sveri.ac.in";
		Properties properties = System.getProperties();
		
		//System.out.println("properties" + properties);
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("maheshpbharati@coep.sveri.ac.in", "Mahesh@99");
			}
		});
		session.setDebug(true);
		
		MimeMessage m = new MimeMessage(session);
		try
		{
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRequest.getTo()));
			m.setSubject(emailRequest.getSubject());
			String path="C:\\Users\\KCMI\\OneDrive\\Pictures\\Screenshots\\adding string.png";
		
			MimeMultipart mimeMultipart = new MimeMultipart();
			MimeBodyPart textMime = new MimeBodyPart();
			MimeBodyPart fileMine = new MimeBodyPart();
			try {
				textMime.setText(emailRequest.getMassage());
				File file = new File(path);
				fileMine.attachFile(file);
				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMine);
				m.setContent(mimeMultipart);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			Transport.send(m);
			System.out.println("massage send sucessfully");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "Email send successfully";
	}
	
}
