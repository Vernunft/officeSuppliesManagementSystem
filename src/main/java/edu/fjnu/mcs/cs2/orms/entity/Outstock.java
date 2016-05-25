package edu.fjnu.mcs.cs2.orms.entity;

import java.util.Date;
import java.util.List;

import edu.fjnu.mcs.cs2.orms.type.Department;
import edu.fjnu.mcs.cs2.orms.type.OutstockType;

public class Outstock {
	private Integer id;

	private Employee make;
	
	private Department department;

	private Employee reciEmp;
	
	private OutstockType type;

	private Date date;

	private String remark;

	private Integer totalCount;

	private Float totalPrice;

	private List<SpecificRes> specificReses;
	
	public List<SpecificRes> getSpecificReses() {
		return specificReses;
	}

	public void setSpecificReses(List<SpecificRes> specificReses) {
		this.specificReses = specificReses;
	}

	public Employee getMake() {
		return make;
	}

	public void setMake(Employee make) {
		this.make = make;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return reciEmp;
	}

	public void setEmployee(Employee employee) {
		this.reciEmp = employee;
	}

	public OutstockType getType() {
		return type;
	}

	public void setType(OutstockType type) {
		this.type = type;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String reserve) {
		this.remark = reserve == null ? null : reserve.trim();
	}
}