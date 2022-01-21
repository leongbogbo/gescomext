package com.mincom.gescomext.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TraitementOpCodeImportation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTrait;
	private String statutTrait;
	private Date dateTrait;
	
	@ManyToOne
	@JoinColumn(name = "opcodeimport_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private OpCodeImportation opCodeImportation;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
}
