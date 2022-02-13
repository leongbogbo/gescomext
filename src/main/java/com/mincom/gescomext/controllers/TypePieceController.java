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
import com.mincom.gescomext.entities.TypePieceIdentite;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.DepartementService;
import com.mincom.gescomext.service.TypePieceIdentiteService;


@Controller
public class TypePieceController {
	@Autowired
	TypePieceIdentiteService typePieceService;
	@Autowired
	UserRepository	userRepo;
	
	@RequestMapping("/parametre/listeTypePieces")
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
		
		List<TypePieceIdentite> deps = typePieceService.getAllTypePieceIdentite();
		modelMap.addAttribute("deps", deps);
		return "autres/listeTypePiece";
	}
	
	@RequestMapping("/parametre/TypePiece/new")
	public String saveDepartement(@Valid TypePieceIdentite typePiece, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) return "listeTypePiece";
		typePieceService.saveTypePieceIdentite(typePiece);
		return "redirect:../listeTypePieces";
	}
}
