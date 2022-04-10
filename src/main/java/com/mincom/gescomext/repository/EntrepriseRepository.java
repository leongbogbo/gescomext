package com.mincom.gescomext.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mincom.gescomext.entities.DomaineActivite;
import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.FormeJuridique;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
	
	@Query("select e from Entreprise e where e.codeImportExportEntr = :codeImportExportEntr")
	Entreprise findByCodeImportExportEntr (@Param("codeImportExportEntr") String codeImportExportEntr);
	
	@Query("select e from Entreprise e where e.contribuableEntr = :contribuableEntr")
	Entreprise findByContribuableEntr (@Param("contribuableEntr") String contribuableEntr);
	
	@Query("select e from Entreprise e where (e.codeImportExportEntr = :code) or (e.contribuableEntr = :code) or (e.codeIdexEntr = :code)")
	Entreprise findByCodeImportExportEntrAndContribuableEntr (@Param("code") String code);
	
	@Query("select e from Entreprise e where (e.codeImportExportEntr = :code) or (e.nomEntr = :code) or (e.regcommerceEntr = :code) or (e.contribuableEntr = :code)")
	List<Entreprise> findEntrepriseByGeneralInfo (@Param("code") String code);
	
	//STATISTIQUE
	@Query("select e from Entreprise e JOIN e.formeJuridique f where (e.dateEntr BETWEEN :datedebut and :datefin)")
	List<Entreprise> findStatAllEntreprise (@Param("datedebut") Date datedebut,@Param("datefin") Date datefin);
	
	@Query("select e from Entreprise e JOIN e.formeJuridique f where (f.idJury = :idjury)")
	List<Entreprise> findByFormeJuridique (@Param("idjury") Long idjury);
	
	@Query("select e from Entreprise e JOIN e.domaineActivite d where (d.idDomAct = :idomAct)")
	List<Entreprise> findByDomaineActivite (@Param("idomAct") Long idomAct);
	
	@Query("select e from Entreprise e JOIN e.formeJuridique f JOIN e.domaineActivite d where (f.idJury = :idjury) and (d.idDomAct = :idomAct)")
	List<Entreprise> findByFormeJuridiqueAndDomaineActiviteLike (@Param("idjury") Long idjury, @Param("idomAct") Long idomAct);
	
	Entreprise findByNomEntr(String nomEntr);
	List<Entreprise> findBynomEntrContaining(String nomEntr);
	Entreprise findByRegcommerceEntr(String regcommerceEntr);
	Entreprise findByNumIduEntr(String numIduEntr);
	//List<Entreprise> findByFormeJuridiqueAndDomaineActiviteLike(FormeJuridique formeJuridique, DomaineActivite domaineActivite);
}
