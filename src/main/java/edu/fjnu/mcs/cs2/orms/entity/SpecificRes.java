package edu.fjnu.mcs.cs2.orms.entity;

import edu.fjnu.mcs.cs2.orms.type.Status;

public class SpecificRes {
    private Integer id;

    private Res res;

    private Status type;
   
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

	public Status getType() {
		return type;
	}

	public void setType(Status type) {
		this.type = type;
	}

	public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }
}