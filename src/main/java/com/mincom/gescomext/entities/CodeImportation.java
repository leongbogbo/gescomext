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
public class CodeImportation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idImp;	
	private Date dateImp;
	private String statutDemandeurCodeImp;
	
	private String numCodFic;
	
	private String numOcca;
	private String numFactureOcca;
	private String emetteurOcca;
	private String dateEmisOcca;
	private String declarationOcca;
	private String objetOcca;
	private String typeCodeOcca;	
	
	private String numGag;
	private String dateGag;
	private String numImmatriculationtGag;
	private String numCarteGriseGag;
	private String numChassisGag;
	private String dateMiseCirculationGag;
	private String typeTechGag;
	private String usageGag;
	
	@ManyToOne
	@JoinColumn(name = "marque_id")
	private Marque marque;
	
	@ManyToOne
	@JoinColumn(name = "genremarque_id")
	private GenreMarque genreMarque;
	
	@ManyToOne
	@JoinColumn(name = "entreprise_id")
	private Entreprise entreprise;
	
	//demandeur du code pour operation
	@ManyToOne
	@JoinColumn(name = "demandeur_id")
	private Demandeur demandeur;
	
	@OneToMany(mappedBy = "codeImportation")
	private List<OpCodeImportation> opCodeImportation;
	
}
