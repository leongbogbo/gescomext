package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Nationalite;
import com.mincom.gescomext.repository.NationaliteRepository;

@Service
public class NationaliteServiceImpl implements NationaliteService {
	
	@Autowired
	NationaliteRepository nationaliteRepository;

	@Override
	public Nationalite saveNationalite(Nationalite elmt) {
		return nationaliteRepository.save(elmt);
	}

	@Override
	public Nationalite updateNationalite(Nationalite elmt) {
		return nationaliteRepository.save(elmt);
	}

	@Override
	public void deleteNationalite(Nationalite elmt) {
		nationaliteRepository.delete(elmt);		
	}

	@Override
	public void deleteNationaliteById(Long id) {
		nationaliteRepository.deleteById(id);		
	}

	@Override
	public Nationalite getNationaliteById(Long id) {
		return nationaliteRepository.findById(id).get();
	}

	@Override
	public List<Nationalite> getAllNationalite() {
		return nationaliteRepository.findAll();
	}

}
