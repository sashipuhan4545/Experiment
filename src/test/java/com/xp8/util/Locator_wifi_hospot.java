package com.xp8.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locator_wifi_hospot {
	private static AndroidDriver<AndroidElement> aDriver;

	public Locator_wifi_hospot(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
	public static AndroidElement networkandinternetopt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").content-desc(\"IDCSONWAP,Check password and try again,Wifi signal full.,Secure network\")")
	public static AndroidElement wifitext;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='IDCSONWAP,Check password and try again,Wifi signal full.,Secure network']")
	public static AndroidElement wifij;
	

	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
	public static AndroidElement wifi;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switchWidget\")")
	public static AndroidElement wifiradiobtn;
//		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
//		public static AndroidElement wifioff;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"IDCSONWAP\")")
	public static AndroidElement wifi1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Belkin 2.4\")")
	public static AndroidElement wifi2;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/password\")")
	public static AndroidElement wifitextfield;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement wifioff;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CONNECT\")")
	public static AndroidElement wificonnect;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Check password and try again\")")
	public static AndroidElement wificonmsg;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
	public static AndroidElement wififrame;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"No Internet \")")
	public static AndroidElement nointernet;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"No Internet \")")
	public static AndroidElement connectinternet;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Starred\")")
	public static AndroidElement starred;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement googlemap;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Maps\")")
	public static AndroidElement maps;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Maps is offline. Check your network connection.\")")
	public static AndroidElement netwkerr;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.apps.maps:id/search_omnibox_menu_button\")")
	public static  AndroidElement appmenu;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Your Timeline\")")
	public static  AndroidElement urtimeline;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connect to the Internet for more results\")")
	public static  AndroidElement gmailerr;
//	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.google.android.gm:id/open_search']/android.widget.ImageButton")
//	public static AndroidElement menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\")")
	public static  AndroidElement menu;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Social\")")
	public static  AndroidElement social;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"YouTube\")")
	public static  AndroidElement youtube;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Search YouTube\")")
	public static  AndroidElement searchyoutubeenter;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").index(\"1\")")
	public static  AndroidElement youtubesearchbtn;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"sonim xp8\")")
	public static  AndroidElement xp8;
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static AndroidElement gmailmenu;
}
