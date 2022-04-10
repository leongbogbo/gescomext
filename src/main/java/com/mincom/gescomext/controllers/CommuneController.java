package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

import groovyjarjarpicocli.CommandLine.Model;
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
	
	@RequestMapping("/parametre/Recherche/Commune")
	public String rechCommune(String nomCommune, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		Commune commune = communeService.findBynomCommune(nomCommune);
		modelMap.addAttribute("communes", commune);
		List<Ville> vils = villeService.getAllVille();
		modelMap.addAttribute("listeVilles", vils);
		return "autres/listeCommune";
	}
	
	@RequestMapping("/parametre/Update/Commune/{id}")
	public String updateCommune(@PathVariable("id") Long id, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		Commune commune = communeService.getCommuneById(id);
		List<Ville> ville = villeService.getAllVille();
		modelMap.addAttribute("commune", commune);
		modelMap.addAttribute("listeVilles", ville);
		return "./autres/updateCommune";
	}
	@RequestMapping("/parametre/Valider/Update/Commune")
	public String updatevCommune(Commune commune, ModelMap modelMap)
	{
		Commune communeFound = communeService.getCommuneById(commune.getIdCommune());
		Ville villeFound = villeService.getVilleById(commune.getVille().getIdVille());
		if(communeFound!=null && villeFound!=null) {
			communeFound.setNomCommune(commune.getNomCommune());
			communeFound.setVille(villeFound);
			communeService.saveCommune(communeFound);
		}
		return "redirect:../../listeCommune";
	}
	
	
}
