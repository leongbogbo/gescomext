package com.mincom.gescomext.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mincom.gescomext.entities.ActionListe;
import com.mincom.gescomext.entities.Commune;
import com.mincom.gescomext.entities.DomaineActivite;
import com.mincom.gescomext.entities.Entreprise;
import com.mincom.gescomext.entities.FormeJuridique;
import com.mincom.gescomext.entities.OpCodeImportation;
import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.Site;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.EntrepriseRepository;
import com.mincom.gescomext.repository.RoleRepository;
import com.mincom.gescomext.service.ActionListeService;
import com.mincom.gescomext.service.DomaineActiviteService;
import com.mincom.gescomext.service.EntrepriseService;
import com.mincom.gescomext.service.FormeJuridiqueService;
import com.mincom.gescomext.service.OpCodeImportationService;
import com.mincom.gescomext.service.RoleService;
import com.mincom.gescomext.service.SiteService;
import com.mincom.gescomext.service.UserService;
import com.mincom.gescomext.service.VilleService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ValidationRESTController {

	@Autowired
	VilleService villeService;
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ActionListeService actionListeService;
	@Autowired
	SiteService siteService;
	@Autowired
	UserService userService;
	@Autowired
	FormeJuridiqueService formeJuridiqueService;
	@Autowired
	DomaineActiviteService domaineActiviteService;
	@Autowired
	OpCodeImportationService opCodeImportationService;
	@Autowired
	RoleService roleService;
	
	DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/form/listeVille", method = RequestMethod.GET)
	public List<Ville> getAllVilles() {
		return villeService.getAllVille();
	}

	@RequestMapping(value = "/raisonSociale/{raison}", method = RequestMethod.GET)
	public Entreprise getElement(@PathVariable("raison") String raison) {
		return entrepriseRepository.findByNomEntr(raison);
	}

	@RequestMapping(value = "/cpici/raisonSociale/{raison}", method = RequestMethod.GET)
	public Map<String, String> getCpiciElement(@PathVariable("raison") String raison) {
		Map<String, String> morse = new HashMap<>();
		if (entrepriseRepository.findByNomEntr(raison) != null) {
			morse.put("respone", "true");
			morse.put("msg", "trouve");
			morse.put("raison", raison);
		} else {
			morse.put("respone", "false");
			morse.put("msg", "element non trouvé");
		}
		return morse;
	}

	@RequestMapping(value = "/numidu/{numidu}", method = RequestMethod.GET)
	public Entreprise getElementnumidu(@PathVariable("numidu") String numidu) {
		return entrepriseRepository.findByNumIduEntr(numidu);
	}

	@RequestMapping(value = "/contribuable/{contribuable}", method = RequestMethod.GET)
	public Entreprise getElementcontribuable(@PathVariable("contribuable") String contribuable) {
		return entrepriseRepository.findByContribuableEntr(contribuable);
	}

	@RequestMapping(value = "/regicommerce/{regicommerce}", method = RequestMethod.GET)
	public Entreprise getElementregicommerce(@PathVariable("regicommerce") String regicommerce) {
		return entrepriseRepository.findByRegcommerceEntr(regicommerce);
	}

	@RequestMapping(value = "/profile/action/{idprofile}", method = RequestMethod.GET)
	public List<ActionListe> getActionProfile(@PathVariable("idprofile") Long idprofile) {
		return roleRepository.getById(idprofile).getActionListe();
	}

	@RequestMapping(value = "/profile/listeAction", method = RequestMethod.GET)
	public List<ActionListe> getActions() {
		return actionListeService.getAllActionListe();
	}

	@RequestMapping(value = "/parametre/listeUser", method = RequestMethod.GET)
	public List<User> getUser() {
		return userService.getAllUser();
	}

	@RequestMapping(value = "/parametre/site/{id}", method = RequestMethod.GET)
	public List<User> getUserByIdSite(@PathVariable("id") Long id) {
		return siteService.getSiteById(id).getUser();
	}

	@RequestMapping(value = "/statistique/entreprise/stat", method = RequestMethod.GET)
	public List<Entreprise> getStatistiqueEntr(@RequestParam(required = false) Map<String, String> infos) throws ParseException {
		List<Entreprise> listeEntreprise = new ArrayList<>();
		List<Entreprise> temponEntreprise = new ArrayList<>();
		Date inputDate = null;
		Date outPutDate = null;
		
		if (!infos.get("inputDate").isEmpty()) {
			inputDate = sourceFormat.parse(infos.get("inputDate").trim());
		}
		if (!infos.get("outPutDate").isEmpty()) {
			outPutDate = sourceFormat.parse(infos.get("outPutDate").trim());
		}
		
		if(inputDate!=null && outPutDate!=null && inputDate.compareTo(outPutDate)<0) {
			listeEntreprise = entrepriseService.findStatAllEntreprise(inputDate,outPutDate);			
			if (!infos.get("idForm").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getFormeJuridique().getIdJury()==Long.valueOf(infos.get("idForm").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
			if (!infos.get("domAct").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getDomaineActivite().getIdDomAct()==Long.valueOf(infos.get("domAct").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
			if (!infos.get("ville").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getCommune().getVille().getIdVille()==Long.valueOf(infos.get("ville").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
			if (!infos.get("commune").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getCommune().getIdCommune()==Long.valueOf(infos.get("commune").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
			if (!infos.get("nationalite").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getProprietaires().getNationalite().getIdNat()==Long.valueOf(infos.get("nationalite").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
		}
		return listeEntreprise;
	}
	
	Long monRole = null;
	@SuppressWarnings("unused")
	@RequestMapping(value = "/statistique/operations/stat", method = RequestMethod.GET)
	public List<OpCodeImportation> getStatistiqueOperation(@RequestParam(required = false) Map<String, String> infos) throws ParseException {
		List<OpCodeImportation> listeOperation = new ArrayList<>();
		List<OpCodeImportation> temponOperation = new ArrayList<>();
		String rubrique = "";
		String typeOp = "";
		Long siteid = null;
		Date inputDate = null;
		Date outPutDate = null;
		
		if (!infos.get("inputDate").isEmpty()) {
			inputDate = sourceFormat.parse(infos.get("inputDate").trim());
		}
		if (!infos.get("outPutDate").isEmpty()) {
			outPutDate = sourceFormat.parse(infos.get("outPutDate").trim());
		}
		
		if(inputDate!=null && outPutDate!=null && inputDate.compareTo(outPutDate)<0) {
			listeOperation = opCodeImportationService.findStatAllOpCodeImportation(inputDate,outPutDate);			
			temponOperation.clear();
			
			if (!infos.get("rubrique").isEmpty()) {
				listeOperation.forEach(listeOp->{
					if(listeOp.getTypeCodeOp().equals(infos.get("rubrique").trim())) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			
			if (!infos.get("typeOp").isEmpty()) {
				listeOperation.forEach(listeOp->{
					if(listeOp.getTypeOp().equals(infos.get("typeOp").trim())) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			if (!infos.get("siteid").isEmpty()) {
				Site site = siteService.getSiteById(Long.valueOf(infos.get("siteid").trim()));
				listeOperation.forEach(listeOp->{
					if(listeOp.getUser().getSite()==site) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			if (!infos.get("fmjuryid").isEmpty()) {
				FormeJuridique formj = formeJuridiqueService.getFormeJuridiqueById(Long.valueOf(infos.get("fmjuryid").trim()));
				listeOperation.forEach(listeOp->{
					if(listeOp.getCodeImportation().getEntreprise().getFormeJuridique()==formj) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			if (!infos.get("domActid").isEmpty()) {
				DomaineActivite domact = domaineActiviteService.getDomaineActiviteById(Long.valueOf(infos.get("domActid").trim()));
				listeOperation.forEach(listeOp->{
					if(listeOp.getCodeImportation().getEntreprise().getDomaineActivite()==domact) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			
			if (!infos.get("roleid").isEmpty()) {
				listeOperation.forEach(listeOp->{
					listeOp.getUser().getRoles().forEach(leRole->{
						monRole = leRole.getRole_id();
					});
					if(monRole==Long.valueOf(infos.get("roleid").trim())) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			if (!infos.get("userid").isEmpty()) {
				User users = userService.getUserById(Long.valueOf(infos.get("userid").trim()));
				listeOperation.forEach(listeOp->{
					if(listeOp.getUser()==users) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			
		}
		
		return listeOperation;
	}

	@RequestMapping(value = "/statistique/entreprise/liste", method = RequestMethod.GET)
	public List<Entreprise> listeStatistiqueEntr() {
		List<Entreprise> listeEntreprises = entrepriseService.getAllEntreprise();
		return listeEntreprises;
	}
	
	@RequestMapping(value = "/statistique/operations/liste", method = RequestMethod.GET)
	public List<OpCodeImportation> listeStatistiqueop() {
		List<OpCodeImportation> listeop = opCodeImportationService.getAllOpCodeImportation();
		return listeop;
	}

}
