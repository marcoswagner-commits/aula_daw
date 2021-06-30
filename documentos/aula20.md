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
  

### Passo 3: Criação de classes de Representação do Modelo e Serviços
- [x] Criar uma classe de representação (DTO) para upload de arquivos (UploadFileResponseDTO)
  - Criar os atributos fileName (string), fileDownloadUri (string), fileType (string), size (long)
  - Usar lombok para Getters/Setters, Construtores, Equals e HashCode
- [x] Criar uma classe de serviços  para upload de arquivos (FileStorageService)
  - Anotação @Service
  - Injetar a classe que foi no pacote config (FileStorageConfig)
  - criar um atributo que armazenará o caminho o arquivo (private final Path fileStorageLocation) - java.nio.file.Path
  - Associar o "fileStorageLocation" com o caminho que está no application.properties
    - Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
    - Criar um try-catch com a tentativa de criar o diretório (Files.createDirectories(this.fileStorageLocation) e lançar uma exceção
      - Mensagem: "não foi possível criar o diretório" - Exception: FileStorageException
  - Criar um método StoreFile com parâmetro MultiPartFile
    - Criar um atributo para armazenar o caminho
    - Usar estrutura try-catch para verificar e validar o arquivo
    - Gravar o arquivo:
      - (Path targetLocation = this.fileStorageLocation.resolve(fileName);
      - Files.copy(file.getInputStream(),targetLocation,StandardCopyOption.REPLACE_EXISTING)
 
 
 ### Passo 4: Criação de classes de Controladores
- [x] Criar uma classe FileController
  - Anotação @RequestMapping("v1/gto/file")
 
  - Injeção de FileStorageService
  - Criar um método uploadFile (@PostMapping("/uploadFile")
    - Receber como parâmetro (@RequestParam) o arquivo (MultipartFile) 
    - Criar estrutura do download do arquivo

- [ ] [códigos finais](#código-final)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/db3670d54bce719e7aee041c942fb02283f44c20/documentos/Capa_aula19.png)](https://www.youtube.com/watch?v=qYucvg6Co8c)
-
🥈:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/db3670d54bce719e7aee041c942fb02283f44c20/documentos/Capa_aula19.png)](https://www.youtube.com/watch?v=KIihqiDyssc)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/db3670d54bce719e7aee041c942fb02283f44c20/documentos/Capa_aula19.png)](https://www.youtube.com/watch?v=i5awBWZLCfU)

```
# habilitar o spring servelet multipart
spring.servlet.multipart.enabled=true
# tamanho da memória usada para armazenar o arquivo - a partir o armazenamento é em disco
spring.servlet.multipart.file-size.threshold=2KB
# tamanho máximo do arquivo
spring.servlet.multipart.max-file-size=200MB
# tamanho máximo da requisição 
spring.servlet.multipart.max-request-size=215MB

# diretório de armazenamento do arquivo em upload
file.upload-dir=/arquivos/gestaoobras/uploaddir 

```


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

