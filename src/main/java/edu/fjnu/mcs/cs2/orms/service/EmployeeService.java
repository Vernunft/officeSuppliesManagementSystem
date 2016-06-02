package edu.fjnu.mcs.cs2.orms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Service;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.EmployeeDao;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.type.EmpWorkStatus;

/**   
 * @Title: EmployeeService.java 
 * @Package edu.fjnu.mcs.cs2.orms.service 
 * @Description: TODO(员工模块业务层) 
 * @author lbb
 * @date 2016年5月28日 下午7:33:36 
 * @version V1.0   
 */
@Service
public class EmployeeService {
	@Resource
	TypeDao typeDao;
	
	@Resource
	EmployeeDao employeeDao;
	Map<String, Object> map = new HashMap<>();
	/**
	 * 
	 * @Title: addEmpWorkStatus 
	 * @Description: TODO(添加员工在职状态) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> addEmpWorkStatus(DTO data) {
		EmpWorkStatus empWorkStatus = data.getEmpWorkStatus();
		if (typeDao.getEmpWorkStatusByName(empWorkStatus.getName())!= null) {
			map.put("code", 101);
		}else {
			typeDao.inertEmpWorkStatus(empWorkStatus);
			map.put("code", 0);
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: deleteEmpWorkStatus 
	 * @Description: TODO(删除员工在职状态) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> deleteEmpWorkStatus(DTO data) {
		EmpWorkStatus empWorkStatus = data.getEmpWorkStatus();
		if (typeDao.deleteCategory(empWorkStatus.getId())==1) {
			map.put("code", 0);
		}else {
			map.put("code", 101);
		}
		return map;
	}

	/**
	 * 
	 * @Title: getEmpWorkStatus 
	 * @Description: TODO(查询员工在职状态列表 ) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> getEmpWorkStatus() {
		List<EmpWorkStatus> empWorkStatuss = new ArrayList<>();
		empWorkStatuss = typeDao.getWorkStatus(EmpWorkStatus.empWorkStatus);
		map.put("empWorkStatuss", empWorkStatuss);
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * @Title: addEmployee 
	 * @Description: TODO(添加员工) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> addEmployee(DTO data) {
		Employee employee = data.getEmployee();
		if (employeeDao.insertEmp(employee)==1) {
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 
	 * @Title: modifyEmpWorkStatus 
	 * @Description: TODO(修改某个员工在职状态 ) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> modifyEmpWorkStatus(DTO data) {
		Employee employee  =  data.getEmployee();
		EmpWorkStatus empWorkStatus = data.getEmpWorkStatus();
		employee.setWorkStatus(empWorkStatus);
		if (employeeDao.getEmpInfoById(employee.getId())!=null) {
			employeeDao.updateEmployee(employee);
			map.put("code", 0);
		}else {
			map.put("code", 101);
		}
		return map;
	}

}
