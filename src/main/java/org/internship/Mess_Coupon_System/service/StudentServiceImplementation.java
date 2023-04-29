package org.internship.Mess_Coupon_System.service;

import java.util.List;
import java.util.Optional;

import org.internship.Mess_Coupon_System.model.Student;
import org.internship.Mess_Coupon_System.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImplementation implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	@Override
	public void saveStudent(Student student) {
		this.studentRepository.save(student);
	}
	@Override
	public Student getStudentById(Long id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = null;
		if(optional.isPresent()) {
			student = optional.get();
		} else {
			throw new RuntimeException("Student not found");
		}
		return student;
	}
	@Override
	public void deleteStudentById(Long id) {
		this.studentRepository.deleteById(id);
	}
	
}
