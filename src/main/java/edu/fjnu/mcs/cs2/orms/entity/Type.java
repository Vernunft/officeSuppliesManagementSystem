package edu.fjnu.mcs.cs2.orms.entity;

public class Type {
    private Integer id;

    private Type parent;

    private String name;

    private String remark;
    
    public static Integer instockType = 6 ;
   	
    public static Integer empWorkStatus = 1 ;
    
    public static Integer category = 10;
    
    public static Integer department = 4;
    
    public static Integer outstockType = 7 ;
    
    public static Integer supplierType = 3;
    
    public static Integer supplierStatus = 9;
    
	public static Integer education = 2;
	
	public static Integer unit = 5;
	
	public static Integer specificResStatus = 8;

   
    public static Integer getInstockType() {
		return instockType;
	}

	public static void setInstockType(Integer instockType) {
		Type.instockType = instockType;
	}

	public static Integer getEmpWorkStatus() {
		return empWorkStatus;
	}

	public static void setEmpWorkStatus(Integer empWorkStatus) {
		Type.empWorkStatus = empWorkStatus;
	}

	public static Integer getCategory() {
		return category;
	}

	public static void setCategory(Integer category) {
		Type.category = category;
	}

	public static Integer getDepartment() {
		return department;
	}

	public static void setDepartment(Integer department) {
		Type.department = department;
	}

	public static Integer getOutstockType() {
		return outstockType;
	}

	public static void setOutstockType(Integer outstockType) {
		Type.outstockType = outstockType;
	}

	public static Integer getSupplierType() {
		return supplierType;
	}

	public static void setSupplierType(Integer supplierType) {
		Type.supplierType = supplierType;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Type getParent() {
		return parent;
	}

	public void setParent(Type parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", parent=" + parent + ", name=" + name + ", remark=" + remark + "]";
	}


}