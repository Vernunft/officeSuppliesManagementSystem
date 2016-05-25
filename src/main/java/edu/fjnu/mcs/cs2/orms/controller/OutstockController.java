package edu.fjnu.mcs.cs2.orms.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.OutstockDao;
import edu.fjnu.mcs.cs2.orms.service.OutstockService;

/**   
 * @Title: OutstockController.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(物品出库模块) 
 * @author lbb
 * @date 2016年5月25日 下午4:04:32 
 * @version V1.0   
 */
@Controller
@RequestMapping("/back")
public class OutstockController {
	@Resource
	OutstockService outstockService;
	
	/**
	 * 
	 * @Title: addOutstockType 
	 * @Description: TODO(增加出库类型) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addOutstockType")
	@ResponseBody 
	public Map<String, Object> addOutstockType(@RequestBody DTO data)throws Exception{
		return outstockService.addOutstockType(data);
	}
	
	/**
	 * 
	 * @Title: deleteOutstockType @Description: TODO(删除某出库类型) @param @param
	 * data @param @return @param @throws Exception 设定文件 @return
	 * Map<String,Object> 返回类型 @throws
	 */
	@RequestMapping("/deleteOutstockType ")
	@ResponseBody
	public Map<String, Object> deleteOutstockType(@RequestBody DTO data) throws Exception {
		return outstockService.deleteOutstockType(data);
	}
	
	/**
	 * 
	 * @Title: getOutstockType @Description:
	 * TODO(查询入库类型) @param @return @param @throws Exception 设定文件 @return
	 * Map<String,Object> 返回类型 @throws
	 */
	@RequestMapping("/getOutstockType")
	@ResponseBody
	public Map<String, Object> getOutstockType() throws Exception {
		return outstockService.getOutstockType();

	}

	/**
	 * 
	 * @Title: addOutstock 
	 * @Description: TODO(添加出库信息) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addOutstock ")
	@ResponseBody
	public Map<String, Object> addOutstock(@RequestBody DTO data) throws Exception {
		return outstockService.addOutstock(data);
	}
	
	/**
	 * 
	 * @Title: getOutstockInfo 
	 * @Description: TODO(查询出库信息) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getOutstockInfo")
	@ResponseBody
	public Map<String, Object> getOutstockInfo (@RequestBody DTO data) throws Exception {
		return outstockService.getOutstockInfo(data);

	}
}
