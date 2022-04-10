package com.mincom.gescomext.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import com.mincom.gescomext.config.CalculeCodesExportation;
import com.mincom.gescomext.config.CalculeCodesIdex;
import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Beneficiaire;
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
import com.mincom.gescomext.service.ActionListeService;
import com.mincom.gescomext.service.BeneficiaireService;
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
import com.mincom.gescomext.validator.BeneficiaireValidator;
import com.mincom.gescomext.validator.DemandeurValidator;
import com.mincom.gescomext.validator.EntrepriseValidator;
import com.mincom.gescomext.validator.LeveeGageValidator;
import com.mincom.gescomext.validator.ProprietaireValidator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class CodeImportationController {

	// INJECTION DES SERVICES
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
	@Autowired
	ActionListeService actionListeService;
	@Autowired
	BeneficiaireService beneficiaireService;

	// INJECTION DES REPOSITORY
	@Autowired
	EntrepriseRepository entrepriseRepo;
	@Autowired
	UserRepository userRepository;

	String validate = "non";
	Integer numDossier = 1001001;
	String codeIdex = "";
	List<String> errorsList = new ArrayList<>();

	// @Value("${server.servlet.context-path}")
	// private String contextPath;

	Date aujourdhui = new Date();
	SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm:ss");
	String dateDuJour = formater.format(aujourdhui);

	@RequestMapping("/")
	public String idex(ModelMap modelMap) throws IOException {
		numDossier = 1001001;
		/*
		 * ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser(); String
		 * username = GetCurrentUser.getUserConnected(); User user =
		 * userRepository.findByUsername(username); //List<ActionListe> listeUrlUser =
		 * classGestionUrl.getListeAcctions(user,category);
		 * //modelMap.addAttribute("listeUrlUser", listeUrlUser);
		 */

		/*
		 * List<TableauCodeIdex> listesd = TableauCodeIdex.getKeyIdex();
		 * listesd.forEach(voir ->{
		 * System.out.println(" BaseDix => "+voir.getBaseDix()+" Correpondance => "+voir
		 * .getKey()); });
		 */

		/*
		 * List<TableauCorrespondance> listesds =
		 * TableauCorrespondance.getContribuableCode(); listesds.forEach(voir ->{
		 * System.out.println(" BaseDix => "+voir.getBaseDix()+" Lettre => "+voir.
		 * getContriKey()+" Correpondance => "+voir.getKey()); });
		 */

		/*
		 * SimpleDateFormat formatGag = new SimpleDateFormat("yyyy-MM-dd"); String
		 * dateDuJours = formatGag.format(new Date()); LocalDate dates =
		 * LocalDate.parse("2021-10-04", DateTimeFormatter.ISO_LOCAL_DATE); LocalDate
		 * dJour = LocalDate.parse(dateDuJours, DateTimeFormatter.ISO_LOCAL_DATE);
		 * 
		 * Period diffDate = Period.between(dJour, dates); int years =
		 * Math.abs(diffDate.getYears()); int mois = Math.abs(diffDate.getMonths());
		 * System.out.println(diffDate); System.out.println(years);
		 */

		/*
		 * List<Entreprise> listeEntreprise = entrepriseService.getAllEntreprise();
		 * listeEntreprise.forEach(entreprise->{ errorsList.clear();
		 * EntrepriseValidator.validate(entreprise).forEach(error -> {
		 * errorsList.add(error); }); if(errorsList.size()==0) { codeIdex =
		 * CalculeCodesIdex.getCodeCodeIdex(entreprise.getContribuableEntr(),"",
		 * numDossier); entreprise.setCodeIdexEntr(codeIdex); }else { codeIdex =
		 * CalculeCodesIdex.getCodeCodeIdex("",entreprise.getNomEntr(), numDossier);
		 * entreprise.setCodeIdexEntr(codeIdex); }
		 * System.out.println(codeIdex+" "+entreprise.getContribuableEntr()+" "
		 * +entreprise.getIdEntr()); entrepriseService.saveEntreprise(entreprise);
		 * numDossier = numDossier+1; });
		 * 
		 * List<Demandeur> listedemandeur = demandeurService.getAllDemandeur();
		 * listedemandeur.forEach(demandeur->{ errorsList.clear();
		 * DemandeurValidator.validate(demandeur).forEach(error -> {
		 * errorsList.add(error); }); if(errorsList.size()==0) { codeIdex =
		 * CalculeCodesIdex.getCodeCodeIdex(demandeur.getContribuableDem(),"",
		 * numDossier); demandeur.setCodeIdexDem(codeIdex); }else { codeIdex =
		 * CalculeCodesIdex.getCodeCodeIdex("",demandeur.getNomDem(), numDossier);
		 * demandeur.setCodeIdexDem(codeIdex); }
		 * System.out.println(codeIdex+" "+demandeur.getContribuableDem()+" "+demandeur.
		 * getIdDem()); demandeurService.saveDemandeur(demandeur); numDossier =
		 * numDossier+1; });
		 */

		return "index";
	}

	@RequestMapping("/{category}/Liste")
	public String listeEntreprises(@PathVariable("category") String category, ModelMap modelMap) throws IOException {
		
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Liste")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		List<OpCodeImportation> codfs = opCodeImportationService.findOpCodeImportationsByTypeCodeOp(category);
		modelMap.addAttribute("listeCode", codfs);

		
		if (validate.equals("oui")) {
			return "./" + category + "/listeDossier";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/RechercherDossier")
	public String rechercheDossier(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap) {
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);

		OpCodeImportation codeElmt = opCodeImportationService.findBynumDocOp(numDoc, category, site);
		modelMap.addAttribute("listeCode", codeElmt);
		return "./" + category + "/listeDossier";
	}

	@RequestMapping(value = { "/{category}/DemandeurPhysique" })
	public String afficheFormulaire(@PathVariable("category") String category, ModelMap modelMap) {
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/CreationDossier")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		Integer numDossier = 1;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if (elmt != null) {
			numDossier = 1 + elmt.getNumDocOp();
		}

		List<Nationalite> nats = natService.getAllNationalite();
		List<TypePieceIdentite> typePieceIdentite = typePieceIdentiteService.getAllTypePieceIdentite();
		List<TypeStructure> typeStructure = typeStructureService.getAllTypeStructure();
		List<Fonction> fonction = fonctionService.getAllFonction();
		List<Marque> marques = marqueService.getAllMarque();
		List<GenreMarque> genreMarques = genreMarqueService.getAllGenreMarque();

		modelMap.addAttribute("listeNationalites", nats);
		modelMap.addAttribute("listetypePieceIdentite", typePieceIdentite);
		modelMap.addAttribute("listefonction", fonction);
		modelMap.addAttribute("listemarques", marques);
		modelMap.addAttribute("listegenreMarques", genreMarques);
		modelMap.addAttribute("listetypeStructure", typeStructure);

		modelMap.addAttribute("numDossiers", numDossier);
		modelMap.addAttribute("dateDuJour", dateDuJour);

		if (validate.equals("oui")) {
			return "./" + category + "/creationDossierPhysique";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping(value = { "/{category}/CreationDossier", "/{category}/DemandeurMoral" })
	public String afficheFormulaires(@PathVariable("category") String category, ModelMap modelMap) {
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/CreationDossier")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		Integer numDossier = 1;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if (elmt != null) {
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

		
		if (validate.equals("oui")) {
			return "./" + category + "/creationDossierMoral";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/confirmationDossier")
	public String confirmationDossier(@PathVariable("category") String category,
			@ModelAttribute("entreprise") Entreprise entreprise,
			@ModelAttribute("proprietaire") Proprietaire proprietaire,
			@ModelAttribute("codeImportation") CodeImportation codeImportation,
			@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation,
			@ModelAttribute("demandeur") Demandeur demandeur, @ModelAttribute("beneficiaire") Beneficiaire beneficiaire,
			String codeAncien, String regcommerceNouv, String regcommerceEntrAnc, ModelMap modelMap) {

		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/CreationDossier")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		if (regcommerceNouv != null && !regcommerceNouv.isEmpty()) {
			entreprise.setRegcommerceEntr(regcommerceNouv);
		}
		if (regcommerceEntrAnc != null && !regcommerceEntrAnc.isEmpty()) {
			entreprise.setRegcommerceEntr(regcommerceEntrAnc);
		}
		if (codeImportation.getDateMiseCirculationGag() != null) {
			Date dategag = codeImportation.getDateMiseCirculationGag();
			SimpleDateFormat formatGag = new SimpleDateFormat("yyyy-MM-dd");
			String dateDuJours = formatGag.format(new Date());
			LocalDate dates = LocalDate.parse(formatGag.format(dategag), DateTimeFormatter.ISO_LOCAL_DATE);
			LocalDate dJour = LocalDate.parse(dateDuJours, DateTimeFormatter.ISO_LOCAL_DATE);

			Period diffDate = Period.between(dJour, dates);
			int jours = Math.abs(diffDate.getDays());
			int mois = Math.abs(diffDate.getMonths());
			int years = Math.abs(diffDate.getYears());
			modelMap.addAttribute("jours", jours);
			modelMap.addAttribute("mois", mois);
			modelMap.addAttribute("years", years);
		}
		String pageselect = "confirmerDossierMoral";
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
		modelMap.addAttribute("verifBeneficiaire", beneficiaire);

		modelMap.addAttribute("numDossiers", codeAncien.toUpperCase());
		if (demandeur.getNomDem() != null && demandeur.getPrenomsDem() != null) {
			pageselect = "confirmerDossier";
		}

		modelMap.addAttribute("dateDuJour", dateDuJour);
	
		if (validate.equals("oui")) {
			return "./" + category + "/" + pageselect;
		} else {
			return "./accessDenied";
		}

	}

	@RequestMapping("/{category}/ajoutDossier")
	public String ajoutEntreprise(@PathVariable("category") String category,
			@ModelAttribute("entreprise") Entreprise entreprise,
			@ModelAttribute("proprietaire") Proprietaire proprietaire,
			@ModelAttribute("codeImportation") CodeImportation codeImportation,
			@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation,
			@ModelAttribute("demandeur") Demandeur demandeur, @ModelAttribute("beneficiaire") Beneficiaire beneficiaire,
			String codeAncien, ModelMap modelMap) {
//User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);

//calcul du dernier dossier
		Integer numDossier = 1001001;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if (elmt != null) {
			numDossier = 1 + elmt.getNumDocOp();
		}

		Date date = new Date();

		String codeStruc = "";
		errorsList.clear();

		if (category.equals("CodeImportExport")) {
			String codesExportation = "";
			String codesFiscals = "";

			EntrepriseValidator.validate(entreprise).forEach(error -> {
				errorsList.add(error);
			});
			ProprietaireValidator.validate(proprietaire).forEach(error -> {
				errorsList.add(error);
			});

			if (errorsList.size() == 0) {

				Entreprise verifByContriEntreprise = entrepriseService
						.findByContribuableEntr(entreprise.getContribuableEntr());
				Entreprise verifByRaisonEntreprise = entrepriseService.findByNomEntr(entreprise.getNomEntr());
				if ((verifByRaisonEntreprise == null) && (verifByContriEntreprise == null)) {

					if (entreprise.getExoregcomEntr() != null && entreprise.getExoregcomEntr().equals("non") && entreprise.getRegcommerceEntr() != null && !entreprise.getRegcommerceEntr().isEmpty()
							&& !entreprise.getContribuableEntr().isEmpty()) {
						codesExportation = CalculeCodesExportation.getCodeImportExport(entreprise.getRegcommerceEntr(),
								entreprise.getContribuableEntr(), numDossier);
						codesFiscals = CalculeCodesExportation.getCodeFixcal(1, numDossier);
					} else if (entreprise.getExoregcomEntr() != null && entreprise.getExoregcomEntr().equals("oui") && entreprise.getRegcommerceEntr() != null && entreprise.getRegcommerceEntr().isEmpty()
							&& !entreprise.getContribuableEntr().isEmpty()) {
						codesExportation = CalculeCodesExportation
								.getCodeImportExportWithOutRCCM(entreprise.getContribuableEntr(), numDossier);
						codesFiscals = CalculeCodesExportation.getCodeFixcal(0, numDossier);
					} else if (entreprise.getDepartement().getIdDep() != 0 && entreprise.getRegcommerceEntr() == null
							&& !entreprise.getContribuableEntr().isEmpty()) {
						codesExportation = CalculeCodesExportation
								.getCodeImportExportWithOutRCCM(entreprise.getContribuableEntr(), numDossier);
						codesFiscals = CalculeCodesExportation.getCodeFixcal(0, numDossier);
					}
					if (!codesExportation.isEmpty() && !codesFiscals.isEmpty()) {
						Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);
						entreprise.setProprietaires(saveProprietaire);
						entreprise.setCodeIdexEntr(
								CalculeCodesIdex.getCodeCodeIdex(entreprise.getContribuableEntr(), "", numDossier));
						entreprise.setDateEntr(date);
						entreprise.setCodeImportExportEntr(codesExportation);

						Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);

						codeImportation.setEntreprise(saveEntreprise);
						codeImportation.setNumCodFic(codesFiscals);
						codeImportation.setStatutDemandeurCodeImp("oui");
						CodeImportation codeImportationSave = codeImportationService
								.saveCodeImportation(codeImportation);

						if (codeAncien != null && codeAncien.length() == 9) {
							opCodeImportation.setTypeOp("Renouvellement");
						} else {
							opCodeImportation.setTypeOp("Attribution");
						}
						opCodeImportation.setMontantOp("30000");
						opCodeImportation.setCodeImportation(codeImportationSave);

						opCodeImportation.setActivePaimentOp(0);
						opCodeImportation.setActiveApprobationOp("inactif");
						opCodeImportation.setActiveSignatureOp("non");

						opCodeImportation.setTypeCodeOp(category);
						opCodeImportation.setNumDocOp(numDossier);
						opCodeImportation.setDateOp(date);
						opCodeImportation.setUser(user);
						opCodeImportationService.saveOpCodeImportation(opCodeImportation);

					} else {
						System.out.println("Une erreur s'est produite pendant l'enregistrement");
					}
				} else {
					System.out.println("La raison sociale ou le cc existe");
				}
			}

		} else if (category.equals("CodeOccasionnel")) {

			if (demandeur.getNomDem() != null) {
				DemandeurValidator.validate(demandeur).forEach(error -> {
					errorsList.add(error);
				});
				if (errorsList.size() == 0) {
					demandeur.setQuotaOccaDem(1);
					demandeur.setCodeIdexDem(CalculeCodesIdex.getCodeCodeIdex("", demandeur.getNomDem(), numDossier));
					Demandeur saveDemandeur = demandeurService.saveDemandeur(demandeur);
					codeImportation.setDemandeur(saveDemandeur);
					codeImportation.setStatutDemandeurCodeImp("non");
					codeStruc = "42000A";
				}
			}

			if (proprietaire.getNomProp() != null && entreprise.getNomEntr() != null) {

				EntrepriseValidator.validate(entreprise).forEach(error -> {
					errorsList.add(error);
				});
				ProprietaireValidator.validate(proprietaire).forEach(error -> {
					errorsList.add(error);
				});
				if (errorsList.size() == 0) {
					TypeStructure typeStructures = typeStructureService
							.getTypeStructureById(entreprise.getTypeStructure().getIdStruc());
					codeStruc = typeStructures.getCodeStruc();
					Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);
					entreprise.setProprietaires(saveProprietaire);
					entreprise.setCodeIdexEntr(
							CalculeCodesIdex.getCodeCodeIdex(entreprise.getContribuableEntr(), "", numDossier));
					entreprise.setDateEntr(date);
					entreprise.setQuotaOccaEntr(1);

					Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);
					codeImportation.setEntreprise(saveEntreprise);
					codeImportation.setStatutDemandeurCodeImp("oui");

				}
			}
			if (errorsList.size() == 0) {
				String codesOccasionnel = CalculeCodesExportation.getCodeOccasionnel(codeStruc, numDossier);
				if (!codesOccasionnel.isEmpty()) {
					codeImportation.setNumOcca(codesOccasionnel);
					CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);
					if (codeStruc.equals("42000A") || entreprise.getExoPaiementEntr().equals("non")) {
						opCodeImportation.setMontantOp("50000");
						opCodeImportation.setActivePaimentOp(0);
						opCodeImportation.setActiveApprobationOp("inactif");
					} else {
						opCodeImportation.setMontantOp("0");
						opCodeImportation.setActivePaimentOp(1);
						opCodeImportation.setActiveApprobationOp("inactif");
					}
					opCodeImportation.setTypeOp("Attribution");
					opCodeImportation.setCodeImportation(codeImportationSave);

					opCodeImportation.setActiveSignatureOp("non");
					opCodeImportation.setTypeCodeOp(category);
					opCodeImportation.setNumDocOp(numDossier);
					opCodeImportation.setDateOp(date);
					opCodeImportation.setUser(user);
					opCodeImportationService.saveOpCodeImportation(opCodeImportation);
				}
			}
		} else if (category.equals("LeveeDeGage")) {
			LeveeGageValidator.validate(codeImportation).forEach(error -> {
				errorsList.add(error);
			});
			String typeGage = "";
			String codesLege = "";
			if (errorsList.size() == 0) {
				Date dategag = codeImportation.getDateGag();

				SimpleDateFormat formatGag = new SimpleDateFormat("yyyy-MM-dd");
				String dateDuJours = formatGag.format(new Date());
				LocalDate dategags = LocalDate.parse(formatGag.format(dategag), DateTimeFormatter.ISO_LOCAL_DATE);
				LocalDate dJour = LocalDate.parse(dateDuJours, DateTimeFormatter.ISO_LOCAL_DATE);

				Period diffDate = Period.between(dJour, dategags);
				int years = Math.abs(diffDate.getYears());
				int mois = Math.abs(diffDate.getMonths());

				if ((years == 2 && mois > 0) || (years > 2)) {
					typeGage = "ordinaire";
				} else if ((years == 2 && mois == 0) || (years < 2)) {
					typeGage = "exceptionnelle";
				}

				codesLege = CalculeCodesExportation.getLeveeGage(codeImportation.getUsageGag(),
						codeImportation.getNumChassisGag(), typeGage, numDossier);
			}
			if (demandeur.getNomDem() != null) {
				DemandeurValidator.validate(demandeur).forEach(error -> {
					errorsList.add(error);
				});
				if (errorsList.size() == 0) {
					demandeur.setCodeIdexDem(CalculeCodesIdex.getCodeCodeIdex("", demandeur.getNomDem(), numDossier));
					Demandeur saveDemandeur = demandeurService.saveDemandeur(demandeur);
					codeImportation.setDemandeur(saveDemandeur);
					codeImportation.setStatutDemandeurCodeImp("non");
				}
			}

			if (proprietaire.getNomProp() != null && entreprise.getNomEntr() != null) {

				EntrepriseValidator.validate(entreprise).forEach(error -> {
					errorsList.add(error);
				});
				ProprietaireValidator.validate(proprietaire).forEach(error -> {
					errorsList.add(error);
				});
				if (errorsList.size() == 0) {
					Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);

					entreprise.setProprietaires(saveProprietaire);
					entreprise.setDateEntr(date);
					entreprise.setCodeIdexEntr(
							CalculeCodesIdex.getCodeCodeIdex(entreprise.getContribuableEntr(), "", numDossier));

					Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);
					codeImportation.setEntreprise(saveEntreprise);
					codeImportation.setStatutDemandeurCodeImp("oui");

				}
			}

			if (errorsList.size() == 0 && codeImportation.getUsageGag().equals("commercial")) {
				BeneficiaireValidator.validate(beneficiaire).forEach(error -> {
					errorsList.add(error);
				});
				if (errorsList.size() == 0) {
					Beneficiaire saveBeneficiaire = beneficiaireService.saveBeneficiaire(beneficiaire);
					codeImportation.setBeneficiaire(saveBeneficiaire);
				}
			}

			if (errorsList.size() == 0) {
				codeImportation.setNumGag(codesLege);
				codeImportation.setTypeGag(typeGage);
				CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);

				if (typeGage.equals("ordinaire")) {
					opCodeImportation.setMontantOp("40000");
				} else if (typeGage.equals("exceptionnelle")) {
					opCodeImportation.setMontantOp("50000");
				}

				opCodeImportation.setTypeOp("Attribution");
				opCodeImportation.setActiveApprobationOp("inactif");
				opCodeImportation.setActivePaimentOp(0);
				opCodeImportation.setCodeImportation(codeImportationSave);

				opCodeImportation.setActiveSignatureOp("non");
				opCodeImportation.setTypeCodeOp(category);
				opCodeImportation.setNumDocOp(numDossier);
				opCodeImportation.setDateOp(date);
				opCodeImportation.setUser(user);
				opCodeImportationService.saveOpCodeImportation(opCodeImportation);

			}
		}

		modelMap.addAttribute("numDossiers", numDossier);
		modelMap.addAttribute("dateDuJour", dateDuJour);
		if (errorsList.size() != 0) {

			if (codeImportation.getDateMiseCirculationGag() != null) {
				Date dategag = codeImportation.getDateMiseCirculationGag();
				SimpleDateFormat formatGag = new SimpleDateFormat("yyyy-MM-dd");
				String dateDuJours = formatGag.format(new Date());
				LocalDate dates = LocalDate.parse(formatGag.format(dategag), DateTimeFormatter.ISO_LOCAL_DATE);
				LocalDate dJour = LocalDate.parse(dateDuJours, DateTimeFormatter.ISO_LOCAL_DATE);

				Period diffDate = Period.between(dJour, dates);
				int jours = Math.abs(diffDate.getDays());
				int mois = Math.abs(diffDate.getMonths());
				int years = Math.abs(diffDate.getYears());
				modelMap.addAttribute("jours", jours);
				modelMap.addAttribute("mois", mois);
				modelMap.addAttribute("years", years);
			}
			String pageselect = "confirmerDossierMoral";

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
			modelMap.addAttribute("verifBeneficiaire", beneficiaire);

			if (demandeur.getNomDem() != null && demandeur.getPrenomsDem() != null) {
				pageselect = "confirmerDossier";
			}

			modelMap.addAttribute("errorsList", errorsList);
			// return "./errorsPage";
			return "./" + category + "/" + pageselect;
		}
		return "./" + category + "/succes";
	}

	// ZONE RENOUVELLEMENT
	@RequestMapping(value = { "/{category}/Renouvellement" })
	public String renouvellementCode(@PathVariable("category") String category, String codeImportExportEntr,
			ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Renouvellement")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}

		String dossierAfficher = "renouvellementDossier";
		if (codeImportExportEntr != null
				&& (codeImportExportEntr.length() == 10 || codeImportExportEntr.length() == 11)) {
			Entreprise entr = entrepriseService.findByCodeImportExportEntrAndContribuableEntr(codeImportExportEntr);
			Proprietaire props = entr.getProprietaires();
			List<OpCodeImportation> fgg = opCodeImportationService
					.findCodeImportationByTypecodeAndByCodeRccmOrCc(codeImportExportEntr, category, site);
			modelMap.addAttribute("infoEntreprise", entr);
			modelMap.addAttribute("infoProprietaire", props);
			modelMap.addAttribute("listeCode", fgg);
		}
		if (codeImportExportEntr != null && codeImportExportEntr.length() == 9) {
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

			modelMap.addAttribute("numDossiers", codeImportExportEntr.toUpperCase());
			modelMap.addAttribute("dateDuJour", dateDuJour);
			dossierAfficher = "reNewDossierMoral";
		}

		
		if (validate.equals("oui")) {
			return "./" + category + "/" + dossierAfficher;
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/Renouveller/{idEntr}")
	public String coderenouveller(@PathVariable("category") String category, @PathVariable("idEntr") Long idEntr,
			@ModelAttribute("codeImportation") CodeImportation codeImportation,
			@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation, ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Renouvellement")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		String codesFiscals = "";
		Date date = new Date();

		// calcul du dernier dossier
		Integer numDossier = 1;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if (elmt != null) {
			numDossier = 1 + elmt.getNumDocOp();
		}
		Entreprise entreprise = entrepriseService.getEntrepriseById(idEntr);

		if (entreprise.getExoregcomEntr().equals("non") && !entreprise.getRegcommerceEntr().isEmpty()
				&& !entreprise.getContribuableEntr().isEmpty()) {
			codesFiscals = CalculeCodesExportation.getCodeFixcal(1, numDossier);
		} else if (entreprise.getExoregcomEntr().equals("oui") && entreprise.getRegcommerceEntr().isEmpty()
				&& !entreprise.getContribuableEntr().isEmpty()) {
			codesFiscals = CalculeCodesExportation.getCodeFixcal(0, numDossier);
		}

		if (!codesFiscals.isEmpty()) {
			codeImportation.setEntreprise(entreprise);
			codeImportation.setNumCodFic(codesFiscals);
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
		}
	
		if (validate.equals("oui")) {
			return "redirect:../../" + category + "/Liste";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/Renouveler/Ancien")
	public String RenouvelleAncCodeEntreprise(@PathVariable("category") String category,
			@ModelAttribute("entreprise") Entreprise entreprise,
			@ModelAttribute("proprietaire") Proprietaire proprietaire,
			@ModelAttribute("codeImportation") CodeImportation codeImportation,
			@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation,
			@ModelAttribute("demandeur") Demandeur demandeur, @ModelAttribute("beneficiaire") Beneficiaire beneficiaire,
			ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Renouvellement")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		errorsList.clear();
		// calcul du dernier dossier
		Integer numDossier = 1001001;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if (elmt != null) {
			numDossier = 1 + elmt.getNumDocOp();
		}
		Date date = new Date();
		String codeStruc = "";
		if (category.equals("CodeImportExport")) {
			String codesExportation = "";
			String codesFiscals = "";

			EntrepriseValidator.validate(entreprise).forEach(error -> {
				errorsList.add(error);
			});
			ProprietaireValidator.validate(proprietaire).forEach(error -> {
				errorsList.add(error);
			});
			if (errorsList.size() == 0) {
				if (entreprise.getExoregcomEntr().equals("non") && !entreprise.getRegcommerceEntr().isEmpty()
						&& !entreprise.getContribuableEntr().isEmpty()) {
					codesExportation = CalculeCodesExportation.getCodeImportExport(entreprise.getRegcommerceEntr(),
							entreprise.getContribuableEntr(), numDossier);
					codesFiscals = CalculeCodesExportation.getCodeFixcal(1, numDossier);
				} else if (entreprise.getExoregcomEntr().equals("oui") && entreprise.getRegcommerceEntr().isEmpty()
						&& !entreprise.getContribuableEntr().isEmpty()) {
					codesExportation = CalculeCodesExportation
							.getCodeImportExportWithOutRCCM(entreprise.getContribuableEntr(), numDossier);
					codesFiscals = CalculeCodesExportation.getCodeFixcal(0, numDossier);
				}
				if (!codesExportation.isEmpty() && !codesFiscals.isEmpty()) {
					Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);
					entreprise.setProprietaires(saveProprietaire);
					entreprise.setCodeIdexEntr(
							CalculeCodesIdex.getCodeCodeIdex(entreprise.getContribuableEntr(), "", numDossier));
					entreprise.setDateEntr(date);
					entreprise.setCodeImportExportEntr(codesExportation);

					Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);

					codeImportation.setEntreprise(saveEntreprise);
					codeImportation.setNumCodFic(codesFiscals);
					codeImportation.setStatutDemandeurCodeImp("oui");
					CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);

					opCodeImportation.setMontantOp("30000");
					opCodeImportation.setCodeImportation(codeImportationSave);
					opCodeImportation.setActiveApprobationOp("inactif");
					opCodeImportation.setActivePaimentOp(0);
				} else {
					System.out.println("Une erreur s'est produite pendant l'enregistrement");
				}
			}

		}
		if (category.equals("CodeOccasionnel")) {

			if (demandeur.getNomDem() != null) {
				DemandeurValidator.validate(demandeur).forEach(error -> {
					errorsList.add(error);
				});
				if (errorsList.size() == 0) {
					demandeur.setQuotaOccaDem(1);
					demandeur.setCodeIdexDem(CalculeCodesIdex.getCodeCodeIdex("", demandeur.getNomDem(), numDossier));
					Demandeur saveDemandeur = demandeurService.saveDemandeur(demandeur);
					codeImportation.setDemandeur(saveDemandeur);
					codeImportation.setStatutDemandeurCodeImp("non");
					codeStruc = "42000A";
				}
			}

			if (proprietaire.getNomProp() != null && entreprise.getNomEntr() != null) {
				EntrepriseValidator.validate(entreprise).forEach(error -> {
					errorsList.add(error);
				});
				ProprietaireValidator.validate(proprietaire).forEach(error -> {
					errorsList.add(error);
				});
				if (errorsList.size() == 0) {
					TypeStructure typeStructures = typeStructureService
							.getTypeStructureById(entreprise.getTypeStructure().getIdStruc());
					codeStruc = typeStructures.getCodeStruc();
					Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);
					entreprise.setProprietaires(saveProprietaire);
					entreprise.setCodeIdexEntr(
							CalculeCodesIdex.getCodeCodeIdex(entreprise.getContribuableEntr(), "", numDossier));
					entreprise.setDateEntr(date);
					entreprise.setQuotaOccaEntr(1);

					Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);
					codeImportation.setEntreprise(saveEntreprise);
					codeImportation.setStatutDemandeurCodeImp("oui");
				}
			}
			if (errorsList.size() == 0) {
				String codesOccasionnel = CalculeCodesExportation.getCodeOccasionnel(codeStruc, numDossier);

				codeImportation.setNumOcca(codesOccasionnel);
				CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);
				if (codeStruc.equals("42000A")) {
					opCodeImportation.setMontantOp("50000");
					opCodeImportation.setActiveApprobationOp("inactif");
					opCodeImportation.setActivePaimentOp(0);
				} else {
					opCodeImportation.setMontantOp("0");
					opCodeImportation.setActivePaimentOp(1);
					opCodeImportation.setActiveApprobationOp("inactif");
				}

				opCodeImportation.setCodeImportation(codeImportationSave);
			}

		}
		if (category.equals("LeveeDeGage")) {
			LeveeGageValidator.validate(codeImportation).forEach(error -> {
				errorsList.add(error);
			});
			String typeGage = "";
			String codesLege = "";
			if (errorsList.size() == 0) {
				Date dategag = codeImportation.getDateGag();

				SimpleDateFormat formatGag = new SimpleDateFormat("yyyy-MM-dd");
				String dateDuJours = formatGag.format(new Date());
				LocalDate dates = LocalDate.parse(formatGag.format(dategag), DateTimeFormatter.ISO_LOCAL_DATE);
				LocalDate dJour = LocalDate.parse(dateDuJours, DateTimeFormatter.ISO_LOCAL_DATE);

				Period diffDate = Period.between(dJour, dates);
				int years = Math.abs(diffDate.getYears());
				int mois = Math.abs(diffDate.getMonths());

				if ((years == 2 && mois > 0) || (years > 2)) {
					typeGage = "ordinaire";
				} else if ((years == 2 && mois == 0) || (years < 2)) {
					typeGage = "exceptionnelle";
				}

				codesLege = CalculeCodesExportation.getLeveeGage(codeImportation.getUsageGag(),
						codeImportation.getNumChassisGag(), typeGage, numDossier);
			}
			if (!codesLege.isEmpty()) {
				if (demandeur.getNomDem() != null) {
					DemandeurValidator.validate(demandeur).forEach(error -> {
						errorsList.add(error);
					});
					if (errorsList.size() == 0) {
						demandeur.setCodeIdexDem(
								CalculeCodesIdex.getCodeCodeIdex("", demandeur.getNomDem(), numDossier));
						Demandeur saveDemandeur = demandeurService.saveDemandeur(demandeur);
						codeImportation.setDemandeur(saveDemandeur);
						codeImportation.setStatutDemandeurCodeImp("non");
					}
				}

				if (proprietaire.getNomProp() != null && entreprise.getNomEntr() != null) {
					EntrepriseValidator.validate(entreprise).forEach(error -> {
						errorsList.add(error);
					});
					ProprietaireValidator.validate(proprietaire).forEach(error -> {
						errorsList.add(error);
					});
					if (errorsList.size() == 0) {
						Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);

						entreprise.setProprietaires(saveProprietaire);
						entreprise.setCodeIdexEntr(
								CalculeCodesIdex.getCodeCodeIdex(entreprise.getContribuableEntr(), "", numDossier));
						entreprise.setDateEntr(date);

						Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);
						codeImportation.setEntreprise(saveEntreprise);
						codeImportation.setStatutDemandeurCodeImp("oui");
					}
				}

				if (beneficiaire.getNomBen() != null) {
					BeneficiaireValidator.validate(beneficiaire).forEach(error -> {
						errorsList.add(error);
					});
					if (errorsList.size() == 0) {
						Beneficiaire saveBeneficiaire = beneficiaireService.saveBeneficiaire(beneficiaire);
						codeImportation.setBeneficiaire(saveBeneficiaire);
					}
				}
				if (errorsList.size() == 0) {
					codeImportation.setNumGag(codesLege);
					codeImportation.setTypeGag(typeGage);
					CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);

					if (typeGage.equals("ordinaire")) {
						opCodeImportation.setMontantOp("40000");
					} else if (typeGage.equals("exceptionnelle")) {
						opCodeImportation.setMontantOp("50000");
					}

					opCodeImportation.setActiveApprobationOp("inactif");
					opCodeImportation.setActivePaimentOp(0);
					opCodeImportation.setCodeImportation(codeImportationSave);
				}
			}
		}
		if (errorsList.size() == 0) {
			opCodeImportation.setTypeOp("Renouvellement");
			opCodeImportation.setActiveSignatureOp("non");
			opCodeImportation.setTypeCodeOp(category);
			opCodeImportation.setNumDocOp(numDossier);
			opCodeImportation.setDateOp(date);
			opCodeImportation.setUser(user);
			opCodeImportationService.saveOpCodeImportation(opCodeImportation);
		}
		modelMap.addAttribute("numDossiers", numDossier);
		modelMap.addAttribute("dateDuJour", dateDuJour);

		if (errorsList.size() != 0) {
			if (codeImportation.getDateMiseCirculationGag() != null) {
				Date dategag = codeImportation.getDateMiseCirculationGag();
				SimpleDateFormat formatGag = new SimpleDateFormat("yyyy-MM-dd");
				String dateDuJours = formatGag.format(new Date());
				LocalDate dates = LocalDate.parse(formatGag.format(dategag), DateTimeFormatter.ISO_LOCAL_DATE);
				LocalDate dJour = LocalDate.parse(dateDuJours, DateTimeFormatter.ISO_LOCAL_DATE);

				Period diffDate = Period.between(dJour, dates);
				int jours = Math.abs(diffDate.getDays());
				int mois = Math.abs(diffDate.getMonths());
				int years = Math.abs(diffDate.getYears());
				modelMap.addAttribute("jours", jours);
				modelMap.addAttribute("mois", mois);
				modelMap.addAttribute("years", years);
			}
			String pageselect = "confirmerDossierMoral";

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
			modelMap.addAttribute("verifBeneficiaire", beneficiaire);

			if (demandeur.getNomDem() != null && demandeur.getPrenomsDem() != null) {
				pageselect = "confirmerDossier";
			}

			modelMap.addAttribute("errorsList", errorsList);
			// return "./errorsPage";
			return "./" + category + "/" + pageselect;
		}

		return "./" + category + "/succes";

	}

	Entreprise infosEntr = new Entreprise();
	Proprietaire infosProp = new Proprietaire();
	Demandeur infosDem = new Demandeur();
	Beneficiaire infosBen = new Beneficiaire();

	// ZONE REATTRIBUTION
	@RequestMapping("/{category}/ReAttribution")
	public String reattributionCode(@PathVariable("category") String category, String codeDem, ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/ReAttribution")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		List<OpCodeImportation> listecodeImp = new ArrayList<>();
		Integer quotaOcca = 0;
		String statuts = "";
		if (codeDem != null && !codeDem.isEmpty()) {
			listecodeImp = opCodeImportationService.findAllCodeImportationByCodeOccaOrCodeLeveeGageEntr(codeDem, site);
			if (listecodeImp.size() != 0) {
				listecodeImp.forEach(listes -> {
					infosEntr = listes.getCodeImportation().getEntreprise();
					infosBen = listes.getCodeImportation().getBeneficiaire();
				});
				statuts = "entr";
				quotaOcca = infosEntr.getQuotaOccaEntr();
				modelMap.addAttribute("listeCode", listecodeImp);
				modelMap.addAttribute("infosEntr", infosEntr);
				modelMap.addAttribute("infosBen", infosBen);
			} else {
				listecodeImp = opCodeImportationService.findAllCodeImportationByCodeOccaOrCodeLeveeGageDem(codeDem,
						site);
				listecodeImp.forEach(listes -> {
					infosDem = listes.getCodeImportation().getDemandeur();
					infosBen = listes.getCodeImportation().getBeneficiaire();
				});
				statuts = "dem";
				quotaOcca = infosDem.getQuotaOccaDem();
				modelMap.addAttribute("listeCode", listecodeImp);
				modelMap.addAttribute("infosDem", infosDem);
				modelMap.addAttribute("infosBen", infosBen);
			}
		}
		List<Nationalite> paysOrigine = natService.getAllNationalite();
		List<TypePieceIdentite> typePieceIdentite = typePieceIdentiteService.getAllTypePieceIdentite();
		List<TypeStructure> typeStructure = typeStructureService.getAllTypeStructure();
		List<Fonction> fonction = fonctionService.getAllFonction();
		List<Marque> marques = marqueService.getAllMarque();
		List<GenreMarque> genreMarques = genreMarqueService.getAllGenreMarque();

		modelMap.addAttribute("listetypePieceIdentite", typePieceIdentite);
		modelMap.addAttribute("listefonction", fonction);
		modelMap.addAttribute("listemarques", marques);
		modelMap.addAttribute("listegenreMarques", genreMarques);
		modelMap.addAttribute("listetypeStructure", typeStructure);
		modelMap.addAttribute("listeNationalites", paysOrigine);
		modelMap.addAttribute("quotaOcca", quotaOcca);
		modelMap.addAttribute("statuts", statuts);

		
		if (validate.equals("oui")) {
			return "./" + category + "/reattributionDossier";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/valider/ReAttribution")
	public String reattributionCode(@PathVariable("category") String category,
			@ModelAttribute("codeImportation") CodeImportation codeImportation,
			@ModelAttribute("beneficiaire") Beneficiaire beneficiaire, Long codeEntr, Long codeDem, ModelMap modelMap) {
		String codeStruc = "";

		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/ReAttribution")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		OpCodeImportation oPCodeImportations = new OpCodeImportation();
		// calcul du dernier dossier
		Integer numDossier = 1001001;
		OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
		if (elmt != null) {
			numDossier = 1 + elmt.getNumDocOp();
		}
		if (category.equals("CodeOccasionnel")) {
			if (codeEntr != null && codeEntr.SIZE != 0 && codeDem == null) {
				Entreprise entrFound = entrepriseService.getEntrepriseById(codeEntr);
				if (entrFound != null) {
					entrFound.setQuotaOccaEntr(entrFound.getQuotaOccaEntr() + 1);
					entrepriseService.saveEntreprise(entrFound);
					codeStruc = entrFound.getTypeStructure().getCodeStruc();
					codeImportation.setEntreprise(entrFound);
					codeImportation.setStatutDemandeurCodeImp("oui");
					oPCodeImportations.setMontantOp("0");
					oPCodeImportations.setActivePaimentOp(1);
					oPCodeImportations.setActiveApprobationOp("inactif");
				}

			} else if (codeEntr == null && codeDem != null && codeDem.SIZE != 0) {
				Demandeur demFound = demandeurService.getDemandeurById(codeDem);
				if (demFound != null) {
					demFound.setQuotaOccaDem(demFound.getQuotaOccaDem() + 1);
					demandeurService.saveDemandeur(demFound);
					codeImportation.setDemandeur(demFound);
					codeImportation.setStatutDemandeurCodeImp("non");
					codeStruc = "42000A";
					oPCodeImportations.setMontantOp("50000");
					oPCodeImportations.setActivePaimentOp(0);
					oPCodeImportations.setActiveApprobationOp("inactif");
				}
			}

			if (codeEntr != null || codeDem != null) {
				String codesOccasionnel = CalculeCodesExportation.getCodeOccasionnel(codeStruc, numDossier);
				codeImportation.setNumOcca(codesOccasionnel);
				CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);

				oPCodeImportations.setTypeOp("Attribution");
				oPCodeImportations.setCodeImportation(codeImportationSave);

				oPCodeImportations.setActiveSignatureOp("non");
				oPCodeImportations.setTypeCodeOp(category);
				oPCodeImportations.setNumDocOp(numDossier);
				oPCodeImportations.setDateOp(new Date());
				oPCodeImportations.setUser(user);
				opCodeImportationService.saveOpCodeImportation(oPCodeImportations);
			}
		} else if (category.equals("LeveeDeGage")) {
			Date dategag = codeImportation.getDateGag();

			SimpleDateFormat formatGag = new SimpleDateFormat("yyyy-MM-dd");
			String dateDuJours = formatGag.format(new Date());
			LocalDate dategags = LocalDate.parse(formatGag.format(dategag), DateTimeFormatter.ISO_LOCAL_DATE);
			LocalDate dJour = LocalDate.parse(dateDuJours, DateTimeFormatter.ISO_LOCAL_DATE);

			Period diffDate = Period.between(dJour, dategags);
			int years = Math.abs(diffDate.getYears());
			int mois = Math.abs(diffDate.getMonths());

			String typeGage = "";

			if (codeEntr != null && codeEntr.SIZE != 0 && codeDem == null) {
				Entreprise entrFound = entrepriseService.getEntrepriseById(codeEntr);
				if (entrFound != null) {
					codeStruc = entrFound.getTypeStructure().getCodeStruc();
					codeImportation.setEntreprise(entrFound);
					codeImportation.setStatutDemandeurCodeImp("oui");
				}

			} else if (codeEntr == null && codeDem != null && codeDem.SIZE != 0) {
				Demandeur demFound = demandeurService.getDemandeurById(codeDem);
				if (demFound != null) {
					codeImportation.setDemandeur(demFound);
					codeImportation.setStatutDemandeurCodeImp("non");
				}
			}

			if (codeEntr != null || codeDem != null) {
				if ((years == 2 && mois > 0) || (years > 2)) {
					typeGage = "ordinaire";
				} else if ((years == 2 && mois == 0) || (years < 2)) {
					typeGage = "exceptionnelle";
				}

				String codesLege = CalculeCodesExportation.getLeveeGage(codeImportation.getUsageGag(),
						codeImportation.getNumChassisGag(), typeGage, numDossier);
				codeImportation.setNumGag(codesLege);

				if (codeImportation.getUsageGag().equals("commercial")) {
					Beneficiaire saveBeneficiaire = beneficiaireService.saveBeneficiaire(beneficiaire);
					codeImportation.setBeneficiaire(saveBeneficiaire);
				}

				codeImportation.setNumGag(codesLege);
				codeImportation.setTypeGag(typeGage);
				CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);

				if (typeGage.equals("ordinaire")) {
					oPCodeImportations.setMontantOp("40000");
				} else if (typeGage.equals("exceptionnelle")) {
					oPCodeImportations.setMontantOp("50000");
				}

				oPCodeImportations.setCodeImportation(codeImportationSave);
				oPCodeImportations.setTypeOp("Attribution");
				oPCodeImportations.setActiveApprobationOp("inactif");
				oPCodeImportations.setActivePaimentOp(0);
				oPCodeImportations.setActiveSignatureOp("non");

				oPCodeImportations.setTypeCodeOp(category);
				oPCodeImportations.setNumDocOp(numDossier);
				oPCodeImportations.setDateOp(new Date());
				oPCodeImportations.setUser(user);
				opCodeImportationService.saveOpCodeImportation(oPCodeImportations);
			}
		}
		
		if (validate.equals("oui")) {
			return "redirect:../../" + category + "/Liste";
		} else {
			return "./accessDenied";
		}
	}

	// ZONE DUPLICATA
	@RequestMapping("/{category}/Duplicata")
	public String duplicataCode(@PathVariable("category") String category, String codeImportExportEntr,
			ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Duplicata")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}

		List<OpCodeImportation> fgg = new ArrayList<>();

		if (codeImportExportEntr != null) {
			if (category.equals("CodeImportExport")) {
				Entreprise entr = entrepriseService.findByCodeImportExportEntrAndContribuableEntr(codeImportExportEntr);
				if (entr != null) {
					Proprietaire props = entr.getProprietaires();
					fgg = opCodeImportationService.findCodeImportationByTypecodeAndByCodeRccmOrCc(codeImportExportEntr,
							category, site);
					modelMap.addAttribute("infoEntreprise", entr);
					modelMap.addAttribute("infoProprietaire", props);
					modelMap.addAttribute("listeCode", fgg);
				} else {
					String errors = "Aucun compte trouvé";
					modelMap.addAttribute("msgErrors", errors);
				}

			} else if (category.equals("CodeOccasionnel") || category.equals("LeveeDeGage")) {
				fgg = opCodeImportationService.findAllCodeImportationByCodeOccaOrCodeLeveeGageEntr(codeImportExportEntr,
						site);
				if (fgg.size() == 0) {
					fgg = opCodeImportationService
							.findAllCodeImportationByCodeOccaOrCodeLeveeGageDem(codeImportExportEntr, site);

				}
				modelMap.addAttribute("listeCode", fgg);
			}

		}
		
		if (validate.equals("oui")) {
			return "./" + category + "/duplicataDossier";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/Dupliquer/{id}")
	public String codeDupliquer(@PathVariable("category") String category, @PathVariable("id") Long id,
			@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation, ModelMap modelMap) {

		OpCodeImportation opCodeImportationFind = opCodeImportationService.getOpCodeImportationById(id);
		if (opCodeImportationFind != null && opCodeImportationFind.getActiveApprobationOp().equals("actif")) {

			CodeImportation codeImportation = opCodeImportationFind.getCodeImportation();
			Date date = new Date();

			// User connecté
			String username = GetCurrentUser.getUserConnected();
			User user = userRepository.findByUsername(username);
			ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
			List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
			modelMap.addAttribute("listeUrlUser", listeUrlUser);

			// calcul du dernier dossier
			Integer numDossier = 1;
			OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
			if (elmt != null) {
				numDossier = 1 + elmt.getNumDocOp();
			}

			opCodeImportation.setMontantOp(opCodeImportationFind.getMontantOp());
			opCodeImportation.setTypeOp("Duplicata");
			if (opCodeImportationFind.getCodeImportation().getEntreprise().getTypeStructure().getCodeStruc()
					.equals("42000A")) {
				opCodeImportation.setActiveApprobationOp("inactif");
				opCodeImportation.setActivePaimentOp(0);
			} else {
				opCodeImportation.setActiveApprobationOp("actif");
				opCodeImportation.setActivePaimentOp(1);
			}
			opCodeImportation.setTypeCodeOp(category);
			opCodeImportation.setNumDocOp(numDossier);
			opCodeImportation.setDateOp(date);
			opCodeImportation.setUser(user);
			opCodeImportation.setCodeImportation(codeImportation);
			opCodeImportationService.saveOpCodeImportation(opCodeImportation);
			opCodeImportationFind.setActiveApprobationOp("desactiver");
			opCodeImportationService.saveOpCodeImportation(opCodeImportationFind);
		} else {
			String errors = "Aucun compte n'est actif";
			System.out.print(errors);
			modelMap.addAttribute("msgErrors", errors);
		}

		return "redirect:../../" + category + "/Liste";
	}

	// ZONNE PAIEMENT

	@RequestMapping("/{category}/Paiement")
	public String rechercheCode(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap) {

		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Paiement")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		if (numDoc != null) {
			if(username.equals("superadmin")) {
				OpCodeImportation codeFic = opCodeImportationService.findBynumDocOpSuper(numDoc, category);
				modelMap.addAttribute("infoCodefic", codeFic);
			}else {
				OpCodeImportation codeFic = opCodeImportationService.findBynumDocOp(numDoc, category, site);
				modelMap.addAttribute("infoCodefic", codeFic);
			}
		}
		if(username.equals("superadmin")) {
			List<OpCodeImportation> cods = opCodeImportationService.findOpCodeImportationsByTypeCodeOp(category);
			modelMap.addAttribute("listeCodes", cods);
		}else {
			List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category, site);
			modelMap.addAttribute("listeCodes", cods);
		}
	
		if (validate.equals("oui")) {
			return "./" + category + "/listePaiement";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/Payer")
	public String validerReglement(@PathVariable("category") String category, Integer numDoc,
			@ModelAttribute("traitementOpCodeImportation") TraitementOpCodeImportation traitementOpCodeImportation,
			ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Paiement")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		OpCodeImportation opcode = opCodeImportationService.findBynumDocOp(numDoc, category, site);
		if (opcode != null) {
			TraitementOpCodeImportation tOpCodeImport = traitementOpCodeImportationService
					.findTraitementOpCodeImportationByStatut(numDoc, "paiement", site);
			if (tOpCodeImport == null) {
				traitementOpCodeImportation.setStatutTrait("paiement");
				traitementOpCodeImportation.setOpCodeImportation(opcode);
				traitementOpCodeImportation.setDateTrait(new Date());
				traitementOpCodeImportation.setUser(user);
				traitementOpCodeImportationService.saveTraitementOpCodeImportation(traitementOpCodeImportation);
				opcode.setActivePaimentOp(1);
				opCodeImportationService.saveOpCodeImportation(opcode);
			}
		}
	
		if(username.equals("superadmin")) {
			List<OpCodeImportation> cods = opCodeImportationService.findOpCodeImportationsByTypeCodeOp(category);
			modelMap.addAttribute("listeCodes", cods);
		}else {
			List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category, site);
			modelMap.addAttribute("listeCodes", cods);
		}
		modelMap.addAttribute("infoCodefic", opcode);

		if (validate.equals("oui")) {
			return "./" + category + "/listePaiement";
		} else {
			return "./accessDenied";
		}
	}

	// ZONNE APPROBATION

	@RequestMapping("/{category}/Approbation")
	public String rechApprobationCode(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Approbation")) {
				validate = "oui";
			}
		});

		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		if (numDoc != null) {
			if(username.equals("superadmin")) {
				OpCodeImportation codeFic = opCodeImportationService.findBynumDocOpSuper(numDoc, category);
				modelMap.addAttribute("infoCodefic", codeFic);
			}else {
				OpCodeImportation codeFic = opCodeImportationService.findBynumDocOp(numDoc, category, site);
				modelMap.addAttribute("infoCodefic", codeFic);
			}
		}
		if(username.equals("superadmin")) {
			List<OpCodeImportation> cods = opCodeImportationService.findOpCodeImportationsByTypeCodeOp(category);
			modelMap.addAttribute("listeCodes", cods);
		}else {
			List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category, site);
			modelMap.addAttribute("listeCodes", cods);
		}

		if (validate.equals("oui")) {
			return "./" + category + "/listeApprobation";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/Approuver")
	public String validerApprobation(@PathVariable("category") String category, Integer numDoc,
			@ModelAttribute("traitementOpCodeImportation") TraitementOpCodeImportation traitementOpCodeImportation,
			ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Approbation")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}

		OpCodeImportation opcodeFic = opCodeImportationService.findBynumDocOp(numDoc, category, site);
		if (opcodeFic != null) {
			TraitementOpCodeImportation tOpCodeImport = traitementOpCodeImportationService
					.findTraitementOpCodeImportationByStatut(numDoc, "approbation", site);
			if (tOpCodeImport == null) {
				traitementOpCodeImportation.setStatutTrait("approbation");
				traitementOpCodeImportation.setOpCodeImportation(opcodeFic);
				traitementOpCodeImportation.setDateTrait(new Date());
				traitementOpCodeImportation.setUser(user);
				traitementOpCodeImportationService.saveTraitementOpCodeImportation(traitementOpCodeImportation);
				opcodeFic.setActiveApprobationOp("actif");
				opCodeImportationService.saveOpCodeImportation(opcodeFic);
			}
		}

		if(username.equals("superadmin")) {
			List<OpCodeImportation> cods = opCodeImportationService.findOpCodeImportationsByTypeCodeOp(category);
			modelMap.addAttribute("listeCodes", cods);
		}else {
			List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category, site);
			modelMap.addAttribute("listeCodes", cods);
		}
		modelMap.addAttribute("infoCodefic", opcodeFic);

		if (validate.equals("oui")) {
			return "./" + category + "/listeApprobation";
		} else {
			return "./accessDenied";
		}
	}

	// ZONE SIGNATURE

	@RequestMapping("/{category}/Signature")
	public String rechSignature(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Signature")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}

		if (numDoc != null) {
			if(username.equals("superadmin")) {
				OpCodeImportation codeFic = opCodeImportationService.findBynumDocOpSuper(numDoc, category);
				modelMap.addAttribute("infoCodefic", codeFic);
			}else {
				OpCodeImportation codeFic = opCodeImportationService.findBynumDocOp(numDoc, category, site);
				modelMap.addAttribute("infoCodefic", codeFic);
			}
		}
		if(username.equals("superadmin")) {
			List<OpCodeImportation> cods = opCodeImportationService.findOpCodeImportationsByTypeCodeOp(category);
			modelMap.addAttribute("listeCodes", cods);
		}else {
			List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category, site);
			modelMap.addAttribute("listeCodes", cods);
		}

		if (validate.equals("oui")) {
			return "./" + category + "/listeSignature";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/Signer")
	public String validerSignature(@PathVariable("category") String category, Integer numDoc,
			@ModelAttribute("traitementOpCodeImportation") TraitementOpCodeImportation traitementOpCodeImportation,
			ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/Signature")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		OpCodeImportation opcodeFic = opCodeImportationService.findBynumDocOp(numDoc, category, site);
		if (opcodeFic != null) {
			TraitementOpCodeImportation tOpCodeImport = traitementOpCodeImportationService
					.findTraitementOpCodeImportationByStatut(numDoc, "signature", site);
			if (tOpCodeImport == null) {
				traitementOpCodeImportation.setStatutTrait("signature");
				traitementOpCodeImportation.setOpCodeImportation(opcodeFic);
				traitementOpCodeImportation.setDateTrait(new Date());
				traitementOpCodeImportation.setUser(user);
				traitementOpCodeImportationService.saveTraitementOpCodeImportation(traitementOpCodeImportation);
				opcodeFic.setActiveSignatureOp("oui");
				opCodeImportationService.saveOpCodeImportation(opcodeFic);
			}
		}

		if(username.equals("superadmin")) {
			List<OpCodeImportation> cods = opCodeImportationService.findOpCodeImportationsByTypeCodeOp(category);
			modelMap.addAttribute("listeCodes", cods);
		}else {
			List<OpCodeImportation> cods = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category, site);
			modelMap.addAttribute("listeCodes", cods);
		}
		modelMap.addAttribute("infoCodefic", opcodeFic);

		if (validate.equals("oui")) {
			return "./" + category + "/listeSignature";
		} else {
			return "./accessDenied";
		}
	}

	// ZONE ETAT CODE
	@RequestMapping("/{category}/listeEtatCodes")
	public String ListeEtatCodes(@PathVariable("category") String category, Long numDoc, String statut,
			ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/listeEtatCodes")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}

		if (numDoc != null && statut != null) {

			if (statut.equals("actif") || statut.equals("bloquer") || statut.equals("annuler")) {
				TraitementOpCodeImportation elmts = traitementOpCodeImportationService
						.getTraitementOpCodeImportationById(numDoc);
				if (elmts != null) {
					elmts.setDateTrait(new Date());
					elmts.setUser(user);
					traitementOpCodeImportationService.saveTraitementOpCodeImportation(elmts);

					OpCodeImportation opCodeImportation = elmts.getOpCodeImportation();
					opCodeImportation.setActiveApprobationOp(statut);
					opCodeImportationService.saveOpCodeImportation(opCodeImportation);
				}
			}
		}
		
		if(username.equals("superadmin")) {
			List<TraitementOpCodeImportation> listecode = traitementOpCodeImportationService
					.findAllTraitementOpCodeImportationByTypeCodeOpSuper(category);
			modelMap.addAttribute("listecodes", listecode);
		}else {
			List<TraitementOpCodeImportation> listecode = traitementOpCodeImportationService
					.findAllTraitementOpCodeImportationByTypeCodeOp(category, site);
			modelMap.addAttribute("listecodes", listecode);
		}

		if (validate.equals("oui")) {
			return "./" + category + "/listeEtatCode";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/ListeEtatCode")
	public String rechListeEtatCode(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/ListeEtatCode")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}

		if (numDoc != null) {
			if(username.equals("superadmin")) {
				List<TraitementOpCodeImportation> codeimp = opCodeImportationService.findBynumDocOpSuper(numDoc, category)
						.getTraitementOpCodeImportation();
				if (codeimp != null) {
					modelMap.addAttribute("listecodes", codeimp);
				}
			}else {
				List<TraitementOpCodeImportation> codeimp = opCodeImportationService.findBynumDocOp(numDoc, category, site)
						.getTraitementOpCodeImportation();
				if (codeimp != null) {
					modelMap.addAttribute("listecodes", codeimp);
				}
			}
		}

		if (validate.equals("oui")) {
			return "./" + category + "/listeEtatCode";
		} else {
			return "./accessDenied";
		}
	}

	@RequestMapping("/{category}/EditionFiches")
	public String gageEditerFiches(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap) {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		validate = "non";
		listeUrlUser.forEach(liens -> {
			if (liens.getLienActPro().equals(category + "/EditionFiches")) {
				validate = "oui";
			}
		});
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		if (numDoc != null) {
			if(username.equals("superadmin")) {
				TraitementOpCodeImportation codes = traitementOpCodeImportationService
						.findTraitementOpCodeImportationByStatutSuper(numDoc, "signature");
				if (codes != null) {
					modelMap.addAttribute("listecodes", codes);
				}
				
			}else {
				TraitementOpCodeImportation codes = traitementOpCodeImportationService
						.findTraitementOpCodeImportationByStatut(numDoc, "signature", site);
				if (codes != null) {
					modelMap.addAttribute("listecodes", codes);
				}
			}
		} else {
			if(username.equals("superadmin")) {
				List<TraitementOpCodeImportation> listecode = traitementOpCodeImportationService
						.findAllTraitementOpCodeImportationByTypeCodeOpSuper(category);
				modelMap.addAttribute("listecodes", listecode);
			}else {
				List<TraitementOpCodeImportation> listecode = traitementOpCodeImportationService
						.findAllTraitementOpCodeImportationByTypeCodeOp(category, site);
				modelMap.addAttribute("listecodes", listecode);
			}
		}

		if (validate.equals("oui")) {
			return "./" + category + "/editerFiche";
		} else {
			return "./accessDenied";
		}
	}

	// ZONNE TIRAGE RECU

	@RequestMapping("/{category}/Recu")
	public ResponseEntity<byte[]> generatePdf(@PathVariable("category") String category, Integer numDoc)
			throws JRException, ParseException, IOException {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();

		HttpHeaders headers = new HttpHeaders();
		byte[] data = {};
		SimpleDateFormat formaters = null;
		formaters = new SimpleDateFormat("dd-MM-yy");
		if (numDoc != null) {
			List<TraitementOpCodeImportation> listOpCodes = new ArrayList<>();
			// OpCodeImportation opCodes = opCodeImportationService.findBynumDocOp(numDoc);
			
				if(username.equals("superadmin")) {
					TraitementOpCodeImportation opCodes = traitementOpCodeImportationService
							.findTraitementOpCodeImportationByStatutSuper(numDoc, "paiement");
					listOpCodes.add(opCodes);
				}else {
					TraitementOpCodeImportation opCodes = traitementOpCodeImportationService
							.findTraitementOpCodeImportationByStatut(numDoc, "paiement", site);
					listOpCodes.add(opCodes);
				}
			
			String fichiers = "";
			if (category.equals("CodeImportExport")) {
				fichiers = "recuImportExport";
			} else if (category.equals("CodeOccasionnel")) {
				fichiers = "recuCodeOccasionnel";
			} else if (category.equals("LeveeDeGage")) {
				fichiers = "recuLeveeDeGage";
			}
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOpCodes);
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("templates/pdf/" + fichiers + ".jrxml").getFile());
			JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream(file));
			HashMap<String, Object> map = new HashMap<>();
			JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
			data = JasperExportManager.exportReportToPdf(report);
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fichiers + numDoc + ".pdf");

		}

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

	// ZONNE TIRAGE FICHE DE CODE

	@RequestMapping("/{category}/EditionFiche")
	public ResponseEntity<byte[]> generatePdfCODE(@PathVariable("category") String category, Integer numDoc)
			throws JRException, ParseException, IOException {
		// User connecté
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		String site = user.getSite().getNomSite();

		HttpHeaders headers = new HttpHeaders();
		byte[] data = {};
		SimpleDateFormat formatGag = new SimpleDateFormat("yyyy-MM-dd");
		String dateDuJours = formatGag.format(new Date());

		LocalDate dJour = LocalDate.parse(dateDuJours, DateTimeFormatter.ISO_LOCAL_DATE);

		if (numDoc != null) {
			List<TraitementOpCodeImportation> listOpCodes = new ArrayList<>();
			TraitementOpCodeImportation opCodes = new TraitementOpCodeImportation();
			// OpCodeImportation opCodes = opCodeImportationService.findBynumDocOp(numDoc);
			if(username.equals("superadmin")) {
				opCodes = traitementOpCodeImportationService
						.findTraitementOpCodeImportationByStatutSuper(numDoc, "signature");
				listOpCodes.add(opCodes);
			}else {
				opCodes = traitementOpCodeImportationService
						.findTraitementOpCodeImportationByStatut(numDoc, "signature", site);
				listOpCodes.add(opCodes);
			}
			
			String fichiers = "";
			int mois = 0;
			int years = 0;
			int jour = 0;
			if (category.equals("CodeImportExport")) {
				fichiers = "ficheImportExport";
			} else if (category.equals("CodeOccasionnel")) {
				fichiers = "ficheCodeOccasionnel";
			} else if (category.equals("LeveeDeGage")) {
				LocalDate dates = LocalDate.parse(
						formatGag.format(
								opCodes.getOpCodeImportation().getCodeImportation().getDateMiseCirculationGag()),
						DateTimeFormatter.ISO_LOCAL_DATE);
				Period diffDate = Period.between(dJour, dates);
				years = Math.abs(diffDate.getYears());
				mois = Math.abs(diffDate.getMonths());
				jour = Math.abs(diffDate.getDays());
				fichiers = "ficheLeveeDeGage";
			}

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listOpCodes);
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("templates/pdf/" + fichiers + ".jrxml").getFile());
			JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream(file));
			HashMap<String, Object> map = new HashMap<>();
			if (category.equals("LeveeDeGage")) {
				map.put("Jour", jour);
				map.put("Mois", mois);
				map.put("Annee", years);
			}

			JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
			data = JasperExportManager.exportReportToPdf(report);
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fichiers + numDoc + ".pdf");
		}
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
