package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.type.Category;

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
	 * 根据物品类别id查询物品信息 
	 * @Title: getCategoryInfoById 
	 * @Description: 根据物品类别id查询物品信息 
	 * @param @param categoryId
	 * @param @return    设定文件 
	 * @return List<Category>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_all_kind where id=#{id}")
	List<Category> getCategoryInfoById(Integer id);

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
	 * @Description: TODO(添加物品类别) 
	 * @param @param query
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@Insert("INSERT INTO tbl_all_kind (id, parent, TYPE, NAME) VALUES(#{id},#{parent},#{type},#{name})")
	int addCategory(Map<String, Object> query);

	/**
	 * 
	 * @Title: deleteCategory 
	 * @Description: TODO(根据id删除物品类别) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@Delete("delete from tbl_all_kind where id = #{id}")
	int deleteCategory(Integer id);


}
