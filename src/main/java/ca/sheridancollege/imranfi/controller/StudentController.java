package ca.sheridancollege.imranfi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.imranfi.Beans.Student;
import ca.sheridancollege.imranfi.repositories.StudentRepository;


//diffr btw restcontroller and controller look in your templates for per named string source in case of controller
//rest just returns whatever we are returning it just prints it to the webbpage 
@RestController
public class StudentController {

	private StudentRepository stuRepo; 
	
	
	
	public StudentController(StudentRepository stuRepo) {
		super();
		this.stuRepo = stuRepo;
	}

	//this is read i.e get

	@GetMapping("/students")
	public List<Student> getStudents() {
		return stuRepo.getStudents() ;
	}
	
	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable int id) {
		return stuRepo.getStudentById(id);
	}
	
	//this is create ie post 
	@PostMapping(value="/students", headers={"Content-type=application/json"})
	public String addStudent(@RequestBody Student student) {
		stuRepo.addStudent(student);
		return "Student was added";
	}
	
	//put : update 
	
	@PutMapping(value="/students", headers={"Content-type=application/json"})
	public String updateStudents(@RequestBody List<Student> students) {	
		stuRepo.editAllStudents(students);
		return "Students are updated";
		
	}
	
	@PutMapping(value="/students/{id}", headers={"Content-type=application/json"})
	public String updateStudentById(@RequestBody Student student) {	
		stuRepo.editStudentsById(student);
		return "Student is updated";
		
	}
	
	//delete 
	
	//@DeleteMapping(value="/students/{id}", headers={"Content-type=application/json"})
	@DeleteMapping("/students/{id}")
	public String deletStudentById( @PathVariable int id) {	
		stuRepo.deleteStudentById(id);
		return "requested student is deleted";
		
	}
	//@DeleteMapping(value="/students", headers={"Content-type=application/json"})
	@DeleteMapping("/students")
	public String deleteStudents() {	
		stuRepo.deleteAllStudents();
		return "Students are deleted";
		
	}
	
	
	
	
	
}
