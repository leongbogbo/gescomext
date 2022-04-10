package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.Departement;

public interface DepartementService {
	Departement saveDepartement(Departement elmt);
	Departement updateDepartement(Departement elmt);
	void deleteDepartement(Departement elmt);
	void deleteDepartementById(Long id);
	Departement getDepartementById(Long id);
	List<Departement> getAllDepartement();
	Departement findByTitreDep(String titreDep);
	List<Departement> findBytitreDepContaining(String titreDep);
}
