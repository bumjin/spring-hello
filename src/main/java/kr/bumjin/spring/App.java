package kr.bumjin.spring;

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
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		SayHello hello = (SayHello) ctx.getBean("hello");
		hello.greet();
    }
}
