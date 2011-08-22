package kr.bumjin.spring;

public class  SayHello
{
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void greet() {
		System.out.println("Hello "+getName()+"!!");
	}
}
