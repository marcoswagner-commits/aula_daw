package net.ufjnet.gestaoobra.exceptionhandler;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.ufjnet.gestaoobra.services.exceptions.BusinessException;
import net.ufjnet.gestaoobra.services.exceptions.InvalidAuthenticationException;
import net.ufjnet.gestaoobra.services.exceptions.MailException;
import net.ufjnet.gestaoobra.services.exceptions.UsernameNotFoundException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource msg;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<StandardError.Fields> erro_campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = msg.getMessage(error, LocaleContextHolder.getLocale());
			
			erro_campos.add(new StandardError.Fields(nome, mensagem));
		}
		
		
		StandardError erro = new StandardError(status.value(),LocalDateTime.now(),"Verifique o preenchimento dos campos!",erro_campos);
		
		return handleExceptionInternal(ex, erro, headers, status, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
	public ResponseEntity<StandardError> businessException (BusinessException ex) {
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(),
			LocalDateTime.now(),ex.getMessage()+ " - "+ex.getCause().getLocalizedMessage(),null);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidAuthenticationException.class)
	public ResponseEntity<StandardError> invalidAuthenticationException (InvalidAuthenticationException ex) {
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(),
			LocalDateTime.now(),ex.getMessage(),null);
		
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<StandardError> usernameNotFoundException (UsernameNotFoundException ex) {
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(),
			LocalDateTime.now(),ex.getMessage(),null);
		
		
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MailException.class)
	public ResponseEntity<StandardError> mailException (MailException ex) {
		StandardError erro = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
			LocalDateTime.now(),ex.getMessage(),null);
		
		
		return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
