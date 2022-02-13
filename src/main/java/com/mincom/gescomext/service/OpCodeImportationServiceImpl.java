package com.mincom.gescomext.service;

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
	public List<OpCodeImportation> findAllCodeImportationByTypeCodeOp(String code, String site) {
		return opCodeImportationRepository.findAllCodeImportationByTypeCodeOp(code, site);
	}

	@Override
	public OpCodeImportation findBynumDocOp(Integer numDocOp, String typecode, String site) {
		return opCodeImportationRepository.findBynumDocOp(numDocOp,typecode,site);
	}

	@Override
	public List<OpCodeImportation> findByTypeCodeOp(String typeCodeOp) {
		return opCodeImportationRepository.findByTypeCodeOp(typeCodeOp);
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
	public List<OpCodeImportation> findOpCodeImportationsByTypeCodeOp(String code) {
		return opCodeImportationRepository.findOpCodeImportationsByTypeCodeOp(code);
	}


	

}
