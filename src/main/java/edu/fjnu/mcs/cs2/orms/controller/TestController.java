package edu.fjnu.mcs.cs2.orms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;
import edu.fjnu.mcs.cs2.orms.dao.EmployeeDao;
import edu.fjnu.mcs.cs2.orms.dao.InstockDao;
import edu.fjnu.mcs.cs2.orms.dao.SupplierDao;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.Instock;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.type.Category;
import edu.fjnu.mcs.cs2.orms.type.InstockType;

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
	@Resource
	InstockDao instockDao;
	@Resource
	EmployeeDao employeeDao;
	@Resource
	SupplierDao supplierDao;
	private static Logger log = (Logger) LoggerFactory.getLogger(TestController.class);
	@RequestMapping("/test")
	public void test(){
		log.debug("我进来了");
		Map<String, Object> map = new HashMap<>();
//		map.put("id",10);
//		map.put("name", "空调");
////		map.put("parent", 1);
//		map.put("type", 1);
////		List<Category> categories = allKindDao.getAllCategoryInfo(map);
////		System.out.println(categories.size());
//		Category category = new Category();
//		category.setName("电视");
//		List<Category> categories =allKindDao.getChildByParId(1);
//		System.out.print(categories.size());
		Instock instock = new Instock();
		InstockType type = new InstockType();
		type.setId(1);
		//type.setName("空调");
		instock.setType(type);
		Employee make  = new Employee();
		make.setId(14);
		instock.setMake(make);
		instock.setAttn(make);
		instock.setDate(new Date());
		Supplier supplier = new Supplier();
		supplier.setId(1);
		instock.setSupplier(supplier);
		instock.setTotalCount(12);
		instock.setTotalPrice(2.5f);
		int flag = instockDao.insertInstock(instock);
		System.out.println(flag);
//		Integer id = allKindDao.getTypeByName(map);
//		System.out.println(id);.
		//测试根据时间段查询入库信息
//		map.put("beginTime", "2016-05-23");
//		map.put("endTime", new Date());
//		List<Instock> instocks = instockDao.getInstockInfoById(1);
//		for (Instock instock : instocks) {
//			System.out.println(instock.getType().getName());
//		}
		InstockType instockType = new InstockType();
		//instockType.setName("购入");
		instockType.setId(77);
//		Instock instock = new Instock();
//		instock.setDate(new Date());
//		instock.setTotalCount(12);
//		instock.setTotalPrice(12f);
//		instock.setType(instockType);
//		Employee employee = employeeDao.getEmpInfoById(1);
//		instock.setAttn(employee);
//		instock.setMake(employee);
//		Supplier supplier = supplierDao.getSupplierById(1);
//		instock.setSupplier(supplier);
//		int flag =0;
//		flag=instockDao.insertInstock(instock);
//		if (flag!=0) {
//			System.out.println(flag);
//		}
//		int flag = allKindDao.deleteCategory(81);
//		System.out.println(flag);
	}
}
