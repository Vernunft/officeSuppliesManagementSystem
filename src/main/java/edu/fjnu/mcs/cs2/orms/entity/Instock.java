package edu.fjnu.mcs.cs2.orms.entity;

import java.util.Date;
import java.util.List;

import edu.fjnu.mcs.cs2.orms.type.InstockType;

public class Instock {
	private Integer id;

	private Employee make;

	private Employee attn;

	private Supplier supplier;

	private InstockType type;

	private Date date;

	private String remark;

	private Integer totalCount;

	private Float totalPrice;

	private List<SpecificRes> specificReses;

	public InstockType getType() {
		return type;
	}

	public void setType(InstockType type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public List<SpecificRes> getSpecificRes() {
		return specificReses;
	}

	public void setSpecificRes(List<SpecificRes> specificRes) {
		this.specificReses = specificRes;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getMake() {
		return make;
	}

	public void setMake(Employee make) {
		this.make = make;
	}

	public Employee getAttn() {
		return attn;
	}

	public void setAttn(Employee attn) {
		this.attn = attn;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date ate) {
		this.date = ate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Instock() {
	}

}