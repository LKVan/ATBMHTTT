package support;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;




public class SendEmail {
	private String from = "van06082002@gmail.com";
	private String password = "poos kfar fzfr llld";
	private String nameShop = "";
	
	public void sendEmail(String sendTo,String subject,String content) {
	
	      // Get system properties
	      Properties properties = new Properties();
	      properties.put("mail.smtp.host", "smtp.gmail.com");
	      properties.put("mail.smtp.port", "587");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      

	      // Setup mail server
	     // properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties,new Authenticator() {
	    	  @Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		// TODO Auto-generated method stub
	    		return new PasswordAuthentication(from, password);
	    	}
		});

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         
	         message.setHeader("Content-Type", "text/html; charset=UTF-8");

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));
//	         message.setFrom("Tiệm Bánh Handmade");
	         
	         

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));

	         // Set Subject: header field
	         message.setSubject(subject);

	         // Now set the actual message
//	         message.setText(content);
//	         message.setContent(content,"text/html");
	         
	         message.setText(content, "utf-8", "html");
	         
	         
	        

	         // Send message
	         Transport.send(message);
	         System.out.println(content);
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNameShop(String nameShop) {
		this.nameShop = nameShop;
	}
	
	
	
	
}

