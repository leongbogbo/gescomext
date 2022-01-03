package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.Marque;

public interface MarqueService {
	Marque saveMarque(Marque elmt);
	Marque updateMarque(Marque elmt);
	void deleteMarque(Marque elmt);
	void deleteMarqueById(Long id);
	Marque getMarqueById(Long id);
	List<Marque> getAllMarque();
}
