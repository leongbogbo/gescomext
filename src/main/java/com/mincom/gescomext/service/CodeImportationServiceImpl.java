package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.CodeImportation;
import com.mincom.gescomext.repository.CodeImportationRepository;

@Service
public class CodeImportationServiceImpl implements CodeImportationService {
	
	@Autowired
	CodeImportationRepository reposy;

	@Override
	public CodeImportation saveCodeImportation(CodeImportation c) {
		return reposy.save(c);
	}

	@Override
	public CodeImportation updateCodeImportation(CodeImportation c) {
		return reposy.save(c);
	}

	@Override
	public void deleteCodeImportation(CodeImportation c) {
		reposy.delete(c);		
	}

	@Override
	public void deleteCodeImportationById(Long id) {
		reposy.deleteById(id);		
	}

	@Override
	public CodeImportation getCodeImportationById(Long id) {
		return reposy.findById(id).get();
	}

	@Override
	public List<CodeImportation> getAllCodeImportation() {
		return reposy.findAll();
	}

	@Override
	public CodeImportation findFirstByOrderByIdImpDesc() {
		return reposy.findFirstByOrderByIdImpDesc();
	}

}
