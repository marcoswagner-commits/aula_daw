package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class ProprietarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	//@EqualsAndHashCode.Include
	//private Integer codigo ;
		
	private String nome;
		
	private String cpf;
		
	private String email;
	
	public ProprietarioDTO (Proprietario obj) {
		//this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		
	}
	
}
