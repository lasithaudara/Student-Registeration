package application;
import java.sql.Date;

public class Users {
	
	String studentName, subjects;
	int phoneNumber;
	Date birthDate;
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public String getSubjects() {
		return subjects;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void users(String studentName, String subjects, int phoneNumber, Date birthDate) {
		this.studentName = studentName;
		this.subjects = subjects;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
	}

}
