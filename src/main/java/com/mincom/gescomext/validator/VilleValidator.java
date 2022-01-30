package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.Ville;

public class VilleValidator {
	public static List<String> validate(Ville ville){		
		List<String> errors = new ArrayList<>();
		if(ville == null || !StringUtils.hasLength(ville.getNomVille())) {
			errors.add("Veuillez renseigner le nom de la ville");
		}
		return errors;
	}
}
