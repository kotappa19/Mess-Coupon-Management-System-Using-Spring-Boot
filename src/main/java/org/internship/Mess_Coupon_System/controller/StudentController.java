package org.internship.Mess_Coupon_System.controller;

import java.util.Arrays;
import java.util.List;

import org.internship.Mess_Coupon_System.model.Student;
import org.internship.Mess_Coupon_System.repository.StudentRepository;
import org.internship.Mess_Coupon_System.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		Student student = new Student();
		List<String> blocks = Arrays.asList("A", "B", "C", "D");
		List<String> years = Arrays.asList("First Year", "Second Year", "Third Year", "Fourth Year");
		List<String> hostels = Arrays.asList("Kumardhara Hostel", "New Boys Hostel", "Netravathi Hostel", "Tungabhadra Hostel", "Godharvari Hostel");
		model.addAttribute("hostels", hostels);
		model.addAttribute("years", years);
		model.addAttribute("blocks", blocks);
		model.addAttribute("students", student);
		return "new-student";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("students") Student student, Model model) {
		Student student2 = studentRepository.findStudentByUsn(student.getUsn());
		Student student3 = studentRepository.findStudentByPhone(student.getPhone());
		if(student2 != null) {
			String error = "Student with usn already found";
			List<String> blocks = Arrays.asList("A", "B", "C", "D");
			List<String> years = Arrays.asList("First Year", "Second Year", "Third Year", "Fourth Year");
			List<String> hostels = Arrays.asList("Kumardhara Hostel", "New Boys Hostel", "Netravathi Hostel", "Tungabhadra Hostel", "Godharvari Hostel");
			model.addAttribute("hostels", hostels);
			model.addAttribute("years", years);
			model.addAttribute("blocks", blocks);
			model.addAttribute("error", error);
			return "new-student";
		} else if(student3 != null){
			String perror = "Phone number is already registered";
			List<String> blocks = Arrays.asList("A", "B", "C", "D");
			List<String> years = Arrays.asList("First Year", "Second Year", "Third Year", "Fourth Year");
			List<String> hostels = Arrays.asList("Kumardhara Hostel", "New Boys Hostel", "Netravathi Hostel", "Tungabhadra Hostel", "Godharvari Hostel");
			model.addAttribute("hostels", hostels);
			model.addAttribute("years", years);
			model.addAttribute("blocks", blocks);
			model.addAttribute("perror", perror);
			return "new-student";
		}
		else {
			studentService.saveStudent(student);
			return "redirect:/";
		}
	}
	
	@PostMapping("/updateStudent")
	public String updateStudent(@ModelAttribute("students") Student student, Model model) {
//		Student student3 = studentRepository.findStudentByPhone(student.getPhone());
//		if(student3 != null){
//			String perror = "Phone number is already registered";
//			List<String> blocks = Arrays.asList("A", "B", "C", "D");
//			List<String> years = Arrays.asList("First Year", "Second Year", "Third Year", "Fourth Year");
//			List<String> hostels = Arrays.asList("Kumardhara Hostel", "New Boys Hostel", "Netravathi Hostel", "Tungabhadra Hostel", "Godharvari Hostel");
//			model.addAttribute("hostels", hostels);
//			model.addAttribute("years", years);
//			model.addAttribute("blocks", blocks);
//			model.addAttribute("perror", perror);
//			return "update-student";
//		}
//		else {
			studentService.saveStudent(student);
			return "redirect:/";
//		}
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
		Student student = studentService.getStudentById(id);
		List<String> blocks = Arrays.asList("A", "B", "C", "D");
		List<String> years = Arrays.asList("First Year", "Second Year", "Third Year", "Fourth Year");
		List<String> hostels = Arrays.asList("Kumardhara Hostel", "New Boys Hostel", "Netravathi Hostel", "Tungabhadra Hostel", "Godharvari Hostel");
		model.addAttribute("hostels", hostels);
		model.addAttribute("years", years);
		model.addAttribute("blocks", blocks);
		model.addAttribute("students", student);
		return "update-student";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable(value = "id")Long id) {
		this.studentService.deleteStudentById(id);
		return "redirect:/";
		
	}
}
