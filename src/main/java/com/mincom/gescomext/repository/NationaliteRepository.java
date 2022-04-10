package com.mincom.gescomext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincom.gescomext.entities.Nationalite;

public interface NationaliteRepository extends JpaRepository<Nationalite, Long> {
	Nationalite findByTitreNat(String titreNat);
}
