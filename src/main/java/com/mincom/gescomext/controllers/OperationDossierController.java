package com.mincom.gescomext.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.config.CalculeCodesExportation;
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

@Controller
public class OperationDossierController {
	
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
	@Autowired
	ActionListeService actionListeService;
	@Autowired
	BeneficiaireService beneficiaireService;
	
	
	//INJECTION DES REPOSITORY
	@Autowired
	EntrepriseRepository entrepriseRepo;
	@Autowired
	UserRepository userRepository;
			
	@RequestMapping("/{category}/Operation/Dossier/Liste")
	public String opDossiers(@PathVariable("category") String category, ModelMap modelMap) throws IOException
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user,category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		
		List<OpCodeImportation> codfs = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category);
		modelMap.addAttribute("listeCode", codfs);
		return "./"+category+"/operationDossier";
	}
	
	@RequestMapping("/{category}/Operation/Rechercher/Dossier")
	public String rechercheOpDossier(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user,category);		
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		
		OpCodeImportation codeElmt = opCodeImportationService.findBynumDocOp(numDoc);
		modelMap.addAttribute("listeCode", codeElmt);
		return "./"+category+"/operationDossier";
	}
	
	@RequestMapping("/{category}/Operation/Update/Dossier/{numDoc}")
	public String rechercheUpDossier(@PathVariable("category") String category,
										@PathVariable("numDoc") Integer numDoc, 
											ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user,category);		
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		
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
		
		Proprietaire verifProp = new Proprietaire();
		Demandeur verifDemandeur = new Demandeur();
		Beneficiaire verifBeneficiaire = new Beneficiaire();
		Entreprise verifEntr = opCodeImportationService.findBynumDocOp(numDoc).getCodeImportation().getEntreprise();
		if(verifEntr!=null) {
			verifProp = opCodeImportationService.findBynumDocOp(numDoc).getCodeImportation().getEntreprise().getProprietaires();
			if(category.equals("LeveeDeGage")) {
				verifBeneficiaire = opCodeImportationService.findBynumDocOp(numDoc).getCodeImportation().getBeneficiaire();
			}
		}else {
			verifDemandeur = opCodeImportationService.findBynumDocOp(numDoc).getCodeImportation().getDemandeur();
		}
		CodeImportation verifCodeImportation = opCodeImportationService.findBynumDocOp(numDoc).getCodeImportation();
		
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
		modelMap.addAttribute("verifEntr", verifEntr);
		modelMap.addAttribute("verifEntreprise", verifEntr);
		modelMap.addAttribute("verifProp", verifProp);
		modelMap.addAttribute("verifProprietaire", verifProp);
		modelMap.addAttribute("verifBeneficiaire", verifBeneficiaire);
		modelMap.addAttribute("verifDemandeur", verifDemandeur);
		modelMap.addAttribute("verifCodeImportation", verifCodeImportation);
		modelMap.addAttribute("numDoc", numDoc);
		return "./"+category+"/updateDossier";
	}
	
	@RequestMapping("/{category}/Operation/Valide/Update")
	public String valideUpDossier(@PathVariable("category") String category,
									@ModelAttribute("entreprise") Entreprise entreprise,
										@ModelAttribute("proprietaire") Proprietaire proprietaire,
											@ModelAttribute("demandeur") Demandeur demandeur,
												@ModelAttribute("codeImportation") CodeImportation codeImportation,
													@ModelAttribute("beneficiaire") Beneficiaire beneficiaire,
														Integer numDossier,
															ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user,category);		
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		//------- GESTION DES ONGLLETS--------
		
		String codesExportation="";
		String codesFiscals="";
		Entreprise verifEntr = new Entreprise();
		Proprietaire verifProp = new Proprietaire();
		Demandeur verifDemandeur = new Demandeur();
		Beneficiaire verifBeneficiaire = new Beneficiaire();
		CodeImportation verifcodeImportation = new CodeImportation();
		
		if(entreprise.getIdEntr()!=null) {
			verifEntr = entrepriseService.getEntrepriseById(entreprise.getIdEntr());		
			verifProp = proprietaireService.getProprietaireById(proprietaire.getIdProp());
			if(category.equals("LeveeDeGage")) {
				verifBeneficiaire = beneficiaireService.getBeneficiaireById(beneficiaire.getIdBen());
			}
		}
		if(demandeur.getIdDem()!=null) {
		verifDemandeur = demandeurService.getDemandeurById(demandeur.getIdDem());
		}
		if(codeImportation!=null) {
		verifcodeImportation = opCodeImportationService.findBynumDocOp(numDossier).getCodeImportation();	
		}
		
		if(verifEntr!=null && verifProp!=null) {
			if(entreprise.getExoregcomEntr()!=null && entreprise.getExoregcomEntr().equals("non") && entreprise.getRegcommerceEntr()!=null && entreprise.getContribuableEntr()!=null){
				codesExportation = CalculeCodesExportation.getCodeImportExport(entreprise.getRegcommerceEntr(), entreprise.getContribuableEntr(), numDossier);
				codesFiscals = CalculeCodesExportation.getCodeFixcal(1,numDossier);
			}else if((entreprise.getExoregcomEntr()!=null && entreprise.getExoregcomEntr().equals("oui") && entreprise.getRegcommerceEntr()==null && entreprise.getContribuableEntr()!=null)
					|| (entreprise.getDepartement() != null && entreprise.getDepartement().getIdDep() !=0 && entreprise.getRegcommerceEntr()==null && entreprise.getContribuableEntr()!=null)){
				codesExportation = CalculeCodesExportation.getCodeImportExportWithOutRCCM(entreprise.getContribuableEntr(), numDossier);
				codesFiscals = CalculeCodesExportation.getCodeFixcal(0,numDossier);
			}
			
			if(!codesExportation.isEmpty()) {
				verifEntr.setCodeImportExportEntr(codesExportation);
			}
			
			if(!codesFiscals.isEmpty()) {			
				verifcodeImportation.setNumCodFic(codesFiscals);
			}
		}
		
		if(verifDemandeur!=null) {
			if(demandeur.getNomDem()!=null) {
				verifDemandeur.setNomDem(demandeur.getNomDem());
			}

			if(demandeur.getPrenomsDem()!=null) {
				verifDemandeur.setPrenomsDem(demandeur.getPrenomsDem());
			}
			
			if(demandeur.getSexeDem()!=null) {
				verifDemandeur.setSexeDem(demandeur.getSexeDem());
			}
			
			if(demandeur.getTypePieceIdentite()!=null) {
				verifDemandeur.setTypePieceIdentite(demandeur.getTypePieceIdentite());
			}
			
			if(demandeur.getValiditePieceDem()!=null) {
				verifDemandeur.setValiditePieceDem(demandeur.getValiditePieceDem());
			}
			
			if(demandeur.getNationalite()!=null) {
				verifDemandeur.setNationalite(demandeur.getNationalite());
			}
			
			if(demandeur.getTelDem()!=null) {
				verifDemandeur.setTelDem(demandeur.getTelDem());
			}
			
			if(demandeur.getEmailDem()!=null) {
				verifDemandeur.setEmailDem(demandeur.getEmailDem());
			}
			demandeurService.saveDemandeur(verifDemandeur);
		}
		if(entreprise!=null) {
			if(entreprise.getNomEntr()!=null) {
				verifEntr.setNomEntr(entreprise.getNomEntr());
			}
			
			if(entreprise.getSigleEntr()!=null) {
				verifEntr.setSigleEntr(entreprise.getSigleEntr());
			}
			
			if(entreprise.getNumIduEntr()!=null) {
				verifEntr.setNumIduEntr(entreprise.getNumIduEntr());
			}
			
			if(entreprise.getDepartement()!=null) {
				verifEntr.setDepartement(entreprise.getDepartement());
			}
			
			if(entreprise.getExoregcomEntr() !=null) {
				verifEntr.setExoregcomEntr(entreprise.getExoregcomEntr());
			}
			
			if(entreprise.getRegcommerceEntr()!=null) {
				verifEntr.setRegcommerceEntr(entreprise.getRegcommerceEntr());
			}
			
			if(entreprise.getCommune()!=null) {
				verifEntr.setCommune(entreprise.getCommune());
			}
			
			if(entreprise.getPostaleEntr()!=null) {
				verifEntr.setPostaleEntr(entreprise.getPostaleEntr());
			}
			
			if(entreprise.getEmailEntr()!=null) {
				verifEntr.setEmailEntr(entreprise.getEmailEntr());
			}
			
			if(entreprise.getFormeJuridique()!=null) {
				verifEntr.setFormeJuridique(entreprise.getFormeJuridique());
			}
			
			if(entreprise.getDomaineActivite()!=null) {
				verifEntr.setDomaineActivite(entreprise.getDomaineActivite());
			}
			
			if(entreprise.getContribuableEntr()!=null) {
				verifEntr.setContribuableEntr(entreprise.getContribuableEntr());
			}
			
			if(proprietaire.getNomProp()!=null) {
				verifProp.setNomProp(proprietaire.getNomProp());
			}
			
			if(proprietaire.getPrenomsProp()!=null) {
				verifProp.setPrenomsProp(proprietaire.getPrenomsProp());
			}
			
			if(proprietaire.getSexeProp()!=null) {
				verifProp.setSexeProp(proprietaire.getSexeProp());
			}
			
			if(proprietaire.getTypePieceIdentite()!=null) {
				verifProp.setTypePieceIdentite(proprietaire.getTypePieceIdentite());
			}
			
			if(proprietaire.getValiditePieceProp()!=null) {
				verifProp.setValiditePieceProp(proprietaire.getValiditePieceProp());
			}
			
			if(proprietaire.getNationalite()!=null) {
				verifProp.setNationalite(proprietaire.getNationalite());
			}
			
			if(proprietaire.getTelProp()!=null) {
				verifProp.setTelProp(proprietaire.getTelProp());
			}
			
			if(proprietaire.getEmailProp()!=null) {
				verifProp.setEmailProp(proprietaire.getEmailProp());
			}
			
			if(category.equals("LeveeDeGage")) {
				if(beneficiaire.getNomBen()!=null) {
					verifBeneficiaire.setEmailBen(beneficiaire.getEmailBen());
				}
				if(beneficiaire.getPrenomsBen()!=null) {
					verifBeneficiaire.setPrenomsBen(beneficiaire.getPrenomsBen());
				}
				if(beneficiaire.getSexeBen()!=null) {
					verifBeneficiaire.setSexeBen(beneficiaire.getSexeBen());
				}
				if(beneficiaire.getNumpieceBen()!=null) {
					verifBeneficiaire.setNumpieceBen(beneficiaire.getNumpieceBen());
				}
				if(beneficiaire.getPieceidentBen()!=null) {
					verifBeneficiaire.setPieceidentBen(beneficiaire.getPieceidentBen());
				}
				if(beneficiaire.getValiditePieceBen()!=null) {
					verifBeneficiaire.setValiditePieceBen(beneficiaire.getValiditePieceBen());
				}
				if(beneficiaire.getNatBeneficiaire()!=null) {
					verifBeneficiaire.setNatBeneficiaire(beneficiaire.getNatBeneficiaire());
				}
				if(beneficiaire.getTelBen()!=null) {
					verifBeneficiaire.setSexeBen(beneficiaire.getSexeBen());
				}
				if(beneficiaire.getEmailBen()!=null) {
					verifBeneficiaire.setEmailBen(beneficiaire.getEmailBen());
				}
				
				beneficiaireService.saveBeneficiaire(verifBeneficiaire);
			}
			
			entrepriseService.saveEntreprise(verifEntr);
			proprietaireService.saveProprietaire(verifProp);
		}	
			
			if(verifcodeImportation!=null) {
				if(category.equals("CodeOccasionnel")) {
					String codeStruc;
					if(verifcodeImportation.getEntreprise()!=null) {
						System.out.println("on est ici");
						codeStruc = verifEntr.getTypeStructure().getCodeStruc();
					}else{
						codeStruc = "42000A";
					}
					String codesOccasionnel = CalculeCodesExportation.getCodeOccasionnel(codeStruc, numDossier);
					
					if(!codesOccasionnel.isEmpty()) {
						verifcodeImportation.setNumOcca(codesOccasionnel);
					}
					
					if(verifcodeImportation.getNumFactureOcca()!=null) {
						verifcodeImportation.setNumFactureOcca(codeImportation.getNumFactureOcca());
					}	
	
					if(verifcodeImportation.getEmetteurOcca()!=null) {
						verifcodeImportation.setEmetteurOcca(codeImportation.getEmetteurOcca());
					}
					if(verifcodeImportation.getDateEmisOcca()!=null) {
						verifcodeImportation.setDateEmisOcca(codeImportation.getDateEmisOcca());
					}
					if(verifcodeImportation.getDeclarationOcca()!=null) {
						verifcodeImportation.setDeclarationOcca(codeImportation.getDeclarationOcca());
					}
					if(verifcodeImportation.getObjetOcca()!=null) {
						verifcodeImportation.setObjetOcca(codeImportation.getObjetOcca());
					}
					if(verifcodeImportation.getTypeCodeOcca()!=null) {
						verifcodeImportation.setTypeCodeOcca(codeImportation.getTypeCodeOcca());
					}
				}
				
				if(category.equals("LeveeDeGage")) {
					
					String dategag = codeImportation.getDateGag();
					
					SimpleDateFormat formatGag = new SimpleDateFormat("yyyy-MM-dd");
					String dateDuJours = formatGag.format(new Date());
					LocalDate dates = LocalDate.parse(dategag, DateTimeFormatter.ISO_LOCAL_DATE);
					LocalDate dJour = LocalDate.parse(dateDuJours, DateTimeFormatter.ISO_LOCAL_DATE);
					
					Period diffDate = Period.between(dJour, dates);
					int years = Math.abs(diffDate.getYears());
					int mois = Math.abs(diffDate.getMonths());
					
					String typeGage="";
					
					if((years == 2 && mois > 0) || (years > 2 )) {
						typeGage = "ordinaire";
					}else if((years == 2 && mois == 0) || (years < 2 )) {
						typeGage = "exceptionnel";
					}

					String codesLege = CalculeCodesExportation.getLeveeGage(codeImportation.getUsageGag(), codeImportation.getNumChassisGag(), typeGage, numDossier);
					
					if(!codesLege.isEmpty()) {
						verifcodeImportation.setNumGag(codesLege);
					}
					if(verifcodeImportation.getDateGag()!=null) {
						verifcodeImportation.setDateGag(codeImportation.getDateGag());
					}
					if(verifcodeImportation.getNumImmatriculationtGag()!=null) {
						verifcodeImportation.setNumImmatriculationtGag(codeImportation.getNumImmatriculationtGag());
					}							
					if(verifcodeImportation.getNumCarteGriseGag()!=null) {
						verifcodeImportation.setNumCarteGriseGag(codeImportation.getNumCarteGriseGag());
					}
					if(verifcodeImportation.getNumChassisGag()!=null) {
						verifcodeImportation.setNumChassisGag(codeImportation.getNumChassisGag());
					}
					if(verifcodeImportation.getDateMiseCirculationGag()!=null) {
						verifcodeImportation.setDateMiseCirculationGag(codeImportation.getDateMiseCirculationGag());
					}
					if(verifcodeImportation.getTypeTechGag()!=null) {
						verifcodeImportation.setTypeTechGag(codeImportation.getTypeTechGag());
					}if(verifcodeImportation.getUsageGag()!=null) {
						verifcodeImportation.setUsageGag(codeImportation.getUsageGag());
					}
					if(verifcodeImportation.getTypeGag()!=null) {
						verifcodeImportation.setTypeGag(codeImportation.getTypeGag());
					}
					if(verifcodeImportation.getMarque()!=null) {
						verifcodeImportation.setMarque(codeImportation.getMarque());
					}
					if(verifcodeImportation.getGenreMarque()!=null) {
						verifcodeImportation.setGenreMarque(codeImportation.getGenreMarque());
					}
				}
				codeImportationService.saveCodeImportation(verifcodeImportation);
			}
			
		return "redirect:../Dossier/Liste";
	}
	
	@RequestMapping("/{category}/Operation/Suppression/Dossier/{numDoc}")
	public String supprOpDossier(@PathVariable("category") String category, @PathVariable("numDoc") Long numDoc, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user,category);		
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		
		OpCodeImportation Elmt = opCodeImportationService.getOpCodeImportationById(numDoc);
		Entreprise infoEntr = opCodeImportationService.getOpCodeImportationById(numDoc).getCodeImportation().getEntreprise();
		Demandeur infoDem = opCodeImportationService.getOpCodeImportationById(numDoc).getCodeImportation().getDemandeur();
		Beneficiaire infoBen = opCodeImportationService.getOpCodeImportationById(numDoc).getCodeImportation().getBeneficiaire();
		//codeImportationService.deleteCodeImportationById(idElmt);
		if(infoEntr != null) {
			Proprietaire infoProp = opCodeImportationService.getOpCodeImportationById(numDoc).getCodeImportation().getEntreprise().getProprietaires();
			modelMap.addAttribute("infoEntreprise", infoEntr);
			modelMap.addAttribute("infoProprietaire", infoProp);			
		}
		if(infoDem != null) {
			modelMap.addAttribute("infofiscos", infoDem);			
		}
		modelMap.addAttribute("listeCode", Elmt);
		modelMap.addAttribute("numDoc", numDoc);
		return "./"+category+"/suppressionDossier";
	}
	
	@RequestMapping("/{category}/Operation/Valider/Suppression/{numDoc}")
	public String valSupprOpDossier(@PathVariable("category") String category, @PathVariable("numDoc") Long numDoc, ModelMap modelMap)
	{		
		CodeImportation idElmt = opCodeImportationService.getOpCodeImportationById(numDoc).getCodeImportation();
		if(idElmt != null) {
			codeImportationService.deleteCodeImportation(idElmt);			
		}
		return "redirect:../../Dossier/Liste";
	}
	
	Integer nbreTrie = 0;
	
	@RequestMapping("/{category}/Doublon/Liste")
	public String doublonDossiers(@PathVariable("category") String category, ModelMap modelMap) throws IOException
	{
		
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user,category);
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		List<OpCodeImportation> tries = new ArrayList<>()
				;
		//List<Proprietaire> proprietaire = proprietaireService.getAllProprietaire();
		List<OpCodeImportation> codfs = opCodeImportationService.findAllCodeImportationByTypeCodeOp(category);
		codfs.forEach(trier ->{
			System.out.println("nbreTrie= "+nbreTrie+" trier.getNumDocOp "+trier.getNumDocOp());
			/*if(nbreTrie == trier.getNumDocOp()) {
				nbreTrie = trier.getNumDocOp();
				tries.add(trier);
				System.out.println("affiche ");
			}else {
				nbreTrie = trier.getNumDocOp();
				tries.add(trier);
				System.out.println("non ");
			}*/
			tries.add(trier);
		});
		modelMap.addAttribute("listeCode", tries);
		return "./"+category+"/doublonDossier";
	}
	
	@RequestMapping("/{category}/Suppression/Doublon/{numDoc}")
	public String dolSupprOpDossier(@PathVariable("category") String category, @PathVariable("numDoc") Long numDoc, ModelMap modelMap)
	{		
		Proprietaire idElmt = proprietaireService.getProprietaireById(numDoc);
		if(idElmt != null) {
			proprietaireService.deleteProprietaire(idElmt);			
		}
		return "redirect:../../Doublon/Liste";
	}
	
	@RequestMapping("/{category}/Doublon/Recherche")
	public String recherchedorDossier(@PathVariable("category") String category, Integer numDoc, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user,category);		
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		
		List<OpCodeImportation> codeElmt = opCodeImportationService.findAllOpCodeImportationNUMDOC(numDoc);
		modelMap.addAttribute("listeCode", codeElmt);
		return "./"+category+"/doublonDossier";
	}

}
