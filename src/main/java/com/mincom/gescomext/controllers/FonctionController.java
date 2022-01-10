package com.mincom.gescomext.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Fonction;
import com.mincom.gescomext.service.FonctionService;


@Controller
public class FonctionController {

	@Autowired
	FonctionService fonctionService;
	
	@RequestMapping("/parametre/listeFonctions")
	public String listeVilles(ModelMap modelMap)
	{
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
