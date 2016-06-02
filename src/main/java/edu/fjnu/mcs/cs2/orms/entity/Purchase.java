package edu.fjnu.mcs.cs2.orms.entity;

import java.util.List;

/**
 * @Title: Purchase.java
 * @Package edu.fjnu.mcs.cs2.orms.entity
 * @Description: TODO(采购单)
 * @author lbb
 * @date 2016年5月28日 上午11:12:56
 * @version V1.0
 */
public class Purchase {
	private Integer id;

	private Employee pic;

	private Boolean complete;

	private String remark;
	
	private List<Res> reses;
	
	private List<PurchaseRes> purchaseRes;
	

	public List<Res> getReses() {
		return reses;
	}

	public void setReses(List<Res> reses) {
		this.reses = reses;
	}

	public List<PurchaseRes> getPurchaseRes() {
		return purchaseRes;
	}

	public void setPurchaseRes(List<PurchaseRes> purchaseRes) {
		this.purchaseRes = purchaseRes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Employee getPic() {
		return pic;
	}

	public void setPic(Employee pic) {
		this.pic = pic;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", pic=" + pic + ", complete=" + complete + ", remark=" + remark
				+ ", purchaseRes=" + purchaseRes + "]";
	}
	
}
