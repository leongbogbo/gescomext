package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Departement;
import com.mincom.gescomext.repository.DepartementRepository;

@Service
public class DepartementServiceImpl implements DepartementService {
	
	@Autowired
	DepartementRepository departementRepository;

	@Override
	public Departement saveDepartement(Departement elmt) {
		return departementRepository.save(elmt);
	}

	@Override
	public Departement updateDepartement(Departement elmt) {
		return departementRepository.save(elmt);
	}

	@Override
	public void deleteDepartement(Departement elmt) {
		departementRepository.delete(elmt);		
	}

	@Override
	public void deleteDepartementById(Long id) {
		departementRepository.deleteById(id);		
	}

	@Override
	public Departement getDepartementById(Long id) {
		return departementRepository.findById(id).get();
	}

	@Override
	public List<Departement> getAllDepartement() {
		return departementRepository.findAll();
	}

	@Override
	public Departement findByTitreDep(String titreDep) {
		return departementRepository.findByTitreDep(titreDep);
	}

	@Override
	public List<Departement> findBytitreDepContaining(String titreDep) {
		return departementRepository.findBytitreDepContaining(titreDep);
	}

}
