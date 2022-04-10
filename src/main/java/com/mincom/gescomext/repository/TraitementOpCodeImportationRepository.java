package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mincom.gescomext.entities.TraitementOpCodeImportation;

public interface TraitementOpCodeImportationRepository extends JpaRepository<TraitementOpCodeImportation, Long> {
	@Query(nativeQuery = true, value="SELECT * FROM traitement_op_code_importation t LEFT JOIN op_code_importation o ON t.opcodeimport_id = o.id_op LEFT JOIN user u ON u.user_id = o.user_id LEFT JOIN site s ON s.id_site = u.site_id_site WHERE (o.type_code_op = :code and s.nom_site = :site) ORDER BY id_trait LIMIT 500")
	List<TraitementOpCodeImportation> findAllTraitementOpCodeImportationByTypeCodeOp (@Param("code") String code, @Param("site") String site);
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o JOIN o.user u JOIN u.site s where (t.statutTrait = :statut and o.numDocOp = :numDocOp and s.nomSite = :site)")
	TraitementOpCodeImportation findTraitementOpCodeImportationByStatut (@Param("numDocOp") Integer numDocOp, @Param("statut") String statut, @Param("site") String site);
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o JOIN o.user u JOIN u.site s where (t.statutTrait = :statut and o.idOp = :idOp and s.nomSite = :site)")
	TraitementOpCodeImportation findTraitementOpCodeImportationByIDoP (@Param("idOp") Long idOp, @Param("statut") String statut, @Param("site") String site);
	
	//SUPPER ADMIN
	@Query(nativeQuery = true, value="SELECT * FROM traitement_op_code_importation t LEFT JOIN op_code_importation o ON t.opcodeimport_id = o.id_op LEFT JOIN user u ON u.user_id = o.user_id WHERE (o.type_code_op = :code) ORDER BY id_trait LIMIT 500")
	List<TraitementOpCodeImportation> findAllTraitementOpCodeImportationByTypeCodeOpSuper (@Param("code") String code);
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o JOIN o.user u where (t.statutTrait = :statut and o.numDocOp = :numDocOp)")
	TraitementOpCodeImportation findTraitementOpCodeImportationByStatutSuper (@Param("numDocOp") Integer numDocOp, @Param("statut") String statut);
	@Query("select t from TraitementOpCodeImportation t JOIN t.opCodeImportation o JOIN o.user u where (t.statutTrait = :statut and o.idOp = :idOp)")
	TraitementOpCodeImportation findTraitementOpCodeImportationByIDoPSuper (@Param("idOp") Long idOp, @Param("statut") String statut);
}
