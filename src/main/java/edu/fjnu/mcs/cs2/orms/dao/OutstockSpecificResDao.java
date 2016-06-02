package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import edu.fjnu.mcs.cs2.orms.entity.InstockSpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.OutstockSpecificRes;

/**   
 * @Title: OutstockSpecificResDao.java 
 * @Package edu.fjnu.mcs.cs2.orms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月28日 下午12:44:04 
 * @version V1.0   
 */
public interface OutstockSpecificResDao {

	/**
	 * 
	 * @Title: getListByOutstockId 
	 * @Description: TODO(根据出库id获取出库信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return List<OutstockSpecificRes>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock_specific_res where outstock_id = #{id}")
	@Results({
		@Result(property = "specificRes", column = "specific_res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SpecificResDao.getSpecificResById") ),
		@Result(property = "outstock", column = "outstock_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.OutstockDao.getOutstockById") )})
	List<OutstockSpecificRes> getListByOutstockId(Integer id);

	/**
	 * 
	 * @Title: insertOutRecord 
	 * @Description: TODO(添加出库记录) 
	 * @param @param outstockSpecificRes
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@Insert("insert into tbl_outstock_specific_res(outstock_id,specific_res_id) values(#{outstock.id},#{specificRes.id})")
	int insertOutRecord(OutstockSpecificRes outstockSpecificRes);

	/**
	 * 
	 * @Title: getRecordBySpeId 
	 * @Description: TODO(根据具体商品id获取出库记录) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return OutstockSpecificRes    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_outstock_specific_res where specific_res_id = #{id}")
	@Results({
		@Result(property = "specificRes", column = "specific_res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SpecificResDao.getSpecificResById") ),
		@Result(property = "outstock", column = "outstock_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.OutstockDao.getOutstockById") )})
	OutstockSpecificRes getRecordBySpeId(Integer id);
}
