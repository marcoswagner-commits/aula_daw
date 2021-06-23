package net.ufjnet.gestaoobra.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.dtos.LancamentoDTO;
import net.ufjnet.gestaoobra.models.Item;
import net.ufjnet.gestaoobra.models.Lancamento;
import net.ufjnet.gestaoobra.models.Obra;
import net.ufjnet.gestaoobra.models.SubItem;
import net.ufjnet.gestaoobra.repositories.ItemDAO;
import net.ufjnet.gestaoobra.repositories.LancamentoDAO;
import net.ufjnet.gestaoobra.repositories.ObraDAO;
import net.ufjnet.gestaoobra.repositories.SubItemDAO;
import net.ufjnet.gestaoobra.services.exceptions.BusinessException;

@AllArgsConstructor
@Service
public class GestaoLancamento {
	
	private ObraDAO obraDAO;
	private ItemDAO itemDAO;
	private SubItemDAO subitemDAO;
	private LancamentoDAO dao;
	
	@Transactional(readOnly = true)
	public Page<LancamentoDTO> findAll(Pageable pageable) {
		Page<Lancamento> result = dao.findAll(pageable);
		return result.map(obj -> new LancamentoDTO(obj));
				
		
	}
	
	

	@Transactional(readOnly = true)
	public LancamentoDTO findById(Integer id) {
		Lancamento result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new LancamentoDTO(result);
			
	}
	
	

	@Transactional
	public LancamentoDTO update(LancamentoDTO obj) {
		Lancamento entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		
		
		entity.setValor(obj.getValor());
		entity.setDescricao(obj.getDescricao());
		entity.setDocumento(obj.getDocumento());
		entity.setObservacoes(obj.getObservacoes());
		
		return new LancamentoDTO(dao.save(entity));
		
		
	}	
	
	
	@Transactional
	public LancamentoDTO save(LancamentoDTO obj) {
				
		Optional<Obra> obra = obraDAO.findById(obj.getObra().getCodigo());
		Optional<Item> item = itemDAO.findById(obj.getItem().getCodigo());
		Optional<SubItem> subitem = subitemDAO.findById(obj.getSubitem().getCodigo());
				
        		
        Lancamento entity = new Lancamento(obj.getCodigo(), 
        		new Obra(obra.get().getCodigo(),
        				obra.get().getDescricao(),
        				obra.get().getLocalizacao(),
        				obra.get().getComplemento(),
        				obra.get().getProprietario()),
        		new Item(item.get().getCodigo(),
        				item.get().getDescricao(),
        				item.get().getComplemento()),
        		new SubItem(subitem.get().getCodigo(),
        				subitem.get().getDescricao(),
        				subitem.get().getComplemento(),
        				subitem.get().getItem()),
        		obj.getValor(),
        		obj.getDescricao(), 
        		obj.getDocumento(),
        		obj.getObservacoes());
				
        		

        entity.setItem(item.orElse(null));
		
		
		
		
		return new LancamentoDTO(dao.save(entity));
	}
	

	
	@Transactional
	public void deleteById(Integer id) {
			dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
	
}
	
