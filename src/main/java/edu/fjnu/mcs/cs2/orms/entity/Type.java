package edu.fjnu.mcs.cs2.orms.entity;

public class Type {
    private Integer id;

    private Integer parent;

    private String name;

    private String remark;
    
    public static Integer type = 1 ;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
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

	public Type(Integer id, Integer parent, String name, String remark) {
		super();
		this.id = id;
		this.parent = parent;
		this.name = name;
		this.remark = remark;
	}

	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}