package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.repository.RoleRepository;
import com.mincom.gescomext.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	RoleService roleService;
	@Autowired
	RoleRepository roleRepo;
	
	@RequestMapping("/parametre/listeRoles")
	public String listeUtilisateurs(ModelMap modelMap)
	{
		List<Role> elmts = roleService.getAllRole();
		modelMap.addAttribute("roles", elmts);
		modelMap.addAttribute("rolesVide", new Role());
		return "autres/listeRole";
	}
	
	@RequestMapping("/parametre/Role/new")
	public String saveRole(String role){
		Role roles = new Role();
		roles.setRole(role);
		roleRepo.save(roles);
		return "redirect:/parametre/listeRoles";
	}
	
}
