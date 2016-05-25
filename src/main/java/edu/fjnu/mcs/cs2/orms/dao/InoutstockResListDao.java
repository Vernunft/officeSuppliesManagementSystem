package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.InoutstockResList;

/**   
 * @Title: InoutctockResListDao.java 
 * @Package edu.fjnu.mcs.cs2.orms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月24日 下午1:31:22 
 * @version V1.0   
 */
@Repository
public interface InoutstockResListDao {

	/**
	 * 
	 * @Title: getListById 
	 * @Description: TODO(根据具体物品id查询出入库记录) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return InoutstockResList    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_inoutstock_res_list where specific_res_id=#{id}")
	@Results({
		@Result(property = "specificRes", column = "specific_res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SpecificResDao.getSpecificResById") ),
		@Result(property = "outstock", column = "outstock_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.OutstockDao.getOutstockById") ),
		@Result(property = "instock", column = "instock_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.InstockDao.getInstockInfoById") )})
	InoutstockResList getListById(Integer id);

	/**
	 * 
	 * @Title: getListByInstockId 
	 * @Description: TODO(根据入库编号查询入库记录) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return List<InoutstockResList>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_inoutstock_res_list where instock_id = #{id}")
	@Results({
		@Result(property = "specificRes", column = "specific_res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SpecificResDao.getSpecificResById") ),
		@Result(property = "outstock", column = "outstock_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.OutstockDao.getOutstockById") ),
		@Result(property = "instock", column = "instock_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.InstockDao.getInstockInfoById") )})
	List<InoutstockResList> getListByInstockId(Integer id);

	/**
	 * 
	 * @Title: insertInRecord 
	 * @Description: TODO(添加出入库记录) 
	 * @param @param inoutstockResList
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@InsertProvider(type=DynaSqlProvider.class,method="insertInRecord")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int insertInRecord(InoutstockResList inoutstockResList);

	/**
	 * 
	 * @Title: updateRecord 
	 * @Description: TODO(更新出入库记录) 
	 * @param @param inoutstockResList
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	@UpdateProvider(type=DynaSqlProvider.class,method="updateRecord")
	int updateRecord(InoutstockResList inoutstockResList);

	/**
	 * 
	 * @Title: getListByOutstockId 
	 * @Description: TODO(根据出库id获取出库信息) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return List<InoutstockResList>    返回类型 
	 * @throws
	 */
	@Select("select * from tbl_inoutstock_res_list where outstock_id = #{id}")
	@Results({
		@Result(property = "specificRes", column = "specific_res_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.SpecificResDao.getSpecificResById") ),
		@Result(property = "outstock", column = "outstock_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.OutstockDao.getOutstockById") ),
		@Result(property = "instock", column = "instock_id", one = @One(select = "edu.fjnu.mcs.cs2.orms.dao.InstockDao.getInstockInfoById") )})
	List<InoutstockResList> getListByOutstockId(Integer id);

}
