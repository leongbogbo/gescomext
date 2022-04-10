package com.mincom.gescomext.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.OpCodeImportation;
import com.mincom.gescomext.repository.OpCodeImportationRepository;

@Service
public class OpCodeImportationServiceImpl implements OpCodeImportationService {
	
	@Autowired
	OpCodeImportationRepository opCodeImportationRepository;

	@Override
	public OpCodeImportation saveOpCodeImportation(OpCodeImportation c) {
		return opCodeImportationRepository.save(c);
	}

	@Override
	public OpCodeImportation updateOpCodeImportation(OpCodeImportation c) {
		return opCodeImportationRepository.save(c);
	}

	@Override
	public void deleteOpCodeImportation(OpCodeImportation c) {
		opCodeImportationRepository.delete(c);		
	}

	@Override
	public void deleteOpCodeImportationById(Long id) {
		opCodeImportationRepository.deleteById(id);		
	}

	@Override
	public OpCodeImportation getOpCodeImportationById(Long id) {
		return opCodeImportationRepository.findById(id).get();
	}

	@Override
	public List<OpCodeImportation> getAllOpCodeImportation() {
		return opCodeImportationRepository.findAll();
	}

	@Override
	public OpCodeImportation findFirstByOrderByIdOpDesc() {
		return opCodeImportationRepository.findFirstByOrderByIdOpDesc();
	}
	
	@Override
	public List<OpCodeImportation> findByTypeCodeOp(String typeCodeOp) {
		return opCodeImportationRepository.findByTypeCodeOp(typeCodeOp);
	}
	

	@Override
	public List<OpCodeImportation> findOpCodeImportationsByTypeCodeOp(String code) {
		return opCodeImportationRepository.findOpCodeImportationsByTypeCodeOp(code);
	}

	@Override
	public List<OpCodeImportation> findAllCodeImportationByTypeCodeOp(String code, String site) {
		return opCodeImportationRepository.findAllCodeImportationByTypeCodeOp(code, site);
	}

	@Override
	public OpCodeImportation findBynumDocOp(Integer numDocOp, String typecode, String site) {
		return opCodeImportationRepository.findBynumDocOp(numDocOp,typecode,site);
	}


	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeImportExport(String code, String site) {
		return opCodeImportationRepository.findAllCodeImportationByCodeImportExport(code, site);
	}

	@Override
	public List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCc(String code, String typecode, String site) {
		return opCodeImportationRepository.findCodeImportationByTypecodeAndByCodeRccmOrCc(code, typecode, site);
	}

	@Override
	public List<OpCodeImportation> findBynumDocOpPdf(Integer code, String site) {
		return opCodeImportationRepository.findBynumDocOpPdf(code, site);
	}

	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGage(String code, String site) {
		return opCodeImportationRepository.findAllCodeImportationByCodeOccaOrCodeLeveeGage(code, site);
	}

	@Override
	public List<OpCodeImportation> findAllOpCodeImportationNUMDOC(Integer code, String site) {
		// TODO Auto-generated method stub
		return opCodeImportationRepository.findAllOpCodeImportationNUMDOC(code, site);
	}

	@Override
	public List<OpCodeImportation> findOpCodeImportationNOMeNTR(String code, String site) {
		return opCodeImportationRepository.findOpCodeImportationNOMeNTR(code, site);
	}

	@Override
	public List<OpCodeImportation> findOpCodeImportationNUMpIECEdEMANDEUR(String code, String site) {
		return opCodeImportationRepository.findOpCodeImportationNUMpIECEdEMANDEUR(code, site);
	}

	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageEntr(String code, String site) {
		return opCodeImportationRepository.findAllCodeImportationByCodeOccaOrCodeLeveeGageEntr(code, site);
	}

	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageDem(String code, String site) {
		return opCodeImportationRepository.findAllCodeImportationByCodeOccaOrCodeLeveeGageDem(code, site);
	}
	
	@Override
	public OpCodeImportation findByInfoEntrOp(String infoEntrOp, String typecode, String site) {
		return opCodeImportationRepository.findByInfoEntrOp(infoEntrOp, typecode, site);
	}

	@Override
	public OpCodeImportation findByInfoDemOp(String infoEntrOp, String typecode, String site) {
		return opCodeImportationRepository.findByInfoDemOp(infoEntrOp, typecode, site);
	}
	
//STATISTIQUE
	@Override
	public List<OpCodeImportation> findStatAllOpCodeImportation(Date datedebut, Date datefin) {
		return opCodeImportationRepository.findStatAllOpCodeImportation(datedebut,datefin);
	}

	@Override
	public List<OpCodeImportation> findStatOpCodeImportationByRubrique(String typecodeop,Date datedebut, Date datefin) {
		return opCodeImportationRepository.findStatOpCodeImportationByRubrique(typecodeop,datedebut, datefin);
	}

	@Override
	public List<OpCodeImportation> findStatOpCodeImportationBySite(Long site, Date datedebut, Date datefin) {
		return opCodeImportationRepository.findStatOpCodeImportationBySite(site, datedebut, datefin);
	}

	@Override
	public List<OpCodeImportation> findStatOpCodeImportationByTypeOp(String typeop, Date datedebut, Date datefin) {
		return opCodeImportationRepository.findStatOpCodeImportationByTypeOp(typeop, datedebut, datefin);
	}
	
//SUPPER ADMIN
	@Override
	public OpCodeImportation findBynumDocOpSuper(Integer numDocOp, String typecode) {
		return opCodeImportationRepository.findBynumDocOpSuper(numDocOp,typecode);
	}


	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeImportExportSuper(String code) {
		return opCodeImportationRepository.findAllCodeImportationByCodeImportExportSuper(code);
	}

	@Override
	public List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCcSuper(String code, String typecode) {
		return opCodeImportationRepository.findCodeImportationByTypecodeAndByCodeRccmOrCcSuper(code, typecode);
	}

	@Override
	public List<OpCodeImportation> findBynumDocOpPdfSuper(Integer code) {
		return opCodeImportationRepository.findBynumDocOpPdfSuper(code);
	}

	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageSuper(String code) {
		return opCodeImportationRepository.findAllCodeImportationByCodeOccaOrCodeLeveeGageSuper(code);
	}

	@Override
	public List<OpCodeImportation> findAllOpCodeImportationNUMDOCSuper(Integer code) {
		// TODO Auto-generated method stub
		return opCodeImportationRepository.findAllOpCodeImportationNUMDOCSuper(code);
	}

	@Override
	public List<OpCodeImportation> findOpCodeImportationNOMeNTRSuper(String code) {
		return opCodeImportationRepository.findOpCodeImportationNOMeNTRSuper(code);
	}

	@Override
	public List<OpCodeImportation> findOpCodeImportationNUMpIECEdEMANDEURSuper(String code) {
		return opCodeImportationRepository.findOpCodeImportationNUMpIECEdEMANDEURSuper(code);
	}

	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageEntrSuper(String code) {
		return opCodeImportationRepository.findAllCodeImportationByCodeOccaOrCodeLeveeGageEntrSuper(code);
	}

	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageDemSuper(String code) {
		return opCodeImportationRepository.findAllCodeImportationByCodeOccaOrCodeLeveeGageDemSuper(code);
	}
	
	@Override
	public OpCodeImportation findByInfoEntrOpSuper(String infoEntrOp, String typecode) {
		return opCodeImportationRepository.findByInfoEntrOpSuper(infoEntrOp, typecode);
	}

	@Override
	public OpCodeImportation findByInfoDemOpSuper(String infoEntrOp, String typecode) {
		return opCodeImportationRepository.findByInfoDemOpSuper(infoEntrOp, typecode);
	}
	


	

}
