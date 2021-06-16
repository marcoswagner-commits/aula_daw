package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonPropertyOrder({"codigo_item",  "descricao_item", "complemento_obra"})
public class ItemDTO extends RepresentationModel<ItemDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_item")
	private Integer codigo;
		
	@NotBlank
	@Size(max=255)
	@JsonProperty("descricao_item")
	private String descricao;
	
		
	@JsonProperty("complemento_item")
	private String complemento;

	
	public ItemDTO (Item obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		complemento = obj.getComplemento();
		
		
		
		
	}
	
}
