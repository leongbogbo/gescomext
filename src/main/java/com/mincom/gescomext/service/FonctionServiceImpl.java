package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Fonction;
import com.mincom.gescomext.repository.FonctionRepository;

@Service
public class FonctionServiceImpl implements FonctionService {
	
	@Autowired
	FonctionRepository fonctionRepository;

	@Override
	public Fonction saveFonction(Fonction elmt) {
		return fonctionRepository.save(elmt);
	}

	@Override
	public Fonction updateFonction(Fonction elmt) {
		return fonctionRepository.save(elmt);
	}

	@Override
	public void deleteFonction(Fonction elmt) {
		fonctionRepository.delete(elmt);		
	}

	@Override
	public void deleteFonctionById(Long id) {
		fonctionRepository.deleteById(id);		
	}

	@Override
	public Fonction getFonctionById(Long id) {
		return fonctionRepository.findById(id).get();
	}

	@Override
	public List<Fonction> getAllFonction() {
		return fonctionRepository.findAll();
	}

	@Override
	public Fonction findByTitreFonc(String titreFonc) {
		return fonctionRepository.findByTitreFonc(titreFonc);
	}

	@Override
	public List<Fonction> findByTitreFoncContaining(String titreFonc) {
		return fonctionRepository.findByTitreFoncContaining(titreFonc);
	}

}
