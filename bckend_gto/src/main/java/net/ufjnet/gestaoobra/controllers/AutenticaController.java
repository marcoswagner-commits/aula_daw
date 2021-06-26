package net.ufjnet.gestaoobra.controllers;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.ufjnet.gestaoobra.models.User;
import net.ufjnet.gestaoobra.repositories.UserDAO;
import net.ufjnet.gestaoobra.security.UserDTO;
import net.ufjnet.gestaoobra.security.jwt.JwtTokenProvider;

@Tag(name = "Autenticação Endpoint") 
@RestController
@RequestMapping("/autentica")
public class AutenticaController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserDAO dao;
	
	@Operation(summary = "Autentica um usuário e retorna um token")
	@PostMapping(value = "/assinatura")
	public ResponseEntity<?> assina(@RequestBody UserDTO objDTO) {
		try {
			
			String username = objDTO.getUsername();
			String password = objDTO.getPassword();
			
			System.out.println("/1/");
			System.out.println(username+" - "+password);
			System.out.println("/1/");
			
			User obj = dao.findByUsername(username);
			obj.setAccountNonExpired(true);
			obj.setAccountNonLocked(true);
			obj.setCredentialsNonExpired(true);
			obj.setEnabled(true);
			
			String token = "";
			
			if (obj.getUsername() != null) {
				token = tokenProvider.createToken(obj.getUsername(), obj.getRoles());
			} else {
				throw new UsernameNotFoundException("Usuário " + obj.getUsername() + " não encontrado!");
			}
			
			System.out.println("/2/");
			System.out.println(obj.isAccountNonLocked());
			System.out.println("/2/");
			
			UsernamePasswordAuthenticationToken tok = new UsernamePasswordAuthenticationToken(username, password);
			
			System.out.println("/3/");
			System.out.println(tok);
			System.out.println("/3/");
			
			authenticationManager.authenticate(tok);
				
			
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", obj.getUsername());
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}
}

