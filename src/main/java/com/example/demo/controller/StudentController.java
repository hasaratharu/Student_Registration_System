package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Degree;
import com.example.demo.model.Student;
import com.example.demo.service.DegreeService;
import com.example.demo.service.StudentService;
@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DegreeService degreeService;
	
	@GetMapping("/")
	public String index() {
//		model.addAttribute("listStudents", studentService.getAllStudents());
		return "index";
	}
	
	@GetMapping("/viewStudents")
	public String viewStudentList(Model model) {
		model.addAttribute("listStudents", studentService.getAllStudents());
		return "viewStudent";
	}
	
//	@PostMapping("/saveStudent")
//	public String saveStudent(@ModelAttribute("student") Student student) {
//		studentService.saveStudent(student);
//		return "redirect:/viewStudents";
//	}
	
	@PostMapping("/saveStudent/{id}")
	public String saveStudent(@PathVariable( value = "id") long id, @ModelAttribute("student") Student student) {
		Degree degree = this.degreeService.getDegreeById(id);
		degree.getStudents().add(student);
		this.degreeService.saveDegree(degree);
		return "redirect:/viewStudents";
	}
	
	@PostMapping("/updateStudent/{id}")
	public String updateStudent(@PathVariable( value = "id") long id, @ModelAttribute("student") Student student) {
		Degree degree = this.degreeService.getDegreeById(id);
//		
		this.studentService.saveStudent(student);
		degree.getStudents().add(student);
		this.degreeService.saveDegree(degree);
		return "redirect:/viewStudents";
	}
	
	
	@GetMapping("/showNewStudentForm/{id}")
	public String showNewStudentForm(@PathVariable( value = "id") long pid, Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		model.addAttribute("pid", pid);
		return "addNewStudent";
	}
	
	@GetMapping("/showFormForUpdate/{id}/{did}")
	public String showFormForUpdate(@PathVariable( value = "id") long id,@PathVariable( value = "did") long did,  Model model) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		model.addAttribute("did", did);
		return "updateStudent";	
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable( value = "id") long id) {
		this.studentService.deleteStudentById(id);
		return "redirect:/viewStudents";
		
	}
}
