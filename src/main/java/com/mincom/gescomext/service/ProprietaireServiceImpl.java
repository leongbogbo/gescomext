package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mincom.gescomext.entities.Proprietaire;
import com.mincom.gescomext.repository.ProprietaireRepository;

@Service
public class ProprietaireServiceImpl implements ProprietaireService {
	
	@Autowired
	ProprietaireRepository proprietaireRepository;

	@Override
	public Proprietaire saveProprietaire(Proprietaire elmt) {
		return proprietaireRepository.save(elmt);
	}

	@Override
	public Proprietaire updateProprietaire(Proprietaire elmt) {
		return proprietaireRepository.save(elmt);
	}

	@Override
	public void deleteProprietaire(Proprietaire elmt) {
		proprietaireRepository.delete(elmt);		
	}

	@Override
	public void deleteProprietaireById(Long id) {
		proprietaireRepository.deleteById(id);		
	}

	@Override
	public Proprietaire getProprietaireById(Long id) {
		return proprietaireRepository.findById(id).get();
	}

	@Override
	public List<Proprietaire> getAllProprietaire() {
		return proprietaireRepository.findAll();
	}

}
