package edu.fjnu.mcs.cs2.orms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.EmployeeDao;
import edu.fjnu.mcs.cs2.orms.dao.InstockSpecificResDao;
import edu.fjnu.mcs.cs2.orms.dao.OutstockDao;
import edu.fjnu.mcs.cs2.orms.dao.OutstockSpecificResDao;
import edu.fjnu.mcs.cs2.orms.dao.ResDao;
import edu.fjnu.mcs.cs2.orms.dao.SpecificResDao;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.InstockSpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Instock;
import edu.fjnu.mcs.cs2.orms.entity.Outstock;
import edu.fjnu.mcs.cs2.orms.entity.OutstockSpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.PurchaseRes;
import edu.fjnu.mcs.cs2.orms.entity.Res;
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
	InstockSpecificResDao inoutstockResListDao;

	@Resource
	EmployeeDao employeeDao;

	@Resource
	ResDao resDao;

	@Resource
	OutstockSpecificResDao outstockSpecificResDao;

	Map<String, Object> map = new HashMap<>();

	/**
	 * 
	 * @Title: addOutstockType @Description: TODO(添加出库类型) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> addOutstockType(DTO data) {
		OutstockType outstockType = data.getOutstockType();
		if (typeDao.getOutstockTypeByName(outstockType.getName()) != null) {
			map.put("code", 101);
		} else {
			typeDao.insertOutstockType(outstockType);
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 
	 * @Title: deleteOutstockType @Description: TODO(删除某出库类型) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> deleteOutstockType(DTO data) {
		OutstockType outstockType = data.getOutstockType();
		if (typeDao.deleteCategory(outstockType.getId()) == 1) {
			map.put("code", 0);
		} else {
			map.put("code", 101);
		}
		return map;
	}

	/**
	 * 
	 * @Title: getOutstockType @Description: TODO(查询出库类型) @param @return
	 *         设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> getOutstockType() {
		List<OutstockType> outstockTypes = typeDao.getOutInfoByType(OutstockType.outstockType);
		map.put("code", 0);
		map.put("instockTypes", outstockTypes);
		return map;
	}

	/**
	 * 
	 * @Title: addOutstock @Description: TODO(添加出库信息) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> addOutstock(DTO data) {
		Outstock outstock = data.getOutstock();
		OutstockSpecificRes outstockSpecificRes = new OutstockSpecificRes();
		outstockSpecificRes.setOutstock(outstock);
		int totalCount = 0;
		float totalPrice = 0;
		outstockDao.insertOutstock(outstock);
		List<SpecificRes> specificRess = outstock.getSpecificReses();
		for (SpecificRes specificRes : specificRess) {
			specificResDao.updateSpecificRes(specificRes);
			System.out.println(specificRes);
			outstockSpecificRes.setSpecificRes(specificRes);
			outstockSpecificResDao.insertOutRecord(outstockSpecificRes);
			totalPrice += specificRes.getBuyPrice();
			totalCount++;
			Res res = resDao.getResById(specificRes.getRes().getId());
			if (res != null) {
				res.setStockNow(res.getStockNow() + 1);
				resDao.updateRes(res);
			}
		}
		outstock.setTotalCount(totalCount);
		outstock.setTotalPrice(totalPrice);
		outstockDao.updateOutstock(outstock);
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * @Title: getOutstockInfo @Description: TODO(查询出库信息) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> getOutstockInfo(DTO data) {
		Map<String, Object> query = new HashMap<>();
		Outstock outstock = data.getOutstock();
		Map<String, Object> map = data.getMap();
		int currentPage = data.getCurrentPage() != null?1:data.getCurrentPage();
		int size = data.getSize() != null?10:data.getSize();
		int offset = (currentPage - 1) * size;
		query.put("offset", offset);
		query.put("size", size);
		List<Outstock> outstocks = new ArrayList<>();
		int totalPage = 0;
		int rowCount = 0;
		if (outstock.getType().getId() != null) {
			System.out.println("1");
			query.put("typeId", outstock.getType().getId());
			outstocks = outstockDao.getOutstockInfoByTypeId(query);
			rowCount = outstockDao.getRowCountByTypeId(outstock.getType().getId());
		} else if (outstock.getType().getName() != null) {
			query.put("name", outstock.getType().getName());
			query.put("type", OutstockType.outstockType);
			Integer typeId = typeDao.getTypeByName(query);
			query.put("typeId", typeId);
			outstocks = outstockDao.getOutstockInfoByTypeId(query);
			rowCount = outstockDao.getRowCountByTypeId(typeId);
		} else if (outstock.getEmployee().getId() != null) {
			query.put("id", outstock.getEmployee().getId());
			outstocks = outstockDao.getOutstockInfoByReEmId(query);
			rowCount = outstockDao.getrowCountByReciEmp(outstock.getEmployee().getId());
		} else if (outstock.getEmployee().getName() != null) {
			Employee reciEmp = employeeDao.getEmplInfoByName(outstock.getEmployee().getName());
			query.put("id", reciEmp.getId());
			outstocks = outstockDao.getOutstockInfoByReEmId(query);
			rowCount = outstockDao.getrowCountByReciEmp(reciEmp.getId());
		} else if (outstock.getDepartment().getId() != null) {
			query.put("id", outstock.getDepartment().getId());
			outstocks = outstockDao.getOutstockInfoByReDeId(query);
			rowCount = outstockDao.getRowCountByDeId(outstock.getDepartment().getId());
		} else if (outstock.getDepartment().getName() != null) {
			query.put("name", outstock.getDepartment().getName());
			query.put("type", Department.outstockType);
			Integer id = typeDao.getTypeByName(query);
			query.put("id", id);
			outstocks = outstockDao.getOutstockInfoByReDeId(query);
			rowCount = outstockDao.getRowCountByDeId(id);
		} else if (outstock.getMake().getId() != null) {
			query.put("id", outstock.getMake().getId());
			outstocks = outstockDao.getOutstockInfoByMakeId(query);
			rowCount = outstockDao.getRowCountByMakeId(outstock.getMake().getId());
		} else if (outstock.getMake().getName() != null) {
			Employee employee = employeeDao.getEmplInfoByName(outstock.getMake().getName());
			Integer makeId = employee.getId();
			outstocks = outstockDao.getOutstockInfoByMakeId(query);
			rowCount = outstockDao.getRowCountByMakeId(makeId);
		} else if (outstock.getSpecificReses().get(0).getId() != null) {
			OutstockSpecificRes outstockSpecificRes = outstockSpecificResDao
					.getRecordBySpeId(outstock.getSpecificReses().get(0).getId());
			outstock = outstockDao.getOutstockInfoById(outstockSpecificRes.getOutstock().getId());
			outstocks.add(outstock);
		} else if (!map.isEmpty()) {
			map.put("offset", offset);
			map.put("size", size);
			outstocks = outstockDao.getOutstockInfoByTime(map);
			rowCount = outstockDao.getRowCountByTime(map);
		}else{
			outstocks = outstockDao.getOutstockInfo(query);
			rowCount = outstockDao.getAllRowCount(query);
		}
		for (Outstock outstock2 : outstocks) {
			if (outstock2 != null) {
				System.out.println(outstock2);
				List<OutstockSpecificRes> outstockSpecificRes = outstockSpecificResDao
						.getListByOutstockId(outstock2.getId());
				System.out.println(outstockSpecificRes);
				List<SpecificRes> specificReses = new ArrayList<>();
				for (OutstockSpecificRes outstockSpecificRes2 : outstockSpecificRes) {
					if (outstockSpecificRes2 != null) {
						SpecificRes specificRes = outstockSpecificRes2.getSpecificRes();
						System.out.println(specificRes);
						/// specificResDao.getSpecificResById(inoutstockResList2.getResId());
						specificReses.add(specificRes);
					}
				}
				outstock2.setSpecificReses(specificReses);
			}
		}
		this.map.put("code", 0);
		this.map.put("instocks", outstocks);
		this.map.put("currentPage", currentPage);
		this.map.put("size",size);
		if (rowCount % data.getSize() != 0) {
			totalPage = rowCount / data.getSize() + 1;
		}else{
			totalPage = rowCount /size;
		}
		this.map.put("totalPage", totalPage);
		return this.map;
	}

}
