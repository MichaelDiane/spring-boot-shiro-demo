package com.xingleng.shiro.chapter01;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroChapter01ApplicationTests {

	@Test
	public void testLogin() {
		//获取 SecurityManager工厂，使用配置文件初始化 SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//利用 factory 实例化SecurityManager
		SecurityManager securityManager = factory.getInstance();
		//利用SubjectUtil绑定securityManager
		SecurityUtils.setSecurityManager(securityManager);
		//获取Subject主题，并创建用户和用密码的token验证
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("test","123456");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//验证用户是否登录成功
		Assert.assertEquals(true, subject.isAuthenticated());
		//退出登录
		subject.logout();
	}
	
	@Test
	public void testCustomRealm() {
		//获取 SecurityManager工厂，使用配置文件初始化 SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//利用 factory 实例化SecurityManager
		SecurityManager securityManager = factory.getInstance();
		//利用SubjectUtil绑定securityManager
		SecurityUtils.setSecurityManager(securityManager);
		//获取Subject主题，并创建用户和用密码的token验证
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("test","123456");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//验证用户是否登录成功
		Assert.assertEquals(true, subject.isAuthenticated());
		//退出登录
		subject.logout();
		
	}

	@Test
	public void testMultiRealm() {
		//获取 SecurityManager工厂，使用配置文件初始化 SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
		//利用 factory 实例化SecurityManager
		SecurityManager securityManager = factory.getInstance();
		//利用SubjectUtil绑定securityManager
		SecurityUtils.setSecurityManager(securityManager);
		//获取Subject主题，并创建用户和用密码的token验证
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("MichaelDiane","123456");
		
		try {
			subject.login(token);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//验证用户是否登录成功
		Assert.assertEquals(true, subject.isAuthenticated());
		//退出登录
		subject.logout();
		
	}	
	

}
