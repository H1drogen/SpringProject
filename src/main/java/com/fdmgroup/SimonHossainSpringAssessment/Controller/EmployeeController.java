package com.fdmgroup.SimonHossainSpringAssessment.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.SimonHossainSpringAssessment.Model.Employee;
import com.fdmgroup.SimonHossainSpringAssessment.Service.EmployeeService;

@Controller
public class EmployeeController {
	private EmployeeService empservice;
	
	@Autowired
	public EmployeeController(EmployeeService empservice) {
		super();
		this.empservice = empservice;
	}
	
	/**
	 * Implements Spring Web functionality for the Index HTML page
	 * @return display the Index HTML page
	 */
	@GetMapping("/")
	public String index() {
		return "Index";
	}
	
	/**
	 * Implements Spring Web functionality that enables the creation of a new Employee object
	 * @param emp1 The Employee object to be added
	 * @return display the CreateEmployee HTML page
	 */
	@GetMapping("/CreateEmployee")
	public String addEmployee(Employee emp1) {
		return "CreateEmployee";
	}
	
	/**
	 * Implements Spring Web functionality to save an employee in the database. 
	 * If any validation rules have not been met, then the HTML page will display errors and not save the Employee
	 * @param emp1 Employee object to be saved
	 * @param result User-input Employee characteristics to be saved in the database
	 * @return display the CreateEmployee HTML page (if errors encountered) or Index HTML page
	 */
	@PostMapping("/SaveEmployee")
	public String saveEmployee(@Valid Employee emp1, BindingResult result) { 
		if(result.hasErrors()){
		return "CreateEmployee";
	} else {
		this.empservice.saveEmployee(emp1);
		return "redirect:/";
	}}
	
	/**
	 * Implements Spring Web functionality for the FindByAddress HTML page
	 * @return display the FindByAddress HTML Page
	 */
	@GetMapping("/FindByAddress")
	public String findByAddress() {
		return "FindByAddress";
	}
	
	/**
	 * Implements Spring Web functionality to get a list of employees that (partially) match a given address
	 * An error page is displayed if there are no employees found
	 * @param address The (partial) address to be used to filter through employees
	 * @param model allows Thymeleaf to iterate through the existing list of Employees 
	 * @return display the Results HTML Page
	 */
	@PostMapping("/FindByAddress")
	public String findByAddress(@RequestParam String address, Model model) {
		List<Employee> EmployeeList = this.empservice.findEmployeeByAddress(address);
		if (EmployeeList.size() > 0) {
			model.addAttribute("employees", EmployeeList);
		} else {
			model.addAttribute("employees", null);
		} return "Results";
	}
	
	/**
	 * Implements Spring Web functionality for the FindByFirstAndLastName HTML page
	 * @return
	 */
	@GetMapping("/FindByFirstAndLastName")
	public String findEmployeeByFirstAndLastName() {
		return "FindByFirstAndLastName";
	}
	
	/**
	 * Implements Spring Web functionality to get a list of employees that exactly match a given full name
	 * @param firstName The exact full name of the target Employee
	 * @param lastName The exact last name of the target employee
	 * @param model allows Thymeleaf to iterate through the existing list of Employees 
	 * @return display the Results HTML page
	 */
	@PostMapping("/FindByFirstAndLastName")
	public String findEmployeeByFirstAndLastName(@RequestParam String firstName,@RequestParam String lastName, Model model) {
		List<Employee> EmployeeList = this.empservice.findEmployeeByFirstAndLastName(firstName, lastName);
		if (EmployeeList.size() > 0) {
			model.addAttribute("employees", EmployeeList);
		} else {
			model.addAttribute("employees", null);
		} 
		return "Results";
	}
}
