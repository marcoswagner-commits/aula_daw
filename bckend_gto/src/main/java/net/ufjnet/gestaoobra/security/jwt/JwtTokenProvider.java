package net.ufjnet.gestaoobra.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.ufjnet.gestaoobra.services.exceptions.InvalidAuthenticationException;

@Service
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:segredo}")
	private String chaveSecreta = "segredo";
	
	@Value("${security.jwt.token.expire-lenght:3600000}")
	private long tempoValidade = 3600000;

	
	@Autowired
	private UserDetailsService userDetaisService;
	
	@PostConstruct
	protected void init() {
		chaveSecreta = Base64.getEncoder().encodeToString(chaveSecreta.getBytes());
	}
	
	public String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", roles);
		
		Date agora = new Date();
		 
		Date validade = new Date(agora.getTime() + tempoValidade);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(agora)
				.setExpiration(validade)
				.signWith(SignatureAlgorithm.HS256, chaveSecreta)
				.compact();
	}
	
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetaisService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
	}
	
	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
			//Bearer KJDKAJFLKAJFLKAJSLKFJALKSJFKLJASKLJFKJASLKFJKLA
		}
		return null;
	}
	
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			
		  return true;	
		} catch (JwtException | IllegalArgumentException ex) {
			throw new InvalidAuthenticationException("Token é inválido ou expirado!");
		}
	}
	
}
