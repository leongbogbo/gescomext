package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.repository.CommuneRepository;

@Service
public class CommuneServiceImpl implements CommuneService {
	
	@Autowired
	CommuneRepository communeRepository;

	@Override
	public Commune saveCommune(Commune c) {
		return communeRepository.save(c);
	}

	@Override
	public Commune updateCommune(Commune c) {
		return communeRepository.save(c);
	}

	@Override
	public void deleteCommune(Commune c) {
		communeRepository.delete(c);		
	}

	@Override
	public void deleteCommuneById(Long id) {
		communeRepository.deleteById(id);		
	}

	@Override
	public Commune getCommuneById(Long id) {
		return communeRepository.findById(id).get();
	}

	@Override
	public List<Commune> getAllCommune() {
		return communeRepository.findAll();
	}

	@Override
	public Commune findBynomCommune(String nom) {
		return communeRepository.findBynomCommune(nom);
	}

}
