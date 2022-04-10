package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Marque;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.MarqueRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.MarqueService;
import com.mincom.gescomext.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	MarqueService marqueService;
	@Autowired
	UserService userService;
	@Autowired
	MarqueRepository marqueRepo;
	@Autowired
	UserRepository  userRepo;
	
	@RequestMapping("/administration/monProfile")
	public String listeMarques(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userService.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		user.setNomUser(user.getNomUser().toUpperCase());
		user.setPrenomsUser(user.getPrenomsUser().toUpperCase());
		user.setUsername(user.getUsername().toUpperCase());		
		modelMap.addAttribute("infosuser", user);
		return "autres/profileUser";
	}
	
	
	@RequestMapping("/administration/profile/{id}")
	public String AfficheMarque(@PathVariable("id") Long id, ModelMap modelMap){
		
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		Marque elmts = marqueService.getMarqueById(id);
		modelMap.addAttribute("marquetrouve", elmts);
		return "/autres/updateMarque";
	}
	
	@RequestMapping("/administration/updatePassProfile")
	public String updateMarque(Long idProfile, String passWd, String passConfirm, ModelMap modelMap){
		
		if(passWd.equals(passConfirm)) {
			User userFind = userService.getUserById(idProfile);
			PasswordEncoder passwordEncoder = passwordEncoderus();
			userFind.setPassword(passwordEncoder.encode(passConfirm));
			userService.saveUser(userFind);
		}
		return "redirect:./monProfile";
	}
	
	@Bean
	public PasswordEncoder passwordEncoderus () {
		return new BCryptPasswordEncoder();
	}
	
}
