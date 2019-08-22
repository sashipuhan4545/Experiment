package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_ProgrammableKey {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_ProgrammableKey(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
 
	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='1']/android.widget.ImageView[@index='2']"))
	public static WebElement DismissIcon;

//	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Programmable Keys')]")
//	public static WebElement ProgrammableKey;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static WebElement Ok_Btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Programmable Keys\")")
	public static WebElement ProgrammableKey;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/summary']")
	public static WebElement PTTsummaryText;

	@AndroidFindBy(xpath ="//android.widget.LinearLayout[@index='1']/android.widget.TextView[@index='1']")
	public static WebElement YellowsummaryText;

	//	@AndroidFindBy(uiAutomator= "UiSelector().className(\"android.widget.TextView\").resource-id(\"android:id/summary\").index("\1\")")
	//    public static WebElement YellowsummaryText;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Select PTT Key app')]")
	public static WebElement SelectPTTKey;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No Application']")
	public static WebElement no_Application_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Camera']")
	public static WebElement camera_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='App Updater']")
	public static WebElement app_Updater_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='App Updater']")
	public static WebElement appUpdates_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Calculator']")
	public static WebElement calculator_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
	public static WebElement contactTransfer_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
	public static WebElement contacts_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Downloads']")
	public static WebElement downloads_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='File Manager']")
	public static WebElement fileManager_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='FM Radio']")
	public static WebElement fmRadio_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Photos']")
	public static WebElement photos_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Messaging']")
	public static WebElement messaging_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Play Music']")
	public static WebElement music_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone']")
	public static WebElement phone_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	public static WebElement settings_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim BLE Connect']")
	public static WebElement sonimBLE_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Scout']")
	public static WebElement sonimScout_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Warranty Registration']")
	public static WebElement sonimScoutWarranty_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimCare']")
	public static WebElement sonimCare_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimSetupWizard']")
	public static WebElement setUpWizard_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sound Recorder']")
	public static WebElement soundRecorder_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Calendar']")
	public static WebElement calendar_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clock']")
	public static WebElement clock_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Chrome']")
	public static WebElement chrome_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Gmail']")
	public static WebElement gmail_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Maps']")
	public static WebElement maps_app;


	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.calendar:id/header']")
	public static WebElement calendar_Page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.calculator:id/formula']")
	public static WebElement calc_Page;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/shutter_button']")
	public static WebElement camera_Page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.deskclock:id/digital_clock']")
	public static WebElement clock_Page;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.sonimtech.easycontactsshare:id/btnBtImportContacts']")
	public static WebElement contact_transfer_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
	public static WebElement contacts_native_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Downloads']")
	public static WebElement downloads_page;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_view_details_item']")
	public static WebElement fileManager_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='FM Radio']")
	public static WebElement fmRadio_page;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.google.android.apps.photos:id/main_container']")
	public static WebElement photos_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Messaging']")
	public static WebElement messaging_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Listen Now']")
	public static WebElement music_page;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.dialer:id/floating_action_button']")
	public static WebElement phone_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Programmable Key']")
	public static WebElement setting_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim BLE Connect']")
	public static WebElement sonimBLE_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Setup']")
	public static WebElement sonimSetUp_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.scout:id/tab_title']")
	public static WebElement sonimScout_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Warranty Registration']")
	public static WebElement warrantyReg_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimCare']")
	public static WebElement sonimCare_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimSetupWizard']")
	public static WebElement setUpWizard_page;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/recordButton']")
	public static WebElement soundRecorder_page;

	@FindBy(how=How.XPATH, using =("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"))
	public static WebElement chrome_page;

	@AndroidFindBy(xpath = "//android.support.v4.view.ViewPager[@resource-id='com.google.android.gm:id/welcome_tour_pager']")
	public static WebElement gmail_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Make it your map']")
	public static WebElement maps_page;

	
	// Yellow Key ----------------------------------------------------------------------------
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Select YELLOW Key app')]")
	public static WebElement SelectYellwKey;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"App Updater\")")
    public static WebElement AppUpdator_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Calculator\")")
    public static WebElement calculator_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Calendar\")")
    public static WebElement Calendar_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Camera\")")
    public static WebElement Camera_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Clock\")")
    public static WebElement Clock_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Contact Transfer\")")
    public static WebElement ContactTransfer_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Contacts\")")
    public static WebElement Contacts_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Downloads\")")
    public static WebElement Downloads_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"File Manager\")")
    public static WebElement FileManager_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"FM Radio\")")
    public static WebElement FMRadio_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Photos\")")
    public static WebElement Photos_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
    public static WebElement Messaging_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Phone\")")
    public static WebElement Phone_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Play Music\")")
    public static WebElement PlayMusic_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
    public static WebElement Settings_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim BLE Connect\")")
    public static WebElement SonimBLEConnect_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim Scout\")")
    public static WebElement SonimScout_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim Warranty Registration\")")
    public static WebElement Warranty_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"SonimCare\")")
    public static WebElement SonimCare_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"SonimSetupWizard\")")
    public static WebElement SonimSetupWizard_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Sound Recorder\")")
    public static WebElement SoundRecorder_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Chrome\")")
    public static WebElement Chrome_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Gmail\")")
    public static WebElement Gmail_YellwApp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Maps\")")
    public static WebElement Maps_YellwApp;
}

