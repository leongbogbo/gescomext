package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.repository.ActionListeRepository;

@Service
public class ActionListeServiceImpl implements ActionListeService {
	
	@Autowired
	ActionListeRepository actionListeRepository;

	@Override
	public ActionListe saveActionListe(ActionListe elmt) {
		return actionListeRepository.save(elmt);
	}

	@Override
	public ActionListe updateActionListe(ActionListe elmt) {
		return actionListeRepository.save(elmt);
	}

	@Override
	public void deleteActionListe(ActionListe elmt) {
		actionListeRepository.delete(elmt);		
	}

	@Override
	public void deleteActionListeById(Long id) {
		actionListeRepository.deleteById(id);		
	}

	@Override
	public ActionListe getActionListeById(Long id) {
		return actionListeRepository.findById(id).get();
	}

	@Override
	public List<ActionListe> getAllActionListe() {
		return actionListeRepository.findAll();
	}

}
