package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.TraitementOpCodeImportation;

public interface TraitementOpCodeImportationService {
	TraitementOpCodeImportation saveTraitementOpCodeImportation(TraitementOpCodeImportation elmt);
	TraitementOpCodeImportation updateTraitementOpCodeImportation(TraitementOpCodeImportation elmt);
	void deleteTraitementOpCodeImportation(TraitementOpCodeImportation elmt);
	void deleteTraitementOpCodeImportationById(Long id);
	TraitementOpCodeImportation getTraitementOpCodeImportationById(Long id);
	List<TraitementOpCodeImportation> getAllTraitementOpCodeImportation();
	List<TraitementOpCodeImportation> findTraitementOpCodeImportationByStatutTrait(String code);
	List<TraitementOpCodeImportation> findAllTraitementOpCodeImportationByTypeCodeOp (String code);
}
