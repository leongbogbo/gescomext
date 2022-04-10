package com.mincom.gescomext.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	List<OpCodeImportation> findByTypeCodeOp(String typeCodeOp);
	
	OpCodeImportation findBynumDocOp(Integer numDocOp, String typecode, String site);
	List<OpCodeImportation> findAllCodeImportationByTypeCodeOp (String code, String site);
	List<OpCodeImportation> findAllCodeImportationByCodeImportExport (String code, String site);
	List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCc (String code, String typecode, String site);
	List<OpCodeImportation> findBynumDocOpPdf (Integer code, String site);
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGage (String code, String site);
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageEntr (String code, String site);
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageDem (String code, String site);
	List<OpCodeImportation> findAllOpCodeImportationNUMDOC(Integer code, String site);
	List<OpCodeImportation> findOpCodeImportationNOMeNTR(String code, String site);
	List<OpCodeImportation> findOpCodeImportationNUMpIECEdEMANDEUR(String code, String site);
	OpCodeImportation findByInfoEntrOp (String infoEntrOp, String typecode, String site);	
	OpCodeImportation findByInfoDemOp (String infoEntrOp, String typecode, String site);
	//POUR LISTE UNIQUEMENT
	List<OpCodeImportation> findOpCodeImportationsByTypeCodeOp (String code);
	
	
	//Statistique
	List<OpCodeImportation> findStatAllOpCodeImportation (Date datedebut,Date datefin);
	List<OpCodeImportation> findStatOpCodeImportationByRubrique (String typecodeop,Date datedebut, Date datefin);
	List<OpCodeImportation> findStatOpCodeImportationBySite (Long site,Date datedebut, Date datefin);
	List<OpCodeImportation> findStatOpCodeImportationByTypeOp (String typeop, Date datedebut, Date datefin);
	
	//SUPPER ADMIN
	OpCodeImportation findBynumDocOpSuper(Integer numDocOp, String typecode);
	List<OpCodeImportation> findAllCodeImportationByCodeImportExportSuper (String code);
	List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCcSuper (String code, String typecode);
	List<OpCodeImportation> findBynumDocOpPdfSuper (Integer code);
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageSuper (String code);
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageEntrSuper (String code);
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageDemSuper (String code);
	List<OpCodeImportation> findAllOpCodeImportationNUMDOCSuper(Integer code);
	List<OpCodeImportation> findOpCodeImportationNOMeNTRSuper(String code);
	List<OpCodeImportation> findOpCodeImportationNUMpIECEdEMANDEURSuper(String code);
	OpCodeImportation findByInfoEntrOpSuper (String infoEntrOp, String typecode);	
	OpCodeImportation findByInfoDemOpSuper (String infoEntrOp, String typecode);
}

