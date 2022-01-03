package com.mincom.gescomext.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.GenreMarque;
import com.mincom.gescomext.repository.GenreMarqueRepository;
import com.mincom.gescomext.service.GenreMarqueService;

@Controller
public class GenreMarqueController {

	@Autowired
	GenreMarqueService genreMarqueService;
	@Autowired
	GenreMarqueRepository genreMarqueRepo;
	
	@RequestMapping("/listeGenreMarques")
	public String listeGenreMarques(ModelMap modelMap)
	{
		List<GenreMarque> elmt = genreMarqueService.getAllGenreMarque();
		modelMap.addAttribute("genreMarques", elmt);
		modelMap.addAttribute("genreMarqueVide", new GenreMarque());
		return "listeGenreMarque";
	}
	
	@RequestMapping("/GenreMarqueNew")
	public String saveMarque(@Valid GenreMarque genreMarque, BindingResult bindingResult,ModelMap modelMap)
	{
		if (bindingResult.hasErrors()) return "listeGenreMarque";
		genreMarqueRepo.save(genreMarque);
		List<GenreMarque> elmt = genreMarqueService.getAllGenreMarque();
		modelMap.addAttribute("genreMarques", elmt);
		return "listeGenreMarque";
	}
	
}
