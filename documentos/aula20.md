# Aula 20 - Desenvolvimento de Aplicações WEB

> Aula 19/08/2021
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
	postman.setEnvironmentVariable('beare_token',jsonData.token);

```

 
 ### Passo 4: Demonstrar o uso do e-mail
- [x] Verificar o envio do e-mail no cadastro de "Proprietários"
- [ ] Adicionar o envio do e-mail na atualização de "Proprietários"



- [ ] [códigos finais](#código-final)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/db3670d54bce719e7aee041c942fb02283f44c20/documentos/Capa_aula19.png)](https://www.youtube.com/watch?v=i_riVI00bog)
-
🥈:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/db3670d54bce719e7aee041c942fb02283f44c20/documentos/Capa_aula19.png)](https://www.youtube.com/watch?v=JtezFJapvx8)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/db3670d54bce719e7aee041c942fb02283f44c20/documentos/Capa_aula19.png)](https://www.youtube.com/watch?v=i5awBWZLCfU)




### Códigos
- Atualização da classe principal
```
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableConfigurationProperties({FileStorageConfig.class})
public class BckendGtoApplication implements CommandLineRunner {
...

```

-  classe FileStorageConfig (na camada repositories)
```
package net.ufjnet.gestaoobra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix= "file")
public class FileStorageConfig {
	
	private String uploadDir;
	

}

```
- Classes de Exceções
```
package net.ufjnet.gestaoobra.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public FileStorageException(String msg) {
		super(msg);
	}

	public FileStorageException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

``` 



```
package net.ufjnet.gestaoobra.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MyFileNotFoundException(String msg) {
		super(msg);
	}

	public MyFileNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
```

-  classe UploadFileResponseDTO (na camada DTOs)
```
package net.ufjnet.gestaoobra.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
public class UploadFileResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;
	
		
}


```

-  classe FileStorageService (na camada Services)
```
package net.ufjnet.gestaoobra.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import net.ufjnet.gestaoobra.config.FileStorageConfig;
import net.ufjnet.gestaoobra.services.exceptions.FileStorageException;

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
	
}



```

-  classe FileController (na camada Controller)
```

package net.ufjnet.gestaoobra.controllers;

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

}


```


### Passo 5: Atualizar o github com os códigos atuais (Upload de arquivos)

