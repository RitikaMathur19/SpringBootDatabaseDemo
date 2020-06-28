package com.ritu.hibernate.jpa.app;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ritu.hibernate.jpa.app.entity.Course;
import com.ritu.hibernate.jpa.app.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateAdvancedConceptsDemoApplication.class)
public class PerformanceTuningTest {

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	@Transactional
	public void creatingNPlusOneProblem() {
		List<Course> courseList = em.createNamedQuery("query_get_all_courses").getResultList();
		for (Course c : courseList) {
			logger.info("Course{} Student{}", c, c.getStudents());

		}
	}

	@Test
	@Transactional
	public void solvingNPlusOneProblem_entityGraph() {
		EntityGraph<Course> courseEntityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> courseSubgraph = courseEntityGraph.addSubgraph("students");
		List<Course> courseList = em.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", courseEntityGraph).getResultList();

		for (Course c : courseList) {
			logger.info("Course{} Student{}", c, c.getStudents());

		}
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblem_joinFetch() {
		EntityGraph<Course> courseEntityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> courseSubgraph = courseEntityGraph.addSubgraph("students");
		List<Course> courseList = em.createNamedQuery("query_get_all_courses_joinFetch", Course.class)
				.getResultList();

		for (Course c : courseList) {
			logger.info("Course{} Student{}", c, c.getStudents());

		}
	}


}
