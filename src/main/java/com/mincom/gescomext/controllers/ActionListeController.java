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
import com.mincom.gescomext.service.UserService;
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
	@Autowired
	UserService userService;
	String validate = "non";
	
	@RequestMapping("/administration/listeActions")
	public String listeActions(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/listeActions")) {
				validate = "oui";
			}
			System.out.println(liens.getLienActPro());
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
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
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/AttributionActions")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
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
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/AttributionActions")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
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
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/listeSites")) {
				validate = "oui";
			}			
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
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
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/listeSites")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		if(site!=null) {
			siteService.saveSite(site);
		}
		return "redirect:/administration/listeSites";
	}
	
	@RequestMapping("/administration/Update/Site/{id}")
	public String updateSite(@PathVariable("id") Long id, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/listeSites")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		Site site = siteService.getSiteById(id);
		modelMap.addAttribute("siteTrouve", site);
		return "autres/updateSite";
	}
	@RequestMapping("/administration/Valider/Update/Site")
	public String updatevSite(Site site, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/listeSites")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		Site siteFound = siteService.getSiteById(site.getIdSite());
		if(siteFound != null) {
			siteFound.setNomSite(site.getNomSite());
			siteService.saveSite(siteFound);
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
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/AttributionSites")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		
		if(username.equals("superadmin")) {
			List<User> elmtUser = userService.getAllUser();
			modelMap.addAttribute("userListes", elmtUser);
		}else {
			List<User> elmtUser = userService.findBySite(user.getSite());
			modelMap.addAttribute("userListes", elmtUser);
		}
		
		List<Site> elmts = siteService.getAllSite();
		modelMap.addAttribute("siteListes", elmts);
		
		return "autres/attributionSite";
	}
	@RequestMapping("/administration/Recherche/User/Site")
	public String rechAttributionSites(String nomSite, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/AttributionSites")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		List<Site> elmts = siteService.getAllSite();
		List<User> elmtUser = userRepository.findBySite(siteService.findByNomSite(nomSite));
		
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
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/AttributionSites")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
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
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlUserAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("administration/AttributionSites")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
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
