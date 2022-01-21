package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.TraitementOpCodeImportation;
import com.mincom.gescomext.repository.TraitementOpCodeImportationRepository;

@Service
public class TraitementOpCodeImportationServiceImpl implements TraitementOpCodeImportationService {
	
	@Autowired
	TraitementOpCodeImportationRepository reposy;

	@Override
	public TraitementOpCodeImportation saveTraitementOpCodeImportation(TraitementOpCodeImportation elmt) {
		return reposy.save(elmt);
	}

	@Override
	public TraitementOpCodeImportation updateTraitementOpCodeImportation(TraitementOpCodeImportation elmt) {
		return reposy.save(elmt);
	}

	@Override
	public void deleteTraitementOpCodeImportation(TraitementOpCodeImportation elmt) {
		reposy.delete(elmt);		
	}

	@Override
	public void deleteTraitementOpCodeImportationById(Long id) {
		reposy.deleteById(id);		
	}

	@Override
	public TraitementOpCodeImportation getTraitementOpCodeImportationById(Long id) {
		return reposy.findById(id).get();
	}

	@Override
	public List<TraitementOpCodeImportation> getAllTraitementOpCodeImportation() {
		return reposy.findAll();
	}

	@Override
	public List<TraitementOpCodeImportation> findTraitementOpCodeImportationByStatutTrait(String code) {
		return findTraitementOpCodeImportationByStatutTrait(code);
	}

	@Override
	public List<TraitementOpCodeImportation> findAllTraitementOpCodeImportationByTypeCodeOp(String code) {
		return reposy.findAllTraitementOpCodeImportationByTypeCodeOp(code);
	}

	@Override
	public TraitementOpCodeImportation findTraitementOpCodeImportationByStatut(Integer numDocOp, String statut) {
		return reposy.findTraitementOpCodeImportationByStatut(numDocOp, statut);
	}

	@Override
	public TraitementOpCodeImportation findTraitementOpCodeImportationByIDoP(Long idOp, String statut) {

		return reposy.findTraitementOpCodeImportationByIDoP(idOp, statut);
	}

}
