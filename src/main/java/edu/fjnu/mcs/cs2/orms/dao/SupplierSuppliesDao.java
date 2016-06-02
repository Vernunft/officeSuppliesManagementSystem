package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.SupplierSupplies;

/**   
 * @Title: SupplierSupplies.java 
 * @Package edu.fjnu.mcs.cs2.orms.dao 
 * @Description: TODO(供应商供应dao层) 
 * @author lbb
 * @date 2016年5月26日 下午10:07:44 
 * @version V1.0   
 */
@Repository
public interface SupplierSuppliesDao {

	/**
	 * 
	 * @Title: getSuplierIdByRes 
	 * @Description: TODO(根据物品id获取供应商供应信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return SupplierSupplies    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier_supplies where res_id=#{resId}")
	@Results({
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ),
		@Result(property = "status", column = "status_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getStatusById") ),
		@Result(property = "res", column = "res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.ResDao.getResById") ) })
	List<SupplierSupplies> getSuplierIdByRes(Integer resId);
	
	/**
	 * 
	 * @Title: getSuplierIdByRes 
	 * @Description: TODO(根据物品id获取供应商供应信息并分页) 
	 * @param @param map
	 * @param @return    设定文件 
	 * @return SupplierSupplies    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier_supplies where res_id=#{resId} order by id limit ${offset},${size}")
	@Results({
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ),
		@Result(property = "status", column = "status_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getStatusById") ),
		@Result(property = "res", column = "res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.ResDao.getResById") ) })
	List<SupplierSupplies> getSuplierIdByResPage(Map<String, Object> query);

	/**
	 * 
	 * @Title: getSupSupBySuId 
	 * @Description: TODO(根据供应商id获取供应信息并分页) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return List<SupplierSupplies>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier_supplies where supplier_id=#{id} order by id limit ${offset},${size}")
	@Results({
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ),
		@Result(property = "status", column = "status_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getStatusById") ),
		@Result(property = "res", column = "res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.ResDao.getResById") ) })
	List<SupplierSupplies> getSupSupBySuIdPage(Map<String, Object> query);

	/**
	 * 
	 * @Title: getSupSupBySuId 
	 * @Description: TODO(根据供应商id获取供应信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return List<SupplierSupplies>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier_supplies where supplier_id=#{id}")
	@Results({
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ),
		@Result(property = "status", column = "status_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getStatusById") ),
		@Result(property = "res", column = "res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.ResDao.getResById") ) })
	List<SupplierSupplies> getSupSupBySuId(Integer id);
	
	@InsertProvider(type=DynaSqlProvider.class,method="insertInfo")
	@Options(useGeneratedKeys = true,keyProperty="id")
	int insertInfo(SupplierSupplies supplierSupplies);
	
	/**
	 * 
	 * @Title: getAllInfo 
	 * @Description: TODO(获取所有供应商供应信息) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return List<SupplierSupplies>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier_supplies  order by id limit ${offset},${size}")
	@Results({
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ),
		@Result(property = "status", column = "status_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getStatusById") ),
		@Result(property = "res", column = "res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.ResDao.getResById") ) })
	List<SupplierSupplies> getAllInfo(Map<String, Object> query);

	/**
	 * 
	 * @Title: getInfoById 
	 * @Description: TODO(根据id查询供应商供应信息) 
	 * @param @param b
	 * @param @return    设定文件 
	 * @return SupplierSupplies    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_supplier_supplies where id =#{id}")
	@Results({
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ),
		@Result(property = "status", column = "status_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getStatusById") ),
		@Result(property = "res", column = "res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.ResDao.getResById") ) })
	SupplierSupplies getInfoById(Integer id);

	@UpdateProvider(type = DynaSqlProvider.class ,method = "updatePriceById")
	int updatePriceById(SupplierSupplies supplierSupplies);

	/**
	 * 
	 * @Title: deleteInfoById 
	 * @Description: TODO(根据id删除供应记录) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@Delete("delete from tbl_supplier_supplies where id = #{id}")
	int deleteInfoById(Integer id);

	@Select("select count(*) from tbl_supplier_supplies where supplier_id =#{id} ")
	int getRowCountBySuId(Integer id);

	@Select("select count(*) from tbl_supplier_supplies where res_id =#{id} ")
	int getRowCountByResId(Integer id);

	@Select("select count(*) from tbl_supplier_supplies")
	int getAllRowCount();
	
}
