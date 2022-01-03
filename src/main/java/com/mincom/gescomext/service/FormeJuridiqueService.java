package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.FormeJuridique;

public interface FormeJuridiqueService {
	FormeJuridique saveFormeJuridique(FormeJuridique elmt);
	FormeJuridique updateFormeJuridique(FormeJuridique elmt);
	void deleteFormeJuridique(FormeJuridique elmt);
	void deleteFormeJuridiqueById(Long id);
	FormeJuridique getFormeJuridiqueById(Long id);
	List<FormeJuridique> getAllFormeJuridique();

}
