package edu.fjnu.mcs.cs2.orms.entity;

public class InoutstockResList {
    private Integer id;
    
    private SpecificRes specificRes;

    private Outstock outstock;

    private Instock instock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public SpecificRes getSpecificRes() {
		return specificRes;
	}

	public void setSpecificRes(SpecificRes specificRes) {
		this.specificRes = specificRes;
	}

	public Outstock getOutstock() {
		return outstock;
	}

	public void setOutstock(Outstock outstock) {
		this.outstock = outstock;
	}

	public Instock getInstock() {
		return instock;
	}

	public void setInstock(Instock instock) {
		this.instock = instock;
	}

}