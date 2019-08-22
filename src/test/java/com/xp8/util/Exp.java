package com.xp8.util;

import java.io.IOException;

public class Exp {

	public static void main(String[] args) throws IOException, InterruptedException {
		
//		Runtime run = Runtime.getRuntime();
//		Process pr = run.exec("adb shell input keyevent 26");
//		pr.waitFor();
//		Thread.sleep(1000);
//		Runtime run1 = Runtime.getRuntime();
//		
//		
//		Process pr1 = run.exec("adb shell input keyevent 82");
//		pr1.waitFor();
		
		Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"adb shell input tap 540 1776\"");

//		Runtime run = Runtime.getRuntime();
//		Process pr = run.exec("adb shell input tap 540 1776");
//		pr.waitFor();
//		Runtime run1 = Runtime.getRuntime();
//		Process pr1 = run1.exec("adb shell input tap 713 1098");
//		pr1.waitFor();
	

	}
	
	
	public static void clearSMS_Permission() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"adb shell input tap 540 1776\"");
	}

}

