package com.ritu.SpringBootDatabase.jdbcDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ritu.SpringBootDatabase.entity.Person;

@Repository
public class PersonJdbcDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//defining an inner class for row mapper as it is to be used only in this DAO
	
	class PersonRowMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("name"));
			person.setBirthdate(rs.getTimestamp("birth_date"));
			return person;
		}
		
	}
	// find all methods

	/*
	 * public List<Person> findAll() {
	 * 
	 * return jdbcTemplate.query("Select * from person ", new
	 * BeanPropertyRowMapper<Person>(Person.class));
	 * 
	 * }
	 */
	//using Person Row mapper
	public List<Person> findAll() {

		return jdbcTemplate.query("Select * from person ", new PersonRowMapper());

	}
//using the default Bean Property row Mapper
	public Person findById(int id) {

		return jdbcTemplate.queryForObject("Select * from person where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));

	}

	public List<Person> findByName(String name) {

		return jdbcTemplate.query("Select * from person where name = ?", new Object[] { name },
				new BeanPropertyRowMapper<Person>(Person.class));

	}

	public int deleteById(int id) {

		return jdbcTemplate.update("Delete from person where id = ?", new Object[] { id });

	}

	public int deleteByLocationName(String location, String name) {

		return jdbcTemplate.update("Delete from person where location = ? or name =?", new Object[] { location, name });

	}

	public int insert(Person person) {

		return jdbcTemplate.update("insert into person (id,name,location,birth_date) " + " values (?,?,?,?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(),
						new Timestamp(person.getBirthdate().getTime()) });

	}

	public int update(Person person) {

		return jdbcTemplate.update("update person set name= ?, location = ?, birth_date = ?  " + " where id =? ",
				new Object[] { person.getName(), person.getLocation(), new Timestamp(person.getBirthdate().getTime()),
						person.getId() });

	}

	public int count() {
		return this.jdbcTemplate.queryForObject("select count(*) from person", Integer.class);
	}
}
