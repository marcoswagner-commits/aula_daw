package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ufjnet.gestaoobra.models.Proprietario;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@JsonPropertyOrder({"codigo_prop", "nome_prop", "email_prop", "cpf_prop"})
public class ProprietarioDTO extends RepresentationModel<ProprietarioDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@NotNull(groups = ValidationsGroups.ProprietarioId.class)
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_prop")
	private Integer codigo;
	
	@NotBlank
	@Size(max=60)
	@JsonProperty("nome_prop")
	private String nome;
	
	@NotBlank
	@Size(max=14)
	@JsonProperty("cpf_prop")
	private String cpf;
		
	@NotBlank
	@Email(groups = Default.class)
	@JsonProperty("email_prop")
	private String email;
	
	
	public ProprietarioDTO (Proprietario obj) {
		this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		
	}
	
}
