package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.Employee;

@Repository
public interface EmployeeDao {
	
	@Select("select * from tbl_employee where name = #{name}")
	@Results({
		@Result(property="department",column="department_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getDepartmentById")),
		@Result(property="workStatus",column="work_status_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEmpWorkStatusById")),
		@Result(property="education",column="education_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEducationById"))
	
	})
	public Employee getEmplInfoByName(String name);
	
	@Select("select * from tbl_employee where id = #{id}")
	@Results({
		@Result(property="department",column="department_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getDepartmentById")),
		@Result(property="workStatus",column="work_status_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEmpWorkStatusById")),
		@Result(property="education",column="education_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEducationById"))
	
	})
	public Employee getEmpInfoById(Integer id);

	@Select("select * from tbl_employee where department_id = #{id}")
	@Results({
		@Result(property="department",column="department_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getDepartmentById")),
		@Result(property="workStatus",column="work_status_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEmpWorkStatusById")),
		@Result(property="education",column="education_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEducationById"))
	
	})
	public List<Employee> getEmpInfoByDId(Integer id);

	@InsertProvider(type=DynaSqlProvider.class,method="insertEmp")
	@Options(useGeneratedKeys=true,keyProperty="id")
	public int insertEmp(Employee employee);

	@UpdateProvider(type=DynaSqlProvider.class,method="updateEmployee")
	public int updateEmployee(Employee employee);
	
	
}
