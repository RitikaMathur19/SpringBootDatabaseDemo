package com.ritu.hibernate.jpa.app.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@Entity

//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

//@Inheritance(strategy=InheritanceType.JOINED)

//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
/**discriminator column is useful in single table inheritance**/
//@DiscriminatorColumn(name="Employee_Type")
public abstract class Employee {

	@Id
	@GeneratedValue
	long id;
	@Column(nullable=false)
	String name;
		protected Employee() {
	}

	public Employee(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Employee(String name) {
		this.name=name;
	}

	public long getId() {
		return id;
	}

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}



}
