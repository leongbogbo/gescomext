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
import com.mincom.gescomext.entities.FormeJuridique;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.FormeJuridiqueRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.FormeJuridiqueService;

@Controller
public class FormeJuridiqueController {

	@Autowired
	FormeJuridiqueService formeJuridiqueService;
	@Autowired
	FormeJuridiqueRepository formeJuridiqueRepo;
	@Autowired
	UserRepository	userRepo;
	
	@RequestMapping("/parametre/listeFormeJuridiques")
	public String listeFormeJuridiques(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<FormeJuridique> formj = formeJuridiqueService.getAllFormeJuridique();
		modelMap.addAttribute("formeJuridiques", formj);
		return "autres/listeFormeJuridique";
	}
	
	@RequestMapping("/parametre/FormeJuridique/new")
	public String saveFormeJuridique(FormeJuridique formeJuridique)
	{
		formeJuridiqueRepo.save(formeJuridique);
		return "redirect:../listeFormeJuridiques";
	}
	
	@RequestMapping("/parametre/Recherche/FormeJuridique")
	public String rechFormeJuridique(String titreJury, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		FormeJuridique formj = formeJuridiqueRepo.findByTitreJury(titreJury);
		modelMap.addAttribute("formeJuridiques", formj);
		return "autres/listeFormeJuridique";
	}
	
	@RequestMapping("/parametre/Update/FormeJuridique/{id}")
	public String updateFormeJuridique(@PathVariable("id") Long id, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		FormeJuridique formeJuridique = formeJuridiqueService.getFormeJuridiqueById(id);
		modelMap.addAttribute("formeJuridique", formeJuridique);
		return "./autres/updateFormeJuridique";
	}
	
	@RequestMapping("/parametre/Valider/Update/FormeJuridique")
	public String updatevFormeJuridique(FormeJuridique formeJuridique)
	{
		FormeJuridique formeJuridiqueFound = formeJuridiqueService.getFormeJuridiqueById(formeJuridique.getIdJury());
		if(formeJuridiqueFound!=null) {
			formeJuridiqueFound.setTitreJury(formeJuridique.getTitreJury());
			formeJuridiqueService.saveFormeJuridique(formeJuridiqueFound);
		}
		return "redirect:../../listeFormeJuridiques";
	}
	
}
