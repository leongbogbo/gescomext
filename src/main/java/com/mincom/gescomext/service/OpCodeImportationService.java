package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.mincom.gescomext.entities.OpCodeImportation;

public interface OpCodeImportationService {
	OpCodeImportation saveOpCodeImportation(OpCodeImportation elmt);
	OpCodeImportation updateOpCodeImportation(OpCodeImportation elmt);
	void deleteOpCodeImportation(OpCodeImportation elmt);
	void deleteOpCodeImportationById(Long id);
	OpCodeImportation getOpCodeImportationById(Long id);
	List<OpCodeImportation> getAllOpCodeImportation();
	OpCodeImportation findFirstByOrderByIdOpDesc();
	List<OpCodeImportation> findAllCodeImportationByTypeCodeOp (String code);
	OpCodeImportation findBynumDocOp(Integer numDocOp);
	List<OpCodeImportation> findByTypeCodeOp(String typeCodeOp);
	List<OpCodeImportation> findAllCodeImportationByCodeImportExport (String code);
	List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCc (String code, String typecode);
	List<OpCodeImportation> findBynumDocOpPdf (Integer code);
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGage (String code);
}

