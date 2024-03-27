package com.fdmgroup.SimonHossainSpringAssessment.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.SimonHossainSpringAssessment.Model.Employee;
import com.fdmgroup.SimonHossainSpringAssessment.Repository.EmployeeRepository;

@Service
public class EmployeeService {
	private EmployeeRepository emprepo;
	
	@Autowired
	public EmployeeService(EmployeeRepository emprepo) {
		this.emprepo = emprepo;
	}
	
	/**
	 * This method saves an Employee to the database
	 * @param emp1 the Employee object that is due to be saved in the database
	 */
	public void saveEmployee(Employee emp1) {
		this.emprepo.save(emp1);
	}
	
	/**
	 * This finds a list of Employees using a method that filters with a specified address
	 * @param address The (partial) address of the employee you are searching
	 * @return a list of employees who (partially) match the address parameter
	 */
	public List<Employee> findEmployeeByAddress(String address) {
		return this.emprepo.searchByAddressLike(address);
	}
	
	/**
	 * This finds a list of Employees using a method that filters with a specified full name
	 * @param firstName The exact first name of the employee you are searching
	 * @param lastName The exact second name of the employee you are searching
	 * @return a list of employees who match the name parameters
	 */
	public List<Employee> findEmployeeByFirstAndLastName(String firstName, String lastName){
		return this.emprepo.searchByFirstNameAndLastNameLike(firstName, lastName);
	}


	

}
