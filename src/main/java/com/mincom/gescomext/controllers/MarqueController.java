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
import com.mincom.gescomext.entities.Marque;
import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.MarqueRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.MarqueService;

@Controller
public class MarqueController {

	@Autowired
	MarqueService marqueService;
	@Autowired
	MarqueRepository marqueRepo;
	@Autowired
	UserRepository	userRepo;
	
	@RequestMapping("/parametre/listeMarques")
	public String listeMarques(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<Marque> elmt = marqueService.getAllMarque();
		modelMap.addAttribute("marques", elmt);
		modelMap.addAttribute("marqueVide", new Marque());
		return "autres/listeMarque";
	}
	
	@RequestMapping("parametre/MarqueNew")
	public String saveMarque(@Valid Marque marque, BindingResult bindingResult,ModelMap modelMap)
	{
		if (bindingResult.hasErrors()) return "listeMarque";
		marqueRepo.save(marque);
		List<Marque> elmt = marqueService.getAllMarque();
		modelMap.addAttribute("marques", elmt);
		return "autres/listeMarque";
	}
	
	@RequestMapping("/parametre/Marque/{idMarque}")
	public String AfficheMarque(@PathVariable("idMarque") Long idMarque, ModelMap modelMap){
		Marque elmts = marqueService.getMarqueById(idMarque);
		modelMap.addAttribute("marquetrouve", elmts);
		return "/autres/updateMarque";
	}
	
	@RequestMapping("/parametre/updateMarque")
	public String updateMarque(Marque marque, ModelMap modelMap){
		
		marqueService.saveMarque(marque);
		List<Marque> listeMarque = marqueService.getAllMarque();
		modelMap.addAttribute("Marque", listeMarque);
		return "redirect:/parametre/listeMarques";
	}
	
}
