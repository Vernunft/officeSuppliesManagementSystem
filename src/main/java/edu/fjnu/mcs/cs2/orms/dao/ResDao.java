package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.entity.ResPO;

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
	List<ResPO> getAllCategoryInfo(int offset, int size);

	/**
	 * 查询某物品类别的详细信息
	 * @param categoryId
	 * @return
	 */
	@Select("select * from tbl_res where category_id=#{categoryId}")
	ResPO getCategotyInfoById(Integer categoryId);


	
	
}
