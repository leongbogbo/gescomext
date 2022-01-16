package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.Beneficiaire;

public interface BeneficiaireService {
	Beneficiaire saveBeneficiaire(Beneficiaire elmt);
	Beneficiaire updateBeneficiaire(Beneficiaire elmt);
	void deleteBeneficiaire(Beneficiaire elmt);
	void deleteBeneficiaireById(Long id);
	Beneficiaire getBeneficiaireById(Long id);
	List<Beneficiaire> getAllBeneficiaire();
}
