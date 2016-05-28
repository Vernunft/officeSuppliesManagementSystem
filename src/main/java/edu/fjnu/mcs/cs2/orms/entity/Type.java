package edu.fjnu.mcs.cs2.orms.entity;

public class Type {
    private Integer id;

    private Type parent;

    private String name;

    private String remark;
    
    public static Integer type = 1 ;
    
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

	public static Integer getType() {
		return type;
	}

	public static void setType(Integer type) {
		Type.type = type;
	}

}