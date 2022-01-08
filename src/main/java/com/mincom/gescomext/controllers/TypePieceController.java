package com.mincom.gescomext.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Departement;
import com.mincom.gescomext.entities.TypePieceIdentite;
import com.mincom.gescomext.service.DepartementService;
import com.mincom.gescomext.service.TypePieceIdentiteService;


@Controller
public class TypePieceController {
	@Autowired
	TypePieceIdentiteService typePieceService;
	
	@RequestMapping("/parametre/listeTypePieces")
	public String listeVilles(ModelMap modelMap)
	{
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
