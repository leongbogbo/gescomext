package com.mincom.gescomext.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Departement;
import com.mincom.gescomext.entities.DomaineActivite;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.DomaineActiviteService;


@Controller
public class DomaineActiviteController {

	@Autowired
	DomaineActiviteService domaineActiviteService;
	@Autowired
	UserRepository	userRepo;
	
	@RequestMapping("/parametre/listeDomaineActivites")
	public String listeVilles(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<DomaineActivite> deps = domaineActiviteService.getAllDomaineActivite();
		modelMap.addAttribute("deps", deps);
		return "autres/listeDomaineActivite";
	}
	
	@RequestMapping("/parametre/DomaineActivite/new")
	public String saveDepartement(@Valid DomaineActivite domaineActivite, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) return "listeDomaineActivite";
		domaineActiviteService.saveDomaineActivite(domaineActivite);
		return "redirect:../listeDomaineActivites";
	}
	@RequestMapping("/parametre/Recherche/DomaineActivite")
	public String rechDepartement(String titreDom, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<DomaineActivite> domaineActivite = domaineActiviteService.findBytitreDomActContaining(titreDom);
		modelMap.addAttribute("deps", domaineActivite);
		return "autres/listeDomaineActivite";
	}
	
	@RequestMapping("/parametre/Update/DomaineActivite/{id}")
	public String updateDepartement(@PathVariable("id") Long id, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		DomaineActivite domaineActivite = domaineActiviteService.getDomaineActiviteById(id);
		modelMap.addAttribute("domaineActivite", domaineActivite);
		return "./autres/updateDomaineActivite";
	}
	
	@RequestMapping("/parametre/Valider/Update/DomaineActivite")
	public String updateVDepartement(DomaineActivite domaineActivite, ModelMap modelMap)
	{
		DomaineActivite domaineActiviteFound = domaineActiviteService.getDomaineActiviteById(domaineActivite.getIdDomAct());
		if(domaineActivite!=null) {
			domaineActiviteFound.setTitreDomAct(domaineActivite.getTitreDomAct());
			domaineActiviteService.saveDomaineActivite(domaineActiviteFound);
		}
		return "redirect:../../listeDomaineActivites";
	}
}
