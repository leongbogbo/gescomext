package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.CommuneRepository;
import com.mincom.gescomext.repository.UserRepository;
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
	@Autowired
	UserRepository	userRepo;
	
	@RequestMapping("/parametre/listeCommune")
	public String listeCommune(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
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
		return "redirect:../listeCommune";
	}
	
	@PostMapping("/test")
	@ResponseBody
	public String saveEntreprise(@RequestParam String nomCommune, @RequestParam String ville)
	{
		
		return nomCommune + " : " + ville;
	}
}
