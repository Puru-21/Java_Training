package com.training.project.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.training.project.bean.EmployeeBean;
import com.training.project.controller.EmployeeController;
import com.training.project.service.EmployeeServiceWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class SpringBoot_Unit_Tests {
	
	@Mock
	EmployeeServiceWrapper empServiceIMPL;
	
	@InjectMocks
	EmployeeController controller;
	
	protected MockMvc mockMVC;

	@Before
	public void mySetup() {
		MockitoAnnotations.initMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void createEmployeeTest() throws Exception {
		String uri = "/insertEmp";
		EmployeeBean employee = new EmployeeBean(101,"Jack", "Java", 90011.1);

		String employeeJsonFormat = JSONUtils.covertFromObjectToJson(employee);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(employeeJsonFormat)
				.contentType(MediaType.APPLICATION_JSON_VALUE);

		when(empServiceIMPL.insert(employee)).thenReturn(101);

		ResultActions rest = mockMVC.perform(request);

		MvcResult mvcREsult = rest.andReturn();

		String finalResult = mvcREsult.getResponse().getContentAsString();
		int actualStatus = mvcREsult.getResponse().getStatus();

		verify(empServiceIMPL, times(1)).insert(ArgumentMatchers.refEq(employee));
		
		Assert.assertTrue(finalResult != null);
		Assert.assertTrue(actualStatus == HttpStatus.CREATED.value());
	}

}
