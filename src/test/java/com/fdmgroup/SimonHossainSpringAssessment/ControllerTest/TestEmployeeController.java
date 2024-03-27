package com.fdmgroup.SimonHossainSpringAssessment.ControllerTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fdmgroup.SimonHossainSpringAssessment.Controller.EmployeeController;
import com.fdmgroup.SimonHossainSpringAssessment.Model.Employee;

@SpringBootTest
@AutoConfigureMockMvc
class TestEmployeeController {
	@Autowired
	private EmployeeController empController;
	
	@Autowired
	WebApplicationContext webAppContext;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() throws Exception {
	this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@Test
	public void test_UserController_LoadsIntoContext() {
		assertThat(empController).isNotNull();
	}
	
	@Test
	public void test_GetMappingRequestsLinksCorrectlyToHTMLPages() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/CreateEmployee")).andExpect(MockMvcResultMatchers.view().name("CreateEmployee"));
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("Index"));
		mockMvc.perform(MockMvcRequestBuilders.get("/FindByAddress")).andExpect(MockMvcResultMatchers.view().name("FindByAddress"));
		mockMvc.perform(MockMvcRequestBuilders.get("/FindByFirstAndLastName")).andExpect(MockMvcResultMatchers.view().name("FindByFirstAndLastName"));
	}
	
	@Test
	public void test_SaveEmployee_SavesEmployeeToTheH2DataBase_AndRedirectstoIndex() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/SaveEmployee")
				.flashAttr("employee",new Employee("Simon","Hossain",LocalDate.of(2017, 10, 10),"123 Fake Address")))
				.andExpectAll(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
	}

	@Test
	public void test_FindByAddress_ReturnsCorrectAddress_InResultsPage() throws Exception{
		LinkedMultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<>();
		requestParameters.add("address", "123 Fake Street");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/FindByAddress").params(requestParameters))
				.andExpectAll(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("Results"));
	}
	
	@Test
	public void test_FindByFirstAndLastName_ReturnsCorrectResults_InResultsPage() throws Exception {
		LinkedMultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<>();
		requestParameters.add("firstName", "Simon");
		requestParameters.add("lastName", "Hossain");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/FindByFirstAndLastName").params(requestParameters))
				.andExpectAll(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("Results"));
	}
	

}
