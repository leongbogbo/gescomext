package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.CommuneRepository;
import com.mincom.gescomext.service.CommuneService;
import com.mincom.gescomext.service.VilleService;
@Controller
public class CommuneController {
	
	@Autowired
	VilleService villeService;

	@Autowired
	CommuneService communeService;
	
	@Autowired
	CommuneRepository communeRepo;
	
	@RequestMapping("/parametre/listeCommune")
	public String listeCommune(ModelMap modelMap)
	{
		List<Commune> coms = communeService.getAllCommune();
		List<Ville> vils = villeService.getAllVille();
		modelMap.addAttribute("communes", coms);
		modelMap.addAttribute("listeVilles", vils);
		return "/autres/listeCommune";
	}
	
	@RequestMapping("/parametre/Commune/new")
	public String saveCommune(Commune commune)
	{
		communeRepo.save(commune);
		return "redirect:/parametre/listeCommune";
	}
	
	@PostMapping("/test")
	@ResponseBody
	public String saveEntreprise(@RequestParam String nomCommune, @RequestParam String ville)
	{
		
		return nomCommune + " : " + ville;
	}
}
