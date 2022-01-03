package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.Nationalite;

public interface NationaliteService {
	Nationalite saveNationalite(Nationalite elmt);
	Nationalite updateNationalite(Nationalite elmt);
	void deleteNationalite(Nationalite elmt);
	void deleteNationaliteById(Long id);
	Nationalite getNationaliteById(Long id);
	List<Nationalite> getAllNationalite();

}
