package edu.fjnu.mcs.cs2.orms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.taglibs.standard.tei.ForEachTEI;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.imap.protocol.MailboxInfo;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.EmployeeDao;
import edu.fjnu.mcs.cs2.orms.dao.InoutstockResListDao;
import edu.fjnu.mcs.cs2.orms.dao.InstockDao;
import edu.fjnu.mcs.cs2.orms.dao.SpecificResDao;
import edu.fjnu.mcs.cs2.orms.dao.SupplierDao;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.InoutstockResList;
import edu.fjnu.mcs.cs2.orms.entity.Instock;
import edu.fjnu.mcs.cs2.orms.entity.SpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.type.InstockType;


/**   
 * @Title: InstockService.java 
 * @Package edu.fjnu.mcs.cs2.orms.service 
 * @Description: TODO(入库模块业务层) 
 * @author lbb
 * @date 2016年5月24日 下午4:49:43 
 * @version V1.0   
 */

@Service
public class InstockService {
	Map<String, Object> map = new HashMap<>();
	@Resource
	TypeDao typeDao;
	
	@Resource
	InstockDao instockDao;
	
	@Resource 
	SpecificResDao specificResDao;
	
	@Resource 
	SupplierDao supplierDao;
	
	@Resource
	EmployeeDao employeeDao;
	
	@Resource
	InoutstockResListDao inoutstockResListDao;
	/**
	 * 
	 * @Title: addInstockType 
	 * @Description: TODO(添加入库类型) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> addInstockType(DTO data) {
		if (typeDao.insertInstockType(data.getInstockType())==1){
			map.put("code", 0);
		}else {
			map.put("code", 101);
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: deleteInstockType 
	 * @Description: TODO(删除某入库类型) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> deleteInstockType(DTO data) {
		InstockType instockType = data.getInstockType();
		if (typeDao.deleteCategory(instockType.getId())==1) {
			map.put("code", 0);
		}else {
			map.put("code", 101);
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: getInstockType 
	 * @Description: TODO(查询入库类型) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> getInstockType() {
		List<InstockType> instockTypes = typeDao.getInfoByType(InstockType.type);
		map.put("code", 0);
		map.put("instockTypes", instockTypes);
		return map;
	}

	/**
	 * 
	 * @Title: addInstock 
	 * @Description: TODO( 添加物品入库信息) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * 
	 */
	//@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> addInstock(DTO data) {
		Instock instock = data.getInstock();
		InoutstockResList inoutstockResList = new InoutstockResList();
		inoutstockResList.setInstock(instock);
		instockDao.insertInstock(instock);
		List<SpecificRes> specificRess = instock.getSpecificRes();
		for (SpecificRes specificRes : specificRess) {
			specificResDao.insertSpecificRes(specificRes);
			inoutstockResList.setSpecificRes(specificRes);
			inoutstockResListDao.insertInRecord(inoutstockResList);
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * @Title: getInstockInfo 
	 * @Description: TODO(获取入库信息) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	//@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Map<String, Object> getInstockInfo(DTO data) {
		Map<String, Object> query = new HashMap<>();
		Instock instock = data.getInstock();
		Map<String, Object> map = data.getMap();
		List<Instock> instocks = null;
		int currentPage = data.getCurrentPage();
		int size = data.getSize();
		int offset = (currentPage - 1) * size;
		query.put("offset", offset);
		query.put("size", size);
		if (instock.getType().getId()!=null) {
			query.put("typeId", instock.getType().getId());
			instocks=(List<Instock>) instockDao.getInstockInfoByTypeId(query);
		}else if (instock.getSupplier().getId()!=null) {
			query.put("suppllierId", instock.getSupplier().getId());
			instocks=  instockDao.getInstockInfoBySupId(query);
		}else if (instock.getSupplier().getName()!=null) {
			Supplier supplier= supplierDao.getSupplierByName(instock.getSupplier().getName());
			query.put("suppllierId", supplier.getId());
			instocks = instockDao.getInstockInfoBySupId(query);
		}else if (instock.getAttn().getId()!=null) {
			query.put("attnId", instock.getAttn().getId());
			instocks = instockDao.getInstockInfoByAttnId(query);
			
		}else if (instock.getAttn().getName()!=null) {
			Employee employee = employeeDao.getEmplInfoByName(instock.getAttn().getName());
			Integer attnId = employee.getId();
			query.put("attnId", attnId);
			instocks = instockDao.getInstockInfoByAttnId(query);
		}else if (instock.getMake().getId()!=null) {
			query.put("makeId", instock.getMake().getId());
			instocks = instockDao.getInstockInfoByMakeId(query);
		}else if (instock.getMake().getName()!=null) {
			Employee employee = employeeDao.getEmplInfoByName(instock.getMake().getName());
			Integer makeId = employee.getId();
			query.put("makeId", makeId);
			instocks = instockDao.getInstockInfoByMakeId(query);
		}else if (instock.getSpecificRes()!=null) {
			List<SpecificRes> specificRes = instock.getSpecificRes();
			for (SpecificRes specificRes2 : specificRes) {
				if (specificRes2.getId()!=null) {
					InoutstockResList inoutstockResList =inoutstockResListDao.getListById(specificRes2.getId());
					instocks=instockDao.getInstockInfoById(inoutstockResList.getInstock().getId()) ;
				}
			}
		}else if (map!=null) {
			map.put("offset", offset);
			map.put("size",size);
			instocks = instockDao.getInstockInfoByTime(map);
		}else if (instock.getType().getName()!=null) {
			query.put("name", instock.getType().getName());
			query.put("type", InstockType.type);
			Integer id = typeDao.getTypeByName(query);
			query.put("id", id);
			instocks= instockDao.getInstockInfoByTypeId(query);
		}else {
			instocks = instockDao.getInstockInfo(query);
		}
		for (Instock instock2 : instocks) {
			List<InoutstockResList> inoutstockResList =inoutstockResListDao.getListByInstockId(instock2.getId());
			List<SpecificRes> specificReses =null;
			for (InoutstockResList inoutstockResList2 : inoutstockResList) {
				SpecificRes specificRes = inoutstockResList2.getSpecificRes();
						///specificResDao.getSpecificResById(inoutstockResList2.getResId());
				specificReses.add(specificRes);
			}
			instock2.setSpecificRes(specificReses);
		}
		this.map.put("code", 0);
		this.map.put("instocks", instocks);
		return this.map;
	}
}
