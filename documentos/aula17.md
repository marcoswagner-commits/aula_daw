# Aula 17 - Desenvolvimento de Aplicações WEB

> 
> 
>  * Estudo de caso: Gestão de Obras *


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Autenticação com JWT e Spring Security


#

![Relação entre Lancamento - Usuário e Permissões](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/bf5d7c17f9f1096d18524edf67596225abc4e149/documentos/User_Permissions.png)

### Passo 1: Criar as relações de usuários e permissões
- [x] Concluir a tabela/entidade de usuários (user)
- [x] Verificar a criação das tabelas no Banco de Dados
- [x] Criar a interface de UserDAO
  - Criar uma assinatura para busca do username usando uma JPQL   
- [x] Criar a classe GestaoUser (services)
  - Implementar a Interface UserDetailsService
  - Fazer o vínculo com o UserDAO
  - Criar um método para busca de userName
- [x] Criar um pacote Security
  - Criar uma classe ContaDTO com username e password
- [x] Criar uma classe InvalidAuthenticationException (similar a BusinessException)
  - Anotar com @ResponseStatus(HttpStatus.BAD_REQUEST)
  - Incluir herança (extends) para AuthenticationException
  - Incluir um construtor
- [x] Atualizar a classe ExceptionHandler com a classe InvalidAuthenticationException
  - Criar o método InvalidAuthenticationException
 - [ ] [códigos de users e permissions](#código-atualizado)


### Passo 2: Criar JWT - JwtTokenProvider
- [x] Criar um pacote dentro de Security com o nome JWT
- [x] Criar no pacote JWT a classe JwtTokenProvider
  - Colocar anotação @Service
  - Criar dois atributos: chave_secreta (String = "segredo") e tempo_validade (long = 3.600.000 - 1hora)
  - Anotar o atributo chave_secreta com @Value("${security.jwt.token.secret-key:segredo}")
  - Anotar o atributo tempo_validade com @Value("${security.jwt.token.expire-lenght:3600000}")
  - Injetar a classe UserDetailsService
  - Criar um método init (@PostConstruct) e "encodar" o secretkey
  - Criar um método "createToken" para fazer a certificação "Claims", vincular as "roles" e estabelecer a duração do token
  - Criar um método para autenticar o token (Authentication) / Gerar um método getUserName
  - Criar um método resolveToken para retornar um "bearerToken" com "Header" "Authorization"
  - Criar um método para validar o token (validateToken)
  - Acessar a página https://jwt.io
 - [ ] [código do JwtTokenProvider](#código-jwttokenprovider)

[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=930kvaBQO0s)
-
🥈:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=kH7dVw7ajec)
-
🥉:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=yx7FlCdwapE)


### Código atualizado
- classe user (na camada models)
```
package net.ufjnet.gestaoobra.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "USERS")
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "user_name", unique = true)
	private String userName;
	
	private String fullName;
		
	private String password;
	
	private Boolean accountNonExpired;
	
	private Boolean accountNonLocked;
	
	private Boolean credentialsNonExpired;
	
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission", joinColumns = @JoinColumn (name = "id_user"),
	                   inverseJoinColumns = @JoinColumn (name = "id_permission"))
	private Set<Permission> permissions;
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for(Permission permission : this.permissions) {
			roles.add(permission.getDescricao());
		}
		return roles;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}
	
	public String getFullName() {
		return this.fullName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	
	public Set<Permission> getPermissions() {
		return permissions;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}

```
- classe userDAO (na camada Repositories)
```
package net.ufjnet.gestaoobra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ufjnet.gestaoobra.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	

	@Query("SELECT obj FROM User obj WHERE obj.userName =:userName")
	User findByUsername(@Param("userName") String userName);
	
}
```
- classe GestaoUser (na camada Services)

```
package net.ufjnet.gestaoobra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.ufjnet.gestaoobra.models.User;
import net.ufjnet.gestaoobra.repositories.UserDAO;


@Service
public class GestaoUser implements UserDetailsService {
	
	@Autowired
	private UserDAO dao;
	
	public GestaoUser(UserDAO dao) {
		this.dao = dao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Usuário "+ username + "não encontrado!");
		}
		
	}
	
	
	
	
}
	
```
- classe UserDTO (na camada Security)

```
package net.ufjnet.gestaoobra.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String username;
	private String password;

}
```
- classe InvalidAuthenticationException (na camada services.exceptions)

```

package net.ufjnet.gestaoobra.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = 1L;
	
	
	public InvalidAuthenticationException(String msg) {
		super(msg);
	}

}
```

- método InvalidAuthenticationException (na classe ExcpetionHandler - pacote exceptionhandler)
```

@org.springframework.web.bind.annotation.ExceptionHandler(InvalidAuthenticationException.class)
	public ResponseEntity<StandardError> InvalidAuthenticationException (InvalidAuthenticationException ex) {
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(),
			LocalDateTime.now(),ex.getMessage(),null);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		//return new ResponseEntity<>(StandardError, HttpStatus.BAD_REQUEST);
	}
```

[voltar](#passo-1-criar-as-relações-de-usuários-e-permissões)

### Código JwtTokenProvider

```



```
[voltar](#passo-2-criar-jwt---jwttokenprovider)


### Passo 3: Atualizar o github com os códigos atuais (JwtTokenProvider)

