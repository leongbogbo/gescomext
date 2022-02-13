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
import com.mincom.gescomext.entities.GenreMarque;
import com.mincom.gescomext.entities.Marque;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.GenreMarqueRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.GenreMarqueService;

@Controller
public class GenreMarqueController {

	@Autowired
	GenreMarqueService genreMarqueService;
	@Autowired
	GenreMarqueRepository genreMarqueRepo;
	@Autowired
	UserRepository	userRepo;
	
	@RequestMapping("/parametre/listeGenres")
	public String listeGenreMarques(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<GenreMarque> elmt = genreMarqueService.getAllGenreMarque();
		modelMap.addAttribute("genreMarques", elmt);
		modelMap.addAttribute("genreMarqueVide", new GenreMarque());
		return "autres/listeGenreMarque";
	}
	
	@RequestMapping("/parametre/GenreNew")
	public String saveMarque(@Valid GenreMarque genreMarque, BindingResult bindingResult,ModelMap modelMap)
	{
		if (bindingResult.hasErrors()) return "listeGenreMarque";
		genreMarqueRepo.save(genreMarque);
		List<GenreMarque> elmt = genreMarqueService.getAllGenreMarque();
		modelMap.addAttribute("genreMarques", elmt);
		return "autres/listeGenreMarque";
	}
	
	@RequestMapping("/parametre/Genre/{idGen}")
	public String AfficheMarque(@PathVariable("idGen") Long idGen, ModelMap modelMap){
		GenreMarque elmts = genreMarqueService.getGenreMarqueById(idGen);
		modelMap.addAttribute("genretrouve", elmts);
		return "/autres/updateGenreMarque";
	}
	
	@RequestMapping("/parametre/updateGenre")
	public String updateMarque(GenreMarque genre, ModelMap modelMap){
		
		genreMarqueService.saveGenreMarque(genre);
		List<GenreMarque> listeGenre = genreMarqueService.getAllGenreMarque();
		modelMap.addAttribute("genreMarques", listeGenre);
		return "redirect:/parametre/listeGenres";
	}
	
}
