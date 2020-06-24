package com.ritu.hibernate.jpa.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	long id;
	@Column(nullable=false)
	String number;
	
	//without mappedby attribute there is duplication of information in DB 
	@OneToOne(fetch=FetchType.LAZY,mappedBy="passport")
	private Student student;
	
	protected Passport() {
	}

	public Passport(Long id, String number) {
		this.id = id;
		this.number = number;
	}

	public Passport(String number) {
		this.number=number;
	}

	
		public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number + "]";
	}



}
