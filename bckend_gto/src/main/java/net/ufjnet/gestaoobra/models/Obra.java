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
@Table(name = "OBRAS")
public class Obra implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_obra")
	private Integer codigo ;
	
	
	@Column(name = "descricao_obra", nullable = false)
	private String descricao;
	
	
	@Column(name = "localizacao_obra", nullable = false)
	private String localizacao;
	
	
	@Column(name = "complemento_obra")
	private String complemento;
	
	
	@ManyToOne()
	private Proprietario proprietario;
	
}