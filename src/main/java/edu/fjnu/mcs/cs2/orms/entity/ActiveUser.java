package edu.fjnu.mcs.cs2.orms.entity;

import java.util.Iterator;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 用户身份信息，存入session 由于tomcat将session会序列化在本地硬盘上，所以使用Serializable接口
 * 
 * @author LBB
 * 
 */
public class ActiveUser implements java.io.Serializable {
	private String userid;//用户id
	private String usercode;// 用户账号
	private String username;// 用户名称
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	//private List<SysPermission> menus;// 菜单
	//private List<SysPermission> permissions;// 权限
	
}