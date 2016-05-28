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
	public Map<String, Object> getAllCategoryInfo(int currentPage, int size) {
		rowCount = resDao.getRowCount();
		offset = (currentPage - 1) * size;
		Map<String, Object> query = new HashMap<>();
		query.put("type",Category.type);
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
			totalPage = rowCount/size+1;
		}
		map.put("totalPage", totalPage);
		return map;
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
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCategoryInfo(DTO data) {
		Category category = data.getCategory();
		List<Category> categories = null;
		if (category.getId()!=null) {
			 categories= (List<Category>) typeDao.getTypeInfoById(category.getId());
		}else {
			Map<String, Object> query = new HashMap<>();
			query.put("type", Category.type);
			offset = (data.getCurrentPage() - 1) * data.getSize();
			query.put("offset",offset );
			query.put("size", data.getSize());
			categories=  typeDao.getCategoryInfo(query);
			rowCount = typeDao.getRowCountByType(Category.type);
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
		Category category = data.getCategory();
		if (typeDao.insertCategory(category)!=0) {
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
		Category category = data.getCategory();
		if (typeDao.deleteCategory(category.getId())!=0) {
			map.put("code", 0);
		}else {
			map.put("code", 101);
		}
		return map;
	}
}
