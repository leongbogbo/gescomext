package com.mincom.gescomext.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String exoPaiementEntr;
	private String postaleEntr;
	private String telEntr;
	private String emailEntr;
	private String contribuableEntr;
	private String regcommerceEntr;
	private String codeImportExportEntr;
	private String numIduEntr;
	private String codeIdexEntr;
	private Integer quotaOccaEntr;
	
	private Date dateEntr;
	
	@JsonIgnore
	@ManyToOne	  
	@JoinColumn(name = "commune_id") 
	private Commune commune;
	
	//@JsonIgnore
	 @ManyToOne		  
	 @JoinColumn(name = "fjury_id")
	 private FormeJuridique formeJuridique;
		 
	 @JsonIgnore
	 @ManyToOne		  
	 @JoinColumn(name = "proprietaire_id")
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 private Proprietaire proprietaires;
	 
	 @JsonIgnore
	 @OneToMany(mappedBy = "entreprise", cascade = CascadeType.REMOVE)
	 private List<CodeImportation> codeImportation;
	 
	 @JsonIgnore
	 @ManyToOne		  
	 @JoinColumn(name = "typestruc_id")
	 private TypeStructure typeStructure;
	 
	 @JsonIgnore
	 @ManyToOne		  
	 @JoinColumn(name = "departement_id")
	 private Departement departement;
	
	 @JsonIgnore
	 @ManyToOne		  
	 @JoinColumn(name = "domact_id")
	 private DomaineActivite domaineActivite;

	
}
