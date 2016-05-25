package edu.fjnu.mcs.cs2.orms.type;

import java.util.List;

import edu.fjnu.mcs.cs2.orms.entity.Type;

/**
 * 
 * @ClassName: Category 
 * @Description: 继承type的子类（物品类别）
 * @author lbb
 * @date 2016年5月21日 下午3:24:41
 */
public class Category extends Type {
	public static Integer type = 12;
	private Integer count; 
	private List<Category> child;
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Category> getChild() {
		return child;
	}

	public void setChild(List<Category> child) {
		this.child = child;
	}
	
}
