package com.xp8.util;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Contacts {
	

	private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Contacts(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	 @FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@resource-id,'com.android.settings:id/switch_widget')]")
	 public static AndroidElement wifiToggle;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.contacts:id/menu_search') @index=['0']")
	 public static AndroidElement searchContactBtn;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.contacts:id/search_view')]")
	 public static AndroidElement searchfield;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.contacts:id/floating_action_button')]")
	 public static AndroidElement addContactBtn;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'OK')]")
	 public static AndroidElement OKBtn;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'PHONE')]")
	 public static AndroidElement defaultPhonrBtn;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.LinearLayout[contains(@index=['1'])")
	 public static AndroidElement defaultSaveContactBtn;
	 
	 @AndroidFindBy(xpath = "//android.widget.EditText[@text='Name prefix']")
	 public static AndroidElement namePrefixField;
	 
//	 @AndroidFindBy(uiAutomator="new UiSelector().text(\"Name\")")
//	  public static AndroidElement contactNameField;
//	  
//	  public static AndroidElement contactNameFieldText()
//	  {
//	   
//	   return contactNameField;
//	  }
	 @AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	 public static AndroidElement nameField;
	
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Phone')]")
	 public static AndroidElement phoneNumberField;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.FrameLayout[contains(@resource-id,'com.android.contacts:id/expansion_view_container')]")
	 public static AndroidElement expandNameField;
	 
	 @AndroidFindBy(xpath = "//android.widget.EditText[@text='First name']")
	 public static AndroidElement firstNameField;
	
}
