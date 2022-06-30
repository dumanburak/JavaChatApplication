package confab;

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

public class JavaMailUtil {
	public static void sendMail(String recepient) throws MessagingException {
		System.out.println("Preparing to send");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		final String myAccountEmail = "confabpremium@gmail.com";
		final String password = "q7gutisimao";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		Message message = prepareMessage(session, myAccountEmail, recepient);
		
		Transport.send(message);
		System.out.println("Message sent");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Verification Code");
			int code = 112312;
			String htmlCode = "<h1> Welcome To Confab Premium!</h1> <br> <h2>Your Verification Code Is "+code+"</h2>";
			message.setContent(htmlCode, "text/html");
			//message.setText("Your Verification Code Is 123123");
			return message;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
