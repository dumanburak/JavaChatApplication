package confab;

import javax.mail.MessagingException;

public class JavaMail {
	public static void main(String[] args) {
		try {
			JavaMailUtil.sendMail("brkdmn190399@gmail.com");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
