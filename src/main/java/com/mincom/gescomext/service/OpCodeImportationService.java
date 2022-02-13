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
	OpCodeImportation findBynumDocOp(Integer numDocOp, String typecode, String site);
	List<OpCodeImportation> findByTypeCodeOp(String typeCodeOp);
	
	List<OpCodeImportation> findAllCodeImportationByTypeCodeOp (String code, String site);
	List<OpCodeImportation> findAllCodeImportationByCodeImportExport (String code, String site);
	List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCc (String code, String typecode, String site);
	List<OpCodeImportation> findBynumDocOpPdf (Integer code, String site);
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGage (String code, String site);
	List<OpCodeImportation> findAllOpCodeImportationNUMDOC(Integer code, String site);
	List<OpCodeImportation> findOpCodeImportationNOMeNTR(String code, String site);
	List<OpCodeImportation> findOpCodeImportationNUMpIECEdEMANDEUR(String code, String site);
	//POUR LISTE UNIQUEMENT
	List<OpCodeImportation> findOpCodeImportationsByTypeCodeOp (String code);
}

