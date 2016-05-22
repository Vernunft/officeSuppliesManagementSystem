package edu.fjnu.mcs.cs2.orms.entity;

import java.util.Date;

public class InstockPO {
    private Integer id;

    private Integer makeId;

    private Integer attnId;

    private Integer suppllierId;

    private Integer typeId;

    private Date ate;

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

    public Integer getAttnId() {
        return attnId;
    }

    public void setAttnId(Integer attnId) {
        this.attnId = attnId;
    }

    public Integer getSuppllierId() {
        return suppllierId;
    }

    public void setSuppllierId(Integer suppllierId) {
        this.suppllierId = suppllierId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getAte() {
        return ate;
    }

    public void setAte(Date ate) {
        this.ate = ate;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve == null ? null : reserve.trim();
    }
}