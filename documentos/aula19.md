# Aula 19 - Desenvolvimento de Aplicações WEB

> 
> 
>   Estudo de caso: Gestão de Obras 


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Upload e Donwload de Arquivos


### Passo 1: Definição de Configurações
- [x] Definir as configurações de Multipart (Interface MultpartFile) no arquivo application.properties
- [x] Criar uma classe de configuração (no pacote config) FileStorageConfig
  - Criar um atributo privado tipo string "uploadDir"
  - Criar Getters/Setters para o atributo (lombok)
  - Inserir uma anotação de classe (@ConfigurationProperties) - springframework.boot.context.properties
  - Indicar na anotação (prefix= "file") qual propriedade das configurações (application.properties) contém o endereço
  - Colocar uma anotação na classe principal da aplicação (BckendGtoApplication) @EnableConfigurationProperties
    - Colocar como parâmetro desta anotação o nome da classe que foi criada para tal finalidade ({FileStorageConfig.class}) 

### Passo 2: Criação de classes de exceções
- [x] Criar uma classe de exceção chamada FileStorageException no pacote services.exceptions (exceção genérica)
  - Atribuir como status de erro o INTERNAL_SERVER_ERROR
  - Criar um outro construtor padrão do atualmente já usado com o parâmetro Throwable (cause)
- [x] Criar uma classe de exceção chamada MyFileNotFoundException no pacote services.exceptions (exceção específica)
  - Atribuir como status de erro o NOT_FOUND 
  - Criar um outro construtor padrão do atualmente já usado com o parâmetro Throwable (cause)
    - Obs.: criar a segunda classe a partir da primeira 

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
  - Criar constante estática logger (Logger logger = LoggerFactory.getLogger(FileController.class)
  - Injeção de FileStorageService
  - Criar um método uploadFile (@PostMapping("/uploadFile")
    - Receber como parâmetro (@RequestParam) o arquivo (MultipartFile) 
    - Criar estrutura do download do arquivo

- [ ] [códigos finais](#código-final)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=qYucvg6Co8c)
-
🥈:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=inLcdThy9YM)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=p35mSdb9BaQ)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=Gim5AAmYOEE)



```
spring.servlet.multipart.enabled=true // habilitar o spring servelet multipart
spring.servlet.multipart.file-size.threshold=2KB  // tamanho da memória usada para armazenar o arquivo - a partir o armazenamento é em disco
spring.servlet.multipart.max-file-size=200MB // tamanho máximo do arquivo
spring.servlet.multipart.max-request-size=215MB // tamanho máximo da requisição 

file.upload-dir=/arquivos/gestaoobras/uploaddir // diretório de armazenamento do arquivo em upload

```

### Código final
-
-  classe PermissionDAO (na camada repositories)
```

```
- classe BckendGtoApplication (classe principal/inicial)
```


```

[voltar](#passo-4-implementar-o-envio-de-e-mails)



### Passo 5: Atualizar o github com os códigos atuais (Upload de arquivos)

