package kr.bumjin.spring;

import kr.bumjin.spring.dao.DataDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //BeanFactory factory = new XmlBeanFactory(new ClassPathResource("application-context.xml") );
        //ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		//SayHello hello = (SayHello) factory.getBean("hello");
		//hello.greet();
		
        //BeanFactory factory = new XmlBeanFactory(new ClassPathResource("dataAccessContext.xml") );
        ApplicationContext context = new ClassPathXmlApplicationContext("dataAccessContext.xml");
		DataDao stuff = (DataDao) context.getBean("dataStuff");
		System.out.println(stuff.getNames());
		System.out.println("OK");
		
    	
    	//BeanFactory context = new XmlBeanFactory(new ClassPathResource("application-context.xml") );
    	/*ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		ConfigTest configTest = (ConfigTest) context.getBean("configTest");
		System.out.println("driver "+configTest.getDriverClass());
		System.out.println("url "+configTest.getUrl());
		System.out.println("username "+configTest.getUsername());
		System.out.println("password "+configTest.getPassword());
		*/
    }
}
