package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.Site;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.repository.ActionListeRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.RoleService;
import com.mincom.gescomext.service.SiteService;
import com.mincom.gescomext.service.ActionListeService;

@Controller
public class ActionListeController{

	@Autowired
	ActionListeService actionListeService;
	@Autowired
	RoleService roleService;
	@Autowired
	SiteService siteService;
	@Autowired
	ActionListeRepository actionListeRepo;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/administration/listeActions")
	public String listeActions(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<ActionListe> elmts = actionListeService.getAllActionListe();
		List<Role> roleList = roleService.getAllRole();
		modelMap.addAttribute("actionListes", elmts);
		modelMap.addAttribute("roleList", roleList);
		modelMap.addAttribute("ActionListesVide", new ActionListe());
		return "autres/listeAction";
	}
	
	@RequestMapping("/administration/AttributionActions")
	public String listeAttributionActions(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<ActionListe> elmts = actionListeService.getAllActionListe();
		List<Role> roleList = roleService.getAllRole();
		modelMap.addAttribute("actionListes", elmts);
		modelMap.addAttribute("roleList", roleList);
		modelMap.addAttribute("ActionListesVide", new ActionListe());
		return "autres/attributionAction";
	}
	
	
	@RequestMapping("/administration/ActionProfile/new")
	public String saveActionListe(Role role, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		if(role.getRole_id()!=null) {
			Role roleFind = roleService.getRoleById(role.getRole_id());
			roleFind.setActionListe(role.getActionListe());
			roleService.saveRole(roleFind);
		}
		return "redirect:/administration/AttributionActions";
	}
	
	@RequestMapping("/administration/listeSites")
	public String listeSites(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<Site> elmts = siteService.getAllSite();
		modelMap.addAttribute("siteListes", elmts);
		return "autres/listeSite";
	}
	
	@RequestMapping("/administration/Site/new")
	public String saveSite(Site site, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		if(site!=null) {
			siteService.saveSite(site);
		}
		return "redirect:/administration/listeSites";
	}
	
	@RequestMapping("/administration/AttributionSites")
	public String listeAttributionSites(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<Site> elmts = siteService.getAllSite();
		List<User> elmtUser = userRepository.findAll();
		
		modelMap.addAttribute("siteListes", elmts);
		modelMap.addAttribute("userListes", elmtUser);
		return "autres/attributionSite";
	}
	
	@RequestMapping("/administration/siteProfile/new")
	public String saveuserSite(Site site, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		if(site!=null) {
			site.getUser().forEach(users->{
				User findUser = userRepository.getById(users.getUser_id());
				if(findUser!=null) {					
					findUser.setSite(site);
					userRepository.save(findUser);
				}
			});
		}
		return "redirect:/administration/AttributionSites";
	}
	
	@RequestMapping("/administration/siteProfile/{id}/suppr")
	public String suprUserSite(@PathVariable("id") Long id, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		if(id!=null) {			
			User findUser = userRepository.getById(id);
			if(findUser!=null) {					
				findUser.setSite(null);
				userRepository.save(findUser);
			}
		}
		return "redirect:/administration/AttributionSites";
	}
}
