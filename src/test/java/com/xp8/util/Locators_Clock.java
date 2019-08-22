package com.xp8.util;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;



public class Locators_Clock{
	
	
	private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Clock(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
		
	}
	
	@FindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement name;
	
	@AndroidFindBy(id = "an")
	public static AndroidElement settings;	
	
	@FindBy(xpath="//android.widget.TextView")
	public static List<AndroidElement> findtext;
	
	
	@FindBy(xpath="//android.support.v7.app.ActionBar$Tab[contains(@index='0')]")
	public static AndroidElement alarmIcon ;
	
	@FindBy(xpath="//android.widget.ImageButton[contains(@resource-id='com.android.deskclock:id/fab')]")
	public static AndroidElement addAlarmIcon ;
	
	@FindBy(xpath="//android.widget.TextView(@resource-id='android:id/minutes')]")
	public static AndroidElement minutesView ;
}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	

