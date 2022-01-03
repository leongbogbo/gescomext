package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.TypeStructure;

public interface TypeStructureService {
	TypeStructure saveTypeStructure(TypeStructure elmt);
	TypeStructure updateTypeStructure(TypeStructure elmt);
	void deleteTypeStructure(TypeStructure elmt);
	void deleteTypeStructureById(Long id);
	TypeStructure getTypeStructureById(Long id);
	List<TypeStructure> getAllTypeStructure();

}
