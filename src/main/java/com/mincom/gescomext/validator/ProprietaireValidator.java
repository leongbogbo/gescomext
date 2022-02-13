package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.Proprietaire;

public class ProprietaireValidator {
	
	public static List<String> validate(Proprietaire valideElement){		
		List<String> errors = new ArrayList<>();
		if(valideElement == null ) {
			errors.add("Veuillez renseigner tous les champs obligatoire");
		}
		if(valideElement.getNomProp() == null || !StringUtils.hasLength(valideElement.getNomProp())) {
			errors.add("Veuillez renseigner le nom  du représentant");
		}
		if(valideElement.getPrenomsProp() == null || !StringUtils.hasLength(valideElement.getPrenomsProp())) {
			errors.add("Veuillez renseigner le prénom du représentant");
		}
		if(valideElement.getSexeProp() == null || !StringUtils.hasLength(valideElement.getSexeProp())) {
			errors.add("Veuillez renseigner le sexe du représentant");
		}
		if(valideElement.getEmailProp() == null || !StringUtils.hasLength(valideElement.getEmailProp())) {
			errors.add("Veuillez renseigner l'E-Mail du représentant");
		}
		else if(!EmailValidator.isValidEmailAddress(valideElement.getEmailProp())){
			errors.add("Veuillez renseigner un E-Mail valide du représentant");
		}
		if(valideElement.getNumpieceProp() == null || !StringUtils.hasLength(valideElement.getNumpieceProp())) {
			
			errors.add("Veuillez renseigner le N° de la pièce du représentant");
		}
		if(valideElement.getTelProp() == null || !StringUtils.hasLength(valideElement.getTelProp())) {
			errors.add("Veuillez renseigner le téléphone du représentant");
		}
		
		return errors;		
	}

}
