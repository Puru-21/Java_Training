package com.training.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.project.bean.EmployeeBean;
import com.training.project.exceptions.EmployeeNotFoundException;
import com.training.project.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class EmployeeController {
	@Autowired
	EmployeeService service;
	
	@RequestMapping(value="getEmp",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeBean> getEmployee(){
		log.info("Inside controller getEmployee");
		List<EmployeeBean> list=service.findAll();
	    return list;
	}
	@RequestMapping(value="insertEmp",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertEmployee(@RequestBody @Valid EmployeeBean bean){
		log.info("Inside controller insertEmployee");
		Integer result=service.insert(bean);
	    return new ResponseEntity<String>("Values inserted!!! Id of employee is "+result, HttpStatus.CREATED);
	}
	@RequestMapping(value="findEmp/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeBean> findEmployee(@PathVariable("id") Integer id)throws Exception{
		log.info("Inside controller findEmployee");
		EmployeeBean bean=service.findById(id);
	    return new ResponseEntity<EmployeeBean>(bean, HttpStatus.ACCEPTED);
	}
	@RequestMapping(value="deleteEmp/{id}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Integer id)throws Exception{
		log.info("Inside controller deleteEmployee");
		service.delete(id);
	    return new ResponseEntity<String>("Value deleted with id: "+id, HttpStatus.ACCEPTED);
	}
	@RequestMapping(value="updateEmp/{id}",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeBean> updateEmployee(@RequestBody @Valid EmployeeBean bean,@PathVariable("id") Integer id)throws Exception{
		log.info("Inside controller updateEmployee");
		service.update(bean.getSalary(),id);
	    return new ResponseEntity<EmployeeBean>(bean, HttpStatus.OK);
	}

}
