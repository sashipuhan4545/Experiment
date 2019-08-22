package com.xp8.util;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_XP8_Sanity {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_XP8_Sanity(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	@AndroidFindBy(xpath="//*[@text='CLOSE']")
	public static AndroidElement close;

	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/floating_action_button' or @resource-id='com.google.android.dialer:id/fab']")
	public static AndroidElement dailerPad;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add call\")")
	public static AndroidElement add_Call;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/digits' or @resource-id='com.google.android.dialer:id/digits']")
	public static AndroidElement enterNumFiled;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='dial']")
	public static AndroidElement dail;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='UsbMode Test']")
	public static AndroidElement usbMode_Test;

	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='TEST_MODE']")
	public static AndroidElement test_Mode;

	@AndroidFindBy(xpath="//*[@text='YES']")
	public static AndroidElement yes;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Date & time']")
	public static AndroidElement dateAndTime;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Automatic date & time']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement auto_DateTime_Enabled;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Automatic date & time']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement auto_DateTime_Disabled;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Automatic time zone']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement auto_TimeZone_Enabled;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Automatic time zone']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement auto_TimeZone_Disabled;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Use 24-hour format']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement hour_format_Enabled;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Use 24-hour format']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement hour_format_Disabled;

	@AndroidFindBy(xpath="//*[@text='Set date']/..//*[@index='1']")
	public static AndroidElement date_in_SetDate;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Set time']/..//android.widget.TextView[@index='1']")
	public static AndroidElement time_in_SetTime;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select time zone']/..//android.widget.TextView[@index='1']")
	public static AndroidElement timeZone;

	@AndroidFindBy(xpath="//*[@text='Battery is full' or @text='Sonim AppKey Policy']")
	public static AndroidElement batteryFullorAppKey;

	@AndroidFindBy(xpath="//*[@text='OK']") 
	public static AndroidElement OK;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"OK\")") 
	public static AndroidElement OK_1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"OK\")") 
	public static AndroidElement OK_2;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='LATER']")
	public static AndroidElement later_Btn;

	@AndroidFindBy(xpath="//*[@text='SKIP']")
	public static AndroidElement skip_Btn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Battery']")
	public static AndroidElement battery;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.settings:id/estimation\")")
	public static AndroidElement charging_over_USB ;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Full']")
	public static AndroidElement battery_Full;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='More']")
	public static AndroidElement more;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Turn off airplane mode to make a call.']")
	public static AndroidElement turnOff_Airplane_PopUp;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular networks']")
	public static AndroidElement cellular_Networks;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Access Point Names']")
	public static AndroidElement accessPointNames;

	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.android.settings:id/text_layout']")
	public static List<AndroidElement> apns; // apns Means APN's

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.systemui:id/keyguard_carrier_text']")
	public static AndroidElement operatorName_Lockscreen;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement airplane_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement airplane_OffState;

	@AndroidFindBy(xpath="//*[contains(@content-desc,'Wi-Fi Off')]")
	public static AndroidElement wifi_OffState_QuickPanel;

	@AndroidFindBy(xpath="//*[contains(@content-desc,'Wi-Fi On,Wi-Fi three bars')]")
	public static AndroidElement wifi_OnState_QuickPanel;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement switch_On_State;

	@AndroidFindBy(xpath="//android.widget.Switch[@text='OFF']")
	public static AndroidElement switch_Off_State;

	@AndroidFindBy(xpath="//*[@text='DONE']")
	public static AndroidElement done_Btn;

	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Bluetooth off.,Open Bluetooth settings.']")
	public static AndroidElement BT_OffState_QuickPanel;

	@AndroidFindBy(xpath="//*[@text='Off']//*[@text='Airplane mode']")
	public static AndroidElement airplane_OffState_QuickPanel;

	@AndroidFindBy(xpath="//*[@text='On']//*[@text='Airplane mode']")
	public static AndroidElement airplane_OnState_QuickPanel;

	@AndroidFindBy(xpath="//android.widget.Switch[@content-desc='Auto-rotate screen,Set to Portrait']")
	public static AndroidElement portrait;

	@AndroidFindBy(xpath="//*[@text='Off' and contains(@content-desc,'Auto-rotate screen')]")
	public static AndroidElement autoRotate_Off;//In notificationBar.

	@AndroidFindBy(xpath="//*[@text='On' and contains(@content-desc,'Auto-rotate screen')]")
	public static AndroidElement autoRotate_On;//In notificationBar.

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.systemui:id/settings_button']")
	public static AndroidElement settingsIcon_QuickPanel;

	@AndroidFindBy(xpath="//*[@text='Settings']")
	public static AndroidElement settingsText_SettingsApp;

	@AndroidFindBy(xpath="//*[@content-desc='End Call' or @content-desc='End call']")
	public static AndroidElement end_Call;

	@AndroidFindBy(xpath="//*[contains(@content-desc,'Call History') or contains(@text,'Recent')]")
	public static AndroidElement call_History;

	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/name' or @resource-id='com.google.android.dialer:id/name']")
	public static AndroidElement first_No_In_CallLog;

	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	public static AndroidElement moreOptions;// This Locator is for tagName "android.widget.ImageButton".

	@AndroidFindBy(xpath="//*[@text='Your call log is empty' or @text='Your call history is empty']")
	public static AndroidElement call_Log_Empty;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete APN']")
	public static AndroidElement deleteApn;

	@AndroidFindBy(xpath="//*[@text='Call History' or @text='Call history']")
	public static AndroidElement callHistory_MoreOptions;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static AndroidElement moreOptions_CallHistory;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear call history']")
	public static AndroidElement clear_CallHistory;

	@AndroidFindBy(xpath="//android.widget.Button[@text='0 selected']")
	public static AndroidElement selected_Option;

	@AndroidFindBy(xpath="//*[@text='Select all']")
	public static AndroidElement selectAll;

	@AndroidFindBy(xpath="//*[@text='1 selected']")
	public static AndroidElement oneSelected;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR']")
	public static AndroidElement clear;

	@AndroidFindAll(@AndroidFindBy(xpath="//android.widget.LinearLayout[@text='']"))
	public static List<AndroidElement> callHistory_List;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Call 080 4030 2040']")
	public static AndroidElement call_08040302040;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.dialer:id/primary_action_button\")")
	public static AndroidElement dialBtn_Call_Log;

	@AndroidFindBy(xpath="//*[@content-desc='add new contact' or @resource-id='com.android.contacts:id/floating_action_button']")
	public static AndroidElement add_NewContact;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Name']")
	public static AndroidElement name_NewContact;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Phone']")
	public static AndroidElement phone_NewContact;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Email']")
	public static AndroidElement email_NewContact;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static AndroidElement settings_Contact;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Default account for new contacts']")
	public static AndroidElement defaultAccount_NewContacts;

	@AndroidFindBy(xpath="//*[@text='PHONE']")
	public static AndroidElement PHONE_RadioBtn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").descriptionContains(\"Navigate\")")
	public static AndroidElement navigateUp;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='More fields']")
	public static AndroidElement moreFields_NewContact;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Address']")
	public static AndroidElement address_NewContact;

	@AndroidFindBy(xpath="//*[@content-desc='Save'or @text='SAVE']")
	public static AndroidElement save_Icon;

	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Search']")
	public static AndroidElement search;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
	public static AndroidElement find_Contacts;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/cliv_name_textview']")
	public static AndroidElement searched_Contact; // This Locator is only for 1 seached contact

	@AndroidFindBy(xpath="//*[@text='Delete']")
	public static AndroidElement delete_moreOption;

	@AndroidFindBy(xpath="//*[@text='DELETE' or @text='Delete']")
	public static AndroidElement delete_Confirm;
	
	@AndroidFindBy(id="android:id/button1")
	public static AndroidElement delete_ConfirmBtn_bell;
	
	@AndroidFindBy(id="com.google.android.apps.messaging:id/start_new_conversation_button")
	public static AndroidElement new_SmsBtn_bell;
	
	@AndroidFindBy(id="com.google.android.apps.messaging:id/recipient_text_view")
	public static AndroidElement sms_ToField;
	
	@AndroidFindBy(id="com.google.android.apps.messaging:id/compose_message_text")
	public static AndroidElement sms_TextField_bell;
	
	@AndroidFindBy(id="com.google.android.apps.messaging:id/contact_picker_create_group")
	public static AndroidElement contactPicker_bell;
	
	@AndroidFindBy(id="com.google.android.apps.messaging:id/send_message_button_container")
	public static AndroidElement sms_sendBtn_bell;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Delete\")")
	public static AndroidElement delete_Btn_Bell;
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Saving to']")
	public static AndroidElement contact_SavingTo;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='PHONE']")
	public static AndroidElement savingTo_Phone;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='SIM Card']")
	public static AndroidElement savingTo_SIM;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.contacts:id/icon']")
	public static AndroidElement callButton_Contact;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='No contacts']")
	public static AndroidElement no_Contacts;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/create\")")
	public static AndroidElement add_NewSMS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button\")")
	public static AndroidElement add_NewSMS_Sprint;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/start_new_conversation_button']")
	public static AndroidElement add_NewSMS1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"To\")")
	public static AndroidElement TO_Field;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").textContains(\"Type a name\")")	
	public static AndroidElement TO_Field1;

/*	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type message\")")
	public static AndroidElement messageField;*/
	
	@AndroidFindBy(id="com.android.mms:id/embedded_text_editor")
	public static AndroidElement messageField;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
	public static AndroidElement messageField1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Text message\")")
	public static AndroidElement messageField2;

	@AndroidFindBy(xpath="//*[@resource-id='com.android.mms:id/send_button_sms' or @resource-id='com.android.mms:id/send_button_mms']")
	public static AndroidElement send_Icon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")")
	public static AndroidElement send_Icon1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/contact_picker_create_group\")")
	public static AndroidElement contactPicker;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/recipients_picker']")
	public static AndroidElement recipients_Picker;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_search\")")
	public static AndroidElement searchContacts; // used search contacts from SMS APP

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement moreOptions_Contact;

	@AndroidFindBy(xpath="//*[@text='Search']")
	public static AndroidElement search_1; // to enter text into search field.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.contacts:id/search_view\")")
	public static AndroidElement search_2; 
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']")
	public static AndroidElement searched_Contact_1;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/btn_ok']")
	public static AndroidElement Ok_SMS;

	@AndroidFindBy(xpath="//*[contains(@text,'Now') or contains(@text,'Not sent') or contains(@text,'now') or contains(@text,'Just now')]")
	public static AndroidElement now_Text;	

	@AndroidFindBy(xpath="new UiSelector().className(\"android.widget.TextView\").textContains(\"Not sent\")")
	public static AndroidElement not_Sent_Text;

	@AndroidFindBy(xpath="//*[@resource-id='com.android.mms:id/avatar']/../..//*[@index='1']//*[@resource-id='com.android.mms:id/from']")
	public static AndroidElement firstSMS_InList;
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='android:id/list']/android.widget.FrameLayout[@index=0]")
	public static AndroidElement firstSMS_InList_Bell;

	@AndroidFindBy(xpath="//*[@index='0' and @resource-id='com.google.android.apps.messaging:id/swipeableContainer']")
	public static AndroidElement firstSMS_InList1;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete thread']")
	public static AndroidElement delete_Thread;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete']")
	public static AndroidElement delete_Sprint;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement mobileData_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement mobileData_OffState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement cellularData_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement cellularData_OffState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mobile data']/../..//com.android.systemui[@index='1']")
	public static AndroidElement mobileData_EnabledIcon_NotificationBar;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']")
	public static AndroidElement urlBar_Chrome;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").textContains(\"Search or type\")")
	public static AndroidElement SearchOrType_URL;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"ACCEPT\")")
	public static AndroidElement ACCEPTCONTINUE; //This Locator is in Chrome Breowser.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"NEXT\")")
	public static AndroidElement NEXT;  //This Locator is in Chrome Breowser.	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"NO THANKS\")")
	public static AndroidElement NO_THANKS;  //This Locator is in Chrome Breowser.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"NEXT\")")
	public static AndroidElement NEXT_Msg;  // This Locator is in Messaging.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.packageinstaller:id/permission_allow_button\")")
	public static AndroidElement allow_Permission;  // This Locator is in Messaging.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.launcher3:id/all_apps_handle\")")
	public static AndroidElement all_apps_handle;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"NO THANKS\")")
	public static AndroidElement NO_THANKS1;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement app_List;

	@AndroidFindBy(xpath="//*[@text='ACCEPT' or @text='AGREE']")
	public static AndroidElement ACCEPTorAGREE;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ACCEPT\")")
	public static AndroidElement ACCEPT;

	@AndroidFindBy(xpath="//android.view.View[@text='Google offered in:']")
	public static AndroidElement google_Logo;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='www.google.co.in']")
	public static AndroidElement googleCoIn_Text;

	@AndroidFindBy(xpath="//*[text()='No internet']")
	public static WebElement no_Internet_Chrome;

	@AndroidFindBy(xpath="//*[@class='icon icon-offline']")
	public static WebElement offline_Icon_Chrome;	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.chrome:id/tab_switcher_button\")")
	public static AndroidElement switcherButton_Chrome;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.chrome:id/menu_button\")")
	public static AndroidElement menuButton_Chrome;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Close all tabs\")")
	public static AndroidElement closeAllTabs_Chrome;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tethering & portable hotspot']")
	public static AndroidElement tetheringAndPortableHotspot;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='USB tethering']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement usbTethering_OffState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='USB tethering']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement usbTethering_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bluetooth tethering']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement BT_Tethering_OffState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bluetooth tethering']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement BT_Tethering_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Portable Wi-Fi hotspot']")
	public static AndroidElement portableWiFi_hotspot;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Portable Wi-Fi hotspot']/..//android.widget.Switch[@text='OFF']")
	public static AndroidElement portableWiFi_hotspot_OffState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Portable Wi-Fi hotspot']/..//android.widget.Switch[@text='ON']")
	public static AndroidElement portableWiFi_hotspot_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Entitlement Error']")
	public static AndroidElement entitlement_Error;

	@AndroidFindBy(xpath="//*[@content-desc='Hang up']")
	public static AndroidElement hangUp;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"android:id/right_icon\")")
	public static AndroidElement ongoingCall_Icon;

	// Below 4 locators for Autosuggestion text in Chrome.
	@AndroidFindBy(xpath="//*[@text='www.amazon.com']")
	public static AndroidElement amazonCom_Text; // This Locator is for Auto Suggestion in Chrome.

	@AndroidFindBy(xpath="//*[@text='www.ebay.com']")
	public static AndroidElement ebayCom_Text; // This Locator is for Auto Suggestion in Chrome.

	@AndroidFindBy(xpath="//*[@text='www.yahoo.com']")
	public static AndroidElement yahooCom_Text; // This Locator is for Auto Suggestion in Chrome.

	@AndroidFindBy(xpath="//*[@text='www.facebook.com']")
	public static AndroidElement facebookCom_Text; // This Locator is for Auto Suggestion in Chrome.

	// Below 4 locators are the logo for above 4 URL.
	@AndroidFindBy(xpath="//*[@text='ref=navm_hdr_logo']")
	public static AndroidElement amazon_Logo;

	@AndroidFindBy(xpath="//*[@text='Cart']")
	public static AndroidElement amazon_CartLogo;

	@AndroidFindBy(xpath="//*[@text='eBay']")
	public static AndroidElement eBay_Logo;

	@AndroidFindBy(xpath="//*[@text='Yahoo']")
	public static AndroidElement yahoo_Logo;

	@AndroidFindBy(xpath="//*[@text='facebook']")
	public static AndroidElement facebook_Logo;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='LanguagesÂ & input']")
	public static AndroidElement languageAndInput;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Languages']")
	public static AndroidElement languages;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Add a language']")
	public static AndroidElement addLanguage;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='android:id/search_src_text']")
	public static AndroidElement typeLanguageName;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='English']")
	public static AndroidElement english_Language;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='FranÃ§ais']")
	public static AndroidElement french_Language;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='EspaÃ±ol']")
	public static AndroidElement spanish_Language;

	@AndroidFindBy(xpath="//*[@text='att.net' or @text='start.att' or @text='Home - Welcome to att.net']")
	public static AndroidElement attNet_Logo;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bookmarks']")
	public static AndroidElement bookmarks_Chrome;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Partner Bookmarks']")
	public static AndroidElement partner_Bookmarks;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Restore']")
	public static AndroidElement restore;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Torch On']")
	public static AndroidElement torch_On_Notification;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Flashlight']")
	public static AndroidElement flashLight_Icon;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='https://www.ndtv.com']")
	public static AndroidElement ndtvCom_Text;

	@AndroidFindBy(xpath="//android.view.View[@text='www.ndtv']")
	public static AndroidElement ndtv_logo_1;

	@AndroidFindBy(xpath="//android.widget.Image[@text='Z']")
	public static AndroidElement ndtv_logo_2;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='https://www.bbc.com']")
	public static AndroidElement bbcCom_Text;

	@AndroidFindBy(xpath="//android.view.View[@text='BBC']")
	public static AndroidElement bbc_logo_1;

	@AndroidFindBy(xpath="//android.widget.Image[@text='Sign in']")
	public static AndroidElement bbc_logo_2;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='https://www.cnbc.com']")
	public static AndroidElement cnbcCom_Text;

	@AndroidFindBy(xpath="//android.view.View[@text='logo ']")
	public static AndroidElement cnbc_logo_1;

	@AndroidFindBy(xpath="//*[contains(@text,'CNBC')]")
	public static AndroidElement cnbc_logo_2;

	@AndroidFindBy(xpath="//android.widget.Button[@text='0']")
	public static AndroidElement zero;

	@AndroidFindBy(xpath="//android.widget.Button[@text='1']")
	public static AndroidElement one;

	@AndroidFindBy(xpath="//android.widget.Button[@text='2']")
	public static AndroidElement two;

	@AndroidFindBy(xpath="//android.widget.Button[@text='3']")
	public static AndroidElement three;

	@AndroidFindBy(xpath="//android.widget.Button[@text='4']")
	public static AndroidElement four;

	@AndroidFindBy(xpath="//android.widget.Button[@text='5']")
	public static AndroidElement five;

	@AndroidFindBy(xpath="//android.widget.Button[@text='6']")
	public static AndroidElement six;

	@AndroidFindBy(xpath="//android.widget.Button[@text='7']")
	public static AndroidElement seven;

	@AndroidFindBy(xpath="//android.widget.Button[@text='8']")
	public static AndroidElement eight;

	@AndroidFindBy(xpath="//android.widget.Button[@text='9']")
	public static AndroidElement nine;

	@AndroidFindBy(xpath="//android.widget.Button[@text='.']")
	public static AndroidElement dot;

	@AndroidFindBy(xpath="//android.widget.Button[@text='=']")
	public static AndroidElement equal;

	@AndroidFindBy(xpath="//android.widget.Button[@text='+']")
	public static AndroidElement plus;

	@AndroidFindBy(xpath="//android.widget.Button[@text='âˆ’']")
	public static AndroidElement minus;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Ã—']")
	public static AndroidElement multiply;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Ã·']")
	public static AndroidElement divide;

	@AndroidFindBy(xpath="//android.widget.Button[@text='DEL']")
	public static AndroidElement delete;

	@AndroidFindBy(xpath="//android.widget.Button[@text='CLR']")
	public static AndroidElement clear_Calci;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.google.android.calculator:id/result']")
	public static AndroidElement result_Calci;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/listButton']")
	public static AndroidElement listButton_SoundRec;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/recordButton']")
	public static AndroidElement recordButton;	

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/stopButton']")
	public static AndroidElement stopButton;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Spinner\").resourceId(\"com.android.soundrecorder:id/selection_spinner\")")
	public static AndroidElement selectionSpinner;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.soundrecorder:id/list_item_title\")")
	public static AndroidElement file_list_SoundRec;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.android.soundrecorder:id/list_empty\")")
	public static AndroidElement list_Empty;

	@AndroidFindBy(xpath="//*[@content-desc='Navigate up']")
	public static AndroidElement navigate_Up;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.soundrecorder:id/rename_edit_text\")")
	public static AndroidElement rename_Edit_Text;

	@AndroidFindBy(xpath="//android.widget.Button[@text='SAVE']")
	public static AndroidElement save_SoundRec;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='SoundRecorder']")
	public static AndroidElement soundRecFolder_FileMng;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.soundrecorder:id/action_delete']")
	public static AndroidElement delete_SoundRec;

	@AndroidFindBy(xpath="//*[@text='ALARM' or @text='Alarm']")
	public static AndroidElement alarm;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Add alarm']")
	public static AndroidElement add_Alarm;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Delete']")
	public static AndroidElement delete_Alarm;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLOCK']")
	public static AndroidElement CLOCK;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.google.android.deskclock:id/digital_clock']")
	public static AndroidElement time_InClock;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.google.android.deskclock:id/date']")
	public static AndroidElement date_InClock;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.google.android.deskclock:id/fab']")
	public static AndroidElement deskClock;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='TIMER']")
	public static AndroidElement TIMER;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.google.android.deskclock:id/timer_setup_time']")
	public static AndroidElement timer_Setup_Time;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='STOPWATCH']")
	public static AndroidElement STOPWATCH;

	@AndroidFindBy(xpath="//android.view.View[@resource-id='com.google.android.deskclock:id/stopwatch_circle']")
	public static AndroidElement stopwatch_Circle;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.google.android.deskclock:id/fab']")
	public static AndroidElement play_Stopwatch;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Storage']")
	public static AndroidElement storage;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/summary']")
	public static AndroidElement summary;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Security']")
	public static AndroidElement security;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Screen lock']")
	public static AndroidElement screenLock;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Swipe']")
	public static AndroidElement swipeLock;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='None']")
	public static AndroidElement noneLock;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Set up SIM card lock']")
	public static AndroidElement setUpSIM_cardLock;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Lock SIM card']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement lockSIMcard_disabledState;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='android:id/edit']")
	public static AndroidElement PIN_TextField;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Change SIM PIN']")
	public static AndroidElement changeSIM_PIN;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Old SIM PIN']")
	public static AndroidElement oldSIM_PINText;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='New SIM PIN']")
	public static AndroidElement new_SIMPINText;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Lock SIM card']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement lockSIMcard_EnabledState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='About phone']")
	public static AndroidElement about_Phone;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Status']")
	public static AndroidElement status;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='SIM status']")
	public static AndroidElement SIM_Status;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular network type']/..//android.widget.TextView[@text='LTE']")
	public static AndroidElement cellularNetworkType;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='USB Power Saving']")
	public static AndroidElement USBPower_Saving;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Automatic power ON']")
	public static AndroidElement automatic_PowerON;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mobile data']")
	public static AndroidElement mobileData_OnStateQuickPanel;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mobile data']")
	public static AndroidElement allow;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bluetooth']")
	public static AndroidElement bluetooth;

	@FindBy(xpath="//*[@resource-id='com.android.systemui:id/task_view_thumbnail']")
	public static AndroidElement recent_App;

	@AndroidFindBy(xpath="//com.android.launcher3.LauncherAppWidgetHostView[@content-desc='Google']")
	public static AndroidElement googleSearchBox;

	@AndroidFindBy(xpath="//*[@text='Build number']/..//*[@resource-id='android:id/summary']")
	public static AndroidElement buildNumber;
	
	@AndroidFindBy(xpath="//*[@text='Baseband version']/..//*[@resource-id='android:id/summary']")
	public static AndroidElement basebandVersion;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/shutter_button\")")
	public static AndroidElement capturePicture;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/preview_thumb\")")
	public static AndroidElement previewPicture;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.photos:id/details\")")
	public static AndroidElement pictureDetails;

	@AndroidFindBy(xpath="//*[@bounds='[222,435][1056,524]']")
	public static AndroidElement picture_Date_Camera;

	@AndroidFindBy(xpath="//*[@bounds='[222,524][1056,613]']")
	public static AndroidElement picture_Time_Camera;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/video_button\")")
	public static AndroidElement video_Button;

	@AndroidFindBy(xpath = "//*[@content-desc='New APN']")
	public static AndroidElement add_APN;

	@AndroidFindBy(xpath = "//*[@text='Name']")
	public static AndroidElement name_AddAPN;

	@AndroidFindBy(xpath = "//*[@text='APN']")
	public static AndroidElement apn_AddAPN;

	@AndroidFindBy(xpath = "//*[@text='Save' or @text='SAVE']")
	public static AndroidElement save;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/edit\")")
	public static AndroidElement textBox;

	@AndroidFindBy(xpath="//*[contains(@text,'roaming')]/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement dataRoaming_OffState;

	@AndroidFindBy(xpath="//*[contains(@text,'roaming')]/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement dataRoaming_OnState;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.caf.fmradio:id/btn_onoff\")")
	public static AndroidElement fm_OnOff_Button;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Scan\")")
	public static AndroidElement scan_FM;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"All Stations\")")
	public static AndroidElement all_Stations_FM;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.cyanogenmod.filemanager:id/ab_actions\")")
	public static AndroidElement actions_FileExpo;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.cyanogenmod.filemanager:id/ab_search\")")
	public static AndroidElement search_FileExpo;

	@AndroidFindBy(xpath="//*[contains(@text,'Check for Update') or contains(@text,'Update Software')]")
	public static AndroidElement checkForUpdates;

	@AndroidFindBy(xpath = "//*[contains(@text,'is up to date')]")
	public static AndroidElement currentSoftwareUptoDate;

	@AndroidFindBy(xpath = "//*[contains(@text,'No Updates Available')]")
	public static AndroidElement noUpdatesAvailable;

	@AndroidFindBy(xpath = "//*[contains(@text,'update is available') or contains(@text,'update available')]")
	public static AndroidElement softwareUpdateAvailable;

	@AndroidFindBy(xpath="//*[@text='Stay in portrait view' or @text='Stay in current orientation']")
	public static AndroidElement stayInPortritView;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Device Help\")")
	public static AndroidElement Device_Help;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Visual Voicemail\")")
	public static AndroidElement Visual_Voicemail;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"QTI Logkit\")")
	public static AndroidElement QTI_Logkit;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Play messages in any order\")")
	public static AndroidElement Play_messages_in_any_order;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement settings;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"FM Radio\")")
	public static AndroidElement fmRadio;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"File Manager\")")
	public static AndroidElement fileManager;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Camera\")")
	public static AndroidElement camera;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Clock\")")
	public static AndroidElement clock;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Chrome\")")
	public static AndroidElement chrome;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Calculator\")")
	public static AndroidElement calculator;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Sound Recorder\")")
	public static AndroidElement soundRecorder;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Voice Activation\")")
	public static AndroidElement Voice_Activation;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"WiPowerAgent\")")
	public static AndroidElement WiPowerAgent;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Hello from WiPower!\")")
	public static AndroidElement Hello_from_WiPower;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"When you turn on Voice Activation\")")
	public static AndroidElement when_you_turn_On_Voice_Activation;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Phone\")")
	public static AndroidElement phone;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Contacts\")")
	public static AndroidElement contacts;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messages\")")
	public static AndroidElement messages;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.contacts:id/search_back_button\")")
	public static AndroidElement backButton;	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").textContains(\"Search Apps\")")
	public static AndroidElement searchApps;
	
	@AndroidFindBy(xpath="//*[@text='Connected']")//*[@text='IDCSONWAP']/..
	public static AndroidElement wifi_ConnecteState;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement switch_OffState;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement switch_OnState;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.settings:id/password\")")
	public static AndroidElement wifiPasswordTextBox;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CONNECT\")")
	public static AndroidElement connect;

	@AndroidFindBy(xpath="//*[@text='XP5800' or @text='XP8800' or @text='KWSA50K' or @text='KWSA80K']")
	public static AndroidElement BT_Devices;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Portable storage\")")
	public static AndroidElement portable_Storage;

	@AndroidFindBy(xpath="//*[@text='IMEI' or contains(@text,'IMEI')]")
	public static AndroidElement imei_Popup;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"GROUPS\")")
	public static AndroidElement groups;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Add Group\")")
	public static AndroidElement add_Group;
	//"new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.contacts:id/group_name\")"
	@AndroidFindBy(xpath="//*[@text='Group's name']")
	public static AndroidElement groupName;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.contacts:id/addGroupMember\")")
	public static AndroidElement addGroupMember;

	@AndroidFindBy(xpath="//*[@bounds='[216,321][1032,386]']")
	public static AndroidElement boundsToPickContact;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Ok\")")
	public static AndroidElement Ok;

	@AndroidFindBy(xpath="//*[@content-desc='Done']")
	public static AndroidElement save1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/action_bar_title\")")
	public static AndroidElement groupName_InBarTitle;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/flash_button\")")
	public static AndroidElement flash_Button;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/front_back_switcher\")")
	public static AndroidElement front_back_switcher;//camera

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add account\")")
	public static AndroidElement add_Account;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement google_Account;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"identifierId\")")
	public static AndroidElement email_googleAcnt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Next\")")
	public static AndroidElement next;

	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	public static AndroidElement password_googleAcnt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"I agree\")")
	public static AndroidElement i_agree;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"MORE\")")
	public static AndroidElement MORE;

/*	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.settings:id/user_id\")")
	public static AndroidElement googleAccount_email;
	*/
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.settings:id/entity_header_title\")")
	public static AndroidElement googleAccount_email;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Play Store\")")
	public static AndroidElement PlayStore;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Security\")")
	public static AndroidElement Security;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Please choose follow\")")
	public static AndroidElement Please_choose_follow;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.vending:id/search_box_idle_text\")")
	public static AndroidElement google_Play;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Search Google Play\")")
	public static AndroidElement search_PlayStore;

	@AndroidFindBy(xpath="//*[@index='0' and contains(@text,'Apk Extract')]")
	public static AndroidElement firstApp_Suggetion;

	@AndroidFindBy(xpath="//*[contains(@content-desc,'Installed')]")
	public static AndroidElement installed_Playstore;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"INSTALL\")")
	public static AndroidElement INSTALL;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"UNINSTALL\")")
	public static AndroidElement UNINSTALL;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Apk Extractor\")")
	public static AndroidElement apkExtractor;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Remove account\")")
	public static AndroidElement remove_Account;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"REMOVE ACCOUNT\")")
	public static AndroidElement REMOVE_ACCOUNT;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"When Bluetooth is turned on\")")
	public static AndroidElement BT_Disable_Text;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Calendar\")")
	public static AndroidElement Calendar;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Google Calendar\")")
	public static AndroidElement google_Calendar;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Docs\")")
	public static AndroidElement docs;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.docs.editors.docs:id/heading\")")
	public static AndroidElement WriteOntheGo_Docs;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Downloads\")")
	public static AndroidElement downloads;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Drive\")")
	public static AndroidElement drive;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Duo\")")
	public static AndroidElement duo;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.tachyon:id/welcome_activity_intro_primary\")")
	public static AndroidElement High_quality_video_calling;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.cyanogenmod.filemanager:id/material_toolbar\")")
	public static AndroidElement material_toolbar_FileManager;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Gmail\")")
	public static AndroidElement gmail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.gm:id/welcome_tour_title\")")
	public static AndroidElement Welcome_to_Gmail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement google;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Keep\")")
	public static AndroidElement keep;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.googlequicksearchbox:id/search_box\")")
	public static AndroidElement Search_Google;

	@AndroidFindBy(xpath="//*[@text='Sign in' or @resource-id='headingText' or @text='Email or phone']")
	public static AndroidElement headingText_Google;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Maps\")")
	public static AndroidElement maps;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Make it your map\")")
	public static AndroidElement Make_it_yourMap;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Photos\")")
	public static AndroidElement photos;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.photos:id/uses_face_grouping\")")
	public static AndroidElement googlePhotos_uses_FaceGrouping;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Play Movies & TV\")")
	public static AndroidElement PlayMovies_TV;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.videos:id/welcome_title\")")
	public static AndroidElement welcome_title_PlayMoviesTV;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Play Music\")")
	public static AndroidElement PlayMusic;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Google Play Music\")")
	public static AndroidElement ToUse_GooglePlayMusic;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").resourceId(\"com.android.vending:id/unauth_home_sign_in_message\")")
	public static AndroidElement  home_sign_in_message_PlayStore;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.settings:id/search\")")
	public static AndroidElement  settings_search;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Search settings\")")
	public static AndroidElement  search_Settings;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Sheets\")")
	public static AndroidElement sheets;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.docs.editors.sheets:id/heading\")")
	public static AndroidElement  welcomeText_Spreadsheets;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Slides\")")
	public static AndroidElement slides;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.docs.editors.slides:id/heading\")")
	public static AndroidElement  welcomeText_Slides;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Sonim Scout\")")
	public static AndroidElement sonimScout;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Sonim Scout\")")
	public static AndroidElement scout_Heading;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Voice Search\")")
	public static AndroidElement voiceSearch;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"YouTube\")")
	public static AndroidElement youTube;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.googlequicksearchbox:id/display_text\")")
	public static AndroidElement listening;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.youtube:id/youtube_logo\")")
	public static AndroidElement youtube_Logo;

	@AndroidFindBy(xpath = "//*[@text='NOT NOW']")
	public static AndroidElement NOT_NOW;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Preferred network type\")")
	public static AndroidElement preferred_NetworkType;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Preferred network mode: GSM\")")
	public static AndroidElement Preferrednetworkmode_GSM;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Preferred network mode: UMTS/GSM\")")
	public static AndroidElement Preferrednetworkmode_UMTS;

	@AndroidFindBy(xpath="//*[contains(@text,'Preferred network mode:') and contains(@text,'LTE')]")
	public static AndroidElement Preferrednetworkmode_LTE;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ToggleButton\").resourceId(\"com.android.dialer:id/audioButton\")")
	public static AndroidElement audioButton;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ToggleButton\").resourceId(\"com.android.dialer:id/muteButton\")")
	public static AndroidElement muteButton;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Draft\")")
	public static AndroidElement draft;

	@AndroidFindBy(xpath="//*[@resource-id='com.android.mms:id/add_attachment_first' or @resource-id='com.google.android.apps.messaging:id/attach_media_button']")
	public static AndroidElement attachment;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement capturePicture_MMS;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/preview_btn_done\")")
	public static AndroidElement done;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/done_button\")")
	public static AndroidElement done_1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.apps.messaging:id/camera_capture_button\")")
	public static AndroidElement take_picture_MMS;

	@AndroidFindBy(xpath="//*[@text='Camera']")
	public static AndroidElement capture_pictures_or_video;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Gmail\")")
	public static AndroidElement g_mail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"GOT IT\")")
	public static AndroidElement GOT_IT;

	@AndroidFindBy(xpath="//*[@text='GOT IT' or @text='Got it']")
	public static AndroidElement GOT_IT_1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"TAKE ME TO GMAIL\")")
	public static AndroidElement TAKE_ME_TO_GMAIL;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.gm:id/compose_button\")")
	public static AndroidElement compose_gmail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.google.android.gm:id/to\")")
	public static AndroidElement To_gmail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.gm:id/subject\")")
	public static AndroidElement subject_gmail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").text(\"Compose email\")")
	public static AndroidElement compose_Mail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.gm:id/send\")")
	public static AndroidElement send_gmail;

	@AndroidFindBy(xpath="//*[contains(@content-desc,'this conversation')]")
	public static AndroidElement select_Mail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.gm:id/delete\")")
	public static AndroidElement delete_gmail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Connected\")")
	public static AndroidElement connected;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"NO THANKS\")")
	public static AndroidElement google_NothanksNotification;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.youtube:id/thumbnail\")")
	public static AndroidElement firstVideo_YT;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.ViewGroup\").resourceId(\"com.google.android.youtube:id/player_view\")")
	public static AndroidElement playerView_YT;
	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"More fields\")")
	public static AndroidElement more_Fields;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Group name\")")
	public static AndroidElement groupName_addContact; // This is in ADD new Contact.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Coworkers\")")
	public static AndroidElement co_workers;

	@AndroidFindBy(xpath = "//*[@text='Audio recordings']/..//*[@resource-id='com.google.android.music:id/li_thumbnail_frame']")
	public static AndroidElement audioRecords_Music;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Audio recordings\")")
	public static AndroidElement audioRecords_Music_1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"AutoFile\")")
	public static AndroidElement autoFile_Recorded_File;// File name is hardcoded for this Locators.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.music:id/play_pause_header\")")
	public static AndroidElement play_pause_header;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.CheckedTextView\").text(\"10 minutes\")")
	public static AndroidElement tenMin_Sleep;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.CheckedTextView\").text(\"30 minutes\")")
	public static AndroidElement thirtyMin_Sleep;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"After 10 minutes of inactivity\")")
	public static AndroidElement tenMinutes_Inactivity;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"After 30 minutes of inactivity\")")
	public static AndroidElement thirtyMinutes_Inactivity;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Big Easy\")")
	public static AndroidElement bigEasy_ringtone;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.calendar:id/right_arrow\")")
	public static AndroidElement right_arrow;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.calendar:id/floating_action_button\")")
	public static AndroidElement addEvent;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Event\")")
	public static AndroidElement event;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.calendar:id/title_edit_text\")")
	public static AndroidElement title_calender;

	@AndroidFindBy(uiAutomator="new UiSelector()..textContains(\"Incoming call\")")
	public static AndroidElement incoming_Call;//"//*[contains(@text,'Incoming call')]"

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Write your own\")")
	public static AndroidElement write_Your_Own;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").index(\"0\")")
	public static AndroidElement msg_popup;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"SEND\")")
	public static AndroidElement SEND;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/call_location_and_date\")")
	public static AndroidElement call_location_and_date;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Call details\")")
	public static AndroidElement call_Details;

	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/caller_name' or @resource-id='com.android.dialer:id/contact_name']")
	public static AndroidElement caller_name_callDetails;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/duration' or @resource-id='com.android.dialer:id/call_duration']")
	public static AndroidElement call_duration_callDetails;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/call_detail_delete_menu_item\")")
	public static AndroidElement call_detail_delete_menu_item;

	@AndroidFindBy(xpath="//*[@resource-id='com.android.settings:id/unmount']/../../..//*[@resource-id='android:id/summary']")
	public static AndroidElement sizeOfSDcard;	

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/summary']")
	public static AndroidElement summaryText;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.gm:id/dismiss_icon\")")
	public static AndroidElement dismiss_icon_Gmail;

	@AndroidFindBy(xpath = "//*[contains(@text,'SNOOZE')]")
	public static WebElement dismissAlarmHeadsUp;

	@AndroidFindBy(xpath = "//*[contains(@text,'Alarm')]")
	public static AndroidElement alarmHeadsUp; 

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").descriptionContains(\"Swipe left to snooze or right to dismiss\")")
	public static AndroidElement alarmRinging; 

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@content-desc='Google Search']")
	public static AndroidElement GoogleSearch_HomePage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.googlequicksearchbox:id/hint_text_alignment\")")
	public static AndroidElement googleSearch_HomeScreen;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.googlequicksearchbox:id/execute_button\")")
	public static AndroidElement alarm_Execute_button;

	@AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"Snoozing until\"))")
	public static AndroidElement snoozing_Until;

	
	
	
	
	
	
	
	
	
	
	
	
	



	//================================================================================================================================
	//================================================= ANdroid O Locators ============================================================
	//================================================================================================================================
		
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"android:id/icon\")")
	public static AndroidElement down_Triangle;

	@AndroidFindBy(xpath="//*[contains(@text,'Preferred network type')]/..//*[contains(@text,'LTE')]")
	public static AndroidElement PreferrednetworkType_LTE; 
	
	@AndroidFindBy(xpath="//*[contains(@text,'Preferred network type')]/..//*[contains(@text,'3G')]")
	public static AndroidElement PreferrednetworkType_3G;
	
	@AndroidFindBy(xpath="//*[contains(@text,'Preferred network type')]/..//*[contains(@text,'2G')]")
	public static AndroidElement PreferrednetworkType_2G;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Message+\")")
	public static AndroidElement MessagePlus;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"hum\")")
	public static AndroidElement Hum;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Welcome to Hum\")")
	public static AndroidElement welcome_To_Hum;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Music\")")
	public static AndroidElement Music;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Artists\")")
	public static AndroidElement Artists;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"My Verizon\")")
	public static AndroidElement MyVerizon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"OmaDMTest\")")
	public static AndroidElement OmaDMTest;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Security & Privacy\")")
	public static AndroidElement SecurityAndPrivacy;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Snapdragon Gallery\")")
	public static AndroidElement Snapdragon_Gallery;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Videos\")")
	public static AndroidElement Videos;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"No videos\")")
	public static AndroidElement No_videos;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Timeline\")")
	public static AndroidElement Timeline;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SDMFOTA\")")
	public static AndroidElement SDMFOTA;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Continue\")")
	public static AndroidElement Continue;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CONTINUE\")")
	public static AndroidElement CONTINUE;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Recent captures and events\")")
	public static AndroidElement Recent_captures_and_events;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Get Started\")")
	public static AndroidElement Get_Started;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ADVANCE\")")
	public static AndroidElement ADVANCE;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Your recordings\")")
	public static AndroidElement Your_Recordings;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Start Messaging\")")
	public static AndroidElement StartMessaging;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SKIP PROVISIONING\")")
	public static AndroidElement skipProvisioning;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.verizon.messaging.vzmsgs:id/composeFab']")
	public static AndroidElement add_NewSMS_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/curRecip\")")	
	public static AndroidElement TO_Field_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type a messageâ€¦\")")
	public static AndroidElement messageField_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Conversations\")")
	public static AndroidElement Conversations;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/composebtnSend\")")
	public static AndroidElement send_Icon_O;
	
	@AndroidFindBy(xpath="//*[contains(@text,'Delivered') or contains(@text,'Sent') or contains(@text,'now') or contains(@text,'Just now')]")
	public static AndroidElement Delivered;
	
	//	@AndroidFindBy(xpath="new UiSelector().className(\"android.widget.TextView\").textContains(\"Not sent\")")
	//	public static AndroidElement not_Sent_Text;
	//*[@index='0' and @resource-id='com.verizon.messaging.vzmsgs:id/avatar']
	@AndroidFindBy(xpath="//*[@resource-id='com.verizon.messaging.vzmsgs:id/date']")
	public static AndroidElement firstSMS_InList_O;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/imgGalleryOption\")")
	public static AndroidElement moreOption_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delete Messages\")")
	public static AndroidElement delete_Messages;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/select_All_conv\")")
	public static AndroidElement ALL_Msg;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/delete_btn\")")
	public static AndroidElement delete_Btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Speaker\")")
	public static AndroidElement speaker_call;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Mute\")")
	public static AndroidElement mute_call;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
	public static AndroidElement Delete;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.dialer:id/contact_name\")")
	public static AndroidElement caller_name_callDetails_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.dialer:id/call_duration\")")
	public static AndroidElement call_duration_callDetails_O;
	
	@AndroidFindBy(xpath="//*[@content-desc='Open navigation drawer' or @content-desc='More options']")
	public static AndroidElement open_Navigation_Drawer;
	
	@AndroidFindBy(xpath="new UiSelector().className(\"android.widget.Button\").text(\"SAVE\")")
	public static AndroidElement SAVE;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/addContact\")")
	public static AndroidElement addContact_Msg_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.contacts:id/search_view\")")
	public static AndroidElement search_contacts;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']")
	public static AndroidElement firstContact_OnSearch;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.verizon.messaging.vzmsgs:id/unread_conv_messages\")")
	public static AndroidElement unread_Conv_Messages;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.verizon.messaging.vzmsgs:id/from\")")
	public static AndroidElement from_ID_Msg;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/btnAttach\")")
	public static AndroidElement button_Attach_SMS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/btnQuickCamera\")")
	public static AndroidElement camera_MMS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/kanvas_shutter_icon\")")
	public static AndroidElement cameraCapture_Icon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/kanvas_send_icon\")")
	public static AndroidElement send_Cam_SMS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ADD CONTACT\")")
	public static AndroidElement add_Contact_O;
	
	@AndroidFindBy(xpath="//android.widget.Spinner[@content-desc='Phone']")
	public static AndroidElement mobile_dropdown;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement add_ContactIcon_O;
	
	@AndroidFindBy(xpath="//*[@text='CANCEL']")
	public static AndroidElement CANCEL;

	@AndroidFindBy(xpath="//*[contains(@content-desc,'Wi-Fi,') and contains(@text,'Off')]")
	public static AndroidElement wifi_OffState_QuickPanel_O;
	
/*	@AndroidFindBy(xpath="//*[contains(@content-desc,'Wi-Fi,Wifi three bars') and contains(@text,'On')]")
	public static AndroidElement wifi_OnState_QuickPanel_O;*/
	
	@AndroidFindBy(xpath="//android.widget.Switch[@index='0' and @text='On']")
	public static AndroidElement wifi_OnState_QuickPanel_O;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Bluetooth off') and contains(@text,'Off')]")
	public static AndroidElement BT_OffState_QuickPanel_O;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Bluetooth on') and contains(@text,'On')]")
	public static AndroidElement BT_OnState_QuickPanel_O;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Airplane mode') and contains(@text,'Off')]")
	public static AndroidElement airplane_OffState_QuickPanel_O;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Airplane mode') and contains(@text,'On')]")
	public static AndroidElement airplane_OnState_QuickPanel_O;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Mobile data') and contains(@text,'Off')]")
	public static AndroidElement mobileData_OffState_QuickPanel_O;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Mobile data') and contains(@text,'On')]")
	public static AndroidElement mobileData_OnState_QuickPanel_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Charging\")")
	public static AndroidElement charging_over_USB_O;

	@AndroidFindBy(xpath="//*[contains(@text,'Full') or contains(@text,'full')]")
	public static AndroidElement battery_Full_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.dialer:id/fab\")")
	public static AndroidElement dailerPad_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.dialer:id/primary_action_button\")")
	public static AndroidElement dialBtn_Call_Log_O;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.dialer:id/call_location_and_date\")")
	public static AndroidElement call_location_and_date_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.dialer:id/call_detail_action_delete\")")
	public static AndroidElement call_detail_delete_menu_item_O;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.chrome:id/tab_switcher_button']")
	public static AndroidElement switcherButton_Chrome_O;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.chrome:id/menu_button']")
	public static AndroidElement menuButton_Chrome_O;

	@AndroidFindBy(xpath="//*[@text='Close all tabs']")
	public static AndroidElement closeAllTabs_Chrome_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").textContains(\"Use your Google account\")")
	public static AndroidElement Use_your_Google_account;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.docs:id/heading\")")
	public static AndroidElement a_SafePlace_for_all_your_files;	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").resourceId(\"with your Google Account. \")")
	public static AndroidElement with_your_GoogleAccount;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"Music library\")")
	public static AndroidElement Music_library;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.caf.fmradio:id/record_msg_tv\")")
	public static AndroidElement record_Btn_FM;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"SKIP\")")
	public static AndroidElement SKIP;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement Google_chrome;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Alert\")")
	public static AndroidElement Alert_Popup;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Create label\")")
	public static AndroidElement create_Label;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Label name\")")
	public static AndroidElement label_Name;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").index(1)")
	public static AndroidElement label_Name_InTitleBar;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delete label\")")
	public static AndroidElement delete_Label;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"TURN OFF\")")
	public static AndroidElement TURN_OFF;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CANCEL\")")
	public static AndroidElement TURN_OFF1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").text(\"Sign in\")")
	public static AndroidElement Google_SignIn_page;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Google Search\")")
	public static AndroidElement Google_Search_chrome;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.google.android.googlequicksearchbox:id/text_container\")")
	public static AndroidElement Google_search_field;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Google Play Movies & TV\")")
	public static AndroidElement Google_playmovies_Tv;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.apps.docs:id/branded_fab\")")
	public static AndroidElement Google_drive_add;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Show navigation drawer']")
	public static AndroidElement play_music_page;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"PLAY NOW\")")
	public static AndroidElement play_now_playMusic;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Available devices\")")
	public static AndroidElement Available_devices;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.google.android.apps.gsa.searchplate.SearchPlate\").text(\"com.google.android.googlequicksearchbox:id/search_plate\")")
	public static AndroidElement search_bar;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='2']/../..//android.widget.TextView[contains(@text,'@gmail.com')]"))
	public static AndroidElement connectedAccount;
	
	@FindBy(id="android:id/button1")
	public static AndroidElement Wifi_connectBtn;
	
	@FindBy(id="com.android.soundrecorder:id/listButton")
	public static  AndroidElement AudioFiles_ListBtn;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@index=0]/android.widget.RelativeLayout")
	public static List<AndroidElement> AudioFile_Lists;
	
	@FindBy(id="com.android.soundrecorder:id/selection_spinner")
	public static  AndroidElement AudioFiles_SelecterBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement AudioFile_SelectAllBtn;
	
	
	@FindBy(id="com.android.soundrecorder:id/action_delete")
	public static  AndroidElement AudioFiles_deleteActionBtn;
	
	@FindBy(id="android:id/button1")
	public static AndroidElement deleteBtn;
	
	@FindBy(id="org.codeaurora.snapcam:id/scene_mode_switcher")
	public static AndroidElement camera_MenuBtn;
	
	@FindBy(id="org.codeaurora.snapcam:id/setting_button")
	public static AndroidElement camera_SettingBtn;	

	@FindBy(id="android:id/button1")
	public static AndroidElement camera_OKBtn;
	
	/*******************************youtube ********************************************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CANCEL UPDATES\")")
	public static AndroidElement playStore_CnclUpdatesBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"NOT NOW\")")
	public static AndroidElement youtube_NotNowBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\")")
	public static AndroidElement setdata;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Search\")")
	public static AndroidElement Search;
	
	
	/************************Password Box ****************************************/
	@AndroidFindBy(id ="com.android.settings:id/password")
	public static AndroidElement wifi_PsswdBx; // Resource ID
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").index(1)")
	public static AndroidElement wifi_PsswdBx_index; // index
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").instance(0)")
	public static AndroidElement wifi_PsswdBx_instance; // Instance
	
	
}
