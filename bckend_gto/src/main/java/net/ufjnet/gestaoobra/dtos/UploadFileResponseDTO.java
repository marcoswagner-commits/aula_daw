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
