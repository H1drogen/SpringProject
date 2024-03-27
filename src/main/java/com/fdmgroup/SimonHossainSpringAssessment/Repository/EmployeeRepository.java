package com.fdmgroup.SimonHossainSpringAssessment.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.SimonHossainSpringAssessment.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> searchByFirstNameAndLastNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);

	public List<Employee> searchByAddressLike(String address);
	
}
