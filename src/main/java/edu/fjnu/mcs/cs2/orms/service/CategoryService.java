package edu.fjnu.mcs.cs2.orms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.serializer.ObjectArraySerializer;
import com.sun.mail.imap.protocol.MailboxInfo;

import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.ResDao;
import edu.fjnu.mcs.cs2.orms.entity.ResPO;
import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.type.Category;

@Service
public class CategoryService {
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
	public Map<String, Object> getAllCategoryInfo(int currentPage, int size) {
		rowCount = resDao.getRowCount();
		offset = (currentPage - 1) * size;
		Map<String, Object> map = new HashMap<>();
		map.put("type",Category.TYPE);
		map.put("offset", offset);
		map.put("size", size);
		Map<String, Object> maps = new HashMap<>();
		List<Category> categories = typeDao.getAllCategoryInfo(map);
		for (Category category : categories) {
			maps.put("id", category.getId());
			maps.put("name", category.getName());
			category.setChild(typeDao.getChildByParId(category.getId()));
			maps.put("child", category.getChild());
		}
		maps.put("results", categories);
		maps.put("code", 0);
		maps.put("currentPage", currentPage);
		maps.put("size", size);
		if (rowCount % size != 0) {
			totalPage = rowCount/size+1;
		}
		maps.put("totalPage", totalPage);
		return maps;
	}

	/**
	 * 
	 * @Title: getCategoryInfo 
	 * @Description: TODO(查询物品类别的详细信息) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> getCategoryInfo(DTO data) {
		Map<String, Object> map = new HashMap<>();
		Category category = (Category) data.getObject();
		List<Category> categories = null;
		if (category.getId()!=null) {
			 categories= typeDao.getCategoryInfoById(category.getId());
		}else {
			Map<String, Object> query = new HashMap<>();
			query.put("type", Category.TYPE);
			offset = (data.getCurrentPage() - 1) * data.getSize();
			query.put("offset",offset );
			query.put("size", data.getSize());
			categories=  typeDao.getCategoryInfo(query);
			rowCount = typeDao.getRowCountByType(Category.TYPE);
			if (rowCount % data.getSize() != 0) {
				totalPage = rowCount/data.getSize() + 1;
			}
			map.put("currentPage", data.getCurrentPage());
			map.put("size", data.getSize());
			map.put("totalPage", totalPage);
		}
		 for (Category category2 : categories) {
				category2.setCount(resDao.getCategotyInfoById(category2.getId()).getStockNow());
				category2.setChild(typeDao.getChildByParId(category2.getId()));
				map.put("id", category2.getId());
				map.put("name", category2.getName());
				map.put("child", category2.getChild());
			}
		 map.put("code", 0);
		 map.put("categotys", categories);
		 return map;
	}

	/***
	 * 
	 * @Title: addCategory 
	 * @Description: TODO(添加物品类别) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> addCategory(DTO data) {
		Map<String, Object> map = new HashMap<>();
		Category category = (Category) data.getObject();
		Integer categoryId = category.getId();
		Map<String, Object> query = new HashMap<>();
		query.put("id", categoryId);
		query.put("name", category.getName());
		query.put("parent", category.getParent());
		query.put("type", Category.TYPE);
		if (typeDao.addCategory(query)==1) {
			map.put("code", 0);
		}else {
			map.put("code", 101);
		}
		return map;
	}

	/**
	 * 
	 * @Title: deleteCategory 
	 * @Description: TODO(删除物品类别) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> deleteCategory(DTO data) {
		Map<String, Object> map = new HashMap<>();
		Category category = (Category) data.getObject();
		if (typeDao.deleteCategory(category.getId())==1) {
			map.put("code", 0);
		}else {
			map.put("code", 101);
		}
		return map;
	}
}
