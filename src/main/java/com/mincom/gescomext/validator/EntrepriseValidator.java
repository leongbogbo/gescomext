package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.Entreprise;

public class EntrepriseValidator {
	public static List<String> validate(Entreprise valideElement) {
		List<String> errors = new ArrayList<>();

		if (valideElement == null) {
			errors.add("Veuillez renseigner tous les champs obligatoire");
		}
		if(valideElement.getTypeStructure().getIdStruc()!=8) {
			if(valideElement.getExoregcomEntr().equals("non")) {
				if (valideElement.getRegcommerceEntr() == null || !StringUtils.hasLength(valideElement.getRegcommerceEntr())) {
					errors.add("Veuillez renseigner le N° RCCM");
				}
			
			if(valideElement.getRegcommerceEntr() != null){
				String[] tabRccm = valideElement.getRegcommerceEntr().split("-");
				
				if (tabRccm.length != 5 && tabRccm.length != 6) {
					errors.add("Format du RCCM non valide");
				}
				if (tabRccm.length == 6) {
					try {
						int result = Integer.parseInt(tabRccm[2]);
					} catch (NumberFormatException e) {
						// do something for the exception.
						errors.add("Format du RCCM non valide : " + tabRccm[2]);
					}
					if(tabRccm[0].toString().length() != 2 || tabRccm[1].toString().length() != 3 || tabRccm[2].toString().length() != 2 || tabRccm[3].toString().length() != 4 || tabRccm[4].toString().length() != 3){
						errors.add("Format du RCCM non valide");
					}
				}
				
				if (tabRccm.length == 5) {
					try {
						int result = Integer.parseInt(tabRccm[3]);
						errors.add("Format du RCCM non valide : " + tabRccm[3]);
					} catch (NumberFormatException e) {
						// do something for the exception.
					}
					if(tabRccm[0].toString().length() != 2 || tabRccm[1].toString().length() != 3 || tabRccm[2].toString().length() != 4){
						errors.add("Format du RCCM non valide");
					}
				}
				
			}
		}
		}
		if(valideElement.getContribuableEntr() == null || !StringUtils.hasLength(valideElement.getContribuableEntr())) {
			errors.add("Veuillez renseigner le N° CC");
		}else {
			if (valideElement.getContribuableEntr().length() < 8) {
				errors.add("Format du Compte Contribuable non valide : " + valideElement.getContribuableEntr());
			} else if (valideElement.getContribuableEntr().length() > 8) {
				errors.add("Format du Compte Contribuable non valide : " + valideElement.getContribuableEntr());
			} else {
				try {
					int results = Integer.parseInt(valideElement.getContribuableEntr().substring(0, 7));
				} catch (NumberFormatException e) {
					// do something for the exception.
					errors.add("Format du Compte Contribuable non valide : "
							+ valideElement.getContribuableEntr().substring(0, 7));
				}
				try {
					int results = Integer.parseInt(valideElement.getContribuableEntr().substring(7, 8));
					errors.add("Format du Compte Contribuable non valide : "
							+ valideElement.getContribuableEntr().substring(7, 8));
				} catch (NumberFormatException ex) {
					
				}
			}			
		}

		if (valideElement.getNomEntr() == null || !StringUtils.hasLength(valideElement.getNomEntr())) {
			errors.add("Veuillez renseigner le nom de l'entreprise");
		}
		if (valideElement.getEmailEntr() == null || !StringUtils.hasLength(valideElement.getEmailEntr())) {
			errors.add("Veuillez renseigner l'E-Mail");
		} else if (!EmailValidator.isValidEmailAddress(valideElement.getEmailEntr())) {
			errors.add("Veuillez renseigner un E-Mail valide de l'entreprise");
		}
		

		
		/*
		 * if(valideElement.getCodeIdexEntr() == null ||
		 * !StringUtils.hasLength(valideElement.getCodeIdexEntr())) { errors.
		 * add("Erreur dans le calcul de l'identifiant unique, veuillez verifier le format du N° RCCM et du N° CC"
		 * ); }
		 */

		return errors;
	}
}
