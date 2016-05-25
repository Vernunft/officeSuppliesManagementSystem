package edu.fjnu.mcs.cs2.orms.entity;

import edu.fjnu.mcs.cs2.orms.type.ResStatus;

public class SpecificRes {
    private Integer id;

    private Res res;

    private ResStatus type;
   
    private Float buyPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Res getRes() {
		return res;
	}

	public void setRes(Res res) {
		this.res = res;
	}

	public ResStatus getType() {
		return type;
	}

	public void setType(ResStatus type) {
		this.type = type;
	}

	public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }
}