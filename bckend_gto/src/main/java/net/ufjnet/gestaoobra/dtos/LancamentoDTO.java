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
