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
import net.ufjnet.gestaoobra.models.SubItem;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@JsonPropertyOrder({"codigo_subitem", "item",  "descricao_subitem", "complemento_subitem"})
public class SubItemDTO extends RepresentationModel<SubItemDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_subitem")
	private Integer codigo;
		
	@NotBlank
	@Size(max=255)
	@JsonProperty("descricao_subitem")
	private String descricao;
	
			
	@JsonProperty("complemento_subitem")
	private String complemento;
		
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationsGroups.ProprietarioId.class)
	private ItemDTO item;
	
	public SubItemDTO (SubItem obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		complemento = obj.getComplemento();
		item = new ItemDTO(obj.getItem());
		
		
		
	}
	
}
