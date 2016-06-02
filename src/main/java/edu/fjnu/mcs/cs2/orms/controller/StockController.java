package edu.fjnu.mcs.cs2.orms.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.service.StockService;

/**   
 * @Title: StockController.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(库存模块控制层) 
 * @author lbb
 * @date 2016年5月28日 下午8:14:44 
 * @version V1.0   
 */
@Controller
@RequestMapping("/back")
public class StockController {
	
	@Resource
	StockService stockService;
	
	/**
	 * 
	 * @Title: addRes 
	 * @Description: TODO(添加新的物品信息 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addRes")
	@ResponseBody
	public Map<String, Object> addRes(@RequestBody DTO data) throws Exception{
		return stockService.addRes(data);
	}
	
	/**
	 * 
	 * @Title: modifyRes 
	 * @Description: TODO(修改物品的信息 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/modifyRes")
	@ResponseBody
	public Map<String, Object> modifyRes(@RequestBody DTO data) throws Exception{
		return stockService.modifyRes(data);
	}
	
	/**
	 * 
	 * @Title: getExceedMaxLimitRes 
	 * @Description: TODO(查询超出库存上限的物品信息 ) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getExceedMaxLimitRes")
	@ResponseBody
	public Map<String, Object> getExceedMaxLimitRes() throws Exception{
		return stockService.getExceedMaxLimitRes();
	}
	
	/**
	 * 
	 * @Title: getExceedMinLimitRes 
	 * @Description: TODO(查询超出库存下限的物品信息 ) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getExceedMinLimitRes")
	@ResponseBody
	public Map<String, Object> getExceedMinLimitRes() throws Exception{
		return stockService.getExceedMinLimitRes();
	}
}
