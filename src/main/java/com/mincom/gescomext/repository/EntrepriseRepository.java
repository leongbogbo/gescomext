package com.mincom.gescomext.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mincom.gescomext.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
	
	@Query("select e from Entreprise e where e.codeImportExportEntr = :codeImportExportEntr")
	Entreprise findByCodeImportExportEntr (@Param("codeImportExportEntr") String codeImportExportEntr);
	
	@Query("select e from Entreprise e where e.contribuableEntr = :contribuableEntr")
	Entreprise findByContribuableEntr (@Param("contribuableEntr") String contribuableEntr);
	
	@Query("select e from Entreprise e where (e.codeImportExportEntr = :code) or (e.contribuableEntr = :code)")
	Entreprise findByCodeImportExportEntrAndContribuableEntr (@Param("code") String code);
	
	Entreprise findByNomEntr(String nomEntr);

}
