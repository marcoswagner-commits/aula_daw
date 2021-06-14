package net.ufjnet.gestaoobra;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.ufjnet.gestaoobra.models.Obra;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.repositories.ObraDAO;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BckendGtoApplication implements CommandLineRunner {

	@Autowired
	private ProprietarioDAO propDAO;
	
	@Autowired
	private ObraDAO obraDAO;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(BckendGtoApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		Proprietario p1 = new Proprietario(1,"Joao","123","joao@gto");
		Proprietario p2 = new Proprietario(2,"Jose","456","jose@gto");
		Proprietario p3 = new Proprietario(3,"Maria","789","maria@gto");
		
		Obra o1 = new Obra(1,"Sobrado com 4 suites", "Rua Dona Olimpia, 1414, Vila Fátima","");
		Obra o2 = new Obra(1,"Casa geminada", "Rua 15, 1515, Setor Hermosa","");
		Obra o3 = new Obra(1,"Casa com 3 quartos", "Rua 16, 1616, Setor Brisas","");
		Obra o4 = new Obra(1,"Casa de Alto Padrão", "Rua 17, 1717, 1414, Setor Cohacol","");
		
		
		propDAO.saveAll(Arrays.asList(p1,p2,p3));
		
		obraDAO.saveAll(Arrays.asList(o1,o2,o3,o4));
		
		
	}

}
