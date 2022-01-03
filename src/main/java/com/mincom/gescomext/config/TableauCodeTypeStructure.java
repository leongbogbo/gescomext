package com.mincom.gescomext.config;

import java.util.ArrayList;
import java.util.List;

public class TableauCodeTypeStructure {
    String codes;
    Integer decimal;
    String hexadecimal;

    public static List<TableauCodeTypeStructure> getCodeTypeStructures() {
		List<TableauCodeTypeStructure> tableauCodeTypeStructure = new ArrayList<>();
        tableauCodeTypeStructure.add(new TableauCodeTypeStructure("420001",01,"01"));
        tableauCodeTypeStructure.add(new TableauCodeTypeStructure("42000A",10,"0A"));
        tableauCodeTypeStructure.add(new TableauCodeTypeStructure("42000C",12,"0C"));
        tableauCodeTypeStructure.add(new TableauCodeTypeStructure("42001D",13,"1D"));
        tableauCodeTypeStructure.add(new TableauCodeTypeStructure("42002E",14,"2E"));
        tableauCodeTypeStructure.add(new TableauCodeTypeStructure("42003F",15,"3F"));
        
		return tableauCodeTypeStructure;
	}

    public TableauCodeTypeStructure(String codes, Integer decimal, String hexadecimal) {
        this.codes = codes;
        this.decimal = decimal;
        this.hexadecimal = hexadecimal;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public Integer getDecimal() {
        return decimal;
    }

    public void setDecimal(Integer decimal) {
        this.decimal = decimal;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public void setHexadecimal(String hexadecimal) {
        this.hexadecimal = hexadecimal;
    }

}
