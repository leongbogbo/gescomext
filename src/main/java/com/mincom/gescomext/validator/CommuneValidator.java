package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.Commune;

public class CommuneValidator {
	public static List<String> validate(Commune valideElement){		
		List<String> errors = new ArrayList<>();
		if(valideElement == null || !StringUtils.hasLength(valideElement.getNomCommune())) {
			errors.add("Veuillez renseigner la commune");
		}
		
		return errors;		
	}
}
