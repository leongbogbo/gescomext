package com.mincom.gescomext.service;

import java.util.List;
import com.mincom.gescomext.entities.CodeImportation;
public interface CodeImportationService {
	CodeImportation saveCodeImportation(CodeImportation elmt);
	CodeImportation updateCodeImportation(CodeImportation elmt);
	void deleteCodeImportation(CodeImportation elmt);
	void deleteCodeImportationById(Long id);
	CodeImportation getCodeImportationById(Long id);
	List<CodeImportation> getAllCodeImportation();
	CodeImportation findFirstByOrderByIdImpDesc();
	

}
