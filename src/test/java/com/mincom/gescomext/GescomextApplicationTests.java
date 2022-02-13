package com.mincom.gescomext;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.TraitementOpCodeImportation;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.EntrepriseRepository;
import com.mincom.gescomext.repository.TraitementOpCodeImportationRepository;
import com.mincom.gescomext.repository.VilleRepository;
import com.mincom.gescomext.service.VilleService;


@SpringBootTest
class GescomextApplicationTests {
	@Autowired
	TraitementOpCodeImportationRepository respo;
	
	/*@Test
	public void testFindAllTraitementOpCodeImportationByTypeCodeOpss ()
	{		
		List<TraitementOpCodeImportation> traitementOpCodeImportation= respo.findAllTraitementOpCodeImportationByTypeCodeOpss("CodeImportExport","site 2",PageRequest.of(0,1));
		traitementOpCodeImportation.forEach(opcode->{
			System.out.println(opcode.getStatutTrait());	
		});
	}*/
	
	/*@Test
	public void testFindByNomProduitContains()
	{
		Page<Ville> prods = villeService.getAllVilleByPage(0,5);
		System.out.println(prods.getSize());
		System.out.println(prods.getTotalElements());
		System.out.println(prods.getTotalPages());
		prods.getContent().forEach(p -> {
			System.out.println(p.getNomVille());
		});
		/*ou bien
		for (Produit p : prods)
		{
		System.out.println(p);
		}
	}*/
	
}
