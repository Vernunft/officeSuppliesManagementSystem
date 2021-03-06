package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.Outstock;

/**   
 * @Title: OutstockDao.java 
 * @Package edu.fjnu.mcs.cs2.orms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月25日 上午12:24:32 
 * @version V1.0   
 */
@Repository
public interface OutstockDao {

	/**
	 * 
	 * @Title: getOutstockById 
	 * @Description: TODO(根据出库id获取出库信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return Outstock    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock where id =#{id}")
	Outstock getOutstockById(Integer id);

	/**
	 * 
	 * @Title: insertOutstock 
	 * @Description: TODO(添加出库信息) 
	 * @param @param outstock
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@InsertProvider(type = DynaSqlProvider.class, method = "insertOutstock")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertOutstock(Outstock outstock);

	/**
	 * 
	 * @Title: getOutstockInfoByTypeId 
	 * @Description: TODO(根据出库类型id获取出库信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return List<Outstock>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock where type_id=#{typeId} order by date limit ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "reciDept", column = "recipient_department_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getOutstockTypeById") ),
		@Result(property = "reciEmp", column = "recipient_employee_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ) })
	List<Outstock> getOutstockInfoByTypeId(Map<String, Object> query);

	/**
	 * 
	 * @Title: getOutstockInfoByReEmId 
	 * @Description: TODO(根据领用人id查询出库信息) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return List<Outstock>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock where recipient_employee_id=#{id} order by id limit ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "reciDept", column = "recipient_department_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getOutstockTypeById") ),
		@Result(property = "reciEmp", column = "recipient_employee_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ) })
	List<Outstock> getOutstockInfoByReEmId(Map<String, Object> query);

	/**
	 * 
	 * @Title: getOutstockInfoByReDeId 
	 * @Description: TODO(根据出库领用部门id查询出库信息) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return List<Outstock>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock where recipient_department_id=#{id} order by id limit ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "reciDept", column = "recipient_department_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getOutstockTypeById") ),
		@Result(property = "reciEmp", column = "recipient_employee_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ) })
	List<Outstock> getOutstockInfoByReDeId(Map<String, Object> query);
	
	/**
	 * 
	 * @Title: getOutstockInfoByMakeId 
	 * @Description: TODO(根据制单人id获取出库信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return List<Outstock>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock where make_id=#{id} order by id limit ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "reciDept", column = "recipient_department_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getOutstockTypeById") ),
		@Result(property = "reciEmp", column = "recipient_employee_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ) })
	List<Outstock> getOutstockInfoByMakeId(Map<String, Object> query);

	/**
	 * 
	 * @Title: getOutstockInfoByTime 
	 * @Description: TODO(根据出库时间段查询出库信息) 
	 * @param @param map
	 * @param @return    设定文件 
	 * @return List<Outstock>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock where date between #{beginTime} and #{endTime} order by id limit ${offset},${size} ")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "reciDept", column = "recipient_department_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getOutstockTypeById") ),
		@Result(property = "reciEmp", column = "recipient_employee_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ) })
	List<Outstock> getOutstockInfoByTime(Map<String, Object> map);

	/**
	 * 
	 * @Title: getOutstockInfoById 
	 * @Description: TODO(根据出库类型id查询出库信息) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return List<Outstock>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock where id=#{id} ")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "reciDept", column = "recipient_department_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getEmpInfoById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getOutstockTypeById") ),
		@Result(property = "reciEmp", column = "recipient_employee_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ) })
	Outstock getOutstockInfoById(Integer id);

	/**
	 * 
	 * @Title: getOutstockInfo 
	 * @Description: TODO(获取所有出库类型信息) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return List<Outstock>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock order by date limit ${offset},${size}")
	@Results({
		@Result(property = "make", column = "make_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ),
		@Result(property = "reciDept", column = "recipient_department_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getDepartmentById") ),
		@Result(property = "type", column = "type_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getOutstockTypeById") ),
		@Result(property = "reciEmp", column = "recipient_employee_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById") ) })
	List<Outstock> getOutstockInfo(Map<String, Object> query);

	@UpdateProvider(type=DynaSqlProvider.class,method="updateOutstock")
	int updateOutstock(Outstock outstock);

	@Select("select count(*) from tbl_outstock where type_id = #{id} ")
	int getRowCountByTypeId(Integer id);

	@Select("select count(*) from tbl_outstock where recipient_employee_id = #{id} ")
	int getrowCountByReciEmp(Integer id);

	@Select("select count(*) from tbl_outstock where recipient_department_id = #{id} ")
	int getRowCountByDeId(Integer id);

	@Select("select count(*) from tbl_outstock where make_id = #{id} ")
	int getRowCountByMakeId(Integer id);
	
	@Select("select count(*) from tbl_outstock where date between #{beginTime} and #{endTime}")
	int getRowCountByTime(Map<String, Object> map);

	@Select("select count(*) from tbl_outstock")
	int getAllRowCount(Map<String, Object> query);
}
