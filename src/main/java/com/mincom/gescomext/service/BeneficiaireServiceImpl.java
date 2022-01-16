package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Beneficiaire;
import com.mincom.gescomext.repository.BeneficiaireRepository;

@Service
public class BeneficiaireServiceImpl implements BeneficiaireService {
	
	@Autowired
	BeneficiaireRepository beneficiaireRepository;

	@Override
	public Beneficiaire saveBeneficiaire(Beneficiaire elmt) {
		return beneficiaireRepository.save(elmt);
	}

	@Override
	public Beneficiaire updateBeneficiaire(Beneficiaire elmt) {
		return beneficiaireRepository.save(elmt);
	}

	@Override
	public void deleteBeneficiaire(Beneficiaire elmt) {
		beneficiaireRepository.delete(elmt);
		
	}

	@Override
	public void deleteBeneficiaireById(Long id) {
		beneficiaireRepository.deleteById(id);;
		
	}

	@Override
	public Beneficiaire getBeneficiaireById(Long id) {
		return beneficiaireRepository.getById(id);
	}

	@Override
	public List<Beneficiaire> getAllBeneficiaire() {
		return beneficiaireRepository.findAll();
	}

	
}
