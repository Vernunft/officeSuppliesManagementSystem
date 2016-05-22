package edu.fjnu.mcs.cs2.orms.entity;

import java.util.Date;

public class OutstockPO {
	private Integer id;

	private Integer makeId;

	private Integer recipientDepartmentId;

	private Integer recipientEmployeeId;

	private Integer typeId;

	private Date date;

	private String reserve;

	private Integer totalCount;

	private Float totalPrice;

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

	public Integer getMakeId() {
		return makeId;
	}

	public void setMakeId(Integer makeId) {
		this.makeId = makeId;
	}

	public Integer getRecipientDepartmentId() {
		return recipientDepartmentId;
	}

	public void setRecipientDepartmentId(Integer recipientDepartmentId) {
		this.recipientDepartmentId = recipientDepartmentId;
	}

	public Integer getRecipientEmployeeId() {
		return recipientEmployeeId;
	}

	public void setRecipientEmployeeId(Integer recipientEmployeeId) {
		this.recipientEmployeeId = recipientEmployeeId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve == null ? null : reserve.trim();
	}
}