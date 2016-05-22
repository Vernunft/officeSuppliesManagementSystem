package edu.fjnu.mcs.cs2.orms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.type.Category;

/**   
 * @Title: TestController.java 
 * @Package edu.fjnu.mcs.cs2.orms.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月21日 下午10:42:52 
 * @version V1.0   
 */
@Controller
public class TestController {
	@Resource
	TypeDao allKindDao;
	
	private static Logger log = (Logger) LoggerFactory.getLogger(TestController.class);
	@RequestMapping("/test")
	public void test(){
		log.debug("我进来了");
		Map<String, Object> map = new HashMap<>();
		map.put("id",10);
		map.put("name", "空调");
		map.put("parent", 1);
		map.put("type", 1);
//		List<Category> categories = allKindDao.getAllCategoryInfo(map);
//		System.out.println(categories.size());
		allKindDao.addCategory(map);
	}
}
