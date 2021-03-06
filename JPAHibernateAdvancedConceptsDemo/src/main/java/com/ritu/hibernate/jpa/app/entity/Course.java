package com.ritu.hibernate.jpa.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="course") //if the name of the table is different than the entity
@NamedQueries(value= {
		@NamedQuery(name="query_get_all_courses",query="Select c from Course c"),
		@NamedQuery(name="query_get_all_courses_joinFetch",query="Select c from Course c JOIN FETCH c.students s "),
		@NamedQuery(name="query_get_angular_courses",query="Select c from Course c where name like '%Angular%' ")
})
@Cacheable
@SQLDelete(sql="update course set is_deleted=true where id =?")
@Where(clause = "is_deleted=false")
public class Course {
	private static Logger logger = LoggerFactory.getLogger(Course.class);
	@Id
	@GeneratedValue
	long id;
	@Column(nullable=false)
	String name;
	
	//used for soft delete
	private boolean isDeleted;
	

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy="course")
	
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany(mappedBy="courses")
	@JsonIgnore
	private List<Student> students = new  ArrayList<>();
	
	protected Course() {}
	
	public Course(String name) {
		super();
		this.name = name;
	}

	@PreRemove
	public void preDelete() {
		logger.info("Calling pre remove...");
		this.isDeleted=true;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
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
	
	public List<Student> getStudents() {
		return students;
	}

	public void addStudents(Student student) {
		this.students.add(student);
	}
	public void removeStudents(Student student) {
		this.students.remove(student);
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

	
}
