package mail;

//File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import play.Logger;

public class mailBL {
	// Recipient's email ID needs to be mentioned.
	public static final String DESTINATAIRE = "yaacovisraelnathan@gmail.com";
	// Sender's email ID needs to be mentioned
	public static final String ENVOYER = "serverhomehouse@gmail.com";
	final static String username = "serverhomehouse";
	final static String password = "Ny7516399";

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	public void sendLoginSuccess(String szUserName) throws AddressException, MessagingException {
		// Set the mail properties
		setProperties();
		
		// Send the mai;
		Logger.info("<MAIL>Send the mail ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(DESTINATAIRE));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ENVOYER));
		generateMailMessage.setSubject("Server say: a user was login");
		String emailBody = "Information about a login. " + "<br><br> " + szUserName
				+ " was logged into the system, <br>Shiboude Derav Nosson";
		generateMailMessage.setContent(emailBody, "text/html");
		Logger.info("<MAIL>Send the mail ===> Mail Session has been created successfully..");
		
		// Step3
		Logger.info("<MAIL>Send the mail ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
		
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", username, password);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		Logger.info("<MAIL>Your Java Program has just sent an Email successfully. Check your email..");
		
	}

	
	private static void setProperties() {
		// Step1
		Logger.info("<MAIL>Set the mail propeties ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		Logger.info("<MAIL>Set the mail propeties ===> Mail Server Properties have been setup successfully..");
	}

}
