package com.mincom.gescomext.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Nationalite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNat;
	private String titreNat;
	
	@OneToMany(mappedBy = "nationalite")
	private List<Proprietaire> proprietaires;
	
	public Nationalite(String titreNat) {
		super();
		this.titreNat = titreNat;
	}
	
	
}
