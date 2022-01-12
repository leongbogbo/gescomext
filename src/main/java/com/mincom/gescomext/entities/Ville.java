package com.mincom.gescomext.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ville {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVille;	
	@NotNull
	@Size (min = 3,max = 255)
	private String nomVille;
	
	@JsonIgnore
	@OneToMany(mappedBy = "ville")
	private List<Commune> communes;
	
	public Ville(String nomVille) {
		super();
		this.nomVille = nomVille;
	}
	
	
}
