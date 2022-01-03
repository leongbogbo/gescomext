package com.mincom.gescomext.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Marque;
import com.mincom.gescomext.repository.MarqueRepository;
import com.mincom.gescomext.service.MarqueService;

@Controller
public class MarqueController {

	@Autowired
	MarqueService marqueService;
	@Autowired
	MarqueRepository marqueRepo;
	
	@RequestMapping("/listeMarques")
	public String listeMarques(ModelMap modelMap)
	{
		List<Marque> elmt = marqueService.getAllMarque();
		modelMap.addAttribute("marques", elmt);
		modelMap.addAttribute("marqueVide", new Marque());
		return "listeMarque";
	}
	
	@RequestMapping("/MarqueNew")
	public String saveMarque(@Valid Marque marque, BindingResult bindingResult,ModelMap modelMap)
	{
		if (bindingResult.hasErrors()) return "listeMarque";
		marqueRepo.save(marque);
		List<Marque> elmt = marqueService.getAllMarque();
		modelMap.addAttribute("marques", elmt);
		return "listeMarque";
	}
	
}
