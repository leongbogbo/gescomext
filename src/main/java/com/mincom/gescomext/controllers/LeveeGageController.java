package com.mincom.gescomext.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.mincom.gescomext.config.CalculeCodesExportation;
import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Beneficiaire;
import com.mincom.gescomext.entities.CodeImportation;
import com.mincom.gescomext.entities.Demandeur;
import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.OpCodeImportation;
import com.mincom.gescomext.entities.Proprietaire;
import com.mincom.gescomext.entities.TypeStructure;
import com.mincom.gescomext.entities.User;
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

public class LeveeGageController {

	
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
			Date aujourdhui = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm:ss");
			String dateDuJour = formater.format(aujourdhui);

	public String ajoutEntreprise(@PathVariable("category") String category, 
			@ModelAttribute("entreprise") Entreprise entreprise,
				@ModelAttribute("proprietaire") Proprietaire proprietaire,
					@ModelAttribute("codeImportation") CodeImportation codeImportation,
						@ModelAttribute("opCodeImportation") OpCodeImportation opCodeImportation,
							@ModelAttribute("demandeur") Demandeur demandeur,
								@ModelAttribute("beneficiaire") Beneficiaire beneficiaire,
									String codeAncien,
										ModelMap modelMap)
{
//User connecté
String username = GetCurrentUser.getUserConnected();
User user = userRepository.findByUsername(username);
ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user,category);		
modelMap.addAttribute("listeUrlUser", listeUrlUser);

//calcul du dernier dossier
Integer numDossier = 1001001;
OpCodeImportation elmt = opCodeImportationService.findFirstByOrderByIdOpDesc();
if(elmt != null) {
numDossier = 1 + elmt.getNumDocOp();
}		
Date date = new Date();
String codeStruc ="";
if(category.equals("CodeImportExport")) {
String codesExportation="";
String codesFiscals="";
Entreprise verifByContriEntreprise = entrepriseService.findByContribuableEntr(entreprise.getContribuableEntr());
Entreprise verifByRaisonEntreprise = entrepriseService.findByNomEntr(entreprise.getNomEntr());
if((verifByRaisonEntreprise == null) && (verifByContriEntreprise == null)){

if(entreprise.getExoregcomEntr().equals("non") && !entreprise.getRegcommerceEntr().isEmpty() && !entreprise.getContribuableEntr().isEmpty()){
codesExportation = CalculeCodesExportation.getCodeImportExport(entreprise.getRegcommerceEntr(), entreprise.getContribuableEntr(), numDossier);
codesFiscals = CalculeCodesExportation.getCodeFixcal(1,numDossier);
}else if(entreprise.getExoregcomEntr().equals("oui") && entreprise.getRegcommerceEntr().isEmpty() && !entreprise.getContribuableEntr().isEmpty()){
codesExportation = CalculeCodesExportation.getCodeImportExportWithOutRCCM(entreprise.getContribuableEntr(), numDossier);
codesFiscals = CalculeCodesExportation.getCodeFixcal(0,numDossier);
}else if(entreprise.getDepartement().getIdDep() !=0 && entreprise.getRegcommerceEntr().isEmpty() && !entreprise.getContribuableEntr().isEmpty()){
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
codeImportation.setStatutDemandeurCodeImp("oui");
CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);

if(codeAncien != null && codeAncien.length()==9) {
opCodeImportation.setTypeOp("Renouvellement");					
}else{
opCodeImportation.setTypeOp("Attribution");
}
opCodeImportation.setMontantOp("30000");			
opCodeImportation.setCodeImportation(codeImportationSave);
opCodeImportation.setActiveApprobationOp("inactif");
opCodeImportation.setActivePaimentOp(0);
}else{
System.out.println("Une erreur s'est produite pendant l'enregistrement");
}
}else{
System.out.println("La raison sociale ou le cc existe");
}

}if(category.equals("CodeOccasionnel")) {

if(demandeur.getNomDem() != null) {
demandeur.setQuotaOccaDem(1);
Demandeur saveDemandeur = demandeurService.saveDemandeur(demandeur);
codeImportation.setDemandeur(saveDemandeur);
codeImportation.setStatutDemandeurCodeImp("non");				
codeStruc = "42000A";
}

if(proprietaire.getNomProp() != null && entreprise.getNomEntr() !=null ) {
TypeStructure typeStructures = typeStructureService.getTypeStructureById(entreprise.getTypeStructure().getIdStruc());
codeStruc = typeStructures.getCodeStruc();
Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);				
entreprise.setProprietaires(saveProprietaire);		
entreprise.setDateEntr(date);
entreprise.setQuotaOccaEntr(1);

Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);
codeImportation.setEntreprise(saveEntreprise);
codeImportation.setStatutDemandeurCodeImp("oui");			
}		

String codesOccasionnel = CalculeCodesExportation.getCodeOccasionnel(codeStruc, numDossier);

codeImportation.setNumOcca(codesOccasionnel);
CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);
if(codeStruc.equals("42000A")) {
opCodeImportation.setMontantOp("50000");
opCodeImportation.setActiveApprobationOp("inactif");
opCodeImportation.setActivePaimentOp(0);
}else {
opCodeImportation.setMontantOp("0");
opCodeImportation.setActivePaimentOp(1);
opCodeImportation.setActiveApprobationOp("inactif");
}
opCodeImportation.setTypeOp("Attribution");
opCodeImportation.setCodeImportation(codeImportationSave);


}if(category.equals("LeveeDeGage")) {

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
if(demandeur.getNomDem() != null) {
Demandeur saveDemandeur = demandeurService.saveDemandeur(demandeur);
codeImportation.setDemandeur(saveDemandeur);
codeImportation.setStatutDemandeurCodeImp("non");
}

if(proprietaire.getNomProp() != null && entreprise.getNomEntr() !=null ) {
Proprietaire saveProprietaire = proprietaireService.saveProprietaire(proprietaire);	

entreprise.setProprietaires(saveProprietaire);		
entreprise.setDateEntr(date);

Entreprise saveEntreprise = entrepriseService.saveEntreprise(entreprise);
codeImportation.setEntreprise(saveEntreprise);
codeImportation.setStatutDemandeurCodeImp("oui");
}

if(codeImportation.getUsageGag().equals("commercial")) {
Beneficiaire saveBeneficiaire = beneficiaireService.saveBeneficiaire(beneficiaire);
codeImportation.setBeneficiaire(saveBeneficiaire);
}

codeImportation.setNumGag(codesLege);
codeImportation.setTypeGag(typeGage);
CodeImportation codeImportationSave = codeImportationService.saveCodeImportation(codeImportation);

if(typeGage.equals("ordinaire")) {
opCodeImportation.setMontantOp("40000");
}else if(typeGage.equals("exceptionnel")) {
opCodeImportation.setMontantOp("50000");
}

opCodeImportation.setTypeOp("Attribution");
opCodeImportation.setActiveApprobationOp("inactif");
opCodeImportation.setActivePaimentOp(0);
opCodeImportation.setCodeImportation(codeImportationSave);
}
}


opCodeImportation.setActiveSignatureOp("non");
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

}
