package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mincom.gescomext.entities.TraitementOpCodeImportation;

public interface TraitementOpCodeImportationRepository extends JpaRepository<TraitementOpCodeImportation, Long> {
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o  where (o.typeCodeOp = :code)")
	List<TraitementOpCodeImportation> findAllTraitementOpCodeImportationByTypeCodeOp (@Param("code") String code);
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o  where (t.statutTrait = :statut and o.numDocOp = :numDocOp)")
	TraitementOpCodeImportation findTraitementOpCodeImportationByStatut (@Param("numDocOp") Integer numDocOp, @Param("statut") String statut);
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o  where (t.statutTrait = :statut and o.idOp = :idOp)")
	TraitementOpCodeImportation findTraitementOpCodeImportationByIDoP (@Param("idOp") Long idOp, @Param("statut") String statut);
}
