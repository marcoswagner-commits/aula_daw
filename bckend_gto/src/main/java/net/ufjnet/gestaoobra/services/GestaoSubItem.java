package net.ufjnet.gestaoobra.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.dtos.SubItemDTO;
import net.ufjnet.gestaoobra.models.Item;
import net.ufjnet.gestaoobra.models.SubItem;
import net.ufjnet.gestaoobra.repositories.ItemDAO;
import net.ufjnet.gestaoobra.repositories.SubItemDAO;
import net.ufjnet.gestaoobra.services.exceptions.BusinessException;

@AllArgsConstructor
@Service
public class GestaoSubItem {
	
	private SubItemDAO dao;
	
	private ItemDAO itemDAO;
	
	
	@Transactional(readOnly = true)
	public Page<SubItemDTO> findAll(Pageable pageable) {
		Page<SubItem> result = dao.findAll(pageable);
		return result.map(obj -> new SubItemDTO(obj));
				
		
	}
	
	

	@Transactional(readOnly = true)
	public SubItemDTO findById(Integer id) {
		SubItem result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new SubItemDTO(result);
			
	}
	
	

	@Transactional
	public SubItemDTO update(SubItemDTO obj) {
		SubItem entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		
		entity.setDescricao(obj.getDescricao());
		entity.setComplemento(obj.getComplemento());
		
		return new SubItemDTO(dao.save(entity));
		
		
	}	
	
	
	@Transactional
	public SubItemDTO save(SubItemDTO obj) {
				
        Optional<Item> item = itemDAO.findById(obj.getItem().getCodigo());
        		
        SubItem entity = new SubItem(obj.getCodigo(), obj.getDescricao(), 
        		obj.getComplemento(),
				new Item(item.get().getCodigo(),
						item.get().getDescricao(),
						item.get().getComplemento()));

        entity.setItem(item.orElse(null));
		
		
		
		
		return new SubItemDTO(dao.save(entity));
	}
	

	
	@Transactional
	public void deleteById(Integer id) {
			dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
	
}
	
