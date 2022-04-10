package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.mincom.gescomext.entities.TraitementOpCodeImportation;

public interface TraitementOpCodeImportationService {
	TraitementOpCodeImportation saveTraitementOpCodeImportation(TraitementOpCodeImportation elmt);
	TraitementOpCodeImportation updateTraitementOpCodeImportation(TraitementOpCodeImportation elmt);
	void deleteTraitementOpCodeImportation(TraitementOpCodeImportation elmt);
	void deleteTraitementOpCodeImportationById(Long id);
	TraitementOpCodeImportation getTraitementOpCodeImportationById(Long id);
	List<TraitementOpCodeImportation> getAllTraitementOpCodeImportation();
	List<TraitementOpCodeImportation> findTraitementOpCodeImportationByStatutTrait(String code);
	
	List<TraitementOpCodeImportation> findAllTraitementOpCodeImportationByTypeCodeOp (String code, String site);
	TraitementOpCodeImportation findTraitementOpCodeImportationByStatut (Integer numDocOp, String statut, String site);
	TraitementOpCodeImportation findTraitementOpCodeImportationByIDoP (Long idOp, String statut, String site);
	
	//SUPPER ADMIN
	List<TraitementOpCodeImportation> findAllTraitementOpCodeImportationByTypeCodeOpSuper (String code);
	TraitementOpCodeImportation findTraitementOpCodeImportationByStatutSuper (Integer numDocOp, String statut);
	TraitementOpCodeImportation findTraitementOpCodeImportationByIDoPSuper (Long idOp, String statut);
	
}
