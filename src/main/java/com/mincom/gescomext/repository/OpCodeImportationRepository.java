package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mincom.gescomext.entities.OpCodeImportation;

public interface OpCodeImportationRepository extends JpaRepository<OpCodeImportation, Long> {	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c where (e.typeCodeOp = :code and s.nomSite = :site) order by e.idOp DESC")
	List<OpCodeImportation> findAllCodeImportationByTypeCodeOp (@Param("code") String code, @Param("site") String site);
	//SANS SITE POUR LISTE UNIQUEMENT
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c where (e.typeCodeOp = :code) order by e.idOp DESC")
	List<OpCodeImportation> findOpCodeImportationsByTypeCodeOp (@Param("code") String code);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c JOIN c.entreprise p where (p.codeImportExportEntr = :code and s.nomSite = :site) or (p.contribuableEntr = :code and s.nomSite = :site)")
	List<OpCodeImportation> findAllCodeImportationByCodeImportExport (@Param("code") String code, @Param("site") String site);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c JOIN c.entreprise p where (e.typeCodeOp = :typecode and p.codeImportExportEntr = :code and s.nomSite = :site) or (e.typeCodeOp = :typecode and p.contribuableEntr = :code and s.nomSite = :site)")
	List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCc (@Param("code") String code, @Param("typecode") String typecode, @Param("site") String site);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c JOIN c.demandeur d where (e.typeCodeOp = :typecode and d.emailDem = :code and s.nomSite = :site)")
	List<OpCodeImportation> findCodeImportationByTypecodeAndByEmailDem (@Param("code") String code, @Param("typecode") String typecode, @Param("site") String site);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation where (e.numDocOp = :code and s.nomSite = :site)")
	List<OpCodeImportation> findBynumDocOpPdf (@Param("code") Integer code, @Param("site") String site);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c where (c.numOcca = :code and s.nomSite = :site) or (c.numGag = :code and s.nomSite = :site)")
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGage (@Param("code") String code, @Param("site") String site);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c where (e.numDocOp = :code and s.nomSite = :site)")
	List<OpCodeImportation> findAllOpCodeImportationNUMDOC(@Param("code") Integer code, @Param("site") String site);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN u.site s JOIN o.codeImportation c JOIN c.entreprise e where (e.nomEntr = :code and s.nomSite = :site)")
	List<OpCodeImportation> findOpCodeImportationNOMeNTR(@Param("code") String code, @Param("site") String site);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN u.site s JOIN o.codeImportation c JOIN c.demandeur d where (d.numpieceDem = :code and s.nomSite = :site)")
	List<OpCodeImportation> findOpCodeImportationNUMpIECEdEMANDEUR(@Param("code") String code, @Param("site") String site);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c where (e.numDocOp = :numDocOp and e.typeCodeOp = :typecode and s.nomSite = :site)")
	OpCodeImportation findBynumDocOp (@Param("numDocOp") Integer numDocOp, @Param("typecode") String typecode, @Param("site") String site);
	
	OpCodeImportation findFirstByOrderByIdOpDesc();
	//OpCodeImportation findBynumDocOp(Integer numDocOp);
	List<OpCodeImportation> findByTypeCodeOp(String typeCodeOp);
	
}
