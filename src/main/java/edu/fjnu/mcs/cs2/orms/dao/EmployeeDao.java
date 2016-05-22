package edu.fjnu.mcs.cs2.orms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao {
	@Options(useGeneratedKeys=true,keyProperty="id")
	public void name();
}
