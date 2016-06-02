package edu.fjnu.mcs.cs2.orms.entity;

public class InstockSpecificRes {
    
    private SpecificRes specificRes;

    private Instock instock;

	public SpecificRes getSpecificRes() {
		return specificRes;
	}

	public void setSpecificRes(SpecificRes specificRes) {
		this.specificRes = specificRes;
	}

	public Instock getInstock() {
		return instock;
	}

	public void setInstock(Instock instock) {
		this.instock = instock;
	}

	@Override
	public String toString() {
		return "InstockSpecificRes [specificRes=" + specificRes + ", instock=" + instock + "]";
	}

}