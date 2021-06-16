package net.ufjnet.gestaoobra.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.dtos.ItemDTO;
import net.ufjnet.gestaoobra.models.Item;
import net.ufjnet.gestaoobra.repositories.ItemDAO;
import net.ufjnet.gestaoobra.services.exceptions.BusinessException;

@AllArgsConstructor
@Service
public class GestaoItem {
	
	private ItemDAO dao;
	
	@Transactional(readOnly = true)
	public Page<ItemDTO> findAll(Pageable pageable) {
		Page<Item> result = dao.findAll(pageable);
		return result.map(obj -> new ItemDTO(obj));
				
		
	}
	
	

	@Transactional(readOnly = true)
	public ItemDTO findById(Integer id) {
		Item result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ItemDTO(result);
			
	}
	
	

	@Transactional
	public ItemDTO update(ItemDTO obj) {
		Item entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		
		entity.setDescricao(obj.getDescricao());
		entity.setComplemento(obj.getComplemento());
		
		return new ItemDTO(dao.save(entity));
		
		
	}	
	
	
	@Transactional
	public ItemDTO save(ItemDTO obj) {
		        		
        Item entity = new Item(obj.getCodigo(), obj.getDescricao(),  obj.getComplemento());
				
		return new ItemDTO(dao.save(entity));
	}
	

	
	@Transactional
	public void deleteById(Integer id) {
			dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
	
}
	
