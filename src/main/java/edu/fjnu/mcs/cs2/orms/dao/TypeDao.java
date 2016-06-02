package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.type.Category;
import edu.fjnu.mcs.cs2.orms.type.Department;
import edu.fjnu.mcs.cs2.orms.type.Education;
import edu.fjnu.mcs.cs2.orms.type.EmpWorkStatus;
import edu.fjnu.mcs.cs2.orms.type.InstockType;
import edu.fjnu.mcs.cs2.orms.type.OutstockType;
import edu.fjnu.mcs.cs2.orms.type.SpecificResStatus;
import edu.fjnu.mcs.cs2.orms.type.SupplierStatus;
import edu.fjnu.mcs.cs2.orms.type.SupplierType;
import edu.fjnu.mcs.cs2.orms.type.Unit;

@Repository
public interface TypeDao {
	/**
	 * 获取所有类别的记录数
	 * @return
	 */
	@Select("select count(*) from tbl_all_kind")
	int getRowCount();

	/**
	 * 获取类别编号和名称
	 * @param offset
	 * @param size
	 * @return
	 */
	@Select("select * from tbl_all_kind where type= #{type} order by id limit ${offset},${size}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<Category> getAllCategoryInfo(Map<String,Object> map);
	
	/**
	 * 获取某物品类别的子类
	 * @param typeId
	 * @return
	 */
	@Select("select * from tbl_all_kind where parent=#{typeId}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<Category> getChildByParId(Integer typeId);
	
	/**
	 * 获取某部门类别的子类
	 * @param typeId
	 * @return
	 */
	@Select("select * from tbl_all_kind where parent=#{typeId}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<Department> getChildByDepId(Integer typeId);
	

	/**
	 * 待定
	 * 根据物品类别id查询物品信息 
	 * @Title: getCategoryInfoById 
	 * @Description: 根据物品类别id查询物品信息 
	 * @param @param categoryId
	 * @param @return    设定文件 
	 * @return List<Category>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where id=#{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	Category getTypeInfoById(Integer id);

	/**
	 * 
	 * @Title: getCategoryInfo 
	 * @Description: 返回所有的物品类别信息
	 * @param @param map
	 * @param @return    设定文件 
	 * @return List<Category>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where type=#{type} limit ${offset},${size}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<Category> getCategoryInfo(Map<String, Object> map);
	
	@Select("select * from tbl_all_kind where id=#{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	Category getCategoryInfoById(Integer id);

	/**
	 * 
	 * @Title: getRowCountByType 
	 * @Description: TODO(查询类别的记录数) 
	 * @param @param type
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@Select("select count(*) from tbl_all_kind where type=#{type}")
	int getRowCountByType(Integer type);

	/**
	 * 
	 * @Title: addCategory 
	 * @Description: TODO(增加入库类别) 
	 * @param @param type
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@InsertProvider(type = DynaSqlProvider.class,  
            method = "insertInstockType")  
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	int insertInstockType(InstockType type);

	/**
	 * 
	 * @Title: deleteCategory 
	 * @Description: TODO(根据id删除类别)
	 * @param @param id
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@Delete("delete from tbl_all_kind where id = #{id}")
	int deleteCategory(Integer id);

	/**
	 * 
	 * @Title: getInfoByType 
	 * @Description: TODO(查询入库类型) 
	 * @param @param type
	 * @param @return    设定文件 
	 * @return InstockType    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where type=#{type}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<InstockType> getInfoByType(Integer type);
	
	/**
	 * 
	 * @Title: getOutInfoByType 
	 * @Description: TODO(查询出库类型) 
	 * @param @param type
	 * @param @return    设定文件 
	 * @return List<OutstockType>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where type=#{type}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<OutstockType> getOutInfoByType(Integer type);


	/**
	 * 
	 * @Title: getTypeByName 
	 * @Description: TODO(根据类型名称查询id) 
	 * @param @param name
	 * @param @return    设定文件 
	 * @return Integer    返回类型 
	 * @throws
	 */
	@Select("select id from tbl_all_kind where name=#{name} and type=#{type}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	Integer getTypeByName(Map<String, Object> map);

	/**
	 * 
	 * @Title: geInstockTypeById 
	 * @Description: TODO(根据id查询入库类型信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return InstockType    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where id=#{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	InstockType geInstockTypeById(Integer id);
	
	/**
	 * 
	 * @Title: getSupplierTypeById 
	 * @Description: TODO(根据id查询供应商信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return Supplier    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where id=#{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	SupplierType getSupplierTypeById(Integer id);
	
	/**
	 * 
	 * @Title: getOutstockTypeById 
	 * @Description: TODO(根据id查询出库类型) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return OutstockType    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where id=#{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	OutstockType getOutstockTypeById(Integer id);
	
	/**
	 * 
	 * @Title: getUnitById 
	 * @Description: TODO(根据id查询物品单位) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return Unit    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where id=#{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	Unit getUnitById(Integer id);
	/**
	 * 
	 * @Title: getStatusById 
	 * @Description: TODO(根据id查询供应商供货状态) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return Status    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where id=#{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	SupplierStatus getStatusById(Integer id);
	
	@Select("select * from tbl_all_kind where id=#{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	Education getEducationById(Integer id);
	
	@Select("select * from tbl_all_kind where id=#{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	EmpWorkStatus getEmpWorkStatusById(Integer id);
	

	/**
	 * 
	 * @Title: insertCategory 
	 * @Description: TODO(增加物品类别) 
	 * @param @param category
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@InsertProvider(type = DynaSqlProvider.class,  
            method = "insertCategory")  
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	int insertCategory(Category category);

	/**
	 * 
	 * @Title: insertOutstockType 
	 * @Description: TODO(添加出库类型) 
	 * @param @param outstockType
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@InsertProvider(type = DynaSqlProvider.class,  
            method = "insertOutstockType")  
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	int insertOutstockType(OutstockType outstockType);

	/**
	 * 
	 * @Title: insertSupplierType 
	 * @Description: TODO(添加供应商类别) 
	 * @param @param supplierType
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@InsertProvider(type = DynaSqlProvider.class,  
            method = "insertSupplierType")  
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertSupplierType(SupplierType supplierType);

	/**
	 * 
	 * @Title: getSupInfoByType 
	 * @Description: TODO(查询供应商) 
	 * @param @param type
	 * @param @return    设定文件 
	 * @return List<SupplierType>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where type=#{type}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<SupplierType> getSupInfoByType(Integer type);

	@Select("select * from tbl_all_kind where name = #{name}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	Department getDepartmentByName(String name);

	@InsertProvider(type = DynaSqlProvider.class,  
            method = "insertDepartment")  
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertDepartment(Department department);

	/**
	 * 
	 * @Title: getDepartmentByType 
	 * @Description: TODO(查询部门信息) 
	 * @param @param type
	 * @param @return    设定文件 
	 * @return List<Department>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where type=#{type}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<Department> getDepartmentByType(Integer type);
	
	@Select("select * from tbl_all_kind where id= #{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getDepartmentById"))
	Department getDepartmentById(Integer id);
	
	@Select("select * from tbl_all_kind where id= #{id}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getSpecificResStatusById"))
	SpecificResStatus getSpecificResStatusById(Integer id);
	
	@Select("select * from tbl_all_kind where type=#{type} order by id limit ${offset},${size}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<Department> getDepartmentByTypePage(Map<String, Object> query);

	@InsertProvider(type = DynaSqlProvider.class,  
            method = "inertEmpWorkStatus")  
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int inertEmpWorkStatus(EmpWorkStatus empWorkStatus);

	@Select("select * from tbl_all_kind where type=#{type}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	List<EmpWorkStatus> getWorkStatus(Integer type);

	@Select("select * from tbl_all_kind where name=#{name}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	InstockType getInstockTypeByName(String name);

	@Select("select * from tbl_all_kind where name=#{name}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	Category getCategoryByName(String name);

	@Select("select * from tbl_all_kind where name=#{name}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	OutstockType getOutstockTypeByName(String name);

	@Select("select * from tbl_all_kind where name=#{name}")
	@Result(property = "parent",column= "parent",one = @One(select="edu.fjnu.mcs.cs2.orms.dao.TypeDao.getTypeInfoById"))
	Supplier getSUpplierTypeByName(String name);

	@Select("select * from tbl_all_kind where name=#{name}")
	EmpWorkStatus getEmpWorkStatusByName(String name);

	@Select("select * from tbl_all_kind where name=#{name}")
	Unit getUnitByName(String name);

	@InsertProvider(type = DynaSqlProvider.class,  
            method = "insertUnit")  
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	int insertUnit(Unit unit);
}
