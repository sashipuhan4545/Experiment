package com.xp8.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;


public class XP8_Clock_Util extends BaseUtil{



	public boolean check;
	/*public static AndroidDriver<AndroidElement> aDriver;
	public static AndroidDriver<MobileElement> mdriver;

	public static WebDriver wdriver;*/
	public SoftAssert softAssert;

	public void End() {
		aDriver.quit();
	}

	

	public void add_alarm(){
	
		
	}







}
