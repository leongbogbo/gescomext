package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Demandeur;
import com.mincom.gescomext.repository.DemandeurRepository;

@Service
public class DemandeurServiceImpl implements DemandeurService {
	
	@Autowired
	DemandeurRepository demandeurRepository;

	@Override
	public Demandeur saveDemandeur(Demandeur elmt) {
		return demandeurRepository.save(elmt);
	}

	@Override
	public Demandeur updateDemandeur(Demandeur elmt) {
		return demandeurRepository.save(elmt);
	}

	@Override
	public void deleteDemandeur(Demandeur elmt) {
		demandeurRepository.delete(elmt);
	}

	@Override
	public void deleteDemandeurById(Long id) {
		demandeurRepository.deleteById(id);
	}

	@Override
	public Demandeur getDemandeurById(Long id) {
		return demandeurRepository.getById(id);
	}

	@Override
	public List<Demandeur> getAllDemandeur() {
		return demandeurRepository.findAll();
	}

	@Override
	public List<Demandeur> findDemandeurByGeneralInfo(String code) {
		return demandeurRepository.findDemandeurByGeneralInfo(code);
	}

	
}
