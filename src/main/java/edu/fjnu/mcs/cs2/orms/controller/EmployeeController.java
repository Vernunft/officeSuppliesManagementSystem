package edu.fjnu.mcs.cs2.orms.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.service.EmployeeService;

/**   
 * @Title: EmployeeController.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(员工模块控制层) 
 * @author lbb
 * @date 2016年5月28日 下午7:32:28 
 * @version V1.0   
 */
@Controller
@RequestMapping("/back")
public class EmployeeController {

	@Resource
	EmployeeService employeeService;
	
	/**
	 * 
	 * @Title: addEmpWorkStatus 
	 * @Description: TODO(添加员工在职状态) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addEmpWorkStatus")
	@ResponseBody
	public Map<String, Object> addEmpWorkStatus(@RequestBody DTO data)throws Exception{
		return employeeService.addEmpWorkStatus(data);
	}
	
	/**
	 * 
	 * @Title: deleteEmpWorkStatus 
	 * @Description: TODO(删除员工在职状态) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/deleteEmpWorkStatus")
	@ResponseBody
	public Map<String, Object> deleteEmpWorkStatus(@RequestBody DTO data)throws Exception{
		return employeeService.deleteEmpWorkStatus(data);
	}
	
	/**
	 * 
	 * @Title: getEmpWorkStatus 
	 * @Description: TODO(查询员工在职状态列表 ) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getEmpWorkStatus")
	@ResponseBody
	public Map<String, Object> getEmpWorkStatus()throws Exception{
		return employeeService.getEmpWorkStatus();
	}
	
	/**
	 * 
	 * @Title: addEmployee 
	 * @Description: TODO(添加员工) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addEmployee")
	@ResponseBody
	public Map<String, Object> addEmployee(@RequestBody DTO data)throws Exception{
		return employeeService.addEmployee(data);
	}
	
	/**
	 * 
	 * @Title: modifyEmpWorkStatus 
	 * @Description: TODO(修改某个员工在职状态 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/modifyEmpWorkStatus")
	@ResponseBody
	public Map<String, Object> modifyEmpWorkStatus (@RequestBody DTO data)throws Exception{
		return employeeService.modifyEmpWorkStatus (data);
	}
}
