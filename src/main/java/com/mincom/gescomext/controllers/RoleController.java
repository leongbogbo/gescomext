package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.RoleRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	RoleService roleService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	UserRepository	userRepo;
	String validate = "non";
	@RequestMapping("/administration/listeRoles")
	public String listeUtilisateurs(ModelMap modelMap)
	{
		validate = "non";
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/listeRoles")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		List<Role> elmts = roleService.getAllRole();
		modelMap.addAttribute("roles", elmts);
		modelMap.addAttribute("rolesVide", new Role());
		return "autres/listeRole";
	}
	
	@RequestMapping("/administration/Role/new")
	public String saveRole(String role){
		validate = "non";
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/listeRoles")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		Role roles = new Role();
		roles.setRole(role);
		roleRepo.save(roles);
		return "redirect:/administration/listeRoles";
	}
	
	@RequestMapping("/administration/Role/{role_id}")
	public String AfficheRole(@PathVariable("role_id") Long role_id, ModelMap modelMap){
		validate = "non";
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/listeRoles")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		Role roles = roleService.getRoleById(role_id);
		modelMap.addAttribute("roles", roles);
		return "/autres/updateRole";
	}
	
	@RequestMapping("/administration/updateRole")
	public String updateRole(String role, Long role_id, ModelMap modelMap){
		validate = "non";
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		System.out.println(username);
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/listeRoles")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		Role roles = new Role();
		roles.setRole_id(role_id);
		roles.setRole(role);
		roleService.saveRole(roles);
		List<Role> listeRole = roleService.getAllRole();
		modelMap.addAttribute("listeRole", listeRole);
		return "redirect:/administration/listeRoles";
	}
	
}
