package com.xp8.util;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Safeguard {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Safeguard(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static WebElement AppListIcon;

	@AndroidFindBy(xpath ="//android.widget.TextView[@content-desc='Sonim Scout']")
	public static WebElement sonim_scout_AppLauncher;

	@AndroidFindBy( xpath = "//android.widget.TextView[@text='SafeGuard']")
	public static WebElement SG_String;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Setup']")
	public static WebElement SG_SetUp_String;

	@AndroidFindBy( xpath = "//android.widget.Switch[@resource-id='com.sonim.safeguard:id/switch_widget']")
	public static WebElement SG_toggle_btn;

	//	@AndroidFindBy(id="com.sonim.safeguard:id/switch_widget")
	//	public static WebElement SG_toggle_btn;

	@AndroidFindBy(id="com.sonim.safeguard:id/dialog_edit_text")
	public static WebElement SG_Enter_Pin;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help']")
	public static WebElement SG_Help;

	@AndroidFindBy(id="com.sonim.safeguard:id/tv_help_description")
	public static WebElement SG_Help_text;

	@AndroidFindBy(id="android:id/summary")
	public static WebElement SG_Version_summary_text;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Version']")
	public static WebElement SG_Version;

	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static WebElement SG_OK_Btn_for_Pin;

	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='OK']")
	public static WebElement SG_OK_Btn_for_PinApp;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='SafeGuard']")
	public static WebElement SG_Safe_Guard_String;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Applications']")
	public static WebElement SG_Applications_String;

	@AndroidFindAll(@AndroidFindBy(xpath="//android.widget.RelativeLayout"))
	public static List<WebElement> SG_All_Apps_List;

	@AndroidFindBy(id="android:id/list")
	public static WebElement SG_All_Apps_ListView;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select All']")
	public static WebElement SG_Select_All_Btn;

	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Save']")
	public static WebElement SG_Save_Btn;

	@AndroidFindBy(xpath="//android:id/search_src_text")
	public static WebElement SG_Scout;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Emergency number']")
	public static WebElement SG_Emergency_No;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enter PIN']")
	public static WebElement SG_Locked_Screen_Validation;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Wi-Fi']")
	public static WebElement SG_WLAN_Validation;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='USB for file transfer']")
	public static WebElement SG_Notification_USB;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Use USB to']")
	public static WebElement SG_Use_USB;
	
	@AndroidFindBy(id="com.sonim.safeguard:id/editText")
	public static WebElement SG_Safe_Guard_Locked_Screen_Edit_Box;

	@AndroidFindBy(id="com.sonim.safeguard:id/editText")
	public static WebElement SG_Locked_Screen_Edit_Box;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Unselect All']")
	public static WebElement SG_Un_Select_All_Btn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Safe Guard']")
	public static WebElement SG_Lock_icon_Text;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='OK']")
	public static WebElement SG_Lock_Screen_OK_Btn;

	@AndroidFindBy(id="android:id/alertTitle")
	public static WebElement SG_Bluetooth_Error_Msg;

	//Locators for Version
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Version']")
	public static WebElement SG_Version_String;

	//Locators for Help
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help']")
	public static WebElement SG_Help_String;

	//Locators for Features
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Features']")
	public static WebElement SG_Features_String;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Call screening']")
	public static WebElement SG_Call_Screening;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage black list']")
	public static WebElement SG_Call_Blocking;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Location']")
	public static WebElement SG_Location;

	@AndroidFindBy(id="com.android.settings:id/switch_bar")
	public static WebElement SG_Location_Off_On_Btn;
	
	@AndroidFindBy(id="com.android.contacts:id/cliv_name_textview")
	public static WebElement SG_FirstEntry_Contact_Btn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Add Contact']")
	public static WebElement SG_Add_Contact_String;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.contacts:id/floating_action_button']")
	public static WebElement SG_ADD_CONTACT_String;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
	public static WebElement SG_Find_Contact_In_Search_Box;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Search']")
	public static WebElement SG_Contact_In_Search_Box;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Edit']")
	public static WebElement SG_Contact_Edit_Icon;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Save']")
	public static WebElement SG_Contact_Edit_Text;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Blocked numbers']")
	public static WebElement SG_Blocked_Number;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Me']")
	public static WebElement SG_ME;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static WebElement SG_Menu_Contact_String;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static WebElement SG_Settings_dailer;

	@AndroidFindBy(id="com.android.dialer:id/dialer_digits")
	public static WebElement SG_dialer_digit;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Please input number or name']")
	public static WebElement SG_Please_input_number;

	@AndroidFindBy(id="com.android.dialer:id/primary_action_view")
	public static WebElement SG_dialer_History_List;

	@AndroidFindBy(id="android:id/button1")
	public static WebElement SG_clear_button;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear all call log']")
	public static WebElement SG_Clear_Call_Log;

	@AndroidFindBy(id="com.android.dialer:id/cliv_name_textview")
	public static WebElement SG_Create_New_Contact;

	//Locators for Change PIN
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Change PIN']")
	public static WebElement SG_Change_Pin_String;

	@AndroidFindBy(id="com.sonim.safeguard:id/old_pinET")
	public static WebElement SG_Old_Pin;

	@AndroidFindBy(id="com.sonim.safeguard:id/new_pinET")
	public static WebElement SG_New_Pin;

	@AndroidFindBy(id="com.sonim.safeguard:id/reenter_pinET")
	public static WebElement SG_Reenter_Pin;

	@AndroidFindBy(id="com.sonim.safeguard:id/okButton")
	public static WebElement SG_OK_Btn_For_Change_Pin;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Activation']")
	public static WebElement SG_Activation_Text;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Input PIN']")
	public static WebElement SG_Input_PIN;


 //Locators used for all device apps
    
    @AndroidFindBy(id="com.caf.fmradio:id/btn_onoff")
  	public static WebElement SG_FM;
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Messaging']")
  	public static WebElement SG_Message;
    
    @AndroidFindBy(id="org.codeaurora.snapcam:id/shutter_button")
  	public static WebElement SG_Camera;
    
    @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/recordButton']")
  	public static WebElement SG_Sound_Recoder;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='SonimSetupWizard']")
  	public static WebElement SG_SetUpwizard;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
  	public static WebElement SG_Contact;
    
    @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='dial pad']")
  	public static WebElement SG_RecentCalls;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Please insert Storage card before taking backup']")
  	public static WebElement SG_BackUp_restore;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim BLE Connect']")
  	public static WebElement SG_BLE_Connect;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='ALLOW']")
  	public static WebElement allow_Btn;
    
    @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
  	public static WebElement SG_BLE_AllowScreen;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Albums']")
  	public static WebElement SG_Album;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Security']")
  	public static WebElement SG_Security;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='More']")
  	public static WebElement SG_More;
    

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Tethering & portable hotspot']")
  	public static WebElement SG_Teathering;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='My Notifications']")
  	public static WebElement SG_MyNotification;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='WLAN']")
   	public static WebElement SG_WIFI;
    
     
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Wireless & networks']")
  	public static WebElement SG_wirelessNw;
    
    @FindBy(id= "android:id/content")
    public static List<WebElement> notificationPanel;
    
    @AndroidFindBy(id="com.android.calculator2:id/simple_pad_circle")
  	public static WebElement SG_Calc;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Agenda']")
  	public static WebElement SG_Calender;
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Downloads']")
  	public static WebElement SG_Downlaod;
    
    @AndroidFindBy(className="android.widget.FrameLayout")
  	public static AndroidElement SG_scroll;
    
  //Locators for Applications
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Contacts']")
  	public static WebElement SG_Contact_Text;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
  	public static WebElement SG_Find_Contacts;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Backup & reset']")
  	public static WebElement SG_Factory_reset;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Bluetooth']")
  	public static WebElement SG_Bluetooth;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Add new contact']")
  	public static WebElement SG_Add_new_Contact;
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Use USB to']")
  	public static WebElement SG_Use_USB_to;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
  	public static WebElement SG_Native_Settings;

    @AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Phone']")
  	public static WebElement SG_Phone_Icon;

  //Locators for Application Pintimeout
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Application PIN Timeout']")
  	public static WebElement SG_App_Pin_TimeOut_String;
    
    @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='30 Seconds']")
  	public static WebElement SG_App_Pin_TimeOut_30_Sec;
    
    @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='1 Minute']")
  	public static WebElement SG_App_Pin_TimeOut_1_Min;
    
    @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Navigate up']")
   	public static WebElement SG_Navigate_Up;
    
    @AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss Sonim Scout.']")
	 public static WebElement DismissIconCare;
    
    @AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss Contacts.']")
	 public static WebElement DismissIconSG;
	
    
}
