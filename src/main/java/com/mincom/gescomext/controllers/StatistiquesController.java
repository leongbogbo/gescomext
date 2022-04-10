package com.mincom.gescomext.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mincom.gescomext.entities.Role;
import com.mincom.gescomext.entities.Site;
import com.mincom.gescomext.entities.TypePieceIdentite;
import com.mincom.gescomext.entities.TypeStructure;
import com.mincom.gescomext.entities.User;
import com.mincom.gescomext.entities.Ville;
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
import com.mincom.gescomext.entities.OpCodeImportation;
import com.mincom.gescomext.repository.ActionListeRepository;
import com.mincom.gescomext.repository.UserRepository;
import com.mincom.gescomext.service.RoleService;
import com.mincom.gescomext.service.SiteService;
import com.mincom.gescomext.service.UserService;
import com.mincom.gescomext.service.VilleService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import com.mincom.gescomext.service.ActionListeService;
import com.mincom.gescomext.service.CommuneService;
import com.mincom.gescomext.service.DomaineActiviteService;
import com.mincom.gescomext.service.EntrepriseService;
import com.mincom.gescomext.service.FormeJuridiqueService;
import com.mincom.gescomext.service.NationaliteService;
import com.mincom.gescomext.service.OpCodeImportationService;

@Controller
public class StatistiquesController{

	@Autowired
	ActionListeService actionListeService;
	@Autowired
	RoleService roleService;
	@Autowired
	ActionListeRepository actionListeRepo;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommuneService communeService;
	@Autowired
	VilleService villeService;
	@Autowired
	NationaliteService natService;
	@Autowired
	FormeJuridiqueService fjuryService;
	@Autowired
	DomaineActiviteService domaineActiviteService;
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	FormeJuridiqueService formeJuridiqueService;
	@Autowired
	SiteService siteService;
	@Autowired
	UserService userService;
	@Autowired
	OpCodeImportationService opCodeImportationService;
	
	Date aujourdhui = new Date();
	SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm:ss");
	String dateDuJour = formater.format(aujourdhui);
	SimpleDateFormat dateCourt = new SimpleDateFormat("yyyy-MM-dd");
	String dateJour = dateCourt.format(aujourdhui);
	SimpleDateFormat dateAnnee = new SimpleDateFormat("yyyy");
	String dateDebut = dateAnnee.format(aujourdhui)+"-01-01";
	String validate = "non";
	
	@RequestMapping("/Statistique/Entreprise")
	public String listeActions(Long idJuridique, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		validate = "non";
		listeUrlStatAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("Statistique/Entreprise")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		List<Commune> coms = communeService.getAllCommune();
		List<Ville> vils = villeService.getAllVille();
		List<Nationalite> nats = natService.getAllNationalite();
		List<FormeJuridique> fmjury = fjuryService.getAllFormeJuridique();
		List<DomaineActivite> domaineActivite = domaineActiviteService.getAllDomaineActivite();
		
		modelMap.addAttribute("listeCommunes", coms);
		modelMap.addAttribute("listeVilles", vils);
		modelMap.addAttribute("listeNationalites", nats);
		modelMap.addAttribute("listeFormjury", fmjury);
		modelMap.addAttribute("listedomaineActivite", domaineActivite);
		
		modelMap.addAttribute("dateDuJour", dateJour);
		modelMap.addAttribute("dateDebut", dateDebut);
		return "Statistiques/listeEntreprise";
	}
	@RequestMapping("/Statistique/Operations")
	public String listeoperations(Long idJuridique, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		
		validate = "non";
		listeUrlStatAdmin.forEach(liens -> {
			if (liens.getLienActPro().equals("Statistique/Operations")) {
				validate = "oui";
			}
		});
		
		if (validate.equals("non")) {
			return "./accessDenied";
		}
		
		List<Site> sites = siteService.getAllSite();
		List<FormeJuridique> fmjury = fjuryService.getAllFormeJuridique();
		List<DomaineActivite> domaineActivite = domaineActiviteService.getAllDomaineActivite();
		List<User> userList = userService.getAllUser();
		List<Role> roleList = roleService.getAllRole();
		
		modelMap.addAttribute("listeRole", roleList);
		modelMap.addAttribute("listeUser", userList);
		modelMap.addAttribute("listeFormjury", fmjury);
		modelMap.addAttribute("listeDomAct", domaineActivite);
		modelMap.addAttribute("listeSite", sites);		
		
		modelMap.addAttribute("dateDuJour", dateJour);
		modelMap.addAttribute("dateDebut", dateDebut);
		return "Statistiques/listeOperation";
	}
	
	/*@RequestMapping("/Statistique/AttributionActions")
	public String listeAttributionActions(ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		
		List<ActionListe> elmts = actionListeService.getAllActionListe();
		List<Role> roleList = roleService.getAllRole();
		modelMap.addAttribute("actionListes", elmts);
		modelMap.addAttribute("roleList", roleList);
		modelMap.addAttribute("ActionListesVide", new ActionListe());
		return "autres/attributionAction";
	}
	
	
	@RequestMapping("/Statistique/ActionProfile/new")
	public String saveActionListe(Role role, ModelMap modelMap)
	{
		ListeRolesActionsUser classGestionUrl = new ListeRolesActionsUser();
		String username = GetCurrentUser.getUserConnected();
		User user = userRepository.findByUsername(username);
		List<ActionListe> listeUrlUser = classGestionUrl.getListeAcctions(user, "parametre");
		List<ActionListe> listeUrlUserAdmin = classGestionUrl.getListeAcctions(user, "administration");
		List<ActionListe> listeUrlStatAdmin = classGestionUrl.getListeAcctions(user, "Statistique");
		modelMap.addAttribute("listeUrlUser", listeUrlUser);
		modelMap.addAttribute("listeUrlUserAdmin", listeUrlUserAdmin);
		modelMap.addAttribute("listeUrlStatAdmin", listeUrlStatAdmin);
		
		if(role.getRole_id()!=null) {
			Role roleFind = roleService.getRoleById(role.getRole_id());
			roleFind.setActionListe(role.getActionListe());
			roleService.saveRole(roleFind);
		}
		return "redirect:/parametre/AttributionActions";
	}*/
	
	@RequestMapping("/Statistique/pdf/Entreprise")
	public ResponseEntity<byte[]> generatePdf(@RequestParam(required = false)  Map<String, String> infos) throws FileNotFoundException, JRException, ParseException {
		
		List<Entreprise> listeEntreprise = new ArrayList<>();
		List<Entreprise> temponEntreprise = new ArrayList<>();
		Date inputDate = null;
		Date outPutDate = null;
		
		if (!infos.get("inputDates").isEmpty()) {
			inputDate = dateCourt.parse(infos.get("inputDates").trim());
		}
		if (!infos.get("outputDates").isEmpty()) {
			outPutDate = dateCourt.parse(infos.get("outputDates").trim());
		}
		
		if(inputDate!=null && outPutDate!=null && inputDate.compareTo(outPutDate)<0) {
			listeEntreprise = entrepriseService.findStatAllEntreprise(inputDate,outPutDate);			
			if (!infos.get("idJuridiques").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getFormeJuridique().getIdJury()==Long.valueOf(infos.get("idJuridiques").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
			if (!infos.get("iddomaineActivite").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getDomaineActivite().getIdDomAct()==Long.valueOf(infos.get("iddomaineActivite").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
			if (!infos.get("idville").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getCommune().getVille().getIdVille()==Long.valueOf(infos.get("idville").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
			if (!infos.get("idcommune").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getCommune().getIdCommune()==Long.valueOf(infos.get("idcommune").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
			if (!infos.get("idnationalite").isEmpty()) {
				listeEntreprise.forEach(listeEntr->{
					if(listeEntr.getProprietaires().getNationalite().getIdNat()==Long.valueOf(infos.get("idnationalite").trim())) {
						temponEntreprise.add(listeEntr);
					}
				});
				listeEntreprise.clear();
				listeEntreprise.addAll(temponEntreprise);
				temponEntreprise.clear();
			}
		}
		
		
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listeEntreprise);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("templates/pdf/statExportListeEntreprise.jrxml").getFile());
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream(file));
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map,beanCollectionDataSource);
		//JasperExportManager.exportReportToPdfFile(report, "entrepriseViewer.pdf");
		byte[] data = JasperExportManager.exportReportToPdf(report);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=entreprisepdf.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@RequestMapping("/Statistique/excel/Entreprise")
    public void getDocument(HttpServletResponse response) throws IOException, JRException {
		
		InputStream inputStream = JRLoader.getResourceInputStream("src/main/resources/templates/pdf/SampleJasperReport.jrxml");
		JasperReport  sourceFileName = JasperCompileManager.compileReport(JRXmlLoader.load(inputStream));
        //String sourceFileName = ResourceUtils.getFile( ResourceUtils.CLASSPATH_URL_PREFIX+"templates/pdf/villepdf.jrxml").getAbsolutePath();
        List<Ville> dataList = villeService.getAllVille();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
        JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);
        JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[] { "Sheet 1" });
        exporter.setConfiguration(reportConfigXLS);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setHeader("Content-Disposition", "attachment;filename=jasperfile.xlsx");
        response.setContentType("application/octet-stream");
        exporter.exportReport();
    }
	
	Integer sommeTT=0;
	Long monRole = null;
	@RequestMapping("/Statistique/pdf/Operation")
	public ResponseEntity<byte[]> generateOpPdf(@RequestParam(required = false)  Map<String, String> infos) throws FileNotFoundException, JRException, ParseException {
		
		List<OpCodeImportation> listeOperation = new ArrayList<>();
		List<OpCodeImportation> temponOperation = new ArrayList<>();
		Date inputDate = null;
		Date outPutDate = null;
		
		if (!infos.get("inputDates").isEmpty()) {
			inputDate = dateCourt.parse(infos.get("inputDates").trim());
		}
		if (!infos.get("outputDates").isEmpty()) {
			outPutDate = dateCourt.parse(infos.get("outputDates").trim());
		}
		if(inputDate!=null && outPutDate!=null && inputDate.compareTo(outPutDate)<0) {
			listeOperation = opCodeImportationService.findStatAllOpCodeImportation(inputDate,outPutDate);			
			temponOperation.clear();
			if (!infos.get("rubriques").isEmpty()) {
				listeOperation.forEach(listeOp->{
					if(listeOp.getTypeCodeOp().equals(infos.get("rubriques").trim())) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			if (!infos.get("typeOps").isEmpty()) {
				listeOperation.forEach(listeOp->{
					if(listeOp.getTypeOp().equals(infos.get("typeOps").trim())) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			if (!infos.get("siteids").isEmpty()) {
				Site site = siteService.getSiteById(Long.valueOf(infos.get("siteids").trim()));
				listeOperation.forEach(listeOp->{
					if(listeOp.getUser().getSite()==site) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			if (!infos.get("fmjuryids").isEmpty()) {
				FormeJuridique formj = formeJuridiqueService.getFormeJuridiqueById(Long.valueOf(infos.get("fmjuryids").trim()));
				listeOperation.forEach(listeOp->{
					if(listeOp.getCodeImportation().getEntreprise().getFormeJuridique()==formj) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			if (!infos.get("domActids").isEmpty()) {
				DomaineActivite domact = domaineActiviteService.getDomaineActiviteById(Long.valueOf(infos.get("domActids").trim()));
				listeOperation.forEach(listeOp->{
					if(listeOp.getCodeImportation().getEntreprise().getDomaineActivite()==domact) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			
			if (!infos.get("roleids").isEmpty()) {
				listeOperation.forEach(listeOp->{
					listeOp.getUser().getRoles().forEach(leRole->{
						monRole = leRole.getRole_id();
					});
					if(monRole==Long.valueOf(infos.get("roleids").trim())) {
						temponOperation.add(listeOp);
					}
				});
				listeOperation.clear();
				listeOperation.addAll(temponOperation);
				temponOperation.clear();
			}
			if (!infos.get("userids").isEmpty()) {
				User users = userService.getUserById(Long.valueOf(infos.get("userids").trim()));
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
		sommeTT=0;
		System.out.println("-----------------------------"+infos.get("rubriques"));
		listeOperation.forEach(listeop->{
			sommeTT = sommeTT + Integer.parseInt(listeop.getMontantOp());
		});
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listeOperation);
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("templates/pdf/statExportListeOperation.jrxml").getFile());
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream(file));
		HashMap<String, Object> map = new HashMap<>();
		map.put("SommeTT", sommeTT);
		JasperPrint report = JasperFillManager.fillReport(compileReport, map,beanCollectionDataSource);
		//JasperExportManager.exportReportToPdfFile(report, "entrepriseViewer.pdf");
		byte[] data = JasperExportManager.exportReportToPdf(report);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=operationpdf.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
