package com.ritu.hibernate.jpa.app.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public  class PartTimeEmployee extends Employee{

	private BigDecimal hourlyWage;
		protected PartTimeEmployee() {
	}

	public PartTimeEmployee( String name,BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
		
	}

	@Override
	public String toString() {
		return "PartTimeEmployee [hourlyWage=" + hourlyWage + ", id=" + id + ", name=" + name + ", getName()="
				+ getName() + ", getId()=" + getId() + ", toString()="+ "]";
	}

	



}
