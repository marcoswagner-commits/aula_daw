package net.ufjnet.gestaoobra.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EnviarMailService {
	
	
	private JavaMailSender mailSender;
	
	public void enviar(String dest, String subj, String text) {
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("noreply@ufjnet.ent");
		email.setTo(dest);
		email.setSubject(subj);
		email.setText(text);
		mailSender.send(email);
		
	}

}
