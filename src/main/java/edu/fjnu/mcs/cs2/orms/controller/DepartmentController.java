package edu.fjnu.mcs.cs2.orms.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.service.DepartmentService;

/**   
 * @Title: DepartmentController.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(部门模块控制层) 
 * @author lbb
 * @date 2016年5月28日 下午3:39:47 
 * @version V1.0   
 */
@Controller
@RequestMapping("/back")
public class DepartmentController {
	
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 
	 * @Title: addDepartment 
	 * @Description: TODO(添加部门) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addDepartment")
	@ResponseBody
	public Map<String, Object> addDepartment(@RequestBody DTO data)throws Exception{
		return departmentService.addDepartment(data);
	}
	
	/**
	 * 
	 * @Title: deleteDepartment 
	 * @Description: TODO(删除部门 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/deleteDepartment")
	@ResponseBody
	public Map<String, Object> deleteDepartment(@RequestBody DTO data)throws Exception{
		return departmentService.deleteDepartment(data);
	}
	
	/**
	 * 
	 * @Title: getDepartment 
	 * @Description: TODO(查询部门列表 ) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getDepartment")
	@ResponseBody
	public Map<String, Object> getDepartment()throws Exception{
		return departmentService.getDepartment();
	}

	/**
	 * 
	 * @Title: getDepartmentInfo 
	 * @Description: TODO(查询部门详细信息 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getDepartmentInfo")
	@ResponseBody
	public Map<String, Object> getDepartmentInfo(@RequestBody DTO data)throws Exception{
		return departmentService.getDepartmentInfo(data);
	}
	
	/**
	 * 
	 * @Title: addEmpForDept 
	 * @Description: TODO(给部门添加员工) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addEmpForDept")
	@ResponseBody
	public Map<String,Object> addEmpForDept(@RequestBody DTO data) throws Exception{
		return departmentService.addEmpForDept(data);
	}
}
