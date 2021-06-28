package net.ufjnet.gestaoobra.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/nome/{nome}")
	@Operation(summary = "Busca um usuário por nome")
	public ResponseEntity<User> buscarNome(@PathVariable String nome) {
			User obj = dao.findByUsername(nome);
			return ResponseEntity.ok(obj);
	}
	
	@Operation(summary = "Autentica um usuário e retorna um token")
	@PostMapping(value = "/assinatura")
	public ResponseEntity<?> assina(@RequestBody UserDTO objDTO) {
		try {
			
			String username = objDTO.getUsername();
			String password = objDTO.getPassword();
			
						
			User obj = dao.findByUsername(username);
			
			
			String token = "";
			
			if (obj.getUsername() != null) {
				token = tokenProvider.createToken(obj.getUsername(), obj.getRoles());
			} else {
				throw new UsernameNotFoundException("Usuário " + obj.getUsername() + " não encontrado!");
			}
			
						
			UsernamePasswordAuthenticationToken tok = new UsernamePasswordAuthenticationToken(username, password);
			
						
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

