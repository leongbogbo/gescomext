package com.mincom.gescomext.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.EntrepriseRepository;
import com.mincom.gescomext.repository.RoleRepository;
import com.mincom.gescomext.service.ActionListeService;
import com.mincom.gescomext.service.EntrepriseService;
import com.mincom.gescomext.service.SiteService;
import com.mincom.gescomext.service.UserService;
import com.mincom.gescomext.service.VilleService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ValidationRESTController {
	
	@Autowired
	VilleService villeService;
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ActionListeService actionListeService;
	@Autowired
	SiteService siteService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/form/listeVille", method = RequestMethod.GET)
	public List<Ville> getAllVilles() {
		return villeService.getAllVille();
	}

	@RequestMapping(value="/raisonSociale/{raison}", method = RequestMethod.GET)
	public Entreprise getElement(@PathVariable("raison") String raison) {
		return entrepriseRepository.findByNomEntr(raison);
	}
	
	@RequestMapping(value="/cpici/raisonSociale/{raison}", method = RequestMethod.GET)
	public Map<String, String> getCpiciElement(@PathVariable("raison") String raison) {
		 Map<String, String> morse = new HashMap<>();		 
		 if(entrepriseRepository.findByNomEntr(raison) !=null) {
			  morse.put("respone", "true");
			  morse.put("msg", "trouve");
			  morse.put("raison", raison);
		 }else {
			 morse.put("respone", "false");
			 morse.put("msg", "element non trouvé");
		 }
		return morse;
	}
	
	@RequestMapping(value="/numidu/{numidu}", method = RequestMethod.GET)
	public Entreprise getElementnumidu(@PathVariable("numidu") String numidu) {
		return entrepriseRepository.findByNumIduEntr(numidu);
	}
	
	@RequestMapping(value="/contribuable/{contribuable}", method = RequestMethod.GET)
	public Entreprise getElementcontribuable(@PathVariable("contribuable") String contribuable) {
		return entrepriseRepository.findByContribuableEntr(contribuable);
	}
	
	@RequestMapping(value="/regicommerce/{regicommerce}", method = RequestMethod.GET)
	public Entreprise getElementregicommerce(@PathVariable("regicommerce") String regicommerce) {
		return entrepriseRepository.findByRegcommerceEntr(regicommerce);
	}
	
	@RequestMapping(value="/profile/action/{idprofile}", method = RequestMethod.GET)
	public List<ActionListe> getActionProfile(@PathVariable("idprofile") Long idprofile) {
		return roleRepository.getById(idprofile).getActionListe();
	}
	
	@RequestMapping(value="/profile/listeAction", method = RequestMethod.GET)
	public List<ActionListe> getActions() {
		return actionListeService.getAllActionListe();
	}
	
	@RequestMapping(value="/parametre/listeUser", method = RequestMethod.GET)
	public List<User> getUser() {
		return userService.getAllUser();
	}
	
	@RequestMapping(value="/parametre/site/{id}", method = RequestMethod.GET)
	public List<User> getUserByIdSite(@PathVariable("id") Long id) {
		return siteService.getSiteById(id).getUser();
	}

}
