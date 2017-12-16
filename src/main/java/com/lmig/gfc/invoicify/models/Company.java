package com.lmig.gfc.invoicify.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

// This needs to be an entity
@Entity
public class Company {

	// This needs an id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// This needs a name
	@Column(length = 50)
	private String name;
	
	// This needs a list of invoice objects named invoices as one-to-many relationship mapped by "company"
	@JsonIgnore
	@OneToMany(mappedBy = "company")
	private List<Invoice> invoices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	// Lots of getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Invoice> getInvoice() {
		return invoices;
	}

	public void setInvoice(List<Invoice> invoices) {
		this.invoices = invoices;
	}


	
}
