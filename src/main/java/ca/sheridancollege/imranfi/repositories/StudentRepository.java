package ca.sheridancollege.imranfi.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.imranfi.Beans.Student;

@Repository 
public class StudentRepository {

	private NamedParameterJdbcTemplate jdbc;

	public StudentRepository(NamedParameterJdbcTemplate jdbc) {
		super();
		this.jdbc = jdbc;
	}
	//this is get because this is read 
	
	public List<Student> getStudents(){
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM students";
		
		ArrayList<Student> students = (ArrayList<Student>)jdbc.query(query, parameters, 
				new BeanPropertyRowMapper<> (Student.class));
	
		System.out.println(students);
		return students;
		
		
	}
	
	public Student getStudentById(int id){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM students WHERE id =:id";
		
		//name parameter 
		parameters.addValue("id", id);
		
		ArrayList<Student> students = (ArrayList<Student>)jdbc.query(query, parameters, 
				new BeanPropertyRowMapper<> (Student.class));
	
		
		if(!(students.isEmpty())) {
			
			return students.get(0);
		}
		return null;
		
	}
	
	//post because this is view
	
	public void addStudent(Student student) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO students (name, grade,letterGrade) VALUES (:name, :grade, :Lgrade) ";
	
		parameters.addValue("name", student.getName());
		parameters.addValue("grade", student.getGrade());
		parameters.addValue("Lgrade", student.getLetterGrade());
	
		jdbc.update(query, parameters);
		
		
	}
	
	public void deleteAllStudents() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM students";	
		jdbc.update(query, parameters);
		
	}
	
	/*
	public void resartIndex() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "alter table students set AUTO_INCREMENT=1";	
		jdbc.update(query, parameters);
		
	}*/
	
	public void deleteStudentById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM students where id=:id";	
		parameters.addValue("id", id);
		
		jdbc.update(query, parameters);
		
	}
	
	public void editAllStudents(List<Student> students) {
	
		MapSqlParameterSource parameters = new 	MapSqlParameterSource();
		for(Student student : students) {
		
		String query = "UPDATE students SET name =:name , "
				+ "grade =:grade ,"
				+ "letterGrade=:Lgrade WHERE id=:id" ;

		parameters.addValue("id", student.getId());
		parameters.addValue("name", student.getName());
		parameters.addValue("grade", student.getGrade());
		parameters.addValue("Lgrade", student.getLetterGrade());
		jdbc.update(query, parameters);
		}
		
		System.out.println(students);
	}
	
	public void editStudentsById(Student student) {
		
			MapSqlParameterSource parameters = new 	MapSqlParameterSource();
					
					String query = "UPDATE students SET name = :name , "
							+ "grade = :grade , "
							+ "letterGrade= :Lgrade WHERE id= :id" ;
			
					parameters.addValue("id", student.getId());
					parameters.addValue("name", student.getName());
					parameters.addValue("grade", student.getGrade());
					parameters.addValue("Lgrade", student.getLetterGrade());
					jdbc.update(query, parameters);
					
					System.out.println(student);
		}

	
	
	

	
}
