package edu.fjnu.mcs.cs2.orms.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.bag.TreeBag;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import edu.fjnu.mcs.cs2.orms.common.DynaSqlProvider;
import edu.fjnu.mcs.cs2.orms.entity.Purchase;

/**   
 * @Title: PurchaseDao.java 
 * @Package edu.fjnu.mcs.cs2.orms.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lbb
 * @date 2016年5月28日 下午9:25:51 
 * @version V1.0   
 */
@Repository
public interface PurchaseDao {

	@InsertProvider(type=DynaSqlProvider.class,method="insertPurchase")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int insertPurchase(Purchase purchase);

	@Select("select * from tbl_purchase where id = #{id}")
	@Result(property="pic",column="pic_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById"))
	Purchase getPurchaseById(Integer id);

	@Select("select * from tbl_purchase where pic_id = #{picId} order by id limit ${offset},${size}")
	@Result(property="pic",column="pic_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById"))
	List<Purchase> getPurchaseByPicId(Map<String, Object> query);

	@Select("select * from tbl_purchase where complete = #{complete} order by id limit ${offset},${size}")
	@Result(property="pic",column="pic_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById"))
	List<Purchase> getPurchaseByCom(Map<String, Object> query);

	@Select("select * from tbl_purchase order by id limit ${offset},${size}")
	@Result(property="pic",column="pic_id",one=@One(select="edu.fjnu.mcs.cs2.orms.dao.EmployeeDao.getEmpInfoById"))
	List<Purchase> getAllInfo(Map<String, Object> query);

	@Select("select count(*) from tbl_purchase where pic_id = #{id}")
	int getRowCountByPicId(Integer id);

	@Select("select count(*) from tbl_purchase where complete = #{complete}")
	int getRowCountByCom(Boolean complete);

	@Select("select count(*) from tbl_purchase")
	int getAllRowCoun();

}
