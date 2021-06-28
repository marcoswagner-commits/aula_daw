# Aula 18 - Desenvolvimento de Aplicações WEB

> Aula 12/08/2021
> 
>  * Estudo de caso: Gestão de Obras *


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Povoamento de Usuários/Permissões - Teste da geração do Token - Envio de E-mails


### Passo 1: Povoar usuários e permissões
- [x] Concluir a classe Permission (anotação ManytoMany)
- [x] Criar a classe PermissionDAO
  - Criar duas permissões (ADMIN e USUARIO)
  - Criar dois usuários
- [x] Povoar as duas classes/tabelas por meio do CommandLineRunner
- [x] Colocar o bcript para gerar as senhas
- [x] Alterar no pacote Config a classe ConfigSecurity para aceitar qualquer acesso
- [x] Criar um método temporário para consulta de usuários e verificação de senhas no Controller
  
 - [ ] [códigos de users e permissions](#código-atualizado)


### Passo 2: Verificar funcionamento do JWT
- [x] Verificar funcionamento por meio do Swagger
- [x] Verificar funcionamento por meio do PostMan
  - Analisar o processo de criação, validação e retorno do token (Figura abaixo)
  - Acessar a página https://jwt.io
 - [ ] [código do JwtTokenProvider](#código-jwttokenprovider)

![JWT](https://user-images.githubusercontent.com/81576640/123691376-ef01d480-d82b-11eb-998c-7507fccddcbf.png)

### Passo 3: Implementar o envio de e-mails
- [x] Inserir a dependência spring-boot-starter-mail
- [x] Inserir a configuração de e-mail no arquivo application.properties
- [x] Criar uma classe EnviarMailService no pacote Services
  - Criar um método "enviar" com parâmetros (destinatário, assunto e conteúdo)
  - Criar um método doFilter

- [ ] [código do EnviarMailService](#código-enviarmailservice)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=FFVQ0pzuh6c)
-
🥈:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=inLcdThy9YM)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=p35mSdb9BaQ)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=Gim5AAmYOEE)



```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

```

```
spring.mail.host=smtps.uhserver.com
spring.mail.port=993
spring.mail.protocol=smtp
spring.mail.username=noreply@ufjnet.net
spring.mail.password=????
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.quitwait=false 


```



### Código atualizado
- classe PermissionDAO (na camada repositories)
```
package net.ufjnet.gestaoobra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.gestaoobra.models.Permission;

public interface PermissionDAO extends JpaRepository<Permission, Integer> {
	


}


```
- classe BckendGtoApplication (classe principal/inicial)
```

package net.ufjnet.gestaoobra;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.ufjnet.gestaoobra.models.Item;
import net.ufjnet.gestaoobra.models.Lancamento;
import net.ufjnet.gestaoobra.models.Obra;
import net.ufjnet.gestaoobra.models.Permission;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.models.SubItem;
import net.ufjnet.gestaoobra.models.User;
import net.ufjnet.gestaoobra.repositories.ItemDAO;
import net.ufjnet.gestaoobra.repositories.LancamentoDAO;
import net.ufjnet.gestaoobra.repositories.ObraDAO;
import net.ufjnet.gestaoobra.repositories.PermissionDAO;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;
import net.ufjnet.gestaoobra.repositories.SubItemDAO;
import net.ufjnet.gestaoobra.repositories.UserDAO;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BckendGtoApplication implements CommandLineRunner {

	@Autowired
	private ProprietarioDAO propDAO;
	
	@Autowired
	private ObraDAO obraDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private SubItemDAO subitemDAO;
	
	@Autowired
	private LancamentoDAO lancamentoDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PermissionDAO pmDAO;
	
	private String r1, r2;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(BckendGtoApplication.class, args);
		


	}

	
	@Override
	public void run(String... args) throws Exception {
		
		//BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(16);
		r1 = "123"; //bCrypt.encode("admin123");
		r2 = "456"; //bCrypt.encode("user123");
		
		
		
		Proprietario p1 = new Proprietario(1,"Marcos","123","marcoswagner@gmail.com");
		Proprietario p2 = new Proprietario(2,"Jose","456","jose@gto");
		Proprietario p3 = new Proprietario(3,"Maria","789","maria@gto");
		
		Obra o1 = new Obra(1,"Sobrado com 4 suites", "Rua Dona Olimpia, 1414, Vila Fátima","",p1);
		Obra o2 = new Obra(2,"Casa geminada", "Rua 15, 1515, Setor Hermosa","",p2);
		Obra o3 = new Obra(3,"Casa com 3 quartos", "Rua 16, 1616, Setor Brisas","",p3);
		Obra o4 = new Obra(4,"Casa de Alto Padrão", "Rua 17, 1717, 1414, Setor Cohacol","",p3);
		
		Item i1 = new Item(1,"Material Básico","");
		Item i2 = new Item(2,"Material de Acabamento","");
		Item i3 = new Item(3,"Material Pintura","");
		Item i4 = new Item(4,"Mão-de-Obra","");
		Item i5 = new Item(5,"Locações","");
		
		SubItem si1 = new SubItem(1,"Fio Elétrico","",i1);
		SubItem si2 = new SubItem(2,"Cimento","",i1);
		SubItem si3 = new SubItem(3,"Tijolo","",i1);
		
		SubItem si4 = new SubItem(4,"Argamassa","",i2);
		SubItem si5 = new SubItem(5,"Porcelanato","",i2);
		
		SubItem si6 = new SubItem(6,"Massa Corrida","",i3);
		SubItem si7 = new SubItem(7,"Tinta","",i3);
		
		SubItem si8 = new SubItem(8,"Pedreiro","",i4);
		
		
		Lancamento l1 = new Lancamento(1,o1,i1,si1,150.50,"Parte Elétrica","","");
		Lancamento l2 = new Lancamento(2,o1,i1,si2,250.00,"Contra-Piso","","");
		Lancamento l3 = new Lancamento(3,o1,i2,si4,250.00,"Piso","","");
		Lancamento l4 = new Lancamento(4,o2,i2,si5,250.00,"Piso","","");
		Lancamento l5 = new Lancamento(5,o2,i3,si6,250.00,"Contra-Piso","","");
		Lancamento l6 = new Lancamento(6,o3,i4,si8,250.00,"Contra-Piso","","");
		Lancamento l7 = new Lancamento(7,o3,i4,si8,250.00,"Contra-Piso","","");
		Lancamento l8 = new Lancamento(8,o4,i3,si7,250.00,"Contra-Piso","","");
		Lancamento l9 = new Lancamento(9,o4,i4,si2,250.00,"Contra-Piso","","");
		Lancamento l10 = new Lancamento(10,o4,i4,si8,250.00,"Contra-Piso","","");
		
		User u1 = new User();
		u1.setUsername("marcos_admin");
		u1.setFullName("Marcos Wagner");
		u1.setPassword(r1);
		u1.setAccountNonExpired(true);
		u1.setAccountNonLocked(true);
		u1.setCredentialsNonExpired(true);
		u1.setEnabled(true);
		
		User u2 = new User();
		u2.setUsername("marcos_user");
		u2.setFullName("Marcos Ribeiro");
		u2.setPassword(r2);
		u2.setAccountNonExpired(true);
		u2.setAccountNonLocked(true);
		u2.setCredentialsNonExpired(true);
		u2.setEnabled(true);
		
		Permission pm1 = new Permission();
		pm1.setDescricao("ADMIN");
		Permission pm2 = new Permission();
		pm2.setDescricao("USUARIO");
		
		
		pm1.getUsers().addAll(Arrays.asList(u1));
		pm1.getUsers().addAll(Arrays.asList(u1,u2));
		
		u1.getPermissions().addAll(Arrays.asList(pm1,pm2));
		u2.getPermissions().addAll(Arrays.asList(pm2));
		
		
		
		propDAO.saveAll(Arrays.asList(p1,p2,p3));
		obraDAO.saveAll(Arrays.asList(o1,o2,o3,o4));
		itemDAO.saveAll(Arrays.asList(i1,i2,i3,i4,i5));
		subitemDAO.saveAll(Arrays.asList(si1,si2,si3,si4,si5,si6,si7,si8));
		lancamentoDAO.saveAll(Arrays.asList(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10));
		
		pmDAO.saveAll(Arrays.asList(pm1,pm2));
		
		userDAO.saveAll(Arrays.asList(u1,u2));
	}

}


```
- classe ConfigSecurity (na camada Config)

```
package net.ufjnet.gestaoobra.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import net.ufjnet.gestaoobra.security.jwt.JwtConfigurer;
import net.ufjnet.gestaoobra.security.jwt.JwtTokenProvider;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/autentica/assinatura", "/v1/gto/**", "/v3/api-docs/**", "/swagger-ui.html**").permitAll()
				//.antMatchers("/v1/gto/**").authenticated()
				.antMatchers("/users").denyAll()
				.and()
				.apply(new JwtConfigurer(tokenProvider));
		
		}


	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}

	
```
- classe AutenticaController (na camada Controllers)

```
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

```

### Código JwtTokenProvider

```
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
	
	@Value("${security.jwt.token.secret-key:gto_obras_sec01}")
	private String chaveSecreta = "gto_obras_sec01";
	
	@Value("${security.jwt.token.expire-length:3600000}")
	private long tempoValidade = 3600000; //1h
	
	@Autowired
	private UserDetailsService userDetailsService;
	
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
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
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
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidAuthenticationException("JWT Token expirado ou inválido");
		}
	}

}


```
[voltar](#passo-2-criar-jwt---jwttokenprovider)

### Código EnviarMailService
```
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

```
[voltar](#passo-3-criar-jwt---jwttokenfilter-e-jwttokenconfigure)


### Código GestaoProprietario
```
@Transactional
	public ProprietarioDTO save(ProprietarioDTO obj) {
		Proprietario entity = new Proprietario(obj.getCodigo(), obj.getNome(), obj.getCpf(), obj.getEmail());
		
		boolean cpfExists = dao.findByCpf(entity.getCpf())
				.stream()
				.anyMatch(objResult -> !objResult.equals(entity));
		
		if (cpfExists) {
			throw new BusinessException("CPF já existente!");
		}
		
		boolean emailExists = dao.findByEmail(entity.getEmail())
				.stream()
				.anyMatch(objResult -> !objResult.equals(entity));
		
		if (emailExists) {
			throw new BusinessException("E-mail já existente!");
		}
		
		try {
			String textoMail = "Informamos que seus dados foram cadastrados no sistema Gestão de Obras";
			email.enviar(entity.getEmail(), "Cadastro Efetuado!", textoMail);
		} catch (Exception e) {
			throw new BusinessException("Erro no envio do e-mail!");
		}
		
		
		
		return new ProprietarioDTO(dao.save(entity));
	}
```

[voltar](#passo-3-criar-jwt---jwttokenfilter-e-jwttokenconfigure)



### Passo 4: Atualizar o github com os códigos atuais (Envio de Email)

