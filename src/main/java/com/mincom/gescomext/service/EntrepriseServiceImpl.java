package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {
	
	@Autowired
	EntrepriseRepository entrepriseRepository;

	@Override
	public Entreprise saveEntreprise(Entreprise c) {
		return entrepriseRepository.save(c);
	}

	@Override
	public Entreprise updateEntreprise(Entreprise c) {
		return entrepriseRepository.save(c);
	}

	@Override
	public void deleteEntreprise(Entreprise c) {
		entrepriseRepository.delete(c);		
	}

	@Override
	public void deleteEntrepriseById(Long id) {
		entrepriseRepository.deleteById(id);		
	}

	@Override
	public Entreprise getEntrepriseById(Long id) {
		return entrepriseRepository.findById(id).get();
	}

	@Override
	public List<Entreprise> getAllEntreprise() {
		return entrepriseRepository.findAll();
	}

	@Override
	public Entreprise findByCodeImportExportEntr(String codeImportExportEntr) {
		return entrepriseRepository.findByCodeImportExportEntr(codeImportExportEntr);
	}

	@Override
	public Entreprise findByContribuableEntr(String contribuableEntr) {
		return entrepriseRepository.findByContribuableEntr(contribuableEntr);
	}

	@Override
	public Entreprise findByCodeImportExportEntrAndContribuableEntr(String code) {
		return entrepriseRepository.findByCodeImportExportEntrAndContribuableEntr(code);
	}

	

}
