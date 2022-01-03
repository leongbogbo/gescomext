package com.mincom.gescomext.config;

import java.util.ArrayList;
import java.util.List;

public class TableauJoursSemaine {
    String jour;
    Integer numero;

    public static List<TableauJoursSemaine> getJoursSemaines() {
		List<TableauJoursSemaine> tableauJoursSemaine = new ArrayList<>();
        tableauJoursSemaine.add(new TableauJoursSemaine("lundi",6));
        tableauJoursSemaine.add(new TableauJoursSemaine("mardi",5));
        tableauJoursSemaine.add(new TableauJoursSemaine("mercredi",4));
        tableauJoursSemaine.add(new TableauJoursSemaine("jeudi",3));
        tableauJoursSemaine.add(new TableauJoursSemaine("vendredi",2));
        tableauJoursSemaine.add(new TableauJoursSemaine("samedi",1));
		return tableauJoursSemaine;
	}

    public TableauJoursSemaine(String jour, Integer numero) {
        this.jour = jour;
        this.numero = numero;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    

}
