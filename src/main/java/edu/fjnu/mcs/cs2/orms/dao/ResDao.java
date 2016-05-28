package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.entity.Res;

@Repository
public interface ResDao {
	
	/**
	 * 获取物品的所有类别记录
	 * @return
	 */
	@Select("select count(*) from tbl_res")
	int getRowCount();

	/**
	 * 获取物品类别信息
	 * @param offset
	 * @param size
	 * @return
	 */
	@Select("select * from tbl_res order by id limit ${offset},${size}")
	@Results({
		@Result(property = "unit", column = "unit_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getUnitById") ),
		@Result(property = "category", column = "category_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getCategoryInfoById") )})
	List<Res> getAllCategoryInfo(int offset, int size);

	/**
	 * 查询某物品类别的详细信息
	 * @param categoryId
	 * @return
	 */
	@Select("select * from tbl_res where category_id=#{categoryId}")
	@Results({
		@Result(property = "unit", column = "unit_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getUnitById") ),
		@Result(property = "category", column = "category_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getCategoryInfoById") )})
	Res getCategotyInfoById(Integer categoryId);

	/**
	 * 
	 * @Title: getResById 
	 * @Description: TODO(根据id查询商品信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return Res    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_res where id=#{id}")
	@Results({
		@Result(property = "unit", column = "unit_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getUnitById") ),
		@Result(property = "category", column = "category_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getCategoryInfoById") )})
	Res getResById(Integer id);

	/**
	 * 
	 * @Title: getResByName 
	 * @Description: TODO(根据物品名称查询物品信息) 
	 * @param @param name
	 * @param @return    设定文件 
	 * @return Integer    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_res where name=#{name}")
	@Results({
		@Result(property = "unit", column = "unit_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getUnitById") ),
		@Result(property = "category", column = "category_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.TypeDao.getCategoryInfoById") )})
	Res getResByName(String name);
	
}
