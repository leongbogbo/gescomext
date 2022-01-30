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
import com.mincom.gescomext.entities.Departement;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.DepartementService;


@Controller
public class DepartementController {

	@Autowired
	DepartementService departementService;
	@Autowired
	UserRepository	userRepo;
	
	@RequestMapping("/parametre/listeDepartements")
	public String listeVilles(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		
		List<Departement> deps = departementService.getAllDepartement();
		modelMap.addAttribute("deps", deps);
		return "autres/listeDepartement";
	}
	
	@RequestMapping("/parametre/Departement/new")
	public String saveDepartement(@Valid Departement departement, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) return "listeDepartement";
		departementService.saveDepartement(departement);
		return "redirect:../listeDepartements";
	}
}
