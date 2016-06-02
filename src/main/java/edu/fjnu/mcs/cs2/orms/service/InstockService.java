package edu.fjnu.mcs.cs2.orms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.taglibs.standard.tei.ForEachTEI;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.imap.protocol.MailboxInfo;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.EmployeeDao;
import edu.fjnu.mcs.cs2.orms.dao.InstockSpecificResDao;
import edu.fjnu.mcs.cs2.orms.dao.PurchaseDao;
import edu.fjnu.mcs.cs2.orms.dao.PurchaseResDao;
import edu.fjnu.mcs.cs2.orms.dao.ResDao;
import edu.fjnu.mcs.cs2.orms.dao.InstockDao;
import edu.fjnu.mcs.cs2.orms.dao.SpecificResDao;
import edu.fjnu.mcs.cs2.orms.dao.SupplierDao;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.InstockSpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Purchase;
import edu.fjnu.mcs.cs2.orms.entity.PurchaseRes;
import edu.fjnu.mcs.cs2.orms.entity.Res;
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
	InstockSpecificResDao instockSpecificResDao;

	@Resource
	ResDao resDao;

	@Resource
	PurchaseResDao purchaseResDao;

	/**
	 * 
	 * @Title: addInstockType @Description: TODO(添加入库类型) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> addInstockType(DTO data) {
		if (typeDao.getInstockTypeByName(data.getInstockType().getName()) != null) {
			map.put("code", 101);
		} else {
			typeDao.insertInstockType(data.getInstockType());
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 
	 * @Title: deleteInstockType @Description: TODO(删除某入库类型) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> deleteInstockType(DTO data) {
		InstockType instockType = data.getInstockType();
		if (typeDao.deleteCategory(instockType.getId()) != 0) {
			map.put("code", 0);
		} else {
			map.put("code", 101);
		}
		return map;
	}

	/**
	 * 
	 * @Title: getInstockType @Description: TODO(查询入库类型) @param @return
	 *         设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> getInstockType() {
		List<InstockType> instockTypes = typeDao.getInfoByType(InstockType.instockType);
		map.put("code", 0);
		map.put("instockTypes", instockTypes);
		return map;
	}

	/**
	 * 
	 * @Title: addInstock
	 * @Description: TODO( 添加物品入库信息)
	 * @param @param
	 *            data
	 * @param @return
	 *            设定文件
	 * @return Map<String,Object> 返回类型
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> addInstock(DTO data) {
		Instock instock = data.getInstock();
		instock.setMake(instock.getAttn());
		InstockSpecificRes instockResList = new InstockSpecificRes();
		instockResList.setInstock(instock);
		List<SpecificRes> specificRess = instock.getSpecificReses();
		int totalCount = 0;
		float totalPrice = 0;
		instockDao.insertInstock(instock);
		for (SpecificRes specificRes : specificRess) {
			specificResDao.insertSpecificRes(specificRes);
			instockResList.setSpecificRes(specificRes);
			instockSpecificResDao.insertInRecord(instockResList);
			totalPrice += specificRes.getBuyPrice();
			totalCount++;
			Res res = resDao.getResById(specificRes.getRes().getId());
			if (res != null) {
//				List<PurchaseRes> purchaseRess = purchaseResDao.getPurchaseResByResId(res.getId());
//				for (PurchaseRes purchaseRes2 : purchaseRess) {
//					totalCount += purchaseRes2.getArrivalCount();
//				}
				res.setStockNow(res.getStockNow() + 1);
				resDao.updateRes(res);
			}
		}
		instock.setTotalCount(totalCount);
		instock.setTotalPrice(totalPrice);
		instockDao.updateInstock(instock);
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * @Title: getInstockInfo @Description: TODO(获取入库信息) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> getInstockInfo(DTO data) {
		Map<String, Object> query = new HashMap<>();
		Instock instock = data.getInstock();
		Map<String, Object> map = data.getMap();
		List<Instock> instocks = new ArrayList<>();
		int currentPage = data.getCurrentPage() != null?1:data.getCurrentPage();
		int size = data.getSize() != null?10:data.getSize();
		int offset = (currentPage - 1) * size;
		query.put("offset", offset);
		query.put("size", size);
		int totalPage = 0;
		int rowCount = 0;
		if (instock.getType().getId() != null) {
			query.put("typeId", instock.getType().getId());
			instocks = instockDao.getInstockInfoByTypeId(query);
			rowCount = instockDao.getRowCountByTypeId(instock.getType().getId());
		} else if (instock.getType().getName() != null) {
			query.put("name", instock.getType().getName());
			query.put("type", InstockType.instockType);
			Integer id = typeDao.getTypeByName(query);
			System.out.println(id);
			query.put("typeId", id);
			instocks = instockDao.getInstockInfoByTypeId(query);
			rowCount = instockDao.getRowCountByTypeId(id);
		} else if (instock.getSupplier().getId() != null) {
			query.put("suppllierId", instock.getSupplier().getId());
			instocks = instockDao.getInstockInfoBySupId(query);
			rowCount = instockDao.getRowCountBySupId(instock.getSupplier().getId());
		} else if (instock.getSupplier().getName() != null) {
			Supplier supplier = supplierDao.getSupplierByName(instock.getSupplier().getName());
			query.put("suppllierId", supplier.getId());
			instocks = instockDao.getInstockInfoBySupId(query);
			rowCount = instockDao.getRowCountBySupId(supplier.getId());
		} else if (instock.getAttn().getId() != null) {
			query.put("attnId", instock.getAttn().getId());
			instocks = instockDao.getInstockInfoByAttnId(query);
			rowCount = instockDao.getRowCountByAttId(instock.getAttn().getId());
		} else if (instock.getAttn().getName() != null) {
			Employee employee = employeeDao.getEmplInfoByName(instock.getAttn().getName());
			Integer attnId = employee.getId();
			query.put("attnId", attnId);
			instocks = instockDao.getInstockInfoByAttnId(query);
			rowCount = instockDao.getRowCountByAttId(attnId);
		} else if (instock.getMake().getId() != null) {
			query.put("makeId", instock.getMake().getId());
			instocks = instockDao.getInstockInfoByMakeId(query);
			rowCount = instockDao.getRowCountByMakeId(instock.getMake().getId());
		} else if (instock.getMake().getName() != null) {
			Employee employee = employeeDao.getEmplInfoByName(instock.getMake().getName());
			Integer makeId = employee.getId();
			query.put("makeId", makeId);
			instocks = instockDao.getInstockInfoByMakeId(query);
			rowCount = instockDao.getRowCountByMakeId(makeId);
		} else if (instock.getSpecificReses().get(0).getId() != null) {
			InstockSpecificRes instockResList = instockSpecificResDao.getListById(instock.getSpecificReses().get(0).getId());
			instock= instockDao.getInstockInfoById(instockResList.getInstock().getId());
			System.out.println(instock);
			instocks.add(instock);
		} else if (!map .isEmpty()) {
			map.put("offset", offset);
			map.put("size", size);
			instocks = instockDao.getInstockInfoByTime(map);
			rowCount = instockDao.getRowCountByTime(map);
		} else {
			instocks = instockDao.getInstockInfo(query);
			rowCount = instockDao.getRowCount();
		}
		for (Instock instock2 : instocks) {
			if (instock2!=null) {
				List<InstockSpecificRes> instockSpecificRes = instockSpecificResDao.getListByInstockId(instock2.getId());
				List<SpecificRes> specificReses = new ArrayList<>();
				for (InstockSpecificRes instockResList2 : instockSpecificRes) {
					if (instockResList2!=null) {
						System.out.println(instockResList2.getSpecificRes());
						SpecificRes specificRes = instockResList2.getSpecificRes();
						specificReses.add(specificRes);
					}
				}
				instock2.setSpecificReses(specificReses);
			}
		}
		this.map.put("code", 0);
		this.map.put("instocks", instocks);
		this.map.put("currentPage", currentPage);
		this.map.put("size", size);
		if (rowCount % size != 0) {
			totalPage = rowCount / size + 1;
		}else{
			totalPage = rowCount /size;
		}
		this.map.put("totalPage", totalPage);
		return this.map;
	}
}
