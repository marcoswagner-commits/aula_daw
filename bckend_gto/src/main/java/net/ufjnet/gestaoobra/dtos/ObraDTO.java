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
import net.ufjnet.gestaoobra.models.Obra;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@JsonPropertyOrder({"codigo_obra", "proprietario",  "descricao_obra", "localizacao_obra", "complemento_obra"})
public class ObraDTO extends RepresentationModel<ObraDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@NotNull(groups = ValidationsGroups.ObraId.class)
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_obra")
	private Integer codigo;
		
	@NotBlank
	@Size(max=255)
	@JsonProperty("descricao_obra")
	private String descricao;
	
	@NotBlank
	@Size(max=255)
	@JsonProperty("localizacao_obra")
	private String localizacao;
		
	@JsonProperty("complemento_obra")
	private String complemento;
	
	
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationsGroups.ProprietarioId.class)
	private ProprietarioDTO proprietario;
	
	public ObraDTO (Obra obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		localizacao = obj.getLocalizacao();
		complemento = obj.getComplemento();
		proprietario = new ProprietarioDTO(obj.getProprietario());
		
		
		
	}
	
}
