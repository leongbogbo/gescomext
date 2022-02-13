package com.mincom.gescomext.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mincom.gescomext.entities.TypePieceIdentite;

public class TypePieceIdentiteValidator {
	public static List<String> validate(TypePieceIdentite valideElement){		
		List<String> errors = new ArrayList<>();
		if(valideElement == null || !StringUtils.hasLength(valideElement.getTitreTyp())) {
			errors.add("Veuillez renseigner le type de la pièce du représentant");
		}

		return errors;		
	}
}
