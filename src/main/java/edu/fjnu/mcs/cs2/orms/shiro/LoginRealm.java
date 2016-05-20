package edu.fjnu.mcs.cs2.orms.shiro;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import edu.fjnu.mcs.cs2.orms.entity.ActiveUser;

/**
 * @author 李冰冰
 * @date 创建时间：2016年5月17日 下午4:41:11
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public class LoginRealm extends AuthorizingRealm {

	@Override
	public void setName(String name) {
		super.setName("testRealm");
	}

	// 支持什么类型的token
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	// 清除缓存。在service修改权限后调用
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("我进来了认证。。。。。。");
		// //因为用户名为Long。则需要判断传入的username是否为数字
		// Pattern pattern = Pattern.compile("[0-9]*");
		// Matcher isNum = pattern.matcher((String) token.getPrincipal());
		// if( !isNum.matches() )return null;
		//
		// Long userCode = Long.parseLong((String) token.getPrincipal());
		// 此时先使用模拟数据

		// 从token中 获取用户身份信息
		String username = (String) token.getPrincipal();
		// 拿username从数据库中查询
		// ....
		// 如果查询不到则返回null
		if (!username.equals("zhang")) {// 这里模拟查询不到
			return null;
		}

		// 获取从数据库查询出来的用户密码
		String password = "123";// 这里使用静态数据模拟。。

		// 根据用户id从数据库取出菜单
		// ...先用静态数据
		// List<SysPermission> menus = new ArrayList<SysPermission>();;
		//
		// SysPermission sysPermission_1 = new SysPermission();
		// sysPermission_1.setName("商品管理");
		// sysPermission_1.setUrl("/item/queryItem.action");
		// SysPermission sysPermission_2 = new SysPermission();
		// sysPermission_2.setName("用户管理");
		// sysPermission_2.setUrl("/user/query.action");
		//
		// menus.add(sysPermission_1);
		// menus.add(sysPermission_2);
		//
		// 构建用户身份信息
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(username);
		activeUser.setUsername(username);
		activeUser.setUsercode(username);
		// activeUser.setMenus(menus);

		// 返回认证信息由父类AuthenticatingRealm进行认证
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, password,
				getName());

		return simpleAuthenticationInfo;

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// System.out.println("我进入到了这里授权认证");
		//
		// //得到身份信息。需跟登录验证类型一致
		// Staff staff = (Staff) principals.getPrimaryPrincipal();
		// System.out.println("授权认证:"+ staff.getName());
		//
		//// System.out.println("缓存验证");
		//// staffDao.getStaffById(110);
		//
		// //添加登录授权的身份
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// StaffRoleEnum[] allStaffRole = StaffRoleEnum.values();
		// String role =null;
		// for (StaffRoleEnum staffRole : allStaffRole) {
		// if(staffRole.getIntValue()==staff.getRole()){
		// role = staffRole.name();
		// System.out.println(role);
		// break;
		// }
		// }
		// simpleAuthorizationInfo.addStringPermission(role);
		// System.out.println("结束授权认证");
		return simpleAuthorizationInfo;

	}

}
