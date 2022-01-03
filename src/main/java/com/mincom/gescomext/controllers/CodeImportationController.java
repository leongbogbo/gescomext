package com.mincom.gescomext.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mincom.gescomext.config.CalculeCodesExportation;
import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.TableauCorrespondance;
import com.mincom.gescomext.entities.CodeImportation;
import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.entities.Demandeur;
import com.mincom.gescomext.entities.Departement;
import com.mincom.gescomext.entities.DomaineActivite;
import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.Fonction;
import com.mincom.gescomext.entities.FormeJuridique;
import com.mincom.gescomext.entities.GenreMarque;
import com.mincom.gescomext.entities.Marque;
import com.mincom.gescomext.entities.Nationalite;
import com.mincom.gescomext.entities.OpCodeImportation;
import com.mincom.gescomext.entities.Proprietaire;
import com.mincom.gescomext.entities.TraitementOpCodeImportation;
import com.mincom.gescomext.entities.TypePieceIdentite;
import com.mincom.gescomext.entities.TypeStructure;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.EntrepriseRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.CodeImportationService;
import com.mincom.gescomext.service.CommuneService;
import com.mincom.gescomext.service.DemandeurService;
import com.mincom.gescomext.service.DepartementService;
import com.mincom.gescomext.service.DomaineActiviteService;
import com.mincom.gescomext.service.EntrepriseService;
import com.mincom.gescomext.service.FonctionService;
import com.mincom.gescomext.service.FormeJuridiqueService;
import com.mincom.gescomext.service.GenreMarqueService;
import com.mincom.gescomext.service.MarqueService;
import com.mincom.gescomext.service.NationaliteService;
import com.mincom.gescomext.service.OpCodeImportationService;
import com.mincom.gescomext.service.ProprietaireService;
import com.mincom.gescomext.service.TraitementOpCodeImportationService;
import com.mincom.gescomext.service.TypePieceIdentiteService;
import com.mincom.gescomext.service.TypeStructureService;
import com.mincom.gescomext.service.VilleService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.mincom.gescomext.service.TypeStructureService;
@Controller
public class CodeImportationController {
	
	//INJECTION DES SERVICES
		@Autowired
		NationaliteService natService;
		@Autowired
		VilleService villeService;
		@Autowired
		CommuneService communeService;
		@Autowired
		FormeJuridiqueService fjuryService;
		@Autowired
		ProprietaireService proprietaireService;
		@Autowired
		EntrepriseService entrepriseService;
		@Autowired
		CodeImportationService codeImportationService;
		@Autowired
		OpCodeImportationService opCodeImportationService;
		@Autowired
		TraitementOpCodeImportationService traitementOpCodeImportationService;
		@Autowired
		DemandeurService demandeurService;
		@Autowired
		TypeStructureService typeStructureService;
		@Autowired
		TypePieceIdentiteService typePieceIdentiteService;
		@Autowired
		DomaineActiviteService domaineActiviteService;
		@Autowired
		DepartementService departementService;		
		@Autowired 
		MarqueService marqueService;		  
		@Autowired 
		GenreMarqueService genreMarqueService;
		@Autowired 
		FonctionService fonctionService;
		
		//INJECTION DES REPOSITORY
		@Autowired
		EntrepriseRepository entrepriseRepo;
		@Autowired
		UserRepository userRepository;

		
		
		Date aujourdhui = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm:ss");
		String dateDuJour = formater.format(aujourdhui);
	
		@RequestMapping("/{category}/Liste")
		public String listeEntreprises(@PathVariable("category") String category, ModelMap modelMap)
		{
			List<OpCodeImportation> codfs = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category);
			modelMap.addAttribute("listeCode", codfs);			
			return "./"+category+"/listeDossier";
		}
	
	@RequestMapping("//{category}/RechercherDossier")
	public String rechercheDossier(Integer numDocCodFic, ModelMap modelMap)
	{
		OpCodeImportation codeFic = opCodeImportationService.findBynumDocOp(numDocCodFic);
		modelMap.addAttribute("listeCodeFiscal", codeFic);
		 
		return "./importExport/listeDossier";
	}
	
	@RequestMapping(value = {"/{category}/DemandeurPhysique"})
	public String afficheFormulaire(@PathVariable("category") String category, ModelMap modelMap)
	{
		Integer numDossier = 1;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if(elmt != null) {
			numDossier = 1 + elmt.getNumDocOp();
		}
		
		List<Nationalite> nats = natService.getAllNationalite();
		List<TypePieceIdentite> typePieceIdentite = typePieceIdentiteService.getAllTypePieceIdentite();
		List<Fonction> fonction = fonctionService.getAllFonction();
		List<Marque> marques = marqueService.getAllMarque();
		List<GenreMarque> genreMarques = genreMarqueService.getAllGenreMarque();
		
		modelMap.addAttribute("listeNationalites", nats);
		modelMap.addAttribute("listetypePieceIdentite", typePieceIdentite);
		modelMap.addAttribute("listefonction", fonction);
		modelMap.addAttribute("listemarques", marques);
		modelMap.addAttribute("listegenreMarques", genreMarques);
		
		modelMap.addAttribute("numDossiers", numDossier);
		modelMap.addAttribute("dateDuJour", dateDuJour);

		return "./"+category+"/creationDossierPhysique";
	}

	@RequestMapping(value = {"/{category}/CreationDossier","/{category}/DemandeurMoral"})
	public String afficheFormulaires(@PathVariable("category") String category, ModelMap modelMap)
	{
		Integer numDossier = 1;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if(elmt != null) {
			numDossier = 1 + elmt.getNumDocOp();
		}
		List<Commune> coms = communeService.getAllCommune();
		List<Ville> vils = villeService.getAllVille();
		List<Nationalite> nats = natService.getAllNationalite();
		List<FormeJuridique> fmjury = fjuryService.getAllFormeJuridique();
		List<Marque> marques = marqueService.getAllMarque();
		List<GenreMarque> genreMarques = genreMarqueService.getAllGenreMarque();
		List<TypeStructure> typeStructure = typeStructureService.getAllTypeStructure();
		List<TypePieceIdentite> typePieceIdentite = typePieceIdentiteService.getAllTypePieceIdentite();
		List<DomaineActivite> domaineActivite = domaineActiviteService.getAllDomaineActivite();
		List<Departement> departement = departementService.getAllDepartement();
		List<Fonction> fonction = fonctionService.getAllFonction();
		modelMap.addAttribute("listeCommunes", coms);
		modelMap.addAttribute("listeVilles", vils);
		modelMap.addAttribute("listeNationalites", nats);
		modelMap.addAttribute("listeFormjury", fmjury);
		modelMap.addAttribute("listemarques", marques);
		modelMap.addAttribute("listegenreMarques", genreMarques);
		modelMap.addAttribute("listetypeStructure", typeStructure);
		modelMap.addAttribute("listetypePieceIdentite", typePieceIdentite);
		modelMap.addAttribute("listedomaineActivite", domaineActivite);
		modelMap.addAttribute("listedepartement", departement);
		modelMap.addAttribute("listefonction", fonction);
		
		modelMap.addAttribute("numDossiers", numDossier);
		modelMap.addAttribute("dateDuJour", dateDuJour);

		return "./"+category+"/creationDossierMoral";
	}
	
	@RequestMapping("/{category}/confirmationDossier")
	public String confirmationDossier(@PathVariable("category") String category, 
									@ModelAttribute("entreprise") Entreprise entreprise,
										@ModelAttribute("proprietaire") Proprietaire proprietaire,
											@ModelAttribute("codeImportation") CodeImportation codeImportation,
												@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation,
													@ModelAttribute("demandeur") Demandeur demandeur,
														ModelMap modelMap){
		String pageselect="confirmerDossierMoral"; 
		List<Commune> coms = communeService.getAllCommune();
		List<Ville> vils = villeService.getAllVille();
		List<Nationalite> nats = natService.getAllNationalite();
		List<FormeJuridique> fmjury = fjuryService.getAllFormeJuridique();
		List<Marque> marques = marqueService.getAllMarque();
		List<GenreMarque> genreMarques = genreMarqueService.getAllGenreMarque();
		List<TypeStructure> typeStructure = typeStructureService.getAllTypeStructure();
		List<TypePieceIdentite> typePieceIdentite = typePieceIdentiteService.getAllTypePieceIdentite();
		List<DomaineActivite> domaineActivite = domaineActiviteService.getAllDomaineActivite();
		List<Departement> departement = departementService.getAllDepartement();
		List<Fonction> fonction = fonctionService.getAllFonction();
		modelMap.addAttribute("listeCommunes", coms);
		modelMap.addAttribute("listeVilles", vils);
		modelMap.addAttribute("listeNationalites", nats);
		modelMap.addAttribute("listeFormjury", fmjury);
		modelMap.addAttribute("listemarques", marques);
		modelMap.addAttribute("listegenreMarques", genreMarques);
		modelMap.addAttribute("listetypeStructure", typeStructure);
		modelMap.addAttribute("listetypePieceIdentite", typePieceIdentite);
		modelMap.addAttribute("listedomaineActivite", domaineActivite);
		modelMap.addAttribute("listedepartement", departement);
		modelMap.addAttribute("listefonction", fonction);
		modelMap.addAttribute("verifEntreprise", entreprise);
		modelMap.addAttribute("verifProprietaire", proprietaire);
		modelMap.addAttribute("verifCodeImportation", codeImportation);
		modelMap.addAttribute("verifDemandeur", demandeur);
		if(demandeur.getNomDem()!=null && demandeur.getPrenomsDem()!=null){
			pageselect = "confirmerDossier";
		}
		
		modelMap.addAttribute("dateDuJour", dateDuJour);
		return "./"+category+"/"+pageselect;
	}
	
	@RequestMapping("/{category}/ajoutDossier")
	public String ajoutEntreprise(@PathVariable("category") String category, 
									@ModelAttribute("entreprise") Entreprise entreprise,
										@ModelAttribute("proprietaire") Proprietaire proprietaire,
											@ModelAttribute("codeImportation") CodeImportation codeImportation,
												@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation,
													@ModelAttribute("demandeur") Demandeur demandeur,
														ModelMap modelMap)
	{
		//User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);

		//calcul du dernier dossier
		Integer numDossier = 1001001;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if(elmt != null) {
			numDossier = 1 + elmt.getNumDocOp();
		}		
		Date date = new Date();
		if(category.equals("CodeImportExport")) {
			String codesExportation="";
			String codesFiscals="";
			
			if(entreprise.getExoregcomEntr().equals("non") && !entreprise.getRegcommerceEntr().isEmpty() && !entreprise.getContribuableEntr().isEmpty()){
				codesExportation = CalculeCodesExportation.getCodeImportExport(entreprise.getRegcommerceEntr(), entreprise.getContribuableEntr(), numDossier);
				codesFiscals = CalculeCodesExportation.getCodeFixcal(1,numDossier);
			}else if(entreprise.getExoregcomEntr().equals("oui") && entreprise.getRegcommerceEntr().isEmpty() && !entreprise.getContribuableEntr().isEmpty()){
				codesExportation = CalculeCodesExportation.getCodeImportExportWithOutRCCM(entreprise.getContribuableEntr(), numDossier);
				codesFiscals = CalculeCodesExportation.getCodeFixcal(0,numDossier);
			}
			if(!codesExportation.isEmpty() && !codesFiscals.isEmpty()){
				Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);
				entreprise.setProprietaires(saveProprietaire);		
				entreprise.setDateEntr(date);
				entreprise.setCodeImportExportEntr(codesExportation);
				
				Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);		
				
				codeImportation.setEntreprise(saveEntreprise);
				codeImportation.setNumCodFic(codesFiscals);
				CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);
				
				opCodeImportation.setMontantOp("30000");			
				opCodeImportation.setCodeImportation(codeImportationSave);
			}else{
				System.out.println("Une erreur s'est produite pendant l'enregistrement");
			}
			
		}if(category.equals("CodeOccasionnel")) {
			// typeStructure typeStructures = typeStructureService.
			// String codesOccasionnel = CalculeCodesExportation.getCodeOccasionnel(codeStruc, numDossier);

			
			if(demandeur.getNomDem() != null) {
				Demandeur saveDemandeur = demandeurService.saveDemandeur(demandeur);
				codeImportation.setDemandeur(saveDemandeur);
			}
			
			if(proprietaire.getNomProp() != null && entreprise.getNomEntr() !=null ) {
			Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);	
			
			entreprise.setProprietaires(saveProprietaire);		
			entreprise.setDateEntr(date);
			
			Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);
			codeImportation.setEntreprise(saveEntreprise);
			codeImportation.setStatutDemandeurCodeImp("oui");
			}
			
			codeImportation.setNumOcca("NUM-CODE-ACCO-"+numDossier);
			CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);
			
			opCodeImportation.setMontantOp("50000");
			opCodeImportation.setCodeImportation(codeImportationSave);
			
			
		}if(category.equals("LeveeDeGage")) {

			String codesLege = CalculeCodesExportation.getLeveeGage(codeImportation.getUsageGag(), codeImportation.getNumChassisGag(), "exceptionnel", numDossier);
			
			if(demandeur.getNomDem() != null) {
				Demandeur saveDemandeur = demandeurService.saveDemandeur(demandeur);
				codeImportation.setDemandeur(saveDemandeur);
			}
			
			if(proprietaire.getNomProp() != null && entreprise.getNomEntr() !=null ) {
				Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);	
				
				entreprise.setProprietaires(saveProprietaire);		
				entreprise.setDateEntr(date);
				
				Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);
				codeImportation.setEntreprise(saveEntreprise);
				codeImportation.setStatutDemandeurCodeImp("oui");
			}
			
			codeImportation.setNumGag("NUM-CODE-GAGE-"+numDossier);
			CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);
			
			opCodeImportation.setMontantOp("50000");
			opCodeImportation.setCodeImportation(codeImportationSave);
			
			
		}
		
		opCodeImportation.setTypeOp("Attribution");
		opCodeImportation.setActiveApprobationOp("inactif");
		opCodeImportation.setTypeCodeOp(category);
		opCodeImportation.setNumDocOp(numDossier);
		opCodeImportation.setDateOp(date);
		opCodeImportation.setUser(user);
		opCodeImportationService.saveOpCodeImportation(opCodeImportation);
		
		numDossier = numDossier +1;
		modelMap.addAttribute("numDossiers", numDossier);
		modelMap.addAttribute("dateDuJour", dateDuJour);
		
		return "./"+category+"/creationDossierMoral";
	}
	
	
	//ZONE RENOUVELLEMENT
	@RequestMapping("/{category}/Renouvellement")
	public String renouvellementCode(@PathVariable("category") String category,
												String codeImportExportEntr,
													ModelMap modelMap){
		if(codeImportExportEntr!=null){
			Entreprise entr = entrepriseService.findByCodeImportExportEntrAndContribuableEntr(codeImportExportEntr);
			Proprietaire props = entr.getProprietaires();
			List<OpCodeImportation> fgg = opCodeImportationService.findCodeImportationByTypecodeAndByCodeRccmOrCc(codeImportExportEntr,category);
			modelMap.addAttribute("infoEntreprise",entr); 
			modelMap.addAttribute("infoProprietaire", props);
			modelMap.addAttribute("listeCode", fgg);
		}	
		return "./"+category+"/renouvellementDossier";
	}
	
	@RequestMapping("/{category}/Renouveller/{idEntr}")
	public String coderenouveller(@PathVariable("category") String category, @PathVariable("idEntr") Long idEntr,
											@ModelAttribute("codeImportation") CodeImportation codeImportation, 
												@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation,
													ModelMap modelMap) {
		//User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);

		//calcul du dernier dossier
		Integer numDossier = 1;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if(elmt != null) {
			numDossier = 1 + elmt.getNumDocOp();
		}
		
		Date date = new Date();
		Entreprise entreprise = entrepriseService.getEntrepriseById(idEntr);
		codeImportation.setEntreprise(entreprise);
		codeImportation.setNumCodFic("NUM-CODE-FISCAL-RENOUV-"+numDossier);
		CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);
		
		opCodeImportation.setMontantOp("30000");
		opCodeImportation.setTypeOp("Renouvellement");
		opCodeImportation.setActiveApprobationOp("inactif");
		opCodeImportation.setTypeCodeOp(category);
		opCodeImportation.setNumDocOp(numDossier);
		opCodeImportation.setDateOp(date);
		opCodeImportation.setUser(user);
		opCodeImportation.setCodeImportation(codeImportationSave);
		opCodeImportationService.saveOpCodeImportation(opCodeImportation);
		
		return "./"+category+"/renouvellementDossier";
	}
	
	//ZONE DUPLICATA
	  @RequestMapping("/{category}/Duplicata")
		public String duplicataCode(@PathVariable("category") String category, String codeImportExportEntr, ModelMap modelMap)
		{
			if(codeImportExportEntr!=null){
				Entreprise entr = entrepriseService.findByCodeImportExportEntrAndContribuableEntr(codeImportExportEntr);
				if(entr != null) {
					Proprietaire props = entr.getProprietaires();
					List<OpCodeImportation> fgg = opCodeImportationService.findCodeImportationByTypecodeAndByCodeRccmOrCc(codeImportExportEntr,category);
					modelMap.addAttribute("infoEntreprise",entr); 
					modelMap.addAttribute("infoProprietaire", props);
					modelMap.addAttribute("listeCode", fgg);
				}else {
					String errors = "Aucun compte n'est actif";
					modelMap.addAttribute("msgErrors", errors);
				}
				
			}
			return "./"+category+"/duplicataDossier";
		}
		
		@RequestMapping("/{category}/Dupliquer/{id}")
		public String codeDupliquer(@PathVariable("category") String category,
											@PathVariable("id") Long id, 
												@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation, 
												ModelMap modelMap){
			
			OpCodeImportation opCodeImportationFind = opCodeImportationService.getOpCodeImportationById(id);
			if(opCodeImportationFind != null && opCodeImportationFind.getActiveApprobationOp().equals("actif")) {
				
				CodeImportation codeImportation = opCodeImportationFind.getCodeImportation();
				Date date = new Date();
				
				//User connecté
				String username = GetCurrentUser.getUserConnected();
				User user = userRepository.findByUsername(username);
				
				//calcul du dernier dossier
				Integer numDossier = 1;	
				OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
				if(elmt != null) {
					numDossier = 1 + elmt.getNumDocOp();
				}			
				
				if(category.equals("CodeImportExport")) {
					opCodeImportation.setMontantOp("30000");
				}if(category.equals("CodeOccasionnel")) {
					opCodeImportation.setMontantOp("50000");
				}if(category.equals("LeveeGage")) {
					opCodeImportation.setMontantOp("50000");
				}
				
				opCodeImportation.setTypeOp("Duplicata");
				opCodeImportation.setActiveApprobationOp("inactif");
				opCodeImportation.setTypeCodeOp(category);
				opCodeImportation.setNumDocOp(numDossier);
				opCodeImportation.setDateOp(date);
				opCodeImportation.setUser(user);
				opCodeImportation.setCodeImportation(codeImportation);				
				opCodeImportationService.saveOpCodeImportation(opCodeImportation);
			}
			else {
				String errors = "Aucun compte n'est actif";
				System.out.print(errors);
				modelMap.addAttribute("msgErrors", errors);
			}
			 
			return "./"+category+"/duplicataDossier";
		}
		
	
	//ZONNE PAIEMENT
	
	@RequestMapping("/{category}/Paiement")
	public String rechercheCode(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap)
	{
		if(numDoc != null) {
			OpCodeImportation codeFic = opCodeImportationService.findBynumDocOp(numDoc);
			modelMap.addAttribute("infoCodefic", codeFic);			
		}
		
		List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category);
		modelMap.addAttribute("listeCodes", cods);		
		 
		return "./"+category+"/listePaiement";
	}
	
	
	@RequestMapping("/{category}/Payer")
	public String validerReglement(@PathVariable("category") String category, Integer numDoc, 
										@ModelAttribute("traitementOpCodeImportation") TraitementOpCodeImportation traitementOpCodeImportation,
											ModelMap modelMap)
	{
		OpCodeImportation opcode = opCodeImportationService.findBynumDocOp(numDoc);
		if(opcode != null) {
			//User connecté
			String username = GetCurrentUser.getUserConnected();
			User user = userRepository.findByUsername(username);
			
			traitementOpCodeImportation.setStatutTrait("paiement");
			traitementOpCodeImportation.setOpCodeImportation(opcode);
			traitementOpCodeImportation.setDateTrait(new Date());
			traitementOpCodeImportation.setUser(user);
			traitementOpCodeImportationService.saveTraitementOpCodeImportation(traitementOpCodeImportation);
			opcode.setActivePaimentOp(1);
			opCodeImportationService.saveOpCodeImportation(opcode);	
		}
		
		List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category);
		modelMap.addAttribute("listeCodes", cods);
		modelMap.addAttribute("infoCodefic", opcode);
		 
		return "./"+category+"/listePaiement";
	}
	
	
	
	//ZONNE APPROBATION
	
	@RequestMapping("/{category}/Approbation")
	public String rechApprobationCode(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap)
	{
		if(numDoc!=null) {
			OpCodeImportation opcodeFic = opCodeImportationService.findBynumDocOp(numDoc);
			modelMap.addAttribute("infoCodefic", opcodeFic);
		}
		
		List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category);
		modelMap.addAttribute("listeCodes", cods);
		 
		return "./"+category+"/listeApprobation";
	}
	
	@RequestMapping("/{category}/Approuver")
	public String validerApprobation(@PathVariable("category") String category, Integer numDoc, 
										@ModelAttribute("traitementOpCodeImportation") TraitementOpCodeImportation traitementOpCodeImportation,
											ModelMap modelMap)
	{
		OpCodeImportation opcodeFic = opCodeImportationService.findBynumDocOp(numDoc);
		if(opcodeFic != null) {
		//User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		
		traitementOpCodeImportation.setStatutTrait("approbation");
		traitementOpCodeImportation.setOpCodeImportation(opcodeFic);
		traitementOpCodeImportation.setDateTrait(new Date());
		traitementOpCodeImportation.setUser(user);
		traitementOpCodeImportationService.saveTraitementOpCodeImportation(traitementOpCodeImportation);
		opcodeFic.setActiveApprobationOp("actif");
		opCodeImportationService.saveOpCodeImportation(opcodeFic);	
		}
		
		List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category);
		modelMap.addAttribute("listeCodes", cods);
		modelMap.addAttribute("infoCodefic", opcodeFic);
		 
		return "./"+category+"/listeApprobation";
	}
		
	//ZONE ETAT CODE
	  @RequestMapping("/{category}/listeEtatCodes")
	  public String ListeEtatCodes(@PathVariable("category") String category,
			  							Long numDoc, 
			  								String statut,  
			  									ModelMap modelMap) {
		  if(numDoc !=null && statut !=null) {
			//User connecté
				String username = GetCurrentUser.getUserConnected();
				User user = userRepository.findByUsername(username);
				
			  if(statut.equals("actif") || statut.equals("bloquer") || statut.equals("annuler")) {
				  TraitementOpCodeImportation elmts = traitementOpCodeImportationService.getTraitementOpCodeImportationById(numDoc);
				  if(elmts != null) {
					  elmts.setDateTrait(new Date());
					  elmts.setUser(user);						  				  
					  traitementOpCodeImportationService.saveTraitementOpCodeImportation(elmts);
					  
					  OpCodeImportation opCodeImportation = elmts.getOpCodeImportation();
					  opCodeImportation.setActiveApprobationOp(statut);	
					  opCodeImportationService.saveOpCodeImportation(opCodeImportation);
				  }
			  }
		  }
		  List<TraitementOpCodeImportation> listelmt = traitementOpCodeImportationService.findAllTraitementOpCodeImportationByTypeCodeOp(category);
		  modelMap.addAttribute("listecodes", listelmt);
		  return "./"+category+"/listeEtatCode";
	}
	  
  @RequestMapping("/{category}/ListeEtatCode")
  public String rechListeEtatCode(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap) {
	  if(numDoc !=null ) {
		  	List<TraitementOpCodeImportation> codeimp = opCodeImportationService.findBynumDocOp(numDoc).getTraitementOpCodeImportation();
		  	if(codeimp!=null) {
		  	modelMap.addAttribute("listecodes", codeimp);
		 }
	  }
	 
	  return "./"+category+"/listeEtatCode";
	}
	  
	  @RequestMapping("/{category}/EditionFiches")
	  public String gageEditerFiches(@PathVariable("category") String category, String numDocCode, ModelMap modelMap) {
		  if(numDocCode !=null) {
			//   List<TraitementOpCodeImportation> codes = traitementOpCodeImportationService.findAllTraitementOpCodeImportationByTypeCodeOp(numDocCode).getTraitementOpCodeImportation();
			//    if(codes!=null) {
			// 	   modelMap.addAttribute("listecodes", codes);
			//    }
		  }else {
			  List<TraitementOpCodeImportation> listecode = traitementOpCodeImportationService.findAllTraitementOpCodeImportationByTypeCodeOp(category);
			  modelMap.addAttribute("listecodes", listecode);
		  }
		  
		  return "./"+category+"/editerFiche";
		}
	  
	//ZONNE PAIEMENT
	  
		@RequestMapping("/{category}/Recu")			
		public ResponseEntity<byte[]> generatePdf(@RequestParam Integer numDoc) throws FileNotFoundException, JRException, ParseException {
			HttpHeaders headers = new HttpHeaders();
			byte[] data = {};
			SimpleDateFormat formaters = null;
		    formaters = new SimpleDateFormat("dd-MM-yy");		    
			if(numDoc != null) {
				List<OpCodeImportation> listOpCodes = new ArrayList<>();
				OpCodeImportation opCodes  = opCodeImportationService.findBynumDocOp(numDoc);
				listOpCodes.add(opCodes);
				JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOpCodes);
				JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/autres/recuModel.jrxml"));
				HashMap<String, Object> map = new HashMap<>();
				JasperPrint report = JasperFillManager.fillReport(compileReport, map,beanCollectionDataSource);
				//JasperExportManager.exportReportToPdfFile(report, "villeViewer.pdf");
				data = JasperExportManager.exportReportToPdf(report);
				headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=recuModel.pdf");
								
			}
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);	
		}
}
