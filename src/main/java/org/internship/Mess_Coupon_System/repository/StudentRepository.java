package org.internship.Mess_Coupon_System.repository;


import org.internship.Mess_Coupon_System.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	Student findStudentByUsn(String usn);
	Student findStudentByPhone(String phone);
}
