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
