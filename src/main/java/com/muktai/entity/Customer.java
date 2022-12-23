package com.muktai.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cust_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE,generator = "myseq")
	private int id;
	
	@Column(name="myname",length = 50,nullable =false,unique = true)
	private String name;
	
	@Column(name="address",length = 50,nullable = false,unique = true)
	private String address;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dob",length = 50,nullable = false,unique = true)
	private Date dateOfBirth;
	
	@Temporal(TemporalType.DATE)
	@Column(name="currTime",length = 50,nullable = false,unique = true)
	private Date currentTime;
	
	@Transient
	private boolean isVaccinated;
	
	@Lob
	private byte[] photo;
	
	@Lob
	private char[] myFile;

	public Customer(int id, String name, String address, Date dateOfBirth, Date currentTime, boolean isVaccinated) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.currentTime = currentTime;
		this.isVaccinated = isVaccinated;
	}
	
	
}
