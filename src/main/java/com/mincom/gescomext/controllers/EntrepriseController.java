package com.mincom.gescomext.controllers;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.config.GetCurrentUser;
import com.mincom.gescomext.config.ListeRolesActionsUser;
import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.entities.Departement;
import com.mincom.gescomext.entities.DomaineActivite;
import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.Fonction;
import com.mincom.gescomext.entities.FormeJuridique;
import com.mincom.gescomext.entities.GenreMarque;
import com.mincom.gescomext.entities.Marque;
import com.mincom.gescomext.entities.Nationalite;
import com.mincom.gescomext.entities.Proprietaire;
import com.mincom.gescomext.entities.TypePieceIdentite;
import com.mincom.gescomext.entities.TypeStructure;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.EntrepriseRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.CommuneService;
import com.mincom.gescomext.service.DepartementService;
import com.mincom.gescomext.service.EntrepriseService;
import com.mincom.gescomext.service.FonctionService;
import com.mincom.gescomext.service.FormeJuridiqueService;
import com.mincom.gescomext.service.GenreMarqueService;
import com.mincom.gescomext.service.MarqueService;
import com.mincom.gescomext.service.NationaliteService;
import com.mincom.gescomext.service.ProprietaireService;
import com.mincom.gescomext.service.TypePieceIdentiteService;
import com.mincom.gescomext.service.TypeStructureService;
import com.mincom.gescomext.service.VilleService;
import com.mincom.gescomext.validator.EntrepriseValidator;
import com.mincom.gescomext.validator.ProprietaireValidator;
import com.mincom.gescomext.service.DomaineActiviteService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Controller
public class EntrepriseController {

	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	CommuneService communeService;
	@Autowired
	VilleService villeService;
	@Autowired
	NationaliteService natService;
	@Autowired
	FormeJuridiqueService fjuryService;
	@Autowired
	MarqueService marqueService;
	@Autowired
	GenreMarqueService genreMarqueService;
	@Autowired
	TypeStructureService typeStructureService;
	@Autowired
	TypePieceIdentiteService typePieceIdentiteService;
	@Autowired
	DomaineActiviteService domaineActiviteService;
	@Autowired
	DepartementService departementService;
	@Autowired
	FonctionService fonctionService;
	@Autowired
	ProprietaireService proprietaireService;
	@Autowired
	EntrepriseRepository entrepriseRepo;
	@Autowired
	UserRepository	userRepo;
	
	List<String> errorsList = new ArrayList<>();
	
	@RequestMapping("/parametre/listeEntreprises")
	public String listeEntreprises(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<Entreprise> elmt = entrepriseService.getAllEntreprise();
		modelMap.addAttribute("entreprises", elmt);
		return "autres/listeEntreprise";
	}
	
	@RequestMapping("/parametre/Entreprise/new")
	public String saveEntreprise(@Valid Entreprise entreprise, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		entrepriseRepo.save(entreprise);
		return "autres/listeEntreprise";
	}
	
	@RequestMapping("/parametre/Recherche/Entreprise")
	public String rechEntreprise(String nomEntr, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepo.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		
		List<Entreprise>  entreprise = entrepriseService.findBynomEntrContaining(nomEntr);
		modelMap.addAttribute("entreprises", entreprise);
		return "autres/listeEntreprise";
	}
	
	@RequestMapping("/parametre/Update/Entreprise/{id}")
	public String updateEntreprise(@PathVariable("id") Long id, ModelMap modelMap)
	{
		Entreprise verifEntreprise = entrepriseService.getEntrepriseById(id);
		Proprietaire verifProprietaire = verifEntreprise.getProprietaires();
		
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
		
		modelMap.addAttribute("verifEntreprise", verifEntreprise);
		modelMap.addAttribute("verifProprietaire", verifProprietaire);
		return "autres/updateEntreprise";
	}
	
	@RequestMapping("/parametre/Valider/Update/Entreprise")
	public String validerUpdateEntreprise(
			@ModelAttribute("entreprise") Entreprise entreprise,
			@ModelAttribute("proprietaire") Proprietaire proprietaire, ModelMap modelMap)
	{
		Entreprise verifEntr = new Entreprise();
		Proprietaire verifProp = new Proprietaire();

		if (entreprise.getIdEntr() != null) {
			EntrepriseValidator.validate(entreprise).forEach(error -> {
				errorsList.add(error);
			});
			ProprietaireValidator.validate(proprietaire).forEach(error -> {
				errorsList.add(error);
			});
			if (errorsList.size() == 0) {
				verifEntr = entrepriseService.getEntrepriseById(entreprise.getIdEntr());
				verifProp = proprietaireService.getProprietaireById(proprietaire.getIdProp());
				
			}
		}
		if (verifEntr != null) {
			if (entreprise.getNomEntr() != null) {
				verifEntr.setNomEntr(entreprise.getNomEntr());
			}

			if (entreprise.getSigleEntr() != null) {
				verifEntr.setSigleEntr(entreprise.getSigleEntr());
			}

			if (entreprise.getNumIduEntr() != null) {
				verifEntr.setNumIduEntr(entreprise.getNumIduEntr());
			}

			if (entreprise.getDepartement() != null) {
				verifEntr.setDepartement(entreprise.getDepartement());
			}

			if (entreprise.getExoregcomEntr() != null) {
				verifEntr.setExoregcomEntr(entreprise.getExoregcomEntr());
			}

			if (entreprise.getRegcommerceEntr() != null) {
				verifEntr.setRegcommerceEntr(entreprise.getRegcommerceEntr());
			}

			if (entreprise.getCommune() != null) {
				verifEntr.setCommune(entreprise.getCommune());
			}

			if (entreprise.getPostaleEntr() != null) {
				verifEntr.setPostaleEntr(entreprise.getPostaleEntr());
			}

			if (entreprise.getEmailEntr() != null) {
				verifEntr.setEmailEntr(entreprise.getEmailEntr());
			}

			if (entreprise.getFormeJuridique() != null) {
				verifEntr.setFormeJuridique(entreprise.getFormeJuridique());
			}

			if (entreprise.getDomaineActivite() != null) {
				verifEntr.setDomaineActivite(entreprise.getDomaineActivite());
			}

			if (entreprise.getContribuableEntr() != null) {
				verifEntr.setContribuableEntr(entreprise.getContribuableEntr());
			}

			if (proprietaire.getNomProp() != null) {
				verifProp.setNomProp(proprietaire.getNomProp());
			}

			if (proprietaire.getPrenomsProp() != null) {
				verifProp.setPrenomsProp(proprietaire.getPrenomsProp());
			}

			if (proprietaire.getSexeProp() != null) {
				verifProp.setSexeProp(proprietaire.getSexeProp());
			}

			if (proprietaire.getTypePieceIdentite() != null) {
				verifProp.setTypePieceIdentite(proprietaire.getTypePieceIdentite());
			}

			if (proprietaire.getValiditePieceProp() != null) {
				verifProp.setValiditePieceProp(proprietaire.getValiditePieceProp());
			}

			if (proprietaire.getNationalite() != null) {
				verifProp.setNationalite(proprietaire.getNationalite());
			}

			if (proprietaire.getTelProp() != null) {
				verifProp.setTelProp(proprietaire.getTelProp());
			}

			if (proprietaire.getEmailProp() != null) {
				verifProp.setEmailProp(proprietaire.getEmailProp());
			}
			proprietaireService.saveProprietaire(verifProp);
			entrepriseService.saveEntreprise(verifEntr);
		}
		return "redirect:/parametre/listeEntreprises";
	}
	
	@RequestMapping("/parametre/pdf/Entreprise")
	public ResponseEntity<byte[]> generatePdf() throws FileNotFoundException, JRException {
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(entrepriseService.getAllEntreprise());
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/autres/entreprisepdf.jrxml"));
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map,beanCollectionDataSource);
		//JasperExportManager.exportReportToPdfFile(report, "entrepriseViewer.pdf");
		byte[] data = JasperExportManager.exportReportToPdf(report);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=entreprisedpdf.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	
	@RequestMapping("/parametre/Suppression/Entreprise/{id}")
	public String suppressionEntreprise(@PathVariable("id") Long id, ModelMap modelMap)
	{
		Entreprise verifEntreprise = entrepriseService.getEntrepriseById(id);
		if(verifEntreprise!=null) {
			List<Entreprise> listEntr = verifEntreprise.getProprietaires().getEntreprises();
			if(listEntr.size()==1) {
				proprietaireService.deleteProprietaire(verifEntreprise.getProprietaires());
			}else if(listEntr.size()>1) {
				entrepriseService.deleteEntreprise(verifEntreprise);		
			}			
		}
		
		return "redirect:/parametre/listeEntreprises";
	}
	
}
