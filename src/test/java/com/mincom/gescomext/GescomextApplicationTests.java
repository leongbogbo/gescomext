package com.mincom.gescomext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.EntrepriseRepository;
import com.mincom.gescomext.repository.VilleRepository;

@SpringBootTest
class GescomextApplicationTests {
	@Autowired
	VilleRepository respo;
	
	/*@Test
	public void testSaveVille (Ville ville)
	{
		ville.setNomVille("");
		respo.save(ville);
	}*/
	
}
