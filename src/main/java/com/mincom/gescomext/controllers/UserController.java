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
import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.RoleService;
import com.mincom.gescomext.service.UserService;

@Controller
public class UserController{

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping("/administration/listeUtilisateurs")
	public String listeUtilisateurs(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		if(username.equals("superadmin")) {
			List<User> elmts = userService.getAllUser();
			modelMap.addAttribute("users", elmts);
		}else {
			List<User> elmts = userService.findBySite(user.getSite());
			modelMap.addAttribute("users", elmts);
		}
		
		List<Role> roleList = roleService.getAllRole();
		modelMap.addAttribute("roleList", roleList);
		modelMap.addAttribute("usersVide", new User());
		return "autres/listeUser";
	}
	@RequestMapping("/administration/Recherche/Utilisateur")
	public String rechUtilisateurs(String nomUser, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<User> elmts = userService.findBynomUserOrPrenomsUserContaining(nomUser,nomUser);
		List<Role> roleList = roleService.getAllRole();
		modelMap.addAttribute("users", elmts);
		modelMap.addAttribute("roleList", roleList);
		modelMap.addAttribute("usersVide", new User());
		return "autres/listeUser";
	}
	
	
	@RequestMapping("/administration/Utilisateurs/new")
	public String saveUser(User user, String passconfirm)
	{
		PasswordEncoder passwordEncoder = passwordEncoders();
		User verifUser = userService.findByUsername(user.getUsername().toLowerCase());
		if(verifUser==null) {
			if(user.getPassword().equals(passconfirm)) {
				user.setUsername(user.getUsername().toLowerCase());
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.setEnabled(false);
				userService.saveUser(user);
			}else {
				System.out.println("Mot de passe non conforme");
			}			
		}else {
			System.out.println("Pseudo deja occup??");
		}
		
		return "redirect:/administration/listeUtilisateurs";
	}
	
	@RequestMapping("/administration/Utilisateurs/Activation/{idUser}")
	public String activeUser(@PathVariable("idUser") Long idUser)
	{		
		User verifUser = userService.getUserById(idUser);
		if(verifUser!=null) {
			if(verifUser.getEnabled() == true) {
				verifUser.setEnabled(false);
			}else if(verifUser.getEnabled() == false) {
				verifUser.setEnabled(true);
			}			
			else {
				System.out.println("Option n'ont troouv??");
			}
			userService.saveUser(verifUser);
		}else {
			System.out.println("Pseudo deja occup??");
		}
		
		return "redirect:/administration/listeUtilisateurs";
	}
	
	@Bean
	public PasswordEncoder passwordEncoders () {
		return new BCryptPasswordEncoder();
	}
	
}
