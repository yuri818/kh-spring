package com.di;

public abstract class Duck {
	int leg = 0;
	String name = null;
	public Duck() {}
	public abstract void display();
	public void swimming() {
		System.out.println("오리는 물에 뜹니당~!");
	}
}
