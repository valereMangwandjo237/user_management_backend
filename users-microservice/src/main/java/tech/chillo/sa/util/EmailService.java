package tech.chillo.sa.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class EmailService implements EmailSender{
	
	private final JavaMailSender mailSender;
	
	@Override
	public void sendEmail(String email, String body) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(body, true);
			helper.setTo(email);
			helper.setSubject("confirmez votre adresse email");
			helper.setFrom("vmangwandjo@gmail.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new IllegalStateException("Echec envoie de mail...");
		}
		
	}

}
