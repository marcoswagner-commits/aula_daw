# Aula 15 - Desenvolvimento de Aplicações WEB

> Aula 05/10/2022
> 
>  * Estudo de caso: Gestão de Obras *
> 
>  *Vínculos entre Classes/Entidades - Criação do Contexto Lançamento*

## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Vínculos entre Classes/Entidades - Criação do Contexto Lançamento

![Relação entre Lancamento - Obras - Item - SubItem](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/f99cca49fd5da210b29f6561a20e5a33c65a049d/documentos/modelo-conceitual-completo.png)

### Passo 1: Criar a classe de lançamento
- [x] Método de Produtividade BackEnd
- [x] Criar a classe Lançamento em "Models"
- [x] Criar a interface "repositories" LancamentoDAO
- [x] Criar a classe "DTOs" LancamentoDTO
- [x] Criar a classe "services" GestaoLancamento
- [x] Criar a classe "Controllers" LancamentoController - [códigos de Lancamento](#código-final)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula15.png)](https://www.youtube.com/watch?v=Tzy9ryJpFls)
-
🥈:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula15.png)](https://www.youtube.com/watch?v=ljvxBBt5qVc)
-
🥉:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula15.png)](https://www.youtube.com/watch?v=JIh3ahARElA)



### Código Final

```
//========================= Lancamento - Model
package net.ufjnet.gestaoobra.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "LANCAMENTOS")
public class Lancamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_lanc")
	private Integer codigo ;
	
	@ManyToOne()
	private Obra obra;
	
	@ManyToOne()
	private Item item;
	
	@ManyToOne()
	private SubItem subitem;
	
	@Column(name = "valor_lanc")
	private Double valor;
	
	@Column(name = "descricao_lanc", nullable = false)
	private String descricao;
	
		
	@Column(name = "documento_lanc")
	private String documento;
	
	@Column(name = "observacoes_lanc")
	private String observacoes;
	
	
	
}


//========================= LancamentoDTO - DTOs

package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ufjnet.gestaoobra.models.Lancamento;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@JsonPropertyOrder({"codigo_lanc", "obra", "item", "subitem", "valor_lanc", "descricao_lanc", "documento_lanc", "observacoes_lanc"})
public class LancamentoDTO extends RepresentationModel<LancamentoDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_lanc")
	private Integer codigo;
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationsGroups.ObraId.class)
	@JsonProperty("obra")
	private ObraDTO obra;
	
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationsGroups.ItemId.class)
	@JsonProperty("item")
	private ItemDTO item;
	
	@JsonProperty("subitem")
	private SubItemDTO subitem;
	
	@JsonProperty("valor_lanc")
	private Double valor;
	
	@NotBlank
	@Size(max=255)
	@JsonProperty("descricao_lanc")
	private String descricao;
	
	@JsonProperty("docuemento_lanc")
	private String documento;		
	
	@JsonProperty("observacoes_lanc")
	private String observacoes;
		
	

	
	public LancamentoDTO (Lancamento obj) {
		codigo = obj.getCodigo();
		obra = new ObraDTO(obj.getObra());
		item = new ItemDTO(obj.getItem());
		subitem = new SubItemDTO(obj.getSubitem());
		valor = obj.getValor();
		descricao = obj.getDescricao();
		documento = obj.getDocumento();
		observacoes = obj.getObservacoes();
		
		
		
		
	}
	
}


//========================= LancamentoDAO - Repositories

package net.ufjnet.gestaoobra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.gestaoobra.models.Lancamento;

public interface LancamentoDAO extends JpaRepository<Lancamento, Integer> {
	


}


//========================= GestaoLancamento - Services

package net.ufjnet.gestaobrapll.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ufjnet.gestaobrapll.dtos.LancamentoDTO;
import net.ufjnet.gestaobrapll.models.Item;
import net.ufjnet.gestaobrapll.models.Lancamento;
import net.ufjnet.gestaobrapll.models.Obra;
import net.ufjnet.gestaobrapll.models.SubItem;
import net.ufjnet.gestaobrapll.repositories.ItemDAO;
import net.ufjnet.gestaobrapll.repositories.LancamentoDAO;
import net.ufjnet.gestaobrapll.repositories.ObraDAO;
import net.ufjnet.gestaobrapll.repositories.SubItemDAO;
import net.ufjnet.gestaobrapll.services.exception.BusinessException;

@Service
public class GestaoLancamento {
	
	@Autowired
	private LancamentoDAO dao;
	
	@Autowired
	private ObraDAO obraDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private SubItemDAO subItemDAO;
	
	@Transactional(readOnly = true)
	public Page<LancamentoDTO> findAll(Pageable pageable) {
		Page<Lancamento> result = dao.findAll(pageable);
		return result.map(obj -> new LancamentoDTO(obj));
		
	}
	
	
	@Transactional(readOnly = true)
	public LancamentoDTO findById(Integer id) {
		Lancamento result = dao.findById(id)
				.orElseThrow(() -> new BusinessException("Lançamento não encontrado!"));
						
			return new LancamentoDTO(result);
	}
	
	@Transactional(readOnly = true)
	public LancamentoDTO findByDescricao(String descricao) {
		Lancamento result = dao.findByDescricao(descricao)
					.orElseThrow(() -> new BusinessException("Lançamento não encontrado!"));
		
		return new LancamentoDTO(result);
				
	}
	
	
	
	@Transactional
	public LancamentoDTO save(LancamentoDTO obj) {
		
		Optional<Obra> obra = obraDAO.findById(obj.getObra().getCodigo());
		Optional<Item> item = itemDAO.findById(obj.getItem().getCodigo());
		Optional<SubItem> subitem = subItemDAO.findById(obj.getSubitem().getCodigo());
		
		
		Lancamento entity = new Lancamento(obj.getCodigo(),
				new Obra (obra.get().getCodigo(), obra.get().getDescricao(), 
						  obra.get().getLocalizacao(), obra.get().getComplemento(), obra.get().getProprietario()),
				new Item (item.get().getCodigo(), item.get().getDescricao(), item.get().getComplemento()),
				new SubItem(subitem.get().getCodigo(), subitem.get().getDescricao(), subitem.get().getComplemento()),
				obj.getDescricao(), obj.getValor(),	obj.getDocumento(),  obj.getObservacoes());
					
		entity.setObra(obra.orElse(null));
		entity.setItem(item.orElse(null));
		entity.setSubItem(subitem.orElse(null));
		
		return new LancamentoDTO(dao.save(entity));
		
	}
	
	
	@Transactional
	public LancamentoDTO update(LancamentoDTO obj) {
		Lancamento entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Lançamento não encontrado!"));
		
		Obra entityObra = obraDAO.findById(obj.getObra().getCodigo())
				.orElseThrow(() -> new BusinessException("Obra não encontrada!"));
		
		Item entityItem = itemDAO.findById(obj.getItem().getCodigo())
				.orElseThrow(() -> new BusinessException("Item não encontrado!"));
		
		SubItem entitySubItem = subItemDAO.findById(obj.getSubitem().getCodigo())
				.orElseThrow(() -> new BusinessException("SubItem não encontrado!"));
	
		entity.setObra(entityObra);
		entity.setItem(entityItem);
		entity.setSubItem(entitySubItem);
		
		entity.setDescricao(obj.getDescricao());
		entity.setValor(obj.getValor());
		entity.setDocumento(obj.getDocumento());
		entity.setObservacoes(obj.getObservacoes());
		
		
		return new LancamentoDTO(dao.save(entity));
		
	}

	
	
	@Transactional
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
}

	


//========================= LancamentoController

package net.ufjnet.gestaoobra.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.ufjnet.gestaoobra.dtos.LancamentoDTO;
import net.ufjnet.gestaoobra.services.GestaoLancamento;

@RestController
@RequestMapping("/v1/gto/lancamentos")
@Tag(name = "Endpoint de lancamento")
public class LancamentoController {
	
	@Autowired
	private GestaoLancamento service;
	
	@GetMapping
	@Operation(summary = "Busca todos os lançamentos")
	public ResponseEntity<CollectionModel<LancamentoDTO>> buscarTodos(
				@RequestParam(value="page", defaultValue = "0") int page,
				@RequestParam(value="limit", defaultValue = "12") int limit,
				@RequestParam(value="direction", defaultValue = "asc") String direction) {


			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "descricao"));
			
			Page<LancamentoDTO> pages = service.findAll(pageable);
			pages
				.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(LancamentoController.class).buscarUm(p.getCodigo())).withSelfRel()
					)
				);
		  	
			return ResponseEntity.ok(CollectionModel.of(pages));
		}

  
	
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca um lançamento por id")
	public ResponseEntity<LancamentoDTO> buscarUm(@PathVariable Integer id) {
			LancamentoDTO objDTO = service.findById(id);
			objDTO.add(linkTo(methodOn(LancamentoController.class).buscarUm(id)).withSelfRel());
			return ResponseEntity.ok(objDTO);
		}	

		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um novo lançamento")
	public ResponseEntity<LancamentoDTO> incluir(@Valid @RequestBody LancamentoDTO objBody) {
		LancamentoDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(LancamentoController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping
	@Operation(summary = "Atualiza um lançamento")
	public ResponseEntity<LancamentoDTO> atualizar(@RequestBody LancamentoDTO objBody) {
		
		LancamentoDTO objDTO = service.update(objBody);
		objDTO.add(linkTo(methodOn(LancamentoController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
	


	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um lançamento por id")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}

		service.deleteById(id);

		return ResponseEntity.noContent().build();

	}


	
	


}


```
[voltar](#passo-1-criar-a-classe-de-lançamento)

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
	
	public static void main(String[] args) {
		SpringApplication.run(BckendGtoApplication.class, args);
		


	}

	
	@Override
	public void run(String... args) throws Exception {
		executaCommandLine();
	}
	
	private void executaCommandLine() {
		
		
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
		
			
		propDAO.saveAll(Arrays.asList(p1,p2,p3));
		obraDAO.saveAll(Arrays.asList(o1,o2,o3,o4));
		itemDAO.saveAll(Arrays.asList(i1,i2,i3,i4,i5));
		subitemDAO.saveAll(Arrays.asList(si1,si2,si3,si4,si5,si6,si7,si8));
		lancamentoDAO.saveAll(Arrays.asList(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10));
		
				
	}

}


```

[voltar](#passo-1-criar-a-classe-de-lançamento)


### Passo 2: Atualizar o github com os códigos atuais (camada com lançamentos)

