package net.ufjnet.gestaoobra;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.ufjnet.gestaoobra.models.Proprietario;

@SpringBootApplication
public class BckendGtoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BckendGtoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Proprietario prop = new Proprietario();
		prop.setCpf("123");
		
	}

}
