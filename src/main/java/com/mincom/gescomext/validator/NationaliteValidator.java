package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.Nationalite;

public class NationaliteValidator {
	public static List<String> validate(Nationalite valideElement){		
		List<String> errors = new ArrayList<>();
		if(valideElement == null || !StringUtils.hasLength(valideElement.getTitreNat())) {
			errors.add("Veuillez renseigner la nationalité du représentant");
		}

		return errors;		
	}
}
