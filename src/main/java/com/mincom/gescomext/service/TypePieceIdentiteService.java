package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.TypePieceIdentite;

public interface TypePieceIdentiteService {
	TypePieceIdentite saveTypePieceIdentite(TypePieceIdentite elmt);
	TypePieceIdentite updateTypePieceIdentite(TypePieceIdentite elmt);
	void deleteTypePieceIdentite(TypePieceIdentite elmt);
	void deleteTypePieceIdentiteById(Long id);
	TypePieceIdentite getTypePieceIdentiteById(Long id);
	List<TypePieceIdentite> getAllTypePieceIdentite();
	TypePieceIdentite findByTitreTyp(String titreTyp);
}
