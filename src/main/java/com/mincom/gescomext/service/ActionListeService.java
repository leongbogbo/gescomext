package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.ActionListe;

public interface ActionListeService {
	ActionListe saveActionListe(ActionListe elmt);
	ActionListe updateActionListe(ActionListe elmt);
	void deleteActionListe(ActionListe elmt);
	void deleteActionListeById(Long id);
	ActionListe getActionListeById(Long id);
	List<ActionListe> getAllActionListe();

}
