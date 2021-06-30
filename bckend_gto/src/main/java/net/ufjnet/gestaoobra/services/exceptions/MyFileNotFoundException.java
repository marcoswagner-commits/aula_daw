package net.ufjnet.gestaoobra.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MyFileNotFoundException(String msg) {
		super(msg);
	}

	public MyFileNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
