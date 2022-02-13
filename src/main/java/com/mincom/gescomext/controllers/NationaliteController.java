package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
		System.out.println(username);
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
	
}
