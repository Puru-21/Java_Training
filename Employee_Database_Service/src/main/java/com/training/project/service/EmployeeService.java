package com.training.project.service;

import java.util.List;

import com.training.project.bean.EmployeeBean;

public interface EmployeeService {
	List<EmployeeBean> findAll();
	Integer insert(EmployeeBean bean);
	EmployeeBean findById(Integer id) throws Exception;
	void delete(Integer id) throws Exception;
	void update(Double salary,Integer id) throws Exception;

}
