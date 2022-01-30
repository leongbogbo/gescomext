package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mincom.gescomext.entities.Demandeur;

public interface DemandeurRepository extends JpaRepository<Demandeur, Long> {
	@Query("select e from Demandeur e where (e.numpieceDem = :code) or (e.emailDem = :code) or (e.telDem = :code)")
	List<Demandeur> findDemandeurByGeneralInfo (@Param("code") String code);
}
