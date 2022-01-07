package com.mincom.gescomext.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ActionListe {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idActPro;
	
	@NotNull
	@Size (min = 2,max = 255)
	private String titreActPro;

	@NotNull
	@Size (min = 3,max = 255)
	private String lienActPro;
	
	public ActionListe(String titreActPro, String lienActPro) {
		super();
		this.titreActPro = titreActPro;
		this.lienActPro = lienActPro;
	}
	
	
}
