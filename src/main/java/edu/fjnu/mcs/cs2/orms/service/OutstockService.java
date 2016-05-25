package edu.fjnu.mcs.cs2.orms.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.EmployeeDao;
import edu.fjnu.mcs.cs2.orms.dao.InoutstockResListDao;
import edu.fjnu.mcs.cs2.orms.dao.OutstockDao;
import edu.fjnu.mcs.cs2.orms.dao.SpecificResDao;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.InoutstockResList;
import edu.fjnu.mcs.cs2.orms.entity.Instock;
import edu.fjnu.mcs.cs2.orms.entity.Outstock;
import edu.fjnu.mcs.cs2.orms.entity.SpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.type.Department;
import edu.fjnu.mcs.cs2.orms.type.InstockType;
import edu.fjnu.mcs.cs2.orms.type.OutstockType;

/**   
 * @Title: OutstockService.java 
 * @Package edu.fjnu.mcs.cs2.orms.service 
 * @Description: TODO(出库模块业务处理) 
 * @author lbb
 * @date 2016年5月25日 下午7:58:34 
 * @version V1.0   
 */
@Service
public class OutstockService {
	
	@Resource
	TypeDao typeDao;
	
	@Resource 
	OutstockDao outstockDao;
	
	@Resource
	SpecificResDao specificResDao;
	
	@Resource
	InoutstockResListDao inoutstockResListDao;
	
	@Resource
	EmployeeDao employeeDao;
	
	Map<String, Object> map;
	
	/**
	 * 
	 * @Title: addOutstockType 
	 * @Description: TODO(添加出库类型) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> addOutstockType(DTO data) {
		OutstockType outstockType = data.getOutstockType();
		if (typeDao.insertOutstockType(outstockType)!=0) {
			map.put("code", 0);
		}else {
			map.put("code", 101);
		}
		return map;
	}

	/**
	 * 
	 * @Title: deleteOutstockType 
	 * @Description: TODO(删除某出库类型) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> deleteOutstockType(DTO data) {
		OutstockType outstockType = data.getOutstockType();
		if (typeDao.deleteCategory(outstockType.getId())!=0) {
			map.put("code", 0);
		}else {
			map.put("code", 101);
		}
		return map;
	}

	/**
	 * 
	 * @Title: getOutstockType 
	 * @Description: TODO(查询出库类型) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> getOutstockType() {
		List<OutstockType> outstockTypes = typeDao.getOutInfoByType(OutstockType.type);
		map.put("code", 0);
		map.put("instockTypes", outstockTypes);
		return map;
	}

	/**
	 * 
	 * @Title: addOutstock 
	 * @Description: TODO(添加出库信息) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> addOutstock(DTO data) {
		Outstock outstock = data.getOutstock();
		InoutstockResList inoutstockResList = new InoutstockResList();
		inoutstockResList.setOutstock(outstock);
		outstockDao.insertOutstock(outstock);
		List<SpecificRes> specificRess = outstock.getSpecificReses();
		for (SpecificRes specificRes : specificRess) {
			specificResDao.updateSpecificRes(specificRes);
			inoutstockResList.setSpecificRes(specificRes);
			inoutstockResListDao.updateRecord(inoutstockResList);
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * @Title: getOutstockInfo 
	 * @Description: TODO(查询出库信息) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> getOutstockInfo(DTO data) {
		Map<String, Object> query = new HashMap<>();
		Outstock outstock = data.getOutstock();
		Map<String, Object> map = data.getMap();
		int currentPage = data.getCurrentPage();
		int size = data.getSize();
		int offset = (currentPage - 1) * size;
		query.put("offset", offset);
		query.put("size", size);
		List<Outstock> outstocks = null;
		if (outstock.getType().getId()!=null) {
			query.put("typeId", outstock.getType().getId());
			outstocks= outstockDao.getOutstockInfoByTypeId(query);
		}else if (outstock.getType().getName()!=null) {
			query.clear();
			query.put("name", outstock.getType().getName());
			query.put("type", OutstockType.type);
			Integer typeId = typeDao.getTypeByName(query);
			query.clear();
			query.put("typeId", typeId);
			query.put("offset", offset);
			query.put("size", size);
			outstocks=  outstockDao.getOutstockInfoByTypeId(query);
		}else if (outstock.getEmployee().getId()!=null) {
			query.remove("typeId");
			query.put("id", outstock.getEmployee().getId());
			outstocks=  outstockDao.getInstockInfoByReEmId(query);
		}else if (outstock.getEmployee().getName()!=null) {
			Employee reciEmp= employeeDao.getEmplInfoByName(outstock.getEmployee().getName());
			query.remove("id");
			query.put("id", reciEmp.getId());
			outstocks = outstockDao.getInstockInfoByReEmId(query);
		}else if (outstock.getDepartment().getId()!=null) {
			query.remove("id");
			query.put("id", outstock.getDepartment().getId());
			outstocks = outstockDao.getOutstockInfoByReDeId(query);
		}else if (outstock.getDepartment().getName()!=null) {
			query.clear();
			query.put("name",outstock.getDepartment().getName());
			query.put("type", Department.type);
			Integer id = typeDao.getTypeByName(query);
			query.clear();
			query.put("offset", offset);
			query.put("size", size);
			query.put("id", id);
			outstocks = outstockDao.getOutstockInfoByReDeId(query);
		}else if (outstock.getMake().getId()!=null) {
			query.remove("id");
			query.put("id", outstock.getMake().getId());
			outstocks = outstockDao.getOutstockInfoByMakeId(query);
		}else if (outstock.getMake().getName()!=null) {
			Employee employee = employeeDao.getEmplInfoByName(outstock.getMake().getName());
			Integer makeId = employee.getId();
			query.remove("id");
			outstocks = outstockDao.getOutstockInfoByMakeId(query);
		}else if (outstock.getSpecificReses()!=null) {
			List<SpecificRes> specificRes = outstock.getSpecificReses();
			for (SpecificRes specificRes2 : specificRes) {
				if (specificRes2.getId()!=null) {
					InoutstockResList inoutstockResList =inoutstockResListDao.getListById(specificRes2.getId());
					outstocks= (List<Outstock>) outstockDao.getOutstockInfoById(inoutstockResList.getOutstock().getId());
				}
			}
		}else if (map!=null) {
			map.put("offset", offset);
			map.put("size", size);
			outstocks = outstockDao.getOutstockInfoByTime(map);
		}else {
			query.remove("id");
			query.remove("typeId");
			query.remove("name");
			outstocks = outstockDao.getInstockInfo(query);
			
		}
		for (Outstock outstock2 : outstocks) {
			List<InoutstockResList> inoutstockResList =inoutstockResListDao.getListByOutstockId(outstock2.getId());
			List<SpecificRes> specificReses =null;
			for (InoutstockResList inoutstockResList2 : inoutstockResList) {
				SpecificRes specificRes = inoutstockResList2.getSpecificRes();
						///specificResDao.getSpecificResById(inoutstockResList2.getResId());
				specificReses.add(specificRes);
			}
			outstock2 .setSpecificReses(specificReses);
		}
		this.map.put("code", 0);
		this.map.put("instocks", outstocks);
		return this.map;
	}
	
}
