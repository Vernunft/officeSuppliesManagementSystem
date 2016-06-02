package edu.fjnu.mcs.cs2.orms.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.service.SupplierService;

/**   
 * @Title: SupplierController.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(供应商模块控制层) 
 * @author lbb
 * @date 2016年5月26日 下午2:37:01 
 * @version V1.0   
 */
@Controller
@RequestMapping("/back")
public class SupplierController {
	
	@Resource
	SupplierService supplierService;
	
	/**
	 * 
	 * @Title: addSupplierType 
	 * @Description: TODO(添加供应商类别) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addSupplierType ")
	@ResponseBody
	public Map<String, Object> addSupplierType(@RequestBody DTO data){
		return supplierService.addSupplierType(data);
	}
	
	/**
	 * 
	 * @Title: deleteSupplierType 
	 * @Description: TODO(删除供应商类型) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/deleteSupplierType ")
	@ResponseBody
	public Map<String, Object> deleteSupplierType(@RequestBody DTO data) throws Exception {
		return supplierService.deleteSupplierType(data);
	}
	
	/**
	 * 
	 * @Title: getOutstockType 
	 * @Description: TODO(查询供应商类型 ) 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getSupplierType")
	@ResponseBody
	public Map<String, Object> getSupplierType() throws Exception {
		return supplierService.getSupplierType();

	}
	
	/**
	 * 
	 * @Title: addSupplier 
	 * @Description: TODO(添加供应商 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addSupplier")
	@ResponseBody
	public Map<String, Object> addSupplier(@RequestBody DTO data) throws Exception {
		return supplierService.addSupplier(data);
	}
	
	/**
	 * 
	 * @Title: getSupplier 
	 * @Description: TODO(查询供应商信息) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getSupplier")
	@ResponseBody
	public Map<String, Object> getSupplier (@RequestBody DTO data) throws Exception {
		return supplierService.getSupplier(data);

	} 
	
	/**
	 * 
	 * @Title: addSupplierSupplies 
	 * @Description: TODO(添加供应商供应的物品信息 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addSupplierSupplies")
	@ResponseBody
	public Map<String, Object> addSupplierSupplies(@RequestBody DTO data)throws Exception{
		return supplierService.addSupplierSupplies(data);
	}
	
	/**
	 * 
	 * @Title: getSupplierSupplies 
	 * @Description: TODO(查询供应商供应的物品信息 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/getSupplierSupplies")
	@ResponseBody
	public Map<String, Object> getSupplierSupplies(@RequestBody DTO data)throws Exception{
		return supplierService.getSupplierSupplies(data);
	}

	/**
	 * 
	 * @Title: modifySupplierSupplies 
	 * @Description: TODO(修改供应商供应的物品标) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/modifySupplierSupplies")
	@ResponseBody
	public Map<String, Object> modifySupplierSupplies(@RequestBody DTO data)throws Exception{
		return supplierService.modifySupplierSupplies(data);
	}
	
	/**
	 * 
	 * @Title: deleteSupplierSupplies 
	 * @Description: TODO(删除供应商供应的物品 ) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/deleteSupplierSupplies")
	@ResponseBody 
	public Map<String, Object> deleteSupplierSupplies(@RequestBody DTO data)throws Exception{
		return supplierService.deleteSupplierSupplies(data);
	}
}
