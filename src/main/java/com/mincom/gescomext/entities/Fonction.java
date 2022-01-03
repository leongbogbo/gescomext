package com.mincom.gescomext.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fonction {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFonc;
	
	@NotNull
	@Size (min = 3,max = 255)
	private String titreFonc;
	
	@OneToMany(mappedBy = "fonction")
	private List<Proprietaire> proprietaire;

	@OneToMany(mappedBy = "fonction")
	private List<Demandeur> demandeur;
	
	public Fonction(String titreFonc) {
		super();
		this.titreFonc = titreFonc;
	}
	
	
}
