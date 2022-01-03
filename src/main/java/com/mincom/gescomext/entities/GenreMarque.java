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
public class GenreMarque {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGen;
	
	@NotNull
	@Size (min = 3,max = 255)
	private String nomGen;
	
	@OneToMany(mappedBy = "genreMarque")
	private List<CodeImportation> codeImportation;
	
	
}
