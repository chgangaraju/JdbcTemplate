package com.imaginea.jdbctemplate;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class StudentJDBCTemplate implements StudentDAO {
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void create(String name, Integer age) {
		String SQL = "insert into Student (name, age) values (?, ?)";
		jdbcTemplate.update(SQL, new Object[] { name, age });
		System.out.println("Created Record Name=" + name + " Age=" + age);
		return;
	}

	public Student getStudent(Integer id) {
		String SQL = "select * from Student where id = ?";
		Student student = (Student) jdbcTemplate.queryForObject(SQL,
				new Object[] { id }, new StudentMapper());
		return student;
	}

	@SuppressWarnings("unchecked")
	public List<Student> listStudents() {
		String SQL = "select * from Student";
		List<Student> students = jdbcTemplate.query(SQL,
				new StudentMapper());
		return students;
	}

	public void delete(Integer id) {
		String SQL = "delete from Student where id = ?";
		jdbcTemplate.update(SQL, new Object[] { id });
		System.out.println("Deleted Record with ID = " + id);
		return;
	}

	public void update(Integer id, Integer age) {
		String SQL = "update Student set age = ? where id = ?";
		jdbcTemplate.update(SQL, new Object[] { age, id });
		System.out.println("Updated Record with ID = " + id);
		return;
	}
}