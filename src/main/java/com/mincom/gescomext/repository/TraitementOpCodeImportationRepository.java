package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mincom.gescomext.entities.TraitementOpCodeImportation;

public interface TraitementOpCodeImportationRepository extends JpaRepository<TraitementOpCodeImportation, Long> {
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o JOIN o.user u JOIN u.site s where (o.typeCodeOp = :code and s.nomSite = :site)")
	List<TraitementOpCodeImportation> findAllTraitementOpCodeImportationByTypeCodeOp (@Param("code") String code, @Param("site") String site);
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o JOIN o.user u JOIN u.site s where (t.statutTrait = :statut and o.numDocOp = :numDocOp and s.nomSite = :site)")
	TraitementOpCodeImportation findTraitementOpCodeImportationByStatut (@Param("numDocOp") Integer numDocOp, @Param("statut") String statut, @Param("site") String site);
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o JOIN o.user u JOIN u.site s where (t.statutTrait = :statut and o.idOp = :idOp and s.nomSite = :site)")
	TraitementOpCodeImportation findTraitementOpCodeImportationByIDoP (@Param("idOp") Long idOp, @Param("statut") String statut, @Param("site") String site);
}
