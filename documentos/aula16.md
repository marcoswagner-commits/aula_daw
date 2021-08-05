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


```
package net.ufjnet.gestaoobra;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.ufjnet.gestaoobra.config.FileStorageConfig;
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
@EnableConfigurationProperties({FileStorageConfig.class})
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
		
		executaCommandLine();
	}
	
	private void executaCommandLine() {
		
	
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(16);
		r1 = bCrypt.encode("admin123");
		r2 = bCrypt.encode("user123");
		
				
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
		Lancamento l3 = new Lancamento(3,o1,i2,si4,280.00,"Piso","","");
		Lancamento l4 = new Lancamento(4,o2,i2,si5,290.00,"Piso","","");
		Lancamento l5 = new Lancamento(5,o2,i3,si6,350.00,"Contra-Piso","","");
		Lancamento l6 = new Lancamento(6,o3,i4,si8,150.00,"Contra-Piso","","");
		Lancamento l7 = new Lancamento(7,o3,i4,si8,50.00,"Contra-Piso","","");
		Lancamento l8 = new Lancamento(8,o4,i3,si7,1250.00,"Contra-Piso","","");
		Lancamento l9 = new Lancamento(9,o4,i4,si2,2500.00,"Contra-Piso","","");
		Lancamento l10 = new Lancamento(10,o4,i4,si8,25.00,"Contra-Piso","","");
		
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
[voltar](#passo-2-criar-as-relações-de-usuários-e-permissões)


### Passo 3: Atualizar o github com os códigos atuais (busca agrupada-inicio autenticação)

