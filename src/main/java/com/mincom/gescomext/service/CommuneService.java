package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.Commune;

public interface CommuneService {
	Commune saveCommune(Commune elmt);
	Commune updateCommune(Commune elmt);
	void deleteCommune(Commune elmt);
	void deleteCommuneById(Long id);
	Commune getCommuneById(Long id);
	List<Commune> getAllCommune();
	Commune findBynomCommune(String nom);

}
