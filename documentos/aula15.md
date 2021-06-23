# Aula 14 - Desenvolvimento de Aplicações WEB

> 
> 
>  * Estudo de caso: Gestão de Obras *


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
🥈:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula15.png)](https://www.youtube.com/watch?v=hLZkcOfJvNc)
-
🥉:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula15.png)](https://www.youtube.com/watch?v=sjmjxv4HqEE)



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



//========================= LancamentoDAO - Repositories



//========================= GestaoLancamento - Services



//========================= LancamentoController

```
[voltar](#passo-1-criar-a-classe-de-lançamento)


### Passo 2: Atualizar o github com os códigos atuais (camada com obras)

