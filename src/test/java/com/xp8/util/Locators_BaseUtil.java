package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_BaseUtil {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_BaseUtil(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	@FindBy(xpath="//android.widget.TextView[@text='No recent items']")
	public static AndroidElement no_Recent_Items;

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.android.systemui:id/task_view_thumbnail']")
	public static AndroidElement recent_App;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR ALL']")
	public static AndroidElement clear_all;	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allow_PopUp;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='3']//android.widget.TextView[@resource-id='com.android.mms:id/next']")
	public static AndroidElement NEXT;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement AppListIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Chrome\")")
	public static AndroidElement browser_App;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
	public static AndroidElement contacts;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone']")
	public static AndroidElement phone_DailerApp;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Scout']")
	public static AndroidElement scoutApp_icon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Applications']")
	public static AndroidElement applications_icon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Music']")
	public static AndroidElement music_icon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='FM Radio']")
	public static AndroidElement FM_App;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='File Manager']")
	public static AndroidElement FileExplorer_App;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Backup and Restore']")
	public static AndroidElement backUP_reset;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Downloads']")
	public static AndroidElement downloads_icon;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static AndroidElement settings;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.borqs.camera:id/photo_capture']")
	//com.sonim.borqs.launcher:id/icon_text
	public static AndroidElement camera_Icon;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Camera']")
	//com.sonim.borqs.launcher:id/icon_text
	public static AndroidElement cameraApp;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Calendar']")
	public static AndroidElement CalenderApp;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tools']")
	public static AndroidElement ToolsOptn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Calculator']")
	public static AndroidElement calciApp;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clock']")
	public static AndroidElement clockApp;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Clock']")
	public static AndroidElement clock_Icon;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sound Recorder']")
	public static AndroidElement RecorderApp;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Photos']")
	public static AndroidElement gallery_icon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Messaging']")
	public static AndroidElement messaging_icon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Message+\")")
	public static AndroidElement MessagePlus;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Messages']")
	public static AndroidElement message_icon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.support.v7.widget.RecyclerView\").resourceId(\"com.android.launcher3:id/apps_list_view\")")
	public static MobileElement SettingsList;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='FillMemory']")
	public static AndroidElement fillmemoryIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Gmail\")")
	public static AndroidElement gmail;


}
