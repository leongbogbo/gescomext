package com.mincom.gescomext.config;

import java.util.ArrayList;
import java.util.List;

public class TableauCorrespondance {
    Integer baseDix;
    String lettreAncien;
    char key;
    char contriKey;

    

   

    public TableauCorrespondance(Integer baseDix, String lettreAncien, char key) {
		super();
		this.baseDix = baseDix;
		this.lettreAncien = lettreAncien;
		this.key = key;
	}
    
    

	public TableauCorrespondance(Integer baseDix, char key, char contriKey) {
		super();
		this.baseDix = baseDix;
		this.key = key;
		this.contriKey = contriKey;
	}



	public static List<TableauCorrespondance> getAlphaBaseDix() {
		List<TableauCorrespondance> tableauCorrespondance = new ArrayList<>();
        int base = 0;
        char[] keys = {'A','B','C','D','E','F','G','H','J','K','L','M','N','P','S','T','U','V','W','X','Y','Z'};
        String[] ancien = {"A","B","M1","M2","M3","M","C","D","E","F","G","H","J","K","N","P","Q","R","S","T","U","V"};
        for (int i = 0; i < 22; i++) {            
            //if( base >= 9){ base = 0; } 
            base = base+1;            
            //char data = (char)(65 + (i/23)*6 + i);
            tableauCorrespondance.add(new TableauCorrespondance(base,ancien[base-1],keys[base-1]));
        }
		return tableauCorrespondance;
	}
    
    public static List<TableauCorrespondance> getContribuableCode() {
		List<TableauCorrespondance> tableauCorrespondance = new ArrayList<>();
        int base = 0;
        char[] contriKey = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for (int i = 0; i < 26; i++) {            
            //if( base >= 9){ base = 0; } 
            base = base+1;            
            char data = (char)(65 + (i/26)*6 + i);
            tableauCorrespondance.add(new TableauCorrespondance(base, data,contriKey[base-1]));
        }
		return tableauCorrespondance;
	}

	public Integer getBaseDix() {
		return baseDix;
	}

	public void setBaseDix(Integer baseDix) {
		this.baseDix = baseDix;
	}

	public String getLettreAncien() {
		return lettreAncien;
	}

	public void setLettreAncien(String lettreAncien) {
		this.lettreAncien = lettreAncien;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}



	public char getContriKey() {
		return contriKey;
	}



	public void setContriKey(char contriKey) {
		this.contriKey = contriKey;
	}
    


}
