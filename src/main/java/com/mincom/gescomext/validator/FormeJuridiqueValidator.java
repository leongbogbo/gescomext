package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.FormeJuridique;

public class FormeJuridiqueValidator {
	public static List<String> validate(FormeJuridique valideElement){		
		List<String> errors = new ArrayList<>();
		if(valideElement == null || !StringUtils.hasLength(valideElement.getTitreJury())) {
			errors.add("Veuillez renseigner la forme juridique");
		}
		
		return errors;		
	}
}
