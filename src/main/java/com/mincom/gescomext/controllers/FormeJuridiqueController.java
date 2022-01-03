package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.FormeJuridique;
import com.mincom.gescomext.repository.FormeJuridiqueRepository;
import com.mincom.gescomext.service.FormeJuridiqueService;

@Controller
public class FormeJuridiqueController {

	@Autowired
	FormeJuridiqueService formeJuridiqueService;
	@Autowired
	FormeJuridiqueRepository formeJuridiqueRepo;
	
	@RequestMapping("/listeFormeJuridiques")
	public String listeFormeJuridiques(ModelMap modelMap)
	{
		List<FormeJuridique> formj = formeJuridiqueService.getAllFormeJuridique();
		modelMap.addAttribute("formeJuridiques", formj);
		return "listeFormeJuridique";
	}
	
	@RequestMapping("/FormeJuridique/new")
	public String saveFormeJuridique(FormeJuridique formeJuridique)
	{
		formeJuridiqueRepo.save(formeJuridique);
		return "redirect:../listeFormeJuridiques";
	}
	
}
