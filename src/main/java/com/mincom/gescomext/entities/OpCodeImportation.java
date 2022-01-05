package com.mincom.gescomext.entities;

import java.util.Date;
import java.util.List;

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
public class OpCodeImportation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOp;
	private Integer numDocOp;
	private String typeOp;
	private String montantOp;
	private Date dateOp;
	private Integer activePaimentOp;
	private String activeSignatureOp;
	private String typeSignatureOp;
	private String activeApprobationOp;
	private String typeCodeOp;
	
	@ManyToOne
	@JoinColumn(name = "codeimport_id")
	private CodeImportation codeImportation;
	
	@OneToMany(mappedBy = "opCodeImportation")
	private List<TraitementOpCodeImportation> traitementOpCodeImportation;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
