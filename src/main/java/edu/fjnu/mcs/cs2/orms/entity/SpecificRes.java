package edu.fjnu.mcs.cs2.orms.entity;

import edu.fjnu.mcs.cs2.orms.type.SpecificResStatus;

public class SpecificRes {

	private Integer id;

    private Res res;

    private SpecificResStatus type;
   
    private Float buyPrice;
    
    public SpecificRes() {
		super();
		// TODO Auto-generated constructor stub
	}
    

    public SpecificRes( Res res, SpecificResStatus type, Float buyPrice) {
		super();
		this.res = res;
		this.type = type;
		this.buyPrice = buyPrice;
	}


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

	public SpecificResStatus getType() {
		return type;
	}

	public void setType(SpecificResStatus type) {
		this.type = type;
	}

	public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }


	@Override
	public String toString() {
		return "SpecificRes [id=" + id + ", res=" + res + ", type=" + type + ", buyPrice=" + buyPrice + "]";
	}
    
}