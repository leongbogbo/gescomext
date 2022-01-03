package com.mincom.gescomext.controllers;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import org.springframework.web.bind.annotation.RequestMapping;

import com.mincom.gescomext.entities.Ville;
import com.mincom.gescomext.repository.VilleRepository;
import com.mincom.gescomext.service.VilleService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Controller
public class VilleController {

	@Autowired
	VilleService villeService;
	@Autowired
	VilleRepository villeRepo;
	
	@RequestMapping("/autres/listeVilles")
	public String listeVilles(ModelMap modelMap)
	{
		List<Ville> vills = villeService.getAllVille();
		modelMap.addAttribute("villes", vills);
		modelMap.addAttribute("villeVide", new Ville());
		return "autres/listeVille";
	}
	
	@RequestMapping("/Ville/new")
	public String saveVille(@Valid Ville ville, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) return "listeVille";
		villeRepo.save(ville);
		return "listeVille";
	}
	
	@RequestMapping("/autres/pdf")
	public ResponseEntity<byte[]> generatePdf() throws FileNotFoundException, JRException {
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(villeService.getAllVille());
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/templates/autres/villepdf.jrxml"));
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map,beanCollectionDataSource);
		//JasperExportManager.exportReportToPdfFile(report, "villeViewer.pdf");
		byte[] data = JasperExportManager.exportReportToPdf(report);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=villedpdf.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	
}
