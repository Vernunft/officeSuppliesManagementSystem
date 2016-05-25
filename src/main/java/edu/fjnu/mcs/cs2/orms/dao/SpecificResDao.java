package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.SpecificRes;

/**   
 * @Title: SpecificResDao.java 
 * @Package edu.fjnu.mcs.cs2.orms.dao 
 * @Description: TODO(具体物品信息的dao层接口 ) 
 * @author lbb
 * @date 2016年5月23日 下午4:25:46 
 * @version V1.0   
 */
@Repository
public interface SpecificResDao {
	
	/**
	 * 
	 * @Title: insertSpecificRes 
	 * @Description: TODO(添加具体物品信息) 
	 * @param @param specificRes
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@InsertProvider(type=DynaSqlProvider.class,method="insertSpecificRes")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int insertSpecificRes(SpecificRes specificRes);

	/**
	 * 
	 * @Title: getSpecificResById 
	 * @Description: TODO(根据具体物品id获取具体物品id) 
	 * @param @param resId
	 * @param @return    设定文件 
	 * @return SpecificRes    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_specific_res where specific_res_id=#{resId}")
	SpecificRes getSpecificResById(Integer resId);

	/**
	 * 
	 * @Title: updateSpecificRes 
	 * @Description: TODO(更新具体物品信息) 
	 * @param @param specificRes
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@UpdateProvider(type=DynaSqlProvider.class,method="updateSpecificRes")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int updateSpecificRes(SpecificRes specificRes);
	
	
}
