package com.mincom.gescomext.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Departement;
import com.mincom.gescomext.service.DepartementService;


@Controller
public class DepartementController {

	@Autowired
	DepartementService departementService;
	
	@RequestMapping("/parametre/listeDepartements")
	public String listeVilles(ModelMap modelMap)
	{
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
