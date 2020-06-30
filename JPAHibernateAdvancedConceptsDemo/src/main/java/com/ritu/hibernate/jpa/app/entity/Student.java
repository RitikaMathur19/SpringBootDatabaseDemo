package com.ritu.hibernate.jpa.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student {

	@Id
	@GeneratedValue
	long id;
	@Column(nullable = false)
	String name;

	/* Assuming Student can have only one passport */
	@OneToOne(fetch = FetchType.LAZY)
	
	@JsonIgnore
	private Passport passport;
	
	/* One student can submit multiple reviews */
	@OneToMany(fetch=FetchType.LAZY)
	private List<Review> reviews = new ArrayList<>();
	
	/* A student can join many courses and vice versa */
	// By default many to many is lazy
	@ManyToMany
	@JoinTable(name="STUDENT_COURSE",joinColumns=@JoinColumn(name="STUDENT_ID"),inverseJoinColumns=@JoinColumn(name = "Course_id"))
	private List<Course> courses = new  ArrayList<>();

	@Embedded
	private Address address;
	protected Student() {
	}

	public Student(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Student(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Review> getReviews() {
		return reviews;
	}

	public void addReviews(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	public List<Course> getCourses() {
		return courses;
	}

	public void addCourses(Course course) {
		this.courses.add(course);
	}
	public void removeCourses(Course course) {
		this.courses.remove(course);
	}


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
