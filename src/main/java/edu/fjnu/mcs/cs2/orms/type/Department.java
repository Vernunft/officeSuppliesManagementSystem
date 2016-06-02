package edu.fjnu.mcs.cs2.orms.type;

import java.util.List;

import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.Type;

/**   
 * @Title: Department.java 
 * @Package edu.fjnu.mcs.cs2.orms.type 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月25日 下午8:45:45 
 * @version V1.0   
 */
public class Department extends Type{
	
	private List<Department> child;
	private List<Employee> emp ;
	
	public List<Employee> getEmployees() {
		return emp;
	}
	public void setEmployees(List<Employee> employees) {
		this.emp = employees;
	}
	public List<Department> getChild() {
		return child;
	}
	public void setChild(List<Department> child) {
		this.child = child;
	}
	
}
