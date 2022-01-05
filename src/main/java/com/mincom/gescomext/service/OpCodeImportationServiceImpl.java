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
	public List<OpCodeImportation> findAllCodeImportationByTypeCodeOp(String code) {
		return opCodeImportationRepository.findAllCodeImportationByTypeCodeOp(code);
	}

	@Override
	public OpCodeImportation findBynumDocOp(Integer numDocOp) {
		return opCodeImportationRepository.findBynumDocOp(numDocOp);
	}

	@Override
	public List<OpCodeImportation> findByTypeCodeOp(String typeCodeOp) {
		return opCodeImportationRepository.findByTypeCodeOp(typeCodeOp);
	}

	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeImportExport(String code) {
		return opCodeImportationRepository.findAllCodeImportationByCodeImportExport(code);
	}

	@Override
	public List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCc(String code, String typecode) {
		return opCodeImportationRepository.findCodeImportationByTypecodeAndByCodeRccmOrCc(code, typecode);
	}

	@Override
	public List<OpCodeImportation> findBynumDocOpPdf(Integer code) {
		return opCodeImportationRepository.findBynumDocOpPdf(code);
	}

	@Override
	public List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGage(String code) {
		return opCodeImportationRepository.findAllCodeImportationByCodeOccaOrCodeLeveeGage(code);
	}


	

}
