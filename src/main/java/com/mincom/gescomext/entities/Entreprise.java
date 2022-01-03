package com.mincom.gescomext.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entreprise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEntr;
	private String nomEntr;
	private String sigleEntr;
	private String exoregcomEntr;
	private String postaleEntr;
	private String telEntr;
	private String emailEntr;
	private String contribuableEntr;
	private String regcommerceEntr;
	private String codeImportExportEntr;
	private String numIduEntr;
	
	private Date dateEntr;
	
	@ManyToOne	  
	@JoinColumn(name = "commune_id") 
	private Commune commune;
	  
	 @ManyToOne		  
	 @JoinColumn(name = "fjury_id")
	 private FormeJuridique formeJuridique;
		 
	
	 @ManyToOne		  
	 @JoinColumn(name = "proprietaire_id") 
	 private Proprietaire proprietaires;
	
	@OneToMany(mappedBy = "entreprise")
	private List<CodeImportation> codeImportation;
	
	@ManyToOne		  
	 @JoinColumn(name = "typestruc_id")
	 private TypeStructure typeStructure;

	 @ManyToOne		  
	 @JoinColumn(name = "departement_id")
	 private Departement departement;
	
	@ManyToOne		  
	 @JoinColumn(name = "domact_id")
	 private DomaineActivite domaineActivite;

	
}
