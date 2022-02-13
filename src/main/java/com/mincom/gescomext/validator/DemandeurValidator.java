package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.Demandeur;

public class DemandeurValidator {
	
	public static List<String> validate(Demandeur valideElement){		
		List<String> errors = new ArrayList<>();
		if(valideElement == null ) {
			errors.add("Veuillez renseigner tous les champs obligatoire");
		}
		if(valideElement.getNomDem() == null || !StringUtils.hasLength(valideElement.getNomDem())) {
			errors.add("Veuillez renseigner le nom  du représentant");
		}
		if(valideElement.getPrenomsDem() == null || !StringUtils.hasLength(valideElement.getPrenomsDem())) {
			errors.add("Veuillez renseigner le prénom du représentant");
		}
		if(valideElement.getSexeDem() == null || !StringUtils.hasLength(valideElement.getSexeDem())) {
			errors.add("Veuillez renseigner le sexe du représentant");
		}
		if(valideElement.getEmailDem() == null || !StringUtils.hasLength(valideElement.getEmailDem())) {
			errors.add("Veuillez renseigner l'E-Mail du représentant");
		}
		else if(!EmailValidator.isValidEmailAddress(valideElement.getEmailDem())){
			errors.add("Veuillez renseigner un E-Mail valide du représentant");
		}
		if(valideElement.getNumpieceDem() == null || !StringUtils.hasLength(valideElement.getNumpieceDem())) {
			
			errors.add("Veuillez renseigner le N° de la pièce du représentant");
		}
		if(valideElement.getTelDem() == null || !StringUtils.hasLength(valideElement.getTelDem())) {
			errors.add("Veuillez renseigner le téléphone du représentant");
		}
		
		if(valideElement.getContribuableDem() == null || !StringUtils.hasLength(valideElement.getContribuableDem())) {
			errors.add("Veuillez renseigner le N° CC");
		}else {
			if (valideElement.getContribuableDem().length() < 8) {
				errors.add("Le nombre de caractère du CC est inferieur à 8 : " + valideElement.getContribuableDem());
			} else if (valideElement.getContribuableDem().length() > 8) {
				errors.add("Le nombre de caractère du CC est supérieur à 8 : " + valideElement.getContribuableDem());
			} else {
				try {
					int results = Integer.parseInt(valideElement.getContribuableDem().substring(0, 7));
				} catch (NumberFormatException e) {
					// do something for the exception.
					errors.add("Les 7 prémiers caracteres du CC doivent être des nombres: "
							+ valideElement.getContribuableDem().substring(0, 7));
				}
				try {
					int results = Integer.parseInt(valideElement.getContribuableDem().substring(7, 8));
					errors.add("Le dernier caractère du CC doit être un caractère: "
							+ valideElement.getContribuableDem().substring(7, 8));
				} catch (NumberFormatException ex) {
					
				}
			}			
		}
		
		return errors;		
	}

}
