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