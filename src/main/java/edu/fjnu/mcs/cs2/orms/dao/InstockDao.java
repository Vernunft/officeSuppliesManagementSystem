package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.Instock;

/**
 * @Title: InstockDao.java
 * @Package edu.fjnu.mcs.cs2.orms.dao
 * @Description: TODO(入库表的dao层接口)
 * @author lbb
 * @date 2016年5月23日 下午3:53:38
 * @version V1.0
 */
@Repository
public interface InstockDao {

	/**
	 * 
	 * @Title: insertInstock @Description: TODO(添加入库信息) @param @param
	 *         instock @param @return 设定文件 @return int 返回类型 @throws
	 */
	@InsertProvider(type = DynaSqlProvider.class, method = "insertInstock")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertInstock(Instock instock);

	/**
	 * 
	 * @Title: getInstockInfoById @Description:
	 *         TODO(通过入库类型编号查询入库信息) @param @param typeId @param @return
	 *         设定文件 @return Instock 返回类型 @throws
	 */
	
	@Select("select * from tbl_instock where type_id=#{id} order by date  ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "attn", column = "attn_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.geInstockTypeById") ),
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ) })
	List<Instock> getInstockInfoByTypeId(Map<String, Object> query);

	/**
	 * 
	 * @Title: getInstockInfoBySupId @Description:
	 *         TODO(根据供应商id获取入库信息) @param @param suppllierId @param @return
	 *         设定文件 @return Instock 返回类型 @throws
	 */
	@Select("select * from tbl_instock where suppllier_id=#{suppllierId} order by date  ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "attn", column = "attn_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.geInstockTypeById") ),
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ) })
	List<Instock> getInstockInfoBySupId(Map<String, Object> query);

	/**
	 * 
	 * @Title: getInstockInfoByAttnId @Description:
	 *         TODO(根据经办人查询入库信息) @param @param id @param @return 设定文件 @return
	 *         List<Instock> 返回类型 @throws
	 */
	@Select("select * from tbl_instock where attn_id=#{attnId} order by date  ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "attn", column = "attn_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.geInstockTypeById") ),
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ) })
	List<Instock> getInstockInfoByAttnId(Map<String, Object> query);

	/**
	 * 
	 * @Title: getInstockInfoById @Description: TODO(根据入库id查询入库信息) @param @param
	 *         id @param @return 设定文件 @return List<Instock> 返回类型 @throws
	 */
	@Select("select * from tbl_instock where id =#{id}")
	@Results({
			@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
			@Result(property = "attn", column = "attn_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
			@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.geInstockTypeById") ),
			@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ) })
	List<Instock> getInstockInfoById(Integer id);

	/**
	 * 
	 * @Title: getInstockInfoByTime @Description:
	 *         TODO(根据时间段查询入库信息) @param @param map @param @return 设定文件 @return
	 *         List<Instock> 返回类型 @throws
	 */
	@Select("select * from tbl_instock where date between #{beginTime} and #{endTime} order by date  ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "attn", column = "attn_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.geInstockTypeById") ),
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ) })
	List<Instock> getInstockInfoByTime(Map<String, Object> map);

	/**
	 * 
	 * @Title: getInstockInfo @Description: TODO(获取所有的入库信息) @param @param
	 *         query @param @return 设定文件 @return List<Instock> 返回类型 @throws
	 */
	@Select("select * from tbl_instock order by date  ${offset},${size}")
	@Results({
			@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
			@Result(property = "attn", column = "attn_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
			@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.geInstockTypeById") ),
			@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ) })
	List<Instock> getInstockInfo(Map<String, Object> query);

	/**
	 * 
	 * @Title: getInstockInfoByMakeId 
	 * @Description: TODO(根据经办人查询入库信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return List<Instock>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_instock where make_id=#{makeId} order by date  ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "attn", column = "attn_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.geInstockTypeById") ),
		@Result(property = "supplier", column = "supplier_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SupplierDao.getSupplierById") ) })
	List<Instock> getInstockInfoByMakeId(Map<String, Object> query);
}
