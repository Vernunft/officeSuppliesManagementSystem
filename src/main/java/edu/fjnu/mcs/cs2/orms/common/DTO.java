package edu.fjnu.mcs.cs2.orms.common;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;

import edu.fjnu.mcs.cs2.orms.entity.Employee;
import edu.fjnu.mcs.cs2.orms.entity.Instock;
import edu.fjnu.mcs.cs2.orms.entity.Outstock;
import edu.fjnu.mcs.cs2.orms.entity.Purchase;
import edu.fjnu.mcs.cs2.orms.entity.Res;
import edu.fjnu.mcs.cs2.orms.entity.Supplier;
import edu.fjnu.mcs.cs2.orms.entity.SupplierSupplies;
import edu.fjnu.mcs.cs2.orms.type.Category;
import edu.fjnu.mcs.cs2.orms.type.Department;
import edu.fjnu.mcs.cs2.orms.type.EmpWorkStatus;
import edu.fjnu.mcs.cs2.orms.type.InstockType;
import edu.fjnu.mcs.cs2.orms.type.OutstockType;
import edu.fjnu.mcs.cs2.orms.type.SupplierType;

/**
 * 
 * @ClassName: DTO 
 * @Description: 数据传输对象
 * @author lbb
 * @date 2016年5月21日 下午3:25:27
 */
public class DTO {
	private Integer currentPage = 1;
	private Integer size = 10;
	private Map<String, Object> map;
	private InstockType instockType;
	private Category category;
	private List<InstockType> instockTypes;
	private List<Category> categories;
	private Instock instock;
	private Outstock outstock;
	private OutstockType outstockType;
	private SupplierType supplierType;
	private Supplier supplier;
	private SupplierSupplies supplierSupplies;
	private Department department;
	private Employee make;
	private Employee attn ;
	private EmpWorkStatus empWorkStatus;
	private Res res;
	private Purchase purchase;
	private Employee employee ;
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Employee getAttn() {
		return attn;
	}
	public void setAttn(Employee attn) {
		this.attn = attn;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public Res getRes() {
		return res;
	}
	public void setRes(Res res) {
		this.res = res;
	}
	public EmpWorkStatus getEmpWorkStatus() {
		return empWorkStatus;
	}
	public void setEmpWorkStatus(EmpWorkStatus empWorkStatus) {
		this.empWorkStatus = empWorkStatus;
	}
	public Employee getMake() {
		return make;
	}
	public void setMake(Employee employee) {
		this.make = employee;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public SupplierSupplies getSupplierSupplies() {
		return supplierSupplies;
	}
	public void setSupplierSupplies(SupplierSupplies supplierSupplies) {
		this.supplierSupplies = supplierSupplies;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public SupplierType getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(SupplierType supplierType) {
		this.supplierType = supplierType;
	}
	public Outstock getOutstock() {
		return outstock;
	}
	public void setOutstock(Outstock outstock) {
		this.outstock = outstock;
	}
	public OutstockType getOutstockType() {
		return outstockType;
	}
	public void setOutstockType(OutstockType outstockType) {
		this.outstockType = outstockType;
	}
	public Instock getInstock() {
		return instock;
	}
	public void setInstock(Instock instock) {
		this.instock = instock;
	}
	public List<InstockType> getInstockTypes() {
		return instockTypes;
	}
	public void setInstockTypes(List<InstockType> instockTypes) {
		this.instockTypes = instockTypes;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public InstockType getInstockType() {
		return instockType;
	}
	public void setInstockType(InstockType instockType) {
		this.instockType = instockType;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
}
