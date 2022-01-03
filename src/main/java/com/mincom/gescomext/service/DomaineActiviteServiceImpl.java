package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.DomaineActivite;
import com.mincom.gescomext.repository.DomaineActiviteRepository;

@Service
public class DomaineActiviteServiceImpl implements DomaineActiviteService {
	
	@Autowired
	DomaineActiviteRepository domaineActiviteRepository;

	@Override
	public DomaineActivite saveDomaineActivite(DomaineActivite elmt) {
		return domaineActiviteRepository.save(elmt);
	}

	@Override
	public DomaineActivite updateDomaineActivite(DomaineActivite elmt) {
		return domaineActiviteRepository.save(elmt);
	}

	@Override
	public void deleteDomaineActivite(DomaineActivite elmt) {
		domaineActiviteRepository.delete(elmt);		
	}

	@Override
	public void deleteDomaineActiviteById(Long id) {
		domaineActiviteRepository.deleteById(id);		
	}

	@Override
	public DomaineActivite getDomaineActiviteById(Long id) {
		return domaineActiviteRepository.findById(id).get();
	}

	@Override
	public List<DomaineActivite> getAllDomaineActivite() {
		return domaineActiviteRepository.findAll();
	}

}
