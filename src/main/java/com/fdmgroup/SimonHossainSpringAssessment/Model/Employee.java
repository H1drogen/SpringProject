package com.fdmgroup.SimonHossainSpringAssessment.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Simon Hossain
 * @version 1.0
 * This class implements and specifies a new employee
 */

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeGen")
	@SequenceGenerator(name = "employeeGen", sequenceName = "employeeSeq", allocationSize = 1)
	private long id;
	@NotBlank(message= "Input Valid First Name")
	private String firstName;
	@NotBlank(message= "Input Valid Last Name")
	private String lastName;
	@NotNull(message="Input Valid Hire Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hireDate;
	@NotBlank(message= "Input Valid Address")
	private String address;
	
	public Employee() {
		super();
	}
	
	/**
	 * This creates an employee object with a name, hire date and address (as specified in the constructor fields)
	 * @param firstName first name of the new employee
	 * @param lastName last name of the new employee
	 * @param hireDate hire date of the new employee
	 * @param address address of the new employee
	 */
	public Employee(String firstName, String lastName, LocalDate hireDate, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.address = address;
	}
	
	/**
	 * Returns the first name of an employee
	 * @return this employee's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets/edits an employee (object) first name characteristic
	 * @param firstName specify/change an employee's first Name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of an employee
	 * @return this employee's last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets/edits an employee (object) last name characteristic
	 * @param lastName specify/change an employee's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the date of hire for an employee
	 * @return this employee's date of hire
	 */
	public LocalDate getHireDate() {
		return hireDate;
	}

	/**
	 * Sets/edits an employee (object) hire date characteristic
	 * @param hireDate specify/change an employee's date of hire
	 */
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	/**
	 * Returns the address of an employee
	 * @return this employee's address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets/edits an employee (object) address characteristic
	 * @param address specify/change and employee's address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
