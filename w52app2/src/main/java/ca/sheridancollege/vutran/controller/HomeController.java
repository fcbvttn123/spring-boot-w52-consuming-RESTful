package ca.sheridancollege.vutran.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.vutran.beans.Student;

@Controller
public class HomeController {
	
	final private String REST_URL = "http://localhost:50000/api/v1/students/";
	
	@GetMapping("/") 
	public String index(Model model, RestTemplate restTemplate) {
		ResponseEntity<ArrayList<Student>> responseEntity =
				(ResponseEntity<ArrayList<Student>>) restTemplate.getForEntity(REST_URL, new ArrayList<Student>().getClass());
		model.addAttribute("studentList", responseEntity.getBody());
		return "index";
	}
	
	@GetMapping("/blankStudentForm")
	public String blankStudentForm(Model model) {
		model.addAttribute("student", new Student());
		return "studentForm";
	}
	
	@PostMapping("/insertStudent")
	public String insertStudent(Model model, @ModelAttribute Student student, RestTemplate restTemplate) {
		System.out.println(student.getGrade());
		restTemplate.postForLocation(REST_URL, student);
		ResponseEntity<ArrayList<Student>> responseEntity =
		(ResponseEntity<ArrayList<Student>>) restTemplate.getForEntity(REST_URL, new ArrayList<Student>().getClass());
		model.addAttribute("studentList", responseEntity.getBody());
		return "index";
	}
	
	@GetMapping("/deleteStudent/{id}") // from a generated hyperlink unique to each student in our display
	public String deleteStudent(Model model, @PathVariable("id") int id, RestTemplate restTemplate) {
		restTemplate.delete(REST_URL + id);
		ResponseEntity<ArrayList<Student>> responseEntity =
		(ResponseEntity<ArrayList<Student>>) restTemplate.getForEntity(REST_URL, new ArrayList<Student>().getClass());
		model.addAttribute("studentList", responseEntity.getBody());
		return "index";
	}
	
	@GetMapping("/getStudent/{id}")
	public String getStudent(Model model, @PathVariable("id") int id, RestTemplate restTemplate) {
		ResponseEntity<Student> responseEntity =
					restTemplate.getForEntity(REST_URL + id, Student.class);
		model.addAttribute("student", responseEntity.getBody());
		return "studentForm";
	}
	
	@PostMapping("/editStudent") // from a generated hyperlink unique to each student in our display
	public String editStudent(Model model, @ModelAttribute Student student, RestTemplate restTemplate) {
		restTemplate.put(REST_URL + student.getId(), student);
		ResponseEntity<ArrayList<Student>> responseEntity =
					(ResponseEntity<ArrayList<Student>>) restTemplate.getForEntity(REST_URL, new ArrayList<Student>().getClass());
		model.addAttribute("studentList", responseEntity.getBody());
		return "index";
	}
	
}
