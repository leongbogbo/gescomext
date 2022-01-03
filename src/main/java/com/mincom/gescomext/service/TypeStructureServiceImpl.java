package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.TypeStructure;
import com.mincom.gescomext.repository.TypeStructureRepository;
import com.mincom.gescomext.repository.VilleRepository;

@Service
public class TypeStructureServiceImpl implements TypeStructureService {
	
	@Autowired
	TypeStructureRepository typeStructureRepository;

	@Override
	public TypeStructure saveTypeStructure(TypeStructure elmt) {
		return typeStructureRepository.save(elmt);
	}

	@Override
	public TypeStructure updateTypeStructure(TypeStructure elmt) {
		return typeStructureRepository.save(elmt);
	}

	@Override
	public void deleteTypeStructure(TypeStructure elmt) {
		typeStructureRepository.delete(elmt);		
	}

	@Override
	public void deleteTypeStructureById(Long id) {
		typeStructureRepository.deleteById(id);		
	}

	@Override
	public TypeStructure getTypeStructureById(Long id) {
		return typeStructureRepository.findById(id).get();
	}

	@Override
	public List<TypeStructure> getAllTypeStructure() {
		return typeStructureRepository.findAll();
	}

}
