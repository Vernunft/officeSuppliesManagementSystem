package edu.fjnu.mcs.cs2.orms.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.service.PurchaseService;

/**   
 * @Title: PurchaseController.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(物品采购模块) 
 * @author lbb
 * @date 2016年5月28日 下午9:17:23 
 * @version V1.0   
 */
@Controller
@RequestMapping("/back")
public class PurchaseController {
	@Resource
	PurchaseService purchaseService;
	
	/**
	 * 
	 * @Title: addPurchase 
	 * @Description: TODO(添加采购 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addPurchase")
	@ResponseBody
	public Map<String, Object> addPurchase(@RequestBody DTO data)throws Exception{
		return purchaseService.addPurchase(data);
	}
	
	/**
	 * 
	 * @Title: getPurchase 
	 * @Description: TODO(查询采购单) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getPurchase")
	@ResponseBody
	public Map<String, Object> getPurchase(@RequestBody DTO data)throws Exception{
		return purchaseService.getPurchase(data);
	}
}
