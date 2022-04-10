package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mincom.gescomext.entities.Ville;

public interface VilleService {
	Ville saveVille(Ville elmt);
	Ville updateVille(Ville elmt);
	void deleteVille(Ville elmt);
	void deleteVilleById(Long id);
	Ville getVilleById(Long id);
	List<Ville> getAllVille();
	Page<Ville> getAllVilleByPage(int page, int size);
	Ville findBynomVille(String nom);

}
