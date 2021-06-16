package net.ufjnet.gestaoobra.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.dtos.ObraDTO;
import net.ufjnet.gestaoobra.models.Obra;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.repositories.ObraDAO;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;
import net.ufjnet.gestaoobra.services.exceptions.BusinessException;

@AllArgsConstructor
@Service
public class GestaoObra {
	
	private ObraDAO dao;
	
	private ProprietarioDAO propDAO;
	
	
	@Transactional(readOnly = true)
	public Page<ObraDTO> findAll(Pageable pageable) {
		Page<Obra> result = dao.findAll(pageable);
		return result.map(obj -> new ObraDTO(obj));
				
		
	}
	
	

	@Transactional(readOnly = true)
	public ObraDTO findById(Integer id) {
		Obra result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ObraDTO(result);
			
	}
	
	

	@Transactional
	public ObraDTO update(ObraDTO obj) {
		Obra entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		
		entity.setDescricao(obj.getDescricao());
		entity.setLocalizacao(obj.getLocalizacao());
		entity.setComplemento(obj.getComplemento());
		
		return new ObraDTO(dao.save(entity));
		
		
	}	
	
	
	@Transactional
	public ObraDTO save(ObraDTO obj) {
				
        Optional<Proprietario> prop = propDAO.findById(obj.getProprietario().getCodigo());
        		
        Obra entity = new Obra(obj.getCodigo(), obj.getDescricao(), 
				obj.getLocalizacao(), obj.getComplemento(),
				new Proprietario(prop.get().getCodigo(),
						prop.get().getNome(),
						prop.get().getCpf(),
						prop.get().getEmail()));

        entity.setProprietario(prop.orElse(null));
		
		
		
		
		return new ObraDTO(dao.save(entity));
	}
	

	
	@Transactional
	public void deleteById(Integer id) {
			dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
	
}
	
