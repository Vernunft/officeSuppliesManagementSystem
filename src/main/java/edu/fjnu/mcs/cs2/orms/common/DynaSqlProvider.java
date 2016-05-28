package edu.fjnu.mcs.cs2.orms.common;

import org.apache.ibatis.jdbc.SQL;

import edu.fjnu.mcs.cs2.orms.entity.InstockSpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Instock;
import edu.fjnu.mcs.cs2.orms.entity.Outstock;
import edu.fjnu.mcs.cs2.orms.entity.SpecificRes;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.entity.SupplierSupplies;
import edu.fjnu.mcs.cs2.orms.entity.Type;
import edu.fjnu.mcs.cs2.orms.type.InstockType;

/**
 * @Title: DynaSqlProvider.java
 * @Package edu.fjnu.mcs.cs2.orms.common
 * @Description: TODO(用一句话描述该文件做什么)
 * @author lbb
 * @date 2016年5月22日 下午7:30:24
 * @version V1.0
 */
public class DynaSqlProvider {
	public String insertType(final Type type) {
		return new SQL() {
			{
				INSERT_INTO("tbl_all_kind");
				if (type.getName() != null) {
					VALUES("NAME", "#{name}");
				}
				if (type.getParent() != null) {
					VALUES("parent", "#{parent}");
				}
				if (type.getId() != null) {
					VALUES("id", "#{id}");
				}
				if (type.type != null) {
					VALUES("type", "#{type}");
				}
			}
		}.toString();
	}

	public String insertInstock(final Instock instock) {
		return new SQL() {
			{
				INSERT_INTO("tbl_instock");
				if (instock.getAttn() != null) {
					VALUES("attn_id", "#{attn.id}");
				}
				if (instock.getDate() != null) {
					VALUES("date", "#{date}");
				}
				if (instock.getId() != null) {
					VALUES("id", "#{id}");
				}
				if (instock.getMake() != null) {
					VALUES("make_id", "#{make.id}");
				}
				if (instock.getRemark() != null) {
					VALUES("remark", "#{remark}");
				}
				if (instock.getSupplier() != null) {
					VALUES("suppllier_id", "#{supplier.id}");
				}
				if (instock.getTotalCount() != null) {
					VALUES("total_count", "#{totalCount}");
				}
				if (instock.getTotalPrice() != null) {
					VALUES("total_price", "#{totalPrice}");
				}
				if (instock.getType() != null) {
					VALUES("type_id", "#{type.id}");
				}

			}
		}.toString();
	}

	public String insertSpecificRes(final SpecificRes specificRes) {
		return new SQL() {
			{
				INSERT_INTO("tbl_specific_res");
				if (specificRes.getBuyPrice() != null) {
					VALUES("buy_price", "#{buyPrice}");
				}
				if (specificRes.getRes() != null) {
					VALUES("res_id", "#{res.id}");
				}
				if (specificRes.getId() != null) {
					VALUES("id", "#{id}");
				}
				if (specificRes.getType() != null) {
					VALUES("type_id", "#{type.id}");
				}
			}
		}.toString();
	}

	public String insertInRecord(final InstockSpecificRes instockSpecificRes) {
		return new SQL() {
			{
				INSERT_INTO("tbl_instock_specific_res");
				if (instockSpecificRes.getInstock() != null) {
					VALUES("instock_id", "#{instock.id}");
				}
				if (instockSpecificRes.getSpecificRes() != null) {
					VALUES("specific_res_id", "#{specificRes.id}");
				}
			}
		}.toString();
	}

	public String insertOutstock(final Outstock outstock) {
		return new SQL() {
			{
				INSERT_INTO("tbl_outstock");
				if (outstock.getDepartment() != null) {
					VALUES("recipient_department_id", "#{department.id}");
				}
				if (outstock.getDate() != null) {
					VALUES("date", "#{date}");
				}
				if (outstock.getId() != null) {
					VALUES("id", "#{id}");
				}
				if (outstock.getMake() != null) {
					VALUES("make_id", "#{make.id}");
				}
				if (outstock.getRemark() != null) {
					VALUES("remark", "#{remark}");
				}
				if (outstock.getEmployee() != null) {
					VALUES("recipient_employee_id", "#{employee.id}");
				}
				if (outstock.getTotalCount() != null) {
					VALUES("total_count", "#{totalCount}");
				}
				if (outstock.getTotalPrice() != null) {
					VALUES("total_price", "#{totalPrice}");
				}
				if (outstock.getType() != null) {
					VALUES("type_id", "#{type.id}");
				}

			}
		}.toString();
	}

	public String insertSupplier(final Supplier supplier) {
		return new SQL() {
			{
				INSERT_INTO("tbl_supplier");
				if (supplier.getAddress() != null) {
					VALUES("address", "#{address}");
				}
				if (supplier.getContactMobile() != null) {
					VALUES("contact_mobile", "#{ContactMobile}");
				}
				if (supplier.getId() != null) {
					VALUES("id", "#{id}");
				}
				if (supplier.getContactName() != null) {
					VALUES("contact_name", "#{contactName}");
				}
				if (supplier.getRemark() != null) {
					VALUES("remark", "#{remark}");
				}
				if (supplier.getContactNumber() != null) {
					VALUES("contact_number", "#{contactNumber}");
				}
				if (supplier.getFax() != null) {
					VALUES("fax", "#{fax}");
				}
				if (supplier.getName() != null) {
					VALUES("name", "#{name}");
				}
				if (supplier.getType() != null) {
					VALUES("type_id", "#{type.id}");
				}
				if (supplier.getPostcode() != null) {
					VALUES("postcode", "#{postcode}");
				}
				if (supplier.getShortName() != null) {
					VALUES("short_name", "#{shortName}");
				}
			}
		}.toString();
	}

	public String updateSpecificRes(final SpecificRes specificRes) {
		return new SQL() {
			{
				UPDATE("tbl_specific_res");
				if (specificRes.getBuyPrice() != null) {
					SET("buy_price = #{buyPrice}");
				}
				if (specificRes.getRes() != null) {
					SET("res_id = #{res.id}");
				}
				if (specificRes.getType() != null) {
					SET("type_id = #{type.id}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
	}

	public String updatePriceById(final SupplierSupplies supplierSupplies) {
		return new SQL() {
			{
				UPDATE("tbl_supplier_supplies");
				if (supplierSupplies.getPrice() != null) {
					SET("price = #{price}");
				}
				if (supplierSupplies.getRes() != null) {
					SET("res_id = #{res.id}");
				}
				if (supplierSupplies.getRemark() != null) {
					SET("remark = #{remark}");
				}
				if (supplierSupplies.getStatus() != null) {
					SET("status_id = #{status.id}");
				}
				if (supplierSupplies.getSupplier() != null) {
					SET("supplier_id = #{supplier.id}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
	}

	public String insertInfo(final SupplierSupplies supplierSupplies) {
		return new SQL() {
			{
				INSERT_INTO("tbl_supplier_supplies");
				if (supplierSupplies.getId() != null) {
					VALUES("id", "#{id}");
				}
				if (supplierSupplies.getPrice() != null) {
					VALUES("price", "#{price}");
				}
				if (supplierSupplies.getRemark() != null) {
					VALUES("remark", "#{remark}");
				}
				if (supplierSupplies.getRes() != null) {
					VALUES("res_id", "#{res.id}");
				}
				if (supplierSupplies.getStatus() != null) {
					VALUES("status_id", "#{status.id}");
				}
				if (supplierSupplies.getSupplier() != null) {
					VALUES("supplier_id", "#{supplier.id}");
				}
			}
		}.toString();
	}

}
