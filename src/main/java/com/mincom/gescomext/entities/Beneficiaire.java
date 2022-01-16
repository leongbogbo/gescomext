package com.mincom.gescomext.entities;

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
public class Beneficiaire {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBen;
	private String nomBen;
	private String prenomsBen;
	private String sexeBen;
	private String pieceidentBen;
	private String validitePieceBen;
	private String numpieceBen;
	private String telBen;
	private String emailBen;

	
	@ManyToOne	  
  	@JoinColumn(name = "nat_id")
	private Nationalite natBeneficiaire;

	@ManyToOne	  
	@JoinColumn(name = "typepiece_id")
	private TypePieceIdentite pieceBeneficiaire;
	
	@OneToMany(mappedBy = "beneficiaire")
	private List<CodeImportation> codeImportation;
	
	
	
}
