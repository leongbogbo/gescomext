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
public class Demandeur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDem;
	private String nomDem;
	private String prenomsDem;
	private String sexeDem;
	private String pieceidentDem;
	private String validitePieceDem;
	private String numpieceDem;
	private String telDem;
	private String emailDem;
	private Integer quotaOccaDem;

	
	@ManyToOne	  
  	@JoinColumn(name = "nat_id")
	private Nationalite nationalite;
	
	@ManyToOne	  
	@JoinColumn(name = "fonction_id")
	private Fonction fonction;

	@ManyToOne	  
	@JoinColumn(name = "typepiece_id")
	private TypePieceIdentite typePieceIdentite;

	
	@OneToMany(mappedBy = "demandeur")
	private List<CodeImportation> codeImportation;
	
	
	
}
