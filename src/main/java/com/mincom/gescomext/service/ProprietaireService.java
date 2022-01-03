package com.mincom.gescomext.service;

import java.util.List;
import com.mincom.gescomext.entities.Proprietaire;

public interface ProprietaireService {
	Proprietaire saveProprietaire(Proprietaire elmt);
	Proprietaire updateProprietaire(Proprietaire elmt);
	void deleteProprietaire(Proprietaire elmt);
	void deleteProprietaireById(Long id);
	Proprietaire getProprietaireById(Long id);
	List<Proprietaire> getAllProprietaire();
}
