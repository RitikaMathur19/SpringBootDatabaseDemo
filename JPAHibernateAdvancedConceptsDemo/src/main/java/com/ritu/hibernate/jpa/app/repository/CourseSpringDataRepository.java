package com.ritu.hibernate.jpa.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ritu.hibernate.jpa.app.entity.Course;


/**Displaying the Spring Data JPA functions 
 * */
public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{
	List<Course> findByNameAndId(String name,Long id);
	List<Course> findByNameOrderByIdDesc(String name);
	int countByName(String name);
	List<Course> deleteByName(String name);
	
	//custom queries
	//JPQL query
	@Query("Select c from Course c where name like '%Dummy%'")
	List<Course> findByNameLikeDummy();

	//Native Query
	@Query(value="Select * from Course c where name like '%Science'",nativeQuery=true)
	List<Course> findByNameLikeScience();
	
	//Named Query
	@Query(name="query_get_all_courses")
	List<Course> findAllCoursesByNamedQuery();
}
