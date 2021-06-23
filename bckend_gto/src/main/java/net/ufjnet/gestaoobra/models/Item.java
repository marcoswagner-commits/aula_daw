package net.ufjnet.gestaoobra.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ITENS")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_item")
	private Integer codigo ;
	
	
	@Column(name = "descricao_item", nullable = false)
	private String descricao;
	
	
	@Column(name = "complemento_item")
	private String complemento;
	

	@OneToMany(mappedBy = "item")
	private Set<SubItem> subitens;
	
	@OneToMany(mappedBy = "item")
	private Set<Lancamento> lancamentos;
	
	
	public Item(Integer codigo, String descricao, String complemento) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.complemento = complemento;
		
		
	}
	
}