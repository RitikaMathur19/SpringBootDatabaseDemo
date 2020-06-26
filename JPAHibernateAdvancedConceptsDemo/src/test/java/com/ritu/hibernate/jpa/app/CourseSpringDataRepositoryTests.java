package com.ritu.hibernate.jpa.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.ritu.hibernate.jpa.app.entity.Course;
import com.ritu.hibernate.jpa.app.repository.CourseSpringDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateAdvancedConceptsDemoApplication.class)
class CourseSpringDataRepositoryTests {

	@Autowired
	CourseSpringDataRepository springDataRepo;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void findById_coursePresent() {
		Optional<Course> courseOptional = springDataRepo.findById(1005L);
		assertTrue(courseOptional.isPresent());
		logger.info("course present-->" + courseOptional);
	}

	@Test
	public void findById_courseNotPresent() {
		Optional<Course> courseOptional = springDataRepo.findById(1007L);
		assertFalse(courseOptional.isPresent());
		logger.info("course not present-->" + courseOptional);
	}

	/** CRUD operations testing */
	@Test
	public void playingAroundWithSpringDataJpa() {
		Course newCourse = new Course("Mythology");
		springDataRepo.save(newCourse);

		// course updated in the same method
		newCourse.setName("Mythological Stories");
		springDataRepo.save(newCourse);
		List<Course> allCourses = springDataRepo.findAll();
		logger.info("All about courses->" + allCourses + "\n No of courses-->" + springDataRepo.count());
	}

	/** Sorting operations testing */
	@Test
	public void sortingData() {

		List<Course> sortedCourses = springDataRepo.findAll(Sort.by(Sort.Direction.DESC, "name", "id"));
		logger.info("Sorted courses->" + sortedCourses);
	}

	/** pagination testing */
	@Test
	public void pagination() {
		Pageable pageRequest = PageRequest.of(0, 4);
		Page<Course> paginatedCourse = springDataRepo.findAll(pageRequest);
		int elements = paginatedCourse.getNumberOfElements();
		int totalPages = paginatedCourse.getTotalPages();

		logger.info("elements:" + elements + ",total pages :" + totalPages);

		while (totalPages != 0 && paginatedCourse.nextPageable() != null) {
			logger.info("Page no:" + pageRequest.getPageNumber());
			logger.info("Page content-->" + paginatedCourse.getContent().toString());
			pageRequest = paginatedCourse.nextPageable();
			paginatedCourse = springDataRepo.findAll(pageRequest);
			totalPages--;

		}

		logger.info("Pagination done!!");

	}

	@Test
	public void findCourseUsingName() {

		logger.info("Number of Dummy courses->" + springDataRepo.countByName("Dummy"));
		logger.info("Names of Dummy courses->" + springDataRepo.findByNameLikeDummy());
		logger.info("Named Query courses->" + springDataRepo.findAllCoursesByNamedQuery());
		logger.info("Names of Science courses->" + springDataRepo.findByNameLikeScience());
		logger.info("findByNameOrderByIdDesc courses->" + springDataRepo.findByNameOrderByIdDesc("Science"));
	}

}
