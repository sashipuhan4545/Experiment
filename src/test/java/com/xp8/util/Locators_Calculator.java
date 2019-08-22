package com.xp8.util;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;



public class Locators_Calculator{
	
		
	
	private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Calculator(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	

	//Calculator Elements
	
	//Numeric Keys

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ACCEPT\")")
	public static AndroidElement Acceptbtn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"0\")")
	public static AndroidElement calc_0_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"1\")")
	public static AndroidElement calc_1_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"2\")")
	public static AndroidElement calc_2_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"3\")")
	public static AndroidElement calc_3_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"4\")")
	public static AndroidElement calc_4_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"5\")")
	public static AndroidElement calc_5_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"6\")")
	public static AndroidElement calc_6_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"7\")")
	public static AndroidElement calc_7_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"8\")")
	public static AndroidElement calc_8_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"9\")")
	public static AndroidElement calc_9_btn;	
	
	
	//Operator Keys
	
//	@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[@index='1']/android.widget.Button[@index='4']"))
//	public static AndroidElement calc_add_btn;	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"+\")")
	 public static AndroidElement calc_add_btn;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"−\")")
	public static AndroidElement calc_sub_btn;	


	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"×\")")
	public static AndroidElement calc_Mul_btn;	
	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"÷\")")
	public static AndroidElement calc_div_btn;	
	
//	@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[@index='1']/android.widget.Button[@index='0']"))
//	public static AndroidElement calc_Delete_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DEL\")")
	 public static AndroidElement calc_Delete_btn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='1']/android.widget.ImageView[@index='2']"))
	 public static AndroidElement DismissIcon;
	
	@FindBy(how=How.XPATH, using =("//android.widget.GridLayout[@index='1']/android.widget.Button[@index='1']"))
	public static AndroidElement calc_Deletelandscape_btn;	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CLR\")")
	public static AndroidElement calc_Clear_btn;	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"=\")")
	public static AndroidElement calc_Eql_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\".\")")
	public static AndroidElement calc_decpoint_btn;	
	
	//Result Field
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@resource-id,'com.google.android.calculator:id/formula')]"))
	public static AndroidElement calc_Edit_text_field;	

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@resource-id,'com.android.calculator2:id/result') @index='2']"))
	public static AndroidElement calc_lower_textView_field;
	
	@FindBy(how=How.XPATH, using =("//android.widget.GridLayout[contains(@resource-id,'com.android.calculator2:id/pad_advanced') @index='1']"))
	public static AndroidElement calc_side_advancecalc;
	
	
	// Hamburger option
	
	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@index='0']"))
	public static AndroidElement calc_hamburger_Icon;	
	
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@text,'Answer as fraction')]"))
	public static AndroidElement calc_asfraction_optn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@resource-id,'com.android.calculator2:id/message')]"))
	public static AndroidElement calc_resultfract_text_field;	

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@text,'Answer with leading digits')]"))
	public static AndroidElement calc_asleadingdig_optn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@text,'Answer with leading digits')]"))
	public static AndroidElement calc_openlicense_optn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.Button[contains(@resource-id,'android:id/button2')]"))
	public static AndroidElement calc_dismiss_Btn;
	
	
	
	//Home Screen
	
	@FindBy(how=How.XPATH, using =("//android.widget.ImageView[contains(@resource-id,'com.android.launcher3:id/all_apps_handle')]"))
	public static AndroidElement apps_trail;
	
	
	//Radian Keys
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"INV\")")
	public static AndroidElement calc_INV_btn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"sin\")")
	public static AndroidElement calc_sin_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DEG\")")
	public static AndroidElement calc_DEG_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"%\")")
	public static AndroidElement calc_percentage_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"cos\")")
	public static AndroidElement calc_cos_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"tan\")")
	public static AndroidElement calc_tan_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ln\")")
	public static AndroidElement calc_ln_btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"log\")")
	public static AndroidElement calc_log_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"!\")")
	public static AndroidElement calc_factorial_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resource-id(\"com.google.android.calculator:id/const_pi\")")
	public static AndroidElement calc_pie_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"e\")")
	public static AndroidElement calc_e_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"(\")")
	public static AndroidElement calc_openbracket_btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\")\")")
	public static AndroidElement calc_closebracket_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resource-id(\"com.google.android.calculator:id/op_sqrt\")")
	public static AndroidElement calc_root_btn;
}