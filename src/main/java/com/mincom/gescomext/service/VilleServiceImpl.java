package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.VilleRepository;

@Service
public class VilleServiceImpl implements VilleService {
	
	@Autowired
	VilleRepository villeRepository;
	@Autowired
	VilleService villeService;

	@Override
	public Ville saveVille(Ville elmt) {
		return villeRepository.save(elmt);
	}

	@Override
	public Ville updateVille(Ville elmt) {
		return villeRepository.save(elmt);
	}

	@Override
	public void deleteVille(Ville elmt) {
		villeRepository.delete(elmt);		
	}

	@Override
	public void deleteVilleById(Long id) {
		villeRepository.deleteById(id);		
	}

	@Override
	public Ville getVilleById(Long id) {
		return villeRepository.findById(id).get();
	}

	@Override
	public List<Ville> getAllVille() {
		return villeRepository.findAll();
	}

	@Override
	public Page<Ville> getAllVilleByPage(int page, int size) {
		return villeRepository.findAll(PageRequest.of(page, size));
	}

}
