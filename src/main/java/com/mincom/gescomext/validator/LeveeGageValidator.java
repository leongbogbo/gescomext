package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.CodeImportation;
import com.mincom.gescomext.entities.Entreprise;

public class LeveeGageValidator {
	public static List<String> validate(CodeImportation valideElement) {
		List<String> errors = new ArrayList<>();

		if (valideElement == null) {
			errors.add("Veuillez renseigner tous les champs obligatoire");
		}


		if (valideElement.getUsageGag() == null || !StringUtils.hasLength(valideElement.getUsageGag())) {
			errors.add("Veuillez selectionner l'usage");
		}
		
		if (valideElement.getNumChassisGag() == null || !StringUtils.hasLength(valideElement.getNumChassisGag())) {
			errors.add("Veuillez renseigner le numero de chassis");
		}

		if (valideElement.getDateGag() == null) {
			errors.add("Veuillez renseigner la date");
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
