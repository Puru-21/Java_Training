package com.training.project.test; 
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.training.project.bean.EmployeeBean;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class SpringBoot_Integration_Tests {

	@Autowired
    WebApplicationContext webApplicationContext; // cached
    
    protected MockMvc mockMVC;
    
    @Before
    public void mySetup(){
		//making the mockMVC aware of the all the application components
		mockMVC= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
    
    @Test
    public void createEmployeeTest() throws Exception{
    	  String uri="/insertEmp";
    	  EmployeeBean employee = new EmployeeBean(101,"Jack@123","Java",90011.1);
    	  String employeeJsonFormat =JSONUtils.covertFromObjectToJson(employee);
    	 
    	  
    	  MockHttpServletRequestBuilder request= MockMvcRequestBuilders.post(uri)
    			  //ResponseContentType: what test case/ test client expects in return
    			  .accept(MediaType.APPLICATION_JSON_VALUE) 
    			  //Data what is sent to server as RequestBody
    			  .content(employeeJsonFormat) 
    			  //Data type of the data being sent to server
    			  .contentType(MediaType.APPLICATION_JSON_VALUE) ;
    	  
    	  ResultActions rest= mockMVC.perform(request);
    	  MvcResult mvcREsult= rest.andReturn();
		   
		  String finalResult= mvcREsult.getResponse().getContentAsString();
		  int actualStatus = mvcREsult.getResponse().getStatus();
		  
		  Assert.assertTrue("Null value",finalResult!=null);
		  Assert.assertTrue("Error Status",actualStatus==HttpStatus.CREATED.value());
		  
    }
    @Test
    public void getAllEmployeesTest() throws Exception{
    	  String uri="/getEmp";
    	  MockHttpServletRequestBuilder request= MockMvcRequestBuilders.get(uri);
    	  ResultActions rest= mockMVC.perform(request); 
    	  MvcResult mvcREsult= rest.andReturn();
		  
    	  //actual status and result
		  String result= mvcREsult.getResponse().getContentAsString();
		  int actualStatus= mvcREsult.getResponse().getStatus();
    	  
    	  //As RestControllerProduces Json result, converting from Json to Java Objects
    	  List<EmployeeBean> listEmp= JSONUtils.covertFromJsonToObject(result, List.class);
    	  
    	  //expected status:
    	  int expectedStatus =HttpStatus.OK.value();
    	  
    	  //Testing: Comparing Expected with Actual
    	  Assert.assertTrue("Null Value",listEmp!=null);
    	  Assert.assertTrue("Error Status",actualStatus==expectedStatus);	  
    }

}
