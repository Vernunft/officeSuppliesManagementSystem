package edu.fjnu.mcs.cs2.orms.entity;
/**   
 * @Title: OutstockSpecificRes.java 
 * @Package edu.fjnu.mcs.cs2.orms.entity 
 * @Description: TODO(物品出库记录) 
 * @author lbb
 * @date 2016年5月28日 上午11:22:53 
 * @version V1.0   
 */
public class OutstockSpecificRes {
	private Outstock outstock;
	
	private SpecificRes specificRes;

	public Outstock getOutstock() {
		return outstock;
	}

	public void setOutstock(Outstock outstock) {
		this.outstock = outstock;
	}

	public SpecificRes getSpecificRes() {
		return specificRes;
	}

	public void setSpecificRes(SpecificRes specificRes) {
		this.specificRes = specificRes;
	}

	@Override
	public String toString() {
		return "OutstockSpecificRes [outstock=" + outstock + ", specificRes=" + specificRes + "]";
	}
	
	
}
