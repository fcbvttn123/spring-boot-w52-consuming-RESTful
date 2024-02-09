package ca.sheridancollege.vutran.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ca.sheridancollege.vutran.services.GradeService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String name;
	private int grade;
	private String letterGrade;
	private boolean attended;
	private boolean participated;
	
	@Transient
	@JsonIgnore
	private GradeService gradeService = new GradeService();
	
	public Student(String name, int grade, boolean attended, boolean participated) {
		this.name = name;
		this.grade = grade;
		this.letterGrade = gradeService.assignLetter(grade);
		this.attended = attended;
		this.participated = participated;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
		this.letterGrade = gradeService.assignLetter(grade);
	}
}
