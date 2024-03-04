package com.training.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import com.training.project.bean.EmployeeBean;
import com.training.project.entity.EmployeeEntity;
import com.training.project.exceptions.EmployeeNotFoundException;
@RepositoryDefinition(domainClass=EmployeeEntity.class,idClass=Integer.class)
public interface EmployeeDao{ //extends CrudRepository<EmployeeEntity, Integer>{
	EmployeeEntity save(EmployeeEntity entity);
	List<EmployeeEntity> findAll();
	EmployeeEntity findById(Integer id) throws EmployeeNotFoundException;
	@Modifying
	@Query("delete from EmployeeEntity e where e.id=?1")
	void delete(Integer id);
	@Modifying
	@Query("update EmployeeEntity e set e.salary=?1 where e.id=?2")
	void update(Double salary,Integer id);
	boolean existsById(Integer id);
	

}
