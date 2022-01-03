package com.mincom.gescomext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mincom.gescomext.entities.GenreMarque;
import com.mincom.gescomext.repository.GenreMarqueRepository;

@Service
public class GenreMarqueServiceImpl implements GenreMarqueService {
	
	@Autowired
	GenreMarqueRepository genreMarqueRepository;

	@Override
	public GenreMarque saveGenreMarque(GenreMarque elmt) {
		return genreMarqueRepository.save(elmt);
	}

	@Override
	public GenreMarque updateGenreMarque(GenreMarque elmt) {
		return genreMarqueRepository.save(elmt);
	}

	@Override
	public void deleteGenreMarque(GenreMarque elmt) {
		genreMarqueRepository.delete(elmt);
		
	}

	@Override
	public void deleteGenreMarqueById(Long id) {
		genreMarqueRepository.deleteById(id);
	}

	@Override
	public GenreMarque getGenreMarqueById(Long id) {
		return genreMarqueRepository.getById(id);
	}

	@Override
	public List<GenreMarque> getAllGenreMarque() {
		return genreMarqueRepository.findAll();
	}

}
