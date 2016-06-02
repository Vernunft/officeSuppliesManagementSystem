package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.PurchaseRes;

/**   
 * @Title: PurchaseResDao.java 
 * @Package edu.fjnu.mcs.cs2.orms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月28日 下午9:26:00 
 * @version V1.0   
 */
@Repository
public interface PurchaseResDao {

	@InsertProvider(type=DynaSqlProvider.class,method="insertPurRes")
	int insertPurRes(PurchaseRes purchaseRes);

	@Select("select * from tbl_purchase_res where purchase_id = #{id}")
	@Results({
		@Result(property="res",column="res_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.ResDao.getResById")),
		@Result(property="purchase",column="purchase_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.PurchaseDao.getPurchaseById"))
	})
	List<PurchaseRes> getPurchaseResByPId(Integer id);

	@Select("select * from tbl_purchase_res where res_id = #{id}")
	@Results({
		@Result(property="res",column="res_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.ResDao.getResById")),
		@Result(property="purchase",column="purchase_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.PurchaseDao.getPurchaseById"))
	})
	List<PurchaseRes> getPurchaseResByResId(Integer id);

}
