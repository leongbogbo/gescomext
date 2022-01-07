package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.repository.ActionListeRepository;
import com.mincom.gescomext.service.RoleService;
import com.mincom.gescomext.service.ActionListeService;

@Controller
public class ActionListeController{

	@Autowired
	ActionListeService actionListeService;
	@Autowired
	RoleService roleService;
	@Autowired
	ActionListeRepository actionListeRepo;
	
	@RequestMapping("/parametre/listeActions")
	public String listeActions(ModelMap modelMap)
	{
		List<ActionListe> elmts = actionListeService.getAllActionListe();
		List<Role> roleList = roleService.getAllRole();
		modelMap.addAttribute("actionListes", elmts);
		modelMap.addAttribute("roleList", roleList);
		modelMap.addAttribute("ActionListesVide", new ActionListe());
		return "autres/listeAction";
	}
	
	@RequestMapping("/parametre/AttributionActions")
	public String listeAttributionActions(ModelMap modelMap)
	{
		List<ActionListe> elmts = actionListeService.getAllActionListe();
		List<Role> roleList = roleService.getAllRole();
		modelMap.addAttribute("actionListes", elmts);
		modelMap.addAttribute("roleList", roleList);
		modelMap.addAttribute("ActionListesVide", new ActionListe());
		return "autres/attributionAction";
	}
	
	
	@RequestMapping("/parametre/ActionProfile/new")
	public String saveActionListe(Role role)
	{	
		if(role.getRole_id()==null) {
			Role roleFind = roleService.getRoleById(role.getRole_id());
			System.out.println(roleFind.getRole());
			roleFind.setActionListe(role.getActionListe());
			roleService.saveRole(roleFind);
		}
		return "redirect:/parametre/AttributionActions";
	}
}
