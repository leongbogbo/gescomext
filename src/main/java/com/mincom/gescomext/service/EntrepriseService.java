package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.mincom.gescomext.entities.Entreprise;

public interface EntrepriseService {
	Entreprise saveEntreprise(Entreprise c);
	Entreprise updateEntreprise(Entreprise c);
	void deleteEntreprise(Entreprise c);
	void deleteEntrepriseById(Long id);
	Entreprise getEntrepriseById(Long id);
	List<Entreprise> getAllEntreprise();
	Entreprise findByCodeImportExportEntr(String codeImportExportEntr);
	Entreprise findByContribuableEntr (String contribuableEntr);
	Entreprise findByCodeImportExportEntrAndContribuableEntr (String code);
	Entreprise findByNomEntr(String nomEntr);
	Entreprise findByRegcommerceEntr(String regcommerceEntr);
	Entreprise findByNumIduEntr(String numIduEntr);
	List<Entreprise> findEntrepriseByGeneralInfo (String code);

}
