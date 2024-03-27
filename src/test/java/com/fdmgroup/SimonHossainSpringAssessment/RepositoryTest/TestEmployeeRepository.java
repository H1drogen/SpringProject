package com.fdmgroup.SimonHossainSpringAssessment.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdmgroup.SimonHossainSpringAssessment.Model.Employee;
import com.fdmgroup.SimonHossainSpringAssessment.Repository.EmployeeRepository;

@DataJpaTest
public class TestEmployeeRepository {
	@Autowired
	private EmployeeRepository empRepo;
	
	@Test
	public void test_searchByAddressLike_ReturnsMatchingAddress() {
		Employee test = new Employee("Simon","Hossain",LocalDate.of(2017, 10, 10),"123 Fake Address");
		this.empRepo.save(test);
		assertEquals(test, this.empRepo.searchByAddressLike("123").get(0));
	}
	
	@Test
	public void test_searchByFirstNameAndLastNameLike_ReturnsMatchingName() {
		Employee test1 = new Employee("Simon","Hossain",LocalDate.of(2017, 10, 10),"123 Fake Address");
		Employee test2 = new Employee("Simon","Maridan",LocalDate.of(2017, 10, 10),"123 Fake Address");
		this.empRepo.save(test1);
		this.empRepo.save(test2);
		assertEquals(test2, this.empRepo.searchByFirstNameAndLastNameLike("Simon", "Maridan").get(0));
	}
}
