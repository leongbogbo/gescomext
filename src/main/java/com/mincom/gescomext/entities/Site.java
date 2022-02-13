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
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSite;
	
	@NotNull
	@Size (min = 2,max = 255)
	private String nomSite;

	@OneToMany(mappedBy = "site")
	@JsonIgnore
	private List<User> user;
	
}
