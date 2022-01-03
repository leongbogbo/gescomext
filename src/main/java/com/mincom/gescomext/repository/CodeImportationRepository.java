package com.mincom.gescomext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincom.gescomext.entities.CodeImportation;
public interface CodeImportationRepository extends JpaRepository<CodeImportation, Long> {
	CodeImportation findFirstByOrderByIdImpDesc();
}
