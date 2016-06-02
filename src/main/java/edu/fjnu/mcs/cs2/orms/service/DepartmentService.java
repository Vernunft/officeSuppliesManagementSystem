package edu.fjnu.mcs.cs2.orms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.EmployeeDao;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.type.Department;

/**
 * @Title: DepartmentService.java
 * @Package edu.fjnu.mcs.cs2.orms.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author lbb
 * @date 2016年5月28日 下午3:40:49
 * @version V1.0
 */
@Service
public class DepartmentService {

	@Resource
	TypeDao typeDao;

	@Resource
	EmployeeDao employeeDao;

	Map<String, Object> map = new HashMap<>();

	/**
	 * 
	 * @Title: addDepartment @Description: TODO(添加部门) @param @param
	 * data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> addDepartment(DTO data) {
		Department department = data.getDepartment();
		if (typeDao.getDepartmentByName(department.getName()) != null) {
			map.put("code", 101);
		} else if (department.getParent() != null) {
			if (typeDao.getTypeInfoById(department.getParent().getId()) == null) {
				map.put("code", 102);
			} else {
				typeDao.insertDepartment(department);
				map.put("code", 0);
			}
		}else {
			typeDao.insertDepartment(department);
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 
	 * @Title: deleteDepartment @Description: TODO(删除部门 ) @param @param
	 * data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> deleteDepartment(DTO data) {
		Department department = data.getDepartment();
		typeDao.deleteCategory(department.getId());
		List<Department> child = typeDao.getChildByDepId(department.getId());
		for (Department department2 : child) {
			typeDao.deleteCategory(department2.getId());
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * @Title: getDepartment @Description: TODO(查询部门列表 ) @param @return
	 * 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> getDepartment() {
		List<Department> departments = typeDao.getDepartmentByType(Department.department);
		List<Department> child = new ArrayList<>();
		for (Department department : departments) {
			child = typeDao.getChildByDepId(department.getId());
			department.setChild(child);
		}
		map.put("code", 0);
		map.put("departments", departments);
		return map;
	}

	/**
	 * 
	 * @Title: getDepartmentInfo @Description: TODO(查询部门详细信息 ) @param @param
	 * data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> getDepartmentInfo(DTO data) {
		Department department = data.getDepartment();
		List<Department> departments = new ArrayList<>();
		List<Employee> employees = new ArrayList<>();
		List<Department> child = new ArrayList<>();
		Map<String, Object> query = new HashMap<>();
		int currentPage = data.getCurrentPage() != null?1:data.getCurrentPage();
		int size = data.getSize() != null?10:data.getSize();
		int offset = (currentPage - 1) * size;
		query.put("offset", offset);
		query.put("size", size);
		int totalPage = 0;
		int rowCount = 0;
		if (department.getId() != null) {
			System.out.println("q");
			department=typeDao.getDepartmentById(department.getId());
			departments.add(department);
		} else if (department.getName() != null) {
			System.out.println("q1");
			department= typeDao.getDepartmentByName(department.getName());
			departments.add(department);
		} else {
			System.out.println("q2");
			query.put("type", Department.department);
			departments = typeDao.getDepartmentByTypePage(query);
		}
		for (Department department2 : departments) {
			System.out.println("q3");
			if (department2!=null) {
				child = typeDao.getChildByDepId(department2.getId());
				department2.setChild(child);
				employees = employeeDao.getEmpInfoByDId(department2.getId());
				department2.setEmployees(employees);
			}
		}
		map.put("departments", departments);
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * @Title: addEmpForDept @Description: TODO(给部门添加员工) @param @param
	 * data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> addEmpForDept(DTO data) {
		Employee employee = data.getEmployee();
		employee = employeeDao.getEmpInfoById(employee.getId());
		employee.setDepartment(data.getDepartment());
		if (employeeDao.insertEmp(employee) ==1) {
			map.put("code", 0);
		}
		return map;
	}

}
