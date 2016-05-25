package edu.fjnu.mcs.cs2.orms.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.entity.Employee;

@Repository
public interface EmployeeDao {
	
	@Select("select * from tbl_employee where name = #{name}")
	public Employee getEmplInfoByName(String name);
	
	@Select("select * from tbl_employee where id = #{id}")
	public Employee getEmpInfoById(Integer id);
	
	
}
