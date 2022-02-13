package com.mincom.gescomext.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalculeCodesIdex {

	Date dateDAy = new Date();

	public static String getCodeCodeIdex(String CCcode, String nameUser,Integer numOdre) {
		Integer partieNumCC = 0;
		Integer lettreA=0;
		String lettreB="";
		String lettreC="";
		String codeIdex="";
		char lettreNom;
		
		numOdre =numOdre+11;
		List<TableauCodeIdex> tableauCodeIdex = TableauCodeIdex.getKeyIdex();
		List<TableauCorrespondance> tableauCodeIdexpers = TableauCodeIdex.getBaseDixNum();
		//calcule de la lettre A
		
		if(CCcode != null && !CCcode.isEmpty()) {
			partieNumCC = Integer.parseInt(CCcode.substring(0, 7));
			lettreA= partieNumCC%9;
		}else if(nameUser != null && !nameUser.isEmpty()) {
			lettreNom = nameUser.substring(0,1).charAt(0);
			for (TableauCorrespondance donnees : tableauCodeIdexpers) {
				if (donnees.getContriKey() == lettreNom) {
					lettreA = donnees.getBaseDix();
				}
			}
		}
		if(lettreA == 0){
			lettreA=9;
		}
		//calcule de BC
		Integer multyA = lettreA * 10;
		//Conversion en base 16
		Integer quotient = multyA/16;
		Integer modulo = multyA%16;
		//trouver la lettre B
		for (TableauCodeIdex donnees : tableauCodeIdex) {
			if (donnees.getBaseDix() == quotient) {
				lettreB = donnees.getKey();
			}
		}
		//trouver la lettre C
		for (TableauCodeIdex donnees : tableauCodeIdex) {
			if (donnees.getBaseDix() == modulo) {
				lettreC = donnees.getKey();
			}
		}
		
		codeIdex = String.valueOf(lettreA) + String.valueOf(lettreB) + String.valueOf(lettreC) +"-"+ String.valueOf(numOdre);
		
		return codeIdex;
	}
}
