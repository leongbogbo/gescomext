package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.TypePieceIdentite;
import com.mincom.gescomext.repository.TypePieceIdentiteRepository;

@Service
public class TypePieceIdentiteServiceImpl implements TypePieceIdentiteService {
	
	@Autowired
	TypePieceIdentiteRepository typePieceIdentiteRepository;

	@Override
	public TypePieceIdentite saveTypePieceIdentite(TypePieceIdentite elmt) {
		return typePieceIdentiteRepository.save(elmt);
	}

	@Override
	public TypePieceIdentite updateTypePieceIdentite(TypePieceIdentite elmt) {
		return typePieceIdentiteRepository.save(elmt);
	}

	@Override
	public void deleteTypePieceIdentite(TypePieceIdentite elmt) {
		typePieceIdentiteRepository.delete(elmt);		
	}

	@Override
	public void deleteTypePieceIdentiteById(Long id) {
		typePieceIdentiteRepository.deleteById(id);		
	}

	@Override
	public TypePieceIdentite getTypePieceIdentiteById(Long id) {
		return typePieceIdentiteRepository.findById(id).get();
	}

	@Override
	public List<TypePieceIdentite> getAllTypePieceIdentite() {
		return typePieceIdentiteRepository.findAll();
	}

	@Override
	public TypePieceIdentite findByTitreTyp(String titreTyp) {
		return typePieceIdentiteRepository.findByTitreTyp(titreTyp);
	}

}
