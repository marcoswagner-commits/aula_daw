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
