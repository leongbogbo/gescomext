package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.Beneficiaire;

public class BeneficiaireValidator {
	
	public static List<String> validate(Beneficiaire valideElement){		
		List<String> errors = new ArrayList<>();
		if(valideElement == null ) {
			errors.add("Veuillez renseigner tous les champs obligatoire");
		}
		if(valideElement.getNomBen() == null || !StringUtils.hasLength(valideElement.getNomBen())) {
			errors.add("Veuillez renseigner le nom  du représentant");
		}
		if(valideElement.getPrenomsBen() == null || !StringUtils.hasLength(valideElement.getPrenomsBen())) {
			errors.add("Veuillez renseigner le prénom du représentant");
		}
		if(valideElement.getSexeBen() == null || !StringUtils.hasLength(valideElement.getSexeBen())) {
			errors.add("Veuillez renseigner le sexe du représentant");
		}
		if(valideElement.getEmailBen() == null || !StringUtils.hasLength(valideElement.getEmailBen())) {
			errors.add("Veuillez renseigner l'E-Mail du représentant");
		}
		else if(!EmailValidator.isValidEmailAddress(valideElement.getEmailBen())){
			errors.add("Veuillez renseigner un E-Mail valide du représentant");
		}
		if(valideElement.getNumpieceBen() == null || !StringUtils.hasLength(valideElement.getNumpieceBen())) {
			
			errors.add("Veuillez renseigner le N° de la pièce du représentant");
		}
		if(valideElement.getTelBen() == null || !StringUtils.hasLength(valideElement.getTelBen())) {
			errors.add("Veuillez renseigner le téléphone du représentant");
		}
		
		return errors;		
	}

}
