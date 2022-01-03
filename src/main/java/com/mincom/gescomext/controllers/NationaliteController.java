package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Nationalite;
import com.mincom.gescomext.repository.NationaliteRepository;
import com.mincom.gescomext.service.NationaliteService;

@Controller
public class NationaliteController {

	@Autowired
	NationaliteService nationaliteService;
	@Autowired
	NationaliteRepository nationaliteRepo;
	
	@RequestMapping("/listeNationalites")
	public String listeNationalites(ModelMap modelMap)
	{
		List<Nationalite> nats = nationaliteService.getAllNationalite();
		modelMap.addAttribute("nationalites", nats);
		return "listeNationalite";
	}
	
	@RequestMapping("/Nationalite/new")
	public String saveNationalite(Nationalite nationalite)
	{
		nationaliteRepo.save(nationalite);
		return "redirect:../listeNationalites";
	}
	
}
