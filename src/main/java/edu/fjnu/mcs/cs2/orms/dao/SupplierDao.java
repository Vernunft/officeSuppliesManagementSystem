package edu.fjnu.mcs.cs2.orms.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.entity.Instock;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;

/**   
 * @Title: SupplierDao.java 
 * @Package edu.fjnu.mcs.cs2.orms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月24日 上午12:33:28 
 * @version V1.0   
 */
@Repository
public interface SupplierDao {

	/**
	 *
	 * @Title: getSupplierByName 
	 * @Description: TODO(根据供应商的名称获取信息) 
	 * @param @param name
	 * @param @return    设定文件 
	 * @return Supplier    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier where name=#{name}")
	Supplier getSupplierByName(String name);
	
	/**
	 * 
	 * @Title: getSupplierById 
	 * @Description: TODO(根据供应商id查询信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return Supplier    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier where id=#{id}")
	Supplier getSupplierById(Integer id);
}
