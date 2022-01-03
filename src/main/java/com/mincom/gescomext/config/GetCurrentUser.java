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

}
