package ca.sheridancollege.imranfi.Beans;

public class Student {
	
private int id;
private  String name;
private int grade; 
private String letterGrade;



private String calculateLetterGrade(int grade) {
	if (grade >=80) return "A";
	else if (grade >=70) return "B";
	else if (grade >=60) return "C";
	else if (grade >=50) return "D";
	else return "F";
	
}

public Student() {}

public Student(String name, int grade) {
	this.name = name;
	this.grade = grade;
	this.letterGrade=calculateLetterGrade(grade);
}

public Student(int id, String name, int grade, String letterGrade) {
	this.id = id;
	this.name = name;
	this.grade = grade;
	this.letterGrade = letterGrade;
}


public void setGrade(int grade) {
	this.grade=grade;
	this.letterGrade=this.calculateLetterGrade(grade);
}



public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getLetterGrade() {
	return letterGrade;
}

public int getGrade() {
	return grade;
}

@Override
public String toString() {
	return "Student [id=" + id + ", name=" + name + ", grade=" + grade + ", letterGrade=" + letterGrade + "]";
}

}
