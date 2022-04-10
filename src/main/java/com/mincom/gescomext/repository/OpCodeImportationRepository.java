package com.mincom.gescomext.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mincom.gescomext.entities.OpCodeImportation;

public interface OpCodeImportationRepository extends JpaRepository<OpCodeImportation, Long> {	
	@Query(nativeQuery = true, value="SELECT * FROM op_code_importation e LEFT JOIN user u ON u.user_id = e.user_id LEFT JOIN code_importation c ON c.id_imp = e.codeimport_id LEFT JOIN site s ON s.id_site = u.site_id_site WHERE (e.type_code_op = :code AND s.nom_site = :site) ORDER BY e.id_op DESC LIMIT 500")
	List<OpCodeImportation> findAllCodeImportationByTypeCodeOp (@Param("code") String code, @Param("site") String site);
	//SANS SITE POUR LISTE UNIQUEMENT
	@Query(nativeQuery = true, value="SELECT * FROM op_code_importation e LEFT JOIN user u ON u.user_id = e.user_id LEFT JOIN code_importation c ON c.id_imp = e.codeimport_id WHERE (e.type_code_op = :code) ORDER BY e.id_op DESC LIMIT 500")
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
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN u.site s JOIN o.codeImportation c JOIN c.entreprise e where (c.numOcca = :code and s.nomSite = :site) or (c.numGag = :code and s.nomSite = :site) or (e.codeIdexEntr = :code and s.nomSite = :site)")
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageEntr (@Param("code") String code, @Param("site") String site);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN u.site s JOIN o.codeImportation c JOIN c.demandeur d where (c.numOcca = :code and s.nomSite = :site) or (c.numGag = :code and s.nomSite = :site) or (d.codeIdexDem = :code and s.nomSite = :site)")
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageDem (@Param("code") String code, @Param("site") String site);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c where (e.numDocOp = :code and s.nomSite = :site)")
	List<OpCodeImportation> findAllOpCodeImportationNUMDOC(@Param("code") Integer code, @Param("site") String site);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN u.site s JOIN o.codeImportation c JOIN c.entreprise e where (e.nomEntr = :code and s.nomSite = :site)")
	List<OpCodeImportation> findOpCodeImportationNOMeNTR(@Param("code") String code, @Param("site") String site);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN u.site s JOIN o.codeImportation c JOIN c.demandeur d where (d.numpieceDem = :code and s.nomSite = :site)")
	List<OpCodeImportation> findOpCodeImportationNUMpIECEdEMANDEUR(@Param("code") String code, @Param("site") String site);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c where (e.numDocOp = :numDocOp and e.typeCodeOp = :typecode and s.nomSite = :site)")
	OpCodeImportation findBynumDocOp (@Param("numDocOp") Integer numDocOp, @Param("typecode") String typecode, @Param("site") String site);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN u.site s JOIN o.codeImportation c JOIN c.entreprise e where ((e.nomEntr = :infoEntrOp or e.contribuableEntr = :infoEntrOp or e.codeImportExportEntr = :infoEntrOp or e.numIduEntr = :infoEntrOp or e.codeIdexEntr = :infoEntrOp) and o.typeCodeOp = :typecode and s.nomSite = :site)")
	OpCodeImportation findByInfoEntrOp (@Param("infoEntrOp") String infoEntrOp, @Param("typecode") String typecode, @Param("site") String site);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN u.site s JOIN o.codeImportation c JOIN c.demandeur d where ((d.numpieceDem = :infoEntrOp or d.codeIdexDem = :infoEntrOp) and o.typeCodeOp = :typecode and s.nomSite = :site)")
	OpCodeImportation findByInfoDemOp (@Param("infoEntrOp") String infoEntrOp, @Param("typecode") String typecode, @Param("site") String site);
	
	OpCodeImportation findFirstByOrderByIdOpDesc();
	//OpCodeImportation findBynumDocOp(Integer numDocOp);
	List<OpCodeImportation> findByTypeCodeOp(String typeCodeOp);
	
	//STATISTIQUE
	@Query("select e from OpCodeImportation e JOIN e.codeImportation c where (e.dateOp BETWEEN :datedebut and :datefin)")
	List<OpCodeImportation> findStatAllOpCodeImportation (@Param("datedebut") Date datedebut,@Param("datefin") Date datefin);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN u.site s JOIN e.codeImportation c where (e.dateOp BETWEEN :datedebut and :datefin  and s.idSite = :site)")
	List<OpCodeImportation> findStatOpCodeImportationBySite ( @Param("site") Long site,@Param("datedebut") Date datedebut,@Param("datefin") Date datefin);
	
	@Query("select e from OpCodeImportation e JOIN e.codeImportation c where (e.dateOp BETWEEN :datedebut and :datefin  and e.typeOp = :typeop)")
	List<OpCodeImportation> findStatOpCodeImportationByTypeOp (@Param("typeop") String typeop, @Param("datedebut") Date datedebut,@Param("datefin") Date datefin);
	
	@Query("select e from OpCodeImportation e where (e.dateOp BETWEEN :datedebut and :datefin and e.typeCodeOp = :typecodeop)")
	List<OpCodeImportation> findStatOpCodeImportationByRubrique (@Param("typecodeop") String typecodeop,@Param("datedebut") Date datedebut,@Param("datefin") Date datefin);
	
	
	//SUPER ADMIN
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN e.codeImportation c JOIN c.entreprise p where (p.codeImportExportEntr = :code) or (p.contribuableEntr = :code)")
	List<OpCodeImportation> findAllCodeImportationByCodeImportExportSuper (@Param("code") String code);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN e.codeImportation c JOIN c.entreprise p where (e.typeCodeOp = :typecode and p.codeImportExportEntr = :code ) or (e.typeCodeOp = :typecode and p.contribuableEntr = :code )")
	List<OpCodeImportation> findCodeImportationByTypecodeAndByCodeRccmOrCcSuper (@Param("code") String code, @Param("typecode") String typecode);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN e.codeImportation c JOIN c.demandeur d where (e.typeCodeOp = :typecode and d.emailDem = :code )")
	List<OpCodeImportation> findCodeImportationByTypecodeAndByEmailDemSuper (@Param("code") String code, @Param("typecode") String typecode);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN e.codeImportation where (e.numDocOp = :code )")
	List<OpCodeImportation> findBynumDocOpPdfSuper (@Param("code") Integer code);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN e.codeImportation c where (c.numOcca = :code ) or (c.numGag = :code )")
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageSuper (@Param("code") String code);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN o.codeImportation c JOIN c.entreprise e where (c.numOcca = :code ) or (c.numGag = :code ) or (e.codeIdexEntr = :code )")
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageEntrSuper (@Param("code") String code);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN o.codeImportation c JOIN c.demandeur d where (c.numOcca = :code ) or (c.numGag = :code ) or (d.codeIdexDem = :code )")
	List<OpCodeImportation> findAllCodeImportationByCodeOccaOrCodeLeveeGageDemSuper (@Param("code") String code);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN e.codeImportation c where (e.numDocOp = :code)")
	List<OpCodeImportation> findAllOpCodeImportationNUMDOCSuper (@Param("code") Integer code);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN o.codeImportation c JOIN c.entreprise e where (e.nomEntr = :code)")
	List<OpCodeImportation> findOpCodeImportationNOMeNTRSuper (@Param("code") String code);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN o.codeImportation c JOIN c.demandeur d where (d.numpieceDem = :code)")
	List<OpCodeImportation> findOpCodeImportationNUMpIECEdEMANDEURSuper (@Param("code") String code);
	
	@Query("select e from OpCodeImportation e JOIN e.user u JOIN e.codeImportation c where (e.numDocOp = :numDocOp and e.typeCodeOp = :typecode)")
	OpCodeImportation findBynumDocOpSuper (@Param("numDocOp") Integer numDocOp, @Param("typecode") String typecode);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN o.codeImportation c JOIN c.entreprise e where ((e.nomEntr = :infoEntrOp or e.contribuableEntr = :infoEntrOp or e.codeImportExportEntr = :infoEntrOp or e.numIduEntr = :infoEntrOp or e.codeIdexEntr = :infoEntrOp) and o.typeCodeOp = :typecode)")
	OpCodeImportation findByInfoEntrOpSuper (@Param("infoEntrOp") String infoEntrOp, @Param("typecode") String typecode);
	
	@Query("select o from OpCodeImportation o JOIN o.user u JOIN o.codeImportation c JOIN c.demandeur d where ((d.numpieceDem = :infoEntrOp or d.codeIdexDem = :infoEntrOp) and o.typeCodeOp = :typecode )")
	OpCodeImportation findByInfoDemOpSuper (@Param("infoEntrOp") String infoEntrOp, @Param("typecode") String typecode);
	
	
}
