package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincom.gescomext.entities.Fonction;

public interface FonctionRepository extends JpaRepository<Fonction, Long> {
	Fonction findByTitreFonc(String titreFonc);
	List<Fonction> findByTitreFoncContaining(String titreFonc);
}
