package com.mincom.gescomext.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.DomaineActivite;
import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.FormeJuridique;
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

	@Override
	public Entreprise findByRegcommerceEntr(String regcommerceEntr) {
		// TODO Auto-generated method stub
		return entrepriseRepository.findByRegcommerceEntr(regcommerceEntr);
	}

	@Override
	public Entreprise findByNumIduEntr(String numIduEntr) {
		return entrepriseRepository.findByNumIduEntr(numIduEntr);
	}

	@Override
	public Entreprise findByNomEntr(String nomEntr) {
		return entrepriseRepository.findByNomEntr(nomEntr);
	}

	@Override
	public List<Entreprise> findEntrepriseByGeneralInfo(String code) {
		return entrepriseRepository.findEntrepriseByGeneralInfo(code);
	}

	@Override
	public List<Entreprise> findBynomEntrContaining(String nomEntr) {
		return entrepriseRepository.findBynomEntrContaining(nomEntr);
	}

	@Override
	public List<Entreprise> findByFormeJuridiqueAndDomaineActiviteLike(Long idjury, Long idomAct) {
		return entrepriseRepository.findByFormeJuridiqueAndDomaineActiviteLike(idjury, idomAct);
	}

	@Override
	public List<Entreprise> findByFormeJuridique(Long idjury) {
		return entrepriseRepository.findByFormeJuridique(idjury);
	}

	@Override
	public List<Entreprise> findByDomaineActivite(Long idomAct) {
		return entrepriseRepository.findByDomaineActivite(idomAct);
	}

	@Override
	public List<Entreprise> findStatAllEntreprise(Date datedebut, Date datefin) {
		return entrepriseRepository.findStatAllEntreprise(datedebut, datefin);
	}

	

}
