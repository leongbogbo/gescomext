package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Marque;
import com.mincom.gescomext.repository.MarqueRepository;

@Service
public class MarqueServiceImpl implements MarqueService {
	
	@Autowired
	MarqueRepository marqueRepository;

	@Override
	public Marque saveMarque(Marque elmt) {
		return marqueRepository.save(elmt);
	}

	@Override
	public Marque updateMarque(Marque elmt) {
		return marqueRepository.save(elmt);
	}

	@Override
	public void deleteMarque(Marque elmt) {
		marqueRepository.delete(elmt);
		
	}

	@Override
	public void deleteMarqueById(Long id) {
		marqueRepository.deleteById(id);
	}

	@Override
	public Marque getMarqueById(Long id) {
		return marqueRepository.getById(id);
	}

	@Override
	public List<Marque> getAllMarque() {
		return marqueRepository.findAll();
	}

	@Override
	public Marque findByNomMarque(String nomMarque) {
		return marqueRepository.findByNomMarque(nomMarque);
	}

}
