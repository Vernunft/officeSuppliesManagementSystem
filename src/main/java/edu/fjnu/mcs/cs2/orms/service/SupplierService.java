package edu.fjnu.mcs.cs2.orms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.ResDao;
import edu.fjnu.mcs.cs2.orms.dao.SupplierDao;
import edu.fjnu.mcs.cs2.orms.dao.SupplierSuppliesDao;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.InstockSpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Instock;
import edu.fjnu.mcs.cs2.orms.entity.Outstock;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.entity.SpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.entity.SupplierSupplies;
import edu.fjnu.mcs.cs2.orms.type.InstockType;
import edu.fjnu.mcs.cs2.orms.type.OutstockType;
import edu.fjnu.mcs.cs2.orms.type.SupplierStatus;
import edu.fjnu.mcs.cs2.orms.type.SupplierType;

/**
 * @Title: SupplierService.java
 * @Package edu.fjnu.mcs.cs2.orms.service
 * @Description: TODO(供应商模块业务层)
 * @author lbb
 * @date 2016年5月26日 下午2:39:50
 * @version V1.0
 */
@Service
public class SupplierService {

	@Resource
	TypeDao typeDao;

	@Resource
	SupplierDao supplierDao;

	@Resource
	SupplierSuppliesDao supplierSuppliesDao;

	@Resource
	ResDao resDao;

	Map<String, Object> map = new HashMap<>();

	/**
	 * 
	 * @Title: addSupplierType @Description: TODO(添加供应商类别) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> addSupplierType(DTO data) {
		SupplierType supplierType = data.getSupplierType();
		if (typeDao.getSUpplierTypeByName(supplierType.getName()) != null) {
			map.put("code", 101);
		} else {
			typeDao.insertSupplierType(supplierType);
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 
	 * @Title: deleteSupplierType @Description: TODO(删除某供应商类型 ) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> deleteSupplierType(DTO data) {
		SupplierType supplierType = data.getSupplierType();
		if (typeDao.deleteCategory(supplierType.getId()) != 0) {
			map.put("code", 0);
		} else {
			map.put("code", 101);
		}
		return map;
	}

	/**
	 * 
	 * @Title: getOutstockType @Description: TODO(查询供应商类型 ) @param @return
	 *         设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> getSupplierType() {
		List<SupplierType> supplierTypes = typeDao.getSupInfoByType(SupplierType.supplierType);
		map.put("code", 0);
		map.put("supplierTypes", supplierTypes);
		return map;
	}

	/**
	 * 
	 * @Title: addSupplier @Description: TODO(添加供应商 ) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> addSupplier(DTO data) {
		Supplier supplier = data.getSupplier();
		if (supplierDao.insertSupplier(supplier) != 0) {
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 
	 * @Title: getSupplier @Description: TODO(查询供应商信息) @param @param
	 *         data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> getSupplier(DTO data) {
		Map<String, Object> query = new HashMap<>();
		Supplier supplier = data.getSupplier();
		Map<String, Object> map = data.getMap();
		List<Supplier> suppliers = new ArrayList<>();
		List<SupplierSupplies> supplierSupplieses = new ArrayList<>();
		int currentPage = data.getCurrentPage() != null?1:data.getCurrentPage();
		int size = data.getSize() != null?10:data.getSize();
		int offset = (currentPage - 1) * size;
		int totalPage = 0;
		int rowCount = 0;
		query.put("offset", offset);
		query.put("size", size);
		if (supplier.getType().getId() != null) {
			query.put("typeId", supplier.getType().getId());
			suppliers = supplierDao.getSupplierByTypeId(query);
			rowCount = supplierDao.getRowCountByTypeId(supplier.getType().getId());
		} else if (supplier.getName() != null) {
			supplier = supplierDao.getSupplierByName(supplier.getName());
			suppliers.add(supplier);
		} else if (supplier.getShortName() != null) {
			supplier = supplierDao.getSupplierBySName(supplier.getShortName());
			suppliers.add(supplier);
		} else if (supplier.getContactName() != null) {
			// 考虑重名
			query.put("contactName", supplier.getContactName());
			suppliers = supplierDao.getSupByConName(query);
			rowCount = supplierDao.getRowCountByConName(supplier.getContactName());
		} else if (supplier.getContactMobile() != null) {
			supplier = supplierDao.getSupByConMob(supplier.getContactMobile());
			suppliers.add(supplier);
		} else if (supplier.getContactNumber() != null) {
			supplier = supplierDao.getSupByConNum(supplier.getContactNumber());
			suppliers.add(supplier);
		} else if (supplier.getFax() != null) {
			supplier = supplierDao.getSupByFax(supplier.getFax());
			suppliers.add(supplier);
		} else if (supplier.getPostcode() != null) {
			query.put("postcode", supplier.getPostcode());
			suppliers = supplierDao.getSupByPos(query);
			rowCount = supplierDao.getRowCountByPos(supplier.getPostcode());
		} else if (supplier.getAddress() != null) {
			supplier = supplierDao.getSupByAdd(supplier.getAddress());
			suppliers.add(supplier);
		} else if (supplier.getReses().get(0).getId() != null) {
			List<Res> reses = supplier.getReses();
			supplierSupplieses = supplierSuppliesDao.getSuplierIdByRes(supplier.getReses().get(0).getId());
			for (SupplierSupplies supplierSupplies : supplierSupplieses) {
				suppliers.add(supplierSupplies.getSupplier());
			}
			rowCount = supplierSupplieses.size();
		} else if (supplier.getReses().get(0).getName() != null) {
			Res res2 = resDao.getResByName(supplier.getReses().get(0).getName());
			supplierSupplieses = supplierSuppliesDao.getSuplierIdByRes(res2.getId());
			for (SupplierSupplies supplierSupplies : supplierSupplieses) {
				suppliers.add(supplierSupplies.getSupplier());
			}
			rowCount = supplierSupplieses.size();
		} else {
			suppliers = supplierDao.getSupplier(query);
			rowCount = supplierDao.getAllRowCount();
		}
		List<Res> reses = new ArrayList<>();
		for (Supplier supplier2 : suppliers) {
			List<SupplierSupplies> supplierSupplies = supplierSuppliesDao.getSupSupBySuId(supplier2.getId());
			for (SupplierSupplies supplierSupplies2 : supplierSupplies) {
				Res res = supplierSupplies2.getRes();
				reses.add(res);
			}
			supplier2.setReses(reses);
		}
		this.map.put("code", 0);
		this.map.put("suppliers", suppliers);
		this.map.put("currentPage", currentPage);
		this.map.put("size", size);
		if (rowCount % size != 0) {
			totalPage = rowCount / size + 1;
		} else {
			totalPage = rowCount / size;
		}
		this.map.put("totalPage", totalPage);
		return this.map;

	}

	/**
	 * 
	 * @Title: addSupplierSupplies @Description: TODO(添加供应商供应的物品信息
	 *         ) @param @param data @param @return 设定文件 @return Map
	 *         <String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> addSupplierSupplies(DTO data) {
		SupplierSupplies supplierSupplies = data.getSupplierSupplies();
		if (resDao.getResById(supplierSupplies.getRes().getId()).getId()==null) {
			map.put("code", 101);
		} else if (supplierDao.getSupplierById(supplierSupplies.getSupplier().getId()).getId() == null) {
			map.put("code", 102);
		} else if (supplierSupplies.getPrice() <=0) {
			map.put("code", 103);
		} else {
			//直接定为可供货
			SupplierStatus status = new SupplierStatus();
			status.setId(20);
			supplierSupplies.setStatus(status);
			supplierSuppliesDao.insertInfo(supplierSupplies);
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 
	 * @Title: getSupplierSupplies @Description: TODO(查询供应商供应的物品信息
	 *         ) @param @param data @param @return 设定文件 @return Map
	 *         <String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> getSupplierSupplies(DTO data) {
		SupplierSupplies supplierSupplies = data.getSupplierSupplies();
		Map<String, Object> query = new HashMap<>();
		int currentPage = data.getCurrentPage() != null?1:data.getCurrentPage();
		int size = data.getSize() != null?10:data.getSize();
		int offset = (currentPage - 1) * size;
		int totalPage = 0;
		int rowCount = 0;
		query.put("offset", offset);
		query.put("size", size);
		List<SupplierSupplies> supplierSupplieses = new ArrayList<>();
		if (supplierSupplies.getSupplier().getId() != null) {
			query.put("id", supplierSupplies.getSupplier().getId());
			supplierSupplieses = supplierSuppliesDao.getSupSupBySuIdPage(query);
			rowCount = supplierSuppliesDao.getRowCountBySuId(supplierSupplies.getSupplier().getId());
		} else if (supplierSupplies.getRes().getId() != null) {
			query.put("resId", supplierSupplies.getRes().getId());
			supplierSupplieses = supplierSuppliesDao.getSuplierIdByResPage(query);
			rowCount = supplierSuppliesDao.getRowCountByResId(supplierSupplies.getRes().getId());
		} else {
			supplierSupplieses = supplierSuppliesDao.getAllInfo(query);
			rowCount = supplierSuppliesDao.getAllRowCount();
		}
		map.put("supplierSupplieses", supplierSupplieses);
		map.put("code", 0);
		this.map.put("currentPage", currentPage);
		this.map.put("size", size);
		if (rowCount % size != 0) {
			totalPage = rowCount / size + 1;
		} else {
			totalPage = rowCount / size;
		}
		this.map.put("totalPage", totalPage);
		return map;
	}

	/**
	 * 
	 * @Title: modifySupplierSupplies @Description:
	 *         TODO(修改供应商供应的物品标价) @param @param data @param @return 设定文件 @return
	 *         Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> modifySupplierSupplies(DTO data) {
		SupplierSupplies supplierSupplies = data.getSupplierSupplies();
		if (supplierSuppliesDao.getInfoById(supplierSupplies.getId()) == null) {
			map.put("code", 101);
		} else {
			supplierSuppliesDao.updatePriceById(supplierSupplies);
			map.put("code", 0);
		}

		return map;
	}

	/**
	 * 
	 * @Title: deleteSupplierSupplies @Description: TODO(删除供应商供应的物品
	 *         ) @param @param data @param @return 设定文件 @return Map
	 *         <String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> deleteSupplierSupplies(DTO data) {
		SupplierSupplies supplierSupplies = data.getSupplierSupplies();
		if (supplierSuppliesDao.getInfoById(supplierSupplies.getId()) == null) {
			map.put("code", 101);
		} else {
			supplierSuppliesDao.deleteInfoById(supplierSupplies.getId());
			map.put("code", 0);
		}
		return map;
	}

}
