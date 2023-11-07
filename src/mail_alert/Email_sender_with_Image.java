package mail_alert;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Email_sender_with_Image {

	
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
                   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("naresh.narsingh@routemobile.com", "ntjzpxkcnpmlwlgo");
		      }
		   });
           new Email_sender_with_Image().sendmail(session,"ranjeet.vishwakarma@routemobile.com","testing","hi ranjeet");
		    
        
    }


public static void sendmail(Session session, String toEmail, String subject, String body){

	try{
         MimeMessage msg = new MimeMessage(session);
         msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	     msg.addHeader("format", "flowed");
	     msg.addHeader("Content-Transfer-Encoding", "8bit");
	      
	     msg.setFrom(new InternetAddress("naresh.narsingh@routemobile.com", "NoReply-JD"));

	    // msg.setreplyto(InternetAddress.parse("ranjeet.vishwakarma@routemobile.com", false));

	     msg.setSubject(subject, "UTF-8");

	     msg.setSentDate(new Date());

	     msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      
         // Create the message body part
         BodyPart messageBodyPart = new MimeBodyPart();

         messageBodyPart.setText(body);
         
         // Create a multipart message for attachment
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Second part is image attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "/home/ranjeet.vishwakarma/Pictures/Ranjeet.png";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         //Trick is to add the content-id header here
         messageBodyPart.setHeader("Content-ID", "image_id");
         multipart.addBodyPart(messageBodyPart);

         //third part for displaying image in the email body
         messageBodyPart = new MimeBodyPart();
         messageBodyPart.setContent("<h1>Attached Image</h1>" +
        		     "<img src='cid:image_id'>", "text/html");
         multipart.addBodyPart(messageBodyPart);
         
         //Set the multipart message to the email message
         msg.setContent(multipart);

         // Send message
         Transport.send(msg);
         System.out.println("EMail Sent Successfully with image!!");
      }catch(Exception e){
           System.out.println(e);
        };
}
}
	
