package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.repository.ActionListeRepository;
import com.mincom.gescomext.repository.UserRepository;
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
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/parametre/listeActions")
	public String listeActions(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		
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
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		
		List<ActionListe> elmts = actionListeService.getAllActionListe();
		List<Role> roleList = roleService.getAllRole();
		modelMap.addAttribute("actionListes", elmts);
		modelMap.addAttribute("roleList", roleList);
		modelMap.addAttribute("ActionListesVide", new ActionListe());
		return "autres/attributionAction";
	}
	
	
	@RequestMapping("/parametre/ActionProfile/new")
	public String saveActionListe(Role role, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		
		if(role.getRole_id()!=null) {
			Role roleFind = roleService.getRoleById(role.getRole_id());
			roleFind.setActionListe(role.getActionListe());
			roleService.saveRole(roleFind);
		}
		return "redirect:/parametre/AttributionActions";
	}
}
