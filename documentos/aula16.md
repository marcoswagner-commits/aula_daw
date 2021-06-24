# Aula 16 - Desenvolvimento de Aplicações WEB

> Aula 05/08/2021
> 
>  * Estudo de caso: Gestão de Obras *


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Testes e Povoamento de Dados; Autenticação com JWT e Spring Security


### Passo 1: Testes, povoamento e buscas agrupadas
- [x] Povoar a tabela de itens
  - Inserir dados - criar collection no PostMan
- [x] Povoar a tabela de subitens
  - Inserir dados - criar collection no PostMan 
- [x] Povoar a tabela de lançamentos
  - Inserir dados - criar collection no PostMan 
- [x] Criar a classe TotalItemDTO (para armazenar o total de gastos com o item)
  - Criar uma busca agrupada no LancamentoDAO
  - Inserir método no GestaoLancamento
  - Inserir método no LancamentoController
- [x] Códigos atualizados (métodos inseridos) - [códigos atualizados](#código-atualizado)

![Relação entre Lancamento - Usuário e Permissões](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/bf5d7c17f9f1096d18524edf67596225abc4e149/documentos/User_Permissions.png)

### Passo 2: Criar as relações de usuários e permissões
- [x] Adicionar dependência para o jwt.io (Acessar https://jwt.io e https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt)
- [x] Criar a classe Permission em "Models"
  - Implementar GrantedAuthority (springframework.security.core.GrantedAuthority) 
- [x] Criar a classe User em "Models"
  - Implementar GrantedAuthority (springframework.security.core.GrantedAuthority)
- [x] Criar a relação de Muitos para Muitos (ManyToMany) para User/Permissions
- [x] Criar o método getRoles
- [x] Verificar a criação das tabelas no Banco de Dados
- [x] Popular as tabelas de usuário e permissão - [códigos de usuário e permissões](#código-final)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=VqcttlfbeIk)
-
🥈:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=omG0xcaolp4)
-
🥉:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=yx7FlCdwapE)
-
🥉:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=rKxBzE0Z83o)



### Código atualizado

```
//========================= TotalItemDTO

package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ufjnet.gestaoobra.models.Item;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
public class TotalItemDTO extends RepresentationModel<TotalItemDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String itemDescricao;
	
	private Double total;

	
	public TotalItemDTO (Item obj, Double total) {
		this.itemDescricao = obj.getDescricao();
		this.total = total;
				
	}
	
}


//========================= LancamentoDAO
package net.ufjnet.gestaoobra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ufjnet.gestaoobra.dtos.TotalItemDTO;
import net.ufjnet.gestaoobra.models.Lancamento;

public interface LancamentoDAO extends JpaRepository<Lancamento, Integer> {
	

	@Query("SELECT new net.ufjnet.gestaoobra.dtos.TotalItemDTO(obj.item, SUM(obj.valor)) FROM Lancamento AS obj GROUP BY obj.item")
	List<TotalItemDTO> totalItem();

}


//========================= GestaoLancamento

@Transactional(readOnly = true)
	public List<TotalItemDTO> totalItem() {
		return dao.totalItem();
				
		
	}
//========================= LancamentoController

@GetMapping("/total-por-item")
	@Operation(summary = "Busca um total de itens agrupados por valor")
	public ResponseEntity<List<TotalItemDTO>> totalItem() {
			List<TotalItemDTO> result = service.totalItem();
			return ResponseEntity.ok(result);
		}	
	
```
[voltar](#passo-1-testes-povoamento-e-buscas-agrupadas)

### Código Final

```
//========================= Permission - Model

package net.ufjnet.gestaoobra.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "PERMISSIONS")
public class Permission implements GrantedAuthority, Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer codigo;
	
	@Column(name = "description")
	private String descricao;

	@Override
	public String getAuthority() {
		return this.descricao;
		
	}

}


//========================= User - Model

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
public class Users implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "user_name", unique = true)
	private String userName;
	
	private String fullName;
		
	private String password;
	
	private Boolean AccountNonExpired;
	
	private Boolean AccountNonLocked;
	
	private Boolean CredentialsNonExpired;
	
	private Boolean Enabled;
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}



```
[voltar](#passo-2-criar-as-relações-de-usuários-e-permissões)


### Passo 3: Atualizar o github com os códigos atuais (camada com obras)

