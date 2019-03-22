package com.xingleng.shiro.chapter01;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/** 
* @author Michael.Zhang
* @version 创建时间：Mar 20, 2019 11:24:26 AM 
* @desc [权限自定义域实现] 
*/
public class MyRealm implements Realm {

	@Override
	public String getName() {
		return "myRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		//仅支持 UsernamePasswordToken 类型的token  
		return (token instanceof UsernamePasswordToken);
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//从token获取已通过验证的用户名
		String userName = (String)token.getPrincipal();
		
		//获取提交的申请验证密码,密码返回时为字符数组
		String password = new String((char[])token.getCredentials());
		
		if(!"test".equals(userName)) {
			throw new UnknownAccountException("用户名或者密码错误");
		}
		
		if(!"123456".equals(password)) {
			throw new IncorrectCredentialsException("用户名或者密码错误");
		}
		//验证成功，返回一个认证信息实例
		return new SimpleAuthenticationInfo(userName, password, getName());
	}

}
