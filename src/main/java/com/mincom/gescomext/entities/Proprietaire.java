package com.mincom.gescomext.entities;

import java.util.List;

import javax.persistence.CascadeType;
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
public class Proprietaire {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProp;
	private String nomProp;
	private String prenomsProp;
	private String sexeProp;	
	private String numpieceProp;
	private String validitePieceProp;
	private String telProp;
	private String emailProp;

	@OneToMany(mappedBy = "proprietaires", cascade = CascadeType.REMOVE)
	private List<Entreprise> entreprises;
	
	  @ManyToOne	  
	  @JoinColumn(name = "nat_id")
	  private Nationalite nationalite;
	  
	  @ManyToOne	  
	  @JoinColumn(name = "typepiece_id")
	  private TypePieceIdentite typePieceIdentite;

	  @ManyToOne	  
	  @JoinColumn(name = "fonction_id")
	  private Fonction fonction;
	 
	public Proprietaire(Nationalite nationalite) {
		super();
		this.nationalite = nationalite;
	}
	
	
	
}
