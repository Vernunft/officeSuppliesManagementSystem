package edu.fjnu.mcs.cs2.orms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.serializer.ObjectArraySerializer;
import com.sun.mail.imap.protocol.MailboxInfo;

import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.ResDao;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.type.Category;

@Service
public class CategoryService {

	Map<String, Object> map = new HashMap<>();
	@Resource
	TypeDao typeDao;

	@Resource
	ResDao resDao;

	private int totalPage;
	private int offset;
	private int rowCount;

	/**
	 * 查询所有物品类别列表
	 * 
	 * @param currentPage
	 * @param size
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> getAllCategoryInfo(DTO data) {
		rowCount = typeDao.getRowCountByType(Category.category);
		int currentPage = data.getCurrentPage() != null?1:data.getCurrentPage();
		int size = data.getSize() != null?10:data.getSize();
		offset = (currentPage - 1) * size;
		Map<String, Object> query = new HashMap<>();
		query.put("type", Category.category);
		query.put("offset", offset);
		query.put("size", size);
		List<Category> categories = typeDao.getAllCategoryInfo(query);
		for (Category category : categories) {
			category.setChild(typeDao.getChildByParId(category.getId()));
		}
		map.put("categorys", categories);
		map.put("code", 0);
		map.put("currentPage", currentPage);
		map.put("size", size);
		if (rowCount % size != 0) {
			totalPage = rowCount / size + 1;
		}
		map.put("totalPage", totalPage);
		return map;
	}

	/**
	 * 
	 * @Title: getCategoryInfo @Description: TODO(查询物品类别的详细信息) @param @param
	 * data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> getCategoryInfo(DTO data) {
		Category category = data.getCategory();
		int currentPage = data.getCurrentPage() != null?1:data.getCurrentPage();
		int size = data.getSize() != null?10:data.getSize();
		List<Category> categories = new ArrayList<>();
		if (category.getId() != null) {
			category =  typeDao.getTypeInfoById(category.getId());
			categories.add(category);
		} else {
			Map<String, Object> query = new HashMap<>();
			query.put("type", Category.category);
			offset = (currentPage - 1) * data.getSize();
			query.put("offset", offset);
			query.put("size", size);
			categories = typeDao.getCategoryInfo(query);
			rowCount = typeDao.getRowCountByType(Category.category);
			if (rowCount % data.getSize() != 0) {
				totalPage = rowCount / data.getSize() + 1;
			}
			map.put("currentPage", currentPage);
			map.put("size", size);
			map.put("totalPage", totalPage);
		}
		int count;
		for (Category category2 : categories) {
			count = 0;
			List<Res> ress = resDao.getResInfoByCatId(category2.getId());
			for (Res res : ress) {
				count += res.getStockNow();
			}
			category2.setChild(typeDao.getChildByParId(category2.getId()));
			category2.setCount(count);
		}
		
		map.put("code", 0);
		map.put("categotys", categories);
		return map;
	}

	/***
	 * 
	 * @Title: addCategory @Description: TODO(添加物品类别) @param @param
	 * data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> addCategory(DTO data) {
		Category category = data.getCategory();
		if ( typeDao.getCategoryByName(category.getName())!=null) {
			map.put("code", 101);
		} else {
			typeDao.insertCategory(category);
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 
	 * @Title: deleteCategory @Description: TODO(删除物品类别) @param @param
	 * data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> deleteCategory(DTO data) {
		Category category = data.getCategory();
		if (typeDao.deleteCategory(category.getId()) ==1) {
			map.put("code", 0);
		} else {
			map.put("code", 101);
		}
		return map;
	}
}
