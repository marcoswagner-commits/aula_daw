# Aula 20 - Desenvolvimento de Aplicações WEB

> Aula 18/10/2022
> 
>   Estudo de caso: Gestão de Obras 


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Upload de múltiplos arquivos, Donwload de Arquivos, Automatização do Token e Teste de Emails


### Passo 1: Upload de múltiplos arquivos
- [x] Atualizar o FileController inserindo o método uploadMultipleFiles (@PostMapping "uploadNfiles")
  - Criar como retorno e como parâmetro uma lista de arquivos
  - Mapear a lista de arquivos (stream().map()) chamando o próprio método já existente para upload de arquivo único
  - Criar um request no PostMan para esta requisição e testar o método
  
### Passo 2: Download de arquivos
- [x] Atualizar a classe FileStorageService para realizar o download (loadFileAsResource)
  - Criar estrutura (try-catch) para verificação de exceções usando a já criada classe MyFileNotFoundException
- [x] Atualizar o FileController inserindo o método downloadFile (@GetMapping "downloadfile{filename:.+}")
  - Criar constante estática logger (Logger logger = LoggerFactory.getLogger(FileController.class)
  - Usar o logger para mostrar informações na estrutura de tratamento de erros
  

### Passo 3: Automatização do registro do token no PostMan
- [x] Criar um environment no PostMan para Armazenar o token (atualizar o existente)
  - Criar em "tests" um script para automatização do armazenamento do token
  - Colocar o valor (value) com a variável 

```
if (responseCode.code >= 200 && responseCode.code <= 299) {
	var jsonData = JSON.parse(responseBody);
	postman.setEnvironmentVariable('bearer_token',jsonData.token);

```

 
 ### Passo 4: Demonstrar o uso do e-mail
- [x] Verificar o envio do e-mail no cadastro de "Proprietários"
- [x] Criar uma classe de tratamento de exceção para envio de e-mails
- [x] Adicionar o envio do e-mail na atualização de "Proprietários"


🅰️ - Configurações finais do application.properties
```
#configurações de e-mail
spring.mail.default-encoding= UTF-8
spring.mail.host= smtp.gmail.com
spring.mail.port= 465
spring.mail.username=  
spring.mail.password= 
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.socketFactory.port= 465
spring.mail.properties.mail.smtp.socketFactory.clas= javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.smtp.socketFactory.fallback= false
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.enable=true

```

- [ ] [códigos finais](#códigos)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/4f661048665df1d014740d1baf4eb93dfb66fbe0/documentos/Capa_aula20.png)](https://www.youtube.com/watch?v=i_riVI00bog)
-
🥈:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/4f661048665df1d014740d1baf4eb93dfb66fbe0/documentos/Capa_aula20.png)](https://www.youtube.com/watch?v=JtezFJapvx8)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/4f661048665df1d014740d1baf4eb93dfb66fbe0/documentos/Capa_aula20.png)](https://www.youtube.com/watch?v=MOn_yvN6D0o)




### Códigos
- FileController
```
package net.ufjnet.gestaoobra.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.dtos.UploadFileResponseDTO;
import net.ufjnet.gestaoobra.services.FileStorageService;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/gto/file")
@Tag(name = "Endpoint de Upload de Arquivos")
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadfile")
	public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/v1/gto/file/downloadFile/")
				.path(fileName)
				.toUriString();
		
		return new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize() );
		
				
	}
	
	@PostMapping("/uploadNfiles")
	public List<UploadFileResponseDTO> UploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
		
	}
	
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.info("Não foi possível determinar o tipo do arquivo!");
		}
		
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
		
	}

}


```

-  classe FileStorageService
```
package net.ufjnet.gestaoobra.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import net.ufjnet.gestaoobra.config.FileStorageConfig;
import net.ufjnet.gestaoobra.services.exceptions.FileStorageException;
import net.ufjnet.gestaoobra.services.exceptions.MyFileNotFoundException;

@Service
public class FileStorageService {
	
	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Não foi possível criar o diretório para o arquivo!", e);
		}
	}

	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Arquivo contém sequência inválida de caracteres!");
			}
			Path targetLocation = fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (Exception e) {
			throw new FileStorageException("Não foi possível salvar o arquivo "+fileName+". Tente novamente!", e);
		}
	}
	
	public Resource loadFileAsResource(String fileName) {
		
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("Arquivo não encontrado: "+fileName+"!");
			}
		} catch (Exception e) {
			throw new MyFileNotFoundException("Arquivo não encontrado: "+fileName+"!",e);
		}
		
	}
	
}


```
- Classe MailException
```
package net.ufjnet.gestaoobra.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MailException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	
	public MailException(String msg) {
		super(msg);
	}
	
	public MailException(String msg, Throwable cause) {
		super(msg, cause);
	}

}

``` 

- método na classe ExceptionHandler

```
@org.springframework.web.bind.annotation.ExceptionHandler(MailException.class)
	public ResponseEntity<StandardError> MailExceptionn (MailException ex) {
		StandardError erro = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
			LocalDateTime.now(),ex.getMessage()+ " - "+ex.getCause().getLocalizedMessage(),null);
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
		//return new ResponseEntity<>(StandardError, HttpStatus.BAD_REQUEST);
	}

```

- método "salvar" e "atualizar" em GestaoProprietarios

```
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

```



### Passo 5: Atualizar o github com os códigos atuais (Download de arquivos)

