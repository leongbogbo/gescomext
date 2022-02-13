package com.mincom.gescomext.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.TypePieceIdentite;
import com.mincom.gescomext.entities.TypeStructure;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.entities.Departement;
import com.mincom.gescomext.entities.DomaineActivite;
import com.mincom.gescomext.entities.Fonction;
import com.mincom.gescomext.entities.FormeJuridique;
import com.mincom.gescomext.entities.GenreMarque;
import com.mincom.gescomext.entities.Marque;
import com.mincom.gescomext.entities.Nationalite;
import com.mincom.gescomext.repository.ActionListeRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.RoleService;
import com.mincom.gescomext.service.VilleService;
import com.mincom.gescomext.service.ActionListeService;
import com.mincom.gescomext.service.CommuneService;
import com.mincom.gescomext.service.DomaineActiviteService;
import com.mincom.gescomext.service.FormeJuridiqueService;
import com.mincom.gescomext.service.NationaliteService;

@Controller
public class StatistiquesController{

	@Autowired
	ActionListeService actionListeService;
	@Autowired
	RoleService roleService;
	@Autowired
	ActionListeRepository actionListeRepo;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommuneService communeService;
	@Autowired
	VilleService villeService;
	@Autowired
	NationaliteService natService;
	@Autowired
	FormeJuridiqueService fjuryService;
	@Autowired
	DomaineActiviteService domaineActiviteService;
	
	Date aujourdhui = new Date();
	SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm:ss");
	String dateDuJour = formater.format(aujourdhui);
	
	@RequestMapping("/Statistique/Entreprise")
	public String listeActions(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<Commune> coms = communeService.getAllCommune();
		List<Ville> vils = villeService.getAllVille();
		List<Nationalite> nats = natService.getAllNationalite();
		List<FormeJuridique> fmjury = fjuryService.getAllFormeJuridique();
		List<DomaineActivite> domaineActivite = domaineActiviteService.getAllDomaineActivite();
		
		modelMap.addAttribute("listeCommunes", coms);
		modelMap.addAttribute("listeVilles", vils);
		modelMap.addAttribute("listeNationalites", nats);
		modelMap.addAttribute("listeFormjury", fmjury);
		modelMap.addAttribute("listedomaineActivite", domaineActivite);
		
		modelMap.addAttribute("dateDuJour", dateDuJour);
		return "Statistiques/listeEntreprise";
	}
	
	@RequestMapping("/Statistique/AttributionActions")
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
	
	
	@RequestMapping("/Statistique/ActionProfile/new")
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
		return "redirect:/parametre/AttributionActions";
	}
}
