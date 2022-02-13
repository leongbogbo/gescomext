package com.mincom.gescomext.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Fonction;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.FonctionService;


@Controller
public class FonctionController {

	@Autowired
	FonctionService fonctionService;
	@Autowired
	UserRepository	userRepo;
	
	@RequestMapping("/parametre/listeFonctions")
	public String listeVilles(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<Fonction> deps = fonctionService.getAllFonction();
		modelMap.addAttribute("deps", deps);
		return "autres/listeFonction";
	}
	
	@RequestMapping("/parametre/Fonction/new")
	public String saveDepartement(@Valid Fonction fonction, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) return "listeFonction";
		fonctionService.saveFonction(fonction);
		return "redirect:../listeFonctions";
	}
	

	
}
