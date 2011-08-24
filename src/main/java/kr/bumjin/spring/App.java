package kr.bumjin.spring;

import kr.bumjin.spring.dao.DataDaoImpl;

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
        /*ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml","dataAccessContext.xml"});
		DataDaoImpl stuff = (DataDaoImpl) context.getBean("dataStuff");*/
		
    	
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
