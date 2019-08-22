package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Warranty_Reg {

private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Warranty_Reg(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='CLEAR ALL']"))
	public static WebElement ClearAll;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss Sonim Scout.']")
	 public static WebElement DismissIconCare;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss Sonim Warranty Registration.']")
	 public static WebElement DismissIconWarranty;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Warranty Registration\")")
	public static WebElement SWR_String_Name;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Support\")")
	public static WebElement SWR_Support_Tab;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Contacting Server. Please wait…\")")
	public static WebElement SWR_Contacting_server_progress_dialog;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Not Registered\")")
	public static WebElement SWR_Not_Registered;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Registered User\")")
	public static WebElement SWR_Registered;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Device Information\")")
	public static WebElement SWR_String_Device_Information;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Register your device\")")
	public static WebElement SWR_String_Register_Your_Device;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Unable to fetch registration information from server\")")
	public static WebElement SWR_UnableTo_Fetch_RegistrationInforamtionFromServer;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static WebElement SWR_OK_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/now\")")
	public static WebElement SWR_Now;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/resetButton\")")
	public static WebElement SWR_Reset;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/submit\")")
	public static WebElement SWR_Submit;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.sonimtech.warrantyregapp:id/submit\").text(\"Remove all\")")
	public static WebElement SWR_Remove_All;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.systemui:id/app_thumbnail_image\")")
	public static WebElement SWR_RecentApp;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please contact Sonim at support@sonimtech.com to register your handset with scanned copy of invoice\")")
	public static WebElement SWR_Please_contact_Sonim_At;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/autocomplete_country\")")
	public static WebElement SWR_Country;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"No internet Connection. Please check your connection settings and try again\")")
	public static WebElement SWR_No_internet_Connection;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter first name\")")
	public static WebElement SWR_please_enter_first_Name;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter first name\")")
	public static WebElement SWR_Enter_First_Name;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter last name\")")
	public static WebElement SWR_Please_Enter_Last_Name;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter company name\")")
	public static WebElement SWR_Enter_Company_Name;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter company\")")
	public static WebElement SWR_Please_enter_Company_Name;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter last name\")")
	public static WebElement SWR_Enter_Last_Name;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter address1\")")
	public static WebElement SWR_Please_enter_Address_1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter address 1\")")
	public static WebElement SWR_Enter_Address_1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter city\")")
	public static WebElement SWR_Please_enter_City;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter city\")")
	public static WebElement SWR_enter_City;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter state\")")
	public static WebElement SWR_Please_Enter_State;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter state name\")")
	public static WebElement SWR_Enter_State_Name;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter zipcode\")")
	public static WebElement SWR_Please_enter_zipcode;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter zip code\")")
	public static WebElement SWR_Enter_zip_Code;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter emailid\")")
	public static WebElement SWR_Please_enter_emailid;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter email id\")")
	public static WebElement SWR_Enter_Email_Id;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter phone number\")")
	public static WebElement SWR_Please_enter_phone_number;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter phone number\")")
	public static WebElement SWR_Enter_Phone_Number;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter purchase date\")")
	public static WebElement SWR_Please_Enter_purchase_date;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/purchaseddate\")")
	public static WebElement SWR_Purchased_On;
	
	
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.NumberPicker\").index(0)")
	public static WebElement SWR_Date;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.NumberPicker\").index(1)")
	public static WebElement SWR_Month;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.NumberPicker\").index(2)")
	public static WebElement SWR_Year;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"@\")")
	public static WebElement SWR_At_the_Rate_Symbol;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CHOOSE\")")
	public static WebElement SWR_ChooseBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter Dealer Name\")")
	public static WebElement SWR_DelearName;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Take a Picture\")")
	public static WebElement SWR_Take_a_Pic;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static WebElement SWR_AllowBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static WebElement SWR_PhotoCapture;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Reset\")")
	public static WebElement SWR_RstBtn;
	
	
		
	
	
	
}
