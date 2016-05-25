package edu.fjnu.mcs.cs2.orms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.service.CategoryService;
import edu.fjnu.mcs.cs2.orms.type.Category;

/**
 * 
 * @ClassName: CategoryController 
 * @Description: 物品分类控制器
 * @author lbb
 * @date 2016年5月21日 下午4:05:53
 */
@Controller
@RequestMapping("/back")
public class CategoryController {
	@Resource
	CategoryService categoryService;
	
	/**
	 * 获取所有物品类别列表
	 * @param currentPage
	 * @param size
	 * @return
	 */
	@RequestMapping("/getAllCategoryInfo")
	@ResponseBody
	public Map<String, Object> getAllCategoryInfo(@RequestBody DTO data)throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (data.getCurrentPage()!=null) {
			if (data.getSize()!=null) {
				map =categoryService.getAllCategoryInfo(data.getCurrentPage(),data.getSize());
			}else {
				map.put("code", 2);
			}
		}else {
			map.put("code", 1);
		}
		return map;
	}
	
	/**
	 * 查询物品类别的详细信息
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/getCategoryInfo")
	@ResponseBody
	public Map<String, Object> getCategoryInfo(@RequestBody DTO data)throws Exception{
		return categoryService.getCategoryInfo(data);
	}
	
	/**
	 * 
	 * @Title: addCategory 
	 * @Description: TODO(添加物品类别) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/addCategory")
	@ResponseBody
	public Map<String, Object> addCategory(@RequestBody DTO data)throws Exception{
		return categoryService.addCategory(data);
		
	}
	
	/**
	 * 
	 * @Title: deleteCategory 
	 * @Description: TODO(删除物品类别) 
	 * @param @param data
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping("/deleteCategory")
	@ResponseBody
	public Map<String, Object> deleteCategory(@RequestBody DTO data)throws Exception{
		return categoryService.deleteCategory(data);
		
	}
	
	
}
