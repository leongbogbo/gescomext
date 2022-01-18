package com.mincom.gescomext.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.EntrepriseRepository;
import com.mincom.gescomext.service.EntrepriseService;
import com.mincom.gescomext.service.VilleService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ValidationRESTController {
	
	@Autowired
	VilleService villeService;
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	@RequestMapping(value="/form/listeVille", method = RequestMethod.GET)
	public List<Ville> getAllVilles() {
		return villeService.getAllVille();
	}

	@RequestMapping(value="/raisonSociale/{raison}", method = RequestMethod.GET)
	public Entreprise getElement(@PathVariable("raison") String raison) {
		return entrepriseRepository.findByNomEntr(raison);
	}
	
	@RequestMapping(value="/numidu/{numidu}", method = RequestMethod.GET)
	public Entreprise getElementnumidu(@PathVariable("numidu") String numidu) {
		return entrepriseRepository.findByNumIduEntr(numidu);
	}
	
	@RequestMapping(value="/contribuable/{contribuable}", method = RequestMethod.GET)
	public Entreprise getElementcontribuable(@PathVariable("contribuable") String contribuable) {
		return entrepriseRepository.findByContribuableEntr(contribuable);
	}
	
	@RequestMapping(value="/regicommerce/{regicommerce}", method = RequestMethod.GET)
	public Entreprise getElementregicommerce(@PathVariable("regicommerce") String regicommerce) {
		return entrepriseRepository.findByRegcommerceEntr(regicommerce);
	}

}
