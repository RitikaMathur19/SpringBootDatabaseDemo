package com.ritu.hibernate.jpa.app.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public  class FullTimeEmployee extends Employee{

	private BigDecimal salary;
	

		protected FullTimeEmployee() {
	}

	public FullTimeEmployee( String name,BigDecimal salary) {
		super(name);
		this.salary = salary;
		
	}
	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "FullTimeEmployee [salary=" + salary + ", id=" + id + ", name=" + name + ", getName()="
				+ getName() + ", getId()=" + getId() + ", toString()="+ "]";
	}

	



}
