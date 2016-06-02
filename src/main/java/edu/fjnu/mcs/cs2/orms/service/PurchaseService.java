package edu.fjnu.mcs.cs2.orms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.PurchaseDao;
import edu.fjnu.mcs.cs2.orms.dao.PurchaseResDao;
import edu.fjnu.mcs.cs2.orms.dao.SupplierSuppliesDao;
import edu.fjnu.mcs.cs2.orms.entity.Purchase;
import edu.fjnu.mcs.cs2.orms.entity.PurchaseRes;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.entity.SupplierSupplies;

/**
 * @Title: PurchaseService.java
 * @Package edu.fjnu.mcs.cs2.orms.service
 * @Description: TODO(采购业务层)
 * @author lbb
 * @date 2016年5月28日 下午9:18:14
 * @version V1.0
 */
@Service
public class PurchaseService {

	@Resource
	PurchaseDao purchaseDao;

	@Resource
	PurchaseResDao purchaseResDao;

	@Resource
	SupplierSuppliesDao supplierSuppliesDao;

	Map<String, Object> map = new HashMap<>();

	/**
	 * 
	 * @Title: addPurchase @Description: TODO(添加采购 ) @param @param
	 * data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> addPurchase(DTO data) {
		Purchase purchase = data.getPurchase();
		List<PurchaseRes> purchaseReses = purchase.getPurchaseRes();
		SupplierSupplies supplierSupplie = new SupplierSupplies();
		purchase.setComplete(true);
		purchaseDao.insertPurchase(purchase);
		for (PurchaseRes purchaseRes : purchaseReses) {
			purchaseRes.setPurchase(purchase);
			purchaseResDao.insertPurRes(purchaseRes);
			supplierSupplie.setRes(purchaseRes.getRes());
			supplierSupplie.setPrice(purchaseRes.getResPrice());
		//	supplierSupplie = supplierSuppliesDao.getSuplierIdByRes(purchaseRes.getRes().getId());
			//待定
			supplierSuppliesDao.updatePriceById(supplierSupplie);
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * @Title: getPurchase @Description: TODO(查询采购单) @param @param
	 * data @param @return 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> getPurchase(DTO data) {
		Purchase purchase = data.getPurchase();
		Map<String, Object> query = new HashMap<>();
		int currentPage = data.getCurrentPage() != null?1:data.getCurrentPage();
		int size = data.getSize() != null?10:data.getSize();
		int offset = (currentPage - 1) * size;
		query.put("offset", offset);
		query.put("size", size);
		int totalPage = 0;
		int rowCount = 0;
		List<Purchase> purchases = new ArrayList<>();
		List<PurchaseRes> purchaseReses = new ArrayList<>();
		List<Res> reses = new ArrayList<>();
		if (purchase.getId() != null) {
			purchase= purchaseDao.getPurchaseById(purchase.getId());
			purchases.add(purchase);
		} else if (purchase.getPic().getId() != null) {
			query.put("picId", purchase.getPic().getId());
			purchases = purchaseDao.getPurchaseByPicId(query);
			rowCount = purchaseDao.getRowCountByPicId( purchase.getPic().getId());
		} else if (purchase.getComplete() != null) {
			query.put("complete", purchase.getComplete());
			purchases = purchaseDao.getPurchaseByCom(query);
			rowCount = purchaseDao.getRowCountByCom(purchase.getComplete());
		} else if (purchase.getPurchaseRes().get(0).getRes().getId()!= null) {
			purchaseReses = purchaseResDao.getPurchaseResByResId(purchase.getPurchaseRes().get(0).getRes().getId());
			for (PurchaseRes purchaseRes : purchaseReses) {
				purchase = purchaseRes.getPurchase();
				purchases.add(purchase);
			}
			rowCount = purchaseReses.size();
		} else {
			purchases = purchaseDao.getAllInfo(query);
			rowCount = purchaseDao.getAllRowCoun();
		}
		Res res = new Res();
		for (Purchase purchase2 : purchases) {
			if (purchase2!=null) {
				purchaseReses= purchaseResDao.getPurchaseResByPId(purchase2.getId());
				for (PurchaseRes purchaseRes : purchaseReses) {
					res = purchaseRes.getRes();
					reses.add(res);
				}
				purchase2.setReses(reses);
			}
		}
		map.put("code", 0);
		map.put("purchases", purchases);
		this.map.put("currentPage", currentPage);
		this.map.put("size", size);
		if (rowCount % size != 0) {
			totalPage = rowCount / size + 1;
		}else{
			totalPage = rowCount /size;
		}
		this.map.put("totalPage", totalPage);
		return map;
	}

}
