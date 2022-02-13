package com.mincom.gescomext.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetCurrentUser {
	
	public static String getUserConnected() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName =null;
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}
		return currentUserName;
	}
	
	/*public void getLinkUserConnected(String category){
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
	}*/
}
