package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators {
	
	// Common Locators are saved in this.
	
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	@FindBy(xpath="//android.widget.TextView[@text='No recent items']")
	public static WebElement no_Recent_Items;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='2']")
	public static WebElement close_Icon_Recent_Items;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR ALL']")
	public static WebElement clear_all;
	

}
