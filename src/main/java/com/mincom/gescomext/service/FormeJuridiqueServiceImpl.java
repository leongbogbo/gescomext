package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.FormeJuridique;
import com.mincom.gescomext.repository.FormeJuridiqueRepository;

@Service
public class FormeJuridiqueServiceImpl implements FormeJuridiqueService {
	
	@Autowired
	FormeJuridiqueRepository formeJuridiqueRepository;

	@Override
	public FormeJuridique saveFormeJuridique(FormeJuridique elmt) {
		return formeJuridiqueRepository.save(elmt);
	}

	@Override
	public FormeJuridique updateFormeJuridique(FormeJuridique elmt) {
		return formeJuridiqueRepository.save(elmt);
	}

	@Override
	public void deleteFormeJuridique(FormeJuridique elmt) {
		formeJuridiqueRepository.delete(elmt);		
	}

	@Override
	public void deleteFormeJuridiqueById(Long id) {
		formeJuridiqueRepository.deleteById(id);		
	}

	@Override
	public FormeJuridique getFormeJuridiqueById(Long id) {
		return formeJuridiqueRepository.findById(id).get();
	}

	@Override
	public List<FormeJuridique> getAllFormeJuridique() {
		return formeJuridiqueRepository.findAll();
	}

	@Override
	public FormeJuridique findByTitreJury(String titreJury) {
		// TODO Auto-generated method stub
		return formeJuridiqueRepository.findByTitreJury(titreJury);
	}

}
