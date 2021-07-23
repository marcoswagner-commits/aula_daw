package net.ufjnet.gestaoobra.config;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EnviarMailService {
	
	
	private JavaMailSender mailSender;
	
	public void enviar(String dest, String subj, String text) {
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("fabricaswufj@gmail.com");
		email.setTo(dest);
		email.setSubject(subj);
		email.setText(text);
		mailSender.send(email);
		
	}

}
