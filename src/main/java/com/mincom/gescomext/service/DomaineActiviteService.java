package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.DomaineActivite;

public interface DomaineActiviteService {
	DomaineActivite saveDomaineActivite(DomaineActivite elmt);
	DomaineActivite updateDomaineActivite(DomaineActivite elmt);
	void deleteDomaineActivite(DomaineActivite elmt);
	void deleteDomaineActiviteById(Long id);
	DomaineActivite getDomaineActiviteById(Long id);
	List<DomaineActivite> getAllDomaineActivite();

}
