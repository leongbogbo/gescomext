package com.mincom.gescomext.config;

import java.util.ArrayList;
import java.util.List;

public class TableauCorrespondance {
    char lettre;
    Integer baseDix;
    char correpondance;

    

    public TableauCorrespondance(char lettre, Integer baseDix, char correpondance) {
        this.lettre = lettre;
        this.baseDix = baseDix;
        this.correpondance = correpondance;
    }

    public static List<TableauCorrespondance> getAlphaBaseDix() {
		List<TableauCorrespondance> tableauCorrespondance = new ArrayList<>();
        int base = 0;
        char[] caract = {'A','B','C','D','E','F','G','H','J','K','L','M','N','P','R','S','T','U','V','W','X','Y','Z'};
        for (int i = 0; i < 23; i++) {            
            //if( base >= 9){ base = 0; } 
            base = base+1;            
            char data = (char)(65 + (i/23)*6 + i);
            tableauCorrespondance.add(new TableauCorrespondance(data,base,caract[base-1]));
        }
		return tableauCorrespondance;
	}
    public char getLettre() {
        return lettre;
    }

    public void setLettre(char lettre) {
        this.lettre = lettre;
    }

    public Integer getBaseDix() {
        return baseDix;
    }

    public void setBaseDix(Integer baseDix) {
        this.baseDix = baseDix;
    }

    public char getCorrepondance() {
        return correpondance;
    }

    public void setCorrepondance(char correpondance) {
        this.correpondance = correpondance;
    }

}
