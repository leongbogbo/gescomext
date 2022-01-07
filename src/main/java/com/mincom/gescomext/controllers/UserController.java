package com.mincom.gescomext.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/parametre/listeUtilisateurs")
	public String listeUtilisateurs(ModelMap modelMap)
	{
		List<User> elmts = userService.getAllUser();
		List<Role> roleList = roleService.getAllRole();
		modelMap.addAttribute("users", elmts);
		modelMap.addAttribute("roleList", roleList);
		modelMap.addAttribute("usersVide", new User());
		return "autres/listeUser";
	}
	
	
	@RequestMapping("/parametre/Utilisateurs/new")
	public String saveUser(User user, String passconfirm)
	{
		PasswordEncoder passwordEncoder = passwordEncoders();
		User verifUser = userService.findByUsername(user.getUsername().toLowerCase());
		if(verifUser==null) {
			if(user.getPassword().equals(passconfirm)) {
				user.setUsername(user.getUsername().toLowerCase());
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userService.saveUser(user);
			}else {
				System.out.println("Mot de passe non conforme");
			}			
		}else {
			System.out.println("Pseudo deja occupé");
		}
		
		return "redirect:/parametre/listeUtilisateurs";
	}
	
	@Bean
	public PasswordEncoder passwordEncoders () {
		return new BCryptPasswordEncoder();
	}
	
}
