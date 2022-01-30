package com.mincom.gescomext.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.service.RoleService;
import com.mincom.gescomext.service.UserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = passwordEncoder();
		//System.out.println(passwordEncoder.encode("gescomext@2022")); 
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*http.authorizeRequests()
		.antMatchers("/CodeImportExport/Liste","/CodeImportExport/CreationDossier","/CodeImportExport/Renouvellement","/CodeImportExport/Duplicata","/CodeImportExport/Paiement","/CodeImportExport/Approbation","/CodeImportExport/Signature","/CodeImportExport/EditionFiches","/CodeImportExport/listeEtatCodes").hasAnyRole("ADMIN");
		http.authorizeRequests()
		.antMatchers("/CodeOccasionnel/Liste","/CodeOccasionnel/CreationDossier","/CodeOccasionnel/Duplicata","/CodeOccasionnel/Paiement","/CodeOccasionnel/Approbation","/CodeOccasionnel/Signature","/CodeOccasionnel/EditionFiches","/CodeOccasionnel/listeEtatCodes").hasAnyRole("ADMIN");
		http.authorizeRequests()
		.antMatchers("/LeveeDeGage/Liste","/LeveeDeGage/CreationDossier","/LeveeDeGage/Duplicata","/LeveeDeGage/Paiement","/LeveeDeGage/Approbation","/LeveeDeGage/Signature","/LeveeDeGage/EditionFiches","/LeveeDeGage/listeEtatCodes").hasAnyRole("ADMIN");
		http.authorizeRequests()
		.antMatchers("/parametre/listeVilles","/parametre/listeCommune","/parametre/listeNationalites","/parametre/listeFormeJuridiques","/parametre/listeUtilisateurs","/parametre/listeRoles").hasAnyRole("ADMIN");
		
		
		List<Role> roles =  roleService.getAllRole();
		for(Role role : roles) {
			String rolep = role.getRole();
			List<ActionListe> actionListes = roleService.getRoleById(role.getRole_id()).getActionListe();
			List<String> urlList = new ArrayList<>();
			for(ActionListe actionListe : actionListes) {
				urlList.add(actionListe.getLienActPro());	
			}
			String[] authorities = urlList.toArray(new String[0]);			
			http.authorizeRequests().antMatchers("/CodeImportExport/Liste").access("@userSecurity.hasUser(authentication,#user_id");	
			System.out.println(role.getRole().toString());
		}
		
		System.out.println("on a redemarer");
		
		roles.forEach(profile->{
			List<String> urlList = new ArrayList<>();
			profile.getActionListe().forEach(profileAction->{
				urlList.add("/"+profileAction.getLienActPro());	
			});
			String[] authorities = urlList.toArray(new String[0]);
			try {
				http.authorizeRequests().antMatchers(authorities).hasAuthority(profile.getRole().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}	
		});*/
		
		
		/*if(authentications != null) {
			String username = GetCurrentUser.getUserConnected();
			if(!username.equals(null)) {
				User agent = userService.findByUsername(username);
				agent.getRoles().forEach(profileAgent->{					
					try {
						System.out.println(profileAgent.getRole());
						profileAgent.getActionListe().forEach(actionsAgent->{
							try {
								System.out.println(actionsAgent.getLienActPro());
								http.authorizeRequests().antMatchers("/"+actionsAgent.getLienActPro()).hasAnyRole(profileAgent.getRole());
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}				
				});			
			}
		}*/
		
		http.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/images/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/svg/**").permitAll()
		.antMatchers("/classes/**").permitAll()
		.antMatchers("/pdf/**").permitAll()
		.antMatchers("/WEB-INF/**").permitAll()
		.anyRequest().authenticated();
		http.formLogin().loginPage("/login");
		http.exceptionHandling().accessDeniedPage("/accessDenied");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
	return new BCryptPasswordEncoder();
	}
}