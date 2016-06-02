package edu.fjnu.mcs.cs2.orms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import edu.fjnu.mcs.cs2.orms.common.DTO;
import edu.fjnu.mcs.cs2.orms.dao.ResDao;
import edu.fjnu.mcs.cs2.orms.dao.TypeDao;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.type.Unit;

/**   
 * @Title: StockService.java 
 * @Package edu.fjnu.mcs.cs2.orms.service 
 * @Description: TODO(库存模块业务层) 
 * @author lbb
 * @date 2016年5月28日 下午8:15:08 
 * @version V1.0   
 */
@Service
public class StockService {

	@Resource
	ResDao resDao;
	
	@Resource
	TypeDao typeDao;
	
	Map<String, Object> map = new HashMap<>();
	/**
	 * 
	 * @Title: addRes 
	 * @Description: TODO(添加新的物品信息 ) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@Transactional
	public Map<String, Object> addRes(DTO data) {
		Res res = data.getRes();
		Unit unit = res.getUnit();
		if (typeDao.getUnitByName(unit.getName())==null) {
			unit.setRemark("计量单位");
			typeDao.insertUnit(unit);
		}
		if (resDao.getResByName(res.getName())!=null) {
			map.put("code", 101);
		}else {
			resDao.insertRes(res);
			map.put("code", 0);
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: modifyRes 
	 * @Description: TODO(修改物品的信息 ) 
	 * @param @param data
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	@Transactional
	public Map<String, Object> modifyRes(DTO data) {
		Res res = data.getRes();
		if (resDao.getResById(res.getId())!=null) {
			resDao.updateRes(data.getRes());
			map.put("code", 0);
		}else{
			map.put("code", 101);
		}
		return map;
	}

	/**
	 * 
	 * @Title: getExceedMaxLimitRes 
	 * @Description: TODO(查询超出库存上限的物品信息,此处暂时不考虑分页) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> getExceedMaxLimitRes() {
		List<Res> reses = resDao.getUpMaxRes();
		map.put("code", 0);
		map.put("reses", reses);
		return map;
	}

	/**
	 * 
	 * @Title: getExceedMinLimitRes 
	 * @Description: TODO(查询超出库存下限的物品信息 ) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public Map<String, Object> getExceedMinLimitRes() {
		List<Res> reses = resDao.getUpMinRes();
		map.put("code", 0);
		map.put("reses", reses);
		return map;
	}

}
