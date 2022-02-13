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

		if (valideElement.getRegcommerceEntr() == null || !StringUtils.hasLength(valideElement.getRegcommerceEntr())) {
			errors.add("Veuillez renseigner le N° RCCM");
		}
		if(valideElement.getRegcommerceEntr() != null){
			String[] tabRccm = valideElement.getRegcommerceEntr().split("-");
			
			try {
				int result = Integer.parseInt(tabRccm[2]);
			} catch (NumberFormatException e) {
				// do something for the exception.
				errors.add("Le code juridique du RCCM doit être un nombre: " + tabRccm[2]);
			}
			
			if (tabRccm.length != 6 || !valideElement.getRegcommerceEntr().contains("-")) {
				errors.add("Mauvais format du N° RCCM ");
			}
		}
		if(valideElement.getContribuableEntr() == null || !StringUtils.hasLength(valideElement.getContribuableEntr())) {
			errors.add("Veuillez renseigner le N° CC");
		}else {
			if (valideElement.getContribuableEntr().length() < 8) {
				errors.add("Le nombre de caractère du CC est inferieur à 8 : " + valideElement.getContribuableEntr());
			} else if (valideElement.getContribuableEntr().length() > 8) {
				errors.add("Le nombre de caractère du CC est supérieur à 8 : " + valideElement.getContribuableEntr());
			} else {
				try {
					int results = Integer.parseInt(valideElement.getContribuableEntr().substring(0, 7));
				} catch (NumberFormatException e) {
					// do something for the exception.
					errors.add("Les 7 prémiers caracteres du CC doivent être des nombres: "
							+ valideElement.getContribuableEntr().substring(0, 7));
				}
				try {
					int results = Integer.parseInt(valideElement.getContribuableEntr().substring(7, 8));
					errors.add("Le dernier caractère du CC doit être un caractère: "
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
