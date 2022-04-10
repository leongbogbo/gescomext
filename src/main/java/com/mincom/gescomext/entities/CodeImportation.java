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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CodeImportation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idImp;	
	private Date dateImp;
	private String statutDemandeurCodeImp;	
	private String numCodFic;	
	private String numOcca;
	private String numFactureOcca;
	private String emetteurOcca;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateEmisOcca;
	private String declarationOcca;
	private String objetOcca;
	private String typeCodeOcca;	
	
	private String numGag;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateGag;
	private String numImmatriculationtGag;	
	private String numCarteGriseGag;
	private String numChassisGag;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateMiseCirculationGag;
	private String typeTechGag;
	private String usageGag;
	private String typeGag;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "marque_id")
	private Marque marque;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "genremarque_id")
	private GenreMarque genreMarque;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "entreprise_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Entreprise entreprise;
	
	//demandeur du code pour operation
	@ManyToOne
	@JoinColumn(name = "demandeur_id")
	private Demandeur demandeur;
	
	//beneficiaire du code pour operation
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "beneficiaire_id")
	private Beneficiaire beneficiaire;
	
	@JsonIgnore
	@ManyToOne	  
  	@JoinColumn(name = "nat_id")
	private Nationalite paysorigine;
	
	@JsonIgnore
	@OneToMany(mappedBy = "codeImportation", cascade = CascadeType.REMOVE)
	private List<OpCodeImportation> opCodeImportation;
	
}
