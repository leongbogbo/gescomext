package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Nationalite;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.NationaliteRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.NationaliteService;

@Controller
public class NationaliteController {

	@Autowired
	NationaliteService nationaliteService;
	@Autowired
	NationaliteRepository nationaliteRepo;
	@Autowired
	UserRepository	userRepo;
	
	@RequestMapping("/parametre/listeNationalites")
	public String listeNationalites(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<Nationalite> nats = nationaliteService.getAllNationalite();
		modelMap.addAttribute("nationalites", nats);
		return "autres/listeNationalite";
	}
	
	@RequestMapping("/parametre/Nationalite/new")
	public String saveNationalite(Nationalite nationalite)
	{
		
		nationaliteRepo.save(nationalite);
		return "redirect:../listeNationalites";
	}
	
	@RequestMapping("/parametre/Recherche/Nationalite")
	public String rechNationalite(String titreNat, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		Nationalite nationalite = nationaliteService.findByTitreNat(titreNat);
		modelMap.addAttribute("nationalites", nationalite);
		return "autres/listeNationalite";
	}
	
	@RequestMapping("/parametre/Update/Nationalite/{id}")
	public String updateNationalite(@PathVariable("id") Long id, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		Nationalite nationalite = nationaliteService.getNationaliteById(id);
		modelMap.addAttribute("nationalite", nationalite);
		return "./autres/updateNationalite";
	}
	
	@RequestMapping("/parametre/Valider/Update/Nationalite")
	public String updatevNationalite(Nationalite nationalite, ModelMap modelMap)
	{
		Nationalite nationaliteFound = nationaliteService.getNationaliteById(nationalite.getIdNat());
		if(nationaliteFound!=null) {
			nationaliteFound.setTitreNat(nationalite.getTitreNat());
			nationaliteService.saveNationalite(nationaliteFound);
		}
		return "redirect:../../listeNationalites";
	}
	
}
