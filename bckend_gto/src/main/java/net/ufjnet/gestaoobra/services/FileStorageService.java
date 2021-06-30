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
