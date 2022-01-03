package com.mincom.gescomext.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Commune {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCommune;
	private String nomCommune;
	
	@ManyToOne
	private Ville ville;
	
	
	  @OneToMany(mappedBy = "commune")
	  private List<Entreprise> entreprises;
	 
	
	public Commune(String nomCommune, Ville ville) {
		super();
		this.nomCommune = nomCommune;
		this.ville = ville;
	}
	
	
}
