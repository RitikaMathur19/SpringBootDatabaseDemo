package com.ritu.hibernate.jpa.app.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	private String rating;

	/* One review is given by one student */
	@ManyToOne
	private Student student;

	//A review is only associated with a single course
	// by default many to one is Eager fetch
	@ManyToOne(fetch = FetchType.LAZY)
	private Course course;

	private String description;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	protected Review() {
	}

	public Review(String rating, String description) {
		this.rating = rating;
		this.description = description;
	}

	public Review(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	
	

	@Override
	public String toString() {
		return "Review [id=" + id + ", description=" + description + " ,rating " + rating + " ]";
	}

}
