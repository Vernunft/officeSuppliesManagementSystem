package edu.fjnu.mcs.cs2.orms.common;

import java.util.List;

/**
 * 
 * @ClassName: DTO 
 * @Description: 数据传输对象
 * @author lbb
 * @date 2016年5月21日 下午3:25:27
 */
public class DTO {
	private String str;
	private Integer inte;
	private Integer currentPage = 1;
	private Integer size = 10;
	private Object object;
	private List<Object> objects;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Integer getInte() {
		return inte;
	}
	public void setInte(Integer inte) {
		this.inte = inte;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public List<Object> getObjects() {
		return objects;
	}
	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}
	
}
