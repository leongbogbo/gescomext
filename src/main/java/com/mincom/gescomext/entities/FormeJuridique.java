package com.mincom.gescomext.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FormeJuridique {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJury;
	private String titreJury;
	
  @JsonIgnore
  @OneToMany(mappedBy = "formeJuridique") 
  private List<Entreprise> entreprises;
	 
	
	public FormeJuridique(String titreJury) {
		super();
		this.titreJury = titreJury;
	}
	
	
}
