package com.mincom.gescomext.service;

import java.util.List;

import com.mincom.gescomext.entities.GenreMarque;

public interface GenreMarqueService {
	GenreMarque saveGenreMarque(GenreMarque elmt);
	GenreMarque updateGenreMarque(GenreMarque elmt);
	void deleteGenreMarque(GenreMarque elmt);
	void deleteGenreMarqueById(Long id);
	GenreMarque getGenreMarqueById(Long id);
	List<GenreMarque> getAllGenreMarque();
}
