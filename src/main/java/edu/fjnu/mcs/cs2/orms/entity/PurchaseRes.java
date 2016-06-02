package edu.fjnu.mcs.cs2.orms.entity;

/**
 * @Title: PurchaseRes.java
 * @Package edu.fjnu.mcs.cs2.orms.entity
 * @Description: TODO(物品采购关联)
 * @author lbb
 * @date 2016年5月28日 上午11:19:36
 * @version V1.0
 */
public class PurchaseRes {
	private Purchase purchase;
	
	private Res res;
	
	private Integer resCount;

	private Float resPrice;

	private Integer arrivalCount;

	private Integer cancelCount;

	public Integer getResCount() {
		return resCount;
	}

	public void setResCount(Integer resCount) {
		this.resCount = resCount;
	}

	public Float getResPrice() {
		return resPrice;
	}

	public void setResPrice(Float resPrice) {
		this.resPrice = resPrice;
	}

	public Integer getArrivalCount() {
		return arrivalCount;
	}

	public void setArrivalCount(Integer arrivalCount) {
		this.arrivalCount = arrivalCount;
	}

	public Integer getCancelCount() {
		return cancelCount;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Res getRes() {
		return res;
	}

	public void setRes(Res res) {
		this.res = res;
	}

	public void setCancelCount(Integer cancelCount) {
		this.cancelCount = cancelCount;
	}

	@Override
	public String toString() {
		return "PurchaseRes [purchase=" + purchase + ", res=" + res + ", resCount=" + resCount + ", resPrice="
				+ resPrice + ", arrivalCount=" + arrivalCount + ", cancelCount=" + cancelCount + "]";
	}

}
