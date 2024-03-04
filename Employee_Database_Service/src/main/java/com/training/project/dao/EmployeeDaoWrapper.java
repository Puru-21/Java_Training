package com.training.project.dao;

import java.util.ArrayList;  
import java.util.List;
import java.util.function.*;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.project.bean.EmployeeBean;
import com.training.project.dao.EmployeeDao;
import com.training.project.entity.EmployeeEntity;
import com.training.project.exceptions.EmployeeNotFoundException;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Transactional
@Repository
public class EmployeeDaoWrapper {
	@Autowired
	EmployeeDao dao;
	public List<EmployeeBean> findAll(){
		List<EmployeeBean> list=new ArrayList<>();
		List<EmployeeEntity> list1=(List<EmployeeEntity>) dao.findAll();
		for (EmployeeEntity employeeBean : list1) {
			EmployeeBean bean=new EmployeeBean();
			BeanUtils.copyProperties(employeeBean, bean);
			list.add(bean);
		}
		log.info("Inside DAO findall method");
		return list;
	}
	public Integer insert(EmployeeBean bean) {
		EmployeeEntity entity=new EmployeeEntity();
		BeanUtils.copyProperties(bean, entity);
		dao.save(entity);
		log.info("Inside DAO insert mehtod");
		return entity.getId();
	}
	public EmployeeBean findById(Integer id) throws EmployeeNotFoundException{
	    EmployeeEntity entity = dao.findById(id);

	    if (entity == null) {
	    	log.error("Inside DAO findById- EmployeeNotFoundException activated");
	        throw new EmployeeNotFoundException(id);
	    }

	    EmployeeBean bean = new EmployeeBean();
	    BeanUtils.copyProperties(entity, bean);
	    return bean;
	}
	public void delete(Integer id) throws EmployeeNotFoundException{
		 if (dao.existsById(id)) {
	            dao.delete(id);
	            log.info("Employee with ID " + id + " deleted successfully.");
	        } else {
	            log.warn("Employee with ID " + id + " does not exist. No deletion performed.");
	            throw new EmployeeNotFoundException(id);
	        }	}
	public void update(Double salary, Integer id) throws EmployeeNotFoundException {
        if (dao.existsById(id)) {
            dao.update(salary, id);
            log.info("Employee with ID " + id + " updated successfully.");
        } else {
            log.warn("Employee with ID " + id + " does not exist. No update performed.");
            throw new EmployeeNotFoundException(id);
        }
    }

}
