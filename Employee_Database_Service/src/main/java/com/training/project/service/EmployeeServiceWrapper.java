package com.training.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.project.bean.EmployeeBean;
import com.training.project.dao.EmployeeDao;
import com.training.project.dao.EmployeeDaoWrapper;
import com.training.project.exceptions.EmployeeNotFoundException;
@Service
public class EmployeeServiceWrapper implements EmployeeService{
	@Autowired
	EmployeeDaoWrapper dao;

	@Override
	public List<EmployeeBean> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Integer insert(EmployeeBean bean) {
		// TODO Auto-generated method stub
		return dao.insert(bean);
	}

	@Override
	public EmployeeBean findById(Integer id) throws EmployeeNotFoundException{
		// TODO Auto-generated method stub
		EmployeeBean bean=dao.findById(id);
//		if(bean==null) {
//			throw new EmployeeNotFoundException(id);
//		}
		return bean;
	}

	@Override
	public void delete(Integer id) throws EmployeeNotFoundException{
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public void update(Double salary,Integer id) throws EmployeeNotFoundException{
		// TODO Auto-generated method stub
		dao.update(salary,id);
	}

}
