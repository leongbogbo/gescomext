package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.Fonction;

public interface FonctionService {
	Fonction saveFonction(Fonction elmt);
	Fonction updateFonction(Fonction elmt);
	void deleteFonction(Fonction elmt);
	void deleteFonctionById(Long id);
	Fonction getFonctionById(Long id);
	List<Fonction> getAllFonction();
	Fonction findByTitreFonc(String titreFonc);
	List<Fonction> findByTitreFoncContaining(String titreFonc);
}
