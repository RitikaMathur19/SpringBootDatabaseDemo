package com.ritu.hibernate.jpa.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ritu.hibernate.jpa.app.entity.Course;
import com.ritu.hibernate.jpa.app.entity.Review;
import com.ritu.hibernate.jpa.app.entity.Student;
import com.ritu.hibernate.jpa.app.repository.CourseRepository;
import com.ritu.hibernate.jpa.app.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateAdvancedConceptsDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository studentRepo;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateAdvancedConceptsDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * logger.info("Searching course--->"+courseRepo.findById(1001L));
		 * courseRepo.deletebyId(1001L); courseRepo.save(new Course("Yogic Sciences"));
		 */
		// studentRepo.saveStudentWithPassport();

		// courseRepo.addHardCodedReviewsForCourse();
		List<Review> reviews = new ArrayList<Review>();
		
		// add two reviews to the course

		Review review1 = new Review("6", "Great Learning");
		Review review2 = new Review("7", "Wow course");
		reviews.add(review2);
		reviews.add(review1);
		courseRepo.addReviewForCourse(1004L, reviews);

		Student student = new Student("Jack Ma");
		Course course = new Course("Artificial Intelligence");
		studentRepo.insertStudentAndCourse(student, course);

	}

}
