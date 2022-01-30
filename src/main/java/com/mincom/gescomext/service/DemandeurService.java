package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.mincom.gescomext.entities.Demandeur;

public interface DemandeurService {
	Demandeur saveDemandeur(Demandeur elmt);
	Demandeur updateDemandeur(Demandeur elmt);
	void deleteDemandeur(Demandeur elmt);
	void deleteDemandeurById(Long id);
	Demandeur getDemandeurById(Long id);
	List<Demandeur> getAllDemandeur();
	List<Demandeur> findDemandeurByGeneralInfo (String code);
}
