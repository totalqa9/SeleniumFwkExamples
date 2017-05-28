package org.toolsqa;

public class ClassA {

	public ClassA m1()
	{
		return this;
	}
	public ClassA m2()
	{
		return this;
	}
	public ClassA m3()
	{
		return this;
	}
	public static void main(String[] args) {
		ClassA obj = new ClassA();
		obj.m1().m2().m3();
	}
}
