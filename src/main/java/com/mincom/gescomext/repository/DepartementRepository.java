package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincom.gescomext.entities.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
	Departement findByTitreDep(String titreDep);
	List<Departement> findBytitreDepContaining(String titreDep);
}
