package net.ufjnet.gestaoobra.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.dtos.ProprietarioDTO;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;
import net.ufjnet.gestaoobra.services.exceptions.BusinessException;
import net.ufjnet.gestaoobra.services.exceptions.MailException;

@AllArgsConstructor
@Service
public class GestaoProprietario {
	
	private ProprietarioDAO dao;
	
	private EnviarMailService email;

	
	
	@Transactional(readOnly = true)
	public Page<ProprietarioDTO> findAll(Pageable pageable) {
		Page<Proprietario> result = dao.findAll(pageable);
		return result.map(obj -> new ProprietarioDTO(obj));
	}
	
	

	@Transactional(readOnly = true)
	public ProprietarioDTO findById(Integer id) {
		Proprietario result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ProprietarioDTO(result);
			
	}
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByNome(String nome) {
		Proprietario result = dao.findByNome(nome).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ProprietarioDTO(result);
		
    	}
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByCPF(String cpf) {
		Proprietario result = dao.findByCpf(cpf).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ProprietarioDTO(result);
		
    }
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByEmail(String email) {
		Proprietario result = dao.findByEmail(email).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ProprietarioDTO(result);
		
	}

	@Transactional
	public ProprietarioDTO update(ProprietarioDTO obj) {
		Proprietario entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		entity.setNome(obj.getNome());
		entity.setCpf(obj.getCpf());
		entity.setEmail(obj.getEmail());
		
		try {
			String textoMail = "Informamos que seus dados foram ATUALIZADOS no sistema Gestão de Obras";
			email.enviar(entity.getEmail(), "Cadastro ATUALIZADO!", textoMail);
		} catch (Exception e) {
			throw new MailException("Erro no envio do e-mail!", e);
		}
		
		return new ProprietarioDTO(dao.save(entity));
		
		
	}	
	
	
	@Transactional
	public ProprietarioDTO save(ProprietarioDTO obj) {
		Proprietario entity = new Proprietario(obj.getCodigo(), obj.getNome(), obj.getCpf(), obj.getEmail());
		
		boolean cpfExists = dao.findByCpf(entity.getCpf())
				.stream()
				.anyMatch(objResult -> !objResult.equals(entity));
		
		if (cpfExists) {
			throw new BusinessException("CPF já existente!");
		}
		
		boolean emailExists = dao.findByEmail(entity.getEmail())
				.stream()
				.anyMatch(objResult -> !objResult.equals(entity));
		
		if (emailExists) {
			throw new BusinessException("E-mail já existente!");
		}
		
		try {
			String textoMail = "Informamos que seus dados foram CADASTRADOS no sistema Gestão de Obras";
			email.enviar(entity.getEmail(), "Cadastro Efetuado!", textoMail);
		} catch (Exception e) {
			throw new MailException("Erro no envio do e-mail!", e);
		}
		
		
		
		return new ProprietarioDTO(dao.save(entity));
	}
	

	
	@Transactional
	public void deleteById(Integer id) {
			dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
	
}
	
