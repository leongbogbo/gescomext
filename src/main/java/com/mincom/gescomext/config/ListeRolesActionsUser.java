package com.mincom.gescomext.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.repository.UserRepository;

public class ListeRolesActionsUser {
	
	@Autowired
	UserRepository userRepository;
			
	public List<ActionListe> getListeAcctions(User user, String category) {
		List<ActionListe> actionListe = new ArrayList<>();
		user.getRoles().forEach(roles->{
			roles.getActionListe().forEach(actions->{
				if(actions.getLienActPro().split("/")[0].equals(category)) {
					actionListe.add(actions);					
				}
			});
		});
		
		return actionListe;
	}
}
