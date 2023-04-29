package org.internship.Mess_Coupon_System.service;

import java.util.List;

import org.internship.Mess_Coupon_System.model.Student;

public interface StudentService {
	List<Student> getAllStudents();
	void saveStudent(Student student);
	Student getStudentById(Long id);
	void deleteStudentById(Long id);
}
