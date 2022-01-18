package com.mincom.gescomext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.repository.EntrepriseRepository;
import com.mincom.gescomext.repository.VilleRepository;

@SpringBootTest
class GescomextApplicationTests {
	
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	@Test
	public void testFindByNomEntr()
	{
		Entreprise prods = entrepriseRepository.findByNomEntr("MAHONSSON");
		System.out.println(prods.getNomEntr());
	}
	@Test
	public void testFindByRegcommerceEntr()
	{
		Entreprise prods = entrepriseRepository.findByRegcommerceEntr("CI-ABJ-05-2021-A12-123456");
		System.out.println(prods.getNomEntr());
	}
	
	@Test
	public void testFindByNumIduEntr()
	{
		Entreprise prods = entrepriseRepository.findByNumIduEntr("IDU-124587");
		System.out.println(prods.getNomEntr());
	}
	
}
