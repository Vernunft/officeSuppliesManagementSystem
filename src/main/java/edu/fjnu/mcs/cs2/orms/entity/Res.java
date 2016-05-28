package edu.fjnu.mcs.cs2.orms.entity;

import edu.fjnu.mcs.cs2.orms.type.Category;
import edu.fjnu.mcs.cs2.orms.type.Unit;

public class Res {
    private Integer id;

    private Category category;

    private Unit unit;

    private String name;

    private String model;

    private Integer stockMax;

    private Integer stockMin;

    private Integer stockNow;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public Integer getStockMax() {
        return stockMax;
    }

    public void setStockMax(Integer stockMax) {
        this.stockMax = stockMax;
    }

    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Integer getStockNow() {
        return stockNow;
    }

    public void setStockNow(Integer stockNow) {
        this.stockNow = stockNow;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}