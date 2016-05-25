package edu.fjnu.mcs.cs2.orms.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.service.InstockService;

/**   
 * @Title: InStockController.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(物品入库模块) 
 * @author lbb
 * @date 2016年5月22日 下午7:12:24 
 * @version V1.0   
 */
@Controller
@RequestMapping("/back")
public class InstockController {

	@Resource
	InstockService instockService;

	/**
	 * 
	 * @Title: addInstockType @Description: TODO(增加入库类型) @param @param
	 * data @param @return @param @throws Exception 设定文件 @return
	 * Map<String,Object> 返回类型 @throws
	 */
	@RequestMapping("/addInstockType")
	@ResponseBody
	public Map<String, Object> addInstockType(@RequestBody DTO data) throws Exception {
		return instockService.addInstockType(data);
	}

	/**
	 * 
	 * @Title: deleteInstockType @Description: TODO(删除某入库类型) @param @param
	 * data @param @return @param @throws Exception 设定文件 @return
	 * Map<String,Object> 返回类型 @throws
	 */
	@RequestMapping("/deleteInstockType ")
	@ResponseBody
	public Map<String, Object> deleteInstockType(@RequestBody DTO data) throws Exception {
		return instockService.deleteInstockType(data);
	}

	/**
	 * 
	 * @Title: getInstockType @Description:
	 * TODO(查询入库类型) @param @return @param @throws Exception 设定文件 @return
	 * Map<String,Object> 返回类型 @throws
	 */
	@RequestMapping("/getInstockType")
	@ResponseBody
	public Map<String, Object> getInstockType() throws Exception {
		return instockService.getInstockType();

	}

	/**
	 * 
	 * @Title: getInstockInfo @Description: TODO(获取入库信息) @param @param
	 * data @param @return @param @throws Exception 设定文件 @return
	 * Map<String,Object> 返回类型 @throws
	 */
	@RequestMapping("/getInstockInfo")
	@ResponseBody
	public Map<String, Object> getInstockInfo(@RequestBody DTO data) throws Exception {
		return instockService.getInstockInfo(data);

	}

	/**
	 *
	 * @Title: addInstock @Description: TODO(添加物品入库信息) @param @param
	 * data @param @return @param @throws Exception 设定文件 @return
	 * Map<String,Object> 返回类型 @throws
	 */
	@RequestMapping("/addInstock ")
	@ResponseBody
	public Map<String, Object> addInstock(@RequestBody DTO data) throws Exception {
		return instockService.addInstock(data);
	}
}
