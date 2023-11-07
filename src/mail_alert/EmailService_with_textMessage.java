package mail_alert;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailService_with_textMessage {

	
//	public boolean sendEmail(String subject,String message,String to) {
//
//		boolean isSend=false;
//		
//		
//		try {
//			sendmail("testing","Hi Naresh How are you","naresh.narsingh@routemobile.com");
//		    isSend=true;
//		} catch (MessagingException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return isSend;
//		
//	}
        
        
        public static void main(String[] args) throws MessagingException, IOException {
            
           new EmailService_with_textMessage().sendmail("testing","Hi Naresh How are you","naresh.narsingh@routemobile.com");
		    
        
    }
	
	
	void sendmail(String subject,String message,String to) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   
		  
		   
		   Session session = Session.getInstance(props, new Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("ranjeet127710@gmail.com", "ahabxuhyslwgpvzt");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("ranjeet127710@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		   msg.setSubject(subject);
		   msg.setContent(message, "text/html");
		   msg.setSentDate(new Date());

//		   MimeBodyPart messageBodyPart = new MimeBodyPart();
//		   messageBodyPart.setContent("Tutorials point email", "text/html");
//
//		   Multipart multipart = new MimeMultipart();
//		   multipart.addBodyPart(messageBodyPart);
//		   MimeBodyPart attachPart = new MimeBodyPart();
//
//		   attachPart.attachFile("/var/tmp/image19.png");
//		   multipart.addBodyPart(attachPart);
//		   msg.setContent(multipart);
		   Transport.send(msg);   
		}
}