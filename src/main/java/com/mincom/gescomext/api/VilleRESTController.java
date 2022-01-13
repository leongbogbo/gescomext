package com.mincom.gescomext.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.service.VilleService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VilleRESTController {
	
	@Autowired
	VilleService villeService;
	
	@RequestMapping(value="/listeVille", method = RequestMethod.GET)
	public List<Ville> getAllVilles() {
		return villeService.getAllVille();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public List<Commune> getCommuneByIdVille(@PathVariable("id") Long id) {
		return villeService.getVilleById(id).getCommunes();
	}

}
