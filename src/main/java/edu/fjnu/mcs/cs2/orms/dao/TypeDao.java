package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.type.Category;
import edu.fjnu.mcs.cs2.orms.type.Department;
import edu.fjnu.mcs.cs2.orms.type.InstockType;
import edu.fjnu.mcs.cs2.orms.type.OutstockType;

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
	List<Category> getAllCategoryInfo(Map<String,Object> map);
	
	/**
	 * 获取某类别的子类
	 * @param typeId
	 * @return
	 */
	@Select("select * from tbl_all_kind where parent=#{type_id}")
	List<Category> getChildByParId(Integer typeId);

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
	Category getCategoryInfoById(Integer id);

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
	List<Category> getCategoryInfo(Map<String, Object> map);

	/**
	 * 
	 * @Title: getRowCountByType 
	 * @Description: TODO(查询物品类别的记录数) 
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
            method = "insertType")  
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
	InstockType geInstockTypeById(Integer id);

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
            method = "insertType")  
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
            method = "insertType")  
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	int insertOutstockType(OutstockType outstockType);

	/**
	 * 
	 * @Title: getDepInfoByName 
	 * @Description: TODO(根据部门id查询部门信息) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return Department    返回类型 
	 * @throws
	 */
}
