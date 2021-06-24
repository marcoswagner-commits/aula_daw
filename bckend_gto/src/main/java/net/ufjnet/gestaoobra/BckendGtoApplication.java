package net.ufjnet.gestaoobra;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.ufjnet.gestaoobra.models.Item;
import net.ufjnet.gestaoobra.models.Lancamento;
import net.ufjnet.gestaoobra.models.Obra;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.models.SubItem;
import net.ufjnet.gestaoobra.repositories.ItemDAO;
import net.ufjnet.gestaoobra.repositories.LancamentoDAO;
import net.ufjnet.gestaoobra.repositories.ObraDAO;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;
import net.ufjnet.gestaoobra.repositories.SubItemDAO;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BckendGtoApplication implements CommandLineRunner {

	@Autowired
	private ProprietarioDAO propDAO;
	
	@Autowired
	private ObraDAO obraDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private SubItemDAO subitemDAO;
	
	@Autowired
	private LancamentoDAO lancamentoDAO;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(BckendGtoApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		Proprietario p1 = new Proprietario(1,"Joao","123","joao@gto");
		Proprietario p2 = new Proprietario(2,"Jose","456","jose@gto");
		Proprietario p3 = new Proprietario(3,"Maria","789","maria@gto");
		
		Obra o1 = new Obra(1,"Sobrado com 4 suites", "Rua Dona Olimpia, 1414, Vila Fátima","",p1);
		Obra o2 = new Obra(2,"Casa geminada", "Rua 15, 1515, Setor Hermosa","",p2);
		Obra o3 = new Obra(3,"Casa com 3 quartos", "Rua 16, 1616, Setor Brisas","",p3);
		Obra o4 = new Obra(4,"Casa de Alto Padrão", "Rua 17, 1717, 1414, Setor Cohacol","",p3);
		
		Item i1 = new Item(1,"Material Básico","");
		Item i2 = new Item(2,"Material de Acabamento","");
		Item i3 = new Item(3,"Material Pintura","");
		Item i4 = new Item(4,"Mão-de-Obra","");
		Item i5 = new Item(5,"Locações","");
		
		SubItem si1 = new SubItem(1,"Fio Elétrico","",i1);
		SubItem si2 = new SubItem(2,"Cimento","",i1);
		SubItem si3 = new SubItem(3,"Tijolo","",i1);
		
		SubItem si4 = new SubItem(4,"Argamassa","",i2);
		SubItem si5 = new SubItem(5,"Porcelanato","",i2);
		
		SubItem si6 = new SubItem(6,"Massa Corrida","",i3);
		SubItem si7 = new SubItem(7,"Tinta","",i3);
		
		SubItem si8 = new SubItem(8,"Pedreiro","",i4);
		
		
		Lancamento l1 = new Lancamento(1,o1,i1,si1,150.50,"Parte Elétrica","","");
		Lancamento l2 = new Lancamento(2,o1,i1,si2,250.00,"Contra-Piso","","");
		Lancamento l3 = new Lancamento(3,o1,i2,si4,250.00,"Piso","","");
		Lancamento l4 = new Lancamento(4,o2,i2,si5,250.00,"Piso","","");
		Lancamento l5 = new Lancamento(5,o2,i3,si6,250.00,"Contra-Piso","","");
		Lancamento l6 = new Lancamento(6,o3,i4,si8,250.00,"Contra-Piso","","");
		Lancamento l7 = new Lancamento(7,o3,i4,si8,250.00,"Contra-Piso","","");
		Lancamento l8 = new Lancamento(8,o4,i3,si7,250.00,"Contra-Piso","","");
		Lancamento l9 = new Lancamento(9,o4,i4,si2,250.00,"Contra-Piso","","");
		Lancamento l10 = new Lancamento(10,o4,i4,si8,250.00,"Contra-Piso","","");
		
		
		propDAO.saveAll(Arrays.asList(p1,p2,p3));
		obraDAO.saveAll(Arrays.asList(o1,o2,o3,o4));
		itemDAO.saveAll(Arrays.asList(i1,i2,i3,i4,i5));
		subitemDAO.saveAll(Arrays.asList(si1,si2,si3,si4,si5,si6,si7,si8));
		lancamentoDAO.saveAll(Arrays.asList(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10));
		
		
	}

}
