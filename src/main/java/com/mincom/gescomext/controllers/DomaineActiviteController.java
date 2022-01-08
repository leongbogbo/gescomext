package com.mincom.gescomext.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Departement;
import com.mincom.gescomext.entities.DomaineActivite;
import com.mincom.gescomext.service.DomaineActiviteService;


@Controller
public class DomaineActiviteController {

	@Autowired
	DomaineActiviteService domaineActiviteService;
	
	@RequestMapping("/parametre/listeDomaineActivites")
	public String listeVilles(ModelMap modelMap)
	{
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
}
