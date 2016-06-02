package edu.fjnu.mcs.cs2.orms.common;

import org.apache.commons.lang.ObjectUtils.Null;

import edu.fjnu.mcs.cs2.orms.entity.Type;

/**
 * @Title: Test.java
 * @Package edu.fjnu.mcs.cs2.orms.common
 * @Description: TODO(用一句话描述该文件做什么)
 * @author lbb
 * @date 2016年5月31日 下午9:38:38
 * @version V1.0
 */
public class Test {

	public static void main(String[] args) {

		// 设置黄文强不在
		Type  type = new Type();

		boolean flag1 = false;

		// 设置刘克强在

		boolean flag2 = false;

		System.out.println("开始->");

		if (type.getId()!=null) {

			System.out.println("黄文强在");

		} else if (type.getId()!=null) {

			System.out.println("刘克强在");

		} else {

			System.out.println("他们不在");

		}

		System.out.println("->结束");

		System.out.println(type.getName()==null);
		System.out.println();

	}

}
