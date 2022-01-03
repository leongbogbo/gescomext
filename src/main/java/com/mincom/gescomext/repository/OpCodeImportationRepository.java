package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mincom.gescomext.entities.OpCodeImportation;

public interface OpCodeImportationRepository extends JpaRepository<OpCodeImportation, Long> {	
	@Query("select e from OpCodeImportation e JOIN e.codeImportation c where (e.typeCodeOp = :code)")
	List<OpCodeImportation> findAllCodeImportationByTypeCodeOp (@Param("code") String code);
	
	@Query("select e from OpCodeImportation e JOIN e.codeImportation c JOIN c.entreprise p where (p.codeImportExportEntr = :code) or (p.contribuableEntr = :code)")
	List<OpCodeImportation> findAllCodeImportationByCodeImportExport (@Param("code") String code);
	
	@Query("select e from OpCodeImportation e JOIN e.codeImportation c JOIN c.entreprise p where (e.typeCodeOp = :typecode and p.codeImportExportEntr = :code) or (e.typeCodeOp = :typecode and p.contribuableEntr = :code)")
	List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCc (@Param("code") String code, @Param("typecode") String typecode);
	
	@Query("select e from OpCodeImportation e JOIN e.codeImportation c JOIN c.demandeur d where (e.typeCodeOp = :typecode and d.emailDem = :code)")
	List<OpCodeImportation> findCodeImportationByTypecodeAndByEmailDem (@Param("code") String code, @Param("typecode") String typecode);
	
	@Query("select e from OpCodeImportation e JOIN e.codeImportation where (e.numDocOp = :code)")
	List<OpCodeImportation> findBynumDocOpPdf (@Param("code") Integer code);
	
	OpCodeImportation findFirstByOrderByIdOpDesc();
	OpCodeImportation findBynumDocOp(Integer numDocOp);
	List<OpCodeImportation> findByTypeCodeOp(String typeCodeOp);
}
