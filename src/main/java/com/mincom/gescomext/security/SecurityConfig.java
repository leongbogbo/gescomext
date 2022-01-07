package com.mincom.gescomext.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.service.UserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	UserService userService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = passwordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String  roles="";
		//User connecté
		Authentication authentications = SecurityContextHolder.getContext().getAuthentication();
		if(authentications != null) {			
			String username = GetCurrentUser.getUserConnected();
			if(username.equals(null)) {
				User agent = userService.findByUsername(username);
				agent.getRoles().forEach(roleAgent->{					
					try {
						System.out.println(roleAgent.getRole());
						http.authorizeRequests().antMatchers("/parametre/listeUtilisateurs").hasAnyRole(roleAgent.getRole());
					} catch (Exception e) {
						e.printStackTrace();
					}				
				});			
			}
		}
		
		http.authorizeRequests()
		.antMatchers("/CodeImportExport/Liste","/CodeImportExport/CreationDossier","/CodeImportExport/Renouvellement","/CodeImportExport/Duplicata","/CodeImportExport/Paiement","/CodeImportExport/Approbation","/CodeImportExport/Signature","/CodeImportExport/EditionFiches","/CodeImportExport/listeEtatCodes").hasAnyRole("ADMIN");
		http.authorizeRequests()
		.antMatchers("/CodeOccasionnel/Liste","/CodeOccasionnel/CreationDossier","/CodeOccasionnel/Renouvellement","/CodeOccasionnel/Duplicata","/CodeOccasionnel/Paiement","/CodeOccasionnel/Approbation","/CodeOccasionnel/Signature","/CodeOccasionnel/EditionFiches","/CodeOccasionnel/listeEtatCodes").hasAnyRole("ADMIN");
		http.authorizeRequests()
		.antMatchers("/LeveeDeGage/Liste","/LeveeDeGage/CreationDossier","/LeveeDeGage/Renouvellement","/LeveeDeGage/Duplicata","/LeveeDeGage/Paiement","/LeveeDeGage/Approbation","/LeveeDeGage/Signature","/LeveeDeGage/EditionFiches","/LeveeDeGage/listeEtatCodes").hasAnyRole("ADMIN");
		http.authorizeRequests()
		.antMatchers("/parametre/listeVilles","/parametre/listeCommune","/parametre/listeNationalites","/parametre/listeFormeJuridiques","/parametre/listeUtilisateurs","/parametre/listeRoles").hasAnyRole("ADMIN");
		
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/images/**").permitAll();
		http.authorizeRequests().antMatchers("/js/**").permitAll();
		http.authorizeRequests().antMatchers("/svg/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().loginPage("/login");
		http.exceptionHandling().accessDeniedPage("/accessDenied");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
	return new BCryptPasswordEncoder();
	}
}