package net.ufjnet.gestaoobra.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MailException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MailException(String msg) {
		super(msg);
	}

	public MailException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
