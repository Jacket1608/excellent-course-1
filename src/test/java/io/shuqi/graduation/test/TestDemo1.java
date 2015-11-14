package io.shuqi.graduation.test;

import io.shuqi.graduation.dao.UserDao;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.enumtype.BrowserEnum;
import io.shuqi.graduation.exception.DaoException;
import io.shuqi.graduation.utils.BrowserUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:application-service.xml"})
public class TestDemo1 {
	@Autowired
	UserDao userDao;
	@Test
	@Transactional
	public void testBaseDao() throws DaoException{
		User user = new User("aaa", "test", "testAmail.com", true, "student", new Date(), 0L);
		userDao.save(user);
		System.out.println(user.getId());
		user.setLoginName("zhangsan");
		userDao.update(user);
		System.out.println(user.getLoginName());
		User temp = userDao.getById(user.getId());
		System.out.println(temp.getLoginName());
		userDao.deleteById(user.getId());
	}
	
	
	
	@Test
	public void testIsIE(){
		BrowserEnum ie = BrowserUtil.getBrowserEnum("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E)");
		BrowserEnum chrome = BrowserUtil.getBrowserEnum("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36");
		Assert.assertTrue(ie.isIE());
		Assert.assertFalse(chrome.isIE());
	}

	@Test
	public void TestVersion(){
		Pattern pattern  = Pattern.compile("MSIE( )+(\\d\\.\\d)+");
		Matcher matcher =  pattern.matcher("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E)");
		if(matcher.find()){
			float vserion =  Float.valueOf(matcher.group(2));
			System.out.println(vserion);
		}
		
	}
	@Test
	public void TestUrl(){
		Pattern pattern  = Pattern.compile("^http://([0-9_a-zA-Z:\\.])+/graduation/login$");
		Matcher matcher =  pattern.matcher("http://localhost/graduation/login");
		if(matcher.find()){
			System.out.println(matcher.group());
		}
	}
	@Test
	public void TestUserName(){
		System.out.println("shuqi".matches("^\\w+$"));
		System.out.println("shuqi".matches("^\\w+$"));
		System.out.println("shuqi>".matches("^\\w+$"));
	}
	@Test
	public void TestFormat(){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(new Date()));
	}
	@Test
	public void TestSystemInfo(){
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			System.out.println(key.toString()+":\t"+System.getProperty((key.toString())));
		}
		System.out.println("");
	}
		
}
