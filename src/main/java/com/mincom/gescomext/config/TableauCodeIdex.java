package com.mincom.gescomext.config;

import java.util.ArrayList;
import java.util.List;

public class TableauCodeIdex {
    Integer baseDix;
    String key;
	
	public TableauCodeIdex(Integer baseDix, String key) {
		super();
		this.baseDix = baseDix;
		this.key = key;
	}
	
	public static List<TableauCorrespondance> getBaseDixNum() {
		List<TableauCorrespondance> tableauCorrespondance = new ArrayList<>();
        int base = 1;
        char[] contriKey = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for (int i = 0; i < 26; i++) {            
            if( base >= 9){ base = 0; } 
            base = base+1;            
            char data = (char)(65 + (i/26)*6 + i);
            tableauCorrespondance.add(new TableauCorrespondance(base, data,contriKey[i]));
        }
		return tableauCorrespondance;
	}

	public static List<TableauCodeIdex> getKeyIdex() {
		List<TableauCodeIdex> TableauCodeIdex = new ArrayList<>();
        int base = 0;
        String[] keys = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        for (int i = 0; i < 16; i++) {            
            TableauCodeIdex.add(new TableauCodeIdex(base,keys[base]));
            base = base+1;
        }
		return TableauCodeIdex;
	}



	public Integer getBaseDix() {
		return baseDix;
	}



	public void setBaseDix(Integer baseDix) {
		this.baseDix = baseDix;
	}



	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}
	
	
}
