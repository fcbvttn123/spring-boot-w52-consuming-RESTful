package ca.sheridancollege.vutran.beans;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Student {
	private Long id;
	@NonNull
	private String name;
	private int grade;
	private String letterGrade;
	private boolean attended;
	private boolean participated;
}
