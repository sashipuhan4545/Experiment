package com.xp8.test;

public class Check {
	
	public static boolean check = false;

	public static void main(String[] args) {
		System.out.println(check);
		Check c = new Check();
		c.changeValue();
		System.out.println(check);

	}
	
	public void changeValue() {
		check=true;
		System.out.println(check);
	}

}
