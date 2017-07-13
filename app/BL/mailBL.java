package BL;

import java.util.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;


import Entity.WebResponce;
import File.FileGetter;
import play.Logger;
import DB.getterDB;

import javax.activation.DataSource;

/**
 * Managing the mail's
 *
 * @author Yaacov
 */
public class mailBL {
    private WebResponce webResponce = new WebResponce();
    // Recipient's email ID needs to be mentioned.
    public static final String DESTINATAIRE = "yaacovisraelnathan@gmail.com";
    // Sender's email ID needs to be mentioned
    public static final String ENVOYER = "shamayimnadlan@gmail.com";
    final static String username = "shamayimnadlan";
    final static String password = "sn123456";
    private static String HOUSES_DOCUMENTS_DIR = "HousesDocuments";
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    private FileGetter fileGetter = new FileGetter();
    private static getterDB getterDb = new getterDB();

    public void sendLoginSuccess(String szUserName, String clientIp) throws AddressException, MessagingException {
        // Set the mail properties
        setProperties();

        // Send the mai;
        Logger.info("<MAIL> Send the mail ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(DESTINATAIRE));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ENVOYER));
        generateMailMessage.setSubject("Server say: a user was login");
        String emailBody = "Information about a login. " + "<br><br> " + szUserName
                + " was logged into the system,<br>" + szUserName + " was logged from IP: " + clientIp + ", <br>Shiboude Derav Nosson";
        generateMailMessage.setContent(emailBody, "text/html");
        Logger.info("<MAIL> Send the mail ===> Mail Session has been created successfully..");

        // Step3
        Logger.info("<MAIL> Send the mail ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", username, password);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
        Logger.info("<MAIL> Your Java Program has just sent an Email successfully. Check your email..");

    }

    public void sendHouseDetailesToAClient(String szHouseName, String szEmailAdressClient) throws AddressException, MessagingException {
        // Set the mail properties
        setProperties();

        // Send the mai;
        Logger.info("<MAIL> Send the mail ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(szEmailAdressClient));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ENVOYER));
        generateMailMessage.setSubject("Shanayin Nadlan, House: " + szHouseName);
        Logger.info("<MAIL> Send the mail ===> Mail Session has been created successfully..");

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText(szHouseName);

        // Create a multipar message
        Multipart multipart = new MimeMultipart();
        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = szHouseName;
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        generateMailMessage.setContent(multipart);


        // Step3
        Logger.info("<MAIL> Send the mail ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", username, password);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
        Logger.info("<MAIL> Your Java Program has just sent an Email successfully. Check your email..");

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

    private static void addAttachment(Multipart multipart, String szFilePath, String szFileName) throws MessagingException {
        DataSource source = new FileDataSource(szFilePath);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(szFileName);
        multipart.addBodyPart(messageBodyPart);
    }

    public void sendHouse(String szHouseName, String szHouseToPrint, String szEmailAdressClient, boolean isPermitedToViewDocuments) {
        webResponce = new WebResponce();
        // Set the mail properties
        setProperties();

        // Send the mai;
        Logger.info("<MAIL> Send the mail ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        try {
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(szEmailAdressClient));
            generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ENVOYER));
            generateMailMessage.setSubject("Shamayim Nadlan, House: " + szHouseName);

            Logger.info("<MAIL> Send the mail ===> Mail Session has been created successfully..");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(szHouseToPrint);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String szFilePath;

            // Itterrate Over Files To Add An Attachment To The Mail
            ArrayList<String> lstExistingFiles = new ArrayList<>();
            // ArrayList<String> lstExistingFiles = fileGetter.getFilesName(HOUSES_DOCUMENTS_DIR, szHouseName);
            ArrayList<String> lstHousePhotos = fileGetter.getFilesName(HOUSES_DOCUMENTS_DIR, szHouseName);
            lstExistingFiles.addAll(lstHousePhotos);
            // if he as persmissoin to view documnets
            if(isPermitedToViewDocuments)
            {
                ArrayList<String> lstHouseDocuments = fileGetter.getFilesName(HOUSES_DOCUMENTS_DIR, szHouseName + "\\Docs");
                lstExistingFiles.addAll(lstHouseDocuments);
            }
            Iterator<String> ltrFiles = lstExistingFiles.iterator();
            String currFile = null;
            if (ltrFiles.hasNext()) {
                currFile = ltrFiles.next();
            }
            while (currFile != null) {
                String currFilePath = currFile.replace("/", "\\");
                szFilePath = System.getProperty("user.dir") + "\\" + HOUSES_DOCUMENTS_DIR + "\\" + currFilePath;
                addAttachment(multipart, szFilePath, currFilePath);
                if (ltrFiles.hasNext()) {
                    currFile = ltrFiles.next();
                } else {
                    currFile = null;
                }

            }


            ///
            // Send the complete message parts
            generateMailMessage.setContent(multipart);


            // Step3
            Logger.info("<MAIL> Send the mail ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password
            // if you have 2FA enabled then provide App Specific Password
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            Logger.info("<MAIL> Your Java Program has just sent an Email successfully. Check your email..");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendFunction(String clientIp, String szMessage) throws AddressException, MessagingException {
        // Set the mail properties
        setProperties();

        // Send the mai;
        Logger.info("<MAIL> Send the mail ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(DESTINATAIRE));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ENVOYER));
        generateMailMessage.setSubject("Server say: a user was login");
        String emailBody = "Information about a client in ip" + clientIp + "****************************<br>" + szMessage + ", <br>Shamayim Nadlan";
        generateMailMessage.setContent(emailBody, "text/html");
        Logger.info("<MAIL> Send the mail ===> Mail Session has been created successfully..");

        // Step3
        Logger.info("<MAIL> Send the mail ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", username, password);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
        Logger.info("<MAIL> Your Java Program has just sent an Email successfully. Check your email..");

    }

}
