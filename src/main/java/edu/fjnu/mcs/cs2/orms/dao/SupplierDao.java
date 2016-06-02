package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
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
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
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
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	Supplier getSupplierById(Integer id);

	/**
	 * 
	 * @Title: insertSupplier 
	 * @Description: TODO(添加供应商) 
	 * @param @param supplier
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@InsertProvider(type = DynaSqlProvider.class, method = "insertSupplier")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertSupplier(Supplier supplier);

	/**
	 * 
	 * @Title: getSupplierByTypeId 
	 * @Description: TODO(根据供应商类别id查询信息) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return List<Supplier>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier where type_id=#{typeId} limit ${offset},${size}")
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	List<Supplier> getSupplierByTypeId(Map<String, Object> query);

	/**
	 * 
	 * @Title: getSupplierBySName 
	 * @Description: TODO(根据简称查询供应商) 
	 * @param @param shortName
	 * @param @return    设定文件 
	 * @return Supplier    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier where short_name=#{shortName}")
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	Supplier getSupplierBySName(String shortName);
	
	/***
	 * 
	 * @Title: getSupByConName 
	 * @Description: TODO(根据联系人的名称查询) 
	 * @param @param contactName
	 * @param @return    设定文件 
	 * @return List<Supplier>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier where contact_name=#{contactName} order by id limit ${offset},${size}")
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	List<Supplier> getSupByConName(Map<String, Object> query);

	/**
	 * 
	 * @Title: getSupByConMob 
	 * @Description: TODO(根据联系人手机查询) 
	 * @param @param contactMobile
	 * @param @return    设定文件 
	 * @return Supplier    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier where contact_mobile=#{contactMobile}")
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	Supplier getSupByConMob(String contactMobile);

	/**
	 * 
	 * @Title: getSupByConNum 
	 * @Description: TODO(根据联系人座机查询) 
	 * @param @param contactNumber
	 * @param @return    设定文件 
	 * @return Supplier    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier where contact_number=#{contactNumber}")
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	Supplier getSupByConNum(String contactNumber);

	/**
	 * 
	 * @Title: getSupByFax 
	 * @Description: TODO(根据fax查询) 
	 * @param @param fax
	 * @param @return    设定文件 
	 * @return List<Supplier>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier where fax=#{fax}")
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	Supplier getSupByFax(String fax);

	/**
	 * 
	 * @Title: getSupByPos 
	 * @Description: TODO(根据邮政编码查询) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return List<Supplier>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier where postcode=#{postcode} limit ${offset},${size}")
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	List<Supplier> getSupByPos(Map<String, Object> query);

	@Select("select * from tbl_supplier where address=#{address}")
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	Supplier getSupByAdd(String address);

	/**
	 * 
	 * @Title: getSupplier 
	 * @Description: TODO(获取所有供应商信息) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return List<Supplier>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier order by id limit ${offset},${size}")
	@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSupplierTypeById") )
	List<Supplier> getSupplier(Map<String, Object> query);

	@Select("select count(*) from tbl_supplier where type_id = #{id}")
	int getRowCountByTypeId(Integer id);
	
	@Select("select count(*) from tbl_supplier where contact_name = #{contactName}")
	int getRowCountByConName(String contactName);

	@Select("select count(*) from tbl_supplier where postcode = #{postcode}")
	int getRowCountByPos(String postcode);
	
	@Select("select count(*) from tbl_supplier")
	int getAllRowCount();
}
