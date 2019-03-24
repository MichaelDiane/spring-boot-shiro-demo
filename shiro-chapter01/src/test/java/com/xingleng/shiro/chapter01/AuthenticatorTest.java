package com.xingleng.shiro.chapter01;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/** 
* @author Michael.Zhang
* @version 创建时间：Mar 24, 2019 2:23:37 PM 
* @desc [类说明] 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticatorTest {
	
	private void login(String configFile) {
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("test", "123456");

        subject.login(token);
	}

	@Test
	public void testAllSuccessfulStrategyWithSuccess() {
		    this.login("classpath:shiro-authenticator-all-success.ini");
	        Subject subject = SecurityUtils.getSubject();
	        //得到一个身份集合，其包含了Realm验证成功的身份信息
	        PrincipalCollection principalCollection = subject.getPrincipals();
	        Assert.assertEquals(2, principalCollection.asList().size());
	}
	
	@Test
	public void testAllSuccessfulStrategyWithFail() {
		    this.login("classpath:shiro-authenticator-all-fail.ini");
	        Subject subject = SecurityUtils.getSubject();
	        //得到一个身份集合，其包含了Realm验证成功的身份信息
	        PrincipalCollection principalCollection = subject.getPrincipals();
	        Assert.assertEquals(2, principalCollection.asList().size());
	}
	
	@Test
	public void testFirstSuccessfulStrategyWithFail() {
		    this.login("classpath:shiro-authenticator-first-success.ini");
	        Subject subject = SecurityUtils.getSubject();
	        //得到一个身份集合，其包含了Realm验证成功的身份信息
	        PrincipalCollection principalCollection = subject.getPrincipals();
	        Assert.assertEquals(1, principalCollection.asList().size());
	}
	
	@Test
	public void testLeastOneSuccessfulStrategyWithFail() {
		    this.login("classpath:shiro-authenticator-leastone-success.ini");
	        Subject subject = SecurityUtils.getSubject();
	        //得到一个身份集合，其包含了Realm验证成功的身份信息
	        PrincipalCollection principalCollection = subject.getPrincipals();
	        Assert.assertEquals(1, principalCollection.asList().size());
	}

}
